package com.example.AuthenticationServer.Service;

import com.example.AuthenticationServer.DTO.UserDTO;
import com.example.AuthenticationServer.Exception.EmailExistException;
import com.example.AuthenticationServer.Exception.EmailNotFoundedException;
import com.example.AuthenticationServer.JWT.JWTUtility;
import com.example.AuthenticationServer.Model.User;
import com.example.AuthenticationServer.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo repo;
    private final PasswordEncoder encoder;
    private final JWTUtility jwtUtility;
    @Override
    public String persistUser(UserDTO userDTO) throws EmailExistException {
          if(isUserExist(userDTO.getEmail())){
              throw new EmailExistException(userDTO.getEmail());
          }
          User user = userDTO.DTOToEntity(userDTO,encoder);
          repo.save(user);
//         Now generate token...
        return generateToken(user.getEmail());
    }

    @Override
    public boolean isUserExist(String email) {
        Optional<User> user = repo.findById(email);
        return user.isPresent();
    }

    @Override
    public String checkCredentials(UserDTO userDTO)  {
//        User implements UserDetails...
          User user = (User) loadUserByUsername(userDTO.getEmail());
        return generateToken(user.getEmail());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = Optional.of(repo.findById(email).orElseThrow());
        return user.get();
    }

    @Override
    public UserDTO deleteUser(String email){
//        Check if the user exist or not...
        User user = (User) loadUserByUsername(email);
        repo.deleteById(email);
        return UserDTO
                .builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .userName(user.getUsername())
                .build();
    }

        @Override
        public UserDTO updateUser(UserDTO userDTO) throws EmailNotFoundedException {
            //        Check if the user exist or not...
            if(!isUserExist(userDTO.getEmail())){
                throw new EmailNotFoundedException(userDTO.getEmail());
            }
            User user  = userDTO.DTOToEntity(userDTO,encoder);
            repo.save(user);
            return
                    UserDTO
                            .builder()
                            .email(user.getEmail())
                            .password(user.getPassword())
                            .userName(user.getUsername())
                            .build();
    }

    @Override
    public UserDTO changePassword(Map<String, String> map) throws EmailNotFoundedException {
        String email = map.get("email");
        String password =  map.get("password");
        if(!isUserExist(email)){
            throw new EmailNotFoundedException(email);
        }
        User user = repo.findById(email).get();
        user.setPassword(password);
        repo.save(user);
        UserDTO userDTO =user.EntityToDTO(user);
        return userDTO;
    }

    public String generateToken(String email){
        return jwtUtility.generateToken(email);
    }

}

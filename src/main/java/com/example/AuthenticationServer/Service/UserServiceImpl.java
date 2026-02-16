package com.example.AuthenticationServer.Service;

import com.example.AuthenticationServer.DTO.UserDTO;
import com.example.AuthenticationServer.Exception.EmailExistException;
import com.example.AuthenticationServer.Model.User;
import com.example.AuthenticationServer.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserDTO userDTO;
    private final UserRepo repo;
    @Override
    public UserDTO persistUser(UserDTO userDTO) throws EmailExistException {
          if(isUserExist(userDTO.getEmail())){
              throw new EmailExistException(userDTO.getEmail());
          }
          User user = userDTO.DTOToEntity(userDTO);
          repo.save(user);
          return userDTO;
    }

    @Override
    public boolean isUserExist(String email) {
        Optional<User> user = repo.findById(email);
        return user.isPresent();
    }
}

package com.example.AuthenticationServer.DTO;

import com.example.AuthenticationServer.Model.User;
import jakarta.annotation.PostConstruct;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class UserDTO {

    UserDTO(){

    }

    @NotNull
    private String email;
    @NonNull
    private String password;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public User DTOToEntity(UserDTO userDTO,PasswordEncoder encoder){
       try{
           System.out.println("Endcoder: "+(encoder));
           String encodedPassword = encoder.encode(userDTO.getPassword());
           return User
                   .builder()
                   .email(userDTO.getEmail())
                   .password(encodedPassword)
                   .build();
       }
       catch (Exception e){
           log.error(e.getMessage());
           throw new RuntimeException();
       }
    }
}

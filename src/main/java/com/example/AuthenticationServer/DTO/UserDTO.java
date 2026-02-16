package com.example.AuthenticationServer.DTO;

import com.example.AuthenticationServer.Model.User;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    @NotNull
    private String email;
    @NonNull
    private String password;
    public User DTOToEntity(UserDTO userDTO){
        return User
                .builder()
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
    }
}

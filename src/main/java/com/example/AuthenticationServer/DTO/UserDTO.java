package com.example.AuthenticationServer.DTO;

import com.example.AuthenticationServer.Model.User;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Builder
@Component
@RequiredArgsConstructor
public class UserDTO {

    @NotNull
    private String email;
    @NonNull
    private String password;
    private final PasswordEncoder encoder;
    public User DTOToEntity(UserDTO userDTO){
        String encodedPassword = encoder.encode(userDTO.getPassword());
        return User
                .builder()
                .email(userDTO.getEmail())
                .password(encodedPassword)
                .build();
    }
}

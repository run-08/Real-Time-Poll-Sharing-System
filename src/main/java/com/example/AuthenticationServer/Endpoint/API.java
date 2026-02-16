package com.example.AuthenticationServer.Endpoint;

import com.example.AuthenticationServer.DTO.UserDTO;
import com.example.AuthenticationServer.Exception.EmailExistException;
import com.example.AuthenticationServer.Service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class API {

    private final UserServiceImpl userService;
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> saveUser(@Validated @RequestBody UserDTO userDTO) throws EmailExistException {
        userDTO = userService.persistUser(userDTO);
        return ResponseEntity
                .ok(userDTO);
    }
}

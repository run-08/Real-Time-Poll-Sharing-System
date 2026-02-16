package com.example.AuthenticationServer.Endpoint;

import com.example.AuthenticationServer.DTO.UserDTO;
import com.example.AuthenticationServer.Exception.EmailExistException;
import com.example.AuthenticationServer.Service.UserServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public ResponseEntity<String> checkUserCredentials(@Validated @RequestBody UserDTO userDTO){
        String token = userService.checkCredentials(userDTO);
        return ResponseEntity.ok(token);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<UserDTO> deleteUser(@RequestParam("email") String email){
        UserDTO userDTO = userService.deleteUser(email);
        return ResponseEntity
                .ok(userDTO);
    }
}

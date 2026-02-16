package com.example.AuthenticationServer.Endpoint;
import com.example.AuthenticationServer.DTO.UserDTO;
import com.example.AuthenticationServer.Exception.EmailExistException;
import com.example.AuthenticationServer.Exception.EmailNotFoundedException;
import com.example.AuthenticationServer.Service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class API {

    private final UserServiceImpl userService;

    @PostMapping("/signup")
    public ResponseEntity<String> saveUser(@Validated @RequestBody UserDTO userDTO) throws EmailExistException {
        String token = userService.persistUser(userDTO);
        return ResponseEntity
                .ok(token);
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

        @PutMapping("/updateUser")
    public ResponseEntity<UserDTO> updateUser(@Validated @RequestBody UserDTO userDTO) throws EmailNotFoundedException {
         userDTO = userService.updateUser(userDTO);
         return ResponseEntity
                 .ok(userDTO);
    }
}

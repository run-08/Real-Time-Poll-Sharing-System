package com.example.AuthenticationServer.Endpoint;
import com.example.AuthenticationServer.DTO.EmailRequest;
import com.example.AuthenticationServer.DTO.UserDTO;
import com.example.AuthenticationServer.Exception.EmailExistException;
import com.example.AuthenticationServer.Exception.EmailNotFoundedException;
import com.example.AuthenticationServer.Exception.OTPNumberFormatException;
import com.example.AuthenticationServer.Mail.MailServiceImpl;
import com.example.AuthenticationServer.Service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class API {

    private final UserServiceImpl userService;
    private final MailServiceImpl mailService;
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

    @PutMapping("/changePassword")
    public ResponseEntity<UserDTO> changePassword(Map<String,String> userDetails) throws EmailNotFoundedException {
        UserDTO userDTO = userService.changePassword(userDetails);
        return ResponseEntity.ok(userDTO);
    }
    @DeleteMapping("/deleteUser")
    public ResponseEntity<UserDTO> deleteUser(@RequestParam("email") String email){
        UserDTO userDTO = userService.deleteUser(email);
        return ResponseEntity
                .ok(userDTO);
    }
    @RequestMapping("/sendOTP")
    public ResponseEntity<Boolean> OTPSender(@RequestBody EmailRequest emailRequest) throws OTPNumberFormatException {
        System.out.println(emailRequest);
        mailService.sendOTP(emailRequest);
        return ResponseEntity.ok(true);
    }
}

package com.example.AuthenticationServer.GlobalException;

import com.example.AuthenticationServer.Exception.EmailExistException;
import com.example.AuthenticationServer.Exception.EmailNotFoundedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> handleError(Exception ex){
        Map<String,String> errorMessage = new HashMap<>();
        errorMessage.put("Date And Time: ", LocalDateTime.now().toString());
        errorMessage.put("Error: ",ex.getMessage());
        log.error("Error: "+ex.getMessage());
        return ResponseEntity
                .internalServerError()
                .body(errorMessage);
    }
    @ExceptionHandler(EmailNotFoundedException.class)
    public ResponseEntity<Map<String,String>> handleError(EmailNotFoundedException ex){
        Map<String,String> errorMessage = new HashMap<>();
        errorMessage.put("Date And Time: ", LocalDateTime.now().toString());
        errorMessage.put("Error: ",ex.getMessage());
        log.error("Error: "+ex.getMessage());
        return ResponseEntity
                .internalServerError()
                .body(errorMessage);
    }
    @ExceptionHandler(EmailExistException.class)
    public ResponseEntity<Map<String,String>> handleError(EmailExistException ex){
        Map<String,String> errorMessage = new HashMap<>();
        errorMessage.put("Date And Time: ", LocalDateTime.now().toString());
        errorMessage.put("Error: ",ex.getMessage());
        log.error("Error: "+ex.getMessage());
        return ResponseEntity
                .internalServerError()
                .body(errorMessage);
    }
}

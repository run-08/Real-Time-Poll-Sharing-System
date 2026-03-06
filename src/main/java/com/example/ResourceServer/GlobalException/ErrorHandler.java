package com.example.ResourceServer.GlobalException;


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
        log.error("Error Occurred: "+ex.getMessage());
        Map<String,String> errorMessage = new HashMap<>();
        errorMessage.put("Date And Time: ", LocalDateTime.now().toString());
        errorMessage.put("Error: ",ex.getMessage());
        System.out.println(errorMessage);
        return ResponseEntity
                .internalServerError()
                .body(errorMessage);
    }
}

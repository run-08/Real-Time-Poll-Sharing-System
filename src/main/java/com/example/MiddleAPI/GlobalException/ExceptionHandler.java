package com.example.MiddleAPI.GlobalException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> sendResponse(Exception ex){
        Map<String,String> map = new HashMap<>();
        map.put("Date And Time: ", LocalDateTime.now().toString());
        map.put("Error: ", ex.getMessage());
        return ResponseEntity.internalServerError().body(map);
    }
}

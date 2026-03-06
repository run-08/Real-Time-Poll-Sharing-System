package com.example.AuthenticationServer.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmailRequest {
    private String OTP;
    private String emailId;
    @Override
    public String toString(){
        return OTP+" "+emailId;
    }
}

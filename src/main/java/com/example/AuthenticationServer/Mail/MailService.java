package com.example.AuthenticationServer.Mail;

import com.example.AuthenticationServer.DTO.EmailRequest;
import com.example.AuthenticationServer.Exception.OTPNumberFormatException;
import org.springframework.stereotype.Service;

@Service
public interface MailService {
    public String OTPMailMessage(String OTP) ;
    public void sendOTP(EmailRequest emailRequest) throws OTPNumberFormatException;
}

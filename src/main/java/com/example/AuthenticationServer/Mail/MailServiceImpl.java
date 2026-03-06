package com.example.AuthenticationServer.Mail;

import com.example.AuthenticationServer.DTO.EmailRequest;
import com.example.AuthenticationServer.Exception.OTPNumberFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private  MailSender mailSender;

    @Override
    public void sendOTP(EmailRequest emailRequest) throws OTPNumberFormatException {
        SimpleMailMessage message = new SimpleMailMessage();
        String email = emailRequest.getEmailId();
        String OTP = emailRequest.getOTP();
        message.setTo(email);
        message.setSubject("RTPO -  OTP's here for changing the password!");
        message.setText(OTPMailMessage(OTP));
        mailSender.send(message);
    }
    @Override
    public String OTPMailMessage(String OTP) {
        StringBuilder bodyMessage = new StringBuilder();
        bodyMessage.append(
                "Dear User!\n Here is Your OTP <h1>"+(OTP)+"<h1> for changing your password!\n"+"Please do not share this with anyone!"
        );
        return bodyMessage.toString();
    }
}

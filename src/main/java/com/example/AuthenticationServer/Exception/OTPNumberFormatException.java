package com.example.AuthenticationServer.Exception;


public class OTPNumberFormatException extends Exception {
    public OTPNumberFormatException(int OTP)  {
        super((OTP)+" is invalid, only numeric allowed!");
    }
}

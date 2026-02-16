package com.example.AuthenticationServer.Exception;

public class EmailExistException extends Exception{
    public EmailExistException(String email){
        super(email+" already exists!");
    }
}

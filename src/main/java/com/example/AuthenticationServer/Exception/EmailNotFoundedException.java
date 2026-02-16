package com.example.AuthenticationServer.Exception;


public class EmailNotFoundedException extends Exception{
    public EmailNotFoundedException(String email){
         super(email+" not founded!");
    }
}

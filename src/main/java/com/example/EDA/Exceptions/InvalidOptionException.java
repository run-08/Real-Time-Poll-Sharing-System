package com.example.EDA.Exceptions;

public class InvalidOptionException extends Exception{
    public InvalidOptionException(Integer option){
        super(option+" didn't exist!");
    }
}

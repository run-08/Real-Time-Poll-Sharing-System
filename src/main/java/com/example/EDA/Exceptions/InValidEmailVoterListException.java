package com.example.EDA.Exceptions;

public class InValidEmailVoterListException extends Exception{
    public InValidEmailVoterListException(String email){
        super(email+" doesn't exist!");
    }
}

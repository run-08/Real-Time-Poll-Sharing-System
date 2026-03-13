package com.example.EDA.Exceptions;

public class InvalidEmailBasedVoterListException extends Exception{
    public InvalidEmailBasedVoterListException(String email){
        super(email+" doesn't exist EmailBasedVoterList!");
    }
}

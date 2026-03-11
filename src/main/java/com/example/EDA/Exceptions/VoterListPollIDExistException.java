package com.example.EDA.Exceptions;

public class VoterListPollIDExistException extends Exception{
    public VoterListPollIDExistException(String PollId){
        super(PollId+" didn't Exist!");
    }
}

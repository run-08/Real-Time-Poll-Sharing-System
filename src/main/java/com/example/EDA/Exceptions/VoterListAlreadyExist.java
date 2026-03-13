package com.example.EDA.Exceptions;

public class VoterListAlreadyExist extends Exception{
    public VoterListAlreadyExist(String pollId){
        super("PollId "+(pollId)+" already exists!");
    }
}

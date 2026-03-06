package com.example.ResourceServer.Exception;

public class PollIDExistException extends Exception{
    public PollIDExistException(String pollId){
        super(pollId+" already exists!");
    }
}

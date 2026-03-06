package com.example.ResourceServer.Exception;

public class PollIDNotFoundedException extends Exception{
    public PollIDNotFoundedException(String id){
        super(id+" not founded!");
    }
}

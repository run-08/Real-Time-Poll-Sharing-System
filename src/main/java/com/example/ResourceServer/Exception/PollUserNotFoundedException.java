package com.example.ResourceServer.Exception;

public class PollUserNotFoundedException extends Exception{
    public PollUserNotFoundedException(String emailId){
        super("Poll from this "+emailId+"is not founded!");
    }
}

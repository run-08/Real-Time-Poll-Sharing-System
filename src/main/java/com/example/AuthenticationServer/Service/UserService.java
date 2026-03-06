package com.example.AuthenticationServer.Service;

import com.example.AuthenticationServer.DTO.UserDTO;
import com.example.AuthenticationServer.Exception.EmailExistException;
import com.example.AuthenticationServer.Exception.EmailNotFoundedException;

import java.util.Map;

public interface UserService  {
    public String persistUser(UserDTO userDTO) throws EmailExistException;
    public boolean isUserExist(String email);
    public String checkCredentials(UserDTO userDTO);
    public UserDTO deleteUser(String email);
    public UserDTO updateUser(UserDTO userDTO) throws EmailNotFoundedException;

    public UserDTO changePassword(Map<String,String> map) throws EmailNotFoundedException;

}

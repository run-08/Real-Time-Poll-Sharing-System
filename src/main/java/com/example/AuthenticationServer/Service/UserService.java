package com.example.AuthenticationServer.Service;

import com.example.AuthenticationServer.DTO.UserDTO;
import com.example.AuthenticationServer.Exception.EmailExistException;

public interface UserService  {
    public UserDTO persistUser(UserDTO userDTO) throws EmailExistException;
    public boolean isUserExist(String email);
    public String checkCredentials(UserDTO userDTO);
    public UserDTO deleteUser(String email);
}

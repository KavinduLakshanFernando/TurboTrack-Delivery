package org.example.delivermanagementsystem.service;


import org.example.delivermanagementsystem.dto.UserDTO;

import java.util.List;

public interface UserService {
    int saveUser(UserDTO userDTO);
    UserDTO searchUser(String username);

    List<UserDTO> getAllUsers();

    int deleteUser(String email);

}
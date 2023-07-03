package com.studentmanagement.services;

import com.studentmanagement.entities.Users;

import java.util.List;

public interface UserService {
    Users registerNewUser(Users user);
    Users updateUser(Users user);

    Users getUserById(String userId);

    List<Users> getAllUsers();

    void deleteUser(String userId);
}

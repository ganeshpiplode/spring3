package com.studentmanagement.serviceImpl;

import com.studentmanagement.config.AppConstants;
import com.studentmanagement.entities.Role;
import com.studentmanagement.entities.Users;
import com.studentmanagement.exceptions.ResourceNotFoundException;
import com.studentmanagement.repositories.RoleRepository;
import com.studentmanagement.repositories.UserRepository;
import com.studentmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepo;

    @Override
    public Users registerNewUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepo.save(user);
    }
    @Override
    public Users updateUser(Users user) {
        System.out.println("Inside method updateUser");
        Users userDao = this.userRepo.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", " Id ", user.getId()));
        userDao.setUserName(user.getUsername());
        userDao.setEmail(user.getEmail());
        userDao.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepo.save(userDao);
    }

    @Override
    public Users getUserById(String userId) {
        return this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));
    }

    @Override
    public List<Users> getAllUsers() {
        return this.userRepo.findAll();
    }

    @Override
    public void deleteUser(String userId) {
        Users user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        this.userRepo.delete(user);
    }

}

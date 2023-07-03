package com.studentmanagement.controllers;


import java.util.List;
import com.studentmanagement.entities.Users;
import com.studentmanagement.payloads.ApiResponse;
import com.studentmanagement.payloads.ResponseController;
import com.studentmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    // PUT- update user
    @PutMapping("/")
    public ResponseEntity<Object> updateUser(@RequestBody Users user) {
        Users userDao = this.userService.updateUser(user);
        return ResponseController.generateResponse("Get User Successfully!", HttpStatus.OK, userDao);
    }

    //ADMIN
    // DELETE -delete user
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") String uid) {
        this.userService.deleteUser(uid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true), HttpStatus.OK);
    }

    // GET - user get
    @GetMapping("/")
    public ResponseEntity<Object> getAllUsers() {
        List<Users>userList = this.userService.getAllUsers();
        return ResponseController.generateResponse("Get All User Successfully!", HttpStatus.OK,userList);
    }

    // GET - user get
    @GetMapping("/{userId}")
    public ResponseEntity<Object> getSingleUser(@PathVariable String userId) {
         Users useDao = this.userService.getUserById(userId);
        return ResponseController.generateResponse("Get User Successfully!", HttpStatus.OK,useDao);
    }

}


package com.studentmanagement;

import static org.junit.jupiter.api.Assertions.*;
import com.studentmanagement.controllers.UserController;
import com.studentmanagement.entities.Users;
import com.studentmanagement.payloads.ApiResponse;
import com.studentmanagement.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class UserControllerTest extends StudentmanagementApplicationTests{

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    void updateUserTest(){
        try{
            System.out.println("Inside method updateUserTest");
            Mockito.when(userService.updateUser(Mockito.any())).thenReturn(updateUserData());
            ResponseEntity<Object> response = userController.updateUser(getUser());
            assertNotNull(response);
            assertEquals(HttpStatus.OK,response.getStatusCode());
        }catch (Exception e){
            System.out.println("Exception occur inside method updateUserTest "+ e);
        }
    }

    @Test
    void userListTest(){
        try{
            System.out.println("Inside method listTest");
            List<Users>userList = new ArrayList<>();
            userList.add(updateUserData());
            Mockito.when(userService.getAllUsers()).thenReturn(userList);
            ResponseEntity<Object> response = userController.getAllUsers();
            assertNotNull(response);
            assertEquals(HttpStatus.OK,response.getStatusCode());
        }catch (Exception e){
            System.out.println("Exception occur inside method updateUserTest "+ e);
        }
    }


    @Test
    void getUserTest(){
        try{
            System.out.println("Inside method getSingleTest");
            Mockito.when(userService.getUserById(Mockito.any())).thenReturn(updateUserData());
            ResponseEntity<Object> response = userController.getSingleUser("649bfc536996ff58414453e4");
            assertNotNull(response);
            assertEquals(HttpStatus.OK,response.getStatusCode());
        }catch (Exception e){
            System.out.println("Exception occur inside method updateUserTest "+ e);
        }
    }

    @Test
    void deleteUserTest(){
        try{
            System.out.println("Inside method deleteTest");
            Mockito.doNothing().when(this).userService.deleteUser(Mockito.any());
            ResponseEntity<ApiResponse> response = userController.deleteUser("649bfc536996ff58414453e4");
            assertNotNull(response);
            assertEquals(HttpStatus.OK,response.getStatusCode());
        }catch (Exception e){
            System.out.println("Exception occur inside method updateUserTest "+ e);
        }
    }

    Users getUser(){
        Users adduser = new Users();
        adduser.setEmail("mukesh@calsoftinc.com");
        adduser.setPassword("ramram@1234");
        adduser.setUserName("ganesh");
        return adduser;
    }


    Users updateUserData(){
        Users adduser = new Users();
        adduser.setId("649bfc536996ff58414453e4");
        adduser.setEmail("vishal@calsoftinc.com");
        adduser.setPassword("ramram@1234");
        adduser.setUserName("vishal");
        return adduser;
    }
}

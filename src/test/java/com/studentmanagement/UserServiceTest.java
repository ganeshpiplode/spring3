package com.studentmanagement;

import com.google.gson.Gson;
import com.studentmanagement.entities.Role;
import com.studentmanagement.entities.Users;
import com.studentmanagement.repositories.UserRepository;
import com.studentmanagement.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

public class UserServiceTest extends StudentmanagementApplicationTests{

    @InjectMocks
    UserServiceImpl userServiceImpl;
    @Mock
    UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void testRegisterUser(){
        try {
            Mockito.when(userRepository.save(Mockito.any())).thenReturn(getUserJson());
            //Mockito.when(userRepository.findByEmail(Mockito.any())).thenReturn(getUserJson());
            Users userDao = userServiceImpl.registerNewUser(addUserData());
            Assertions.assertNotNull(userDao);
            Assertions.assertEquals(getUserJson().getEmail(), userDao.getEmail());
        }catch(Exception e){
            System.out.println(e.getMessage());
            Assertions.fail();
        }

    }


    @Test
    void testUpdateUser(){
        try {
            System.out.println("update check");
            Optional<Users> optional = Optional.of(updateUserData());
            Mockito.when(userRepository.findById(Mockito.any())).thenReturn(optional);
            Mockito.when(userRepository.save(Mockito.any())).thenReturn(updateUserData());
            Mockito.when(passwordEncoder.encode(Mockito.any())).thenReturn("$2a$10$EGrckg6pkF9Nwn8m7xVGmOF8xapyXQLNhm4DP/yVUOuscW0SV6jJi");
            Users userDao = userServiceImpl.updateUser(updateUserData());
            Assertions.assertNotNull(userDao);
            Assertions.assertEquals("vishal@calsoftinc.com", userDao.getEmail());
        }catch(Exception e){
            System.out.println(e.getMessage()+"===========>>>>"+e.getStackTrace());
            e.printStackTrace();
            Assertions.fail();
        }

    }

    @Test
    void testListUsers(){
        try {
            List<Users> userList = new ArrayList<>();
            userList.add(updateUserData());
            Mockito.when(userRepository.findAll()).thenReturn(userList);
            List<Users>list = userServiceImpl.getAllUsers();
            Assertions.assertEquals(userList,list);
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    @Test
    void testGetUser(){
        try {
            System.out.println("get check");
            Optional<Users> optional = Optional.of(updateUserData());
            Mockito.when(userRepository.findById(Mockito.any())).thenReturn(optional);
            Users userDao = userServiceImpl.getUserById(getUserJson().getId());
            Assertions.assertNotNull(userDao);
            Assertions.assertEquals("vishal@calsoftinc.com", userDao.getEmail());
        }catch(Exception e){
            System.out.println(e.getMessage()+"===========>>>>"+e.getStackTrace());
            e.printStackTrace();
            Assertions.fail();
        }
    }


    @Test
    void testDeleteUser(){
        try {
            System.out.println("delete check");
            Optional<Users> optional = Optional.of(updateUserData());
            Mockito.when(userRepository.findById(Mockito.any())).thenReturn(optional);
            userServiceImpl.deleteUser(getUserJson().getId());
            Assertions.assertTrue(true);
        }catch(Exception e){
            System.out.println(e.getMessage()+"===========>>>>"+e.getStackTrace());
            e.printStackTrace();
            Assertions.fail();
        }
    }

    Users addUserData(){
        Users adduser = new Users();
        adduser.setEmail("mukesh@calsoftinc.com");
        adduser.setPassword("ramram@1234");
        adduser.setUserName("ganesh");
        Role roles = new Role();
        roles.setId("649409d2a7061a0375707ec8");
        roles.setName("Student");
        Set<Role>rolesList = new HashSet<>();
        rolesList.add(roles);
        adduser.setRoles(rolesList);
        return adduser;
    }

    Users updateUserData(){
        Users adduser = new Users();
        adduser.setId("649bfc536996ff58414453e4");
        adduser.setEmail("vishal@calsoftinc.com");
        adduser.setPassword("ramram@1234");
        adduser.setUserName("vishal");
        Role roles = new Role();
        roles.setId("649409d2a7061a0375707ec8");
        roles.setName("Student");
        Set<Role>rolesList = new HashSet<>();
        rolesList.add(roles);
        adduser.setRoles(rolesList);
        return adduser;
    }

    Users getUserJson(){
        String string = "{\n" +
                "  \"id\": \"649bfd535253c44f2b35ea2b\",\n" +
                "  \"email\": \"ganesh@calsoftinc.com\",\n" +
                "  \"password\": \"$2a$10$EGrckg6pkF9Nwn8m7xVGmOF8xapyXQLNhm4DP/yVUOuscW0SV6jJi\",\n" +
                "  \"roles\": [\n" +
                "    {\n" +
                "      \"id\": \"649409d2a7061a0375707ec8\",\n" +
                "      \"name\": \"Student\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"assignCourse\": null,\n" +
                "  \"enabled\": true,\n" +
                "  \"username\": \"ganesh\",\n" +
                "  \"authorities\": [\n" +
                "    {\n" +
                "      \"authority\": \"Student\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"accountNonExpired\": true,\n" +
                "  \"credentialsNonExpired\": true,\n" +
                "  \"accountNonLocked\": true\n" +
                "}";
        Gson g = new Gson();
        Users use = g.fromJson(string, Users.class);
        return use;
    }


}

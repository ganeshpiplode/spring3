package com.studentmanagement;

import com.google.gson.Gson;
import com.studentmanagement.controllers.AssignCourseController;
import com.studentmanagement.entities.AssignCourse;
import com.studentmanagement.entities.Courses;
import com.studentmanagement.entities.Users;
import com.studentmanagement.payloads.ApiResponse;
import com.studentmanagement.services.AssignCourseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AssignCourseControllerTest extends StudentmanagementApplicationTests{

    @InjectMocks
    private AssignCourseController assignCourseController;

    @Mock
    private AssignCourseService assignCourseService;

    @Test
    void createAssignCourseTest(){
        try{
            System.out.println("Inside method createAssignCourseTest");
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Mockito.when(assignCourseService.createAssignCourse(Mockito.any(), Mockito.any())).thenReturn(getCourseData());
            ResponseEntity<Object> response = assignCourseController.createAssignCourse(getCourseData(),auth);
            assertNotNull(response);
            assertEquals(HttpStatus.CREATED,response.getStatusCode());
        }catch (Exception e){
            System.out.println("Exception occur inside method createAssignCourseTest "+ e);
        }
    }

    @Test
    void getAssignCourseTest(){
        try{
            System.out.println("Inside method getAssignCourseTest");
            Mockito.when(assignCourseService.searchAssignCourse(Mockito.any())).thenReturn(getSearchCourse());
            ResponseEntity<Object> response = assignCourseController.searchAssignCourse("AWS fundamental");
            assertNotNull(response);
            assertEquals(HttpStatus.OK,response.getStatusCode());
        }catch (Exception e){
            System.out.println("Exception occur inside method getAssignCourseTest "+ e);
        }
    }

    @Test
    void deleteUserTest(){
        try{
            System.out.println("Inside method deleteTest");
            Mockito.doNothing().when(this).assignCourseService.deleteAssignCourse(Mockito.any(),Mockito.any());
            ResponseEntity<ApiResponse> response = assignCourseController.deleteAssignCourse("649ae707a7061a0375707ed2","649bcd1a8cc7d92292852ea4");
            assertNotNull(response);
            assertEquals(HttpStatus.OK,response.getStatusCode());
        }catch (Exception e){
            System.out.println("Exception occur inside method updateUserTest "+ e);
        }
    }

    AssignCourse getCourseData(){
        AssignCourse data = new AssignCourse();
        data.setStudentId("649ae707a7061a0375707ed2");
        List<String>courseList = new ArrayList<>();
        courseList.add("649bcd1a8cc7d92292852ea4");
        courseList.add("649bc00a56879a59f6b495c9");
        data.setCourses(courseList);
        return data;
    }

    List<Users>getSearchCourse(){
        String string = " {\n" +
                "            \"data\": [\n" +
                "            {\n" +
                "                \"id\": \"64a12fe8a45a594e9b208d21\",\n" +
                "                    \"email\": \"mohit@gmail.com\",\n" +
                "                    \"password\": \"$2a$10$kEUZNsAN4Hkv6IiTcEaFcu2csu2w5yWN7HywT2khtbqYESYWkQq/2\",\n" +
                "                    \"roles\": [\n" +
                "                {\n" +
                "                    \"id\": \"649409d2a7061a0375707ec8\",\n" +
                "                        \"name\": \"Student\"\n" +
                "                }\n" +
                "      ],\n" +
                "                \"enabled\": true,\n" +
                "                    \"username\": \"mohit@gmail.com\",\n" +
                "                    \"course\": [\n" +
                "                {\n" +
                "                    \"id\": \"649bc00a56879a59f6b495c9\",\n" +
                "                        \"couseName\": \"AWS fundamental\",\n" +
                "                        \"detail\": \"AWS services and fundamental\",\n" +
                "                        \"duration\": \"6 Month\",\n" +
                "                        \"createdBy\": \"ratan@gmail.com\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"649bcd1a8cc7d92292852ea4\",\n" +
                "                        \"couseName\": \"java Microservices and spring boot\",\n" +
                "                        \"detail\": \"java Microservices & spring boot with core java\",\n" +
                "                        \"duration\": \"1 year\",\n" +
                "                        \"createdBy\": \"ratan@gmail.com\"\n" +
                "                }\n" +
                "      ],\n" +
                "                \"authorities\": [\n" +
                "                {\n" +
                "                    \"authority\": \"Student\"\n" +
                "                }\n" +
                "      ],\n" +
                "                \"accountNonLocked\": true,\n" +
                "                    \"accountNonExpired\": true,\n" +
                "                    \"credentialsNonExpired\": true\n" +
                "            }\n" +
                "  ],\n" +
                "            \"message\": \"Get Course Successfully!\",\n" +
                "                \"status\": 200\n" +
                "        }";
        Gson g = new Gson();
        Users use = g.fromJson(string, Users.class);
        List<Users>listUser = new ArrayList<>();
        listUser.add(use);
        return listUser;
    }

}

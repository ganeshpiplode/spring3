package com.studentmanagement;

import com.google.gson.Gson;
import com.studentmanagement.entities.AssignCourse;
import com.studentmanagement.entities.Courses;
import com.studentmanagement.entities.Users;
import com.studentmanagement.repositories.AssignCourseRepository;
import com.studentmanagement.repositories.CoursesRepository;
import com.studentmanagement.repositories.UserRepository;
import com.studentmanagement.serviceImpl.AssignCourseServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AssignCourseServiceTest extends StudentmanagementApplicationTests{

    @InjectMocks
    AssignCourseServiceImp assignCourseServiceImp;
    @Mock
    AssignCourseRepository assignCourseRepository;
    @Mock
    UserRepository userRepository;

    @Mock
    CoursesRepository coursesRepository;

    @Mock
    private Principal principal;

    @Test
    void testCreateAssignCourse(){
        try {
            String defaultName = "ganesh@calsoftinc.com";
            List<Courses>listCourse = new ArrayList<>();
            listCourse.add(getUpdateCourseData());
            Optional<Users> useropt = Optional.of(getUserJson());
            Mockito.when(userRepository.findById(Mockito.any())).thenReturn(useropt);
            Mockito.when(coursesRepository.findByIdIn(Mockito.any())).thenReturn(listCourse);
            Mockito.when(principal.getName()).thenReturn(defaultName);
            Mockito.when(userRepository.findByEmail(Mockito.any())).thenReturn(getUserJson());
            AssignCourse assignCourse = assignCourseServiceImp.createAssignCourse(getCourseData(),principal);
            Assertions.assertNotNull(assignCourse);
            Assertions.assertEquals(getCourseData().getStudentId(), assignCourse.getStudentId());
        }catch(Exception e){
            System.out.println(e.getMessage());
            Assertions.fail();
        }

    }

    @Test
    void testDeleteCourse(){
        try {
            System.out.println("delete check");
            Optional<Users> useropt = Optional.of(getUserJson());
            Mockito.when(userRepository.findById(Mockito.any())).thenReturn(useropt);
            assignCourseServiceImp.deleteAssignCourse(getCourseData().getStudentId(),getCourseData().getCourses().get(0));
            Assertions.assertTrue(true);
        }catch(Exception e){
            System.out.println(e.getMessage()+"===========>>>>"+e.getStackTrace());
            e.printStackTrace();
            Assertions.fail();
        }
    }

    AssignCourse getCourseData(){
        AssignCourse data = new AssignCourse();
        data.setStudentId("649ae707a7061a0375707ed2");
        List<String> courseList = new ArrayList<>();
        courseList.add("649bcd1a8cc7d92292852ea4");
        courseList.add("649bc00a56879a59f6b495c9");
        data.setCourses(courseList);
        return data;
    }

    Courses getUpdateCourseData(){
        Courses data = new Courses();
        data.setId("649bc00a56879a59f6b495gj");
        data.setCouseName("CSS");
        data.setDetail("ui design course");
        data.setDuration("1 month");
        return data;
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
                "  \"Course\": [\n" +
                "    {\n" +
                "      \"id\": \"649bc00a56879a59f6b495c9\",\n" +
                "      \"couseName\": \"AWS fundamental\",\n" +
                "      \"detail\": \"AWS services and fundamental\",\n" +
                "      \"duration\": \"6 Month\",\n" +
                "      \"createdBy\": \"ratan@gmail.com\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"649bcd1a8cc7d92292852ea4\",\n" +
                "      \"couseName\": \"java Microservices and spring boot\",\n" +
                "      \"detail\": \"java Microservices & spring boot with core java\",\n" +
                "      \"duration\": \"1 year\",\n" +
                "      \"createdBy\": \"ratan@gmail.com\"\n" +
                "    }\n" +
                "  ],\n" +
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

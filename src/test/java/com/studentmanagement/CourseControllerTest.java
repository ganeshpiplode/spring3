package com.studentmanagement;

import com.studentmanagement.controllers.CourseController;
import com.studentmanagement.entities.Courses;
import com.studentmanagement.services.CourseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CourseControllerTest extends StudentmanagementApplicationTests{

    @InjectMocks
    private CourseController courseController;

    @Mock
    private CourseService courseService;

    @Test
    void createCourseTest(){
        try{
            System.out.println("Inside method createCourseTest");
            Mockito.when(courseService.addCourse(Mockito.any())).thenReturn(getUpdateCourseData());
            ResponseEntity<Object> response = courseController.createCourse(getCourseData());
            assertNotNull(response);
            assertEquals(HttpStatus.CREATED,response.getStatusCode());
        }catch (Exception e){
            System.out.println("Exception occur inside method createCourseTest "+ e);
        }
    }


    @Test
    void updateCourseTest(){
        try{
            System.out.println("Inside method updateCourseTest");
            Mockito.when(courseService.UpdateCourse(Mockito.any())).thenReturn(getUpdateCourseData());
            ResponseEntity<Object> response = courseController.updateCourse(getCourseData());
            assertNotNull(response);
            assertEquals(HttpStatus.OK,response.getStatusCode());
        }catch (Exception e){
            System.out.println("Exception occur inside method updateCourseTest "+ e);
        }
    }

    @Test
    void courseListTest(){
        try{
            System.out.println("Inside method listTest");
            List<Courses>coursesList = new ArrayList<>();
            coursesList.add(getUpdateCourseData());
            Mockito.when(courseService.courses()).thenReturn(coursesList);
            ResponseEntity<Object> response = courseController.getCourses();
            assertNotNull(response);
            assertEquals(HttpStatus.OK,response.getStatusCode());
        }catch (Exception e){
            System.out.println("Exception occur inside method courseListTest "+ e);
        }
    }

    @Test
    void getCourseTest(){
        try{
            System.out.println("Inside method getCourseTest");
            Mockito.when(courseService.getCourse(Mockito.any())).thenReturn(getUpdateCourseData());
            ResponseEntity<Object> response = courseController.getCourse("649bc00a56879a59f6b495gj");
            assertNotNull(response);
            assertEquals(HttpStatus.OK,response.getStatusCode());
        }catch (Exception e){
            System.out.println("Exception occur inside method getCourseTest "+ e);
        }
    }

    Courses getCourseData(){
        Courses data = new Courses();
        data.setCouseName("html");
        data.setDetail("ui design course");
        data.setDuration("3 month");
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
}

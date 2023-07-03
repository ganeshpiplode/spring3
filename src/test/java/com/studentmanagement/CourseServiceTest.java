package com.studentmanagement;

import com.studentmanagement.entities.Courses;
import com.studentmanagement.repositories.CoursesRepository;
import com.studentmanagement.serviceImpl.CourseServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class CourseServiceTest extends StudentmanagementApplicationTests{

    @InjectMocks
    CourseServiceImp courseServiceImp;
    @Mock
    CoursesRepository coursesRepository;

    @Test
    void testAddCourse(){
        try {
            System.out.println("test check");
            Mockito.when(coursesRepository.save(Mockito.any())).thenReturn(getCourseData());
            Courses courseDao = courseServiceImp.addCourse(getCourseData());
            Assertions.assertNotNull(courseDao);
            Assertions.assertEquals("html", courseDao.getCouseName());
        }catch(Exception e){
            System.out.println(e.getMessage());
            Assertions.fail();
        }

    }

    @Test
    void testUpdateCourse(){
        try {
            System.out.println("update check");
            Mockito.when(coursesRepository.save(Mockito.any())).thenReturn(getUpdateCourseData());
            Mockito.when(coursesRepository.findCourseById(Mockito.any())).thenReturn(getCourseData());
            Courses courseDao = courseServiceImp.UpdateCourse(getUpdateCourseData());
            Assertions.assertNotNull(courseDao);
            Assertions.assertEquals("CSS", courseDao.getCouseName());
        }catch(Exception e){
            System.out.println(e.getMessage()+"===========>>>>"+e.getStackTrace());
            e.printStackTrace();
            Assertions.fail();
        }

    }

    @Test
    void testUpdateCourseFail(){
        try {
            System.out.println("update check");
            Mockito.when(coursesRepository.findCourseById(Mockito.any())).thenReturn(null);
            courseServiceImp.UpdateCourse(getUpdateCourseData());
            Assertions.fail();
        }catch(Exception e){
            e.printStackTrace();
            Assertions.assertTrue(true);
        }

    }

    @Test
    void testListCourse(){
        try {
            List<Courses> coursesList = new ArrayList<>();
            coursesList.add(getCourseData());
            Mockito.when(coursesRepository.findAll()).thenReturn(coursesList);
            List<Courses>list = courseServiceImp.courses();
            Assertions.assertEquals(coursesList,list);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Test
    void testGetCourseFail(){
        try {
            Mockito.when(coursesRepository.findCourseById(Mockito.any())).thenReturn(null);
            Courses courses = courseServiceImp.getCourse("649bc00a56879a59f6b495gj");
            Assertions.fail();
        }catch(Exception e){
            e.printStackTrace();
            Assertions.assertTrue(true);
        }

    }

    @Test
    void testGetCourse(){
        try {
            Mockito.when(coursesRepository.findCourseById(Mockito.any())).thenReturn(getUpdateCourseData());
            Courses courses = courseServiceImp.getCourse("649bc00a56879a59f6b495gj");
            Assertions.assertNotNull(courses);
            Assertions.assertEquals(getUpdateCourseData().getCouseName(),courses.getCouseName());
        }catch(Exception e){
            e.printStackTrace();
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

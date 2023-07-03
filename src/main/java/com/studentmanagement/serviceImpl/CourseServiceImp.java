package com.studentmanagement.serviceImpl;

import com.studentmanagement.entities.Courses;
import com.studentmanagement.exceptions.ResourceNotFoundException;
import com.studentmanagement.repositories.CoursesRepository;
import com.studentmanagement.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class CourseServiceImp implements CourseService {
    @Autowired
    CoursesRepository coursesRepository;
    @Override
    public Courses addCourse(Courses courses) {
        Courses addCourse  = coursesRepository.save(courses);
        return addCourse;
    }

    @Override
    public Courses UpdateCourse(Courses courses) {
        Courses  courseData  = coursesRepository.findCourseById(courses.getId());
        if(courseData==null)
           throw new ResourceNotFoundException("Course","courseId",courses.getId());

        courseData.setCouseName(courses.getCouseName());
        courseData.setDetail(courses.getDetail());
        courseData.setDuration(courses.getDuration());
        Courses courseUpdate = coursesRepository.save(courseData);
        return courseUpdate;
    }

    @Override
    public List<Courses> courses() {
        List<Courses>courseList = coursesRepository.findAll();
        return courseList;
    }

    @Override
    public Courses getCourse(String id) {
        Courses  courseData  = coursesRepository.findCourseById(id);
        if(courseData==null)
           throw new ResourceNotFoundException("Courses","courseId",id);
        return courseData;
    }
}

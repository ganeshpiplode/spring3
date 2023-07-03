package com.studentmanagement.services;

import com.studentmanagement.entities.Courses;

import java.util.List;

public interface CourseService {

    Courses addCourse(Courses courses);
    Courses UpdateCourse(Courses courses);

    List<Courses> courses();
    Courses getCourse(String id);
}

package com.studentmanagement.repositories;

import com.studentmanagement.entities.Courses;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CoursesRepository extends MongoRepository<Courses,String> {

    Courses findCourseById(String CourseId);
    List<Courses> findByIdIn(List<String>CourseIds);
}

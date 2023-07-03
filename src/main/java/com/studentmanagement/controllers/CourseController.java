package com.studentmanagement.controllers;

import com.studentmanagement.entities.Courses;
import com.studentmanagement.payloads.ResponseController;
import com.studentmanagement.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    CourseService courseService;

    @PostMapping("/")
    public ResponseEntity<Object> createCourse(@RequestBody Courses courses) {
        Courses createCourses = this.courseService.addCourse(courses);
        return ResponseController.generateResponse("Course Added Successfully!", HttpStatus.CREATED, createCourses);
    }

    @PutMapping("/")
    public ResponseEntity<Object> updateCourse(@RequestBody Courses courses) {
        Courses updatedCourses = this.courseService.UpdateCourse(courses);
        return ResponseController.generateResponse("Course Updated Successfully!", HttpStatus.OK, updatedCourses);
    }
    // get
    @GetMapping("/{courseId}")
    public ResponseEntity<Object> getCourse(@PathVariable String courseId) {
        Courses coursesData = this.courseService.getCourse(courseId);
        return ResponseController.generateResponse("Fetch Data Successfully!", HttpStatus.OK, coursesData);
    }
    // get all
    @GetMapping("/")
    public ResponseEntity<Object> getCourses() {
        List<Courses> coursesList = this.courseService.courses();
        return ResponseController.generateResponse("Get Course List Successfully!", HttpStatus.OK, coursesList);
    }


}

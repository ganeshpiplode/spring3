package com.studentmanagement.controllers;

import com.studentmanagement.entities.AssignCourse;
import com.studentmanagement.entities.Users;
import com.studentmanagement.exceptions.ResourceNotFoundException;
import com.studentmanagement.payloads.ApiResponse;
import com.studentmanagement.payloads.ResponseController;
import com.studentmanagement.services.AssignCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/assign/courses")
public class AssignCourseController {
    @Autowired
    AssignCourseService assignCourseService;

    @PostMapping("/")
    public ResponseEntity<Object> createAssignCourse(@RequestBody AssignCourse assignCourse, Principal principal) {
        AssignCourse createCourses = null;
        try {
            createCourses = assignCourseService.createAssignCourse(assignCourse, principal);
        }catch (Exception e){
           throw new ResourceNotFoundException(e.getMessage());
        }
        return ResponseController.generateResponse("Course Assigned Successfully!", HttpStatus.CREATED, createCourses);
    }

    @GetMapping("/{searchCourse}")
    public ResponseEntity<Object> searchAssignCourse(@PathVariable String searchCourse) {
        List<Users> userlist = null;
        try {
            userlist = assignCourseService.searchAssignCourse(searchCourse);
        }catch (Exception e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return ResponseController.generateResponse("Get Course Successfully!", HttpStatus.OK, userlist);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{studentId}/{assignCourseId}")
    public ResponseEntity<ApiResponse> deleteAssignCourse(@PathVariable String studentId,@PathVariable String assignCourseId) {
           assignCourseService.deleteAssignCourse(studentId,assignCourseId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Assign course deleted successfully !!", true), HttpStatus.OK);
    }
}

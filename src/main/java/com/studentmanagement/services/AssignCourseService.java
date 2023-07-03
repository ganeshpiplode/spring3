package com.studentmanagement.services;

import com.studentmanagement.entities.AssignCourse;
import com.studentmanagement.entities.Users;

import java.security.Principal;
import java.util.List;

public interface AssignCourseService {
    AssignCourse createAssignCourse(AssignCourse assignCourse,Principal principal);
    void deleteAssignCourse(String studentId,String assignCourseId);
    List<Users> searchAssignCourse(String searchCourseName);
}

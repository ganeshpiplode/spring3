package com.studentmanagement.repositories;

import com.studentmanagement.entities.AssignCourse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AssignCourseRepository extends MongoRepository<AssignCourse,String> {
    AssignCourse findAssingCourseById(String assignCourseId);
    @Query(value="{'_id' : ?0}", delete = true)
    void deleteAssignCourseById(String assignCourseId);
}

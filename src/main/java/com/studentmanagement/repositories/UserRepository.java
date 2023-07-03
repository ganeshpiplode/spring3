package com.studentmanagement.repositories;

import com.studentmanagement.entities.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<Users,String> {
    @Query("{email:'?0'}")
    Users findByEmail(String email);

    @Query("{'Course.couseName':'?0'}")
    List<Users> findByCouseName(String courseName);


}

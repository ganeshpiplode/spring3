package com.studentmanagement.repositories;

import com.studentmanagement.entities.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RoleRepository extends MongoRepository<Role, String> {
    @Query("{name:'?0'}")
    Role findByName(String name);
}

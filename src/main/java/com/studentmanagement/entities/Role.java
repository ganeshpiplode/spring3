package com.studentmanagement.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("role")
public class Role{
    @Id
    private String id;
    private String name;
}

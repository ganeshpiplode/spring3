package com.studentmanagement.entities;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("Courses")
public class Courses {
    @Id
    private String id;
    private String couseName;
    private String detail;
    private String duration;
    private String createdBy;
}

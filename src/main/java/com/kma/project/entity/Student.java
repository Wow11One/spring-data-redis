package com.kma.project.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RedisHash
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
public class Student implements Serializable {

    @Id
    String id;
    String firstName;
    String lastName;
    Integer course;
    @Reference
    List<Grade> grades = new ArrayList<>();

    public Student(String firstName, String lastName, Integer course) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;
    }
}

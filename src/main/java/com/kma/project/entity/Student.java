package com.kma.project.entity;


import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RedisHash
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Student implements Serializable {

    @Id
    @Indexed
    @EqualsAndHashCode.Include
    String id;
    @Indexed
    String firstName;
    @Indexed
    String lastName;
    @Indexed
    String faculty;
    @Indexed
    Integer course;
    @Reference
    List<Grade> grades = new ArrayList<>();

    public Student(String firstName,
                   String lastName,
                   Integer course,
                   String faculty) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;
        this.faculty = faculty;
    }
}

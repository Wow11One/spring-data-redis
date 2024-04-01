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

@RedisHash
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
public class Grade implements Serializable {

    @Id
    String id;
    Integer value;
    String type;
    @Reference
    Student student;
    @Reference
    Subject subject;

    public Grade(Integer value,
                 String type,
                 Student student,
                 Subject subject) {
        this.value = value;
        this.type = type;
        this.student = student;
        this.subject = subject;
    }
}

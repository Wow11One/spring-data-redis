package com.kma.project.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
public class Subject implements Serializable {

    @Id
    String id;
    String name;
    String teacher;
    Integer creditsECTS;
    Integer hoursAmountPerWeek;

    public Subject(String name,
                   String teacher,
                   Integer creditsECTS,
                   Integer hoursAmountPerWeek) {
        this.name = name;
        this.teacher = teacher;
        this.creditsECTS = creditsECTS;
        this.hoursAmountPerWeek = hoursAmountPerWeek;
    }
}

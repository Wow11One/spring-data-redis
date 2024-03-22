package com.kma.project.service;

import com.kma.project.entity.Student;
import com.redis.testcontainers.RedisContainer;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@FieldDefaults(level = AccessLevel.PRIVATE)
@Testcontainers
class StudentServiceTest {

    @Autowired
    StudentService studentService;


    @Container
    @ServiceConnection
    static RedisContainer container =
            new RedisContainer(DockerImageName.parse("redis:5.0.3-alpine")).withExposedPorts(6379);

    

    @Test
    void containerIsRunningTest() {
        assertThat(container.isRunning()).isTrue();
    }

    @Test
    void findAllByFullNameTest() {
        List<Student> students = studentService.findAll();
        students.forEach(System.out::println);
    }

    @Test
    void findAllByCourseGreaterThanTest() {
    }

    @Test
    void findAllWithNoGradesTest() {
    }
}
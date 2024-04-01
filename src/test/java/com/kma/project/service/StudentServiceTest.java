package com.kma.project.service;

import com.kma.project.entity.Grade;
import com.kma.project.entity.Student;
import com.kma.project.entity.Subject;
import com.redis.testcontainers.RedisContainer;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;
import java.util.Objects;

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

    @BeforeEach
    public void init() {

        Subject subject = new Subject("Spring Boot DB",
                "Maksym Androshchuk",
                3,
                3);

        Student studentFirst = new Student("Volodymyr",
                "Havryliuk",
                3,
                "FI");

        Student studentSecond = new Student("Oleksandr",
                "Ihumnov",
                1,
                "FGN");

        Student studentThird = new Student("Dmytro",
                "Volkov",
                4,
                "FI");

        Grade gradeFirst = new Grade(97, "Homework", studentFirst, subject);
        Grade gradeSecond = new Grade(92, "Exam", studentFirst, subject);
        Grade gradeThird = new Grade(65, "Homework", studentSecond, subject);

        studentFirst.getGrades().add(gradeFirst);
        studentFirst.getGrades().add(gradeSecond);
        studentSecond.getGrades().add(gradeThird);

        studentService.save(studentFirst);
        studentService.save(studentSecond);
        studentService.save(studentThird);
    }

    @AfterEach
    public void destroy() {
        studentService.deleteAll();
    }

    @Test
    public void containerIsRunningTest() {
        assertThat(container.isRunning()).isTrue();
    }

    @Test
    public void findAllTest() {
        List<Student> allStudents = studentService.findAll();
        //it is not empty because we put some data in init method
        assertThat(allStudents).isNotEmpty();
    }

    @Test
    void findByFullNameTest() {
        String firstName = "Volodymyr";
        String lastName = "Havryliuk";

        List<Student> allStudents = studentService.findAll();
        Student expectedStudent = allStudents
                .stream()
                .filter(student ->
                        student.getFirstName().equals(firstName) &&
                                student.getLastName().equals(lastName)
                )
                .toList()
                .getFirst();

        Student actualStudent = studentService.findByFullName(firstName, lastName).getFirst();

        assertThat(expectedStudent).isEqualTo(actualStudent);
    }

    @Test
    void findAllByCourseTest() {
        Integer course = 3;

        List<Student> allStudents = studentService.findAll();
        List<Student> expectedStudentList = allStudents
                .stream()
                .filter(student -> Objects.equals(student.getCourse(), course))
                .toList();

        List<Student> actualStudentList = studentService.findAllByCourse(course);

        assertThat(actualStudentList).isNotEmpty();
        assertThat(expectedStudentList.size()).isEqualTo(actualStudentList.size());
    }

    @Test
    void findAllByFacultyTest() {
        String faculty = "FI";
        List<Student> allStudents = studentService.findAll();
        List<Student> expectedStudentList = allStudents
                .stream()
                .filter(student -> student.getFaculty().equals(faculty))
                .toList();

        List<Student> actualStudentList = studentService.findAllByFaculty(faculty);

        assertThat(actualStudentList).isNotEmpty();
        assertThat(expectedStudentList.size()).isEqualTo(actualStudentList.size());
    }
}
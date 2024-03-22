package com.kma.project.service;

import com.kma.project.entity.Student;
import com.kma.project.repository.StudentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class StudentService {

    StudentRepository studentRepository;

    public List<Student> findAll() {
        return (List<Student>) studentRepository.findAll();
    }

    public List<Student> findAllByFullName(String firstName, String lastName) {
        return studentRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<Student> findAllByCourseGreaterThan(Integer course) {
        return studentRepository.findByCourseGreaterThan(course);
    }

    public List<Student> findAllWithNoGrades(Integer course) {
        return studentRepository.findByGradesEmpty();
    }

    public void deleteAll() {
        studentRepository.deleteAll();
    }
}

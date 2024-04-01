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

    public void save(Student student) {
        studentRepository.save(student);
    }

    public List<Student> findAll() {
        return (List<Student>) studentRepository.findAll();
    }

    public List<Student> findByFullName(String firstName, String lastName) {
        return (List<Student>) studentRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<Student> findAllByCourse(Integer course) {
        return studentRepository.findByCourse(course);
    }

    public List<Student> findAllByFaculty(String faculty) {
        return studentRepository.findByFaculty(faculty);
    }

    public void deleteAll() {
        studentRepository.deleteAll();
    }
}

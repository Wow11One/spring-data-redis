package com.kma.project.repository;

import com.kma.project.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {

    Iterable<Student> findByFirstNameAndLastName(String firstName, String lastName);

    List<Student> findByCourse(Integer course);

    List<Student> findByFaculty(String faculty);

}

package com.kma.project.repository;

import com.kma.project.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {

    List<Student> findByFirstNameAndLastName(String firstName, String lastName);
    List<Student> findByCourseGreaterThan(Integer course);
    List<Student> findByGradesEmpty();

}

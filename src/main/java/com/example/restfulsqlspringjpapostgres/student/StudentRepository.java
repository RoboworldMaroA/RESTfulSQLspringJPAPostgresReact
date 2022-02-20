package com.example.restfulsqlspringjpapostgres.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//import for comunication 3000 port and 8080
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//responsible for data access
@Repository
public interface StudentRepository
        extends JpaRepository<Student, Long> {//student class and long because ID is long

    //we want ot find student using email to do that we have can use Optional imported from Java package
    //equivalent in sql SELECT * FROM student WHERE email = ?
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}

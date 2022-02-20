package com.example.restfulsqlspringjpapostgres.student;
//create a bean

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){

        return args -> {
//            Student Marek = new Student(
//                    //1L,// it will be generated
//                    "Marek",
//                    "Augustyn",
//                    LocalDate.of(2000, MAY, 2),
//                    21
//            );

            Student Marek = new Student(
                    //1L,// it will be generated
                    "Marek",
                    "Augustyn",
                    LocalDate.of(2000, MAY, 2)
            );



            Student Janek = new Student(
                    //1L, it will be generated
                    "Janek",
                    "Student 2",
                    LocalDate.of(2004, MAY, 2)
            );

            //now we tell that we want save student using list
            repository.saveAll(
                    List.of(Marek, Janek)

            );
        };


    }
}

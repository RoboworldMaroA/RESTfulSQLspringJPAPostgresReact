package com.example.restfulsqlspringjpapostgres.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
//this
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
    return studentRepository.findAll();


        //this will be in database so this info we get from three the interface from database
//        return List.of(new Student(1L,
//                "Marek",
//                "Augustyn",
//                LocalDate.of(2000, Month.MAY,2),
//                21
//
//        ));


    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        //instead of printing student we want to save new student
        //System.out.println(student);
        studentRepository.save(student);

    }
    //implementation services
    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists){
            throw new IllegalStateException("`student  with id: "+ studentId+" does not exist.");

        }
        //otherwise
        studentRepository.deleteById(studentId);

    }

    //implementation PUT
    @Transactional
    public void updateStudent(Long studentId,
                              String name,
                              String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new IllegalStateException(
                "Student with id"+studentId+"dose not exist"));//-> I think this is kind og get
        if (name != null && name.length()>0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }
        //check email
        if (email != null && email.length()>0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }



    }//end update student




}

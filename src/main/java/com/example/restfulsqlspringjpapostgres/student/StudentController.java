package com.example.restfulsqlspringjpapostgres.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

// in this class we implement GET, POST,PUT, DELETE
//this is a proper way we could have rst controller in main restfull app but
// this is good practice do this that way

@RestController
@RequestMapping(path = "api/v1/student")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        //this.studentService = new StudentService();//this is incorrect so use annotation to inject student service
        this.studentService = studentService;
    }

    //printing list of the students GET method
        @GetMapping
        public List<Student> getStudents(){
//            return List.of(new Student(1L,
//                    "Marek",
//                    "Augustyn",
//                    LocalDate.of(2000, Month.MAY,2),
//                    21
//
//                    ));

            return studentService.getStudents();
        }
      //implementation post
      // add new student
    //request body is an element student with details

    //**********   POST *******************************************
    //@PostMapping(value = "/api/v1/student", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);



    }//end POST

    //**********   DELETE     *******************************************
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(  @PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);

    }

    //**********   PUT      *******************************************
    // put update data in the student for example we want ot change name and email
    @PutMapping(path="{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){
        studentService.updateStudent(studentId,name,email);
    }



}

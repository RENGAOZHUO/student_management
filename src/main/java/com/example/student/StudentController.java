package com.example.student;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }

    private StudentResponse toResponse(Student student) {

        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getScore()
        );
    }

    @GetMapping
    public List<StudentResponse> getAllStudents(){

        List<Student> students =
                studentService.findAllStudents();

        List<StudentResponse> responses =
                new ArrayList<>();

        for(Student student : students){

            responses.add(
                    toResponse(student)
            );
        }

        return responses;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(
            @PathVariable int id){

        Student student = studentService.findStudentById(id);

        return ResponseEntity.ok(toResponse(student));
    }

    @PostMapping
    public ResponseEntity<Void> addStudent(
            @Valid @RequestBody CreateStudentRequest request//Student student//它告诉 Spring：从 HTTP 请求体中读取 JSON，并把数据转换成一个 Student 对象。
    ){
        Student student = new Student();

        student.setName(request.getName());
        student.setAge(request.getAge());
        student.setScore(request.getScore());
        studentService.addStudent(student);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

   @PutMapping("/{id}/score")
    public ResponseEntity<Void> updateScore(
            @PathVariable("id") int id,
            @Valid @RequestBody UpdateScoreRequest request
   ){
                studentService.updateScore(id,request.getScore());

        return ResponseEntity.noContent().build();
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable("id") int id
   ){

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
   }
}

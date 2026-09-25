package com.example.student;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }

    @GetMapping("/status")
    public String status(){

        return studentService.grtStatus();

    }

    @GetMapping
    public List<Student> getAllStudents() throws SQLException{

        return studentService.findAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(
            @PathVariable int id) throws SQLException{

        Student student = studentService.findStudentById(id);

        if(student == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(student);
    }

    @PostMapping//它表示这个方法负责处理 POST 请求。
    //由于 Controller 已经有 /api/students 这个路径，因此最终接口就是：
    //POST http://localhost:8080/api/students
    public ResponseEntity<String> addStudent(
            @RequestBody Student student//它告诉 Spring：从 HTTP 请求体中读取 JSON，并把数据转换成一个 Student 对象。
    )throws SQLException{
        System.out.println("Controller 收到学生：" + student);

        int rows = studentService.addStudent(student);

        if (rows == 1) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("学生添加成功");
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("学生添加失败");//新增成功后，我们返回一个 HTTP 响应：
    }
    //问题产生的原因
    //你的 Student 类有两个构造方法：一个无参构造方法，一个包含 id 的四参数构造方法。
    //产生问题： Jackson 在转换 JSON 时，可能自动选择了四参数构造方法。
    //触发错误： 你发送的 JSON 没有 id，Jackson 试图把 null 赋给 int id，但 Java 的 int 不能接收 null，因此返回 HTTP 400。
    //解决方法： 给无参构造方法添加 @JsonCreator，明确告诉 Jackson 使用它创建对象，再通过 Setter 填入学生信息。
    //=======================
    //记住这个知识点
    //HTTP 400 不一定是 JSON 写错了，也可能是 Spring 无法将 JSON 正确转换成 Java 对象。
    //这次不是 Controller、Service 或 SQL 的问题，而是对象反序列化时的构造方法选择问题。
    //另外，你的 id 是数据库自增主键，正常新增学生时不需要手动传入。

   @PutMapping("/{id}/score")
    public ResponseEntity<Void> updateScore(
            @PathVariable("id") int id,
            @RequestBody UpdateScoreRequest request
   ){
        Double score = request.getScore();

        if(score == null||!Double.isFinite(score)||score<0||score>100){

            return ResponseEntity.badRequest().build();
        }

        boolean success = studentService.updateScore(id,score);

        if(!success){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
   }
}

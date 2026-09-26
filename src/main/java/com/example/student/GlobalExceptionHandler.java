package com.example.student;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//如果 Service 抛出了异常，那谁处理？这就是今天最重要的新东西。
//创建全局异常处理器
@RestControllerAdvice
//你现在可以把它理解为：专门负责整个项目 Controller 异常处理的类。
//不是只管一个 Controller。而是可以统一处理整个项目里的异常。
public class GlobalExceptionHandler {
    //处理学生不存在
    @ExceptionHandler(StudentNotFoundException.class)
    //意思是：如果 Controller 执行过程中出现 StudentNotFoundException，交给这个方法处理。
    public ResponseEntity<Void> handleStudentNotFound(
            StudentNotFoundException e
    ){
        return ResponseEntity.notFound().build();
        //返回：404 Not Found
    }

    //处理成绩错误
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> handleIllegalArgument(
            IllegalArgumentException e
    ){

        return ResponseEntity.badRequest().build();
        //这样：IllegalArgumentException就会被转换成：400 Bad Request
    }
}

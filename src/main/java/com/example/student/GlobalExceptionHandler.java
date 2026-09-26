package com.example.student;

import org.springframework.http.HttpStatus;
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
    /*@ExceptionHandler(StudentNotFoundException.class)
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
}*/
    @ExceptionHandler(StudentNotFoundException.class)
public ResponseEntity<ApiError> handleStudentNotFound(
        StudentNotFoundException e
){

    ApiError error =
            new ApiError(
                    HttpStatus.NOT_FOUND.value(),
                    e.getMessage()
                    //这里第一次出现了：e.getMessage()
                    //还记得 Service 中我们写过：throw new StudentNotFoundException("学生不存在，id = " + id)
                    //假设：id = 999;那么异常对象 e 里面保存的信息就是：学生不存在，id = 999
                    //所以：e.getMessage()就能把这段信息拿出来。
            );

    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(error);
    //ResponseEntity.status().body() 是什么？以前你用过：ResponseEntity.notFound().build();
    //这个只能返回状态，没有响应体。
    //今天使用的结构，可以拆成：status(404)设置 HTTP 状态码。然后：body(error)把 ApiError 对象放进响应体。
    //最终：
    //HTTP状态码：404
    //
    //响应体：
    //{
    //    "status": 404,
    //    "message": "学生不存在，id = 999"
    //}
    //所以这里：ResponseEntity<ApiError>
    //它就是：返回一个 HTTP 响应，响应体的数据类型是 ApiError。
}
@ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgument(
            IllegalArgumentException e
){

        ApiError error =
                new ApiError(
                        HttpStatus.BAD_REQUEST.value(),
                        e.getMessage()
                );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
}
}
//今天再理解一下异常的完整流程
//完整过程：
//PUT 请求
//   ↓
//StudentController
//   ↓
//StudentService
//   ↓
//发现 150 > 100
//   ↓
//throw IllegalArgumentException
//   ↓
//GlobalExceptionHandler
//   ↓
//handleIllegalArgument()
//   ↓
//创建 ApiError
//   ↓
//ResponseEntity<ApiError>
//   ↓
//400 + JSON

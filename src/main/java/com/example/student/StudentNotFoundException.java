package com.example.student;

public class StudentNotFoundException extends RuntimeException{
    //这里：extends RuntimeException表示：StudentNotFoundException 是一种运行时异常。

    public StudentNotFoundException(String message){
        super(message);//就是把错误信息交给父类。所以以后我们可以写：throw new StudentNotFoundException("学生不存在");
    }
}

//为什么要自己创建异常？
//因为如果全部都写：throw new RuntimeException();
//以后你根本不知道到底发生了什么。
//eg.学生不存在？ 数据库错误？ 成绩错误？ 其他错误？
//而：StudentNotFoundException名字本身就告诉我们：学生不存在。以后 Spring 就可以专门处理它。
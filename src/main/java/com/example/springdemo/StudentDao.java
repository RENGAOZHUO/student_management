package com.example.springdemo;


import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {

    public StudentDao(){
        System.out.println("创建了StudentDao对象");
    }

    public void test(){
        System.out.println("StudentDao正在工作");
    }
}
//今天第一次看到：@Repository
//它前面的 @ 表示这是一个注解。
//你现在可以暂时理解成：告诉 Spring：请管理这个 StudentDao 对象。
//也就是说：
//@Repository
//public class StudentDao {
//}
//Spring 发现它以后，就可以创建一个 StudentDao Bean。
package com.example.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context=
                new AnnotationConfigApplicationContext(AppConfig.class);

        StudentService s1 =
                context.getBean(StudentService.class);

        StudentService s2 =
                context.getBean(StudentService.class);

        System.out.println(s1==s2);
    }
}
//这几行代码到底发生了什么？
//这里是今天最关键的一段。
//AnnotationConfigApplicationContext context =
//        new AnnotationConfigApplicationContext(AppConfig.class);
//你现在可以把：context
//理解为：Spring 容器
//它内部大概有：
//Spring 容器 context
//│
//├── StudentDao Bean
//│
//└── StudentService Bean
//        │
//        └── StudentDao 已被注入
//然后：context.getBean(StudentService.class);
//意思是：Spring，请把你管理的 StudentService 对象给我。
//注意，又没有：new StudentService();甚至 StudentService 需要的 StudentDao，我们也没有：new StudentDao();
//Spring 全部完成了。
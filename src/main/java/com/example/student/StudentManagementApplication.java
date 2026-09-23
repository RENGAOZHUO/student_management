package com.example.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication 是什么？
//昨天写了：@Configuration @ComponentScan("com.example.student")
//今天我们使用：@SpringBootApplication
//它是一个组合注解，包含配置、组件扫描和自动配置等能力。
//默认情况下，组件扫描从启动类所在的包开始，因此这里会扫描 com.example.student 及其子包。
//它不会自动扫描旁边的 com.example.springdemo 包，这也能避免你昨天遇到的两个实验类混用问题。

public class StudentManagementApplication {

    public static void main(String[] args){

        SpringApplication.run(
                StudentManagementApplication.class,
                args
        );
        //SpringApplication.run() 是什么？
        //这行代码会启动 Spring Boot 应用，创建并初始化 Spring 容器，并根据项目依赖启动相应的 Web 基础设施。
        //你不需要再手动创建 AnnotationConfigApplicationContext。
        //但这并不代表 Spring 容器消失了——只是 Spring Boot 帮你完成了启动过程。

    }
}

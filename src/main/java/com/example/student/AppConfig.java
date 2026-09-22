package com.example.student;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//怎么让 Spring 知道去哪里找这些类？
//现在还有一个问题。我们写了：@Service @Repository
//但 Spring 从哪里开始找？需要一个配置类。创建：AppConfig.java
//今天先理解两个注解。
//@Configuration 告诉 Spring：这是一个 Spring 配置类。
//@ComponentScan 告诉 Spring：请扫描 com.example.springdemo 这个包，寻找需要你管理的类。
//Spring 扫描后发现：@Repository StudentDao 以及：@Service StudentService
//于是把它们创建成 Bean。
@Configuration
@ComponentScan("com.example.student")
public class AppConfig {

}

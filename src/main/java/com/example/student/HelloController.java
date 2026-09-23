package com.example.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//告诉 Spring：这是一个处理 Web 请求的控制器，它的方法可以直接向 HTTP 响应写入数据。
//它也是一个 Spring 组件，会被容器管理。官方 Spring Boot 入门指南也是通过 @RestController 来实现简单 Web 接口的。

public class HelloController {

    @GetMapping("/api/hello")
    //这个注解指定：当服务器收到匹配 /api/hello 路径的 HTTP GET 请求时，调用对应方法。
    //GET 是 HTTP 请求方法之一，通常用于获取数据。
    //在浏览器地址栏访问网页，通常就是发送 GET 请求。
    public String hello(
            @RequestParam(defaultValue = "Student")String name
            //它的作用是：从请求 URL 的查询参数中取得 name。
            //例如：/api/hello?name=Alice对应：name = "Alice";
            //如果用户没有提供 name，则使用默认值 Student。
    ){
        return "Hello,"+name+"!";
        //不再是向 IDEA 控制台输出内容，而是把字符串作为 HTTP 响应内容返回给请求方。
    }
}

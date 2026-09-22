package com.example.springdemo;

import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentDao studentDao;

    public StudentService(StudentDao studentDao){

        System.out.println("创建了StudentService对象");
        this.studentDao=studentDao;
    }

    public void test(){
        System.out.println("StudentService正在工作");

        studentDao.test();
    }
}
//这里有一个非常重要的地方：
//public StudentService(StudentDao studentDao) {
//    this.studentDao = studentDao;
//}
//仔细观察：我们没有写：new StudentDao();
//StudentService 只是说：“我要正常工作，需要一个 StudentDao。”
//至于 StudentDao 从哪里来？
//Spring 会提供。
//这就是我们刚才讲的：DI —— 依赖注入
//----------------------------
//@Service 和 @Repository 是什么？
//今天先这样理解：@Service
//表示：这个类主要负责业务逻辑，并交给 Spring 管理。
//@Repository
//表示：这个类主要负责访问数据库，并交给 Spring 管理。

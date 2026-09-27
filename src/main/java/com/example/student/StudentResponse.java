package com.example.student;
//Response DTO——控制“服务器返回给客户端什
//今天完成 DTO 的另一半：Controller 查询到 Student 后，不再直接把内部 Student 对象原样返回给客户端，而是转换成 StudentResponse。
//这样我们就第一次形成完整的数据流：
//Request DTO->Student->Service/DAO->Response DTO->JSON
//现在 Student 只有：id name age score所以暂时没出什么问题。
//但假设以后 Student 增加：private String internalRemark;或者：private String password;
//如果继续：return ResponseEntity.ok(student);这些内部字段就可能跟着 Student 一起序列化出去。
//所以：数据库/内部对象有什么字段，不应该自动决定 API 返回什么字段。这就是 Response DTO 的意义.
public class StudentResponse {

    private int id;
    private String name;
    private int age;
    private double score;

    public StudentResponse(
            int id,
            String name,
            int age,
            double score
    ){
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public double getScore(){
        return score;
    }
}
//今天不需要：setId() setName() ...因为这个对象是我们自己创建出来返回给客户端的。
//Jackson 只需要读取 getter，把它转换成 JSON。
//这里为什么没有无参构造方法？
//Day 49 的：CreateStudentRequest需要完成：JSON → Java 因此 Jackson 需要创建它，并给字段赋值。
//而今天的：StudentResponse 方向恰好相反：Java → JSON
//我们自己已经：new StudentResponse(...)创建好了对象。Jackson 主要负责读取数据并输出 JSON。

package com.example.student;

import jakarta.validation.constraints.*;

//DTO Data Transfer Object数据传输对象
//表示的是：客户端新增学生时允许提交的数据。
public class CreateStudentRequest {

    @NotBlank(message = "姓名不能为空")
    //对于 String：null "" "  "都会判定失败。这比单纯：@NotNull更适合姓名。
    private String name;

    @NotNull(message = "年龄不能为空")
    @Min(value =1,message = "年龄不能小于1")
    @Max(value = 150,message = "年龄不能不超过150")
    private Integer age;

    @NotNull(message ="成绩不能为空")
    @DecimalMin(
            value = "0.0",
            message = "成绩不能小于0"
    )
    @DecimalMax(
            value = "100.0",
            //HV000016 这次不是用户提交的数据错误，而是你写的 Validation 校验规则本身格式错误。我写成了100，0
            message = "成绩不能超过100"
    )
    private Double score;//这里使用的Double是为了如果客户端没有传递值，我们可以得到null，从而让@Notnull发现问题

    public CreateStudentRequest(){
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Integer getAge(){
        return age;
    }

    public void setAge(Integer age){
        this.age = age;
    }

    public Double getScore(){
        return score;
    }

    public void setScore(Double score){
        this.score = score;
    }
}

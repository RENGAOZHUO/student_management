package com.example.student;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class UpdateScoreRequest {

    @NotNull(message = "成绩不能为空")
    //表示：score 不能是 null。
    //所以客户端发送：{}反序列化以后：score == null校验失败。
    //错误信息：成绩不能为空.这也再次说明为什么我们之前使用：Double,而不是：double
    //因为 Double 可以保存：null
    @DecimalMin(
            value = "0.0",
            message = "成绩不能小于0"
    )
    //表示：score >= 0
    @DecimalMax(
            value = "100.0",
            message = "成绩不能超过100"
    )
    private Double score;

    public UpdateScoreRequest(){

    }

    public Double getScore(){
        return  score;
    }

    public void setScore(Double score){
        this.score = score;
    }
}

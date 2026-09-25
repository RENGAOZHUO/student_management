package com.example.student;

public enum UpdateScoreResult {

    SUCCESS,

    STUDENT_NOT_FOUND,

    INVALID_SCORE
    //如果在程序中将SUCCESS写错成SUCESS，IDEA会报错，而换成string不会那么好用
    //这种固定状态通常很适合使用 enum。
}

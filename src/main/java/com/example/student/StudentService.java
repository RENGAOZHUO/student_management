package com.example.student;

import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class StudentService {

    private final StudentDao studentDao;

    public StudentService(StudentDao studentDao){
        this.studentDao = studentDao;
    }

    public void showAllStudents() throws SQLException{

        for(Student student : studentDao.findall()){
            System.out.println(student);
        }
    }
}

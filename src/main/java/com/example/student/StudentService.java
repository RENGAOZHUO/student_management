package com.example.student;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

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

    public String grtStatus(){
        return "StudentService is ready";
    }

    public List<Student> findAllStudents() throws SQLException{

        return studentDao.findall();
    }

    public Student findStudentById(int id) throws SQLException{

        return studentDao.findById(id);
    }

    public int addStudent(Student student)throws SQLException{

        int rows = studentDao.addStudent(
                student.getName(),
                student.getAge(),
                student.getScore()
        );
        return rows;
    }

    public UpdateScoreResult updateScore(int id, Double score){

        if(score == null
        ||!Double.isFinite(score)
        ||score<0
        ||score>100
        ){
            return UpdateScoreResult.INVALID_SCORE;
        }

        try {
            boolean success = studentDao.updateScore(id,score);

            if(!success){
                return UpdateScoreResult.STUDENT_NOT_FOUND;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return UpdateScoreResult.SUCCESS;


    }

    public boolean deleteStudent(int id){

        try {
            return studentDao.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

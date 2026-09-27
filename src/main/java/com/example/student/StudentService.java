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

    public List<Student> findAllStudents(){

        try{
            return studentDao.findall();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Student findStudentById(int id){

        try{
            Student student = studentDao.findById(id);

            if(student == null){
                throw new StudentNotFoundException(
                        "学生不存在，id = " + id
                );
            }

            return student;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void addStudent(Student student){

        try{
            int rows = studentDao.addStudent(
                    student.getName(),
                    student.getAge(),
                    student.getScore()
            );

            if(rows != 1){
                throw new RuntimeException(
                        "新增学生失败"
                );
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public /*UpdateScoreResult*/ void updateScore(int id, Double score){

        if(score == null
        ||!Double.isFinite(score)
        ||score<0
        ||score>100
        ){
            /*return UpdateScoreResult.INVALID_SCORE;*/
            throw new IllegalArgumentException(
                    "成绩必须在0到100之间"
            );
        }

        try {
            boolean success = studentDao.updateScore(id,score);

            if(!success){
                /*return UpdateScoreResult.STUDENT_NOT_FOUND;*/
                throw new StudentNotFoundException(
                        "学生不存在，id = "+id
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        /*return UpdateScoreResult.SUCCESS;*/


    }

    public void deleteStudent(int id){

        try{
            boolean success = studentDao.deleteById(id);

            if(!success){
                throw new StudentNotFoundException(
                        "学生不存在，id = " + id
                );
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}

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

    public boolean updateScore(int id,double score){

        try {
            Student student = studentDao.findById(id);

            if(student==null){
                return false;
            }

            studentDao.updateScore(id,score);

            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteStudent(int id){

        try {
            Student student = studentDao.findById(id);

            if (student == null) {
               return false;
            }


        }catch(SQLException e){
            e.printStackTrace();
        }
        try {
            studentDao.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}

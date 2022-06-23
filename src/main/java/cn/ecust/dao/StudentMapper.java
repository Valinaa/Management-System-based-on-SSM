package cn.ecust.dao;

import java.util.List;

import cn.ecust.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper {
    Integer addStudent(Student student);
    List<Student> selectAll();
    int updateStu(@Param("number") String number,@Param("age") int age, @Param("subject")String subject);
    int deleteStu(@Param("number")String number);
    
    Student selectByAdmin(@Param("number")String number);
}
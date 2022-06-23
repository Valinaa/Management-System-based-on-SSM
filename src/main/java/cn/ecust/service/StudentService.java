package cn.ecust.service;

import cn.ecust.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentService {
	Integer addStudent(Student student);
	int updateStu(@Param("number") String number,@Param("age") int age, @Param("subject")String subject);
	int deleteStu(@Param("number")String number);
    Student selectByAdmin(@Param("number")String number);
    List<Student> selectAll();
}

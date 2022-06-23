package cn.ecust.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ecust.entity.Teacher;

public interface TeacherService {
	Integer addTeacher(Teacher teacher);
	int deleteTea(@Param("tNumber")String tNumber);
	int updateTea(@Param("tNumber")String tNumber,@Param("tSubject")String tSubject);
	List<Teacher> selectAll();
	Teacher selectByAdmin(@Param("tNumber")String tNumber);
}

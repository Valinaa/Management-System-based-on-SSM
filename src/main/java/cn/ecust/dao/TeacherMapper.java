package cn.ecust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.ecust.entity.Teacher;

/**
 * @author Chen Kang
 */
@Repository
public interface TeacherMapper {
	 Integer addTeacher(Teacher teacher);
	 int deleteTea(@Param("tNumber")String tNumber);
	 int updateTea(
			@Param("tNumber")String tNumber,
			@Param("tSubject")String tSubject
	);
	 List<Teacher> selectAll();
	 Teacher selectByAdmin(@Param("tNumber")String tNumber);
}

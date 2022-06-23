package cn.ecust.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.ecust.entity.Exam;

/**
 * @author Chen Kang
 */
@Repository
@Mapper
public interface ExamMapper {
	 int addExam(Exam exam);
	 int deleteExam(@Param("eid")int eid);
	 int updateExam(Exam exam);
	 List<Exam> selectAll();

}

package cn.ecust.service;

import cn.ecust.entity.Exam;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Chen Kang
 * @Date 2022/5/4
 */
public interface ExamService {
	int addExam(Exam exam);
	int deleteExam(@Param("eid")int eid);
	int updateExam(Exam exam);
	List<Exam> selectAll();
}

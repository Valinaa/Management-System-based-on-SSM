package cn.ecust.service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import cn.ecust.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ecust.entity.Exam;
import cn.ecust.dao.ExamMapper;

import lombok.Setter;

/**
 * @author Chen Kang
 * @Date 2022/5/4
 */

@Service("examServiceImpl")
public class ExamServiceImpl implements ExamService {

	@Autowired
	@Setter
	private ExamMapper examMapper;
	
	@Override
	public int addExam(Exam exam) {
		examMapper.addExam(exam);
		return  exam.getEid();
	}

	@Override
	public int deleteExam(int eid) {
		return examMapper.deleteExam(eid);
	}

	@Override
	public int updateExam(Exam exam) {
		return examMapper.updateExam(exam);
	}

	@Override
	public List<Exam> selectAll() {
		return examMapper.selectAll();
	}
	
	
}

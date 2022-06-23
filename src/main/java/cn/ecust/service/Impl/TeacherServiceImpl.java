package cn.ecust.service.Impl;

import java.util.List;

import cn.ecust.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ecust.entity.Teacher;
import cn.ecust.dao.TeacherMapper;

import lombok.Setter;

/**
 * @author Chen Kang
 * @Date 2022/5/4
 */

@Service
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	@Setter
	private TeacherMapper teacherMapper;
	
	@Override
	public Integer addTeacher(Teacher teacher) {
		return teacherMapper.addTeacher(teacher);
	}

	@Override
	public int deleteTea(String tNumber) {
		return teacherMapper.deleteTea(tNumber);
	}

	@Override
	public int updateTea(String tNumber, String tSubject) {
		return teacherMapper.updateTea(tNumber, tSubject);
	}

	@Override
	public List<Teacher> selectAll() {
		return teacherMapper.selectAll();
	}
	
	/**
	 * @param tNumber
	 * @return
	 */
	@Override
	public Teacher selectByAdmin(String tNumber) {
		return teacherMapper.selectByAdmin(tNumber);
	}
	
}

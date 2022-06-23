package cn.ecust.service.Impl;

import cn.ecust.dao.StudentMapper;
import cn.ecust.entity.Student;
import cn.ecust.service.StudentService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Chen Kang
 * @Date 2022/5/4
 */
@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	@Setter
	private StudentMapper studentMapper;
	
	@Override
	public Integer addStudent(Student student) {
		return studentMapper.addStudent(student);
	}

	@Override
	public int updateStu(String number,int age,String subject) {
		return studentMapper.updateStu(number, age, subject);
	}

	@Override
	public int deleteStu(String number) {
		return studentMapper.deleteStu(number);
	}
	
	/**
	 * @param number
	 * @return
	 */
	@Override
	public Student selectByAdmin(String number) {
		return studentMapper.selectByAdmin(number);
	}
	
	@Override
	public List<Student> selectAll() {
		return studentMapper.selectAll();
	}
	
}

package cn.ecust.controller;

import cn.ecust.entity.Teacher;
import cn.ecust.entity.User;
import cn.ecust.service.TeacherService;
import cn.ecust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Chen Kang
 * @Date 2022/5/4
 * @Description 教师信息控制类
 */

@Controller
public class TeacherController {
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/allTeacher")
	public String list(Model model) {
		List<Teacher> all=teacherService.selectAll();
		model.addAttribute("all", all);
		return "allTeacher";
	}
	
	@RequestMapping("/teacher/addTeacher")
	public String toAddTea(String admin,String password, HttpServletRequest request){
		String tNumber=request.getParameter("tNumber");
		String tName=request.getParameter("tName");
		String tSex=request.getParameter("tSex");
		String tSubject=request.getParameter("tSubject");
		User user=new User();
		user.setUid(tNumber);
		user.setRole(2);
		user.setAdmin(admin);
		user.setPassword(password);
		userService.addUser(user);
		Teacher teacher=new Teacher();
		teacher.settNumber(tNumber);
		teacher.settName(tName);
		teacher.settSex(tSex);
		teacher.settSubject(tSubject);
		teacherService.addTeacher(teacher);
		return "redirect:/allTeacher";
	}
	
	@RequestMapping("/teacher/deleteTea/{tNumber}")
	public String toDeleteTea(@PathVariable("tNumber") String tNumber){
		teacherService.deleteTea(tNumber);
		userService.deleteUser(tNumber);
		return "redirect:/allTeacher";
	}
	
	@RequestMapping("/teacher/updateTea/{tNumber}")
	public String toUpdateTea(@PathVariable("tNumber") String tNumber,String tSubject,String admin,String password){
		teacherService.updateTea(tNumber,tSubject);
		User user =new User();
		user.setUid(tNumber);
		user.setAdmin(admin);
		user.setPassword(password);
		userService.updateUser(user);
		return "redirect:/allTeacher";
	}
}

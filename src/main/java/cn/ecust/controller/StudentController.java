package cn.ecust.controller;

import cn.ecust.entity.Student;
import cn.ecust.entity.User;
import cn.ecust.service.StudentService;
import cn.ecust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Valina
 * @Date 2022/5/4
 * @Description 学生信息控制类
 */

@Controller
public class StudentController {
    
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;
    
    @RequestMapping("/allStudent")
    public String list(Model model) {
        List<Student> all=studentService.selectAll();
        model.addAttribute("all", all);
        return "allStudent";
    }
    
    @RequestMapping("/student/addStudent")
    public String toAddStu(String admin,String password, HttpServletRequest request){
        String number=request.getParameter("number");
        String name=request.getParameter("name");
        String sex=request.getParameter("sex");
        String subject=request.getParameter("subject");
        int age=Integer.parseInt(request.getParameter("age"));
        User user=new User();
        user.setUid(number);
        user.setRole(3);
        user.setAdmin(admin);
        user.setPassword(password);
        userService.addUser(user);
        Student student=new Student();
        student.setNumber(number);
        student.setName(name);
        student.setAge(age);
        student.setSex(sex);
        student.setSubject(subject);
        studentService.addStudent(student);
        return "redirect:/allStudent";
    }
    
    @RequestMapping("/student/deleteStu/{number}")
    public String toDelStu(@PathVariable("number") String number){
        studentService.deleteStu(number);
        userService.deleteUser(number);
        return "redirect:/allStudent";
    }
    
    @RequestMapping("/student/updateStu/{number}")
    public String toUpdateStu(@PathVariable("number")String number,int age,String subject,String admin,String password){
        studentService.updateStu(number, age, subject);
        User user =new User();
        user.setUid(number);
        user.setAdmin(admin);
        user.setPassword(password);
        userService.updateUser(user);
        return "redirect:/allStudent";
    }
    
}

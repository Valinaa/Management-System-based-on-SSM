package cn.ecust.controller;

import cn.ecust.entity.Student;
import cn.ecust.entity.User;
import cn.ecust.service.StudentService;
import cn.ecust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Valinaa
 * @Date 2022/5/4
 * @Description 学生信息控制类
 */

@Controller
public class StudentController {
    
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;
    
    @GetMapping("/allStudent")
    public String list(Model model) {
        List<Student> all = studentService.selectAll();
        model.addAttribute("all", all);
        return "allStudent";
    }
    
    @PostMapping("/student")
    @ResponseBody
    public String toAddStu(String admin, String password, HttpServletRequest request) {
        String number = request.getParameter("number");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String subject = request.getParameter("subject");
        int age = Integer.parseInt(request.getParameter("age"));
        User user = new User();
        user.setUid(number);
        user.setRole(3);
        user.setAdmin(admin);
        user.setPassword(password);
        Student student = new Student();
        student.setNumber(number);
        student.setName(name);
        student.setAge(age);
        student.setSex(sex);
        student.setSubject(subject);
        try {
            userService.addUser(user);
            studentService.addStudent(student);
            return "添加成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "添加失败！";
        }
    }
    
    @DeleteMapping("/student/{number}")
    @ResponseBody
    public String toDelStu(@PathVariable("number") String number) {
        try {
            studentService.deleteStu(number);
            userService.deleteUser(number);
            return "已删除！";
        } catch (Exception e) {
            e.printStackTrace();
            return "删除失败！";
        }
    }
    
    @PutMapping("/student/{number}")
    @ResponseBody
    public String toUpdateStu(@PathVariable("number") String number, Integer age, String subject, String admin, String password) {
        User user = new User();
        user.setUid(number);
        user.setAdmin(admin);
        user.setPassword(password);
        try {
            studentService.updateStu(number, age, subject);
            userService.updateUser(user);
            return "修改成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "修改失败！";
        }
    }
    
}

package cn.ecust.controller;

import cn.ecust.entity.Teacher;
import cn.ecust.entity.User;
import cn.ecust.service.TeacherService;
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
 * @Description 教师信息控制类
 */

@Controller
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserService userService;
    
    @GetMapping("/allTeacher")
    public String list(Model model) {
        List<Teacher> all = teacherService.selectAll();
        model.addAttribute("all", all);
        return "allTeacher";
    }
    
    @PostMapping("/teacher")
    @ResponseBody
    public String toAddTea(String admin, String password, HttpServletRequest request) {
        String tNumber = request.getParameter("tNumber");
        String tName = request.getParameter("tName");
        String tSex = request.getParameter("tSex");
        String tSubject = request.getParameter("tSubject");
        User user = new User();
        user.setUid(tNumber);
        user.setRole(2);
        user.setAdmin(admin);
        user.setPassword(password);
        Teacher teacher = new Teacher();
        teacher.settNumber(tNumber);
        teacher.settName(tName);
        teacher.settSex(tSex);
        teacher.settSubject(tSubject);
        try {
            userService.addUser(user);
            teacherService.addTeacher(teacher);
            return "添加成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "添加失败！";
        }
        
    }
    
    @DeleteMapping("/teacher/{tNumber}")
    @ResponseBody
    public String toDeleteTea(@PathVariable("tNumber") String tNumber) {
        try {
            teacherService.deleteTea(tNumber);
            userService.deleteUser(tNumber);
            return "删除成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "删除失败！";
        }
        
    }
    
    @PutMapping("/teacher/{tNumber}")
    @ResponseBody
    public String toUpdateTea(@PathVariable("tNumber") String tNumber, String tSubject, String admin, String password) {
        User user = new User();
        user.setUid(tNumber);
        user.setAdmin(admin);
        user.setPassword(password);
        try {
            teacherService.updateTea(tNumber, tSubject);
            userService.updateUser(user);
            return "修改成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "修改失败！";
        }
        
    }
}

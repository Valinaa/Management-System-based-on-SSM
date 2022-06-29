package cn.ecust.controller;

import cn.ecust.entity.User;
import cn.ecust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Valinaa
 * @Date 2022/5/10
 * @Description 管理员信息控制类
 */
@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/allUser")
    public String list(Model model) {
        List<User> all = userService.selectAll();
        model.addAttribute("all", all);
        return "allUser";
    }
    
    @PostMapping("/user")
    @ResponseBody
    public String toAddUser(String uid, String admin, String password) {
        User user = new User();
        user.setUid(uid);
        user.setRole(1);
        user.setAdmin(admin);
        user.setPassword(password);
        try {
            userService.addUser(user);
            return "添加成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "添加失败！";
        }
        
    }
    
    @DeleteMapping("/user/{uid}")
    @ResponseBody
    public String toDeleteUser(@PathVariable("uid") String uid) {
        try {
            userService.deleteUser(uid);
            return "删除成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "删除失败！";
        }
        
    }
    
    @PutMapping("/user/{uid}")
    @ResponseBody
    public String toUpdateUser(@PathVariable("uid") String uid, String admin, String password) {
        User user = new User();
        user.setUid(uid);
        user.setAdmin(admin);
        user.setPassword(password);
        try {
            userService.updateUser(user);
            return "修改成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "修改失败！";
        }
    }
}

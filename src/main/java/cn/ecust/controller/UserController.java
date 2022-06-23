package cn.ecust.controller;

import cn.ecust.entity.User;
import cn.ecust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Chen Kang
 * @Date 2022/5/10
 * @Description 管理员信息控制类
 */
@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("/allUser")
    public String list(Model model){
        List<User> all = userService.selectAll();
        model.addAttribute("all",all);
        return "allUser";
    }
    
    @RequestMapping("user/addUser")
    public String toAddUser(String uid,String admin, String password){
        User user=new User();
        user.setUid(uid);
        user.setRole(1);
        user.setAdmin(admin);
        user.setPassword(password);
        userService.addUser(user);
        return "redirect:/allUser";
    }
    
    @RequestMapping("/user/deleteUser/{uid}")
    public String toDeleteUser(@PathVariable("uid") String uid){
        userService.deleteUser(uid);
        return "redirect:/allUser";
    }
    
    @RequestMapping("/user/updateUser/{uid}")
    public String toUpdateUser(@PathVariable("uid") String uid,String admin,String password){
        User user=new User();
        user.setUid(uid);
        user.setAdmin(admin);
        user.setPassword(password);
        userService.updateUser(user);
        return "redirect:/allUser";
    }
}

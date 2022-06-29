package cn.ecust.controller;

import cn.ecust.entity.Escore;
import cn.ecust.service.EscoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Valinaa
 * @Date 2022/5/17
 * @Description 成绩表控制类
 */
@Controller
public class EscoreController {
    @Autowired
    private EscoreService escoreService;
    
    @GetMapping("/allScore")
    public String getAllScore(Model model) {
        List<Escore> scores = escoreService.selectAll();
        model.addAttribute("allS", scores);
        return "allScore";
    }
    
    @DeleteMapping("/score/{sid}")
    @ResponseBody
    public String toDeleteEscore(@PathVariable("sid") Integer sid) {
        try {
            escoreService.deleteEscore(sid);
            return "删除成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "删除失败！";
        }
    }
    
    @PutMapping("/score/{sid}")
    @ResponseBody
    public String toUpdateScore(@PathVariable("sid") Integer sid, Integer score) {
        try {
            escoreService.updateScore(sid, score);
            return "修改成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "修改失败！";
        }
        
    }
}

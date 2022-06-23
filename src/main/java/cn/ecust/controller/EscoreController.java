package cn.ecust.controller;

import cn.ecust.entity.Escore;
import cn.ecust.service.EscoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Chen Kang
 * @Date 2022/5/17
 * @Description 成绩表控制类
 */
@Controller
public class EscoreController {
    @Autowired
    private EscoreService escoreService;
    
    @RequestMapping("/allScore")
    public String getAllScore(Model model){
        List<Escore> scores=escoreService.selectAll();
        model.addAttribute("allS",scores);
        return "allScore";
    }
    
    @RequestMapping("/score/deleteEscore/{sid}")
    public String toDeleteEscore(@PathVariable("sid")Integer sid){
        escoreService.deleteEscore(sid);
        return "redirect:/allScore";
    }
    
    @RequestMapping("/score/updateScore/{sid}")
    public String toUpdateScore(@PathVariable("sid")Integer sid,Integer score){
        escoreService.updateScore(sid,score);
        return "redirect:/allScore";
    }
}

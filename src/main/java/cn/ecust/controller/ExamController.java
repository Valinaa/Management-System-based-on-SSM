package cn.ecust.controller;

import cn.ecust.entity.Exam;
import cn.ecust.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Valinaa
 * @Date 2022/5/15
 * @Description 考试控制类
 */

@Controller
public class ExamController {
    @Autowired
    private ExamService examService;
    
    @GetMapping("/allExam")
    public String getAllExams(Model model) {
        List<Exam> allE = examService.selectAll();
        model.addAttribute("allE", allE);
        return "allExam";
    }
    
    @DeleteMapping("/exam/{eid}")
    @ResponseBody
    public String toDeleteExam(@PathVariable("eid") Integer eid) {
        try {
            examService.deleteExam(eid);
            return "删除成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "删除失败！";
        }
        
    }
    
    @PutMapping("/exam/{eid}")
    @ResponseBody
    public String updateExam(@PathVariable("eid") Integer eid, HttpServletRequest request) {
        String eName = request.getParameter("eName");
        LocalDateTime time = LocalDateTime.parse(request.getParameter("time"));
        Integer durations = Integer.parseInt(request.getParameter("durations"));
        String remark = request.getParameter("remark");
        Exam exam = new Exam();
        exam.setEid(eid);
        exam.seteName(eName);
        exam.setRemark(remark);
        exam.setTime(time);
        exam.setDurations(durations);
        try {
            examService.updateExam(exam);
            return "修改成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "修改失败！";
        }
    }
}

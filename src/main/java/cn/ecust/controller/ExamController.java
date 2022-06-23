package cn.ecust.controller;

import cn.ecust.entity.Exam;
import cn.ecust.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Valina
 * @Date 2022/5/15
 * @Description 考试控制类
 */

@Controller
public class ExamController {
    @Autowired
    private ExamService examService;
    
    @RequestMapping("/allExam")
    public String getAllExams(Model model) {
        List<Exam> allE = examService.selectAll();
        model.addAttribute("allE", allE);
        return "allExam";
    }
    
    @RequestMapping("/exam/deleteExam/{eid}")
    public String toDeleteExam(@PathVariable("eid") Integer eid) {
        examService.deleteExam(eid);
        return "redirect:/allExam";
    }
    
    @RequestMapping("/exam/updateExam/{eid}")
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
        examService.updateExam(exam);
        return "redirect:/allExam";
    }
}

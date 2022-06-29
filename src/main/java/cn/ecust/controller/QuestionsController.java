package cn.ecust.controller;

import cn.ecust.entity.Questions;
import cn.ecust.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Valinaa
 * @Date 2022/5/12
 * @Description 题库控制类
 */
@Controller
public class QuestionsController {
    @Autowired
    private QuestionsService questionsService;
    
    @GetMapping("/allQuestions")
    public String list(Model model) {
        List<Questions> all = questionsService.selectAll();
        model.addAttribute("all", all);
        return "allQuestions";
    }
    
    @PostMapping("/question")
    @ResponseBody
    public String toAddStu(HttpServletRequest request) {
        String QSub = request.getParameter("QSub");
        String QType = request.getParameter("QType");
        String question = request.getParameter("question");
        String answer = request.getParameter("answer");
        int marks = Integer.parseInt(request.getParameter("marks"));
        Questions Question = new Questions();
        Question.setQSub(QSub);
        Question.setQType(QType);
        Question.setQuestion(question);
        Question.setAnswer(answer);
        Question.setMarks(marks);
        try {
            int id = questionsService.addQuestion(Question);
            System.out.println("获得的主键qid值为：" + id);
            return "添加成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "添加失败！";
        }
    }
    
    @DeleteMapping("/question/{qid}")
    @ResponseBody
    public String toDelQues(@PathVariable("qid") Integer qid) {
        try {
            questionsService.deleteQuestion(qid);
            return "删除成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "删除失败！";
        }
    }
    
    @PutMapping("/question/{qid}")
    @ResponseBody
    public String toUpdateQues(@PathVariable("qid") Integer qid, HttpServletRequest request) {
        String QSub = request.getParameter("QSub");
        String QType = request.getParameter("QType");
        String question = request.getParameter("question");
        String answer = request.getParameter("answer");
        int marks = Integer.parseInt(request.getParameter("marks"));
        Questions Question = new Questions();
        Question.setQid(qid);
        Question.setQSub(QSub);
        Question.setQType(QType);
        Question.setQuestion(question);
        Question.setAnswer(answer);
        Question.setMarks(marks);
        try {
            questionsService.updateQuestion(Question);
            return "修改成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "修改失败！";
        }
        
    }
}

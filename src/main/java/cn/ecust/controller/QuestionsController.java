package cn.ecust.controller;

import cn.ecust.entity.Questions;
import cn.ecust.entity.Student;
import cn.ecust.entity.User;
import cn.ecust.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Chen Kang
 * @Date 2022/5/12
 * @Description 题库控制类
 */
@Controller
public class QuestionsController {
    @Autowired
    private QuestionsService questionsService;
    
    @RequestMapping("/allQuestions")
    public String list(Model model) {
        List<Questions> all=questionsService.selectAll();
        model.addAttribute("all", all);
        return "allQuestions";
    }
    
    @RequestMapping("/questions/addQuestion")
    public String toAddStu(HttpServletRequest request){
        String QSub=request.getParameter("QSub");
        String QType=request.getParameter("QType");
        String question=request.getParameter("question");
        String answer=request.getParameter("answer");
        int marks=Integer.parseInt(request.getParameter("marks"));
        Questions Question=new Questions();
        Question.setQSub(QSub);
        Question.setQType(QType);
        Question.setQuestion(question);
        Question.setAnswer(answer);
        Question.setMarks(marks);
        int id=questionsService.addQuestion(Question);
        System.out.println("获得的主键qid值为："+id);
        return "redirect:/allQuestions";
    }
    
    @RequestMapping("/question/deleteQues/{qid}")
    public String toDelQues(@PathVariable("qid") Integer qid){
        questionsService.deleteQuestion(qid);
        return "redirect:/allQuestions";
    }
    
    @RequestMapping("/questions/updateQues/{qid}")
    public String toUpdateQues(@PathVariable("qid")Integer qid,HttpServletRequest request){
        String QSub=request.getParameter("QSub");
        String QType=request.getParameter("QType");
        String question=request.getParameter("question");
        String answer=request.getParameter("answer");
        int marks=Integer.parseInt(request.getParameter("marks"));
        Questions Question=new Questions();
        Question.setQid(qid);
        Question.setQSub(QSub);
        Question.setQType(QType);
        Question.setQuestion(question);
        Question.setAnswer(answer);
        Question.setMarks(marks);
        questionsService.updateQuestion(Question);
        return "redirect:/allQuestions";
    }
}

package cn.ecust.controller;

import cn.ecust.entity.*;
import cn.ecust.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Valinaa
 * @Date 2022/5/4
 * @Description 中央控制器
 */
@Controller
public class IndexController {
    
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionsService questionsService;
    @Autowired
    private ExamService examService;
    @Autowired
    private EscoreService escoreService;
    
    /*
     *执行对教师的数据储存
     */
    
    @RequestMapping("/RegisCheck2")
    public String regisTea(String admin, String password, HttpServletRequest request) {
        String tNumber = request.getParameter("tNumber");
        String tName = request.getParameter("tName");
        String tSex = request.getParameter("tSex");
        String tSubject = request.getParameter("tSubject");
        User user = new User();
        user.setUid(tNumber);
        user.setRole(2);
        user.setAdmin(admin);
        user.setPassword(password);
        userService.addUser(user);
        Teacher teacher = new Teacher();
        teacher.settNumber(tNumber);
        teacher.settName(tName);
        teacher.settSex(tSex);
        teacher.settSubject(tSubject);
        teacherService.addTeacher(teacher);
        return "Login";
    }
    
    /*
     *执行对学生的数据储存
     */
    
    @RequestMapping("/RegisCheck3")
    public String regisStu(String admin, String password, HttpServletRequest request) {
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
        userService.addUser(user);
        Student student = new Student();
        student.setNumber(number);
        student.setName(name);
        student.setAge(age);
        student.setSex(sex);
        student.setSubject(subject);
        studentService.addStudent(student);
        return "Login";
    }
    
    /*
     *检验登录用户名与密码是否正确
     *再跳转至主页面
     */
    
    @RequestMapping("/LoginCheck")
    public String loginVerify(HttpServletRequest request, Model model, HttpServletResponse response) {
        /*
         * 根据前端输入的身份
         * 先进行身份输入的合法性判断
         * */
        String j = request.getParameter("role");
        model.addAttribute("Error", " ");
        boolean result = false;
        if (j.length() > 2) {
            model.addAttribute("Error", "请输入您的身份！");
            return "Login";
        } else {
            /*
             * 先判断用户名与密码是否正确
             * */
            String admin = request.getParameter("admin");
            String password = request.getParameter("password");
            List<Map<String, String>> admPas = userService.selectAdmPas();
            Iterator<Map<String, String>> it = admPas.iterator();
            int a = admPas.size(), b = 0;
            while (it.hasNext()) {
                Map<String, String> aP = it.next();
                List<String> check = new ArrayList<>();
                for (String value : aP.values()) {
                    check.add(value);
                }
                if (!check.get(0).equals(admin) && !check.get(1).equals(admin)) {
                    b++;
                } else if (!check.get(0).equals(password) && !check.get(1).equals(password)) {
                    model.addAttribute("Error", "密码错误！请重新输入！");
                    break;
                } else {
                    result = true;
                    break;
                }
            }
            /*
             * 若用户名密码正确，判断用户类型并跳转指定页面
             * 通过添加cookie实现保存用户的登录状态与信息储存
             * */
            if (result) {
                int i = Integer.parseInt(j);
                String id = userService.selectByAdmin(admin);
                Cookie role = new Cookie("role", j);
                if (i == 1) {
                    Cookie admin1 = new Cookie("admin1", admin);
                    Cookie password1 = new Cookie("password1", password);
                    Cookie name1 = new Cookie("name1", id);
                    Cookie aRole = new Cookie("aRole", "管理员");
                    response.addCookie(admin1);
                    response.addCookie(password1);
                    response.addCookie(name1);
                    response.addCookie(aRole);
                } else if (i == 2) {
                    Cookie admin2 = new Cookie("admin2", admin);
                    Cookie password2 = new Cookie("password2", password);
                    Teacher teacher = teacherService.selectByAdmin(id);
                    Cookie tName = new Cookie("tName", teacher.gettName());
                    Cookie tSex = new Cookie("tSex", teacher.gettSex());
                    Cookie tNum = new Cookie("tNum", teacher.gettNumber());
                    Cookie tSubject = new Cookie("tSubject", teacher.gettSubject());
                    Cookie tRole = new Cookie("tRole", "教师");
                    response.addCookie(admin2);
                    response.addCookie(password2);
                    response.addCookie(tName);
                    response.addCookie(tSex);
                    response.addCookie(tNum);
                    response.addCookie(tSubject);
                    response.addCookie(tRole);
                } else {
                    Cookie admin3 = new Cookie("admin3", admin);
                    Cookie password3 = new Cookie("password3", password);
                    Student student = studentService.selectByAdmin(id);
                    Cookie name = new Cookie("name", student.getName());
                    Cookie age = new Cookie("age", String.valueOf(student.getAge()));
                    Cookie sex = new Cookie("sex", student.getSex());
                    Cookie num = new Cookie("num", student.getNumber());
                    Cookie subject = new Cookie("subject", student.getSubject());
                    Cookie sRole = new Cookie("sRole", "学生");
                    response.addCookie(admin3);
                    response.addCookie(password3);
                    response.addCookie(name);
                    response.addCookie(age);
                    response.addCookie(sex);
                    response.addCookie(num);
                    response.addCookie(subject);
                    response.addCookie(sRole);
                }
                response.addCookie(role);
                return "redirect:/mainView";
            }
            /*
             * 出现错误返回登录界面，并在前端返回错误信息
             * */
            else {
                if (a == b) {
                    model.addAttribute("Error", "用户名不存在！请重新输入或注册！");
                }
                return "Login";
            }
        }
    }
    
    /*
     *Ajax检验用户名是否重复
     */
    
    @RequestMapping("/CheckAdmin")
    @ResponseBody
    public String checkAdmin(HttpServletRequest request, Model model) {
        List<String> list = userService.selectAdmin();
        String admin = request.getParameter("admin");
        Iterator<String> it = list.iterator();
        String Admin = " ";
        boolean repeat = false;
        while (it.hasNext()) {
            if (it.next().equals(admin)) {
                Admin = "<font color='red'>用户名已存在！</font>";
                repeat = true;
                break;
            }
        }
        if (!repeat) {
            Admin = "<font color='green'>用户名可使用</font>";
        }
        return Admin;
    }
    
    /*
     *密码修改
     */
    
    @PutMapping("/password/{admin}/{password}")
    @ResponseBody
    public String toUpdatePassword(@PathVariable("admin") String admin, @PathVariable("password") String password) {
        try {
            userService.updatePass(admin, password);
            return "密码修改成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "密码修改失败！";
        }
    }
    
    /*
     * 导入考试信息库
     * 根据输入的题号创建对应数据表
     */
    
    @PostMapping("/exam/{type}")
    @ResponseBody
    public String toSetExam(@PathVariable("type") Integer type, HttpServletRequest request) {
        String eName = request.getParameter("eName");
        String eSubject = request.getParameter("eSubject");
        LocalDateTime time = LocalDateTime.parse(request.getParameter("time"));
        int durations = Integer.parseInt(request.getParameter("durations"));
        String remark = request.getParameter("remark");
        /*
        插入考试数据
         */
        Exam exam = new Exam();
        exam.seteName(eName);
        exam.setType(type);
        exam.seteSubject(eSubject);
        exam.setTime(time);
        exam.setDurations(durations);
        exam.setRemark(remark);
        try {
            String e = String.valueOf(examService.addExam(exam));
            System.out.println("自增Eid值为：" + e);
            e = "Exam" + e;
            questionsService.createTemp(e);
            System.out.println("表名为：" + e);
            String qid = request.getParameter("qids");
            String[] qids = qid.split(",");
            for (int i = 0; i < qids.length; i++) {
                Questions question = questionsService.selectByQid(Integer.parseInt(qids[i]));
                /*
                 * 将"\n"转化为表单元素与换行符<br>
                 * 通过自增的i值实现不同题目name属性的独立
                 * */
                switch (Integer.parseInt(question.getQType())) {
                    case 1:
                        //单选题
                        String T0 = "<br><input name=\"Answer" + i + "\" class=\"form-check-input\" type=\"radio\" value=\"A\">";
                        String T1 = "<br><input name=\"Answer" + i + "\" class=\"form-check-input\" type=\"radio\" value=\"B\">";
                        String T2 = "<br><input name=\"Answer" + i + "\" class=\"form-check-input\" type=\"radio\" value=\"C\">";
                        String T3 = "<br><input name=\"Answer" + i + "\" class=\"form-check-input\" type=\"radio\" value=\"D\">";
                        String B = question.getQuestion().replaceFirst("\\n", T0);
                        String C = B.replaceFirst("\\n", T1);
                        String D = C.replaceFirst("\\n", T2);
                        String E = D.replaceFirst("\\n", T3);
                        question.setQuestion(E);
                        break;
                    case 2:
                        //多选题
                        String M0 = "<br><input name=\"Answer" + i + "\" class=\"form-check-input\" type=\"checkbox\" value=\"A\">";
                        String M1 = "<br><input name=\"Answer" + i + "\" class=\"form-check-input\" type=\"checkbox\" value=\"B\">";
                        String M2 = "<br><input name=\"Answer" + i + "\" class=\"form-check-input\" type=\"checkbox\" value=\"C\">";
                        String M3 = "<br><input name=\"Answer" + i + "\" class=\"form-check-input\" type=\"checkbox\" value=\"D\">";
                        String B2 = question.getQuestion().replaceFirst("\\n", M0);
                        String C2 = B2.replaceFirst("\\n", M1);
                        String D2 = C2.replaceFirst("\\n", M2);
                        String E2 = D2.replaceFirst("\\n", M3);
                        question.setQuestion(E2);
                        break;
                    case 3:
                        //判断题
                        String Tr = "<br><input name=\"Answer" + i + "\" class=\"form-check-input\" type=\"radio\" value=\"对\">";
                        String Fa = "<br><input name=\"Answer" + i + "\" class=\"form-check-input\" type=\"radio\" value=\"错\">";
                        String B3 = question.getQuestion().replaceFirst("\\n", Tr);
                        String C3 = B3.replaceFirst("\\n", Fa);
                        question.setQuestion(C3);
                        break;
                    default:
                        //填空题与简答题
                        String W = "<br><input name=\"Answer" + i + "\" class=\"form-control\" type=\"text\" placeholder=\"请作答\">";
                        String B5 = question.getQuestion().replaceAll("\\n", W);
                        question.setQuestion(B5);
                        break;
                }
                questionsService.addTemp(e, question);
                System.out.println("第" + i + "次插入的qid为" + qids[i]);
                System.out.println("这一次插入的question是：" + question.getQuestion());
            }
            return "添加成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "添加失败";
        }
        
    }
    
    /*
     * 根据数据表显示对应题目
     * 获取相应数据
     * 跳转至考试界面
     */
    
    @RequestMapping("/getQues/{tableName}")
    public String toGetQues(@PathVariable("tableName") String tableName, RedirectAttributes model) {
        List<Questions> allQ = questionsService.selectTemp(tableName);
        model.addFlashAttribute("allQ", allQ);
        List<Exam> exams = examService.selectAll();
        Exam re = new Exam();
        /*
         * 遍历考试列表
         * 获取对应的考试信息
         * */
        for (Exam exam : exams) {
            String e = "Exam" + exam.getEid();
            if (e.equals(tableName)) {
                model.addFlashAttribute("EID", exam.getEid());
                re = exam;
                break;
            }
        }
        /*
         * 传入考试相关信息
         * */
        model.addFlashAttribute("EName", re.geteName());
        model.addFlashAttribute("subj", re.geteSubject());
        model.addFlashAttribute("Durations", re.getDurations());
        model.addFlashAttribute("Time", re.getTime());
        return "redirect:/TakeExam";
    }
    
    /*
     * 对客观题进行自动批改
     * 过滤主观题
     * */
    
    @RequestMapping("/checkAnswer")
    /*通过实体获取JSON数组*/
    public void toCheckAnswer(@RequestBody List<QuesCheck> check, String ExamID, String NUM, HttpServletRequest request, HttpServletResponse response) {
        List<HashMap<String, String>> an = questionsService.getQuesAnswer();
        List<String> li = new ArrayList<>();
        HashMap<String, String> my = new HashMap<>();
        System.out.println("ExamID:" + ExamID + "\n" + "NUM:" + NUM);
        int myMarks = 0, totalMarks = 0;
        /*
         * 构建新的题号-答案 键值对，便于批阅
         * */
        for (HashMap<String, String> map : an) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String mapValue = String.valueOf(entry.getValue());
                li.add(mapValue);
            }
        }
        for (int j = 0; j < li.size(); j += 2) {
            my.put(li.get(j + 1), li.get(j));
            System.out.println("新的key:" + li.get(j + 1) + "新的value：" + li.get(j));
        }
        /*
         * 获取学生答题情况
         * 并与题库答案对比
         * 计算总分与实际得分
         * */
        for (QuesCheck qc : check) {
            System.out.println(qc.toString());
            if (my.containsKey(qc.getQid().toString())) {
                totalMarks += qc.getMarks();
                if (my.get(qc.getQid().toString()).equals(qc.getMyans())) {
                    myMarks += qc.getMarks();
                }
            }
        }
        /*
         * 添加成绩表
         * */
        System.out.println("总分：" + totalMarks + "\n" + "成绩：" + myMarks);
        Escore escore = new Escore();
        escore.setExamId(Integer.parseInt(ExamID));
        escore.setsNumber(NUM);
        escore.setTotalScore(totalMarks);
        escore.setScore(myMarks);
        escoreService.addEscore(escore);
        System.out.println("考试添加成功！");
        /*
         * ajax请求完成之后重定向至主页面
         * */
        String type = request.getHeader("X-Requested-With");
        String n = "XMLHttpRequest";
        if (n.equals(type)) {
            //处理AJAX请求，设置响应头信息
            response.setHeader("REDIRECT", "REDIRECT");
            //需要跳转页面的URL
            response.setHeader("CONTEXTPATH", "/mainView");
        }
    }
    
    @RequestMapping("/Login")
    public String getLogin() {
        return "Login";
    }
    
    @RequestMapping("/Register")
    public String getRegister() {
        return "Register";
    }
    
    @RequestMapping("/TakeExam")
    public String getTakeExam() {
        return "TakeExam";
    }
    
    @RequestMapping("/mainView")
    public String getMainView(Model model) {
        List<Exam> test = examService.selectAll();
        model.addAttribute("test", test);
        return "mainView";
    }
}

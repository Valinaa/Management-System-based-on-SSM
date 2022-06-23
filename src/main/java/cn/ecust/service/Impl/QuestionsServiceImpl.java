package cn.ecust.service.Impl;

import cn.ecust.dao.QuestionsMapper;
import cn.ecust.entity.Questions;
import cn.ecust.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chen Kang
 * @Date 2022/5/12
 * @Description
 */
@Service
public class QuestionsServiceImpl implements QuestionsService {
    @Autowired
    private QuestionsMapper questionsMapper;
    @Override
    public int addQuestion(Questions question) {
        questionsMapper.addQuestion(question);
        return question.getQid();
    }
    @Override
    public int deleteQuestion(Integer qid) {
        return questionsMapper.deleteQuestion(qid);
    }
    @Override
    public int updateQuestion(Questions question) {
        return questionsMapper.updateQuestion(question);
    }

    @Override
    public int createTemp(String tableName) {
        return questionsMapper.createTemp(tableName);
    }

    @Override
    public int dropTemp(String tableName) {
        return questionsMapper.dropTemp(tableName);
    }
    
    @Override
    public int addTemp(String tableName, Questions question) {
        return questionsMapper.addTemp(tableName,question);
    }

    @Override
    public Questions selectByQid(Integer qid) {
        return questionsMapper.selectByQid(qid);
    }
    
    @Override
    public List<Questions> selectTemp(String tableName) {
        return questionsMapper.selectTemp(tableName);
    }
    
    @Override
    public List<Questions> selectAll() {
        return questionsMapper.selectAll();
    }

    @Override
    public List<HashMap<String, String>> getQuesAnswer() {
        return questionsMapper.getQuesAnswer();
    }
}

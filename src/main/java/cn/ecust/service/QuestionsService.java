package cn.ecust.service;

import cn.ecust.entity.Questions;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chen Kang
 * @Date 2022/5/12
 * @Description
 */
public interface QuestionsService {
    
    int addQuestion(Questions question);
    int deleteQuestion(Integer qid);
    int updateQuestion(Questions question);
    int createTemp(@Param("tableName")String tableName);
    int dropTemp(@Param("tableName")String tableName);
    int addTemp(@Param("tableName")String tableName,Questions question);
    Questions selectByQid(@Param("qid") Integer qid);
    List<Questions> selectTemp(@Param("tableName")String tableName);
    List<Questions> selectAll();
    List<HashMap<String,String>> getQuesAnswer();
    
}
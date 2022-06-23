package cn.ecust.dao;

import cn.ecust.entity.Questions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @author Chen Kang
 * @Date 2022/5/12
 * @Description
 */
@Repository
@Mapper
public interface QuestionsMapper {
    int addQuestion(Questions question);
    int deleteQuestion(@Param("qid") Integer qid);
    int updateQuestion(Questions question);
    List<HashMap<String, String>> getQuesAnswer();
    int createTemp(@Param("tableName") String tableName);
    int addTemp(@Param("tableName") String tableName,@Param("Question") Questions question);
    int dropTemp(@Param("tableName") String tableName);
    List<Questions> selectTemp(@Param("tableName") String tableName);
    Questions selectByQid(@Param("qid") Integer qid);
    List<Questions> selectAll();
    
}

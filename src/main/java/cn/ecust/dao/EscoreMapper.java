package cn.ecust.dao;

import cn.ecust.entity.Escore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Chen Kang
 * @Date 2022/5/17
 * @Description 成绩表
 */
@Repository
@Mapper
public interface EscoreMapper {
    int addEscore(Escore escore);
    int deleteEscore(@Param("sid")int sid);
    int updateScore(@Param("sid")int sid,@Param("score")int score);
 //   int scoreTemp(@Param("tempName") String tempName);
    List<Escore> selectAll();
    
}

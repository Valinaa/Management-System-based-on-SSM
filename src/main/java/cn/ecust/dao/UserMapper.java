package cn.ecust.dao;

import cn.ecust.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Chen Kang
 * @Date 2022/5/5
 * @Description
 */
@Repository
public interface UserMapper {
    
    Integer addUser(User user);
    
    List<String> selectAdmin();
    List<Map<String,String>> selectAdmPas();
    List<User> selectAll();
    List<String> selectUid();
    
    String selectByAdmin(@Param("admin")String admin);
    int updateUser(User user);
    
    int updatePass(@Param("admin") String admin,@Param("password")String password);
    int deleteUser(@Param("uid")String uid);
    
}

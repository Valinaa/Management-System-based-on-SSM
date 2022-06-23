package cn.ecust.service;

import cn.ecust.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Chen Kang
 * @Date 2022/5/5
 * @Description
 */
public interface UserService {
    
    Integer addUser(User user);
    List<String> selectAdmin();
    List<Map<String,String>> selectAdmPas();
    List<String> selectUid();
    int updateUser(User user);
    int updatePass(@Param("admin")String admin,@Param("password")String password);
    String selectByAdmin(@Param("admin")String admin);
    int deleteUser(@Param("uid")String uid);
    List<User> selectAll();
}

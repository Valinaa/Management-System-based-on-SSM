package cn.ecust.service.Impl;

import cn.ecust.dao.UserMapper;
import cn.ecust.entity.User;
import cn.ecust.service.UserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Chen Kang
 * @Date 2022/5/5
 * @Description
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    @Setter
    private UserMapper userMapper;
    
    /**
     * @param user
     * @return
     */
    @Override
    public Integer addUser(User user) {
        return userMapper.addUser(user);
    }
    
    /**
     * @return
     */
    @Override
    public List<String> selectAdmin() {
        return userMapper.selectAdmin();
    }
    
    /**
     * @return
     */
    @Override
    public List<Map<String, String>> selectAdmPas() {
        return userMapper.selectAdmPas();
    }
    
    /**
     * @return
     */
    @Override
    public List<String> selectUid() {
        return userMapper.selectUid();
    }
    
    /**
     * @param user
     * @return
     */
    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }
    
    /**
     * @param admin
     * @return
     */
    @Override
    public int updatePass(String admin,String password) {
        return userMapper.updatePass(admin,password);
    }
    
    /**
     * @param admin
     * @return
     */
    @Override
    public String selectByAdmin(String admin) {
        return userMapper.selectByAdmin(admin);
    }
    
    /**
     * @param uid
     * @return
     */
    @Override
    public int deleteUser(String uid) {
        return userMapper.deleteUser(uid);
    }
    
    /**
     * @return
     */
    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }
}

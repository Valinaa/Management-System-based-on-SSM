package cn.ecust.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author Chen Kang
 * @Date 2022/5/5
 */
@Component
@Getter
@Setter
public class User implements Serializable {
	private String uid;
	/**
	* 1为管理员，2为教师，3为学生
	 * */
	private int role;
	private String admin;
	private String password;
	
	
	public User(){}
	public User(String uid, int role, String admin, String password) {
	}
}

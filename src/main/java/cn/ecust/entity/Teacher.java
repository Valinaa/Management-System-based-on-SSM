package cn.ecust.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author Chen Kang
 * @Date 2022/5/5
 */
@Component
public class Teacher implements Serializable {
	private String tNumber;
	private String tName;
	private String tSex;
	private String tSubject;
	
	
	public String gettNumber() {
		return tNumber;
	}
	
	public void settNumber(String tNumber) {
		this.tNumber = tNumber;
	}
	public String gettName() {
		return tName;
	}
	
	public void settName(String tName) {
		this.tName = tName;
	}
	public String gettSex() {
		return tSex;
	}
	
	public void settSex(String tSex) {
		this.tSex = tSex;
	}
	public String gettSubject() {
		return tSubject;
	}
	
	public void settSubject(String tSubject) {
		this.tSubject = tSubject;
	}
	
}
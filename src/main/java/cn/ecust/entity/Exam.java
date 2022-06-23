package cn.ecust.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @author Chen Kang
 * @Date 2022/5/9
 * @Description 考试类创建
 */
@Component
public class Exam implements Serializable {
	private Integer type;//考试类型，1为全专业统考，2为专业考
	private Integer eid;//自增id
	private String eName;//考试名称
	private String eSubject;//科目
	private LocalDateTime time;//考试开始时间，精确到分
	private Integer durations;//考试持续时间，单位是分
	private String remark;//备注
	private List<Questions> questions;//试题列表
	
	public String getRemark() {
		return remark;
}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String geteSubject() {
		return eSubject;
	}
	
	public void seteSubject(String eSubject) {
		this.eSubject = eSubject;
	}
	
	public LocalDateTime getTime() {
		return time;
	}
	
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	public String geteName() {
		return eName;
	}
	
	public void seteName(String eName) {
		this.eName = eName;
	}
	
	public Integer getEid() {
		return eid;
	}
	
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getDurations() {
		return durations;
	}
	
	public void setDurations(Integer durations) {
		this.durations = durations;
	}
	
	public List<Questions> getQuestions() {
		return questions;
	}
	
	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}
	
}

package cn.ecust.entity;

import org.springframework.stereotype.Component;

/**
 * @author Chen Kang
 * @Date 2022/5/4
 */

@Component
public class Escore {
	private int sid;
	private int examId;//获取EID
	private String sNumber;//获取Number
	private int score;
	private int totalScore;
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getSid() {
		return sid;
	}
	
	public void setSid(int sid) {
		this.sid = sid;
	}
	
	public int getExamId() {
		return examId;
	}
	
	public void setExamId(int examId) {
		this.examId = examId;
	}
	
	public String getsNumber() {
		return sNumber;
	}
	
	public void setsNumber(String sNumber) {
		this.sNumber = sNumber;
	}
	
	public int getTotalScore() {
		return totalScore;
	}
	
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	
}

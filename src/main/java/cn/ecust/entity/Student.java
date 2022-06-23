package cn.ecust.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author Chen Kang
 * @Date 2022/5/5
 */
@Getter
@Setter
@Component
public class Student implements Serializable {
	private String number;
	private String name;
	private String sex;
	private int age;
	private String subject;
/*
静态工厂方法
public static Student SetStuInfo(String Number,String Name,String Sex,int Age,String Subject) {
	Student student=new Student();
	student.Number=Number;
	student.Name=Name;
	student.Sex=Sex;
	student.Age=Age;
	student.Subject=Subject;
	return student;
}
*/
}
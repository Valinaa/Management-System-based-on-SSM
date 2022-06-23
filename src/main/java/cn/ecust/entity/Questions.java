package cn.ecust.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author Chen Kang
 * @Date 2022/5/12
 * @Description 创建题库(Item Bank)
 */
@Data
@Component
public class Questions implements Serializable {
    private Integer qid;
    private String QSub;//科目
    private String QType;//题型(单选题，多选题，判断题，填空题，简答题)
    private String question;
    private String answer;
    private Integer marks;//分值
}

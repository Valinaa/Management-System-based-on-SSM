package cn.ecust.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author Chen Kang
 * @Date 2022/5/16
 * @Description
 */
@Component
@Data
public class QuesCheck {
    private Integer qid;
    private String myans;
    private Integer marks;
}

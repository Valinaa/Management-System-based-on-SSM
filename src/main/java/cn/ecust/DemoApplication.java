package cn.ecust;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Chen Kang
 * @Date 2022/5/4
 */
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("cn.ecust")
@MapperScan("cn.ecust.dao")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

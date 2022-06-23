package cn.ecust.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.FormContentFilter;

/**
 * @author Valina
 * @Date 2022/6/23
 * @Description Web构建配置
 */
@Configuration
public class WebConfiguration {
    @Bean
    public FilterRegistrationBean<FormContentFilter> testFormContentFilter() {
        FilterRegistrationBean<FormContentFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new FormContentFilter());
        /*
          过滤器名称
          过滤所有路径
          优先级，最顶级
          */
        registration.setName("FormContentFilter");
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }
}

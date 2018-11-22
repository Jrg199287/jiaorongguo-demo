package com.primeton.jiaorongguo.demo.config;

import com.primeton.jiaorongguo.demo.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器配置类
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2018/10/28 21:15
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2018/10/28 21:15
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
@Configuration
public class GlobalInterceptorConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/api/users", "/api/users/*")
//                .excludePathPatterns("/api/users/actions/login","/swagger-ui.html");
        super.addInterceptors(registry);
    }
}

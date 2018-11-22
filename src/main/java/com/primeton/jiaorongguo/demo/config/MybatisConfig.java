package com.primeton.jiaorongguo.demo.config;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 *
 *
 * @Description: 配置分页查询类
 * @Author: 作者姓名
 * @CreateDate: 2018/10/25 9:25
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2018/10/25 9:25
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
@Configuration
public class MybatisConfig {
    @Bean
    public PageHelper pagehandle() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        return pageHelper;
    }
}

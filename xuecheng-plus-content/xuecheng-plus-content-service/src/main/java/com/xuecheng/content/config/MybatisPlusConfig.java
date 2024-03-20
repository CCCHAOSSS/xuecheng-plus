package com.xuecheng.content.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author limei
 * @date 2024/3/20 13:30
 * @description MybatisPlus分页插件配置
 */


@Configuration
@MapperScan("com.xuecheng.content.mapper")
public class MybatisPlusConfig {


    /**
     * 定义分页插件
    * */
    @Bean   //分页拦截器的bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}

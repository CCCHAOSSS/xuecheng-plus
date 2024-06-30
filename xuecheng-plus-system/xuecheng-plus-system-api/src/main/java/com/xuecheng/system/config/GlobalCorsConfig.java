package com.xuecheng.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author limei
 * @date 2024/3/20 20:07
 * @description 跨域
 */

@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        /**
         * 所有的请求都会加上这些信息
        * */
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");    //允许所有来源来访问
        corsConfiguration.addAllowedHeader("*");    //放行全部原始头信息
        corsConfiguration.addAllowedMethod("*");    //允许所有方法跨域调用
        corsConfiguration.setAllowCredentials(true);    //  允许跨域发送cookie

        UrlBasedCorsConfigurationSource soure = new UrlBasedCorsConfigurationSource();
        soure.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(soure);
    }
}

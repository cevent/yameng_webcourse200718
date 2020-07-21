package com.cevent.yameng.webcourse;/**
 * Created by Cevent on 2020/7/18.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * @author cevent
 * @description 网关路由子组件装载eureka
 * @date 2020/7/18 17:40
 */
@SpringBootApplication
@EnableEurekaClient
public class GatewayApplication {
    private static final Logger LOG= LoggerFactory.getLogger(GatewayApplication.class);

    public static void main(String[] args) {
        SpringApplication springApplication=new SpringApplication(GatewayApplication.class);
        Environment environment=springApplication.run(args).getEnvironment();
        LOG.info("Gateway网关路由启动成功！");
        LOG.info("Gateway地址：\t http://127.0.0.1:{}",environment.getProperty("server.port"));
    }

    //解决跨域配置
    @Bean
    public CorsWebFilter corsWebFilter(){
        CorsConfiguration corsConfiguration=new CorsConfiguration();
        //1.开启允许证书
        corsConfiguration.setAllowCredentials(Boolean.TRUE);
        //2.允许方法访问path
        corsConfiguration.addAllowedMethod("*");
        //3.允许origin访问
        corsConfiguration.addAllowedOrigin("*");
        //4.允许header访问
        corsConfiguration.addAllowedHeader("*");
        //5.在1小时内不需要再次发送预检（options请求）
        corsConfiguration.setMaxAge(3600L);

        //6.将cors配置注入到UrlBase
        UrlBasedCorsConfigurationSource corsConfigurationSource=new UrlBasedCorsConfigurationSource(new PathPatternParser());
        corsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        //7.返回到CorsWebFilter拦截器
        return new CorsWebFilter(corsConfigurationSource);
    }
}

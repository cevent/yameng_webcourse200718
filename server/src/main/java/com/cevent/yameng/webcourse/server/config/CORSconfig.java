//package com.cevent.yameng.webcourse.server.config;/**
// * Created by Cevent on 2020/7/21.
// */
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @author cevent
// * @description 解决浏览器跨域问题
// * @date 2020/7/21 10:50
// */
//@Configuration
//public class CORSconfig implements WebMvcConfigurer {
//
//    //重写跨域方法
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedHeaders(CorsConfiguration.ALL)
//                .allowedMethods(CorsConfiguration.ALL)
//                .allowCredentials(true)
//                .maxAge(3600); //1小时之内不需要再发预检（发options请求）
//    }
//}

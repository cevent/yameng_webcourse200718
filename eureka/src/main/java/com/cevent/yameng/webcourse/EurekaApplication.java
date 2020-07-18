package com.cevent.yameng.webcourse;/**
 * Created by Cevent on 2020/7/18.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.env.Environment;

/**
 * @author cevent
 * @description Eureka注册中心应用
 * @date 2020/7/18 11:21
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {
//    public static void main(String[] args) {
//        SpringApplication.run(EurekaApplication.class, args);
//    }

    //1. 调用log日志框架:springboot默认继承slf4j框架，实现为logback和（本项目）log4j
    private static final Logger LOG= LoggerFactory.getLogger(EurekaApplication.class);

    //2. 主main
    public static void main(String[] args) {
        SpringApplication springApplication=new SpringApplication(EurekaApplication.class);
        //获取spring app环境
        Environment environment=springApplication.run(args).getEnvironment();
        LOG.info("cevent-Eureka注册中心服务启动成功...");
        //获取端口号
        LOG.info("Eureka地址：\thttp://127.0.0.1:{}",environment.getProperty("server.port"));
    }
}

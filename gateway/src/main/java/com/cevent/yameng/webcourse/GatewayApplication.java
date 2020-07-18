package com.cevent.yameng.webcourse;/**
 * Created by Cevent on 2020/7/18.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;

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
}

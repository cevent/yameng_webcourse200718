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
 * @description 将system设置为eureka子模组@EnableEurekaClient
 * @date 2020/7/18 16:05
 */
@SpringBootApplication
@EnableEurekaClient
public class SystemApplication {
    //新建日志
    private static final Logger LOG= LoggerFactory.getLogger(SystemApplication.class);

    public static void main(String[] args) {
        SpringApplication springApplication=new SpringApplication(SystemApplication.class);
        Environment environment=springApplication.run(args).getEnvironment();
        LOG.info("System模组启动成功！");
        LOG.info("System地址：\t http://127.0.0.1:{}",environment.getProperty("server.port"));
    }
}

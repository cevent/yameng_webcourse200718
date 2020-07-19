package com.cevent.yameng.webcourse;/**
 * Created by Cevent on 2020/7/18.
 */

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * @author cevent
 * @description 将system设置为eureka子模组@EnableEurekaClient
 * @MapperScan("注入mapper扫描路径")
 * @ComponentScan("spring扫描指定包下所有的类")，扫描注解类包括： @Component @Controller @RestController @Service
 * 项目基本分层：
 * 1.Controller：指定跳转路径 @RestController @RequestMapping("/test")
 * 2.mapper：指定业务接口(interface)
 * 3.Service：指定持久层业务实现(class)@Service @Resource指定调用interface接口资源
 * 4.mapper.xml：指定SQL
 * @date 2020/7/18 16:05
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.cevent.yameng.webcourse.mapper")
@ComponentScan("com.cevent.yameng")
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

package com.cevent.yameng.webcourse;/**
 * Created by Cevent on 2020/7/18.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cevent
 * @description Eureka注册中心应用
 * @date 2020/7/18 11:21
 */
@SpringBootApplication
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}

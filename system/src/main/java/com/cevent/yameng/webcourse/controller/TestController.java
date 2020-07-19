package com.cevent.yameng.webcourse.controller;/**
 * Created by Cevent on 2020/7/18.
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cevent
 * @description
 * @date 2020/7/18 16:23
 * 在类上加入@RequestMapping("/system")，则整个类下的请求都需要访问system
 */
@RestController
public class TestController {
    //system controller 测试
    //在system下加入请求路径system，之后的所有请求都必须加入system/路径
    @RequestMapping("/test")
    public String testSystem(){
        return "eureka module -system 启动成功!";
    }
}

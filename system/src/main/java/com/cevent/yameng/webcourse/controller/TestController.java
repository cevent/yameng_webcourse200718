package com.cevent.yameng.webcourse.controller;/**
 * Created by Cevent on 2020/7/18.
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cevent
 * @description
 * @date 2020/7/18 16:23
 */
@RestController
public class TestController {
    //system controller 测试
    @RequestMapping("/test")
    public String testSystem(){
        return "eureka module -system 启动成功!";
    }
}

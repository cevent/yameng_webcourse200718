package com.cevent.yameng.webcourse.business.controller;/**
 * Created by Cevent on 2020/7/23.
 */

import com.cevent.yameng.webcourse.server.dto.ResponseDto;
import com.cevent.yameng.webcourse.server.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author cevent
 * @description 定义统一异常控制，获取异常信息
 * 处于对于系统的安全考虑，不对外暴露验证规则
 * 用户端需要进行前端验证
 * @date 2020/7/23 17:48
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    //打印日志
    private static final Logger LOG= LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value=ValidationException.class)
    @ResponseBody
    public ResponseDto validatorExceptionHandler(ValidationException e){
        ResponseDto responseDto=new ResponseDto();
        responseDto.setSuccess(false);
        LOG.warn(e.getMessage());
        //responseDto.setResponseMsg(e.getMessage());
        responseDto.setResponseMsg("请求参数异常，请填写正确信息！");
        return responseDto;
    }

}

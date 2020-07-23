package com.cevent.yameng.webcourse.server.exception;/**
 * Created by Cevent on 2020/7/23.
 */

/**
 * @author cevent
 * @description 自定义异常
 * 可以继承RuntimeException或者Exception，一般业务异常用于RuntimeException
 * @date 2020/7/23 17:04
 */
public class ValidationException extends RuntimeException{
    public ValidationException(String message){
        super(message);
    }
}

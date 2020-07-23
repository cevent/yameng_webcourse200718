package com.cevent.yameng.webcourse.server.util;/**
 * Created by Cevent on 2020/7/23.
 */

import com.cevent.yameng.webcourse.server.exception.ValidationException;
import org.springframework.util.StringUtils;


/**
 * @author cevent
 * @description
 * @date 2020/7/23 16:57
 */
public class Validator {
    //1.空校验
    public static void require(String str,String fieldName){
        if(StringUtils.isEmpty(str)){
            throw new ValidationException(fieldName+"不能为空");
        }
    }

    //2.长度校验
    public static void length(String str,String fieldName,int min ,int max){
        int length=0;
        if(!StringUtils.isEmpty(str)){
            length=str.length();
        }
        if(length<=min || length>max){
            throw new ValidationException(fieldName+"长度"+min+"~"+max+"位!");
        }
    }
}

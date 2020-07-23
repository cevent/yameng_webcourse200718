package com.cevent.yameng.webcourse.server.util;/**
 * Created by Cevent on 2020/7/22.
 */

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cevent
 * @description 复制工具类
 * @date 2020/7/22 22:57
 */
public class CopyUtil {
    public static <T> List<T> copyList(List source,Class<T> clazz){
        List<T> target=new ArrayList<>();
        if(!CollectionUtils.isEmpty(source)){
            if(!CollectionUtils.isEmpty(source)){
                for (Object c:source){
                    T obj=copy(c,clazz);
                    target.add(obj);
                }
            }
        }
        return target;
    }

    //拷贝单个实体类
    public static <T> T copy(Object source,Class<T> tClass){
        if(source==null){
            return null;
        }
        T obj=null;
        try {
            obj=tClass.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        /**
         * 封装BeanUtils.copyProperties，利用反射，牺牲一些性能（可忽略不计），获取开发效率
         */
        BeanUtils.copyProperties(source,obj);
        return obj;
    }
}

package com.cevent.yameng.webcourse.server.config;/**
 * Created by Cevent on 2020/7/23.
 */

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import com.cevent.yameng.webcourse.server.util.UUIDUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

/**
 * @author cevent
 * @description 日志AOP配置
 * springboot3之后取消了AspectJ --> import org.aspectj.lang.annotation.Aspect;
 * 这里在主pom下引入了spring-boot-starter-aop：2.3.1.RELEASE
 * @date 2020/7/23 21:13
 */
@Aspect
@Component
public class LogAspect {
    private final static Logger LOG= LoggerFactory.getLogger(LogAspect.class);

    //1.定义切面:所有包下*Controller的方法
    @Pointcut("execution(public * com.cevent.yameng.webcourse.*.controller..*Controller.*(..))")
    public void controllerPointcutAspect(){

    }

    //2.切点开始之前执行
    @Before("controllerPointcutAspect()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        //2.1日志编号：日志流水token，一个日志token用来跟踪一个请求，生产环境往往同时打印多个请求，通过grep 日志token来查找每一次请求
        //key="UUID"与logback中的encoder-pattern变量名相同
        /**
         * <Pattern>
         *     %d{ss.SSS} %highlight(%-5level) %blue(%-30logger{30}:%-4line) $green(%-8X{UUID}) %msg%n
         * </Pattern>
         */
        MDC.put("UUID", UUIDUtil.getShortUUID());
        //2.2打印请求日志
        ServletRequestAttributes attrs= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attrs.getRequest();
        //2.3获取签名信息
        Signature signature=joinPoint.getSignature();
        String signatureName=signature.getName();
        //2.4打印业务操作
        String serviceOperation="";
        //contains有约定要求，方法首位必须是list or save ...
        if(signatureName.contains("list") || signatureName.contains("query") || signatureName.contains("get")){
            serviceOperation="查询";
        }
        if (signatureName.contains("save") || signatureName.contains("add") || signatureName.contains("insert") || signatureName.contains("edit")){
            serviceOperation="保存";
        }
        if(signatureName.contains("delete") || signatureName.contains("del")){
            serviceOperation="删除";
        }else{
            serviceOperation="更新其他操作";
        }

        //3.反射：获取业务名称（签名声明类型:BUSINESS_NAME）
        Class clazz=signature.getDeclaringType();
        Field field;
        String businessName="";

        try {
            field=clazz.getField("BUSINESS_NAME");
            if(!StringUtils.isEmpty(field)){
                businessName=(String) field.get(clazz);
            }
        }catch (NoSuchFieldException e){
            LOG.error("为获取到业务（BUSINESS_NAME）名称");
        }catch (SecurityException e){
            LOG.error("获取业务名称失败：",e);
        }

        //4.打印请求
        LOG.info("-------------------------BUSINESS【{}】 {} 开始 -------------------------",businessName,serviceOperation);
        LOG.info("请求地址：{} {}",request.getRequestURL().toString(),request.getMethod());
        LOG.info("类名：{} 方法：{}",signature.getDeclaringTypeName(),signatureName);
        LOG.info("远程地址：{}",request.getRemoteAddr());

        //5.打印请求参数
        Object[] args=joinPoint.getArgs();
        Object[] arguments=new Object[args.length];
        for(int i=0;i<args.length;i++){
            if(args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile){
                continue;
            }
            arguments[i]=args[i];
        }

        //6.排除字段，敏感字段(身份证/手机号等)或太长的字段(图片会转为base64长文本)不显示，定义碎片
        String[] excludeProperties={"shard"};
        PropertyPreFilters filters=new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludeFilter=filters.addFilter();
        excludeFilter.addExcludes(excludeProperties);
        //为空不打印，但是类图片等长字段会打印
        LOG.info("exclude filter：请求参数：{}", JSONObject.toJSONString(arguments,excludeFilter));
    }

    //环绕通知：打印返回信息
    @Around("controllerPointcutAspect()")
    public Object returnAroundMessage(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        //打印时间：结束-开始执行时间
        long startTime=System.currentTimeMillis();
        Object result=proceedingJoinPoint.proceed();//执行业务代码记录
        //排除字段，敏感字段或太长的字段不显示
        String[] excludeProperties={"password","shard"};
        PropertyPreFilters filters=new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludeFilter=filters.addFilter();
        excludeFilter.addExcludes(excludeProperties);
        LOG.info("@Around返回结果：{}",JSONObject.toJSONString(result,excludeFilter));
        LOG.info("----------------结束耗时：{} ms------------------",System.currentTimeMillis()-startTime);

        return result;

    }

}

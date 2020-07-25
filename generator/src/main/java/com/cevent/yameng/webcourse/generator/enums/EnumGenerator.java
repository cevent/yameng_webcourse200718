package com.cevent.yameng.webcourse.generator.enums;/**
 * Created by Cevent on 2020/7/24.
 */
import com.cevent.yameng.webcourse.server.enums.*;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cevent
 * @description
 * @date 2020/7/24 18:09
 */
public class EnumGenerator {
    static String path="admin\\public\\static\\js\\enums.js";

    public static void main(String[] args) {
        StringBuffer bufferObject=new StringBuffer();
        StringBuffer bufferArray=new StringBuffer();
        long begin=System.currentTimeMillis();

        try {
            toJson(SectionChargeEnum.class,bufferObject,bufferArray);
            toJson(YesOrNoEnum.class,bufferObject,bufferArray);
            toJson(CourseLevelEnum.class,bufferObject,bufferArray);
            toJson(CourseChargeEnum.class,bufferObject,bufferArray);
            toJson(CourseStatusEnum.class,bufferObject,bufferArray);
            toJson(FileUseEnum.class,bufferObject,bufferArray);
            toJson(SMSUseEnum.class,bufferObject,bufferArray);
            toJson(SMSStatusEnum.class,bufferObject,bufferArray);

            StringBuffer stringBuffer=bufferObject.append("\r\n").append(bufferArray);
            writeJS(stringBuffer);
        }catch (Exception e){
            e.printStackTrace();
        }

        long end=System.currentTimeMillis();
        System.out.println("执行耗时："+(end-begin)+" 毫秒！");
    }

    /**
     * 写文件
     */
    public static void writeJS(StringBuffer stringBuffer){
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream=new FileOutputStream(path);
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream,"UTF-8");
            System.out.println(path);
            outputStreamWriter.write(stringBuffer.toString());
            outputStreamWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                fileOutputStream.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static void toJson(Class clazz,StringBuffer bufferObject,StringBuffer bufferArray) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String key=toUnderLine(clazz.getSimpleName());
        toJson(clazz,key,bufferObject,bufferArray);
    }

    private static void toJson(Class clazz,String key,StringBuffer bufferObject,StringBuffer bufferArray)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //获取枚举常量数组
        Object[] objects=clazz.getEnumConstants();
        Method name=clazz.getMethod("name");
        Method getDESC=clazz.getMethod("getDesc");
        Method getCODE=clazz.getMethod("getCode");

        //生成对象
        bufferObject.append(key).append("={");
        for(int i=0;i<objects.length;i++){
            Object obj=objects[i];
            if(getCODE==null){
                bufferObject.append(name.invoke(obj))
                        .append(":{key:\"")
                        .append(name.invoke(obj))
                        .append("\",value:\"")
                        .append(getDESC.invoke(obj))
                        .append("\"}");
            }else{
                bufferObject.append(name.invoke(obj))
                        .append(":{key:\"")
                        .append(getCODE.invoke(obj))
                        .append("\",value:\"")
                        .append(getDESC.invoke(obj))
                        .append("\"}");
            }

            if(i<objects.length-1){
                bufferObject.append(",");
            }
        }

        bufferObject.append("};\r\n");

        //生成数组
        bufferArray.append(key).append("_ARRAY[");
        for (int i=0;i<objects.length;i++){
            Object obj=objects[i];
            if (getCODE==null){
                bufferArray.append("{key:\"")
                        .append(name.invoke(obj))
                        .append("\",value:\"")
                        .append(getDESC.invoke(obj))
                        .append("\"}");
            }else{
                bufferArray.append("{key:\"")
                        .append(getCODE.invoke(obj))
                        .append("\",value:\"")
                        .append(getDESC.invoke(obj))
                        .append("\"}");
            }

            if(i<objects.length-1){
                bufferArray.append(",");
            }
        }

        bufferArray.append("]:\r\n");

    }

    //功能--------------------
    /**
     * 大驼峰：转 大写_下划线，并去掉_ENUM
     * 如：SectionChargeEnum 转为SECTION_CHARGE
     */
    public static String toUnderLine(String str){
        String result=underLine(str).toString();
        return result.substring(1,result.length()).toUpperCase().replace("_ENUM","");
    }

    /**
     * 小驼峰：转 小写_下划线，第一位是_下划线
     * 如：sectionChargeEnum 转为_section_charge_enum
     */
    public static StringBuffer underLine(String str){
        Pattern pattern=Pattern.compile("[A-Z]");
        Matcher matcher=pattern.matcher(str);
        StringBuffer stringBuffer=new StringBuffer(str);
        if(matcher.find()){
            stringBuffer=new StringBuffer();
            matcher.appendReplacement(stringBuffer,"_"+matcher.group(0).toLowerCase());
            matcher.appendTail(stringBuffer);
        }else{
            return stringBuffer;
        }
        return underLine(stringBuffer.toString());
    }
}

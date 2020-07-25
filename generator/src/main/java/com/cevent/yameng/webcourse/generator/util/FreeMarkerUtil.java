package com.cevent.yameng.webcourse.generator.util;/**
 * Created by Cevent on 2020/7/24.
 */

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * @author cevent
 * @description
 * @date 2020/7/24 10:31
 */
public class FreeMarkerUtil {

    //1.定义模板路径，所有模板放在ftl
    static String ftlPath="generator\\src\\main\\java\\com\\cevent\\yameng\\webcourse\\generator\\ftl\\";

    //公共属性
    static Template template;

    //3.读取模板
    public static void initConfig(String ftlName) throws IOException {

        Configuration config=new Configuration(Configuration.VERSION_2_3_30);
        config.setDirectoryForTemplateLoading(new File(ftlPath));
        config.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_30));
        template=config.getTemplate(ftlName);
    }

    //4.生成方法
    public static void generator(String targetPathfileName, Map<String,Object> map) throws IOException, TemplateException {
        //调整参数：targetPath+"Test.java= targetPathfileName
        //FileWriter fileWriter=new FileWriter(targetPath+"Test.java");
        FileWriter fileWriter=new FileWriter(targetPathfileName);
        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
        //放入serverGenerator定义的变量
        template.process(map,bufferedWriter);
        bufferedWriter.flush();
        fileWriter.close();
    }
}

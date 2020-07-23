package com.cevent.yameng.webcourse.generator.test;/**
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

/**
 * @author cevent
 * @description 测试freemarker
 *  * .ftl是freemarker模板文件的默认后缀名，可以改为html、xml都可以
 * @date 2020/7/24 0:00
 */
public class TestUtil {
    //1.定义模板路径
    static String ftlPath="generator\\src\\main\\java\\com\\cevent\\yameng\\webcourse\\generator\\test\\";
    //2.生成文件路径
    static String targetPath="generator\\src\\main\\java\\com\\cevent\\yameng\\webcourse\\generator\\test\\";

    //3.读取ftl生成文件
    public static void main(String[] args) throws IOException, TemplateException {
        //3.1读取模板
        Configuration config=new Configuration(Configuration.VERSION_2_3_30);
        config.setDirectoryForTemplateLoading(new File(ftlPath));
        config.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_30));
        Template template=config.getTemplate("test.ftl");
        //3.2生成文件
        FileWriter fileWriter=new FileWriter(targetPath+"Test.java");
        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
        template.process(null,bufferedWriter);
        bufferedWriter.close();
        fileWriter.close();
    }
}

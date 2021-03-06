package com.cevent.yameng.webcourse.generator.vue;/**
 * Created by Cevent on 2020/7/25.
 */

import com.cevent.yameng.webcourse.generator.util.DBUtil;
import com.cevent.yameng.webcourse.generator.util.FieldUtil;
import com.cevent.yameng.webcourse.generator.util.FreeMarkerUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.*;

/**
 * @author cevent
 * @description vue自动生成代码
 * @date 2020/7/25 20:36
 */
public class VueGenerator {

    static String MODULE="business";
    static String targetVuePath="admin\\src\\views\\admin\\";
    //直接读取server-generator-config文件
    static String generatorConfig="server\\src\\main\\resources\\generator\\generatorConfig.xml";

    public static void main(String[] args) throws Exception {
        //提取模组名称
        String module=MODULE;
        //1.只生成配置文件中第一个table节点：tableName="section" domainObjectName="Section"
        File file=new File(generatorConfig);
        SAXReader reader=new SAXReader();
        //2.读取xml到document文件中
        Document doc=reader.read(file);
        //3.读取xml文件的根节点
        Element rootElement=doc.getRootElement();
        //4.读取context节点
        Element contextElement=rootElement.element("context");
        //5.定义一个element用于遍历
        Element tableElement;
        //6.获取第一个table节点
        tableElement=contextElement.elementIterator("table").next();
        String Domain=tableElement.attributeValue("domainObjectName");
        String tableName=tableElement.attributeValue("tableName");
        String tableNameCN=DBUtil.getTableComment(tableName);
        String domain=Domain.substring(0,1).toLowerCase()+Domain.substring(1);
        System.out.println("表名："+tableElement.attributeValue("tableName"));
        System.out.println("Domain: "+tableElement.attributeValue("domainObjectName"));

        //获取所有列信息,调取xml后弃用
        //List<FieldUtil> fieldUtilList= DBUtil.getColumnByTableName(domain);
        List<FieldUtil> fieldUtilList= DBUtil.getColumnByTableName(tableName);
        //根据类信息，获取所有的set类型集合
        Set<String> typeSet=getJavaTypes(fieldUtilList);
        Map<String,Object> map=new HashMap<>();
        map.put("Domain",Domain);
        map.put("domain",domain);
        map.put("tableNameCN",tableNameCN);
        map.put("module",module);
        map.put("fieldUtilList",fieldUtilList);
        map.put("typeSet",typeSet);

        //1.vue初始化
        FreeMarkerUtil.initConfig("vue.ftl");
        //1.生成，将map传递到模板
        FreeMarkerUtil.generator(targetVuePath+domain+".vue",map);

    }

    /*获取所有的java类型，set去重*/
    private static Set<String> getJavaTypes(List<FieldUtil> fieldUtilList){
        Set<String> set=new HashSet<>();
        for (int i=0;i<fieldUtilList.size();i++){
            FieldUtil fieldUtil=fieldUtilList.get(i);
            set.add(fieldUtil.getJavaType());
        }
        return set;
    }
}

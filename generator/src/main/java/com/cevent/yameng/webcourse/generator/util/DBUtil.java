package com.cevent.yameng.webcourse.generator.util;/**
 * Created by Cevent on 2020/7/24.
 */

import com.cevent.yameng.webcourse.generator.enums.EnumGenerator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cevent
 * @description 数据库连接
 * @date 2020/7/24 15:03
 */
public class DBUtil {

    //1.获取数据库连接
    public static Connection getConnection(){
        Connection connection=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://127.0.0.1:3306/cevent_yameng_webcourse";
            String user="cevent_ym";
            String password="cevent";
            connection= DriverManager.getConnection(url,user,password);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return connection;
    }

    //2.获取表注释
    public static String getTableComment(String tableName) throws SQLException {
        Connection connection=getConnection();
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery(
                //查询注释，来自表的结构
                "SELECT table_comment from information_schema.TABLES where TABLE_NAME='"+tableName+"'");
        String tableNameCH="";
        if(resultSet!=null){
            while (resultSet.next()){
                tableNameCH=resultSet.getString("table_comment");
                break;
            }
        }
        resultSet.close();
        statement.close();
        connection.close();
        System.out.println("表名："+tableNameCH);
        return tableNameCH;
    }

    //下划线转小驼峰
    public static String lineToSmallHump(String str){
        Pattern linePattern=Pattern.compile("_(\\w)");
        str =str.toLowerCase();
        Matcher matcher=linePattern.matcher(str);
        StringBuffer stringBuffer=new StringBuffer();
        while (matcher.find()){
            matcher.appendReplacement(stringBuffer,matcher.group(1).toUpperCase());
        }
        //加入结尾
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }
    //下划线转大驼峰
    public static String lineToBigHump(String str){
        String s=lineToSmallHump(str);
        return s.substring(0,1).toUpperCase()+s.substring(1);
    }
    //数据库类型转为java类型
    public static String sqlTypeToJavaType(String sqlType){
        if(sqlType.toUpperCase().contains("varchar".toUpperCase())
            || sqlType.toUpperCase().contains("char".toUpperCase())
                || sqlType.toUpperCase().contains("text".toUpperCase())
        ){
            return "String";
        } else if(sqlType.toUpperCase().contains("datetime".toUpperCase())){
            return "Date";
        } else if(sqlType.toUpperCase().contains("int".toUpperCase())){
            return "Integer";
        } else if(sqlType.toUpperCase().contains("long".toUpperCase())){
            return "Long";
        } else if(sqlType.toUpperCase().contains("decimal".toUpperCase())){
            return "BigDecimal";
        }else{
            return "String";
        }
    }

    //3.获取所有列信息
    public static List<FieldUtil> getColumnByTableName(String tableName) throws SQLException {
        List<FieldUtil> fieldUtilList=new ArrayList<>();
        Connection connection=getConnection();
        Statement statement=connection.createStatement();
        //执行sql，获取所有列
        ResultSet resultSet=statement.executeQuery("SHOW FULL COLUMNS FROM "+tableName+"");
        if(resultSet!=null){
            while (resultSet.next()){
                String columnName=resultSet.getString("Field");
                String type=resultSet.getString("Type");
                String comment=resultSet.getString("Comment");
                String nullAble=resultSet.getString("Null"); //yes / no

                FieldUtil fieldUtil=new FieldUtil();
                fieldUtil.setName(columnName);
                fieldUtil.setNameSmallHump(lineToSmallHump(columnName));
                fieldUtil.setNameBigHump(lineToBigHump(columnName));
                fieldUtil.setType(type);
                fieldUtil.setJavaType(DBUtil.sqlTypeToJavaType(resultSet.getString("Type")));
                fieldUtil.setComment(comment);

                if(comment.contains("|")){
                    fieldUtil.setNameCN(comment.substring(0,comment.indexOf("|")));
                }else{
                    fieldUtil.setNameCN(comment);
                }
                //为空校验，如果获取到的Null为yes，那么NullAble=true
                /**
                 * char类型：一般为固定长度的字段，常见的id（无需校验）和枚举字段（有下拉框，不是手输无需校验），
                 */
                fieldUtil.setNullAble("YES".equals(nullAble));
                if(type.toUpperCase().contains("varchar".toUpperCase())){
                    //如果length>0，需要对length校验，
                    String lengthStr=type.substring(type.indexOf("(")+1,type.length()-1);
                    fieldUtil.setLength(Integer.valueOf(lengthStr));
                }else{
                    //length=0则不校验
                    fieldUtil.setLength(0);
                }
                //判断注释中有枚举，则提取EnumClass名
                if(comment.contains("枚举")){
                    fieldUtil.setEnums(true);
                    //以课程等级为例，从注释中的“枚举：[CourseLevelEnum]”,得到COURSE_LEVEL
                    int start=comment.indexOf("[");
                    int end=comment.indexOf("]");
                    String enumsName=comment.substring(start+1,end);
                    String enumsConst= EnumGenerator.toUnderLine(enumsName);
                    fieldUtil.setEnumsConst(enumsConst);
                }else{
                    fieldUtil.setEnums(false);
                }
                fieldUtilList.add(fieldUtil);
            }
        }
        resultSet.close();
        statement.close();
        connection.close();
        System.out.println("列信息："+fieldUtilList);
        return fieldUtilList;
    }

}

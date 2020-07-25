package com.cevent.yameng.webcourse.generator.util;/**
 * Created by Cevent on 2020/7/24.
 */

/**
 * @author cevent
 * @description
 * @date 2020/7/24 14:27
 */
public class FieldUtil {
    private String name; //字段名
    private String nameSmallHump; //小驼峰courseId
    private String nameBigHump; //大驼峰CourseId
    private String nameCN; //中文名
    private String type; //字段类型:char(1)/varchar(1)
    private String javaType; //java类型
    private String comment; //注释 中文名nameCN | ID
    private Boolean nullAble; //是否为空：校验
    private Integer length; //字符串长度：校验
    private Boolean Enums; //是否为枚举：校验
    private String enumsConst; //是否为枚举常量COURSE_LEVEL

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameSmallHump() {
        return nameSmallHump;
    }

    public void setNameSmallHump(String nameSmallHump) {
        this.nameSmallHump = nameSmallHump;
    }

    public String getNameBigHump() {
        return nameBigHump;
    }

    public void setNameBigHump(String nameBigHump) {
        this.nameBigHump = nameBigHump;
    }

    public String getNameCN() {
        return nameCN;
    }

    public void setNameCN(String nameCN) {
        this.nameCN = nameCN;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getNullAble() {
        return nullAble;
    }

    public void setNullAble(Boolean nullAble) {
        this.nullAble = nullAble;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Boolean getEnums() {
        return Enums;
    }

    public void setEnums(Boolean enums) {
        Enums = enums;
    }

    public String getEnumsConst() {
        return enumsConst;
    }

    public void setEnumsConst(String enumsConst) {
        this.enumsConst = enumsConst;
    }

    @Override
    public String toString() {
        return "FieldUtil{" +
                "name='" + name + '\'' +
                ", nameSmallHump='" + nameSmallHump + '\'' +
                ", nameBigHump='" + nameBigHump + '\'' +
                ", nameCN='" + nameCN + '\'' +
                ", type='" + type + '\'' +
                ", javaType='" + javaType + '\'' +
                ", comment='" + comment + '\'' +
                ", nullAble=" + nullAble +
                ", length=" + length +
                ", Enums=" + Enums +
                ", enumsConst='" + enumsConst + '\'' +
                '}';
    }
}

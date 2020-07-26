package com.cevent.yameng.webcourse.server.dto;
/*
* typeSet:整理出所有用到的java类型，生成import语句，一种类型只需要引入一次，所以去重
*/
<#list typeSet as type>
    <#if type=="Date">
        import java.util.Date;
        import com.fasterxml.jackson.annotation.JsonFormat;
    </#if>
    <#if type=="BigDecimal">
        import java.math.BigDecimal;
    </#if>
</#list>

public class ${Domain}Dto{
    /**
    * 循环list
    */
    <#list fieldUtilList as field>
        /**
        * ${field.comment}
        */
        <#if field.javaType="Date">
            @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        </#if>
        private ${field.javaType} ${field.nameSmallHump};
    </#list>

    /**
    * 循环list:set/get方法
    */
    <#list fieldUtilList as field>
        public ${field.javaType} get${field.nameBigHump}(){
            return ${field.nameSmallHump};
        }

        public void set${field.nameBigHump}(${field.javaType} ${field.nameSmallHump}){
            this.${field.nameSmallHump}=${field.nameSmallHump};
        }
    </#list>

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(" [");
        stringBuilder.append("Hash = ").append(hashCode());

        <#list fieldUtilList as field>
            stringBuilder.append(", ${field.nameSmallHump}=").append(${field.nameSmallHump});
        </#list>

        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
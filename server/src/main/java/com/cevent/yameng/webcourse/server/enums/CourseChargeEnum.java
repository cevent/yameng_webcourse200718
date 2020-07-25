package com.cevent.yameng.webcourse.server.enums;/**
 * Created by Cevent on 2020/7/24.
 */

/**
 * @author cevent
 * @description 枚举收费神奇类
 * @date 2020/7/24 20:28
 */
public enum CourseChargeEnum {

    CHARGE("C","收费"),
    FREE("F","免费");

    private String code;
    private String desc;

    CourseChargeEnum(String code,String desc){
        this.code=code;
        this.desc=desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

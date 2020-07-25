package com.cevent.yameng.webcourse.server.enums;

/**
 * Created by Cevent on 2020/7/24.
 */
public enum SectionChargeEnum {

    CHARGE("C","收费"),
    FREE("F","免费");

    private String code;
    private String desc;

    SectionChargeEnum(String code,String desc){
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

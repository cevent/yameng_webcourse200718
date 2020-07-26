package com.cevent.yameng.webcourse.server.enums;/**
 * Created by Cevent on 2020/7/24.
 */

/**
 * @author cevent
 * @description 枚举收费类
 * 会员状态：正常、注销、冻结等。这些状态不能在界面选择，会员注册时默认
 * code：程序使用
 * desc：用户接收
 * C/F:前后端数据传输，数据库存储
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

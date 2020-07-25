package com.cevent.yameng.webcourse.server.enums;

/**
 * Created by Cevent on 2020/7/24.
 */
public enum FileUseEnum {

    COURSE("C","课程"),
    TEACHER("T","讲师");

    private String code;
    private String desc;

    FileUseEnum(String code,String desc){
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

    public static FileUseEnum getByCode(String code){
        for (FileUseEnum f:FileUseEnum.values()){
            if(code.equals(f.getCode())){
                return f;
            }
        }
        return null;
    }
}

package com.cevent.yameng.webcourse.server.dto;/**
 * Created by Cevent on 2020/7/22.
 */

/**
 * @author cevent
 * @description
 * @date 2020/7/22 23:10
 */
public class ResponseDto<T> {
    //1.业务执行的成功/失败
    private boolean success=true;
    //2.返回码
    private String responseCode;
    //3.返回信息
    private String responseMsg;
    //4.返回泛型数据，自定义类型
    private T responseData;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "success=" + success +
                ", responseCode='" + responseCode + '\'' +
                ", responseMsg='" + responseMsg + '\'' +
                ", responseData=" + responseData +
                '}';
    }
}

package com.yeta.userconsumerribbon.util;

/**
 * 统一返回实体
 * @author YETA
 * @date 2018/11/27/18:31
 */
public class CommonResponse {

    public static final Integer CODE1 = 1000;
    public static final Integer CODE2 = 1001;
    public static final Integer CODE3 = 1002;
    //...

    public static final String MESSAGE1 = "请求成功";
    public static final String MESSAGE2 = "参数不匹配";
    public static final String MESSAGE3 = "出现异常";

    private Integer code;

    private Object data;

    private String message;

    public CommonResponse() {
    }

    public CommonResponse(Integer code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}

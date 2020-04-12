package com.zcj.mybatisplus.enums;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS(true,20000,"成功"),
    UNKNOWN_ERROR(false,20001,"未知错误"),
    PARAM_ERROR(false,20002,"参数错误");

    private boolean success;
    private Integer code;
    private String message;

    ResultCode(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}

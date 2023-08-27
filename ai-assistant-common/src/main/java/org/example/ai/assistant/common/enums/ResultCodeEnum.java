package org.example.ai.assistant.common.enums;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    OK(200, "OK"),
    BIZ_ERROR(400, "biz error"),
    ;

    private Integer code;

    private String msg;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

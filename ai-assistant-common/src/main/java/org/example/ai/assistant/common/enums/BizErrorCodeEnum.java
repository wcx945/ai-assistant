package org.example.ai.assistant.common.enums;

import lombok.Getter;

@Getter
public enum BizErrorCodeEnum {

    VERIFY_CODE_EXPIRE(4000101, "验证码过期"),
    VERIFY_CODE_ERROR(4000102, "验证码错误"),
    VERIFY_PHONE_ERROR(4000103, "验证手机格式错误"),
    VERIFY_CODE_SEND_REPEAT(4000104, "请勿频繁发送验证码"),
    VERIFY_CODE_USE(4000105, "验证码已使用"),

    LOGIN_FAIL_COUNT_MAX(4000201, "登录失败超过5次，请稍后5分钟后重试"),
    LOGIN_TOKEN_ERROR(4000202, "token失效请重新登录"),
    LOGIN_NOT_EXISTS_ERROR(4000203, "用户不存在请重新登录"),
    ;

    private final Integer code;

    private final String msg;

    BizErrorCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

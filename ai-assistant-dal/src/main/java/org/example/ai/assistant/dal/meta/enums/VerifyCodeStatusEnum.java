package org.example.ai.assistant.dal.meta.enums;

import lombok.Getter;

@Getter
public enum VerifyCodeStatusEnum {

    WAIT(0, "等待验证"),
    USE(1, "已使用"),
    EXPIRE(2, "验证码过期"),
    ;
    private final Integer value;

    private final String desc;

    VerifyCodeStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc= desc;
    }
}

package org.example.ai.assistant.dal.meta.enums;

import lombok.Getter;

@Getter
public enum VerifyCodeTypeEnum {

    SMS(1, "短信"),
    ;
    private final Integer value;

    private final String desc;

    VerifyCodeTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc= desc;
    }
}

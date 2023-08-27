package org.example.ai.assistant.biz.meta.param;

import lombok.Data;

@Data
public class LoginVerifyParam {

    /**
     * 手机号
     */
    private String phone;
    /**
     * 验证码
     */
    private String code;
}

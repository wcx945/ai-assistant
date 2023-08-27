package org.example.ai.assistant.biz.service;

import org.example.ai.assistant.biz.meta.param.LoginVerifyParam;

public interface LoginService {

    /**
     * 验证登录
     * @param param
     */
    String loginVerify(LoginVerifyParam param);

    /**
     * 发送验证码
     * @param phone
     * @return
     */
    void sendCode(String phone);

}

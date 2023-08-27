package org.example.org.example.ai.assistant.web.controller;

import org.example.ai.assistant.biz.meta.param.LoginVerifyParam;
import org.example.ai.assistant.biz.meta.param.SendCodeParam;
import org.example.ai.assistant.biz.service.LoginService;
import org.example.ai.assistant.common.constants.HeaderConstants;
import org.example.org.example.ai.assistant.web.meta.bean.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 用户登录
     * @param param
     * @param response
     * @return
     */
    @PostMapping("/verify")
    public Result<Void> loginVerify(@RequestBody LoginVerifyParam param, HttpServletResponse response) {
        String token = loginService.loginVerify(param);
        response.setHeader(HeaderConstants.AUTH_TOKEN, token);
        return Result.success();
    }

    /**
     * 发送短信验证码
     * @param param
     * @return
     */
    @PostMapping("/code/send")
    public Result<Void> codeSend(@RequestBody SendCodeParam param) {
        loginService.sendCode(param.getPhone());
        return Result.success();
    }

    @GetMapping("/getData")
    public Result<Void> getData() {
        return Result.success();
    }
}

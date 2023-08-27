package org.example.org.example.ai.assistant.web.controller;

import com.alibaba.fastjson.JSON;
import org.example.ai.assistant.common.bean.RequestContext;
import org.example.ai.assistant.common.bean.UserBO;
import org.example.org.example.ai.assistant.web.meta.bean.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/getData")
    public Result<Void> getData() {
        UserBO user = RequestContext.getUser();
        System.out.println(JSON.toJSONString(user));
        return Result.success();
    }
}

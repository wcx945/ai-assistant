package org.example;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.ai.assistant.dal.meta.po.UserPO;
import org.example.ai.assistant.dal.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/data")
public class DataController {

    @Resource
    private UserService userService;

    @GetMapping("/get")
    public String getData(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        Page<UserPO> page = userService.page(new Page<>(pageNo, pageSize), new QueryWrapper<>());
        System.out.println(JSON.toJSONString(page));
        UserPO userPO = userService.getById(1);
        return JSON.toJSONString(userPO);
    }
}

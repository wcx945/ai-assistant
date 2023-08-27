package org.example.ai.assistant.integration.service.impl;

import org.example.ai.assistant.integration.service.SmsService;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {

    @Override
    public void send(String phone, String code) {

    }

    /**
     * 生成短信消息
     * @param code
     * @return
     */
    private String generateMsg(String code) {

        return code;
    }
}

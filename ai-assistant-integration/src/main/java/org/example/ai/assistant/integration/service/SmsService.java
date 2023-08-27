package org.example.ai.assistant.integration.service;

public interface SmsService {
    /**
     * 发送短信
     * @param phone
     * @param code
     */
    void send(String phone, String code);

}

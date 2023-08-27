package org.example.org.example.ai.assistant.web.config;

import org.example.ai.assistant.common.exception.BizException;
import org.example.org.example.ai.assistant.web.meta.bean.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BizException.class)
    public <T> Result<T> BizExceptionHandler(HttpServletRequest request, HttpServletResponse response, BizException e) {
        return Result.fail(e.getCodeEnum().getCode(), e.getCodeEnum().getMsg());
    }
}

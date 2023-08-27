package org.example.org.example.ai.assistant.web.meta.bean;

import lombok.Data;
import org.example.ai.assistant.common.enums.ResultCodeEnum;

@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    Result() {}

    Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCodeEnum.OK.getCode(), ResultCodeEnum.OK.getMsg(), data);
    }

    public static <T> Result<T> success() {
        return new Result<>(ResultCodeEnum.OK.getCode(), ResultCodeEnum.OK.getMsg(), null);
    }

    public static <T> Result<T> fail(Integer code, String msg) {
        return new Result<>(code, msg, null);
    }
}

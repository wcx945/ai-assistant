package org.example.ai.assistant.common.exception;

import lombok.Data;
import org.example.ai.assistant.common.enums.BizErrorCodeEnum;

@Data
public class BizException extends RuntimeException {

    private final BizErrorCodeEnum codeEnum;

    public BizException(BizErrorCodeEnum codeEnum) {
        this.codeEnum = codeEnum;
    }

}

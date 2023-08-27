package org.example.ai.assistant.dal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.ai.assistant.dal.meta.po.VerifyCodePO;

/**
 * <p>
 * 验证码表 服务类
 * </p>
 *
 * @author wuchaoxin
 * @since 2023-08-27
 */
public interface VerifyCodeService extends IService<VerifyCodePO> {

    VerifyCodePO getLastRecord(Long userId);

}

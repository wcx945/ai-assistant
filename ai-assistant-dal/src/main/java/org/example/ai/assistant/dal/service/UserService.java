package org.example.ai.assistant.dal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.ai.assistant.dal.meta.po.UserPO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuchaoxin
 * @since 2023-08-27
 */
public interface UserService extends IService<UserPO> {

    UserPO getByPhone(String phone);

}

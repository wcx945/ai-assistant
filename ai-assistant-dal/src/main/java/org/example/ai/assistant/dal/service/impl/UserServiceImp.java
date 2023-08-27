package org.example.ai.assistant.dal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.ai.assistant.dal.mapper.UserMapper;
import org.example.ai.assistant.dal.meta.po.UserPO;
import org.example.ai.assistant.dal.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuchaoxin
 * @since 2023-08-27
 */
@Service
public class UserServiceImp extends ServiceImpl<UserMapper, UserPO> implements UserService {

    public UserPO getByPhone(String phone) {
        UserPO userParam = new UserPO();
        userParam.setPhone(phone);
        return this.getOne(new QueryWrapper<>(userParam));
    }
}

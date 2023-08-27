package org.example.ai.assistant.dal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.ai.assistant.dal.mapper.VerifyCodeMapper;
import org.example.ai.assistant.dal.meta.po.VerifyCodePO;
import org.example.ai.assistant.dal.service.VerifyCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Objects;

/**
 * <p>
 * 验证码表 服务实现类
 * </p>
 *
 * @author wuchaoxin
 * @since 2023-08-27
 */
@Service
public class VerifyCodeServiceImp extends ServiceImpl<VerifyCodeMapper, VerifyCodePO> implements VerifyCodeService {

    public VerifyCodePO getLastRecord(Long userId) {
        QueryWrapper<VerifyCodePO> query = new QueryWrapper<>();
        query.eq("user_id", userId);
        query.orderByDesc("create_time");
        Page<VerifyCodePO> page = this.page(new Page<>(1,1), query);
        if (Objects.nonNull(page) && !CollectionUtils.isEmpty(page.getRecords())) {
            return page.getRecords().get(0);
        }
        return null;
    }
}

package org.example.ai.assistant.biz.service.impl;

import org.example.ai.assistant.biz.meta.param.LoginVerifyParam;
import org.example.ai.assistant.biz.service.LoginService;
import org.example.ai.assistant.common.enums.BizErrorCodeEnum;
import org.example.ai.assistant.common.exception.BizException;
import org.example.ai.assistant.common.utils.JJwtUtils;
import org.example.ai.assistant.common.utils.PhoneUtils;
import org.example.ai.assistant.dal.meta.enums.VerifyCodeStatusEnum;
import org.example.ai.assistant.dal.meta.enums.VerifyCodeTypeEnum;
import org.example.ai.assistant.dal.meta.po.UserPO;
import org.example.ai.assistant.dal.meta.po.VerifyCodePO;
import org.example.ai.assistant.dal.service.UserService;
import org.example.ai.assistant.dal.service.VerifyCodeService;
import org.example.ai.assistant.integration.service.SmsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Random;

@Service
public class LoginServiceImpl implements LoginService {

    private final Random random = new Random();
    /**
     * 登录失败次数
     */
    private final static int LOGIN_FAIL_COUNT_MAX = 5;

    @Resource
    private UserService userService;
    @Resource
    private VerifyCodeService verifyCodeService;
    @Resource
    private SmsService smsService;

    /**
     * 登录验证
     * @param param
     * @return
     */
    @Override
    public String loginVerify(LoginVerifyParam param) {
        // 检查手机号格式
        if (!PhoneUtils.checkPhone(param.getPhone())) {
            throw new BizException(BizErrorCodeEnum.VERIFY_PHONE_ERROR);
        }
        // 验证用户验证码是否正确
        UserPO userPO = userService.getByPhone(param.getPhone());
        if (Objects.isNull(userPO)) {
            createUser(param.getPhone());
            userPO = userService.getByPhone(param.getPhone());
        }
        // 超过最大失败次数，禁止登录
        if (userPO.getLoginFailCount() >= LOGIN_FAIL_COUNT_MAX
                && userPO.getLockTime() != 0
                && System.currentTimeMillis() < userPO.getLockTime()) {
            throw new BizException(BizErrorCodeEnum.LOGIN_FAIL_COUNT_MAX);
        }
        // 获取验证信息
        VerifyCodePO verifyCodePO = verifyCodeService.getLastRecord(userPO.getId());
        if (Objects.isNull(verifyCodePO)) {
            throw new BizException(BizErrorCodeEnum.VERIFY_CODE_EXPIRE);
        }
        if (VerifyCodeStatusEnum.EXPIRE.getValue().equals(verifyCodePO.getStatus())) {
            throw new BizException(BizErrorCodeEnum.VERIFY_CODE_EXPIRE);
        } else if (VerifyCodeStatusEnum.USE.getValue().equals(verifyCodePO.getStatus())) {
            throw new BizException(BizErrorCodeEnum.VERIFY_CODE_USE);
        }
        if (System.currentTimeMillis() > verifyCodePO.getExpireTime()) {
            throw new BizException(BizErrorCodeEnum.VERIFY_CODE_EXPIRE);
        }
        // 校验验证码是否正确
        if (!verifyCodePO.getCode().equals(param.getCode())) {
            if (userPO.getLoginFailCount() >= LOGIN_FAIL_COUNT_MAX) {
                userPO.setLockTime(System.currentTimeMillis() + 50 * 1000);
            }
            // 更新失败次数
            userPO.setLoginFailCount(userPO.getLoginFailCount() + 1);
            userService.updateById(userPO);
            throw new BizException(BizErrorCodeEnum.VERIFY_CODE_ERROR);
        }
        // 更新验证码状态
        verifyCodePO.setStatus(VerifyCodeStatusEnum.USE.getValue());
        verifyCodePO.setUpdateTime(System.currentTimeMillis());
        verifyCodeService.updateById(verifyCodePO);
        // 登录成功重置失败次数
        userPO.setLoginFailCount(0);
        userPO.setLockTime(0L);
        userService.updateById(userPO);
        // 生成token
        return JJwtUtils.createToken(String.valueOf(userPO.getId()));
    }

    @Override
    public void sendCode(String phone) {
        // 检查手机号格式
        if (!PhoneUtils.checkPhone(phone)) {
            throw new BizException(BizErrorCodeEnum.VERIFY_PHONE_ERROR);
        }
        UserPO userPO = userService.getByPhone(phone);
        if (Objects.isNull(userPO)) {
            createUser(phone);
            userPO = userService.getByPhone(phone);
        }
        VerifyCodePO verifyCodePO = verifyCodeService.getLastRecord(userPO.getId());
        if (Objects.nonNull(verifyCodePO)
                && VerifyCodeStatusEnum.WAIT.getValue().equals(verifyCodePO.getStatus())
                && System.currentTimeMillis() < verifyCodePO.getExpireTime()) {
            throw new BizException(BizErrorCodeEnum.VERIFY_CODE_SEND_REPEAT);
        }
        if (Objects.nonNull(verifyCodePO)
                && VerifyCodeStatusEnum.WAIT.getValue().equals(verifyCodePO.getStatus())
                && System.currentTimeMillis() > verifyCodePO.getExpireTime()) {
            // 验证码状态更新为已过期
            verifyCodePO.setStatus(VerifyCodeStatusEnum.EXPIRE.getValue());
            verifyCodePO.setUpdateTime(System.currentTimeMillis());
            verifyCodeService.updateById(verifyCodePO);
        }
        long expireTime = System.currentTimeMillis() + 60 * 1000;
        String code = generateCode();
        // 发送短信
        smsService.send(userPO.getPhone(), code);

        // 一分钟后过期
        VerifyCodePO newVerifyCodePO = new VerifyCodePO();
        newVerifyCodePO.setUserId(userPO.getId());
        newVerifyCodePO.setCode(code);
        newVerifyCodePO.setType(VerifyCodeTypeEnum.SMS.getValue());
        newVerifyCodePO.setStatus(VerifyCodeStatusEnum.WAIT.getValue());
        newVerifyCodePO.setExpireTime(expireTime);
        newVerifyCodePO.setCreateTime(System.currentTimeMillis());
        newVerifyCodePO.setUpdateTime(System.currentTimeMillis());
        verifyCodeService.save(newVerifyCodePO);
    }

    /**
     * 随机生成6位数字
     * @return
     */
    private String generateCode() {
        return String.valueOf(random.nextInt(1000000));
    }

    /**
     * 创建用户信息
     * @param phone
     */
    private void createUser(String phone) {
        UserPO userCreate = new UserPO();
        userCreate.setPhone(phone);
        userCreate.setNickName("游客");
        userCreate.setCreateTime(System.currentTimeMillis());
        userService.save(userCreate);
    }

}

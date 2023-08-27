package org.example.org.example.ai.assistant.web.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.example.ai.assistant.common.bean.RequestContext;
import org.example.ai.assistant.common.bean.UserBO;
import org.example.ai.assistant.common.constants.HeaderConstants;
import org.example.ai.assistant.common.enums.BizErrorCodeEnum;
import org.example.ai.assistant.common.exception.BizException;
import org.example.ai.assistant.common.utils.JJwtUtils;
import org.example.ai.assistant.dal.meta.po.UserPO;
import org.example.ai.assistant.dal.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
public class ContextInterceptor implements HandlerInterceptor {

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authToken = request.getHeader(HeaderConstants.AUTH_TOKEN);
        if (StringUtils.isBlank(authToken)) {
            throw new BizException(BizErrorCodeEnum.LOGIN_TOKEN_ERROR);
        }
        if (!JJwtUtils.verifyToken(authToken)) {
            throw new BizException(BizErrorCodeEnum.LOGIN_TOKEN_ERROR);
        }
        String userId = JJwtUtils.getUserIdByToken(authToken);
        UserPO userPO = userService.getById(Long.valueOf(userId));
        if (Objects.isNull(userPO)) {
            throw new BizException(BizErrorCodeEnum.LOGIN_NOT_EXISTS_ERROR);
        }
        UserBO userBO = getUserBO(userPO);
        RequestContext.setUser(userBO);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("\n-------- request context clear --- ");
        RequestContext.clear();
    }

    private UserBO getUserBO(UserPO userPO) {
        UserBO userBO = new UserBO();
        userBO.setUserId(userPO.getId());
        userBO.setPhone(userPO.getPhone());
        return userBO;
    }
}

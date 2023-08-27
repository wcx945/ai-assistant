package org.example.ai.assistant.common.bean;

public class RequestContext {

    private static final ThreadLocal<UserBO> userBOThreadLocal = new ThreadLocal<>();

    public static void setUser(UserBO user) {
        userBOThreadLocal.set(user);
    }

    public static UserBO getUser() {
        return userBOThreadLocal.get();
    }

    public static void clear() {
        userBOThreadLocal.remove();
    }

}

package org.example.ai.assistant.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class PhoneUtils {

    final static Pattern PHONE_FORMAT_PATTERN = Pattern.compile("^1[0-9]{10}$");

    /**
     * 检查手机号格式
     * @param phone
     * @return
     */
    public static boolean checkPhone(String phone) {
        if (StringUtils.isBlank(phone)) {
            return false;
        }
        return PHONE_FORMAT_PATTERN.matcher(phone).matches();
    }

    public static void main(String[] args) {
        String phone = null;
        System.out.println(checkPhone(phone));
    }
}

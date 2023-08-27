package org.example.ai.assistant.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JJwtUtils {

    private final static Long EXPIRE_TIME = 24L * 60 * 60 * 1000;
    private final static String SECRET = "7d976326f0d54fb683dafe1d5069f160";

    public static String createToken(String userId){
        Date nowDate = new Date();
        System.out.println(nowDate);
        Date expireDate = new Date(nowDate.getTime() + EXPIRE_TIME);

        // 可添加自定义私有的payload的key-value。若与已有的重名会覆盖
        // Map<String, String> claims = new HashMap();
        // claims.put("key1", "value1");

        return Jwts.builder()
                .setAudience(userId)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                // .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, generalKey())
                .compact();
    }

    public static boolean verifyToken(String token) {
        try {
            Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            // e.printStackTrace();
            return false;
        }
    }

    // 从token中获取用户名
    public static String getUserNameByToken(String token) {
        return getTokenClaim(token).getSubject();
    }

    // 从token中获取用户id
    public static String getUserIdByToken(String token) {
        return getTokenClaim(token).getAudience();
    }

    // 获取token中注册信息
    private static Claims getTokenClaim (String token) {
        try {
            return Jwts.parser().setSigningKey(generalKey())
                    .parseClaimsJws(token).getBody();
        }catch (Exception e){
            // e.printStackTrace();
            return null;
        }
    }

    // 将secret加密
    private static SecretKey generalKey(){
        String stringKey = SECRET;
        byte[] encodedKey = Base64.encodeBase64(stringKey.getBytes());
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    public static void main(String[] args) {
        String userId = "1";
        String token = JJwtUtils.createToken(userId);
        System.out.println("token:" + token);
        boolean verifyToken = JJwtUtils.verifyToken(token);
        System.out.println("verifyToken:" + verifyToken);
        System.out.println("userId:" +JJwtUtils.getUserIdByToken(token));
    }
}

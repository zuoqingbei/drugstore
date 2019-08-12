package com.hlsofttech.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hlsofttech.constant.JWTConstant;
import com.hlsofttech.exception.CustomException;
import com.hlsofttech.utils.Base64ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

/**
 * 功能描述: JwtUtil
 *
 * @author suntf
 * @date 2019/8/12
 */
@Slf4j
@Component
public class JwtUtil {

    /**
     * 校验token是否正确
     *
     * @param token Token
     * @return boolean 是否正确
     * @author suntf
     * @date 2018/8/31 9:05
     */
    public static boolean verify(String token) {
        try {
            // 帐号加JWT私钥解密
            String secret = getClaim(token, "account") + Base64ConvertUtil.decode(JWTConstant.JWT_SECERT);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (UnsupportedEncodingException e) {
            log.error("JWTToken认证解密出现UnsupportedEncodingException异常:" + e.getMessage());
            throw new CustomException("JWTToken认证解密出现UnsupportedEncodingException异常:" + e.getMessage());
        }
    }

    /**
     * 获得Token中的信息无需secret解密也能获得
     *
     * @param token 1
     * @param claim 2
     * @return java.lang.String
     * @author suntf
     * @date 2018/9/7 16:54
     */
    public static String getClaim(String token, String claim) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            // 只能输出String类型，如果是其他类型返回null
            return jwt.getClaim(claim).asString();
        } catch (JWTDecodeException e) {
            log.error("解密Token中的公共信息出现JWTDecodeException异常:" + e.getMessage());
            throw new CustomException("解密Token中的公共信息出现JWTDecodeException异常:" + e.getMessage());
        }
    }

    /**
     * 生成签名
     *
     * @param account 帐号
     * @return java.lang.String 返回加密的Token
     * @author suntf
     * @date 2018/8/31 9:07
     */
    public static String sign(String account, String currentTimeMillis) {
        try {
            // 帐号加JWT私钥加密
            String secret = account + Base64ConvertUtil.decode(JWTConstant.JWT_SECERT);
            // 此处过期时间是以毫秒为单位
            Date date = new Date(System.currentTimeMillis() + JWTConstant.JWT_TTL);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带account帐号信息
            return JWT.create()
                    .withClaim("account", account)
                    .withClaim("currentTimeMillis", currentTimeMillis)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            log.error("JWTToken加密出现UnsupportedEncodingException异常:" + e.getMessage());
            throw new CustomException("JWTToken加密出现UnsupportedEncodingException异常:" + e.getMessage());
        }
    }
    /**
     * 根据用户名和密码生成签名
     *
     * @param account 帐号
     * @return java.lang.String 返回加密的Token
     * @author suntf
     * @date 2018/8/31 9:07
     */
    public static String signWithPassword(String account, String password,String currentTimeMillis) {
        try {
            // 帐号加JWT私钥加密
            String secret = account + Base64ConvertUtil.decode(JWTConstant.JWT_SECERT);
            // 此处过期时间是以毫秒为单位
            Date date = new Date(System.currentTimeMillis() + JWTConstant.JWT_TTL);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带account帐号信息
            return JWT.create()
                    .withClaim("account", account)
                    .withClaim("password", password)
                    .withClaim("currentTimeMillis", currentTimeMillis)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            log.error("JWTToken加密出现UnsupportedEncodingException异常:" + e.getMessage());
            throw new CustomException("JWTToken加密出现UnsupportedEncodingException异常:" + e.getMessage());
        }
    }
    /**
     * 功能描述: 解密Token
     *
     * @param token 1
     * @return java.util.Map<java.lang.String                                                                                                                               ,                                                                                                                               com.auth0.jwt.interfaces.Claim>
     * @author suntf
     * @date 2019/2/28 9:49
     */
    private static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt;
        try {
            String secret = getClaim(token, "account") + Base64ConvertUtil.decode(JWTConstant.JWT_SECERT);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("token 校验失败");
        }
        return jwt.getClaims();
    }

    /**
     * 根据Token获取account
     *
     * @param token 1
     * @return account
     */
    public static String getAppUID(String token) {
        Map<String, Claim> claims = verifyToken(token);
        Claim userIdClaim = claims.get("account");
        if (null == userIdClaim || StringUtils.isEmpty(userIdClaim.asString())) {
            // token 校验失败, 抛出Token验证非法异常
            throw new CustomException("token 校验失败");
        }
        return userIdClaim.asString();

    }
}

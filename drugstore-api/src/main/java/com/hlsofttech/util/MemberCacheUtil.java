package com.hlsofttech.util;

import com.alibaba.fastjson.JSONObject;
import com.bjucloud.redis.client.RedisAccessException;
import com.bjucloud.redis.client.RedisTemplate;
import com.bjucloud.usercenter.dto.RegisterDTO;
import com.hlsofttech.common.TokenConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/***
 * @Author: suntf
 * @Description:用户取值工具类
 * @Date: 2019/8/19
 **/
@Slf4j
@Component
public class MemberCacheUtil implements ApplicationContextAware {

    /**
     * Spring应用上下文环境
     */
    private static ApplicationContext applicationContext;
    private static MemberCacheUtil memberCacheUtil;

    private MemberCacheUtil() {

    }

    public static MemberCacheUtil get() {
        if (null == MemberCacheUtil.memberCacheUtil) {
            memberCacheUtil = new MemberCacheUtil();
        }
        return memberCacheUtil;
    }

    /***
     * @Author: suntf
     * @Description:新增 或者更新 对象
     * @Date: 2019/8/19
     * @param registerDTO:11
     **/
    public static void saveOrUpadte(RegisterDTO registerDTO) {

        // 根据手机号存
        String phone = registerDTO.getUserMobile();
        // 根据用户名存
        String userName = registerDTO.getLoginname();
        RedisTemplate redisTemplate = (RedisTemplate) getBean("redisTemplate");
        if (!StringUtils.isEmpty(phone) && !StringUtils.isEmpty(userName)) {
            String keyPhone = TokenConstants.CURRENT_LOGIN_TOKEN + phone;
            String keyUserName = TokenConstants.CURRENT_LOGIN_TOKEN + userName;
            try {
                redisTemplate.set(keyPhone, 30 * 24 * 60 * 60 * 60, JSONObject.toJSONString(registerDTO));
                redisTemplate.set(keyUserName, 30 * 24 * 60 * 60 * 60, JSONObject.toJSONString(registerDTO));
            } catch (RedisAccessException e) {
                e.printStackTrace();
            }
        } else if (!StringUtils.isEmpty(phone)) {
            String keyPhone = TokenConstants.CURRENT_LOGIN_TOKEN + phone;
            try {
                redisTemplate.set(keyPhone, 30 * 24 * 60 * 60 * 60, JSONObject.toJSONString(registerDTO));
            } catch (RedisAccessException e) {
                e.printStackTrace();
            }
        } else if (!StringUtils.isEmpty(userName)) {
            String keyUserName = TokenConstants.CURRENT_LOGIN_TOKEN + userName;
            try {
                redisTemplate.set(keyUserName, 30 * 24 * 60 * 60 * 60, JSONObject.toJSONString(registerDTO));
            } catch (RedisAccessException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 功能描述: 获取当前登陆用户信息
     *
     * @return com.threecat.api.common.member.dto.MemberDto
     * @author suntf
     * @date 2019/2/22 18:44
     */
    public static RegisterDTO getSessionUserInfo() {
        RegisterDTO registerDTO = null;
        Subject subject = SecurityUtils.getSubject();
        // 从权限中获取用户标识
        String token = (String) subject.getPrincipal();
        String userName = JwtUtil.getAppUID(token);
//        // 根据用户标识从缓存中获取用户信息
        if (!StringUtils.isBlank(userName)) {
            String redisKey = TokenConstants.CURRENT_LOGIN_TOKEN + userName;
            RedisTemplate redisTemplate = (RedisTemplate) getBean("redisTemplate");
            String geto = null;
            try {
                geto = redisTemplate.get(redisKey) + "";
            } catch (RedisAccessException e) {
                e.printStackTrace();
            }
            if (!StringUtils.isBlank(geto)) {
                registerDTO = JSONObject.parseObject(geto, RegisterDTO.class);
            }
            if (registerDTO == null) {
                log.warn("没有发现缓存对象！");
                // 如果缓存中没有用户信息，则调用服务从数据库中查询
                throw new AuthenticationException();
            }
        } else {
            log.warn("用户登录失效或者没有登录");
            throw new AuthenticationException();
        }
        return registerDTO;
    }

    /**
     * 实现ApplicationContextAware接口的回调方法。设置上下文环境
     *
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MemberCacheUtil.applicationContext = applicationContext;
    }

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取对象
     *
     * @param name
     * @return Object
     */
    public static Object getBean(String name) {
//        String[] str=applicationContext.getBeanDefinitionNames();
//        for (String string : str) {
//            System.out.println("..."+string);
//        }
        return applicationContext.getBean(name);
    }

    /**
     * 获取对象通过Class
     *
     * @param cls
     * @return Object
     * @throws BeansException
     */
    public static <C> Object getBean(Class<C> cls) throws BeansException {
        return applicationContext.getBean(cls);
    }
}

package com.hlsofttech.interceptors;

import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.common.TokenConstants;
import com.hlsofttech.enumeration.PlatformType;
import com.hlsofttech.exception.AppWebException;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Package: com.hailian.interceptor.AppInterceptors
 * Description: 描述
 * Copyright: Copyright (c) 2017
 *
 * @author lv bin
 * Date: 2018/1/19 14:11
 * Version: V1.0.0
 */
@Configuration
public class AppInterceptors extends WebMvcConfigurerAdapter {

    public static String REQUEST_TIME = "http_request_time";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                                     Object handler) throws Exception {
                request.setAttribute(REQUEST_TIME, new Date());
                return true;
            }
        }).addPathPatterns("/*", "/user/**");
        registry.addInterceptor(new ApiInterceptor()).addPathPatterns("/api/**");
    }

    /**
     * api 拦截器
     */
    private class ApiInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

            String uri = request.getRequestURI();

            boolean avoidVersion = false;

            boolean avoidLogin = true;

            boolean avoidPower = true;

            boolean avoidSign = true;
            boolean avoidPlatform = true;
            if (handler instanceof HandlerMethod) {

                HandlerMethod method = (HandlerMethod) handler;
                AuthPower authPower = method.getMethodAnnotation(AuthPower.class);
                avoidVersion = authPower.avoidVersion();
                avoidLogin = authPower.avoidLogin();
                avoidPower = authPower.avoidPower();
                avoidSign = authPower.avoidSign();
                avoidPlatform = authPower.avoidPlatform();
            }

            String platform = request.getHeader(TokenConstants.X_PLATFORM);

            if (!avoidPlatform && (StringUtils.isEmpty(platform) || PlatformType.getTypeByPlatform(platform) == null)) {
                throw new AppWebException("平台类型异常：只能为PC、ANDROID、IOS");
            }

//            String version = "/api/".concat(APP_VERSION);
//
//            // 版本号校验
//            if (!avoidVersion && !uri.startsWith(version)) {
//                throw new AppWebException("版本参数异常，当前版本" + version);
//            }

            // 登录校验
            if (!avoidLogin) {
//                String tokenAuth = request.getHeader(DEFAULT_TOKEN_NAME);
//                // token 是否为空  以及redis中获取token是否存在
//                if (StringUtils.isEmpty(tokenAuth)) {
//                    throw new AuthenticationException("X-Token不能为空！");
//                }
//
//                String account = JwtUtil.getClaim(tokenAuth, JWTConstant.ACCOUNT);
//                if (account == null) {
//                    throw new AuthenticationException("登陆超时，请重新登陆！");
//                }
//
//                // 延长token时间
//                JedisUtil.getJedis().expire(TokenConstants.CURRENT_LOGIN_TOKEN + ":" + account, TokenConstants.TOKEN_EXPIRES_TIME);
            }

            if (!avoidPower) {
                // 需要判断用户是否有权访问接口 -- db内配置用户角色接口访问权限
                throw new AuthenticationException("没有访问权限!");
            }

//            String signAuth = request.getHeader(DEFAULT_AUTH_NAME);
//            if (!avoidSign && StringUtils.isEmpty(signAuth)) {
//                // 判断是否需要校验参数规则
//                throw new AppWebException("非法参数加密值 !");
//            }

            return super.preHandle(request, response, handler);
        }
    }
}
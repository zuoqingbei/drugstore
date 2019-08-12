package com.hlsofttech.configuration.shiro.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 功能描述: JwtToken
 *
 * @author suntf
 * @date 2019/2/28 9:50
 */
public class JwtToken implements AuthenticationToken {
    private static final long serialVersionUID = 9174816704242500805L;
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}

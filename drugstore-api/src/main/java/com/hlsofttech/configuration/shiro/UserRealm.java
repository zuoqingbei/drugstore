package com.hlsofttech.configuration.shiro;

import com.hlsofttech.configuration.shiro.jwt.JwtToken;
import com.hlsofttech.constant.JWTConstant;
import com.hlsofttech.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;

/**
 * 功能描述: 用户权限校验
 *
 * @author suntf
 * @date 2019/2/24 15:11
 */
@Slf4j
@Service
public class UserRealm extends AuthorizingRealm {

    /**
     * 用户服务接口
     */
//    @Resource
//    UserConsumer userConsumer;

    /**
     * 功能描述: 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     *
     * @param principals 1
     * @return org.apache.shiro.authz.AuthorizationInfo
     * @author suntf
     * @date 2019/2/27 21:30
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return new SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得account，用于和数据库进行对比
        String account = JwtUtil.getClaim(token, JWTConstant.ACCOUNT);
        // 解密获得密码，用于和数据库进行对比
        String password = JwtUtil.getClaim(token, JWTConstant.PASS);

        // 帐号为空
        if (StringUtils.isBlank(account)) {
            throw new AuthenticationException("Token中帐号为空(The account in Token is empty.)");
        }
        // 查询用户是否存在
//        MemberDTO memberDTOCheck = MemberCacheUtil.getMemberByPhone(phone);
//        if (memberDTOCheck == null) {
//            Result<MemberDTO> memberDTOResult = userConsumer.getUserByPhone(phone);
//            if (memberDTOResult != null && memberDTOResult.getBody() != null) {
//                memberDTOCheck = memberDTOResult.getBody();
//            } else {
//                throw new UnknownAccountException(MsgHelpConstants.NOT_FLUND_MEMBER);
//            }
//        }
        // 校验密码
//        MemberDTO memberDTO = new MemberDTO();
//        memberDTO.setPassword(password);
//        memberDTO.setSalt(memberDTOCheck.getSalt());
//        // 密码加密是以密码+盐的形式进行加密
//        PasswordHelper.encryptPassword(memberDTO);
//        memberDTO.setPassword(memberDTO.getPassword() == null ? "" : memberDTO.getPassword());
//        if (!memberDTO.getPassword().equals(memberDTOCheck.getPassword())) {
//            throw new AuthenticationException("修改了密码，需重新登录！");
//        }

        // 判断账户是否冻结
//        if (memberDTOCheck.getFreezeStatus() != null && memberDTOCheck.getFreezeStatus() == CommonConstants.INT_2) {
//            throw new UnknownAccountException(MsgHelpConstants.USER_FREEZE);
//        }
        // 开始认证，要AccessToken认证通过
        if (JwtUtil.verify(token)) {
            return new SimpleAuthenticationInfo(token, token, "userRealm");
        }
        log.info("token:" + token);
        throw new AuthenticationException("Token已过期(Token expired or incorrect.)");
    }


    /**
     * 大坑，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
}

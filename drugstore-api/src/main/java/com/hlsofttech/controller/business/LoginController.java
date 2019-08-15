package com.hlsofttech.controller.business;

import com.bjucloud.redis.client.RedisAccessException;
import com.bjucloud.redis.client.RedisTemplate;
import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.common.TokenConstants;
import com.hlsofttech.entity.vo.LoginParam;
import com.hlsofttech.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/***
 * @Author: suntf
 * @Description:商户端登录接口
 * @Date: 2019/8/12
 **/
@RestController
@Api(tags = "商户端-登陆", value = "商户端-登陆", description = "商户端-登陆 @author suntf")
public class LoginController {
    @Resource
    RedisTemplate redisTemplate;
    /**
     * 功能描述: 用户名密码登陆
     *
     * @param loginParam          登录参数
     * @param httpServletResponse 网络响应
     * @return java.lang.String
     * @author suntf
     * @date 2019/2/22 19:21
     */
    @PostMapping("/api/login/pass")
    @ApiOperation(value = "登陆-账号密码", notes = "登陆-账号密码", httpMethod = "POST")
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = false, avoidPlatform = true)
    public String login(@RequestBody LoginParam loginParam, HttpServletResponse httpServletResponse) {
        // 查询登录用户是否存在
//        MemberDTO member = null;
//        Result<MemberDTO> result = userConsumer.getUserByPhone(loginParam.getUsername());
//        if (result.getSuccess()) {
//            member = result.getBody();
//        }
//        if (member == null) {
//            // 不存在该用户
//            return ResponseUtil.error(MsgHelpConstants.NOT_FLUND_MEMBER);
//        }

        // 密码进行加密
//        MemberDTO memberDTO = new MemberDTO();
//        memberDTO.setPassword(loginParam.getPassword());
//        memberDTO.setSalt(member.getSalt());
//        // 密码加密是以密码+盐的形式进行加密
//        PasswordHelper.encryptPassword(memberDTO);

//        if (memberDTO.getPassword().equals(member.getPassword())) {

        // 设置RefreshToken，时间戳为当前时间戳，直接设置即可(不用先删后设，会覆盖已有的RefreshToken)
        String currentTimeMillis = String.valueOf(System.currentTimeMillis());
        try {
            redisTemplate.setObject(TokenConstants.CURRENT_LOGIN_TOKEN + loginParam.getUsername(), currentTimeMillis,
                    TokenConstants.TOKEN_EXPIRES_TIME);
        } catch (RedisAccessException e) {
            e.printStackTrace();
        }
        // 登陆成功，将用户信息放入或更新到缓存
//            MemberCacheUtil.saveOrUpadte(member);
        // 从Header中Token返回AccessToken，时间戳为当前时间戳
        String token = JwtUtil.signWithPassword(loginParam.getUsername(), loginParam.getPassword(), currentTimeMillis);
        httpServletResponse.setHeader("Authorization", token);
        httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
        // 返回给前端的对象字段过滤
//            MemberVO memberVO = new MemberVO();
//            BeanUtils.copyProperties(member, memberVO);
//            return ResponseUtil.data(memberVO, MsgHelpConstants.MSG_LOGIN);
        return "";
//        } else {
//            return ResponseUtil.notLogin(MsgHelpConstants.PASSWORD_ERROR);
//        }
    }
}

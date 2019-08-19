package com.hlsofttech.controller.business;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjucloud.common.ExecuteResult;
import com.bjucloud.redis.client.RedisAccessException;
import com.bjucloud.redis.client.RedisTemplate;
import com.bjucloud.usercenter.dto.RegisterDTO;
import com.bjucloud.usercenter.dto.RegisterInfoDTO;
import com.bjucloud.usercenter.dto.UserDTO;
import com.bjucloud.usercenter.dto.UserPasswordDTO;
import com.bjucloud.usercenter.service.UserExportService;
import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.common.Constant;
import com.hlsofttech.common.TokenConstants;
import com.hlsofttech.entity.vo.LoginParam;
import com.hlsofttech.exception.CommonBizException;
import com.hlsofttech.exception.ExpCodeEnum;
import com.hlsofttech.rsp.Result;
import com.hlsofttech.util.JwtUtil;
import com.hlsofttech.util.MemberCacheUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @Reference(version = Constant.VERSION_ZT, timeout = Constant.TIMEOUT)
    UserExportService userExportService;

    /**
     * 功能描述: 用户名密码登陆
     *
     * @param loginParam          登录参数
     * @param httpServletResponse 网络响应
     * @return java.lang.String
     * @author suntf
     * @date 2019/2/22 19:21
     */
    @PostMapping("/api/business/login/pass")
    @ApiOperation(value = "登陆-账号密码", notes = "登陆-账号密码", httpMethod = "POST")
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = false, avoidPlatform = true)
    public Result login(@RequestBody LoginParam loginParam, HttpServletResponse httpServletResponse) {
        // 查询登录用户是否存在
        boolean userExists = userExportService.verifyLoginName(loginParam.getUsername());
        if (!userExists) {
            // 不存在该用户
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.NO_USER));
        }
        // 根据登陆名称查询 salt 和 hash
        ExecuteResult<UserPasswordDTO> pwdResult = userExportService.getEntryPwdByName(loginParam.getUsername());
        if (pwdResult.isSuccess() && pwdResult.getResult() != null) {
            // 校验密码
            if (pwdResult.getResult().getEnctyptPwd().equals(loginParam.getPassword())) {

                // 设置RefreshToken，时间戳为当前时间戳，直接设置即可(不用先删后设，会覆盖已有的RefreshToken)
                String currentTimeMillis = String.valueOf(System.currentTimeMillis());
                try {
                    redisTemplate.setObject(TokenConstants.CURRENT_LOGIN_TOKEN + loginParam.getUsername(), currentTimeMillis,
                            TokenConstants.TOKEN_EXPIRES_TIME);
                } catch (RedisAccessException e) {
                    e.printStackTrace();
                }
                // 登陆成功，将用户信息放入或更新到缓存
                RegisterDTO registerDTO = userExportService.getUserInfoByUsername(loginParam.getUsername());
                if (registerDTO != null && registerDTO.getShopId() != null) {
                    MemberCacheUtil.saveOrUpadte(registerDTO);
                    // 从Header中Token返回AccessToken，时间戳为当前时间戳
                    String token = JwtUtil.signWithPassword(loginParam.getUsername(), loginParam.getPassword(), currentTimeMillis);
                    httpServletResponse.setHeader("Authorization", token);
                    httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");

                    return Result.newSuccessResult(registerDTO);
                } else {
                    return Result.newFailureResult(new CommonBizException(ExpCodeEnum.NO_USER));
                }

            } else {
                return Result.newFailureResult(new CommonBizException(ExpCodeEnum.NAME_OR_PWD_ERROR));
            }
        } else {
            return Result.newFailureResult(new CommonBizException(ExpCodeEnum.LOGIN_FAIL));
        }
    }


    /***
     * @Author: suntf
     * @Description:用户注册
     * @Date: 2019/8/19
     * @return: java.lang.String
     **/
    @GetMapping("/api/business/center/regist")
    @ApiOperation(value = "(禁止调用)用户-用户名密码注册", notes = "(禁止调用)用户-用户名密码注册", httpMethod = "GET")
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = false, avoidPlatform = true)
    public Result addShop(String userName, String pwd) {

        RegisterInfoDTO registerInfoDTO = new RegisterInfoDTO();
        registerInfoDTO.setLoginname(userName);
        registerInfoDTO.setLoginpwd(pwd);
        Long ret = userExportService.registerUser(registerInfoDTO);
        return Result.newSuccessResult(ret);
    }

    /***
     * @Author: suntf
     * @Description:中台用户关联店铺
     * @Date: 2019/8/19
     * @return: java.lang.String
     **/
    @GetMapping("/api/business/center/userShop")
    @ApiOperation(value = "(禁止调用)用户-关联店铺", notes = "(禁止调用)用户-关联店铺", httpMethod = "GET")
    @AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = false, avoidPlatform = true)
    public Result addShop(Long shopId, Long userId) {

        UserDTO userDTO = new UserDTO();
        userDTO.setShopId(shopId);
        userDTO.setUid(userId);
        boolean ret = userExportService.modifyUserInfo(userDTO);
        return Result.newSuccessResult(ret);
    }
}
package com.hlsofttech.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 功能描述: 登陆所需参数
 *
 * @author suntf
 * @date 2019/8/12
 */
@Getter
@Setter
@ToString
public class LoginParam {

    String username;
    String password;

    String phone;
    String code;

}

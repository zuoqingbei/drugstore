package com.hlsofttech.delivery.platform.meituan.util;

import com.hlsofttech.platform.meituan.constants.OpenApiConfig;
import com.hlsofttech.platform.meituan.sign.SignHelper;
import com.hlsofttech.platform.meituan.util.HttpClient;
import com.hlsofttech.rsp.Result;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;


/***
 * @Description:
 * @Date: 2019/7/29 16:05
 * @Author: suncy
 **/
@Slf4j
public class MeituanUtils {

    public static Result getResult(Object res, String code, String message) {
        if ("0".equals(code)) {
            return new Result<>(true, code, message, res);
        } else {
            return new Result<>(false, code, message, res);
        }
    }

    public static String signAndRequest(Map<String, String> params, String url) throws NoSuchAlgorithmException, IOException {
        String sign = SignHelper.generateSign(params, OpenApiConfig.SECRET);

        params.put("sign", sign);

        String res = HttpClient.post(url, params);
        log.info(String.format("reponse data: %s", res));
        return res;
    }

}

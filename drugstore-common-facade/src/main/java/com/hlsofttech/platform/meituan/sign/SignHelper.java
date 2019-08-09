package com.hlsofttech.delivery.platform.meituan.sign;

import com.hlsofttech.delivery.platform.meituan.constants.OpenApiConfig;
import com.hlsofttech.delivery.platform.meituan.util.SHA1Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 签名计算工具类
 */
@Slf4j
public class SignHelper {

    public static String generateSign(Map<String, String> params, String secret)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String encodeString = getEncodeString(params, secret);
        log.info(String.format("encodeString: %s", encodeString));
        String sign = generateSign(encodeString);
        log.info(String.format("generateSign: %s", sign));
        return sign;
    }

    private static String getEncodeString(Map<String, String> params, String secret) {
        Iterator<String> keyIter = params.keySet().iterator();
        Set<String> sortedParams = new TreeSet<>();
        while (keyIter.hasNext()) {
            sortedParams.add(keyIter.next());
        }

        StringBuilder strB = new StringBuilder(secret);

        // 排除sign和空值参数
        for (String key : sortedParams) {
            if (key.equals("sign")) {
                continue;
            }

            String value = params.get(key);

            if (StringUtils.isNotEmpty(value)) {
                strB.append(key).append(value);
            }
        }
        return strB.toString();
    }

    private static String generateSign(String content)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return SHA1Util.Sha1(content).toLowerCase();
    }

    /***
     * @Description: 加密并比较
     * @Date: 2019/7/30 11:41
     * @param params
     * @return: boolean
     * @Author: suncy
     **/
    public static boolean signAndCheck(Map<String, String> params)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String nowSign = generateSign(params, OpenApiConfig.SECRET);
        log.info("回调收到的sign【" + params.get("sign") + "】，参数加密后的sign【" + nowSign + "】");
        return params.get("sign").equals(nowSign);
    }

    /***
     * @Description: 传递动态的secret进行比较
     * @Date: 2019/8/5 16:38
     * @param params:
     * @param secret:
     * @return: boolean
     * @Author: suncy
     **/
    public static boolean signAndCheck(Map<String, String> params, String secret)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String nowSign = generateSign(params, secret);
        log.info("回调收到的sign【" + params.get("sign") + "】，参数加密后的sign【" + nowSign + "】");
        return params.get("sign").equals(nowSign);
    }
}

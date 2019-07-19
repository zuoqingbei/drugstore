package com.hlsofttech.utils;

import java.util.Random;

/**
 * 随机密码生成
 * @author ZhaoJC
 * @date 2019年3月25日
 */
public class RandPwdUtil {
  public static void main(String[] args) {
    String password = genRandPwd(24);
    System.out.println(password);
  }

  // 获取验证过的随机密码（必须包含大小写字母、数字和特殊字符）
  public static String genRandPwd(int len) {
    String result = null;
    while (len >= 6) {
      result = makeRandPwd(len);
      if (result.matches(".*[a-z]{1,}.*") && result.matches(".*[A-Z]{1,}.*") && result.matches(".*\\d{1,}.*")
          && result.matches(".*[~!@#$%^&*\\.?]{1,}.*")) {
        return result;
      }else {
        System.out.println("不符合："+result);
      }
    }
    return "长度不得少于6位!";
  }

  // 随机密码生成
  private static String makeRandPwd(int len) {
    char charr1[] = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    char charr2[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    char charr3[] = "1234567890".toCharArray();
    char charr4[] = "~!@#$%^&*.?".toCharArray();
    char charr[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890~!@#$%^&*.?".toCharArray();
    // System.out.println("字符数组长度:" + charr.length); //可以看到调用此方法多少次
    StringBuilder sb = new StringBuilder();
    Random r = new Random();
    sb.append(charr1[r.nextInt(charr1.length)]);
    sb.append(charr2[r.nextInt(charr2.length)]);
    sb.append(charr3[r.nextInt(charr3.length)]);
    sb.append(charr4[r.nextInt(charr4.length)]);
    for (int x = 0; x < len; ++x) {
      sb.append(charr[r.nextInt(charr.length)]);
    }
    return sb.toString();
  }

}

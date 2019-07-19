package com.hlsofttech.utils;

/**
 * 
 * @author ZhaoJC
 * @date 2018年8月8日
 */
public class IntegerUtils {

  /**
   * 比较
   * 
   * @param v1
   * @param v2
   * @return
   * @author ZhaoJC
   * @date 2018年8月8日上午11:13:28
   */
  public static boolean equals(Integer v1, Integer v2) {
    if (null == v1 && null == v2) {
      return true;
    } else if (null == v1 || null == v2) {
      return false;
    } else {
      return v1.equals(v2);
    }
  }

  /**
   * parseInt that never throws exceptions
   * 
   * @param str
   * @return
   */
  public static int parseInt(String str) {
    try {
      return Integer.parseInt(str);
    } catch (Exception e) {
      return 0;
    }
  }

}

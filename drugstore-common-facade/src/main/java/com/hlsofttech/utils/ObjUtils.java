package com.hlsofttech.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * 
 * @author ZhaoJC
 * @date 2019年1月10日
 */
public class ObjUtils {

  /**
   * 判断数组、集合是否为空(null或者没有元素)
   * 
   * @param obj
   * @return
   * @author ZhaoJC
   * @date 2019年1月10日下午5:38:38
   */
  public static boolean isEmpty(Object obj) {
    if (null == obj) {
      return true;
    }
    boolean ifarray = obj.getClass().isArray();
    if (ifarray) {
      return 0 == Array.getLength(obj) ? true : false;
    }
    boolean ifcol = obj instanceof Collection;
    if (ifcol) {
      Collection<?> col = (Collection<?>) obj;
      return col.isEmpty();
    }
    boolean ifmap = obj instanceof Map;
    if (ifmap) {
      Map<?, ?> map = (Map<?, ?>) obj;
      return map.isEmpty();
    }
    return false;
  }

  public static boolean isNotEmpty(Object obj) {
    return !isEmpty(obj);
  }


}

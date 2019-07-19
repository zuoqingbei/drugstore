package com.hlsofttech.base;

import java.beans.PropertyEditorSupport;
import org.apache.commons.lang3.StringUtils;
import com.hlsofttech.utils.SysDateUtil;

/**
 * Date数据绑定类
 * @author Administrator
 *
 */
public class DateEditor extends PropertyEditorSupport {

  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    setValue(SysDateUtil.parseDate(StringUtils.trim(text)));
  }

}

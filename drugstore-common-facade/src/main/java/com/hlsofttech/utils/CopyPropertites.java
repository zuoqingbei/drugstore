package com.hlsofttech.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class CopyPropertites {
	public static void copy(Object source, Object to) {
		try {
			// 获取属性
			BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(),
					java.lang.Object.class);
			PropertyDescriptor[] sourceProperty = sourceBean
					.getPropertyDescriptors();

			BeanInfo destBean = Introspector.getBeanInfo(to.getClass(),
					java.lang.Object.class);
			PropertyDescriptor[] destProperty = destBean
					.getPropertyDescriptors();

			for (int i = 0; i < sourceProperty.length; i++) {

				for (int j = 0; j < destProperty.length; j++) {

					if (sourceProperty[i].getName().equals(
							destProperty[j].getName())) {
						// 调用source的getter方法和dest的setter方法
						destProperty[j].getWriteMethod().invoke(
								to,
								sourceProperty[i].getReadMethod()
										.invoke(source));
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

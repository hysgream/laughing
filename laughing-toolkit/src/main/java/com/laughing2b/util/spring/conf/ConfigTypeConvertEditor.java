package com.laughing2b.util.spring.conf;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.SimpleTypeConverter;
import org.springframework.beans.TypeMismatchException;

import com.google.common.collect.Lists;

/**
 * 
 * @ClassName: ConfigTypeConvertEditor
 * @Description: * 参数类型转换 配置文件的参数以 ","号作为元素分隔符
 * @author lifei.pan
 * @email plfnet@163.com
 * @date 2015年8月7日 下午4:10:36
 *
 */
public class ConfigTypeConvertEditor extends SimpleTypeConverter {
	public static final String ELEMENT_SEPARATOR = ",";

	public Object convertIfNecessary(Object value, Type type)
			throws TypeMismatchException {
		// 奇怪：没有自动使用多态
		if (type instanceof Class)
			return convertIfNecessary(value, (Class) type);
		if (value instanceof String) {
			ArrayList<String> list = Lists.newArrayList(((String) value)
					.split(ELEMENT_SEPARATOR));
			List<Object> finalList = Lists.newArrayList();
			Type[] fTypes = ((ParameterizedType) type).getActualTypeArguments();

			Type fType = fTypes[0];

			for (String v : list) {
				finalList.add(convertIfNecessary(v, (Class) fType));
			}
			return finalList;
		}
		return value;
	}

}

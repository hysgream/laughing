package com.laughing.lang.magic.cglib;

import com.laughing.lang.utils.ClassUtil;

public class CglibUtil {
	
	private static final String CGLIB_TOKEN = "$$EnhancerByCGLIB$$";
	
	public static Class<?> getJavaClass(String cglibProxyClassName) {
		String className = cglibProxyClassName;
		int pos = className.indexOf(CGLIB_TOKEN);
		if (pos < 0) {
			return ClassUtil.forName(className);
		}
		String javaClassName = className.substring(0, pos);
		return ClassUtil.forName(javaClassName);
	}
	
	public static Class<?> getJavaClass(Class<?> cglibProxyClass) {
		String className = cglibProxyClass.getName();
		int pos = className.indexOf(CGLIB_TOKEN);
		if (pos < 0) {
			return cglibProxyClass;
		}
		String javaClassName = className.substring(0, pos);
		return ClassUtil.forName(javaClassName);
	}
}

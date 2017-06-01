package com.laughing2b.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SpringContextHolder
 * @Description: applicationContextHolder帮助类,用于静态注入 private static UserService
 *               userService = SpringContextHolder .getBean(UserService.class);
 * @author lifei.pan
 * @email plfnet@163.com
 * @date 2015年9月11日 下午4:04:05
 *
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextHolder.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanId) {
		return (T) applicationContext.getBean(beanId);
	}

	public static <T> T getBean(Class<T> classType) {
		return (T) applicationContext.getBean(classType);
	}
}

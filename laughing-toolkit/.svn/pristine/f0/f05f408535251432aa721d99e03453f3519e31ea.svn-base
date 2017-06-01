package com.laughing2b.util.spring.conf;

/**
 * @ClassName: SystemEnv
 * @Description: * 程序运行环境的管理器 默认情况程序以 develop，test，cp，product 模式运行
 *               如果生产环境部署了多个模块，通过：命名 productXX 来 区分,如produc1，product2
 * @author lifei.pan
 * @email plfnet@163.com
 * @date 2015年8月7日 下午4:10:05
 *
 */
public class SystemEnv {
	private static String envTag = "env";

	/**
	 * 获取当前环境名
	 * 
	 * @return
	 */
	public static String getCurrentEnv() {
		String env = System.getProperty(envTag);
		if (env == null)
			env = System.getenv("env");
		if (env == null)
			env = "product";
		return env;
	}

	/**
	 * 当前环境是否是生产环境
	 * 
	 * @return 如果是返回true
	 */
	public static boolean isProductEnv() {
		return getCurrentEnv().contains("product");
	}
}

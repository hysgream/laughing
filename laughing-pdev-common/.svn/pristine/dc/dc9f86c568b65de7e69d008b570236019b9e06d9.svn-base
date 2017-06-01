package com.laughing2b.common.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: OrderUtil
 * @Description: 生成唯一订单号
 * @author lifei.pan
 * @email plfnet@163.com
 * @date 2015年10月12日 上午9:57:10
 *
 */
public class OrderUtil {
	private static String serverId = "0";
	private final static AtomicInteger baseId = new AtomicInteger(0);
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyhhmmss");

	/**
	 * 生成订单流水号
	 *
	 * @param prefix
	 *            前缀，如appid或者商户Id等，以保证每个应用生成的都不一样
	 * @return 流水号
	 */
	public static String makeOrderId(String prefix) {
		// 如果达到10000，又重新来过
		baseId.compareAndSet(10000, 0);
		String s = String.format("%2s%6s%8s%4s", serverId, prefix,
				sdf.format(new Date()), baseId.getAndIncrement());
		// 去掉空字符
		return s.replaceAll(" ", "0");
	}

	public void setServerId(String serverId) {
		OrderUtil.serverId = serverId;
	}
}

package com.laughing2b.util.list;

import java.util.List;

/**
 * @ClassName: ListUtil
 * @Description: 列表处理类
 * @author lifei.pan
 * @email plfnet@163.com
 * @date 2015年8月7日 下午5:35:26
 *
 */
public class ListUtil {

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(List list) {
		return !isNotEmpty(list);
	}

	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(List list) {
		if (list != null && !list.isEmpty()) {
			return true;
		}

		return false;
	}
}

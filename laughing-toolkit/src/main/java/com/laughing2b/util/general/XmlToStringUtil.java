package com.laughing2b.util.general;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: XmlToStringUtil
 * @Description: 获取两个字符,中间的内容(可用于复杂的XML处理)
 * @author lifei.pan
 * @email plfnet@163.com
 * @date 2015年8月7日 下午5:16:50
 *
 */
public class XmlToStringUtil {
	public static String getStringFromXml(String xml, String reg) {
		String regex = "<" + reg + ">(.*)</" + reg + ">";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(xml);
		while (matcher.find()) {
			return matcher.group(1);
		}
		return null;
	}
}

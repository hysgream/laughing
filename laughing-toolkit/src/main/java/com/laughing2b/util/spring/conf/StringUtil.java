package com.laughing2b.util.spring.conf;

import org.apache.commons.lang3.StringUtils;

/**
* @ClassName: StringUtil 
* @Description:扩展了字符串工具集
* @author lifei.pan
* @email plfnet@163.com
* @date 2015年8月7日 下午4:09:07 
*
 */
public class StringUtil extends StringUtils {
    /**
     * 用 特定字符替换字符串尾部
     * example:
     * StringUtils.encodeEnd("123456", "*", 3) = "123***"
     * StringUtils.encodeEnd("12", "*", 3)= "12"
     * StringUtils.encodeEnd(null, "*", 3)= ""
     *
     * @param oldStr     原始字符
     * @param replaceStr 替换字符
     * @param prefixCount 前面留多少位
     * @return
     */
    public static String encodeSuffix( String oldStr, String replaceStr, int prefixCount) {
        if (oldStr == null)
            return "";
        String prefix = substring(oldStr, 0, prefixCount);
        String suffix = substring(oldStr, prefixCount, oldStr.length()).replaceAll(".", replaceStr);
        return prefix + suffix;
    }

    /**
     * 加密前面部分
     * @param oldStr
     * @param replaceStr
     * @param suffixCount  后面留多少位
     * @return
     */
    public static String encodePrefix(String oldStr, String replaceStr, int suffixCount) {
       if (oldStr == null)
            return "";
        if(oldStr.length()<= suffixCount)
            return oldStr;
        int startIndex = oldStr.length() - suffixCount;
        String prefix = substring(oldStr, 0, startIndex).replaceAll(".", replaceStr);
        String suffix = substring(oldStr,startIndex);
        return prefix + suffix;
    }
}

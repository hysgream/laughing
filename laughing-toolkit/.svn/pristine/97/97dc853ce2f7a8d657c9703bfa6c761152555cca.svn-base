package com.laughing2b.util.string;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * Author: Lynn.Mo
 * Email: lynn.mo@sky-mobi.com
 * Date: 2014-1-21
 * url工具类
 */
public class UrlUtils {
	private static Logger logger = LoggerFactory.getLogger(UrlUtils.class);

	public static String getIp(HttpServletRequest request) {

        String ip = request.getHeader("X-Real-IP");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
	
    /**
     * 给url增加参数
     *
     * @param URL
     * @param name
     * @param value
     * @return
     */
    public static String addParameter(String URL, String name, String value) {
        int qpos = URL.indexOf('?');
        int hpos = URL.indexOf('#');
        String sep = "";
        if (qpos == -1)
            sep = "?";
        else {
            if (!URL.endsWith("?"))
                sep = "&";
        }

        String seg = sep + encodeUrl(name) + '=' + encodeUrl(value);
        return hpos == -1 ? URL + seg : URL.substring(0, hpos) + seg
                + URL.substring(hpos);
    }


    public static String removeParameters(String url, Object... args) {
        for (Object key : args) {
            url = url.replaceAll(key + "=*.&?", "");

        }
        return url;
    }

    public static String encodeUrl(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException uee) {
            throw new IllegalArgumentException(uee);
        }
    }


    /**
     * 从url中获得去掉queryString后的url
     * 如：http://rm.myroman.com/pay/xn_bak_sk.php?act=server
     * 操作后：http://rm.myroman.com/pay/xn_bak_sk.php
     *
     * @param url
     * @return
     */
    public static String getBase(String url) {
        if (url.contains("?")) {
            int index = url.indexOf("?");
            return url.substring(0, index);
        }
        return url;
    }

    /**
     * 将参数补充到query字符串
     *
     * @param query
     * @param params
     * @return
     */
    public static String appendQuery(String query, Object... params) {
        Preconditions.checkArgument(params != null && (params.length % 2 == 0), "参数必须成对，2的倍数个");
        if (query == null)
            query = "";
        for (int i = 0; i < params.length; i++) {
            //忽略所有空值
            if (params[i + 1] != null && !"".equals(params[i + 1])) {
                if (!query.equals(""))
                    query += "&";
                query = String.format("%s%s=%s", query, params[i], encode(params[i + 1]));
            }
            i++;
        }
        return query;

    }

    /**
     * utf-8的urlecode
     *
     * @param parameter
     * @return
     */
    public static Object encode(Object parameter) {
        try {
            if (parameter == null)
                return null;

            String str = parameter.toString();

            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("", e);
        }
        return parameter;
    }

    /**
     * 判断字符是否包含中文
     *
     * @param str
     * @return
     */
    public static boolean isChinese(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.substring(i, i + 1).matches("[\\u4e00-\\u9fbb]+")) {
                return true;
            }
        }
        return false;
    }

    public static String getQuery(String url) {
        return URIUtil.getQuery(url);
    }

    private final static String separator = "&";
    private final static String equal = "=";

    public static Map<String, String> transform(String queryString) {
        Map<String, String> ret = new HashMap<String, String>();
        transform(ret, queryString);
        return ret;
    }
    
    public static void transform(Map<String, String> retMap, String queryString) {
    	if (queryString == null) {
			return;
		}
    	
        int begin = 0;
        int end = 0;

        do {
            end = queryString.indexOf(separator, begin);
            if (-1 == end) {
                end = queryString.length();
            }
            String pair = queryString.substring(begin, end);
            begin = end + 1;

            // deal with pair
            int idx = pair.indexOf(equal);
            if (-1 != idx) {
                String key = pair.substring(0, idx).trim();
                String value = pair.substring(idx + 1);
                String list = retMap.get(key);
                if (null == list) {
                	if ("null".equals(value)) {
                		value = "";
					}	
                	retMap.put(key, value);
                } else {
                    list = list +","+ value;
                    retMap.put(key, list);
                }

            }
        } while (end < queryString.length());
    }
    
    /**
	 * 是否包含url转义字符
	 * 1. + URL 中+号表示空格 %2B
	 * 2. 空格 URL中的空格可以用+号或者编码 %20
	 * 3. / 分隔目录和子目录 %2F
	 * 4. ? 分隔实际的 URL 和参数 %3F
	 * 5. % 指定特殊字符 %25
	 * 6. # 表示书签 %23
	 * 7. & URL中指定的参数间的分隔符%26
	 * 8. = URL中指定参数的值 %3D
	 * 9. ! URL中指定参数的值 %21
	 */
	public static boolean includeUrlEscape(String src, String urlEscapeRegex){
		if (src == null) {
			return false;
		}
		
		if (StringUtils.isNotBlank(urlEscapeRegex)) {
			return src.matches(urlEscapeRegex);
		}
		
		if (src.matches(".*[\u4e00-\u9fa5 /?&=].*")) {
			return true;
		}
		
		return false;
	}
	
	public static String urlEncode(String src) {
		String result = null;
		
		if (src == null) {
			return null;
		}
		
		try {
			result = URLEncoder.encode(src, "UTF-8");
		} catch (Exception e) {
			logger.error("encode " + src + ":", e);
		}
		
		return result;
	}
}

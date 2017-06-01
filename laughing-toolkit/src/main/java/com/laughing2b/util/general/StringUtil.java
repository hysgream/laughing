package com.laughing2b.util.general;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import org.apache.log4j.Logger;

public class StringUtil {

    private static final Logger logger = Logger.getLogger(StringUtil.class);
    
    public static String getRepeatString(char a, int size){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<size; i++){
            sb.append(a);
        }
        return sb.toString();
    }
 
    public static String getRandomNumStr(int size){
        StringBuilder sb = new StringBuilder("");
        for(int i=0;i<size;i++){
            sb.append((int)(Math.random()*10));
        }
        return sb.toString();
    }
    
    public static Integer getInteger(String str, int min, int max){
        try{
            Integer i = Integer.valueOf(str);
            if(i > max || i< min){
                throw new IllegalArgumentException("数字超出范围：" + i);
            }
            return i;
        }catch(Exception e){
            logger.error("转换数字错误！", e);
        }
        return -1;
    }
    
    public static String getFixedString(char defaultChar, String str, int size){
        int strSize = str.length();
        if(strSize == size){
            return str;
        }else if(strSize < size){
            return (StringUtil.getRepeatString(defaultChar, size - strSize) + str);
        }else{
            throw new IllegalArgumentException("传入查收长度大于指定长度！参数：" + str);
        }
    }
    /**
     * 数字字符串的进制转换，范围2-36
     * 
     * @param str
     * @return
     */
    public static String transRadix(String str, int from, int to){
        BigInteger i = new BigInteger(str, from);
        return i.toString(to);
        
    }
    
    public static void checkStringLength(String[] strs, String name, int minLength, int maxLength){
        for(String str : strs){
            checkStringLengthNoNull(str, name, maxLength, minLength);
        }
    }
    
    public static void checkStringLengthNoNull(String str, String name, int minLength, int maxLength){
        if(str == null){
            throw new IllegalArgumentException(name+"字段不能为null");
        }
        if(str.length() < minLength){
            throw new IllegalArgumentException(name+"字段小于最小长度"+minLength);
        }
        if(str.length() > maxLength){
            throw new IllegalArgumentException(name+"字段大于最大长度"+maxLength);
        }
    }
    
    public static void checkStringLengthCanBeNull(String str, String name, int minLength, int maxLength){
        if(str == null){
            return;
        }
        if(str.length() < minLength){
            throw new IllegalArgumentException(name+"字段小于最小长度"+minLength);
        }
        if(str.length() > maxLength){
            throw new IllegalArgumentException(name+"字段大于最大长度"+maxLength);
        }
    }
    
    
    public static String UrlEncode(String str){
        try {
            String retStr = URLEncoder.encode(str, "UTF-8");
            return retStr;
        } catch (UnsupportedEncodingException e) {
            //ignore
        }
        return "";
    }
    
    public static String UrlDecode(String str){
        try {
            String retStr = URLDecoder.decode(str, "UTF-8");
            return retStr;
        } catch (UnsupportedEncodingException e) {
            //ignore
        }
        return "";
    }
    
    public static String getSubString(String string, int size){
        if(string == null || string.length() <= size){
            return string;
        }
        return string.substring(0, size);
    }
    
    /**
	 * 获取前后月份，返回格式为yyyyMM
	 * @return String
	 */
	public static String getBeforAfterMonth(int num) {
		Date date = new Date();
		int year = date.getYear() + 1900;
		int month = date.getMonth() + num;
		if(month >= 12) {
			year = year +1;
			month = month - 11;
		} else if(month < 0){
			year = year-1;
			month = month + 13;
		} else{
			month = month + 1;
		}
		return year + (month < 10 ? "0" : "") + month;
	}	
}

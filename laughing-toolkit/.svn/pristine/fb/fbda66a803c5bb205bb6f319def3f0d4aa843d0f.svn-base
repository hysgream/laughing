package com.laughing2b.util.interceptor;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.laughing2b.util.general.AtomicIntegerTool;

/**
 * Author: Lynn.Mo
 * Email: lynn.mo@sky-mobi.com
 * Date: 2013-5-19
 * Describe: 
 */
public class UniqueUtil {
	private final static AtomicIntegerTool BASE_ID = new AtomicIntegerTool(10000);
	private final static String PATTERN = "yyyyMMddHHmmssssssss";
	
	//25位
	public static String createUniqueId() {
		String s = String.format("%s%s%4s", 
				DateFormatUtils.format(new Date(), PATTERN), getRandom(1), BASE_ID.getAndIncrement());
		//去掉空字符
		return s.replaceAll(" ", "0");
	} 
	
	public static String getRandom(int lenth) {
    	String strSrc = "0123456789";
        String result = "";
        
        int index = 0;
        
        for (int i = 0; i < lenth; i++) {
        	index = (int)Math.round((Math.random()*9));
        	result = result + strSrc.charAt(index);
		}
        
        return result;
    }
}


package com.laughing2b.util.general;

import java.util.Iterator;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

/** 
* @ClassName: GetRequestParamUtil 
* @Description: 打印所有的请求参数
* @author lifei.pan
* @date 2015年4月15日 下午3:49:34 
* @Email lifei.pan@sky-mobi.com 
*/
public class GetRequestParamUtil {
	@SuppressWarnings("rawtypes")
	public static String getParam(HttpServletRequest request){
		Iterator iterator = request.getParameterMap().entrySet().iterator(); 
		   StringBuffer param = new StringBuffer();
		   int i = 0;
		   while (iterator.hasNext()) {  
		    i++;
		       Entry entry = (Entry) iterator.next(); 
		       if(i == 1)
		            param.append("?").append(entry.getKey()).append("="); 
		       else
		         param.append("&").append(entry.getKey()).append("=");
		       if (entry.getValue() instanceof String[]) {  
		           param.append(((String[]) entry.getValue())[0]);  
		       } else {  
		           param.append(entry.getValue());  
		       }  
		   }
		   return param.toString();
	}
}

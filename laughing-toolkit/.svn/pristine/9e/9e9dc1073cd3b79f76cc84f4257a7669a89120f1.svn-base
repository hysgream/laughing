package com.laughing2b.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Author: Lynn.Mo
 * Email: lynn.mo@sky-mobi.com
 * Date: 2013-3-8
 * Describe: 
 */
public class AddUniqueIdInterceptor extends HandlerInterceptorAdapter {
	public static final String THREAD_UNIQUE_KEY = "UUID";
	
	private static Logger logger = LoggerFactory.getLogger(AddUniqueIdInterceptor.class);
	
	private final Object mutex = new Object();
	private long reqCnt = 0;
	
	private NamedThreadLocal<Long>  startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");

	private long maxReqCnt = 0;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		MDC.put(THREAD_UNIQUE_KEY, UniqueUtil.createUniqueId());
		
		if (logger.isInfoEnabled()) {
			long beginTime = System.currentTimeMillis();//1、开始时间
			startTimeThreadLocal.set(beginTime);//线程绑定变量（该数据只有当前请求的线程可见）
			
			if (maxReqCnt > 0 && reqCnt > maxReqCnt) {
				logger.warn("抛弃请求：[{}]，请求数：{}, 已大于最大请求数：{}", 
						new Object[]{request.getServletPath(), reqCnt, maxReqCnt});
				return false;
			}
			
			incrReq();
			logger.info("当前请求数：{}", reqCnt);
		}
		
		return super.preHandle(request, response, handler);
	}
	
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		if (logger.isInfoEnabled()) {
			decrReq();
			
			long endTime = System.currentTimeMillis();//2、结束时间
			long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
			long consumeTime = endTime - beginTime;//3、消耗的时间
			logger.info("请求：[{}]，耗时 ：{}ms，剩余请求数：{}", 
					new Object[]{request.getServletPath(), consumeTime, reqCnt});
		}
	}

	private void incrReq(){
		synchronized (mutex) {
			reqCnt++;
		}
	}
	
	private void decrReq(){
		synchronized (mutex) {
			reqCnt--;
		}
	}

	public void setMaxReqCnt(long maxReqCnt) {
		this.maxReqCnt = maxReqCnt;
	}
}


package com.laughing2b.util.interceptor.model;
/**
 * Author: Lynn.Mo
 * Email: lynn.mo@sky-mobi.com
 * Date: 2014年8月13日
 * Describe: 
 */
public class AtomicIntegerTool {
	private final Object mutex = new Object();
	private int value = 0;
	private int maxValue;
	
	public AtomicIntegerTool(int maxValue) {
		this.maxValue = maxValue;
	}

	public int getAndIncrement() {
		synchronized (mutex) {
			if (++value >= maxValue) {
				value = 0;
			}
			return value;
		}
    }
}

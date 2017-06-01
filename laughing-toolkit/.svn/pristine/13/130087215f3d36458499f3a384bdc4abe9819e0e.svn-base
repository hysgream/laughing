package com.laughing2b.util.general;
/**
 * 
* @ClassName: AtomicIntegerTool 
* @Description: 
* @author lifei.pan
* @email plfnet@163.com
* @date 2015年8月7日 下午5:25:42 
*
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

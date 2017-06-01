package com.laughing2b.common;

/**
 * @ClassName: Msg
 * @Description: 返回消息对象
 * @author lifei.pan
 * @email plfnet@163.com
 * @date 2015年9月6日 下午6:31:13
 *
 */
public class Msg {
	public enum MsgType{
		success,
		info,
		error,
		block,
		danger
	}
	
	
	private MsgType type;
	private String content;
	private String ex;

	public Msg() {

	}

	public Msg(MsgType type, String content, String ex) {
		this.type = type;
		this.content = content;
		this.ex = ex;
	}

	public MsgType getType() {
		return type;
	}

	public void setType(MsgType type) {
		this.type = type;
	}


	public String getEx() {
		return ex;
	}

	public void setEx(String ex) {
		this.ex = ex;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Msg [type=" + type + ", content=" + content + ", ex=" + ex
				+ "]";
	}


}

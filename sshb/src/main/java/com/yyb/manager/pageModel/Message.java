package com.yyb.manager.pageModel;

public class Message implements java.io.Serializable {
	/**
	 * 
	 */

	public Message(String code, String msg) {
		this.code = code;
		this.message = msg;
	}
	public Message(String code, String msg,Object obj) {
		this.code = code;
		this.message = msg;
		this.obj = obj;
	}

	private static final long serialVersionUID = 1L;
	private String code;
	private String message;
	private Object obj;

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

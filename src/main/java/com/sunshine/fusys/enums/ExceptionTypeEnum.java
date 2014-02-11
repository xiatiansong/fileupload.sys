package com.sunshine.fusys.enums;


/**
 * 
 * 创建人：fantasy <br>
 * 创建时间：2013-2-25 <br>
 * 功能描述： 异常类型枚举值<br>
 * 
 */
public enum ExceptionTypeEnum {

	VALUE_NULL(301, "值为空");

	private int code;

	private String message;

	private ExceptionTypeEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
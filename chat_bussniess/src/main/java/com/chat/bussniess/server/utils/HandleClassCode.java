package com.chat.bussniess.server.utils;

/**
 * @ClassName HandleClassCode
 * @Description 处理模块
 * @author ASUS
 * @Date 2017年8月16日 下午5:31:07
 * @version 1.0.0
 */
public enum HandleClassCode {
	
	TEST(1, "test");

	private int code;
	private String handleClass;

	HandleClassCode(int code, String handleClass) {
		this.code = code;
		this.handleClass = handleClass;
	}

	public static String getMethodName(int code) {
		HandleClassCode[] method = HandleClassCode.values();
		for (HandleClassCode key : method) {
			if (key.getCode() == code) { return key.getHandleClass(); }
		}
		return null;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getHandleClass() {
		return handleClass;
	}

	public void setHandleClass(String handleClass) {
		this.handleClass = handleClass;
	}
}

package com.chat.bussniess.server.utils;

/**
 * @ClassName MethodCode
 * @Description 方法枚举类
 * @author liupei
 * @Date 2017年8月16日 下午5:14:31
 * @version 1.0.0
 */
public enum MethodCode {

	TEST(1001, "test");

	private int code;
	private String methodName;

	MethodCode(int code, String methodName) {
		this.code = code;
		this.methodName = methodName;
	}
	
	public static String getMethodName(int code) {
		MethodCode[] method = MethodCode.values();
		for (MethodCode key : method) {
			if (key.getCode() == code) {
				return key.getMethodName();
			}
		}
		return null;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
}

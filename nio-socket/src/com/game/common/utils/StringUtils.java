package com.game.common.utils;


/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * @author ThinkGem
 * @version 2013-05-22
 */
public class StringUtils  {
	
	public static boolean isNotBlank (String str) {
		if (str != null && str.trim().length() > 0) {
			return true;
		}
		return false;
	}
	
}

package com.game.user.handler.base;

import com.alibaba.fastjson.JSONObject;

/**
 * 处理基类
 * @author ASUS
 *
 */
public interface BaseHandler {
	
	public JSONObject handle(JSONObject json);
}

package com.chat.bussniess.server.decode;

import com.alibaba.fastjson.JSONObject;
import com.chat.bussniess.act.entiy.Activity;
import com.chat.bussniess.user.entity.UserInfo;

/**
 * @ClassName DataRequest
 * @Description 数据转换处理
 * @author ASUS
 * @Date 2017年8月16日 下午2:56:43
 * @version 1.0.0
 */
public class DataRequest {
	
	private Activity act = null;
	private UserInfo user = null;
	
	/**
	 * @Description 转换数据
	 * @param jsonStr
	 */
	public void encode(String jsonStr) {
		JSONObject json = JSONObject.parseObject(jsonStr);
		if (json.containsKey("act")) {
			act = new Activity();
		}
		if (json.containsKey("user")) {
			user = new UserInfo();
		}
	}
	
	public Activity getAct() {
		return act;
	}
	
	public void setAct(Activity act) {
		this.act = act;
	}
	
	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	} 
}

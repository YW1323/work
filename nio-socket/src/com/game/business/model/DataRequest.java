package com.game.business.model;

import com.alibaba.fastjson.JSONObject;
import com.game.business.model.entitys.Content;
import com.game.business.model.entitys.UserInfo;

/**
 * @ClassName DataRequest
 * @Description 解析请求数据类
 * @author ASUS
 * @Date 2017年7月7日 下午5:49:05
 * @version 1.0.0
 */
public class DataRequest {
	
	private UserInfo user = new UserInfo();
	private Content content = new Content();
	
	public DataRequest encode(String data){
		if(data != null && data.length()>0){
			JSONObject json = JSONObject.parseObject(data);
			if (json.containsKey("user")) {
				user.parseJSON(json.get("user"));
			} 
			if (json.containsKey("content")) {
				content.parseJSON(json.get("content"));
			}
		}
		return this;
	}
	
	public UserInfo getUser() {
		return user;
	}
	
	public void setUser(UserInfo user) {
		this.user = user;
	}
	
	public Content getContent() {
		return content;
	}
	
	public void setContent(Content content) {
		this.content = content;
	}
	
}

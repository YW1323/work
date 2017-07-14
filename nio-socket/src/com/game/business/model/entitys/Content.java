package com.game.business.model.entitys;

import com.alibaba.fastjson.JSONObject;
import com.game.business.model.entitys.base.BaseModel;

/**
 * @ClassName Content
 * @Description 聊天内容
 * @author ASUS
 * @Date 2017年7月14日 上午10:11:49
 * @version 1.0.0
 */
public class Content implements BaseModel {
	
	private static String index_message = "a";
	
	private String message;

	@Override
	public JSONObject buildJSON() {
		return null;
	}

	@Override
	public void parseJSON(Object obj) {
		try {
			JSONObject json = null;
			if (obj instanceof String) {
				json = JSONObject.parseObject((String)obj);
			} else if(obj instanceof JSONObject) {
				json = (JSONObject)obj;
			} else {
				return;
			}
			if (json.size() > 0) {
				this.setMessage(json.containsKey(index_message) ? json.getString(index_message) : "");
			}
		} catch(Throwable e) {
//			LogUtils.log.
		}
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}

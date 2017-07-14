package com.game.business.model.entitys;

import com.alibaba.fastjson.JSONObject;
import com.game.business.model.entitys.base.BaseModel;

/**
 * @ClassName UserInfo
 * @Description 用户信息实体类
 * @author liupei
 * @Date 2017年7月7日 下午5:52:10
 * @version 1.0.0
 */
public class UserInfo implements BaseModel {
	private static String index_user_account = "a";
	private static String index_user_pwd = "b";
	private static String index_user_token = "c";
	private static String index_user_type = "d";
	private static String index_user_server_id = "e";
	
	private String userAccount;
	private String pwd;
	private String token;
	private String type;
	private String serverId;
	
	/**
	 * @Description 对象转换json
	 * @return
	 */
	public JSONObject buildJSON(){
		JSONObject json = new JSONObject();
		json.put(index_user_account, this.getUserAccount());
		json.put(index_user_token, this.getToken());
		json.put(index_user_type, this.getType());
		json.put(index_user_server_id, this.getServerId());
		return json;
	}
	
	/**
	 * @Description json转换对象
	 * @return
	 */
	public void parseJSON(Object obj){
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
				this.setUserAccount(json.containsKey(index_user_account) ? json.getString(index_user_account) : "");
				this.setPwd(json.containsKey(index_user_pwd) ? json.getString(index_user_pwd) : "");
				this.setToken(json.containsKey(index_user_token) ? json.getString(index_user_token) : "");
				this.setType(json.containsKey(index_user_type) ? json.getString(index_user_type) : "");
				this.setServerId(json.containsKey(index_user_server_id) ? json.getString(index_user_server_id) : "");
			}
		} catch(Throwable e) {
//			LogUtils.log.
		}
		
	}
	
	public String getUserAccount() {
		return userAccount;
	}
	
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getServerId() {
		return serverId;
	}
	
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
}

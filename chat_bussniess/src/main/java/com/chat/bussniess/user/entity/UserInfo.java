package com.chat.bussniess.user.entity;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName UserInfo
 * @Description 用户实体
 * @author liupei
 * @Date 2017年8月16日 下午2:40:24
 * @version 1.0.0
 */
public class UserInfo {

	private static String index_uid = "a";
	private static String index_session_id = "b";
	private static String index_role_id = "c";
	private static String index_role_level = "d";

	private String uid;// 用户id
	private String sessionId;// 类型
	private String roleId;// 地址
	private String roleLevel;// icon地址

	public void pareJSON(Object obj) {
		if (obj instanceof JSONObject) {
			this.setUid(((JSONObject) obj).getString(index_uid));
			this.setSessionId(((JSONObject) obj).getString(index_session_id));
			this.setRoleId(((JSONObject) obj).getString(index_role_id));
			this.setRoleLevel(((JSONObject) obj).getString(index_role_level));
		}
	}

	public JSONObject buildJSON() {
		JSONObject json = new JSONObject();
		json.put(index_uid, this.getUid());
		json.put(index_role_id, this.getRoleId());
		json.put(index_role_level, this.getRoleLevel());
		return json;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}
}

package com.chat.bussniess.act.entiy;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName Activity
 * @Description 活动类实体
 * @author ASUS
 * @Date 2017年8月16日 下午2:47:17
 * @version 1.0.0
 */
public class Activity {
	private static String index_title = "a";
	private static String index_type = "b";
	private static String index_url = "c";
	private static String index_icon_url = "d";
	
	private String title;//标题
	private int type;//类型
	private String url;//地址
	private String iconUrl;//icon地址
	
	public void pareJSON(Object obj) {
		if (obj instanceof JSONObject) {
			this.setTitle(((JSONObject) obj).getString(index_title));
			this.setType(((JSONObject) obj).getInteger(index_type));
			this.setUrl(((JSONObject) obj).getString(index_url));
			this.setIconUrl(((JSONObject) obj).getString(index_icon_url));
		}
	}
	
	public JSONObject buildJSON() {
		JSONObject json = new JSONObject();
		json.put(index_title, this.getTitle());
		json.put(index_type, this.getType());
		json.put(index_url, this.getUrl());
		json.put(index_icon_url, this.getIconUrl());
        return json;
    }
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getIconUrl() {
		return iconUrl;
	}
	
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
}

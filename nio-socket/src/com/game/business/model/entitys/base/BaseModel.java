package com.game.business.model.entitys.base;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName BaseModel
 * @Description 实体基类
 * @author ASUS
 * @Date 2017年7月12日 上午9:34:03
 * @version 1.0.0
 */
public interface BaseModel {
	
	/**
	 * @Description 对象转换json
	 * @return
	 */
	JSONObject buildJSON();
	/**
	 * @Description json转换对象
	 * @return
	 */
	void parseJSON(Object obj);
}

package com.game.utils;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通道工具类
 * @author liupei
 *
 */
public class ChannelUtils {
	
	public static Map<String,Channel> channelMap = new ConcurrentHashMap<String,Channel>();
	
	public static Channel getChannel(String key){
		return channelMap.get(key);
	}
	
}

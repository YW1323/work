package com.game.utils;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.game.netty.socket.encode.MsgEncode;

/**
 * 通道工具类
 * @author liupei
 *
 */
public class ChannelUtils {
	
	public static Map<String,Channel> channelMap = new ConcurrentHashMap<String,Channel>();
	
	public static List<Channel> channelList = new ArrayList<Channel>();
	
	public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	public static Channel getChannel(String key){
		if (channelMap.containsKey(key)) {
			/*if () {
				
			}*/
		}
		return null;
	}
	
	/**
	 * @Description 写入消息
	 * @return
	 */
	public static boolean writeByte(Channel channel, String msg){
		if (channel.isRegistered()) {
			channel.write(msg);
			channel.flush();
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @Description 读取消息
	 * @return
	 */
	public static boolean readByte(){
		return true;
	}
	
	/**
	 * @Description 加密消息
	 * @return
	 */
	public static boolean msgEncodeByte(String msg){
		Charset charset = Charset.forName("UTF-8");
		MsgEncode encode = new MsgEncode(charset);
		try {
			encode.acceptOutboundMessage(msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}

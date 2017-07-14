package com.game.netty.socket.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.game.business.handle.ChatHandler;
import com.game.business.task.BusinessTask;
import com.game.utils.ChannelUtils;

/**
 * 具体逻辑修改处理类
 * @author ASUS
 *
 */
public class BuissnessHandlerAdaptor extends ChannelInboundHandlerAdapter {
	
	private Map<String,Object> map = new HashMap<String, Object>();
	
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
    	System.out.println(msg);
    	System.out.println(ctx.channel().remoteAddress());
    	JSONObject json = JSONObject.parseObject((String)msg);
    	ChannelUtils.channelMap.put(json.getJSONObject("user").getString("userAccount"), ctx.channel());
    	ChatHandler chat = new ChatHandler();
    	BusinessTask.addTask(ctx.channel(), chat, (String)msg);
    	ctx.channel().write("ok");  
    	ctx.channel().flush();
    }
    
    @Override  
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {  
        ctx.flush();  
    }

	
	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	public void addLast(String name,Object obj){
		this.map.put(name, obj);
	}
}

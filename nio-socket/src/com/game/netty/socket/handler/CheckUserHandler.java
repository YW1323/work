package com.game.netty.socket.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.game.utils.ChannelUtils;
import com.game.utils.LogUtils;
import com.game.utils.RedisUtils;

/**
 * 验证用户
 * @author ASUS
 *
 */
public class CheckUserHandler extends ChannelInboundHandlerAdapter {
	
	private Map<String,Object> map = new HashMap<String, Object>();
	
	
	// 接收server端的消息，并打印出来  
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
        // 接收并打印客户端的信息  
        LogUtils.log.info("Client said:" + msg);
        //解析协议
        JSONObject json = JSONObject.parseObject((String)msg);
        if( json.containsKey("user") && json.get("user")!=null ){
        	String key = json.getJSONObject("user").getString("a");//账号
        	String sessionId = json.getJSONObject("user").getString("b");
        	Channel channel = ctx.channel();
        	if( key != null && "".equals(key) && sessionId != null && "".equals(sessionId) ){
        		String value = RedisUtils.get(key);
        		if( value!= null && value.equals(sessionId) ){//验证用户
        			if(!ChannelUtils.channelMap.containsKey(key)){
        				ChannelUtils.channelMap.put(key, channel);
        			}
        		}else{
        			channel.close();
        		}
        	}
        }
  
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

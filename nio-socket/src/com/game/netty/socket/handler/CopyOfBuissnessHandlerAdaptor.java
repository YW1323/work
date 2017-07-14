package com.game.netty.socket.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.game.user.handler.base.BaseHandler;
import com.game.utils.LogUtils;

/**
 * 具体逻辑修改处理类
 * @author ASUS
 *
 */
public class CopyOfBuissnessHandlerAdaptor extends ChannelInboundHandlerAdapter {
	
	private Map<String,Object> map = new HashMap<String, Object>();
	
	// 接收server端的消息，并打印出来  
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
        // 接收并打印客户端的信息  
        LogUtils.log.info("Client said:" + msg);
        //解析协议
        JSONObject json = JSONObject.parseObject((String)msg);
        if( json.containsKey("action") && json.get("action")!=null ){
        	String clazzName = json.getString("a");
        	int methodInt = json.getInteger("b");
        	//1 SpringContext getbean
    		//第二种map
        	BaseHandler base = (BaseHandler) map.get(clazzName);
        	if( base != null ){
				Method[] method = base.getClass().getMethods();
				if(method[methodInt].getName().equals("")){
					method[methodInt].invoke(base, new JSONObject());
				}
        	}
			//第三种list
        }else{
        	
        }
//        Channel channel = ctx.channel();
        /*// 向客户端发送消息  
        String response = "I am ok!";  
        // 在当前场景下，发送的数据必须转换成ByteBuf数组  
        ByteBuf encoded = ctx.alloc().buffer(4 * response.length());  
        encoded.writeBytes(response.getBytes());  
        ctx.write(encoded);  
        ctx.flush();  */
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

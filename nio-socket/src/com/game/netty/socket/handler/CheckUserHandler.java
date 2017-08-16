package com.game.netty.socket.handler;

import java.util.logging.Level;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import com.alibaba.fastjson.JSONObject;
import com.game.common.utils.StringUtils;
import com.game.utils.ChannelUtils;
import com.game.utils.LogUtils;
import com.game.utils.RedisUtils;

/**
 * 验证用户
 * @author liupei
 *
 */
public class CheckUserHandler extends ChannelInboundHandlerAdapter {
	
	// 接收server端的消息，并打印出来  
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
        // 接收并打印客户端的信息  
        LogUtils.log.info("Client said:" + msg);
        //解析协议
        JSONObject json = JSONObject.parseObject((String)msg);
        if (json.containsKey("user") && json.get("user") != null) {
        	String key = json.getJSONObject("user").getString("a");//账号
        	String sessionId = json.getJSONObject("user").getString("b");//sessionId
        	//redis存在比对sessionid正确
        	String value = RedisUtils.get(key);
        	if (StringUtils.isNotBlank(value) && value.equals(sessionId)) {
        		Channel channel = ctx.channel();
    			if(!ChannelUtils.channelMap.containsKey(key)){
    				ChannelUtils.channelMap.put(key, channel);
    			}
        	} else {
        		LogUtils.log.info("错误用户验证");
        		ctx.channel().close();
        	}
        } else {
        	LogUtils.log.info("请求数据格式不正确");
        	ctx.channel().close();
        }
    }
    
    @Override  
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {  
        ctx.flush();  
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//    	LogUtils.log.info("CheckUserHandler Exception:"+cause.getMessage());
    	LogUtils.log.log(Level.SEVERE, "CheckUserHandler Exception", cause);
    }
}

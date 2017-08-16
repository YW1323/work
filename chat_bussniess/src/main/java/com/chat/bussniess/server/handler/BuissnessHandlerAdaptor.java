package com.chat.bussniess.server.handler;

import com.chat.bussniess.server.decode.DataRequest;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 具体逻辑修改处理类
 * @author liupei
 *
 */
public class BuissnessHandlerAdaptor extends ChannelInboundHandlerAdapter {
	
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
    	DataRequest dataRequest = (DataRequest) msg;
    	
    }
    
    @Override  
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {  
        ctx.flush();  
    }
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
	}
}

package com.game.netty.socket.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 心跳包
 * @author ASUS
 *
 */
public class Heartbeat extends ChannelDuplexHandler{
	
	@Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception{
        Channel channel = ctx.channel();
        if (evt instanceof IdleStateEvent){
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state()== IdleState.READER_IDLE) {
            	if (channel.isActive()) {
//            		channel.close();
//            		System.out.println("read");
            		channel.writeAndFlush("read");
            	}
            } else if(e.state() == IdleState.WRITER_IDLE){ 
            	try {
//            		ByteBuf buf = ctx.channel().alloc().buffer();
//            		System.out.println("write");
//                	buf.writeBytes(bdata);
            		channel.writeAndFlush("write");
            	} catch (Throwable t) {
            		t.printStackTrace();
            	}
            } else {
            	if (channel.isActive()) {
//            		channel.close();
//            		System.out.println("all");
            		channel.writeAndFlush("all");
            	}
            }
        }
    }
	
}

package com.game.netty.socket.handler;

import java.nio.ByteBuffer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.socket.SocketChannel;
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
            if( e.state()== IdleState.READER_IDLE ){
            	System.out.println("read");
            	System.out.println(channel.isActive());
            	System.out.println(channel.isWritable());
            	try {
            		channel.writeAndFlush("111111read");
            	} catch (Throwable t) {
            		t.printStackTrace();
            	}
//                channel.close();  
            }else if( e.state()== IdleState.WRITER_IDLE ){ 
            	System.out.println("write");
            	System.out.println(channel.isActive());
            	System.out.println(channel.isWritable());
            	try {
            		channel.writeAndFlush("111111read");
            	} catch (Throwable t) {
            		t.printStackTrace();
            	}
//                channel.close();
            }
        }
    }
	
}

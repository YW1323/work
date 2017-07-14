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
        Channel channel=ctx.channel();
        if (evt instanceof IdleStateEvent){
            IdleStateEvent e= (IdleStateEvent) evt;
            if( e.state()== IdleState.READER_IDLE ){
                channel.close();  
            }else if( e.state()== IdleState.WRITER_IDLE ){ 
                channel.close();
            }
        }
    }
	
}

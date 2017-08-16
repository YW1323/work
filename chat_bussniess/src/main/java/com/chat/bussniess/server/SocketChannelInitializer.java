package com.chat.bussniess.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

/**
 * socket服务端 初始化数据通道 
 * @author liupei
 */
public class SocketChannelInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		// 以("|$|")为结尾分割的 解码器
		ByteBuf buf = Unpooled.copiedBuffer("|$|".getBytes("utf-8"));
		channel.pipeline().addLast("framer", new DelimiterBasedFrameDecoder(Integer.MAX_VALUE,buf));
		// 字符串解码
//		channel.pipeline().addLast("handle", new BuissnessHandlerAdaptor());//处理逻辑

	}

}

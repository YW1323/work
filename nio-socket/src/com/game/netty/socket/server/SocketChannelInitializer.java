package com.game.netty.socket.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.nio.charset.Charset;

import com.game.netty.socket.decode.MsgDecode;
import com.game.netty.socket.encode.MsgEncode;
import com.game.netty.socket.handler.BuissnessHandlerAdaptor;
import com.game.netty.socket.handler.Heartbeat;

/**
 * socket服务端 初始化数据通道 
 * @author ASUS
 */
public class SocketChannelInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		// 以("|$|")为结尾分割的 解码器
		ByteBuf buf = Unpooled.copiedBuffer("|$|".getBytes("utf-8"));
		channel.pipeline().addLast("framer", new DelimiterBasedFrameDecoder(1024,buf));
		// 字符串解码
		channel.pipeline().addLast("decoder", new MsgDecode(Charset.forName("utf-8")));
//		channel.pipeline().addLast("validUser", new CheckUserHandler());//验证用户
		channel.pipeline().addLast("timeout", new IdleStateHandler(1, 1, 2));//此两项为添加心跳机制 30秒查看一次在线的客户端channel是否空闲，IdleStateHandler为netty jar包中提供的类  
		channel.pipeline().addLast("hearbeat", new Heartbeat());
		channel.pipeline().addLast("handle", new BuissnessHandlerAdaptor());//处理逻辑
		// 字符串编码
//		channel.pipeline().addLast("encoder", new MsgEncode());
	}

}

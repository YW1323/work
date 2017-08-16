package com.chat.bussniess.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.logging.Level;

import com.chat.bussniess.utils.LogUtils;

public class SocketServer {
	
	private static int port = 8888;
	
	public static void init() {
		EventLoopGroup bossGroup = null;
		EventLoopGroup workerGroup = null;
		try {
			bossGroup = new NioEventLoopGroup();  
			workerGroup = new NioEventLoopGroup();   
            ServerBootstrap b = new ServerBootstrap();  
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)  
                    .childHandler(new SocketChannelInitializer())
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = b.bind(port).sync(); 
			f.channel().closeFuture().sync();
		} catch (Throwable e) {
			LogUtils.log.log(Level.SEVERE, "启动socket服务端报错", e);
		} finally {
			if (bossGroup != null) {
				workerGroup.shutdownGracefully(); 
			}
			if (workerGroup != null) {
				bossGroup.shutdownGracefully(); 
			}
        }  
	}
	
	public static void main(String[] args) {
		init();
	}
}

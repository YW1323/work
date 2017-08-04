package com.game.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.fastjson.JSONObject;
import com.game.netty.socket.utils.DesUtils;
import com.game.netty.socket.utils.ZipUtils;

public class Copy_2_of_SocketChannelDemo {
	public static AtomicInteger n = new AtomicInteger(1);
	
	public  static void startClient()throws Exception{
//		for (int i = 0 ; i < 100; i ++) {
			Thread test = new Thread( new Runnable() {
				
				@Override
				public void run() {
					try {
						String host = "127.0.0.1";
				        int port = Integer.parseInt("8888");
				        EventLoopGroup workerGroup = new NioEventLoopGroup();

				        try {
				            Bootstrap b = new Bootstrap(); // (1)
				            b.group(workerGroup); // (2)
				            b.channel(NioSocketChannel.class); // (3)
				            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
				            b.handler(new ChannelInitializer<SocketChannel>() {
				                @Override
				                public void initChannel(SocketChannel ch) throws Exception {
				                    ch.pipeline().addLast(new TimeClientHandler());
				                }
				            });

				            // Start the client.
				            ChannelFuture f = b.connect(host, port).sync(); // (5)

				            // Wait until the connection is closed.
				            f.channel().closeFuture().sync();
				        } finally {
				            workerGroup.shutdownGracefully();
				        }
					} catch(Throwable e) {
						
					}
				}

			});
			test.start();
//		}
		
	}
	
	public static byte[] addHeader(byte[] b){
		byte[] head = {10,45,35,79,34,67};
		byte[] lastData = new byte[head.length + b.length];
		System.arraycopy(head, 0, lastData, 0, head.length);
		System.arraycopy(b, 0, lastData, head.length, b.length);
		return lastData;
	}

	private static byte[] addTail(byte[] lastData) {
		byte[] lastTailData = null;
		byte[] head;
		try {
			head = "|$|".getBytes("utf-8");
			lastTailData = new byte[head.length + lastData.length];
			System.arraycopy(lastData, 0, lastTailData, 0, lastData.length);
			System.arraycopy(head, 0, lastTailData, lastData.length, head.length);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lastTailData;
	}
	
	private static byte[] addArray(byte[] data, byte[] data2) {
		byte[] lastTailData = new byte[data.length + data2.length];
		System.arraycopy(data, 0, lastTailData, 0, data.length);
		System.arraycopy(data2, 0, lastTailData, data.length, data2.length);
		return lastTailData;
	}
	
	public static void main(String[] args) throws Exception {
		startClient();
	}
	
	/*public static void main(String[] args) {
		System.out.println(addHeader(new byte[1]).length);
	}*/
	
}

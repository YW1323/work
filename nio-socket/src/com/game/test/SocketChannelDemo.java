package com.game.test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.fastjson.JSONObject;

public class SocketChannelDemo {
	public static AtomicInteger n = new AtomicInteger(1);
	
	public  static void startClient()throws Exception{
		for (int i = 0 ; i < 100; i ++) {
			Thread test = new Thread( new Runnable() {
				
				@Override
				public void run() {
					try {
						SocketChannel socketChannel = SocketChannel.open();
						socketChannel.connect(new InetSocketAddress("localhost", 8888));
				//		socketChannel.configureBlocking(false);
						
				//		String request = "|$|";
						JSONObject json = new JSONObject();
						JSONObject user = new JSONObject();
						user.put("userAccount", "lp"+n.getAndIncrement());
						user.put("msg", "你好!");
						json.put("user", user);
						ByteBuffer buf = ByteBuffer.wrap(json.toJSONString().getBytes("UTF-8"));
						socketChannel.write(buf);		
						
					    ByteBuffer rbuf = ByteBuffer.allocate(1024);
					    int size =  socketChannel.read(rbuf);
					    while(size != 1){
					    	rbuf.flip();
					    	Charset charset = Charset.forName("UTF-8");
					    	if (size>0) {
					    		System.out.println(Thread.currentThread().getName()+":"+charset.newDecoder().decode(rbuf));
					    	}
					    	rbuf.clear();
					    	size =  socketChannel.read(rbuf);
					    	Thread.sleep(1000);
					    }
					    buf.clear();
					    rbuf.clear();
						socketChannel.close();
						
						Thread.sleep(50000);
					} catch(Throwable e) {
						
					}
				}
			});
			test.start();
		}
		
	}

	public static void main(String[] args) throws Exception {
		startClient();
	}
}

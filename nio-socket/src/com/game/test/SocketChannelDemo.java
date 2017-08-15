package com.game.test;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.fastjson.JSONObject;
import com.game.netty.socket.utils.DesUtils;
import com.game.netty.socket.utils.ZipUtils;

public class SocketChannelDemo {
	public static AtomicInteger n = new AtomicInteger(1);
	
	public  static void startClient()throws Exception{
//		for (int i = 0 ; i < 100; i ++) {
			Thread test = new Thread( new Runnable() {
				
				@Override
				public void run() {
					try {
						SocketChannel socketChannel = SocketChannel.open();
						socketChannel.connect(new InetSocketAddress("localhost", 8888));
						socketChannel.configureBlocking(false);
						
				//		String request = "|$|";
						JSONObject json = new JSONObject();
						JSONObject user = new JSONObject();
						user.put("userAccount", "lp"+n.getAndIncrement());
						user.put("msg", "你好!");
						json.put("user", user);
						String data = json.toJSONString() + "|$|";
						byte[] bData = ZipUtils.compressByte(json.toJSONString().getBytes("utf-8"), "utf-8");
						byte[] eData = DesUtils.encrypt(bData);
						byte[] lastData = addHeader(eData);
						byte[] lastHeadData = addTail(lastData);
//						byte[] lastLimitHeadData = addArray("|$|".getBytes("utf-8"), lastHeadData);
//						byte[] lastLimitHeadData = addArray(lastHeadData, lastData);
						ByteBuffer buf = ByteBuffer.wrap(lastHeadData);
						socketChannel.write(buf);	
					    ByteBuffer rbuf = ByteBuffer.allocate(100);
					    int size =  socketChannel.read(rbuf);
					    System.out.println(size);
					    while (size != 1) {
					    	rbuf.flip();
					    	if (size > 0) {
					    		/*Charset charset = Charset.forName("UTF-8");
					    		System.out.println(Thread.currentThread().getName()+":"+charset.newDecoder().decode(rbuf));*/
					    		try {
						    		byte[] byteData = new byte[size];
						    		rbuf.get(byteData);
						    		System.out.println(new String(byteData, "utf-8"));
						    		/*
						    		byte[] edata = ZipUtils.uncompressByByte(byteData, "utf-8");
						    		byte[] b = DesUtils.decrypt(edata);
						    		System.out.println(new String(b, "utf-8"));*/
						    		MsgHandler.handle(byteData);
						    		
					    		} catch (Throwable e) {
					    			e.printStackTrace();
					    		}
					    	}
					    	rbuf.clear();
					    	if (size >= 0) {
					    		size =  socketChannel.read(rbuf);
					    	} else {
					    		System.out.println(1);
					    		socketChannel.close();
					    		break;
					    	}
//					    	System.out.println(rbuf.toString());
					    	Thread.sleep(1000);
					    }
					    buf.clear();
					    rbuf.clear();
						socketChannel.close();
						
						Thread.sleep(50000);
					} catch(Throwable e) {
						e.printStackTrace();
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

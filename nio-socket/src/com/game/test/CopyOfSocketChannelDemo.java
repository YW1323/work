package com.game.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.fastjson.JSONObject;
import com.game.netty.socket.utils.DesUtils;
import com.game.netty.socket.utils.ZipUtils;

public class CopyOfSocketChannelDemo {
	public static AtomicInteger n = new AtomicInteger(1);
	
	public  static void startClient()throws Exception{
//		for (int i = 0 ; i < 100; i ++) {
			Thread test = new Thread( new Runnable() {
				
				@Override
				public void run() {
					try {
						 // 1、创建客户端Socket，指定服务器地址和端口
			            // Socket socket=new Socket("127.0.0.1",5200);
			            Socket socket = new Socket("192.168.1.57", 8888);
			            System.out.println("客户端启动成功");
			            // 2、获取输出流，向服务器端发送信息
			            // 向本机的52000端口发出客户请求
			            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			            // 由系统标准输入设备构造BufferedReader对象
			            PrintWriter write = new PrintWriter(socket.getOutputStream());
			            // 由Socket对象得到输出流，并构造PrintWriter对象
			            //3、获取输入流，并读取服务器端的响应信息 
			            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			            // 由Socket对象得到输入流，并构造相应的BufferedReader对象
			            String readline;
			            readline = br.readLine(); // 从系统标准输入读入一字符串
			            while (!readline.equals("end")) {
			                // 若从标准输入读入的字符串为 "end"则停止循环
			                write.println(readline);
			                // 将从系统标准输入读入的字符串输出到Server
			                write.flush();
			                // 刷新输出流，使Server马上收到该字符串
			                System.out.println("Client:" + readline);
			                // 在系统标准输出上打印读入的字符串
			                System.out.println("Server:" + in.readLine());
			                // 从Server读入一字符串，并打印到标准输出上
			                readline = br.readLine(); // 从系统标准输入读入一字符串
			            } // 继续循环
			            //4、关闭资源 
			            write.close(); // 关闭Socket输出流
			            in.close(); // 关闭Socket输入流
			            socket.close(); // 关闭Socket
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

package com.game.test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.game.netty.socket.utils.DesUtils;
import com.game.netty.socket.utils.ZipUtils;

/**
 * @ClassName MsgHandler
 * @Description 消息处理
 * @author ASUS
 * @Date 2017年8月15日 下午4:03:18
 * @version 1.0.0
 */
public class MsgHandler {
	private static String split_str = "|$|";
	
	public static List<String> handle(byte[] data){
		List<String> list = new ArrayList<String>();
		try {
			int index = 0;
			byte[] copyData = Arrays.copyOfRange(data, 0, data.length);
			while (index < (data.length - 3)) {
				byte[] length = Arrays.copyOfRange(copyData, 0, 4);
				int n = bytesToInt(length);
				if (n > 0) {
					byte[] byteData = Arrays.copyOfRange(copyData, 4, 4 + n);
					byte[] edata = ZipUtils.uncompressByByte(byteData, "utf-8");
		    		byte[] b = DesUtils.decrypt(edata);
		    		list.add(new String(b, "utf-8"));
				} 
				index += (4 + n);
				if (index < data.length - 3) copyData = Arrays.copyOfRange(copyData, 4 + n, copyData.length);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void main(String[] args) {
	}
	
	static int bytesToInt(byte[] ary){
		int value;
	    value = (int) ((ary[0]&0xFF)   
	            | ((ary[1]<<8) & 0xFF00)  
	            | ((ary[2]<<16)& 0xFF0000)   
	            | ((ary[3]<<24) & 0xFF000000)); 
	    return value;  
	}
}

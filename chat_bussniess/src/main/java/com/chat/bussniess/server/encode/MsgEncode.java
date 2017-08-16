package com.chat.bussniess.server.encode;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.nio.charset.Charset;
import java.util.List;

import com.chat.bussniess.utils.DesUtils;
import com.chat.bussniess.utils.ZipUtils;

/**
 * 加密/编码
 * @author ASUS
 *
 */
public class MsgEncode extends MessageToMessageEncoder<String>{
	
	private final Charset charset;
	/**
     * Creates a new instance with the current system character set.
     */
    public MsgEncode() {
        this(Charset.defaultCharset());
    }

	/**
     * Creates a new instance with the specified character set.
     */
    public MsgEncode(Charset charset) {
        if (charset == null) {
            throw new NullPointerException("charset");
        }
        this.charset = charset;
    }

	@Override
	protected void encode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
		try {
			if (msg.length() > 0) {
				ByteBuf buf = Unpooled.buffer();
				byte[] resouceData = msg.getBytes(charset.name());
				byte[] b = DesUtils.encrypt(resouceData);
		    	byte[] edata = ZipUtils.compressByte(b, charset.name());
		    	int length = edata.length;
		    	byte[] head = intTobytes(length);
		    	buf.writeBytes(head);
		    	buf.writeBytes(edata);
		    	out.add(buf);
			} 
		} catch (Throwable e) {
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println(cause.getMessage());
	}
	
	static byte[] intTobytes(int value){
		byte[] bdata = new byte[4];
		bdata[3] = (byte) ((value & 0xFF000000)>>24);  
		bdata[2] = (byte) ((value & 0x00FF0000)>>16);  
		bdata[1] = (byte) ((value & 0x0000FF00)>>8);    
		bdata[0] = (byte) ((value & 0x000000FF));   ; 
	    return bdata;  
	}
}

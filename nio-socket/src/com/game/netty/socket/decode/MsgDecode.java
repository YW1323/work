package com.game.netty.socket.decode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;

import com.game.netty.socket.utils.DesUtils;
import com.game.netty.socket.utils.ZipUtils;

/**
 * 解密/解码
 * @author ASUS
 *
 */
public class MsgDecode extends MessageToMessageDecoder<ByteBuf>{
	
    @SuppressWarnings("unused")
	private final Charset charset;

    /**
     * Creates a new instance with the current system character set.
     */
    public MsgDecode() {
        this(Charset.defaultCharset());
    }

	/**
     * Creates a new instance with the specified character set.
     */
    public MsgDecode(Charset charset) {
        if (charset == null) {
            throw new NullPointerException("charset");
        }
        this.charset = charset;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
    	/*OutputStream out = new OutputStream
    	GZIPOutputStream gout = new GZIPOutputStream(out);*/
    	int length = msg.writerIndex();
    	if( length>4 ){
    		byte[] resouceData = new byte[length];
//	    	byte[] resouceData = new byte[length-4];
//	    	msg.skipBytes(4).readBytes(resouceData);
	    	msg.readBytes(resouceData);
//	    	byte[] b = DesUtils.decrypt(resouceData);
//	    	String data = ZipUtils.uncompress(b, "utf-8");
//	    	out.add(data);
	    	out.add(new String(resouceData,"utf-8"));
    	}
    }
    
}

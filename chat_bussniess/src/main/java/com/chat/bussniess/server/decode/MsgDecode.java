package com.chat.bussniess.server.decode;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;
import java.util.logging.Level;

import com.chat.bussniess.utils.LogUtils;
import com.chat.bussniess.utils.StringUtils;

/**
 * 解密/解码
 * @author liupei
 *
 */
public class MsgDecode extends MessageToMessageDecoder<String>{
	
    @SuppressWarnings("unused")
	private final Charset charset;

    public MsgDecode() {
        this(Charset.defaultCharset());
    }

    public MsgDecode(Charset charset) {
        if (charset == null) {
            throw new NullPointerException("charset");
        }
        this.charset = charset;
    }
	
    @Override
    protected void decode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
    	if (StringUtils.isNotBlank(msg)) {
    		DataRequest data = new DataRequest();
    		data.encode(msg);
    		out.add(data);
    	}
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    	LogUtils.log.log(Level.SEVERE, "MsgDecode Exception", cause);
    }
    
}

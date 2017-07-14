package com.game.netty.socket.decode;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.nio.charset.Charset;
import java.util.List;

import com.game.netty.socket.utils.DesUtils;
import com.game.netty.socket.utils.ZipUtils;

/**
 * 加密/编码
 * @author ASUS
 *
 */
public class MsgEncode extends MessageToMessageEncoder<ByteBuf>{
	
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
	protected void encode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
		ByteBuf buf = Unpooled.copiedBuffer("|$|".getBytes("utf-8"));
		byte[] b = DesUtils.encrypt(msg.array());
    	byte[] edata = ZipUtils.compressByte(b, charset.name());
    	String head = "lp";
    	msg.writeBytes(head.getBytes());
    	msg.writeBytes(edata);//写入加密数据
    	msg.writeBytes(buf.array());//写入分隔符
	}

}

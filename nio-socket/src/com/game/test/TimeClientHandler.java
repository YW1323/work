package com.game.test;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class TimeClientHandler extends ChannelInboundHandlerAdapter{
	
	 @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf m = (ByteBuf) msg; // (1)
        try {
            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
        } finally {
            m.release();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
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
	
}

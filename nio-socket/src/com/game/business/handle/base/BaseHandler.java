package com.game.business.handle.base;

import io.netty.channel.Channel;

/**
 * @ClassName BaseHandler
 * @Description 业务处理基类
 * @author liupei
 * @Date 2017年7月6日 上午10:19:14
 * @version 1.0.0
 */
public interface BaseHandler {
	
	public void beforeHandle(Channel channel, String data);
	public void handle(Channel channel, String data);
	public void afterHandle(Channel channel, String data);
	
}

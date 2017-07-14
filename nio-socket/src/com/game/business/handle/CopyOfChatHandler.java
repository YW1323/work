package com.game.business.handle;

import java.util.Map.Entry;
import java.util.Set;

import io.netty.channel.Channel;

import com.game.business.handle.base.BaseHandler;
import com.game.business.model.DataRequest;
import com.game.utils.ChannelUtils;

/**
 * @ClassName ChatHandler
 * @Description 聊天处理类
 * @author ASUS
 * @Date 2017年7月7日 下午4:01:00
 * @version 1.0.0
 */
public class CopyOfChatHandler implements BaseHandler {
	
	/**
	 * 发送前保存，状态发送中
	 */
	@Override
	public void beforeHandle(Channel channel, String data) {
		
	}

	@Override
	public void handle(Channel channel, String data) {
		//解析内容
		DataRequest dataRequest = new DataRequest();
		dataRequest.encode(data);
		String key = dataRequest.getUser().getUserAccount();
		String msgContent = dataRequest.getContent().getMessage();
		if ("ALL".equals(key)) {
			Set<Entry<String, Channel>> set = ChannelUtils.channelMap.entrySet();
			for (Entry<String, Channel> entry : set) {
				Channel writeChannel = entry.getValue();
				writeChannel.write(msgContent);
				writeChannel.flush();
			}
		} else {
			Channel writeChannel = ChannelUtils.getChannel(key);
			writeChannel.write(msgContent);
			writeChannel.flush();
		}
	}
	
	/**
	 * 发送后修改状态，成功：发送完毕，失败：重新发送
	 */
	@Override
	public void afterHandle(Channel channel, String data) {
	}

}

package com.game.business.task;

import io.netty.channel.Channel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.game.business.handle.base.BaseHandler;

/**
 * @ClassName BusinessTask
 * @Description 业务推送相关
 * @author ASUS
 * @Date 2017年7月7日 下午4:12:16
 * @version 1.0.0
 */
public class BusinessTask {
	
	private static ThreadPoolExecutor fixedThreadPool = null;
	private static BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(2048);
	/** 
	 * @Description 初始化
	 */
	public static void init() {
		if (fixedThreadPool == null) {
			fixedThreadPool = new ThreadPoolExecutor(1, Runtime.getRuntime().availableProcessors()*2,
					30, TimeUnit.SECONDS, workQueue);
		}
	}
	/**
	 * @Description 加入任务
	 */
	public static void addTask(Channel channel, BaseHandler handler, String data) {
		if (fixedThreadPool == null) {
			init();
		}
		WorkTask task = new WorkTask(handler, channel, data);
		fixedThreadPool.execute(task);
	}
	
	static class WorkTask extends Thread{
		private BaseHandler handler;
		private Channel channel;
		private String data;
		
		public WorkTask(BaseHandler handler, Channel channel, String data) {
			super();
			this.handler = handler;
			this.channel = channel;
			this.data = data;
		}

		@Override
		public void run() {
			handler.handle(channel, data);
		}

		public BaseHandler getHandler() {
			return handler;
		}

		public void setHandler(BaseHandler handler) {
			this.handler = handler;
		}
	
		public Channel getChannel() {
			return channel;
		}

		public void setChannel(Channel channel) {
			this.channel = channel;
		}

		public String getData() {
			return data;
		}
		
		public void setData(String data) {
			this.data = data;
		}
	}
}

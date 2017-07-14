package com.game.utils;



public class StringUtils {
	
	static class MyThread implements Runnable{

		@Override
		public void run() {
//			int i=100000;
			while(true){
//				i--;
			}
		}
		
	}
	
	public static void main(String[] args) {
		/*System.out.println(1);
		Thread t = new Thread(new MyThread());
		t.start();
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		System.out.println("<br/><b>线程信息</b>");
		System.out.println("<br/>线程数:"+threadMXBean.getThreadCount());
		System.out.println("<br/>死锁线程数:"+threadMXBean.findDeadlockedThreads());*/
		
		/*String regex = "(\\d{4}-\\d{4},*)+";
		Pattern patern = Pattern.compile(regex);
		String str = "08242-4526,0822-4526,0822-4526";
		System.out.println(patern.matcher(str).matches());*/
		
		String str = "0822";
		String str1 = "1230";
		System.out.println(str1.compareTo(str));
	}
}

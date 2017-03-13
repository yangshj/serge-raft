package org.serge.raft;

import org.serge.core.timer.TimerTask;

public class TestAsyncTest {
	
	private static int count = 0;
	
	public static void main(String[] args) {
		
		TimerTask<Void, Void> task = new TimerTask<Void, Void>() {
			@Override
			protected long period() {
				return 1000; // 十秒一次
			}
			@Override
			protected long delay(){
				return 1000; // 十秒一次
			}
			@Override
			protected Void doInBackground(Void... params) throws Throwable {
				System.out.println("随机启动的定时任务isDaemon(): "+Thread.currentThread().isDaemon());
				System.out.println("随机启动的定时任务执行 "+ ++count +"次");
				return null;
			}
		};
		task.execute();
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

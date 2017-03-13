package org.serge.core.timer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseTimer  extends Thread{
	private final static BaseTimer bt;
	private final Object lock = new Object();
	protected final static long Timeout=1000;//60000;
	static{
		bt = new BaseTimer();
		bt.setDaemon(true);
		bt.start();
	}
	//
	private List<TimerTask<?, ?>> list = Collections.synchronizedList(new ArrayList<TimerTask<?,?>>());
	private long timeout=Timeout;//60000;
	protected BaseTimer(){
	}
	public static BaseTimer instance(){
		return bt;
	}
	public static BaseTimer instance(long period){
		BaseTimer b = new BaseTimer();
		b.timeout=period;
		return b;
	}
	@Override
	public void run() {
		try{
			while (true) {
				try {
					synchronized (lock) {
						lock.wait(timeout);
					}
				} catch (InterruptedException e) {
				}
				//
				for(int i=list.size()-1; i>=0; i--){
					TimerTask<?, ?> t = list.get(i);
					long currentTimeMillis = System.currentTimeMillis();
					if(0 == t.lastRunTime){
						t.lastRunTime=currentTimeMillis;
					}
					//
					if(currentTimeMillis>t.getCreateDate()+t.delay()){
						if(0 == t.runTimes){
							t.executeNow();
							if(t.period()>0){
								t.runTimes++;
								t.lastRunTime=currentTimeMillis;
							}else{
								list.remove(i);
							}
						}else if(currentTimeMillis>t.lastRunTime+t.period()){
							t.executeNow();
							t.runTimes++;
							t.lastRunTime=currentTimeMillis;
						}
					}
				}
			}
		}finally{
			run();
		}
	}
	public BaseTimer add(TimerTask<?, ?> task){
		this.list.add(task);
		synchronized (lock) {
			lock.notify();
		}
		return this;
	}
	public BaseTimer remote(TimerTask<?, ?> task){
		this.list.remove(task);
		return this;
	}

}

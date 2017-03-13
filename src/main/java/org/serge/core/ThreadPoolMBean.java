package org.serge.core;

public class ThreadPoolMBean implements IThreadPoolMBean{
	private final IThreadPool pool;

	public ThreadPoolMBean(IThreadPool pool){
		this.pool = pool;
	}
	@Override
	public int getTaskCount() {
		return pool.getTaskCount();
	}

	@Override
	public int getThreadCount() {
		return pool.getThreadCount();
	}
	public String getPoolName(){
		return pool.toString();
	}
	@Override
	public void setMaxWaitingQueue(int maxWaitingCount) {
		this.pool.setMaxWaitingQueue(maxWaitingCount);
	}
}

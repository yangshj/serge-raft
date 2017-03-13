package org.serge.core;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SimpleThreadPool  extends Thread implements IThreadPool{
	//
	private final static int DefaultWorkQueueCount = 100000;
	//
	private final int initThreadCount;
	private final long maxFreeTime;
	private final int maxThreadCount;
	private BlockingQueue<Runnable> workQueue;// = new ArrayBlockingQueue<Runnable>(1000000);
	private ThreadPoolExecutor pool;
	/**
	 * @param maxThreadCount 最大线程数
	 * @param maxFreeTime 最长线程空闲时间
	 */
	public SimpleThreadPool(int maxThreadCount, long maxFreeTime){
		this(maxThreadCount, maxThreadCount, maxFreeTime);
	}
	/**
	 * @param initThreadCount 初始化线程数
	 * @param maxThreadCount 最大线程数
	 * @param maxFreeTime 最长线程空闲时间
	 */
	public SimpleThreadPool(int initThreadCount, int maxThreadCount, long maxFreeTime){
		this.initThreadCount = initThreadCount;
		this.maxFreeTime = maxFreeTime;
		this.maxThreadCount = maxThreadCount;
		this.setDaemon(true);
		//加入管理计划
		ThreadPoolManagerMBean.addManager(this, "BaseThreadPool-"+this.hashCode());
		//
	}
	@Override
	public IThreadPool putTask(AsyncTask<?, ?> task)
			throws WaitingQueueToBigException {
		try {
			task.onPreExecute();
			pool.execute(task);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WaitingQueueToBigException();
		}
		return this;
	}

	@Override
	public int getTaskCount() {
		return workQueue.size();
	}

	@Override
	public int getThreadCount() {
		return pool == null?0:pool.getActiveCount();
	}

	/**
	 * 延时处理
	 */
	@Override
	public void setMaxWaitingQueue(final int maxWaitingCount) {
		if(null == workQueue){
			workQueue = new ArrayBlockingQueue<Runnable>(maxWaitingCount);
		}
	}

	@Override
	public void interrupt() {
		pool.shutdown();
	}

	@Override
	public boolean isInterrupted() {
		return pool.isShutdown();
	}

	@Override
	public void start() {
		if(null == workQueue){
			workQueue = new ArrayBlockingQueue<Runnable>(DefaultWorkQueueCount);
		}
		ThreadFactory tf = new ThreadFactory() {
			int i=1;
			@Override
			public Thread newThread(Runnable r) {
				Thread t = new Thread(r);
				t.setDaemon(true);
				t.setName(this.hashCode()+"AysncTaskPool-"+i++);
				return t;
			}
		};
		pool = new ThreadPoolExecutor(initThreadCount, maxThreadCount, maxFreeTime, TimeUnit.MILLISECONDS, workQueue, tf);//new ArrayBlockingQueue<Runnable>(maxThreadCount));
	}
	
	@Override
	public boolean removeTask(AsyncTask<?, ?> task) {
		return workQueue.remove(task);
	}

}

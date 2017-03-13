package org.serge.core;

public interface IThreadPool {
	static final int DefaultWaitCount = 6000000;
	/**
	 * 插入新的任务
	 * @param task
	 * @return
	 * @throws WaitingQueueToBigException
	 */
	IThreadPool putTask(AsyncTask<?, ?> task) throws WaitingQueueToBigException;
	/**
	 * 移除任务
	 * @param task
	 * @return
	 */
	boolean removeTask(AsyncTask<?, ?> task);
	
	/**
	 * 当前等待队列长度
	 * @return
	 */
	int getTaskCount();

	/**
	 * 当前线程数量
	 * @return
	 */
	int getThreadCount();

	/**
	 * 设置等待队列大小
	 * @param maxWaitingCount
	 */
	void setMaxWaitingQueue(int maxWaitingCount);
	/**
	 * 销毁线程池，不在接受新任务。队列执行完后自动销毁线程池
	 */
	void interrupt();
	/**
	 * 是否已经销毁
	 */
	boolean isInterrupted();
	/**
	 * 启动线程池
	 */
	void start();

}

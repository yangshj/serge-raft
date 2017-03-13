package org.serge.core;

public interface IThreadPoolMBean {
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
}

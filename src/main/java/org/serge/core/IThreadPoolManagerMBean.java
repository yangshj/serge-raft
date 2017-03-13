package org.serge.core;

import java.util.Map;

public interface IThreadPoolManagerMBean {
	public int getPoolCount();
	public Map<String, ThreadPoolMBean> getPools();
	/**
	 * 设置等待队列大小
	 * @param maxWaitingCount
	 */
	void setMaxWaitingQueue(String poolName, int maxWaitingCount);

}

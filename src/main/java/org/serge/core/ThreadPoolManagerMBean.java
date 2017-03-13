package org.serge.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadPoolManagerMBean implements IThreadPoolManagerMBean{
	private static final Map<String, ThreadPoolMBean> map = new ConcurrentHashMap<String, ThreadPoolMBean>();
	public static final void addManager(IThreadPool pool, String name){
		map.put(name, new ThreadPoolMBean(pool));
	}
	@Override
	public int getPoolCount() {
		return map.size();
	}

	@Override
	public Map<String, ThreadPoolMBean> getPools() {
		return map;
	}
	@Override
	public void setMaxWaitingQueue(String poolName, int maxWaitingCount) {
		ThreadPoolMBean poolMBean = map.get(poolName);
		if(null != poolMBean){
			poolMBean.setMaxWaitingQueue(maxWaitingCount);
		}
	}

}

package org.serge.raft.server;

public interface Lifecycle {
	
	/** 启动服务 */
	public void start() throws LifecycleException;
	
	/** 停止服务 */
	public void stop() throws LifecycleException;
	
	/** 初始化 */
	public void initialize() throws LifecycleException;
}

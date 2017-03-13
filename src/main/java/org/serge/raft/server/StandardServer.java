package org.serge.raft.server;

import java.util.List;

import org.serge.raft.node.Node;

public class StandardServer implements Server, Lifecycle, Runnable{
	
	/**  组件是否已经启动  */
	private boolean started = false;
	/** 后台线程 */
	private Thread thread = null;
	/** 后台线程的注册名字 */
	private String threadName = null;

	
	public void start() throws LifecycleException {
		if(started){
			throw new LifecycleException("程序已经启动");
		}
		started = true;
		// 开启后台线程
		threadStart(); 
	}

	public void stop() throws LifecycleException {
		if(!started){
			throw new LifecycleException("程序未启动");
		}
		started = false;
	}

	public List<Node> getOnlineNodes() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	/**
	 * 启动后台处理线程
	 */
	private void threadStart() {
		// 启动后台线程
		thread = new Thread(this, threadName);
		thread.setDaemon(true);
		thread.start();
	}

	public void run() {
		while (started) {
			
		}
	}

	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}

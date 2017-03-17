package org.serge.raft.connector;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.serge.raft.config.RaftConfig;
import org.serge.raft.config.RaftConfigHelper;
import org.serge.raft.server.Lifecycle;
import org.serge.raft.server.LifecycleException;

/**
 * 客户端连接器
 * @author yangshj
 *
 */
public class ClientConnector implements IConnector, Lifecycle, Runnable{
	
	/** 监听端口 */
	private int port;
	/**  组件是否已经启动  */
	private boolean started = false;
	/** 后台线程 */
	private Thread thread = null;
	/** 后台线程的注册名字 */
	private String threadName = null;
	private ServerSocket serverSocket = null;
	
	
	
	/**
	 * 初始化
	 */
	public void initialize() throws LifecycleException {
		RaftConfig config = RaftConfigHelper.getRaft();
		try{
			serverSocket = openSocket(Integer.parseInt(config.getClientPort()),100,config.getIp());		
		} catch(Exception e){
			throw new LifecycleException(e.getMessage());
		}
	}
	
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
			Socket socket = null;
			try {
				socket = serverSocket.accept();
				// 处理客户端请求
				Processer.process(socket);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private ServerSocket openSocket(int port, int backlog, String ip) throws UnknownHostException, IOException{
		return new ServerSocket(port, backlog, InetAddress.getByName(ip));
	}
	
	public void setPort(int port) {
		this.port = port;
	}

	public int getProt() {
		return port;
	}

}

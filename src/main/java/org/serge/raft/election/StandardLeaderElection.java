package org.serge.raft.election;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.serge.raft.config.RaftConfigHelper;
import org.serge.raft.message.HeartBeatRequest;
import org.serge.raft.message.MessageListener;
import org.serge.raft.node.Node;
import org.serge.raft.node.NodeState;
import org.serge.raft.server.Lifecycle;
import org.serge.raft.server.LifecycleException;

public class StandardLeaderElection implements ILeaderElection, Runnable, Lifecycle{
	
	/**  组件是否已经启动  */
	private boolean started = false;
	/** 后台线程 */
	private Thread thread = null;
	/** 后台线程的注册名字 */
	private String threadName = null;
	/** 节点 */
	private Node node = null;
	/** 注册到选举服务中的事件监听器 */
	private List<MessageListener> listeners = null;
	/** 选举超时150ms和300ms之间 */
	private long electionTimeout;
	
	public void initialize() {
		// 初始化事件监听器列表
		this.listeners = new ArrayList<MessageListener>();
		// 初始化节点，默认 Follower
		this.node = new Node(RaftConfigHelper.getRaft());
		this.threadName = "选举线程";
		this.electionTimeout = genElectionTimeout();
	}

	public void start() throws LifecycleException{
		if(started){
			throw new LifecycleException("程序已经启动");
		}
		started = true;
		// 开启后台线程
		threadStart(); 
	}
	
	IHeartBeat hearBeat = new StandardHeartBeat();
	
	public void run(){
		while(started){
			// 领导者 发送心跳包给集群中的节点
			if(node.getState()==NodeState.Leader){
				hearBeat.sendHeartBeat(new HeartBeatRequest());
			}
			// 跟随者  如果followers不能从Leader获取心跳，则变为一个candidate.
			else if(node.getState()==NodeState.Follower){
				
			} 
			// 候选者 参与选举
			else if(node.getState()==NodeState.Candidate){
				
			}
			//然后candidate请求 votes从其他的nodes.
			//Nodes将回复他们的vote.
			//candidate如果收到超过一半的投票数将变为leader
			//After the election timeout the follower becomes a candidate and starts a new election term...
			//...votes for itself......and sends out Request Vote messages to other nodes.
			//If the receiving node hasn't voted yet in this term then it votes for the candidate...
			//...and the node resets its election timeout.
			//Once a candidate has a majority of votes it becomes leader.
			//The leader begins sending out Append Entries messages to its followers.
			//These messages are sent in intervals specified by the heartbeat timeout
			//Followers then respond to each Append Entries message.
			//This election term will continue until a follower stops receiving heartbeats and becomes a candidate
			//If two nodes become candidates at the same time then a split vote can occur
		}
	}
	

	public void stop() throws LifecycleException {
		// TODO Auto-generated method stub
		
	}
	
	public void addListener(MessageListener event) {
		// TODO Auto-generated method stub
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
	
	public static int genElectionTimeout() {
        return new Random().nextInt(150) + 150;
    }
	
	
}

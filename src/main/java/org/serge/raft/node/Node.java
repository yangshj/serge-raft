package org.serge.raft.node;

import org.serge.raft.config.RaftConfig;

/**
 * 节点
 * @author yangshj
 *
 */
public class Node {
	
	/** 节点状态 */
	private NodeState state;
	/** 服务id */
	private String id;
	/** 服务ip */
	private String ip;
	
	public Node(RaftConfig config){
		// 默认Follower
		state = NodeState.Follower;
		id = config.getId();
		ip = config.getIp();
	}

	public NodeState getState() {
		return state;
	}
	
	public void setState(NodeState state) {
		this.state = state;
	}
	
	
}

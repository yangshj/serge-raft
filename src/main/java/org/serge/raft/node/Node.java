package org.serge.raft.node;

/**
 * 节点
 * @author yangshj
 *
 */
public class Node {
	
	//节点状态
	private NodeState state;
	
	
	public Node(){
		state = NodeState.Follower;
	}


	public NodeState getState() {
		return state;
	}
	public void setState(NodeState state) {
		this.state = state;
	}
	
	
}

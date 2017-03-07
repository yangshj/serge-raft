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
	
	
}

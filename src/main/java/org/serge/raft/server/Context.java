package org.serge.raft.server;

import org.serge.raft.node.Node;

/**
 * 上下文
 * @author yangshj
 *
 */
public class Context {
	
	/** 当前服务节点 */
	private Node node;

	
	///////////////////////////////////////
	public Node getNode() {
		return node;
	}
	public void setNode(Node node) {
		this.node = node;
	}
	
}

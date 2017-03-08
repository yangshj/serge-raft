package org.serge.raft.server;

import java.util.List;

import org.serge.raft.node.Node;

/**
 * 服务端
 * @author yangshj
 *
 */
public interface Server {

	/** 获取所有在线状态的节点 */
    public List<Node> getOnlineNodes();
}

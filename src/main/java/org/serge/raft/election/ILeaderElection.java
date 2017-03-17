package org.serge.raft.election;

import org.serge.raft.message.MessageListener;

/**
 * 领导人选举
 * If followers don't hear from a leader then they can become a candidate
 * The candidate then requests votes from other nodes
 * Nodes will reply with their vote
 * The candidate becomes the leader if it gets votes from a majority of nodes
 * This process is called Leader Election.
 * All changes to the system now go through the leader.
 * Each change is added as an entry in the node's log.
 * @author yangshj
 *
 */
public interface ILeaderElection {
	
	/** 添加监听器 */
	public void addListener(MessageListener event);

}

package org.serge.raft.node;


/**
 * 节点状态
 * All our nodes start in the follower state
 * If followers don't hear from a leader then they can become a candidate
 * The candidate then requests votes from other nodes
 * Nodes will reply with their vote
 * The candidate becomes the leader if it gets votes from a majority of nodes
 * This process is called Leader Election.
 * @author lenovo
 *
 */
public enum NodeState {
	 /**领导人*/
    Leader(),//
    /**追随者*/
    Follower(),//
    /**候选人*/
    Candidate(),
}

package org.serge.raft.stateMachine;

/**
 * 状态机
 * Each change is added as an entry in the node's log.
 * This log entry is currently uncommitted so it won't update the node's value.
 * To commit the entry the node first replicates it to the follower nodes...
 * then the leader waits until a majority of nodes have written the entry.
 * The entry is now committed on the leader node and the node state is "5".
 * The leader then notifies the followers that the entry is committed.
 * The cluster has now come to consensus about the system state.
 * This process is called Log Replication
 * @author yangshj
 *
 */
public class StateMachine {

}

package org.serge.raft.election;

/**
 * 事件
 * @author yangshj
 *
 */
public enum MessageType {
	
	/** 心跳事件  */
	HearBeat,
	/** 日志事件 */
	LogEntry,
	/** 选举事件 */
	Election
}

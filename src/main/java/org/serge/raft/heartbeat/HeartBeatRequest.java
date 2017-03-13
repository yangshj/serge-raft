package org.serge.raft.heartbeat;

import org.serge.raft.election.MessageType;

/**
 * 心跳请求
 * @author yangshj
 *
 */
public class HeartBeatRequest {
	
	/** 候选人 ServerID */
	private String serverID = null;
	/** 候选人当前 term 值 */
    private String term = null; 
    /** 消息类型 */
	private MessageType type = MessageType.HearBeat;
	
	
	/////////////////////////////////////////////////
	public String getServerID() {
		return serverID;
	}
	public void setServerID(String serverID) {
		this.serverID = serverID;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	
}
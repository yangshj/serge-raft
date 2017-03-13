package org.serge.raft.election;

/**
 * 投票请求类
 * @author yangshj
 *
 */
public class VoteRequest {
	
	/** 候选人 ServerID */
	private String serverID = null;
	/** 候选人当前 term 值 */
    private String term     = null;
	private MessageType type = MessageType.Election;
	
	
	/////////////////////////////////////////////
	public String getServerID() {
		return serverID;
	}
	public void setServerID(String serverID) {
		this.serverID = serverID;
	}
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	
}

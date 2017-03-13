package org.serge.raft.heartbeat;

/**
 * 心跳响应
 * @author yangshj
 *
 */
public class HeartBeatResponse {
	
	/** 候选人 ServerID */
	private String serverID = null;
	/** 候选人当前 term 值 */
    private String term = null; 
	private boolean reuslt;
	
	
	///////////////////////////////////////////
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
	public boolean isReuslt() {
		return reuslt;
	}
	public void setReuslt(boolean reuslt) {
		this.reuslt = reuslt;
	}
	
}

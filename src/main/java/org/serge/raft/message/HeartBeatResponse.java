package org.serge.raft.message;

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
	private boolean result;
	
	
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
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
}

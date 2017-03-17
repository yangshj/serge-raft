package org.serge.raft.message;

/**
 * 投票响应类
 * @author yangshj
 *
 */
public class VoteResponse {
	
	/** 候选人 ServerID */
	private String serverID = null;
	/** 候选人当前 term 值 */
    private String term = null; 
	/** 投票结果 */
	private boolean result;
	
	
	
	//////////////////////////////////////
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

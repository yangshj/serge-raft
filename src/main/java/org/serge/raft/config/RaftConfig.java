package org.serge.raft.config;

/**
 * 配置文件
 * @author yangshj
 *
 */
public class RaftConfig {
	
	/** 服务ID */
	private String id;
	/** 服务ip */
	private String ip;
	/** 客户端连接服务端的端口  */
	private String clientPort;
	/** 选举用端口 */
	private String electionPort;
	/** 日志数据用端口 */
	private String logPort;
	/** 服务状态 */
	private String state;
	/** 服务列表 */
	private String servers;
	
	///////////////////////////////////////////
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getServers() {
		return servers;
	}
	public void setServers(String servers) {
		this.servers = servers;
	}
	public String getClientPort() {
		return clientPort;
	}
	public void setClientPort(String clientPort) {
		this.clientPort = clientPort;
	}
	public String getElectionPort() {
		return electionPort;
	}
	public void setElectionPort(String electionPort) {
		this.electionPort = electionPort;
	}
	public String getLogPort() {
		return logPort;
	}
	public void setLogPort(String logPort) {
		this.logPort = logPort;
	}
	
}

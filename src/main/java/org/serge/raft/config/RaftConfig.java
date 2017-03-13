package org.serge.raft.config;

/**
 * 配置文件
 * @author yangshj
 *
 */
public class RaftConfig {
	
	private String ip;
	private String clientPort;
	private String state;
	private String id;
	private String servers;
	
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
	
}

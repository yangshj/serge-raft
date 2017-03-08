package org.serge.raft.server;

public interface Service {
	
	public Server getServer();
	
	public void setServer(Server server);
	
	public void initialize();
	
}

package org.serge.raft.election;

import org.serge.raft.message.HeartBeatRequest;
import org.serge.raft.message.HeartBeatResponse;

public class StandardHeartBeat implements IHeartBeat{
	
	/** 心跳超时时间 */
	private long heartbeatTimeout;

	public HeartBeatResponse sendHeartBeat(HeartBeatRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}

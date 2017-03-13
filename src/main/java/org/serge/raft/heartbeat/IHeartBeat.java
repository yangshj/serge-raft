package org.serge.raft.heartbeat;

/**
 * 心跳接口
 * @author yangshj
 *
 */
public interface IHeartBeat {
	
	/** 发送心跳  */
	public HeartBeatResponse sendHeartBeat(HeartBeatRequest request);
}

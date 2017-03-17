package org.serge.raft.election;

import org.serge.raft.message.HeartBeatRequest;
import org.serge.raft.message.HeartBeatResponse;

/**
 * 心跳接口
 * @author yangshj
 *
 */
public interface IHeartBeat {
	
	/** 发送心跳  */
	public HeartBeatResponse sendHeartBeat(HeartBeatRequest request);
}

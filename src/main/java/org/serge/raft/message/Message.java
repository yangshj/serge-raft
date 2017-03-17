package org.serge.raft.message;

/**
 * 消息（心跳，选举，日志）
 * @author yangshj
 *
 */
public class Message {
	
	
	private MessageType type;

	
	
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	
	
}

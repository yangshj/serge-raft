package org.serge.raft.connector;

/**
 * 连接器接口类，负责接收客户端的请求
 * 1、所有的更改都将通过leader节点（非leader将更改转发到leader）
 * 2、每个更改都作为一个Entry被添加到节点的日志。
 * 3、要提交该节点的Entry，首先将其复制到跟随节点
 * 	    然后Leader等待，直到大多数节点已写入Entry。
 * 	  Leader节点提交Entry，然后通知Follower提交Entry。
 * 	    这个过程被称为日志复制。
 * @author yangshj
 *
 */
public interface IConnector {
	
	public void setPort(int redirectPort);
	
	public int getProt();
}

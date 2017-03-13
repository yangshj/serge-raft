package org.serge.raft.connector;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import org.serge.raft.election.LogEntryRequest;
import org.serge.raft.election.VoteRequest;
import org.serge.raft.heartbeat.HeartBeatRequest;
import org.serge.raft.util.HttpUtil;
import org.serge.raft.util.JsonUtil;

/**
 * 处理器负责处理收到的请求
 * @author yangshj
 *
 */
public class Processer {
	
	//处理选举
	//处理心跳
	public static void processElection(Socket socket){
		try {
			InputStream in = socket.getInputStream();
			String data = HttpUtil.parsePostDatat(in, "utf-8");
			VoteRequest message = JsonUtil.fromJson(data, VoteRequest.class);
			HeartBeatRequest message1 = JsonUtil.fromJson(data, HeartBeatRequest.class);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 处理来自客户端的请求
	public static void processClient(Socket socket){
		try {
			InputStream in = socket.getInputStream();
			String data = HttpUtil.parsePostDatat(in, "utf-8");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//处理日志
	public static void processLogEntry(Socket socket){
		try {
			InputStream in = socket.getInputStream();
			String data = HttpUtil.parsePostDatat(in, "utf-8");
			LogEntryRequest message = JsonUtil.fromJson(data, LogEntryRequest.class);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package org.serge.raft.connector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.serge.core.timer.TimerTask;
import org.serge.raft.config.RaftConfigHelper;
import org.serge.raft.message.HeartBeatRequest;
import org.serge.raft.message.HeartBeatResponse;
import org.serge.raft.message.LogEntryRequest;
import org.serge.raft.message.VoteRequest;
import org.serge.raft.message.VoteResponse;
import org.serge.raft.util.HttpUtil;
import org.serge.raft.util.JsonUtil;

/**
 * 处理器负责处理收到的请求
 * @author yangshj
 *
 */
public class Processer {
	
	public static void process(Socket socket){
		// 启动异步任务执行
		TimerTask<Socket, Socket> task = new TimerTask<Socket, Socket>() {
			@Override
			protected Socket doInBackground(Socket... params) throws Throwable {
				Socket socket = params[0];
				InputStream in = socket.getInputStream();
				String data = HttpUtil.parsePostDatat(in, "utf-8");
				// 选举
				if(data.contains("Election")){
					VoteRequest message = JsonUtil.fromJson(data, VoteRequest.class);
					processElection(socket, message);
				}
				// 心跳
				else if(data.contains("HearBeat")){
					HeartBeatRequest message = JsonUtil.fromJson(data, HeartBeatRequest.class);
					processHeart(socket, message);
				}
				// 日志
				else if(data.contains("LogEntry")){
					LogEntryRequest message = JsonUtil.fromJson(data, LogEntryRequest.class);
					processLog(socket, message);
				}
				return null;
			}
		};
		task.execute(socket);
	}
	
	//处理选举
	public static void processElection(Socket socket, VoteRequest message) throws IOException{
		VoteResponse result = new VoteResponse();
		result.setServerID(RaftConfigHelper.getRaft().getId());
		result.setTerm(message.getTerm());
		result.setResult(true);
		String json = JsonUtil.toJson(result);
		OutputStream out = socket.getOutputStream();
		out.write(json.getBytes());
		out.flush();
		out.close();
	}
	
	//处理心跳
	public static void processHeart(Socket socket, HeartBeatRequest message) throws IOException{
		HeartBeatResponse result = new HeartBeatResponse();
		result.setServerID(RaftConfigHelper.getRaft().getId());
		result.setTerm(message.getTerm());
		result.setResult(true);
		String json = JsonUtil.toJson(result);
		OutputStream out = socket.getOutputStream();
		out.write(json.getBytes());
		out.flush();
		out.close();
	}
	
	//处理日志
	public static void processLog(Socket socket, LogEntryRequest message) throws IOException{
		// TODO
		
	}
	
}

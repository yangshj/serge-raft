package org.serge.raft.config;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.serge.raft.util.ReadFileUtil;
import org.serge.raft.util.XmlUtil;

/**
 * 配置文件助手类
 * 获取配置文件raft.xml内容
 * @author yangshj
 */
public class RaftConfigHelper {
	
	
	/**
	 * 读取配置文件raft.xml并返回本机配置对象
	 * @return raft
	 */
	public static RaftConfig getRaft(){
		URL url = Thread.currentThread().getContextClassLoader().getResource("");
		String xmlPath = url.getFile() + Constant.configFileName;
		String xml = ReadFileUtil.readTxtFromFile(xmlPath);
		RaftConfig raft = xmlToRaft(xml);
		return raft;
	}
	
	/**
	 * 读取配置文件raft.xml并返回集群中所有的配置
	 * @return
	 */
	public static List<RaftConfig> getRaftList(){
		List<RaftConfig> ratfList = new ArrayList<RaftConfig>();
		String servers = getRaft().getServers();
		String temp[] = servers.split(",");
		for(int i=0; i<temp.length; i++){
			String t[] = temp[i].split("=");
			RaftConfig raft = new RaftConfig();
			raft.setId(t[0]);
			String ipport[] = t[1].split(":");
			raft.setIp(ipport[0]);
			raft.setElectionPort(ipport[1]);
			raft.setLogPort(ipport[2]);
			ratfList.add(raft);
		}
		return ratfList;
	}
	
	private static RaftConfig xmlToRaft(String xml){
		RaftConfig raft = (RaftConfig) XmlUtil.xmlStrToBean(xml, RaftConfig.class);
		return raft;
	}
	
	public static void main(String[] args) {
		RaftConfig raft = getRaft();
		List<RaftConfig> lists = getRaftList();
		for(RaftConfig config : lists){
			System.out.println(config.getId());
			System.out.println(config.getIp());
			System.out.println(config.getElectionPort());
			System.out.println(config.getLogPort());
		}
		System.out.println(raft.getServers());
	}
}

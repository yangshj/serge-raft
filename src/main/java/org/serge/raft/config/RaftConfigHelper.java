package org.serge.raft.config;

import java.net.URL;

import org.serge.raft.util.ReadFileUtil;
import org.serge.raft.util.XmlUtil;

/**
 * 配置文件助手类
 * 获取配置文件raft.xml内容
 * @author yangshj
 */
public class RaftConfigHelper {
	
	
	/**
	 * 读取配置文件raft.xml并返回对象
	 * @return raft
	 */
	public static RaftConfig getRaft(){
		URL url = Thread.currentThread().getContextClassLoader().getResource("");
		String xmlPath = url.getFile() + Constant.fileName;
		String xml = ReadFileUtil.readTxtFromFile(xmlPath);
		RaftConfig raft = xmlToRaft(xml);
		return raft;
	}
	
	private static RaftConfig xmlToRaft(String xml){
		RaftConfig raft = (RaftConfig) XmlUtil.xmlStrToBean(xml, RaftConfig.class);
		return raft;
	}
	
	public static void main(String[] args) {
		RaftConfig raft = getRaft();
		System.out.println(raft.getServers());
	}
}

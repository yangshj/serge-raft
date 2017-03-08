package org.serge.raft.config;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.serge.raft.util.ReadFileUtil;
import org.serge.raft.util.XmlUtil;

/**
 * 获取配置文件raft.xml内容
 * @author yangshj
 */
public class RaftConfigHelper {
	
	private static final String fileName = "raft.xml";
	
	/**
	 * 读取配置文件raft.xml并返回对象列表
	 * @return
	 */
	public static List<Raft> getRaftList(){
		List<Raft> raftList = new ArrayList<Raft>();
		URL url = Thread.currentThread().getContextClassLoader().getResource("");
		String xmlPath = url.getFile() + fileName;
		String xml = ReadFileUtil.readTxtFromFile(xmlPath);
		// 截掉头尾无用部分
		xml = xml.substring(xml.indexOf("<raft>"), xml.lastIndexOf("</raft>")+7);
		System.out.println(xml);
		while(xml.indexOf("<raft>")>-1){
			String temp = xml.substring(xml.indexOf("<raft>"), xml.indexOf("</raft>")+7);
			Raft raft = xmlToRaft(temp);
			raftList.add(raft);
			xml = xml.substring(xml.indexOf("</raft>")+7, xml.length());
			System.out.println(raft);
		}
		return raftList;
	}
	
	private static Raft xmlToRaft(String xml){
		Raft raft = (Raft) XmlUtil.xmlStrToBean(xml, Raft.class);
		return raft;
	}
	
	public static void main(String[] args) {
		List<Raft> list = getRaftList();
		for(Raft raft : list){
			System.out.println(raft.getIp());
		}
	}
}

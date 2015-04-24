package com.kangdainfo.util.io.configFile;

import java.util.List;
import java.util.ArrayList;


public class Node {

	private String key;
	private List inList;
	private List outList;
	private String value;
	
	public Node() {
		this.inList = new ArrayList();
		this.outList = new ArrayList();
	}

	public List getInList() { return inList; }
	public void setInList(List inList) { this.inList = inList; }
	public List getOutList() { return outList; }
	public void setOutList(List outList) { this.outList = outList; }
	public String getValue() { return value; }
	public void setValue(String value) { this.value = value; }
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	public void addInNode(Node node) {
		inList.add(node);
	}
	public void addOutNode(Node node) {
		outList.add(node);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("key=" + key + ",");
		sb.append("value=" + value + ",");
		for(int i=0; i < inList.size(); i++) {
			Node node = (Node) inList.get(i);
			sb.append("inNode=" + node.getKey() + ",");
		}
		for(int i=0; i < outList.size(); i++) {
			Node node = (Node) outList.get(i);
			sb.append("outNode=" + node.getKey() + ",");
		}
		return sb.toString();
	}

	
}

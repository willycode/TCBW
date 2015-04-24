package com.kangdainfo.util.io.configFile;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Graph {

	private Map nodeMap;
	private Map traversedNodeMap;
	
	public Graph() {
		this.nodeMap = new HashMap();
		this.traversedNodeMap = new HashMap();
	}
	public Node getNode(String key) {
		return (Node) nodeMap.get(key);
	}
	public void putNode(String key, Node node) {
		nodeMap.put(key, node);
	}
	
	public void addTraversedNode(Node node) {
		traversedNodeMap.put(node.getKey(), node);
	}
	public boolean isTraversed(Node node) {
		return traversedNodeMap.containsKey(node.getKey());
	}
	
	public List getTraversedNodes() {
		List list = new ArrayList();
		if(traversedNodeMap.size()==0) {
			return list;
		}
		Object array[] = traversedNodeMap.values().toArray();
		for(int i=0; i < array.length; i++) {
			list.add(array[i]);
		}
		return list;
	}
	
	public Node[] getNodeArray() {
		return (Node[]) nodeMap.values().toArray(new Node[nodeMap.size()]);
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("nodeMap size=" + nodeMap.size() + "\r\n");
		sb.append("traversedNodeMap size=" + traversedNodeMap.size() + "\r\n");
		Node nodes[] = getNodeArray();
		for(int i=0; i < nodes.length; i++) {
			sb.append(nodes[i] + "\r\n");
		}
		return sb.toString();
	}
	 
}

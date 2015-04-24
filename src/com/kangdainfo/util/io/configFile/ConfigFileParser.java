package com.kangdainfo.util.io.configFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.kangdainfo.util.io.ConfigFile;
import com.kangdainfo.util.io.FileIO;
import com.kangdainfo.util.lang.StringUtil;

public class ConfigFileParser {

	public static String NEWLINE = FileIO.NEWLINE;
	
	public static void parse(ConfigFile configFile) throws IOException {
		ConfigFile.Output output = configFile.getOutput();
		ConfigFile.Input input = configFile.getInput();
		boolean isIgnoreHashLine = input.isIgnoreHashLine();
		File files[] = input.getFiles();
		File file = input.getFile();
		String rawContent = input.getRawContent();
		String parsedContent = null;
		
		Set duplicateKeys = output.getDuplicateKeys();
		Properties properties = output.getProperties();
		
		if(files!=null && files.length>0) {
			StringBuffer sb = new StringBuffer();
			for(int i=0; i < files.length; i++) {
				String text = FileIO.toString(files[i]);
				sb.append(text + NEWLINE);
			}
			rawContent = sb.toString();
			input.setRawContent(rawContent);
		}
		else if(file!=null) {
			rawContent = FileIO.toString(file);
			input.setRawContent(rawContent);
		}
		else if(rawContent==null) {
			return;
		}
		
		parsedContent = parse(rawContent, isIgnoreHashLine);
		output.setParsedContent(parsedContent);
		
		String lines[] = StringUtil.splitByNewLine(parsedContent);
		 for(int i=0; i < lines.length; i++) {
			 String array[] = lines[i].split("=");
			 if(array!=null && array.length==2) {
				 String key = array[0];
				 String value = array[1];
				 if(isIgnoreHashLine && key.trim().indexOf('#')!=-1) {
					 continue; 
				 }
				 if(properties.get(key)!=null) {
					 duplicateKeys.add(key);
				 }
				 properties.put(key,value);
			 }
		 }		
	}
	
	
	private static String parse(String content, boolean ignoreHashLine) throws IOException {
		 String lines[] = StringUtil.splitByNewLine(content);
		 Map map = new HashMap();
		 for(int i=0; i < lines.length; i++) {
			 String array[] = lines[i].split("=");
			 if(array!=null && array.length==2) {
				 String key = array[0];
				 String value = array[1];
				 if(ignoreHashLine && key.trim().indexOf('#')!=-1) {
					 continue; 
				 }
				 map.put(key,value);
			 }
		 }
		 ConfigLines configLines = new ConfigLines();
		 String keys[] = (String[]) map.keySet().toArray(new String[map.size()]);
		 for(int i=0; i < keys.length; i++) {
			 String key = (String) keys[i];
			 String value = (String) map.get(key);
			 ConfigLine configLine = new ConfigLine();
			 configLine.setKey(key);
			 configLine.setLine(value);
			 configLine.populate();
			 configLines.addConfigLine(configLine);
		 }
		 
		 
		 String configArray[] = configLines.getConfigValues();
		 for(int i=0; i < configArray.length; i++) {
			 String configValue = configArray[i];
			 if(!map.containsKey((configValue))) {
				 configLines.removeConfigValue(configValue);
				  //cannot resolve this config value, so remove it
			 }
		 }
		 
		 ConfigLine configLineArray[] = configLines.getConfigLineArray();
		 for(int i=0; i < configLineArray.length; i++) {
			 ConfigLine configLine = configLineArray[i];
			 configLines.addConfigValue(configLine.getKey());
		 }
		  
		 Graph graph = createGraph(configLines.getConfigSet());
		 		 
		 configLineArray = configLines.getConfigLineArray();
		 for(int i=0; i < configLineArray.length; i++) {
			 ConfigLine configLine = (ConfigLine) configLineArray[i];
			 Node keyNode = graph.getNode(configLine.getKey());
			 if(configLine.hasConfigValues()) {
				 String configValues[] = configLine.getConfigValues();
				 for(int j=0; j < configValues.length ;j++) {
					 String configValue = configValues[j];
					 Node node = graph.getNode(configValue);
					 keyNode.addInNode(node);
					 node.addOutNode(keyNode);
				 }				 
			 }
			 else {
				 keyNode.setValue(configLine.getLine());
				 graph.addTraversedNode(keyNode);
			 }
		 }

		 
		 List traversedNodeList = graph.getTraversedNodes();
		 for(int i=0; i < traversedNodeList.size(); i++) {
			 traverseGraph(configLines, graph, (Node) traversedNodeList.get(i));
		 }
		 
		 //System.out.println(graph);
		 
		 Node nodes[] = graph.getNodeArray();
		 String newContent = content;
		 for(int i=0; i < nodes.length; i++) {
			 Node node = nodes[i];
			 newContent = ConfigLine.replace(newContent, node.getKey(), node.getValue());
		 }
		 return newContent;
	}
	
	private static Graph createGraph(Set configSet) {
		Graph graph = new Graph();
		Object array[] = configSet.toArray();
		for(int i=0; i < array.length; i++) {
			String configValue = (String) array[i];
			Node node = new Node();
			node.setKey(configValue);
			graph.putNode(configValue, node);
		}
		return graph;
	}
	
	private static void traverseGraph(ConfigLines configLines, Graph graph, Node node) {
		 //System.out.println("traversing node=" + node);
		 
		 List outList = node.getOutList();
		 for(int i=0 ; i < outList.size(); i++) {
			 Node outNode = (Node) outList.get(i);
			 if(graph.isTraversed(outNode)) {
				 continue;
			 }
			 List inList = outNode.getInList();
			 if(hasNullValueNode(inList)) {
				 continue;
			 }
			 ConfigLine configLine = configLines.getConfigLine(outNode.getKey());
			 String line = configLine.getLine();
			 for(int j=0; j < inList.size(); j++) {
				 Node inNode = (Node) inList.get(j);
				 line = ConfigLine.replace(line, inNode.getKey(), inNode.getValue());
			 }
			 outNode.setValue(line);
			 graph.addTraversedNode(outNode);
			 traverseGraph(configLines,graph,outNode);
		 }
	}
	
	private static boolean hasNullValueNode(List inList) {
		 for(int i=0; i < inList.size(); i++) {
			 Node inNode = (Node) inList.get(i);
			 if(inNode.getValue()==null) {
				 return true;
			 }
		 }
		 return false;
	}
	
	
}

package com.kangdainfo.util.io;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import com.kangdainfo.util.io.configFile.ConfigFileParser;


public class ConfigFile {

	private Input input;
	private Output output;
	
	public Input getInput() { return input; }
	public void setInput(Input input) { this.input = input; }
	public Output getOutput() { return output; }
	public void setOutput(Output output) { this.output = output; }
	
	public class Input {

		private File files[];
		private File file;
		private String rawContent;		
		private boolean ignoreHashLine;
		
		private Input() {
		}
		
		public File getFile() { return file; }
		public void setFile(File file) { this.file = file; }
		public File[] getFiles() { return files; }
		public void setFiles(File[] files) { this.files = files; }
		public boolean isIgnoreHashLine() { return ignoreHashLine; }
		public void setIgnoreHashLine(boolean ignoreHashLine) { this.ignoreHashLine = ignoreHashLine; }
		public String getRawContent() { return rawContent; }
		public void setRawContent(String rawContent) { this.rawContent = rawContent; }
		
	}

	public class Output {
		private String parsedContent;
		private Properties properties;
		private Set duplicateKeys;
		
		private Output () {
			properties = new Properties();
			duplicateKeys = new TreeSet();
		}
		
		public Set getDuplicateKeys() { return duplicateKeys; }
		public void setDuplicateKeys(Set duplicateKeys) { this.duplicateKeys = duplicateKeys; }

		public String getParsedContent() { return parsedContent; }
		public void setParsedContent(String parsedContent) { this.parsedContent = parsedContent; }
		public Properties getProperties() { return properties; }
		public void setProperties(Properties properties) { this.properties = properties; }		
	}

	
	private ConfigFile() {
		input = new Input();
		output = new Output();
	}
	
	public static ConfigFile getInstance() {
		ConfigFile configFile = new ConfigFile();
		return configFile;
	}
	
	public void parse() throws IOException {
		ConfigFileParser.parse(this);	
	}




	
	
	
}

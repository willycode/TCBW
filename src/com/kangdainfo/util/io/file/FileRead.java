package com.kangdainfo.util.io.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import com.kangdainfo.util.io.ConfigFile;
import com.kangdainfo.util.io.FileIO;
import com.kangdainfo.util.io.FileReadOption;
import com.kangdainfo.util.lang.StringUtil;

public class FileRead {
	
	private static final int BLKSIZ = FileIO.BLKSIZ;	
	
	public static String toString(Reader reader, boolean closeReader) throws IOException {
	    StringBuffer sb = new StringBuffer();
	    char[] b = new char[BLKSIZ];
	    int n;
	
	    while ((n = reader.read(b)) > 0) {
	        sb.append(b, 0, n);
	    }
	    if(closeReader) {
	    	reader.close();
	    }

	    return sb.toString();
	}
    public static String toString(File file) throws IOException {
    	return toString(new FileReader(file),true);
    }
    public static String toString(InputStream is) throws IOException {
    	return toString(new InputStreamReader(is),true);
    }

	    
    public static String toString(String fileName, FileReadOption fileReadOption) throws IOException {
    	
    	if(fileReadOption==null) {
    		return toString(new File(fileName));
    	}

    	String content = null;
    	Boolean isIsLoadByClasspath = fileReadOption.getIsLoadByClasspath();
    	if(isIsLoadByClasspath!=null && isIsLoadByClasspath.booleanValue()==true) {
    		InputStream is = null;
    		try{
        	is = fileName.getClass().getClassLoader().getResourceAsStream(fileName);
        	content = toString(is);
    		}catch(IOException ioe){
    			throw ioe;
    		}finally{
    			if(is!=null)is.close();
    		}
    	}
    	else {
    		content = toString(new File(fileName));
    	}
    	
    	if(fileReadOption.getIsConfigurationFile().booleanValue()==true) {
    		Boolean isIgnoreHashLine = fileReadOption.getIsIgnoreHashLine();
    		ConfigFile configFile = ConfigFile.getInstance();
  		   	configFile.getInput().setRawContent(content);
  		   
    		if(isIgnoreHashLine!=null && isIgnoreHashLine.booleanValue()==true) {
      		   	configFile.getInput().setIgnoreHashLine(true);   			
    		}
    		else {
      		   	configFile.getInput().setIgnoreHashLine(false);   			
    		}
  		   	configFile.parse();
  		   	content = configFile.getOutput().getParsedContent();
    	}
    	return content;
    }

	public static String toString(File file, String encoding) throws IOException {
		StringBuffer sb = new StringBuffer();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			sb.append(toString(fis, encoding));
		} catch (IOException e) {
			throw e;
		} finally {
			if (fis != null)
				fis.close();
		}
		return sb.toString();
	}
	
	public static String toString(InputStream is, String encoding) throws IOException {
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is,
					encoding));
			int count = 0;
			int size = 2048;
			char cc[] = new char[size];
			while ((count = br.read(cc)) > -1) {
				sb.append(new String(cc, 0, count));
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (is != null)
				is.close();
		}
		return sb.toString();
	}	
	public static String[] toLines(File file, String encoding) throws IOException {
		String text = toString(file,encoding);
		text = text.replaceAll("\r\n", "\n");
		text = text.replaceAll("\r",  "\n");
		return text.split("\n");
	}
	
    public static String[] toLines(File file) throws IOException {
    	FileReader reader = new FileReader(file);
    	BufferedReader in = new BufferedReader(reader);
    	String line = null;
    	ArrayList list = new ArrayList();
    	while((line=in.readLine())!=null) {
    		list.add(line);
    	}
    	in.close();
    	reader.close();
    	return (String[]) list.toArray(new String[list.size()]);
    }
    
    public static String[] toLines(String text) throws IOException {
    	if(text==null) {
    		return null;
    	}
    	StringReader sReader = new StringReader(text);
    	LineNumberReader lReader = new LineNumberReader(sReader);
    	String line = null;
    	ArrayList list = new ArrayList();
    	while((line=lReader.readLine())!=null) {
    		list.add(line);
    	}
    	lReader.close();
    	sReader.close();
    	return (String[]) list.toArray(new String[list.size()]);    	
    }
    
    
	public static String getCurrentWorkingDirectory() {
		return (new File(".")).getAbsolutePath();
	}
	
    public static byte[] filetoBytes(File inputFile) throws IOException {
    	return FileUtils.readFileToByteArray(inputFile);
    }
    
}

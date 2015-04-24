package com.kangdainfo.util.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SimpleLogger {
	
	private BufferedWriter out;
	private File logFile;
	private int numberOfCharactersInBufferedWriter;
	private static final int BUF_SIZE = 4096;
	
	public SimpleLogger(File logFile) {
		this.logFile = logFile;
	}
	
	public void open()  {
		try {
			if(logFile.exists()) {
				logFile.delete();
			}
			FileWriter fileWriter = new FileWriter(logFile,true);
			out = new BufferedWriter(fileWriter);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	public void info(String s) {
		write(s);
	}
	public void infoln(String s) {
		info(s+"\r\n");
	}
	public void info_c(String s) {
		write(s);
		System.out.print(s);	
	}
	public void infoln_c(String s) {
		info_c(s+"\r\n");
	}
	
	public void write(String s) {
		try {
			numberOfCharactersInBufferedWriter += s.length();
			out.write(s);
			if(numberOfCharactersInBufferedWriter>BUF_SIZE) {
				flush();
				numberOfCharactersInBufferedWriter = 0;
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}		
	}
	
	public void flush() {
		try {
			out.flush();
		} catch (IOException e) {
			
			e.printStackTrace();
		}		
	}
	public void close()  {
		try {
			out.flush();
			out.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	

}

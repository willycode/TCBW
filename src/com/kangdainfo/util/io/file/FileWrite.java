package com.kangdainfo.util.io.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.kangdainfo.util.io.FileWriteOption;

public class FileWrite {

    public static void bytesToFile(byte bytes[], File outputFile) throws IOException {
    	FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
    	fileOutputStream.write(bytes);
    	fileOutputStream.flush();
    	fileOutputStream.close();
	}
    
    
    public static void stringToFile(String text, String outFilePath) throws IOException {
    	stringToFile(text,new File(outFilePath));
    }
    public static void stringToFile(String text, File outputFile) throws IOException {
    	FileWriteOption fileWriteOption = new FileWriteOption();
    	fileWriteOption.setIsAppend(Boolean.FALSE);
    	stringToFile(text,outputFile,fileWriteOption);
	}
    public static void stringToFile(String text, File outputFile, FileWriteOption fileWriteOption) throws IOException {
    	write(text,outputFile,fileWriteOption);
    }
    
    private static void write(String text, File outputFile, FileWriteOption fileWriteOption) throws IOException  {
    	boolean append = fileWriteOption.getIsAppend().booleanValue();
    	FileWriter writer = new FileWriter(outputFile,append);
    	BufferedWriter os = new BufferedWriter(writer);
		os.write(text);
		os.flush();
		os.close();
		writer.close();
    }
	
	
    
}

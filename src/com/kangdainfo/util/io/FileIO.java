package com.kangdainfo.util.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import com.kangdainfo.util.io.file.FileCopy;
import com.kangdainfo.util.io.file.FileRead;
import com.kangdainfo.util.io.file.FileWrite;

public class FileIO {

	public static final String NEWLINE = "\r\n";

	public static final int BLKSIZ = 1024 * 16;

    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }
    
	public static java.util.List<File> getFileList(File dir, java.io.FileFilter fileFilter, java.util.List<File> fileList) throws IOException {
		if (dir!=null && dir.exists()) {
			if (fileList==null) fileList = new java.util.ArrayList<File>();
	        if (dir.isDirectory()) {
	        	File[] children = fileFilter!=null?dir.listFiles(fileFilter):dir.listFiles();
	            for (int i = 0; i < children.length; i++) {
	            	getFileList(children[i], fileFilter, fileList);
	            }
	        } else {
	        	fileList.add(dir);
	        }
		}
		return fileList;
	}
	
	public static void copyFile(File inFile, File outFile) throws IOException {
		FileCopy.copyFile(inFile, outFile);
	}

	public static void copyFile(String inFilePath, String outFilePath)
			throws IOException {
		FileCopy.copyFile(inFilePath, outFilePath);
	}

	public static void copyFile(InputStream is, OutputStream os)
			throws IOException {
		FileCopy.copyFile(is, os);
	}

	public static void copyFile(InputStream is, OutputStream os,
			boolean closeIs, boolean closeOs) throws IOException {
		FileCopy.copyFile(is, os, closeIs, closeOs);
	}

	public static void copyFile(Reader is, Writer os) throws IOException {
		FileCopy.copyFile(is, os);
	}

	public static void copyFile(Reader reader, Writer writer,
			boolean closeReader, boolean closeWriter) throws IOException {
		FileCopy.copyFile(reader, writer, closeReader, closeWriter);
	}

	public static void copyFile(String inFilePath, PrintWriter pw,
			boolean closePw) throws IOException {
		FileCopy.copyFile(inFilePath, pw, closePw);
	}

	public static void copyFileBuffered(String inFilePath, String outFilePath)
			throws IOException {
		FileCopy.copyFileBuffered(inFilePath, outFilePath);
	}

	public static String toString(Reader reader, boolean closeReader)
			throws IOException {
		return FileRead.toString(reader, closeReader);
	}

	public static String toString(File file) throws IOException {
		return FileRead.toString(file);
	}

	public static String toString(InputStream is) throws IOException {
		return FileRead.toString(is);
	}

	public static String toString(String fileName, FileReadOption fileReadOption)
			throws IOException {
		return FileRead.toString(fileName, fileReadOption);
	}

	public static String[] toLines(File file) throws IOException {
		return FileRead.toLines(file);
	}

	public static String[] toLines(String text) throws IOException {
		return FileRead.toLines(text);
	}

	public static String getCurrentWorkingDirectory() {
		return FileRead.getCurrentWorkingDirectory();
	}

	public static String toString(File file, String encoding)
			throws IOException {
		return FileRead.toString(file, encoding);
	}

	public static String toString(InputStream is, String encoding)
			throws IOException {
		return FileRead.toString(is, encoding);
	}

	public static String[] toLines(File file, String encoding)
			throws IOException {
		return FileRead.toLines(file, encoding);
	}

	/**
	 * toProperties
	 * 
	 * @param file
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	public static Properties toProperties(File file, String encoding)
			throws IOException {
		String array[] = FileIO.toLines(file, encoding);
		return toProperties(array, encoding);
	}

	/**
	 * toProperties
	 * 
	 * @param is
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	public static Properties toProperties(InputStream is, String encoding)
			throws IOException {
		String ss = FileIO.toString(is, encoding);
		String array[] = ss.split("\r\n");
		return toProperties(array, encoding);
	}

	private static Properties toProperties(String array[], String encoding)
			throws IOException {
		Properties prop = new Properties();
		for (int i = 0, max = array.length; i < max; i++) {
			String ss = array[i];
			if (ss == null || ss.trim().length() == 0)
				continue;
			if (!ss.startsWith("#")) {
				int pos = ss.indexOf("=");
				if (pos > -1) {
					String key = ss.substring(0, pos);
					String value = ss.substring(pos + 1);
					prop.setProperty(key.trim(), value.trim());
				}
			}
		}
		return prop;
	}

    public static void bytesToFile(byte bytes[], File outputFile) throws IOException {
    	FileWrite.bytesToFile(bytes, outputFile);
    }
    public static byte[] filetoBytes(File inputFile) throws IOException {
    	return FileRead.filetoBytes(inputFile);
    }
    
	public static void stringToFile(String text, String outFilePath)
			throws IOException {
		FileWrite.stringToFile(text, outFilePath);
	}

	public static void stringToFile(String text, File outputFile)
			throws IOException {
		FileWrite.stringToFile(text, outputFile);
	}

	public static void stringToFile(String text, File outputFile,
			FileWriteOption fileWriteOption) throws IOException {
		FileWrite.stringToFile(text, outputFile, fileWriteOption);
	}
	
	public static void delete(File fileOrDirectory) throws IOException {
		FileUtils.forceDelete(fileOrDirectory);
		// FileDelete.delete(fileOrDirectory);
	}

}

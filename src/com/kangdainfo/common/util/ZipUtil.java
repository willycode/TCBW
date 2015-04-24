package com.kangdainfo.common.util;

import java.io.*;
import org.apache.tools.zip.ZipOutputStream;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipEntry;

/**
 * 在網路上併驟來的ZipUtil<br>
 * 主要修改的部份是讓它可以支援中文檔名的壓縮及解壓縮
 * 
 * @author shark
 *
 */
public class ZipUtil {
	
	static final int BUFFER = 2048;
	private String filePath;
	
	public ZipUtil() {}
	
	/**
	 * 壓縮檔案<br>
	 * e.g.<br>
	 * zipFile(new java.io.File("d:\\temp\\tempFolder",new java.io.File("d:\\temp\\tempFolder.zip"));
	 * @param source = 來源檔案或目錄
	 * @param destination = 目的壓縮檔
	 */
	public void zipFile(File source, File destination) {
		if (null == source)
			throw new IllegalArgumentException("source can't be null.");
		if (null == destination)
			throw new IllegalArgumentException("destination can't be null.");		
		try {
			org.apache.tools.zip.ZipOutputStream out = new ZipOutputStream(new FileOutputStream(destination));
			filePath = source.getAbsolutePath() + File.separator;			
			makeZip(source,out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}		

	private void makeZip(File fromFile, org.apache.tools.zip.ZipOutputStream out) {
		try {
			File[] fileList = fromFile.listFiles();
			for (int i = 0; i < fileList.length; i++) {
				File _f = fileList[i];
				if (_f.isDirectory()) {
					makeZip(_f, out);
				} else {
					String path = _f.getAbsolutePath();										
					path = getAbsolutePath(path);
					writeEntry(path, _f, out);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeEntry(String EntryName, File file, ZipOutputStream out2)
			throws Exception {
		org.apache.tools.zip.ZipEntry entry = new org.apache.tools.zip.ZipEntry(
				EntryName);
		out2.putNextEntry(entry);
		FileInputStream fi = null;
		try{
		fi = new FileInputStream(file);
		BufferedInputStream origin = null;
		origin = new BufferedInputStream(fi, BUFFER);

		int count;
		byte data[] = new byte[BUFFER];
		while ((count = origin.read(data, 0, BUFFER)) != -1) {
			out2.write(data, 0, count);
		}
		origin.close();
		}catch(IOException ioe){
			throw ioe;
		}finally{
			fi.close();
		}
	}

	private String getAbsolutePath(String strPath) {
		return strPath.substring(filePath.length(), strPath.length());
	}

	/**
	 * 解壓檔案
	 * @param source = 要解壓縮的來源zip檔
	 * @param destination = 解開後的文件存放位置
	 * @throws Exception
	 */
	public void unzipFile(File source, File destination)
			throws Exception {
		if (null == source)
			throw new IllegalArgumentException("source can't be null.");
		if (null == destination)
			throw new IllegalArgumentException("destination can't be null.");
		ZipFile zip_file = null;
		java.util.Enumeration entries = null;
		try {
			zip_file = new ZipFile(source);
		} catch (IOException e) {
			throw new Exception("Error while creating the zipfile '"
					+ source.getAbsolutePath() + "'.", e);
		}
		entries = zip_file.getEntries();

		while (entries.hasMoreElements()) {
			ZipEntry entry = null;
			InputStream input_stream = null;
			String output_filename = null;
			File output_file = null;
			StringBuilder output_file_directoryname = null;
			File output_file_directory = null;
			FileOutputStream file_output_stream = null;
			byte[] buffer = new byte[1024];
			int return_value = -1;

			entry = (ZipEntry) entries.nextElement();
			try {
				input_stream = zip_file.getInputStream(entry);
			} catch (IOException e) {
				throw new Exception(
						"Error while obtaining the inputstream for entry '"
								+ entry.getName() + "'.", e);
			}

			output_filename = destination.getAbsolutePath() + File.separator
					+ entry.getName().replace('/', File.separatorChar);
			output_file = new File(output_filename);
			output_file_directoryname = new StringBuilder(output_file.getPath());
			output_file_directoryname.setLength(output_file_directoryname
					.length()
					- output_file.getName().length() - File.separator.length());
			output_file_directory = new File(output_file_directoryname
					.toString());
			if (!output_file_directory.exists()) {
				if (!output_file_directory.mkdirs()) {
					throw new Exception("Couldn't create directory '"
							+ output_file_directory.getAbsolutePath()
							+ "' and its parents.");
				}
			} else {
				if (!output_file_directory.isDirectory()) {
					throw new Exception("Destination '"
							+ output_file_directory.getAbsolutePath()
							+ "' exists and is not a directory.");
				}
			}

			try {
				file_output_stream = new FileOutputStream(output_filename);
			} catch (IOException e) {
				throw new Exception(
						"Error while creating the output stream for file '"
								+ output_filename + "'.", e);
			}

			try {
				return_value = input_stream.read(buffer);

				while (-1 != return_value) {
					file_output_stream.write(buffer, 0, return_value);
					return_value = input_stream.read(buffer);
				}
			} catch (IOException e) {
				throw new Exception("Error while uncompressing entry '"
						+ output_filename + "'.", e);
			}

			try {
				file_output_stream.close();
			} catch (IOException e) {
				throw new Exception(
						"Error while closing the output stream for file '"
								+ output_filename + "'.", e);
			}
			try {
				input_stream.close();
			} catch (IOException e) {
				throw new Exception(
						"Error while closing the input stream for entry '"
								+ entry.getName() + "'.", e);
			}
		}

		try {
			zip_file.close();
		} catch (IOException e) {
			throw new Exception("Error while closing the zipfile '"
					+ source.getAbsolutePath() + "'.", e);
		}
	}

	public static void main(String[] args) {
//		File fromFile = new File("d:\\temp\\sn"); // 來源路徑
//		File toFile = new File("d:\\temp\\壓縮文件.zip"); // 存放路徑
//		ZipUtil zip = new ZipUtil();	
//		try {
//			zip.zipFile(fromFile, toFile);			
//			zip.unzipFile(toFile,  new File("d:\\temp\\gy"));
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}

	}

}

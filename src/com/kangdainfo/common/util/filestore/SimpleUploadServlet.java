package com.kangdainfo.common.util.filestore;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class SimpleUploadServlet extends HttpServlet implements Servlet {
	
	private static final long serialVersionUID = 1L;


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4096);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(10000000);
		
		List fileItems = null;
		try {
			fileItems = upload.parseRequest(request);
			Iterator i = fileItems.iterator();
			while (i.hasNext()) {
				FileItem fi = (FileItem)i.next();
				String fileName = getFilename(fi.getName()); // fi.getName()得到的是包含路徑的檔名
				File f = new File("/upload/" + fileName);
				try {
					fi.write(f); // 將檔案寫到磁碟
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}


	}
	
	
	/**
	 * 將路徑過濾掉，只傳回檔名
	 */
	public String getFilename(String fullname) {
		String filename = null;

		fullname = fullname.replace('\\', '/' );
		StringTokenizer token = new StringTokenizer(fullname, "/");
		while (token.hasMoreTokens()) {
			filename = token.nextToken();
		}
		return filename;
	}

}

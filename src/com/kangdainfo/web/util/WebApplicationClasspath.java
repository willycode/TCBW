package com.kangdainfo.web.util;

import java.io.File;
import java.io.FileFilter;

import javax.servlet.ServletContext;

import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.lang.SystemUtils;
import org.apache.log4j.Logger;

import com.kangdainfo.web.listener.MyServletContext;

public class WebApplicationClasspath {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	public String getClassPath() {
		ServletContext servletContext = MyServletContext.getInstance().getServletContext();
		String realPath = servletContext.getRealPath("/");
		String web_inf_dir = realPath + "WEB-INF/";
		String lib_dir_path = web_inf_dir + "lib/";
		String classes_dir_path = web_inf_dir + "classes/";
		String applicationContext_dir_path  = web_inf_dir + "applicationContext/tast/modules/";
		
		File lib_dir = new File(lib_dir_path);
		File classes_dir = new File(classes_dir_path);
		File applicationContext_dir = new File(applicationContext_dir_path);
		
		String suffix_lib[] = new String[2];
		suffix_lib[0]=".jar";
		suffix_lib[1]=".zip";
		
		File lib_dir_files[] = lib_dir.listFiles((FileFilter) new SuffixFileFilter(suffix_lib));
		
		StringBuffer classpathSb = new StringBuffer();
		
		String CP_DELIMITER = SystemUtils.PATH_SEPARATOR;
		String FILE_SEPARATOR = SystemUtils.FILE_SEPARATOR;
		
		//classpathSb.append(classes_dir.getAbsolutePath()+FILE_SEPARATOR+"log4j.properties"+CP_DELIMITER);
		classpathSb.append(classes_dir.getAbsolutePath()+CP_DELIMITER);
		for(int i=0,max_i=lib_dir_files.length; i < max_i; i++) {
			classpathSb.append(lib_dir_files[i].getAbsolutePath()+CP_DELIMITER);
		}
		classpathSb.append(applicationContext_dir.getAbsolutePath()+CP_DELIMITER);
		
		logger.info(classpathSb.toString().replaceAll(CP_DELIMITER, CP_DELIMITER+"\r\n"));
		
		return classpathSb.toString();
	}
}

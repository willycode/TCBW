package com.kangdainfo.web.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.kangdainfo.common.model.bo.CommonVersion;
import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.View;
import com.kangdainfo.persistence.PersistenceServiceGetter;
import com.kangdainfo.web.util.MySpringWebApplicationContext;

public class MySpringContextLoaderListener extends ContextLoaderListener {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		MyServletContext.getInstance().setServletContext(event.getServletContext());
		
		MySpringWebApplicationContext.defaultWebApplicationContext = 
			WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		
		String isDelete = event.getServletContext().getInitParameter("deleteTempFilesOnStartup");
		if (isDelete!=null && (isDelete.equals("true") || isDelete.equals("Y"))) {			
			java.io.File dir = new java.io.File(System.getProperty("java.io.tmpdir"));
	        if (dir.isDirectory() && dir.getParentFile()!=null) {
	            String[] children = dir.list();
	            for (int i=0; i<children.length; i++) {
	            	File f = new File(dir, children[i]);
	            	f.delete();
	            }
	            String msg = "Clear " + System.getProperty("java.io.tmpdir") + " : " + children.length + " files deleted.";
	            System.out.println(msg);
	            logger.info(msg);
	        }
			java.io.File filestoreLocation = new java.io.File(event.getServletContext().getInitParameter("filestoreLocation"));
			if (!filestoreLocation.exists() || !filestoreLocation.isDirectory()) filestoreLocation.mkdirs();	        
		}
		

		String hql = " from " + CommonVersion.class.getCanonicalName() + " where id=1 and bitYN='Y' ";
		CommonVersion obj = (CommonVersion) View.getObject(hql);
		if (obj==null) {			
			String dbProductName = "";
			String sPath = "";
			java.sql.Connection conn = null;
			try {				
				conn = PersistenceServiceGetter.getInstance().getDataSource().getConnection();
				dbProductName = conn.getMetaData().getDatabaseProductName();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(conn != null){
					try{
						conn.close();
					}catch(SQLException sqle){}
				}
			}
			if (Common.get(dbProductName).toLowerCase().indexOf("oracle")!=-1) sPath = event.getServletContext().getRealPath("/sys/ap/minimum_ora.sql");
			else sPath = event.getServletContext().getRealPath("/sys/ap/minimum.sql");
			try {
				java.io.InputStream fin = new java.io.FileInputStream(new java.io.File(sPath));
				BufferedReader br = new BufferedReader(new InputStreamReader(fin,"UTF-8"));
				String s1 = "";
		        while (br.ready()) {
		        	s1 = Common.get(br.readLine()).replace(";", "");
		        	if (!"".equals(s1)) {
		        		PersistenceServiceGetter.getInstance().getJdbcTemplate().execute(s1);
		        	}
		        }
			    br.close(); 						    
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

package com.kangdainfo.common.util.filestore;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kangdainfo.common.util.Common;
import com.kangdainfo.web.util.WebConstants;

 public class FileDownloadSimpleServlet extends javax.servlet.http.HttpServlet {
        
	public final String PARM_FILE_ID = "fileID";
	private static final long serialVersionUID = 1L;
	
	public FileDownloadSimpleServlet() {
		super();
	}

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        this.doProcess(req,res);       
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        this.doProcess(req,res);
    }
        
    protected void doProcess(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        
        ServletContext context = getServletContext();
        
        String fileType = request.getParameter("fileType");
        if(fileType == null || fileType.equals(""))
        	fileType = "filestoreLocation";
        
        String filestoreLocation = context.getInitParameter(fileType);
        
        String fileID = java.net.URLDecoder.decode(request.getParameter(PARM_FILE_ID),"UTF-8");
        String fileName = request.getParameter("fileName");

        String[] arrFileName;
        File sFile = null;
        //先執行資安檢測
        if(fileID==null && fileName==null) 
        {
            response.sendError(404,"Parameter Not Found !");
            return;
        } 
        else if (fileID!=null)
        {        	
        	
        	if (context.getServerInfo().toLowerCase().indexOf("tomcat")>0)
        	{
        		//fileID = Common.isoToBig5(fileID);
        	}
        	arrFileName = fileID.split(":;:"); 

        	if(arrFileName.length>2){
        		arrFileName[0] = Common.isValidChildFilePath(arrFileName[0]);
        		arrFileName[1] = Common.isValidChildFilePath(arrFileName[1]);
        		arrFileName[2] = Common.isValidChildFilePath(arrFileName[2]);
        		File dir = new File(Common.isValidFilePath(filestoreLocation+File.separator+arrFileName[0]+File.separator+arrFileName[1]));
        		if (dir.isDirectory()) 
                {       			
        			sFile = new File(dir, Common.isValidChildFilePath(arrFileName[2]));
                }
        	}
        	else if (arrFileName.length>1) 
        	{        
        		arrFileName[0] = Common.isValidChildFilePath(arrFileName[0]);
        		arrFileName[1] = Common.isValidChildFilePath(arrFileName[1]);
        		File dir = new File(Common.isValidFilePath(filestoreLocation+File.separator+arrFileName[0]));
        		if (dir.isDirectory()) 
                {        			
        			sFile = new File(dir, Common.isValidChildFilePath(arrFileName[1]));
        			if(sFile!=null && sFile.exists()){
        				sFile = new File(dir, Common.isValidChildFilePath(arrFileName[1]));
        			}else{
        				String[] children = dir.list();
                        for (int i=0; i<children.length; i++) {            	
                        	sFile = new File(dir, children[i]);
                        }
        			}
                }   	
        	} 
        	else 
        	{
        		//sFile = new File(filestoreLocation+File.separator+arrFileName[0]+File.separator+arrFileName[1]);
        		sFile = new File(filestoreLocation+File.separator+Common.isValidChildFilePath(fileID));
        	}        
        	
        	
        	
        } 
        else 
        {
        	if (request.getSession().getAttribute(WebConstants.SessionKeys.CURRENT_USER)==null) {
                response.sendError(404,"Parameter Not Found !");
                return;        		
        	}
        	/**
        	if (ServiceGetter.getInstance().getAuthenticationService().getCurrentUser()==null) {        		
                response.sendError(404,"Parameter Not Found !");
                return;
        	}  
        	**/     
        	
        	fileName = Common.isValidChildFilePath(fileName);
        	if (context.getServerInfo().toLowerCase().indexOf("tomcat")>0) 
        	{
        		fileID = Common.isoToBig5(fileName);
        		sFile = new File(Common.isoToBig5(fileName));
        	} 
        	else 
        	{
        		fileID = fileName;
        		sFile = new File(fileName);
        	} 	    
        }
	        
        if(sFile!=null && sFile.exists())
        {	        
	        String ct = ContentTypeConfiguration.getContentType(fileID);

	        if(ct!=null)
	        {
	            response.setContentType(ct);
	        }	        
	        
	        int len = new Long(sFile.length()).intValue();
	        
	        if (len>0) 
	        {	        	
	        	
	        	
	        	//response.setContentType("application/download");		        
		        //response.setHeader("Content-Type", "text/html;charset=UTF-8");
		        //response.setHeader("Content-Type", "application/download");
		        //response.setHeader("Content-Disposition", "inline; filename=" + sFile.getName());
		        //response.setHeader("Content-Disposition", "attachment; filename=\"" + Common.isoToBig5(sFile.getName()) + "\"");
		        //response.setContentType("text/html;charset=UTF-8");
	        	
	        	response.setContentLength(len);
	        	response.setHeader("Cache-control","");
	        	response.setHeader("Content-Disposition", "attachment; filename=\"" + Common.big5ToIso(sFile.getName()) + "\"");		        
		        
				FileInputStream is = null;	        
				OutputStream os = response.getOutputStream();		        
		        try{
		        	byte b[] = new byte[len];
		        	if (sFile.isFile()) {
		        		response.flushBuffer();
		        	    is = new FileInputStream(sFile);
		        	    os = response.getOutputStream();
		        	    int read = 0;
		        	    while ((read = is.read(b)) != -1){
		        	        os.write(b,0,read);
		        	    }
		        	}
		        }catch(Exception x){
		        	try{
		        		response.sendError(500,"File Output Error!");
		        	}catch(Exception e){}
		        }finally{
		        	if (os!=null) os.close();
		            if (is!=null) is.close();
		        }		        
	        } else {
	            response.sendError(404,"File Is Empty !");
	            return;
	        }
        } else {
            response.sendError(404,"File Not Found !");
            return;        	
        }       
    }

	
}
package com.kangdainfo.common.util.report;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperPrint;

import org.apache.log4j.Logger;

import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.filestore.ContentTypeConfiguration;

public class TableModelReportWordGenerator {
	
	protected ReportWordEnvironment env;
	protected Object[] wordArray;		//資料內容
	protected String format;			//文件格式
	protected String wordFile;			//取代的主檔案
	protected String subWordFile;		//取代的子檔案
	
	public TableModelReportWordGenerator(ReportWordEnvironment env) {
        this.env = env;
    }
	private Logger logger = Logger.getLogger(TableModelReportWordGenerator.class);
	
	private void loadEnvironment(){
		wordArray = env.getWordTableModel();
		format = env.getFormat();
		wordFile = env.getWordFile();
		subWordFile = env.getSubWordFile();
		env.getSubWordFile();
	}
	
	public void reportToHttpResponse(HttpServletResponse response) throws Exception {
		loadEnvironment();
		String filename = Common.getVMID(); 
		if (ReportWordEnvironment.VAL_FORMAT_DOC.equals(format)) {
	        filename = filename + ".doc";	
	    } else if (ReportWordEnvironment.VAL_FORMAT_DOCX.equals(format)) {
	        filename = filename + ".docx";		
	    } else {
	        throw new IllegalArgumentException("Doesn't support format:" + format);
	    }	
		
		java.io.File outputFile = new java.io.File(System.getProperty("java.io.tmpdir")+java.io.File.separator+filename);
		FileOutputStream fos = new FileOutputStream(outputFile);
		OutputStreamWriter osw = new OutputStreamWriter(fos); 

		//取代word文字
		List<String> mLins = getFileLines(wordFile);
		List<String> dLins = getFileLines(subWordFile);
		osw = setReplaceWord(mLins, dLins, wordArray, osw);
		osw.close();
		fos.close();
		
		//產出檔案
		int len = new Long(outputFile.length()).intValue();
		String ct = ContentTypeConfiguration.getContentType(outputFile.getName());
        if(ct!=null){
            response.setContentType(ct);
        }		        	
    	response.setHeader("Cache-control","");
	    response.setContentLength(len);	        
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + getResponseFileName(filename) + "\"");
	    
	    FileInputStream is = null;	      
	    OutputStream os = null;
	    try{
	    	byte b[] = new byte[len];
	    	if (outputFile.isFile()) {
	    		response.flushBuffer();
	    	    is = new FileInputStream(outputFile);
	    	    os = response.getOutputStream();
	    	    int read = 0;
	    	    while ((read=is.read(b)) != -1) {
	    	        os.write(b,0,read);
	    	    }
	    	}
	    }catch(Exception x){
	    	logger.error(x.getMessage(), x);
	    	x.printStackTrace();
	    }finally{
	    	if (os!=null) os.close();
	        if (is!=null) is.close();
	        if (outputFile!=null && outputFile.exists()) outputFile.delete();
	    }
	}
	
	public void reportToWord(File output) throws Exception 
	{
		loadEnvironment();

		FileOutputStream fos = new FileOutputStream(output);
		OutputStreamWriter osw = new OutputStreamWriter(fos); 
		//取代word文字
		List<String> mLins = getFileLines(wordFile);
		List<String> dLins = getFileLines(subWordFile);
		osw = setReplaceWord(mLins, dLins, wordArray, osw);
		osw.close();
		fos.close();

	}
	
	private OutputStreamWriter setReplaceWord(List<String> mlins,List<String> dlins, Object[] wordArray, OutputStreamWriter outWrite)
	{
	    try 
	    {
			for (String lin: mlins)
			{
				if(null != lin) 
				{
					for(int i=0; i<wordArray.length; i++)
					{
						String replKey = "##"+Common.get(i+1)+"##";
						if(replKey.equalsIgnoreCase(lin))
						{
							
							if("java.util.ArrayList".equals(wordArray[i].getClass().getCanonicalName()))
							{
								lin = "";
								if(null != dlins && !dlins.isEmpty()){
									java.util.List<Object[]> subArrayList = (List<Object[]>) wordArray[i];
									for(Object[] subArray:subArrayList){
										outWrite = setReplaceWord(dlins, null, subArray, outWrite);
									}
								}
							}
							else
							{
								lin = convertUncode10(Common.get(wordArray[i]));
							}
						}
					}
					//System.out.println(lin+"\n");
					outWrite.write(lin+"\n");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outWrite;
	}
	
	/**
	 * 檔案變成 List
	 * @param filename
	 * @return
	 */
	private List<String> getFileLines(String filename) {
		File file = null;
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    DataInputStream dis = null;
	    List data = null;
	    
	    if(null != filename && !"".equals(filename)){
	    	file = new File(filename);
	    	data = new LinkedList<String>();
	    	try {
	 	       fis = new FileInputStream(file);
	 	       bis = new BufferedInputStream(fis);
	 	       dis = new DataInputStream(bis);
	 	 
	 	       while (dis.available() != 0) {
	 	    	   data.add(dis.readLine());
	 	       }
	 	       fis.close();
	 	       bis.close();
	 	       dis.close();
	 	    } catch (Exception e){}
	    }
	    return data;
	}
	
	/**
	 * 方法說明：把中文轉為mht專用的unicode 10進制編碼
	 * @param s
	 * @return
	 */
	public String convertUncode10(String s) {
		String conStr = "";
		// 轉碼uncode 10進制
		for (int i = 0; i < s.length(); i++) {
			char a = s.charAt(i);
			conStr += "&#" + (int) a + ";"; // 將中文轉成10進制的表示
		}
		return conStr;
	}
	
	private String getResponseFileName(String filename) {
		String rptFile = wordFile;
		java.io.File f = new java.io.File(rptFile);
		int i = filename.lastIndexOf('.');
		int j = f.getName().indexOf('.');
		if (i>0 && i!=-1 && j>0 && j!=-1) {
			return f.getName().substring(0,j) + filename.substring(i).toLowerCase();				
		}
		return filename;
    }
}

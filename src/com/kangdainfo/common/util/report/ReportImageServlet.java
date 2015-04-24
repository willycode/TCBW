package com.kangdainfo.common.util.report;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.owasp.esapi.ESAPI;

import com.kangdainfo.common.util.Common;

/**
 * Servlet implementation class for Servlet: ReportImageServlet
 *
 * @web.servlet
 *   name="ReportImageServlet"
 *   display-name="ReportImageServlet" 
 *
 * @web.servlet-mapping
 *   url-pattern="/reportImage"
 *  
 * @web.servlet-init-param
 *    name="CacheTimeout"
 *    value="60000" 
 *    description="Image Cache timeout" 
 * 
 */
 public class ReportImageServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -232935106126901630L;

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ReportImageServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest arg0, HttpServletResponse arg1)
	 */
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		process(arg0,arg1);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest arg0, HttpServletResponse arg1)
	 */
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		process(arg0,arg1);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String imageIndex = Common.checkGet(request.getParameter(ReportGenerator.IMAGE_REQUEST_PARAMETER_KEY));
			if(imageIndex==null) {
				throw new Message("Cann't Find imageIndex in request parameter. Please re-query your report.");
			} else {
				HttpSession session = request.getSession();
				
				ImageCacheHandler handler = (ImageCacheHandler)session.getAttribute(ReportGenerator.IMAGE_SESSION_KEY);
				if(handler==null) throw new Message("Cann't Find ImageCacheHandler in session. Please re-query your report.");
				HashMap<String,byte[]> imageMap = handler.getImageMap();				
				byte[] obj = imageMap.get(imageIndex);
				if(obj==null) throw new Message("Cann't Find Image for "+imageIndex+" ImageCacheHandler. Please re-query your report.");				
				byte[] data = obj;
				
				response.setContentType("image/gif");
//				response.setHeader("Content-disposition", "inline; filename="+ imageIndex);
//				response.setHeader("Pragma","no-cache");
//				response.setHeader("Cache-Control","no-cache");
				ESAPI.httpUtilities().addHeader(response,"Content-disposition", "inline; filename="+ Common.checkGet(imageIndex));
				ESAPI.httpUtilities().addHeader(response,"Pragma","no-cache");
				ESAPI.httpUtilities().addHeader(response,"Cache-Control","no-cache");
				response.setDateHeader("Expires", -1);
				response.setHeader("Content-length", Long.toString(data.length));				
				response.getOutputStream().write(data);
			}
		}catch(Message msg){
			msg.printStackTrace();
		}catch(Exception x){
			x.printStackTrace();
		}
		
	}
	
	static class Message extends Throwable{
		private static final long serialVersionUID = 1L;

		public Message(String msg){
			super(msg);
		}
	}
}
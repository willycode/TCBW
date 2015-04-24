package com.kangdainfo.common.util.report;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kangdainfo.common.util.Common;
import com.kangdainfo.common.util.report.ReportImageServlet.Message;

/**
 * Servlet implementation class for Servlet: ReportPrintServlet
 *
 * @web.servlet
 *   name="ReportPrintServlet"
 *   display-name="ReportPrintServlet" 
 *
 * @web.servlet-mapping
 *   url-pattern="/reportPrint"
 *  
 **/
public class ReportPrintServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	private static final long serialVersionUID = -7991166451055437914L;

	public ReportPrintServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		process(arg0,arg1);
	}  	

	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		process(arg0,arg1);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession();
			String printIndex = Common.checkGet(request.getParameter(ReportGenerator.PRINT_REQUEST_PARAMETER_KEY));
			if (printIndex!=null) {
				Object jasperPrint = session.getAttribute(printIndex);
				if(jasperPrint==null) throw new Message("Cann't Find JasperPrint for "+printIndex+" Handle. Please re-query your report.");
				
				response.setContentType("application/octet-stream");
				javax.servlet.ServletOutputStream ouputStream = response.getOutputStream();					
				java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(ouputStream);
				oos.writeObject(jasperPrint);
				oos.flush();
				oos.close();
				ouputStream.flush();
				ouputStream.close();	 	
				
				session.removeAttribute(printIndex);
			}
		}catch(Message msg){
			response.setContentType("text/html");
			//response.getWriter().println(msg);
		}catch(Exception x){
			x.printStackTrace();
			//response.setContentType("text/html");
			//x.printStackTrace(response.getWriter());
		}
		
	}	
}

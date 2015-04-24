<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0001F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
	try{

		TableModelReportEnvironment env = new TableModelReportEnvironment();		
		javax.swing.table.DefaultTableModel model = obj.getTableModel();	
		
		if(null == model){			
			out.write("查無資料!");			
			return;		
		}	
		
		java.util.HashMap<String, Object> parms = new java.util.HashMap<String, Object>();		
		env.setTableModel(model);		
		env.setJasperFile(this.getServletContext().getRealPath("/report/drg/DRGIN0001_1r.jasper"));		
		env.setFormat(ReportEnvironment.VAL_FORMAT_XLS);	    
		TableModelReportGenerator generator = new TableModelReportGenerator(env);		
		out.clear();		
		generator.reportToHttpResponse(request, response, parms);		
		out = pageContext.pushBody();		
		
	}catch(Exception e){
		System.out.println(e.getMessage());	
		e.printStackTrace();
	}
%>


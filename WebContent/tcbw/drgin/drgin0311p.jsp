<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0311F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
	try{
		TableModelReportEnvironment env = new TableModelReportEnvironment();
		javax.swing.table.DefaultTableModel model = obj.getTableModel();
		if(model==null){
			out.write("資料庫裡沒有符合的資料");
			return;
		}		
		java.util.HashMap<String, Object> parms = new java.util.HashMap<String, Object>();
		env.setTableModel(model);
		env.setHtmlImagePattern(Common.getReportImageCachePath());
		env.setJasperFile(this.getServletContext().getRealPath("/report/drg/DRGIN0311R.jasper"));
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


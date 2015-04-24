<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj"  scope="request" class="com.kangdainfo.tcbw.view.hfrex.HFREX0104F">
    <jsp:setProperty name='obj' property='*'/>
</jsp:useBean>
<%
try {	
	
	obj.setRequest(request);
	
	java.util.HashMap<String, Object> parms = new java.util.HashMap<String, Object>();	
	if("1".equals(obj.getHfrType())){
		obj.setSR_Parameter(parms);
	}else{
		obj.setGR_Parameter(parms);
	}
	
    StringBuilder sb = new StringBuilder();
    
    TableModelReportEnvironment env = new TableModelReportEnvironment();
    env.setFormat("PDF");
    //env.setFormat("XLS");
	javax.swing.table.DefaultTableModel model = null;
	if("1".equals(obj.getHfrType())){
		model = obj.getSimpleDefaultTableModel();
	}else{
		model = obj.getGeneralDefaultTableModel();
	}
	
	if (model==null) 
	{
		out.write("資料庫裡沒有符合的資料");
		return;
	}	
	
	env.setTableModel(model);
	env.setHtmlImagePattern(Common.getReportImageCachePath());
	env.setFormat("PDF");
	//env.setFormat("XLS");
	if("1".equals(obj.getHfrType())){
		env.setJasperFile(this.getServletContext().getRealPath("/report/hfr/hfrin0102sr.jasper"));
	}else{
		env.setJasperFile(this.getServletContext().getRealPath("/report/hfr/hfrin0102gr.jasper"));
	}
    TableModelReportGenerator generator = new TableModelReportGenerator(env);
	out.clear();
	generator.reportToHttpResponse(request, response, parms);
	out = pageContext.pushBody();
} 
catch (Exception e) 
{
	out.write("列印錯誤，請洽詢管理員!");
	e.printStackTrace();
}
%>



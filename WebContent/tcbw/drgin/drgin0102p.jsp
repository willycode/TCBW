<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj"  scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0102Q">
    <jsp:setProperty name='obj' property='*'/>
</jsp:useBean>
<%
try {	
	
	obj.setRequest(request);
	
	java.util.HashMap<String, Object> parms = new java.util.HashMap<String, Object>();	
    
    StringBuilder sb = new StringBuilder();
   
    parms.put("dateSE", sb.toString());
    parms.put("printDate",Common.formatYYYMMDD(Datetime.getYYYMMDD(),4));
    
    TableModelReportEnvironment env = new TableModelReportEnvironment();
    env.setFormat(obj.getQ_outputFormat());
	javax.swing.table.DefaultTableModel model = obj.getDefaultTableModel();
	if (model==null) 
	{
		out.write("資料庫裡沒有符合的資料");
		return;
	}	
	

	env.setTableModel(model);
	env.setHtmlImagePattern(Common.getReportImageCachePath());
	env.setJasperFile(this.getServletContext().getRealPath("/report/drg/DRGIN0101r.jasper"));
	env.setFormat(obj.getQ_outputFormat());	
    
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



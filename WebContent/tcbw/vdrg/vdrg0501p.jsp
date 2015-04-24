<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj"  scope="request" class="com.kangdainfo.tcbw.view.vdrg.VDRG0501F">
    <jsp:setProperty name='obj' property='*'/>
</jsp:useBean>
<%
try {	
	
    TableModelReportEnvironment env = new TableModelReportEnvironment();
	javax.swing.table.DefaultTableModel model = obj.getDefaultTableModel();
	if (model==null) 
	{
		out.write("資料庫裡沒有符合的資料");
		return;
	}	
	java.util.HashMap<String, Object> parms = new java.util.HashMap<String, Object>();
	parms.put("caseType", "藥品");
    parms.put("queryDept","藥害救濟基金會");
    parms.put("sysDate", Common.get(obj.getQ_dataRevDateS())+"~"+Common.get(obj.getQ_dataRevDateE()));
    
	env.setTableModel(model);
	env.setHtmlImagePattern(Common.getReportImageCachePath());
	env.setJasperFile(this.getServletContext().getRealPath("/report/vdrg/VDRG0501R.jasper"));
	env.setFormat(ReportEnvironment.VAL_FORMAT_XLS);
    
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



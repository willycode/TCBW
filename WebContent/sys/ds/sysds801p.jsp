<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="SYSDS801" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.common.view.sys.ds.SYSDS801F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
try {		
    TableModelReportEnvironment env = new TableModelReportEnvironment();
	javax.swing.table.DefaultTableModel model = obj.getVisitLogTableModel();
	if (model==null) {
		out.write("資料庫裡沒有符合的資料");
		return;
	}	
	java.util.HashMap<String, Object> parms = new java.util.HashMap<String, Object>();
	parms.put("datePeriod", Common.formatYYYMMDD(obj.getQ_logDate(), 4)+ "至"+ Common.formatYYYMMDD(obj.getQ_logDateE(), 4));
	//parms.put("auditPercentage", "查核百分比：" + obj.getAuditPercentage() + "%");
	parms.put("printDate", Common.formatYYYMMDD(Datetime.getYYYMMDD(), 4));
	
	env.setTableModel(model);
	env.setHtmlImagePattern(Common.getReportImageCachePath());
	env.setJasperFile(this.getServletContext().getRealPath("/report/sys/ds/sysds801r.jasper"));
	env.setFormat(obj.getQ_outputFormat());	
    
    TableModelReportGenerator generator = new TableModelReportGenerator(env);
	out.clear();
	generator.reportToHttpResponse(request, response, parms);
	out = pageContext.pushBody();
} catch (Exception e) {
	e.printStackTrace();
}
%>



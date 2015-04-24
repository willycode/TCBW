<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj"  scope="request" class="com.kangdainfo.common.view.sys.ex.SYSEX903R">
    <jsp:setProperty name='obj' property='*'/>
</jsp:useBean>
<%
try {		
	java.util.HashMap<String, Object> parms = new java.util.HashMap<String, Object>();	
	parms.put("isPrintPageNumber", "Y");
	parms.put("pagginPrefix", "頁碼前置 - ");
	parms.put("pageNumberVar", 100);
	
    TableModelReportEnvironment env = new TableModelReportEnvironment();    
	env.setHtmlImagePattern(Common.getReportImageCachePath());
	env.setJasperFile(this.getServletContext().getRealPath("/report/sys/ds/SYSEX903R.jasper"));
	env.setFormat(obj.getQ_outputFormat());	

    javax.swing.table.DefaultTableModel model = null;
	if (obj.getState().equals("1")) {
		model = obj.getReportTableModel();
	} else {
		model = obj.getDefaultTableModel();	
	}
	if (model==null) {
		out.write("資料庫裡沒有符合的資料");
		return;
	}	
	env.setTableModel(model);	
    TableModelReportGenerator generator = new TableModelReportGenerator(env);
	out.clear();	
	generator.reportToHttpResponse(request, response, parms);
	out = pageContext.pushBody();	
} catch (Exception e) {
	//out.write(e.getMessage());
	e.printStackTrace();
}
%>
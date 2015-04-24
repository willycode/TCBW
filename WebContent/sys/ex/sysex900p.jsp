<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj"  scope="request" class="com.kangdainfo.common.view.sys.ex.SYSEX900R">
    <jsp:setProperty name='obj' property='*'/>
</jsp:useBean>
<%
try {		
	java.util.HashMap<String, Object> parms = new java.util.HashMap<String, Object>();	
    parms.put("pageNumberVar",Common.getInt(obj.getPageNumberVar())-1);      
    if (!"".equals(obj.getIsPrintPageNumber())) parms.put("isPrintPageNumber",obj.getIsPrintPageNumber());
    parms.put("pagginPrefix",obj.getPagginPrefix());
    
	if (obj.getState().equals("1")) {
		SQLReportEnvironment env = new SQLReportEnvironment();	
		env.setJasperFile(this.getServletContext().getRealPath("/report/sys/ds/SYSEX900R.jasper"));		
		env.setHtmlImagePattern(Common.getReportImageCachePath());
		env.setFormat(obj.getQ_outputFormat());
		
		obj.setSQLReportEnvironment(env, parms);		
		SQLReportGenerator generator = new SQLReportGenerator(env);
		out.clear();
		generator.reportToHttpResponse(request, response, parms);
		out = pageContext.pushBody();		
	} else {
	    TableModelReportEnvironment env = new TableModelReportEnvironment();
	    env.setFormat(obj.getQ_outputFormat());
		javax.swing.table.DefaultTableModel model = obj.getDefaultTableModel();
		if (model==null) {
			out.write("資料庫裡沒有符合的資料");
			return;
		}	
		env.setTableModel(model);
		env.setHtmlImagePattern(Common.getReportImageCachePath());
		env.setJasperFile(this.getServletContext().getRealPath("/report/sys/ds/SYSEX900R.jasper"));
		env.setFormat(obj.getQ_outputFormat());	
	    
	    TableModelReportGenerator generator = new TableModelReportGenerator(env);
		out.clear();
		generator.reportToHttpResponse(request, response, parms);
		out = pageContext.pushBody();		
	}
} catch (Exception e) {
	//out.write(e.getMessage());
	e.printStackTrace();
}
%>



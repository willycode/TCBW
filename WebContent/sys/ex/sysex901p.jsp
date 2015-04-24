<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.kangdainfo.common.view.sys.ds.SYSDS801F"%>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj"  scope="request" class="com.kangdainfo.common.view.sys.ex.SYSEX900R">
    <jsp:setProperty name='obj' property='*'/>
</jsp:useBean>
<%
try {		
	TableModelReportEnvironment env = new TableModelReportEnvironment();
	env.setFormat(obj.getQ_outputFormat());	
	
    com.kangdainfo.common.view.sys.ds.SYSDS801F ref = new com.kangdainfo.common.view.sys.ds.SYSDS801F();
    ref.setQ_logDate("0990501");
    ref.setQ_logDateE("0990522");    
    javax.swing.table.DefaultTableModel model = ref.getVisitLogTableModel();    
    javax.swing.table.DefaultTableModel model2 = obj.getDefaultTableModel();
    
    if (model==null) {
    	out.write("model is null");
    	return;
    }
    if (model2==null) {
    	out.write("model2 is null");
    	return;
    }
    
    java.util.List tableModelList = new java.util.ArrayList();
	tableModelList.add(model);
	tableModelList.add(model2);
	env.setTableModelList(tableModelList);
	
	java.util.List jasperFileList = new java.util.ArrayList();	
	jasperFileList.add(this.getServletContext().getRealPath("/report/sys/ds/sysds801r.jasper"));
	jasperFileList.add(this.getServletContext().getRealPath("/report/sys/ds/SYSEX900R.jasper"));	
	env.setJasperFileList(jasperFileList);
	
	java.util.HashMap<String, Object> parms = new java.util.HashMap<String, Object>();	
    parms.put("pageNumberVar",Common.getInt(obj.getPageNumberVar())-1);      
    if (!"".equals(obj.getIsPrintPageNumber())) parms.put("isPrintPageNumber",obj.getIsPrintPageNumber());
    parms.put("pagginPrefix",obj.getPagginPrefix());	
    
    java.util.List jasperParamList = new java.util.ArrayList();
    java.util.HashMap<String, Object> p1 = new java.util.HashMap<String, Object>();
    p1.put("pagginPrefix","P1");	
    java.util.HashMap<String, Object> p2 = new java.util.HashMap<String, Object>();
    p2.put("pagginPrefix","P2");
    jasperParamList.add(p1);
    jasperParamList.add(p2);
    env.setJasperParamList(jasperParamList);
    
	TableModelReportGenerator generator = new TableModelReportGenerator(env);
	out.clear();	    	
	generator.reportToHttpResponse(request, response, parms);
	out = pageContext.pushBody();
} catch (Exception e) {
	//out.write(e.getMessage());
	e.printStackTrace();
}
%>



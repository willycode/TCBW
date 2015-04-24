<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%@ page import="java.io.*"%>
<jsp:useBean id="obj"  scope="request" class="com.kangdainfo.tcbw.view.sdrg.SDRG0101F">
    <jsp:setProperty name='obj' property='*'/>
</jsp:useBean>
<%
try {	
	
	ReportWordEnvironment env = new ReportWordEnvironment();
	Object[] rowArray = obj.getDefaultTableModelForWord();
	if (rowArray==null){
		out.write("資料庫裡沒有符合的資料");
		return;
	}	
	env.setWordTableModel(rowArray);
	if("01".equals(obj.getReportType())){
		env.setWordFile(this.getServletContext().getRealPath("/word/sdrg/SDRG0101_01.mht"));
	}else if("02".equals(obj.getReportType())){
		env.setWordFile(this.getServletContext().getRealPath("/word/sdrg/SDRG0101_02.mht"));
	}else if("03".equals(obj.getReportType())){
		env.setWordFile(this.getServletContext().getRealPath("/word/sdrg/SDRG0301_01.mht"));
	}else if("04".equals(obj.getReportType())){
		env.setWordFile(this.getServletContext().getRealPath("/word/sdrg/SDRG0701_01.mht"));
	}
	
	env.setFormat(ReportWordEnvironment.VAL_FORMAT_DOC);
	
	TableModelReportWordGenerator generator = new TableModelReportWordGenerator(env);
	out.clear();
	generator.reportToHttpResponse(response);
	out = pageContext.pushBody();
} catch (Exception e) {
	out.write("列印錯誤，請洽詢管理員!");
	e.printStackTrace();
}
%>



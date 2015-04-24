<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN0301Q">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
java.io.FileInputStream fis = null;
	try{
		//因為要使用選定欄位的方式列印報表，不能使用jasper做處理，改由一般的產生excel的方式
		java.util.List list = obj.getTableModel(); 
		com.kangdainfo.common.util.XlsUtil xlsUtil = new com.kangdainfo.common.util.XlsUtil();
		java.io.File file = xlsUtil.genXlsFile(list);
		fis = new java.io.FileInputStream(file); 
		out.clear();

		response.setContentType("application/octet-stream; charset=iso-8859-1;");
		response.setHeader("content-disposition","attachment; filename="+"medin0301.xls");
		
		int byteRead;
		while(-1 != (byteRead = fis.read())){	      
	        out.write(byteRead);
		}
	    file.delete();
	}catch(Exception e){
		System.out.println(e.getMessage());	
		e.printStackTrace();
	}finally{
		if(fis!=null)fis.close();
	}
%>


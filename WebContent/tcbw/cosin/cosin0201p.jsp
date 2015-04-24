<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN0201Q">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
java.io.FileInputStream fis = null;
try{
	if(obj.getReportField()!=null && obj.getReportField().length>0){
		java.util.List list = obj.getTableModel();
		if(list!=null && list.size()>0){
			com.kangdainfo.common.util.XlsUtil xlsUtil = new com.kangdainfo.common.util.XlsUtil();
			
			java.io.File file = xlsUtil.genXlsFile(list);
			fis = new java.io.FileInputStream(file); 
			out.clear();

			response.setContentType("application/octet-stream; charset=iso-8859-1;");
			response.setHeader("content-disposition","attachment; filename=" + "cosin0201.xls");
			
			int byteRead;
			while(-1 != (byteRead = fis.read())){	      
		        out.write(byteRead);
			}
		    file.delete();
		}else{
			out.write("查無資料，請重新查詢 !");
		}
	}else{
		out.write("未選取結果欄位，請重新勾選 !");
	}
}catch(Exception e){
	out.write("發生異常，請重新查詢 !");
	System.out.println(e.getMessage());	
	e.printStackTrace();
}finally{
	if(fis != null){
		fis.close();
	}
}
%>


<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj"  scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN2003F">
    <jsp:setProperty name='obj' property='*'/>
</jsp:useBean>
<%
try{
	java.util.List list = obj.getTableModel(); 
	com.kangdainfo.common.util.XlsUtil xlsUtil = new com.kangdainfo.common.util.XlsUtil();
	java.io.File file = xlsUtil.genXlsFile(list);
	java.io.FileInputStream fis = new java.io.FileInputStream(file); 
	out.clear();

	response.setContentType("application/octet-stream; charset=iso-8859-1;");
	response.setHeader("content-disposition","attachment; filename="+"medin2003.xls");
	
	int byteRead;
	while(-1 != (byteRead = fis.read())){	      
        out.write(byteRead);
	}
    fis.close();
    file.delete();
}catch(Exception e){
	System.out.println(e.getMessage());	
	e.printStackTrace();
}
%>



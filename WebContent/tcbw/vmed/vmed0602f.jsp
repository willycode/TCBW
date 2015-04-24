<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.vmed.VMED0601F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
try 
{	
	String id=request.getParameter("id");
	String applNo=request.getParameter("applNo");
	obj.print(id,applNo);
} 
catch (Exception e) 
{
	out.write("列印錯誤，請洽詢管理員!");
	e.printStackTrace();
}
%>

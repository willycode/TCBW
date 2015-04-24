<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj"  scope="request" class="com.kangdainfo.tcbw.view.dynamic.DYNAMIC0801R">
    <jsp:setProperty name='obj' property='*'/>
</jsp:useBean>
<%
try
{
	out.clear();	
	try
	{		
	    obj.exportXls(request,response);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		out.write("查無資料!");
	}
	
	out = pageContext.pushBody();
}
catch(Exception e)
{
	out.write("執行失敗，請洽詢系統管理者或相關承辦人員!");
	e.printStackTrace();
}
%>



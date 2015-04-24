<!--
程式目的：
程式代號：medex0103f.jsp
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.medex.MEDEX0105F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
	String isSave = Common.get(request.getParameter("isSave"));

	String resultMsg = ""; 
	if("Y".equals(isSave))
	{
		try
		{
			obj.setHttpRequest(pageContext.getRequest());
			obj.doAutoSave();
			resultMsg = "Y";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			resultMsg = "N";
		}
	}
%>

<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<script type="text/javascript">
function init()
{
	if("<%=isSave%>" == "Y")
	{
		window.parent.unLockAutoSaveButton();
	}
}
</script>
</head>
<body onLoad="init();">
</body>
</html>

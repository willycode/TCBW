<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.vdrg.VDRG0100F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
	String isSave = Common.get(request.getParameter("isSave"));
	String id = Common.get(request.getParameter("id"));
	String resultMsg = ""; 
	if("Y".equals(isSave))
	{
		try
		{
			obj.setHttpRequest(pageContext.getRequest());
			obj.setQueryAllFlag("true");
			obj.update();
			
			//resultMsg = "Y";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//resultMsg = "N";
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
		window.parent.queryOne('<%=id%>');
	}
}
</script>
</head>
<body onLoad="init();">
</body>
</html>
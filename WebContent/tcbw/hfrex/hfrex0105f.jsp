<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.hfrex.HFREX0103F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
	String isSave = Common.get(request.getParameter("isSave"));
	if("Y".equals(isSave)){
		try{
			obj.setHttpRequest(pageContext.getRequest());
			obj.doAutoSave();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
%>

<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<script type="text/javascript">
function init(){
	if("<%=isSave%>" == "Y"){
		window.parent.unLockAutoSaveButton();
	}
}
</script>
</head>
<body onLoad="init();">
</body>
</html>

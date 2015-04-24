
<!--
程式目的：
程式代號：
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.trans.TRANSCOMMONUSER">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
	String confirm = request.getParameter("confirm");
	if (Common.get(confirm).equals("執行")) 
	{
		obj.trans();
		obj.setErrorMsg(obj.getMsg());
	}	

%>

<html>
<head>
<title>執行</title>
<meta http-equiv="Expires" content="-1"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css">
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript">
function checkField()
{
    var alertStr="";

    if(alertStr.length!=0){ alert(alertStr); return false; }
    form1.submit();
}
function init()
{

}


</script>

</head>

<body topmargin="0" onLoad="init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->

<%=Common.get(obj.getTransMsg())%>
<!--Toolbar區============================================================-->
<tr><td class="bg" style="text-align:center">
<span id="spanConfirm">
<input class="toolbar_default" type="submit" followPK="false" name="confirm" value="執行"  >
</span>
</td></tr>

</table>
</form>

</body>
</html>
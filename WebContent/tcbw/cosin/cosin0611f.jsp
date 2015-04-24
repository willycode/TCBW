<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%@ page import="java.io.*" %> 

<%
String field = Common.get(request.getParameter("field"));
%>

<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">
function toConfirm(){
	var alertStr = checkEmpty(form1.reDisReason, "重新分類理由");
	if(alertStr.length > 0){
		alert(alertStr);
		return false;
	}
	
	var f = form1.field.value;
	if(opener.document.all.item(f) != null){
		opener.document.all.item(f).value = form1.reDisReason.value;
		opener.reDisCase();
		window.close();
	}else{
		alert("未取得原視窗資料，請確認");
	}
}

function toCancel(){
	window.close();
}
</script>
</head>
<body>
<form id="form1" name="form1" method="post" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">
	<div style="height:auto">
	<table class="queryTable" width="100%">
	<tr>
		<td nowrap class="td_form" width="20%">重新分類理由：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="reDisReason" size="50" maxlength="50">
		</td>
	</tr>
	</table>
	</div>
</td></tr>

<tr><td nowrap class="bgToolbar" style="text-align:center">
	<input type="hidden" name="field" value="<%=field%>">
	<span id="spanDoConfirm">
    	<input class="toolbar_default" type="button" followPK="false" name="doConfirm" value="確   定" onClick="toConfirm();">&nbsp;
    </span>
    <span id="spanDoCancel">
    	<input class="toolbar_default" type="button" followPK="false" name="doCancel" value="取   消" onClick="toCancel();">&nbsp;
    </span>
</td></tr>
</table>
</form>
</body>
</html>

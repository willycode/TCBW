<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%@ page import="java.io.*" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="SDRG0301"/>
</jsp:include>
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
	var rad = document.getElementById("form1");
	var v1 ="";
	for (var i=0; i<rad.nType.length; i++)
	{
	   if (rad.nType[i].checked)
	   {
	      v1 = rad.nType[i].value;
	      break;
	   }
	}	
	if(v1 != ""){
		opener.document.all.item("updateType").value = v1;
		opener.checkField();
		window.close();
	}else{
		alert("請選擇送出類別!");
	}
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
		<td nowrap class="td_form_left" width="40%">送出類別：</td>
	</tr>
	<tr>
		<td nowrap class="td_form_white">
			<%=View.getRadioBoxTextOption("field_Q","nType","4;廠商回收;5;廠商回覆;6;廠商回收及回覆;","4")%>
		</td>
	</tr>
	</table>
	</div>
</td></tr>

<tr><td nowrap class="bgToolbar" style="text-align:center">
	<span id="spanDoConfirm">
    	<input class="toolbar_default" type="button" followPK="false" name="doConfirm" value="確   定" onClick="toConfirm();">&nbsp;
    </span>
</td></tr>
</table>
</form>
</body>
</html>

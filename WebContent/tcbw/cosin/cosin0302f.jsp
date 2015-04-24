<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%@ page import="java.io.*" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="COSIN0301" />
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
	var choseType = "";
	if(form1.cType[0].checked && !form1.cType[1].checked){
		choseType = "1";
	}else if(!form1.cType[0].checked && form1.cType[1].checked){
		choseType = "2";
	}else if(form1.cType[0].checked && form1.cType[1].checked){
		choseType = "3";
	}else{
		alert("請選不良事件類別 !");
		return;
	}
	
	if(choseType != ""){
		opener.document.all.item("cosType").value = choseType;
		opener.goInsert();
		window.close();
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
		<td nowrap class="td_form" width="40%">不良事件類別：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="checkbox" name="cType" value="1">不良品 <br>
			<%=View.getLookupField("select formDesc from Con1016Db where systemType='COS' and formType = '01' ")%><br>
			<input class="field" type="checkbox" name="cType" value="2">不良反應 <br>
			<%=View.getLookupField("select formDesc from Con1016Db where systemType='COS' and formType = '02' ")%>
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

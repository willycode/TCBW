<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%@ page import="java.io.*" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="HFRIN0801"/>
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
	if(form1.type.value != ""){
		if(form1.type.value == "1"){
			
		}else{
			opener.document.all.item("state").value = "pauseSave";
		}
		opener.goSubmit();
		window.close();
	}else{
		alert("回覆結果請選擇");
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
		<td nowrap class="td_form" width="40%">是否回覆案件通報者：</td>
		<td nowrap class="td_form_white">
			<select class="field" name="type" type="select">
				<%=View.getTextOption("1;已回覆;2;未回覆;", "") %>
			</select>
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

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN0405F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
request.setCharacterEncoding("UTF-8");
String q_caseNo = Common.get(request.getParameter("q_caseNo"));
obj.setQ_caseNo(q_caseNo);

obj = (com.kangdainfo.tcbw.view.cosin.COSIN0405F)obj.queryOne();
%>

<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="./../../js/jquery.js"></script>
<script type="text/javascript">
function checkField(){
	var alertStr = "";
	if(alertStr.length!=0){ alert(alertStr); return }	
	beforeSubmit();
	form1.submit();
}

function init(){
	setAllReadOnly();
}

$(function(){
	setDisplayItem('spanInsert,spanUpdate,spanQueryAll,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden','H');
});
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" autocomplete="off">

<!--Toolbar區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bgToolbar">
	<input type="hidden" name="q_caseNo" value="<%=obj.getQ_caseNo()%>">
	
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
    <jsp:include page="../../home/toolbar.jsp">
		<jsp:param value="N" name="btnCopy"/>
    </jsp:include>
</td></tr>
</table>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" align="center" >
		<tr>
			<td nowrap class="td_form" style="text-align:center" colspan="4">登錄資料</td>
	    </tr>
		<tr>
			<td nowrap class="td_form">登錄編號</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="CASENO" size="30" value="<%=obj.getCASENO()%>">
			</td>
			<td nowrap class="td_form">許可證字號</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="LICENSENO" size="30" value="<%=obj.getLICENSENO()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">完成登錄日期</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="FINISHDATE" size="20" value="<%=obj.getFINISHDATE()%>">
			</td>
			<td nowrap class="td_form">有效期限</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="ENDDATE" size="20" value="<%=obj.getENDDATE()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">中文品名</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="PRODCNAME" size="90" value="<%=obj.getPRODCNAME()%>">
			</td>					
		</tr>
		<tr>
			<td nowrap class="td_form">外文品名</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="PRODENAME" size="90" value="<%=obj.getPRODENAME()%>">
			</td>					
		</tr>
		<tr>
			<td nowrap class="td_form">登錄廠商名稱</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="COMPNAME" size="30" value="<%=obj.getCOMPNAME()%>">
			</td>
			<td nowrap class="td_form">登錄廠商電話</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="COMPTEL" size="20" value="<%=obj.getCOMPTEL()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">登錄廠商地址</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="COMPADDRESS" size="90" value="<%=obj.getCOMPADDRESS()%>">
			</td>
		</tr>
	</table>
	</div>
</td></tr>
</table>
</form>
</body>
</html>

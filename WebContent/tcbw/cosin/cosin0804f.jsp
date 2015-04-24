<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="COSIN0801" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN0803F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
obj = (com.kangdainfo.tcbw.view.cosin.COSIN0803F)obj.queryOne();
%>

<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript">
$(function(){
	setDisplayItem('spanInsert,spanUpdate,spanQueryAll,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden','H');
});
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');showErrorMsg('');">
<form id="form1" name="form1" method="post" autocomplete="off">

<!--Toolbar區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bgToolbar" style="text-align:left">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<jsp:include page="../../home/toolbar.jsp" />
</td></tr>
</table>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">

	<div id="formContainer" style="height:auto">
	<table id="Tab6" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td nowrap class="td_form" colspan="3" style="text-align:center">評估歷史資料</td>
	</tr>
	<tr>
		<td nowrap class="td_form">評估日期</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getPopCalendar("field", "assessDate", obj.getAssessDate())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">評估結果</td>
		<td nowrap class="td_form_white" colspan="3">
			<select class="field" name="assessResult" type="select">
				<%=View.getTextOption("1;不良品;2;非不良品", obj.getAssessResult()) %>
			</select>
			<input type="hidden" name="cos0007DbId" value="<%=obj.getCos0007DbId()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">評估備註</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field" type="text" name="assessRemark" size="80" maxlength="100" value="<%=obj.getAssessRemark()%>">
		</td>
	</tr>
	</table>	
	</div>
	
</td></tr>
</table>
</form>
</body>
</html>
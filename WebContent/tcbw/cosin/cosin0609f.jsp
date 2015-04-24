<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="COSIN0701" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN0603F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
obj = (com.kangdainfo.tcbw.view.cosin.COSIN0603F)obj.queryOne();
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
var idFileCount = 0;
var win = null;
$(function(){
	setDisplayItem('spanInsert,spanUpdate,spanQueryAll,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden','H');
	<%=obj.getCOSVRFileRowSBuilder()%>
});

function init(){
	$(":button[name$=Download]").each(function(){
		$(this).attr("disabled", false);
	});
}
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('');">
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
		<td nowrap class="td_form" colspan="4" style="text-align:center">廠商回覆歷史資料</td>
	</tr>
	<tr>
		<td nowrap class="td_form">通知日期</td>
		<td nowrap class="td_form_white">
			<input class="field_RO" type="text" name="notifyDate9" size="7" maxlength="7" value="<%=obj.getNotifyDate9()%>">
			<input type="hidden" name="cos0009DbId" value="<%=obj.getCos0009DbId()%>">
		</td>
		<td nowrap class="td_form">回覆日期</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field", "replyDate9", obj.getReplyDate9())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">目前處理情形/調查報告</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field" type="text" name="handling" size="80" maxlength="100" value="<%=obj.getHandling()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">預防矯正措施</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field" type="text" name="precaution9" size="80" maxlength="100" value="<%=obj.getPrecaution9()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">類似客訴</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field" type="text" name="similarComplaint" size="80" maxlength="100" value="<%=obj.getSimilarComplaint()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">補充說明</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field" type="text" name="remark9" size="80" maxlength="100" value="<%=obj.getRemark9()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">預計完成日期</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getPopCalendar("field", "predictDate", obj.getPredictDate())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">相關附件</td>
		<td nowrap class="td_form_white" colspan="3">
			<jsp:include page="../cosex/cosex0104f.jsp">
				<jsp:param value="COSVR" name="fileType"/>
			</jsp:include>
		</td>
	</tr>
	</table>	
	</div>
</td></tr>
</table>
</form>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="COSIN0701" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN0604F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
obj = (com.kangdainfo.tcbw.view.cosin.COSIN0604F)obj.queryOne();
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
			<input class="field_RO" type="text" name="notifyDate10" size="7" maxlength="7" value="<%=obj.getNotifyDate10()%>">
			<input type="hidden" name="cos0010DbId" value="<%=obj.getCos0010DbId()%>">
		</td>
		<td nowrap class="td_form">回覆日期</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field", "replyDate10", obj.getReplyDate10())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">是否接獲該產品其他消費者客訴</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field" type="radio" name="isComplaintYn10" value="Y" <%=obj.getIsComplaintYn10().equals("Y")?"checked":"" %>>是，件數
			<input class="field" type="text" name="complaintNum" size="5" maxlength="5" value="<%=obj.getComplaintNum()%>" onChange="checkVal(this.value);">
			<input class="field" type="radio" name="isComplaintYn10" value="N" <%=obj.getIsComplaintYn10().equals("N")?"checked":"" %>>否
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">類似客訴案件之後續處理情形</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field" type="text" name="dealWith10" size="80" maxlength="100" value="<%=obj.getDealWith10()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">其他回覆</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field" type="text" name="replyOther10" size="80" maxlength="100" value="<%=obj.getReplyOther10()%>">
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
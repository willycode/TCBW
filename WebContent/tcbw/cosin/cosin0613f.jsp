<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="COSIN0701" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN0605F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
obj = (com.kangdainfo.tcbw.view.cosin.COSIN0605F)obj.queryOne();
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
			<input class="field_RO" type="text" name="notifyDate0910" size="7" maxlength="7" value="<%=obj.getNotifyDate0910()%>">
			<input type="hidden" name="cos0009DbId" value="<%=obj.getCos0009DbId()%>">
			<input type="hidden" name="cos0010DbId" value="<%=obj.getCos0010DbId()%>">
		</td>
		<td nowrap class="td_form">回覆日期</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field", "replyDate0910", obj.getReplyDate0910())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">是否接獲該產品<br>其他消費者客訴</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field" type="radio" name="isComplaintYn0910" value="Y" <%=obj.getIsComplaintYn0910().equals("Y")?"checked":"" %>>是，件數
			<input class="field" type="text" name="complaintNum0910" size="5" maxlength="5" value="<%=obj.getComplaintNum0910()%>" onChange="checkVal(this.value);">
			<input class="field" type="radio" name="isComplaintYn0910" value="N" <%=obj.getIsComplaintYn0910().equals("N")?"checked":"" %>>否
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">類似客訴<br>之後續處理情形</td>
		<td nowrap class="td_form_white" colspan="3">
			<textarea class="field"  name="similarComplaint0910" cols="60" rows="2" ><%=obj.getSimilarComplaint0910()%></textarea>(200字以內)
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">預防矯正措施</td>
		<td nowrap class="td_form_white" colspan="3">
			<textarea class="field"  name="precaution0910" cols="60" rows="5" ><%=obj.getPrecaution0910()%></textarea>(500字以內)
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">目前處理情形<br>調查報告</td>
		<td nowrap class="td_form_white" colspan="3">
			<textarea class="field"  name="handling0910" cols="60" rows="5" ><%=obj.getHandling0910()%></textarea>(500字以內)
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">補充說明</td>
		<td nowrap class="td_form_white" colspan="3">
			<textarea class="field"  name="remark0910" cols="60" rows="2" ><%=obj.getRemark0910()%></textarea>(200字以內)
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">預計完成日期</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getPopCalendar("field", "predictDate0910", obj.getPredictDate0910())%>
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
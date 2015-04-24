<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.hfrin.HFRIN0604F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
request.setCharacterEncoding("UTF-8");
String id = Common.get(request.getParameter("id"));
obj.setId(id);

obj = (com.kangdainfo.tcbw.view.hfrin.HFRIN0604F)obj.queryOne();
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
var idFileCount = 0;
var win = null;

function checkField(){
	var alertStr = "";
	if(alertStr.length!=0){ alert(alertStr); return; }	
	beforeSubmit();
	form1.submit();
}

function init(){
	$(":button[name$=Download]").each(function(){
		$(this).attr("disabled", false);
	});
}

$(function(){
	setDisplayItem('spanInsert,spanUpdate,spanQueryAll,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden','H');
	<%=obj.getHFRFOFileRowSBuilder()%>
});
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" autocomplete="off">

<!--Toolbar區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bgToolbar">
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
			<td nowrap class="td_form_left" colspan="4" style="text-align:center">初評資訊</td>
	    </tr>
		<tr>
			<td nowrap class="td_form">初評完成日期</td>
			<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field", "preCompleteDate", obj.getPreCompleteDate())%>
				<input type="hidden" name="id" value="<%=obj.getId()%>">
			</td>
			<td nowrap class="td_form">預評送交日期</td>
			<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field", "assessmentSendDate", obj.getAssessmentSendDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">預評完成日期</td>
			<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field", "assessmentCompleteDate", obj.getAssessmentCompleteDate())%>
			</td>
			<td nowrap class="td_form">案件分類</td>
			<td nowrap class="td_form_white" colspan="3">
				<select class="field" name="caseType" type="select">
					<%=View.getOptionCodeKind("HFRCKD", obj.getCaseType()) %>
				</select>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">非預期反應分類</td>
			<td nowrap class="td_form_white" colspan="3">
				<select class="field" name="unExpectedClassify" type="select">
					<%=View.getOptionCodeKind("HFRFUC", obj.getUnExpectedClassify()) %>
				</select>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">非預期反應原因</td>
			<td class="td_form_white" colspan="3">
				<%=View.getCommonCodeKindBoxOption("field", "unExpectedReason", "HFRNRS", obj.getUnExpectedReason()) %>
				，說明
				<input class="field" type="text" name="unPreExplain" size="50" maxlength="50" value="<%=obj.getUnPreExplain()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">證據性</td>
			<td nowrap class="td_form_white">
				<select class="field" name="evidentiary" type="select">
					<%=View.getOptionCodeKind("HFRFEV", obj.getEvidentiary()) %>
				</select>
			</td>
			<td nowrap class="td_form">行政處置層級</td>
			<td nowrap class="td_form_white">
				<select class="field" name="administrativeLevel" type="select">
					<%=View.getOptionCodeKind("HFRDRSP", obj.getAdministrativeLevel()) %>
				</select>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">嚴重程度</td>
			<td nowrap class="td_form_white" colspan="3">
				<select class="field" name="preSeverity" type="select">
					<%=View.getOptionCodeKind("HFRSVR", obj.getPreSeverity()) %>
				</select>
				，說明
				<input class="field" type="text" name="preExplain" size="50" maxlength="50" value="<%=obj.getPreExplain()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">承辦備註</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="preRemark" size="100" maxlength="100" value="<%=obj.getPreRemark()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">評估意見</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="preOpinion" rows="3" cols="50"><%=obj.getPreOpinion() %></textarea>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">相關附件</td>
			<td nowrap class="td_form_white" colspan="3">
				<jsp:include page="hfrin0410f.jsp">
					<jsp:param value="HFRFO" name="fileType"/>
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

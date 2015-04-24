<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.hfrin.HFRIN0803F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
request.setCharacterEncoding("UTF-8");
String id = Common.get(request.getParameter("id"));
obj.setId(id);

obj = (com.kangdainfo.tcbw.view.hfrin.HFRIN0803F)obj.queryOne();
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
	<%=obj.getHFRREFileRowSBuilder()%>
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
			<td nowrap class="td_form" style="text-align:center" colspan="4">複評資訊</td>
	    </tr>
		<tr>
			<td nowrap class="td_form">委員會會期</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="committeeMeeting" size="10" maxlength="10" value="<%=obj.getCommitteeMeeting() %>">
			</td>
			<td nowrap class="td_form">委員會召開日期</td>
			<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field", "committeeDate", obj.getCommitteeDate())%>
		 	</td>
		</tr>
		<tr>
			<td nowrap class="td_form">會議紀錄發文日期</td>
			<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field", "recordDate", obj.getRecordDate())%>
			</td>
			<td nowrap class="td_form">案件回饋日期</td>
			<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field", "caseBackDate", obj.getCaseBackDate())%>
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
				<input class="field" type="text" name="unThiExplain" size="50" maxlength="50" value="<%=obj.getUnThiExplain()%>">
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
				<select class="field" name="thiSeverity" type="select">
					<%=View.getOptionCodeKind("HFRSVR", obj.getThiSeverity()) %>
				</select>
				，說明
				<input class="field" type="text" name="thiExplain" size="50" maxlength="50" value="<%=obj.getThiExplain()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">複評結果</td>
			<td nowrap class="td_form_white" colspan="3">
				<select class="field" name="reEvaluateResult" type="select">
					<%=View.getTextOption("1;結案;2;重新評估;", obj.getReEvaluateResult()) %>
				</select>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">相關附件</td>
			<td nowrap class="td_form_white" colspan="3">
				<jsp:include page="hfrin0410f.jsp">
					<jsp:param value="HFRRE" name="fileType"/>
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

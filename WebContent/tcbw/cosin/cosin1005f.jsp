<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="COSIN1001" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN1003F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
obj = (com.kangdainfo.tcbw.view.cosin.COSIN1003F)obj.queryOne();
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
	chFirstResult("<%=obj.getFirstResult()%>");
});

function chFirstResult(val){
	if(val == "02"){
		$("#FR1").show();
		$("#FR2").hide();
		$("[name=leaveCaseReason]").val("");
	}else if(val == "03"){
		$("#FR2").show();
		$("#FR1").hide();
		$("[name=nonDefective]").val("");
	}else{
		$("#FR1").hide();
		$("[name=nonDefective]").val("");
		$("#FR2").hide();
		$("[name=leaveCaseReason]").val("");
	}
}
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
		<td nowrap class="td_form" colspan="3" style="text-align:center">分類歷史資料</td>
	</tr>
	<tr>
		<td nowrap class="td_form">初步判定結果</td>
		<td nowrap class="td_form_white" colspan="3">
			<select class="field" name="firstResult" type="select" onChange="chFirstResult(this.value);">
				<%=View.getOptionCodeKind("CFR", obj.getFirstResult()) %>
			</select>
			<span id="FR1">
				，非不良品原因
				<input class="field" type="text" name="nonDefective" size="50" maxlength="90" value="<%=obj.getNonDefective()%>">
			</span>
			<span id="FR2">
				，留案備查理由
				<input class="field" type="text" name="leaveCaseReason" size="50" maxlength="90" value="<%=obj.getLeaveCaseReason()%>">
			</span>
			<%--
			<select class="field" name="firstResult" type="select">
				<%=View.getOptionCodeKind("CFR", obj.getFirstResult()) %>
			</select>
			，
			非不良品原因
			<input class="field_RO" type="text" name="nonDefective" size="50" maxlength="90" value="<%=obj.getNonDefective()%>">
			 --%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">調查摘要</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field" type="checkbox" name="dMarked" value="Y" <%=obj.getdMarked().equals("Y")?"checked":"" %>>標示問題
			<br>
			
			<input class="field" type="checkbox" name="dLawlessIng" value="Y" <%=obj.getdLawlessIng().equals("Y")?"checked":"" %>>疑似含有不法或其他有效成分，
			說明
			<input class="field" type="text" name="dLawlessIngOther" size="30" maxlength="50" value="<%=obj.getdLawlessIngOther()%>">
			<br>
			
			<input class="field" type="checkbox" name="dExteriorError" value="Y" <%=obj.getdExteriorError().equals("Y")?"checked":"" %>>外觀異常，
			說明
			<input class="field" type="text" name="dExteriorErrorOther" size="30" maxlength="50" value="<%=obj.getdExteriorErrorOther()%>">
			<br>
			
			<input class="field" type="checkbox" name="dPackageError" value="Y" <%=obj.getdPackageError().equals("Y")?"checked":"" %>>包裝瑕疵，
			說明
			<input class="field" type="text" name="dPackageErrorOther" size="30" maxlength="50" value="<%=obj.getdPackageErrorOther()%>">
			<br>
			
			<input class="field" type="checkbox" name="dExpired" value="Y" <%=obj.getdExpired().equals("Y")?"checked":"" %>>過期，
			說明
			<input class="field" type="text" name="dExpiredOther" size="30" maxlength="50" value="<%=obj.getdExpiredOther()%>">
			<br>
			
			<input class="field" type="checkbox" name="dOthers" value="Y" <%=obj.getdOthers().equals("Y")?"checked":"" %>>其他，
			說明
			<input class="field" type="text" name="dOthersDesc" size="30" maxlength="50" value="<%=obj.getdOthersDesc()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">採取措施</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getCommonCheckBoxTextOption("field", "measure", "CMS", obj.getMeasure()) %>
		</td>
	</tr>
	</table>	
	</div>
</td></tr>
</table>
</form>
</body>
</html>
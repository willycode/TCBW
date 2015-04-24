<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="COSIN1001" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN1004F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
obj = (com.kangdainfo.tcbw.view.cosin.COSIN1004F)obj.queryOne();
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
<%--	
	doShow1("<%=obj.getReactionLev()%>");
	doShow2("<%=obj.getIsComplaintYn()%>");
	doShow3("<%=obj.getdIsOtherDeptYn()%>");
--%>	
	chPreResult("<%=obj.getPreResult()%>");
});

function doShow1(val){
	var s = "";
	if(val == "1"){
		$(":radio[name=adverseCondition]").each(function(){
			if($(this).attr("checked") && $(this).val()!="99"){
				s += $(this)[0].nextSibling.nodeValue;
			}
		});
		$("#show1").empty().show().append($("<font color='red'>" + s + "</font>"));
	}else if(val == "2"){
		$(":radio[name=adverseCondition]").each(function(){
			if($(this).attr("checked") && $(this).val()=="99"){
				s += $(this).next().val();
			}
		});
		$("#show1").empty().show().append($("<font color='red'>" + s + "</font>"));
	}else{
		$("#show1").empty();
		$("#show1").hide();
	}
}	

function doShow2(val){
	if(val == "Y"){
		var s = "";
		$(":radio[name=dealWith]").each(function(){
			if($(this).attr("checked")){
				s += $(this)[0].nextSibling.nodeValue;
			}
		});
		$("#show2").show().append($("<font color='red'>" + s + "</font>"));
	}else{
		$("#show2").empty();
		$("#show2").hide();
	}	
}

function doShow3(val){
	if(val == "Y"){
		var s = $(":text[name=otherDpetName]").val();
		$("#show3").show().append($("<font color='red'>" + s + "</font>"));
	}else{
		$("#show3").empty();
		$("#show3").hide();
	}
}


function chPreResult(val){
	if(val == "02"){
		$("#COSFR1").show();	
	}else{
		$("#COSFR1").hide();	
		$("[name=leftCaseReason]").val("");
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
		<td nowrap class="td_form" colspan="4" style="text-align:center">分類歷史資料</td>
	</tr>
	<tr>
		<td nowrap class="td_form">初步判定結果</td>
		<td nowrap class="td_form_white" colspan="3">
			<select class="field" name="preResult" type="select" onChange="chPreResult(this.value)">
				<%=View.getOptionCodeKind("COSFR", obj.getPreResult()) %>
			</select>
			<span id="COSFR1">
				，留案備查理由
				<input class="field" type="text" name="leftCaseReason" size="60" maxlength="80" value="<%=obj.getLeftCaseReason()%>">
			</span>
			<input type="hidden" name="cos0008DbId" value="<%=obj.getCos0008DbId()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">通報資料完整性</td>
		<td nowrap class="td_form_white">
			<%=View.getYNRadioBoxTextOption("field", "isCompleteYn", obj.getIsCompleteYn()) %>
		</td>
		<td nowrap class="td_form">是否聯絡通報者取得完整資料</td>
		<td nowrap class="td_form_white">
			<%=View.getYNRadioBoxTextOption("field", "dIsContactYn", obj.getdIsContactYn()) %>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">不良反應嚴重程度</td>
		<td nowrap class="td_form_white" colspan="3">
			<select class="field" name="reactionLev" type="select" onChange="doShow1(this.value);">
				<%=View.getTextOption("1;嚴重不良反應;2;非嚴重不良反應", obj.getReactionLev()) %>	
			</select>
			&nbsp;&nbsp;
			<span id="show1"></span>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">時序性</td>
		<td nowrap class="td_form_white">
			<select class="field" name="timingLev" type="select">
				<%=View.getOptionCodeKind("COSTIM", obj.getTimingLev()) %>
			</select>
		</td>
		<td nowrap class="td_form">過往通報紀錄</td>
		<td nowrap class="td_form_white">
			<select class="field" name="previousNotify" type="select">
				<%=View.getTextOption("Y;有;N;無", obj.getPreviousNotify()) %>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">是否連絡廠商進行客訴</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getYNRadioBoxTextOption("field", "isComplaintYn", obj.getIsComplaintYn()) %>
			
			&nbsp;&nbsp;
			<span id="show2"></span>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">是否送交相關單位處理</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getYNRadioBoxTextOption("field", "dIsOtherDeptYn", obj.getdIsOtherDeptYn()) %>
			
			&nbsp;&nbsp;
			<span id="show3"></span>
		</td>
	</tr>
	</table>	
	</div>
</td></tr>
</table>
</form>
</body>
</html>
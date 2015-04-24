<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosex.COSEX0104F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>

<%
if("doCaseReply".equals(obj.getState())){
	try{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doReplyUpdate();
	}catch(Exception e){
		obj.setActionMsg("replyError");
		e.printStackTrace();
	}
}

obj = (com.kangdainfo.tcbw.view.cosex.COSEX0104F)obj.queryOne();
obj.setStateQueryOneSuccess();
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
	alertStr += validateATTFileTable();
	alertStr += checkEmpty(form1.replyBody, "補件回覆");
	if(alertStr.length > 0){
		alert(alertStr);
		return;
	}
	beforeSubmit();
	form1.submit();
}

function init() {
	if("<%=obj.getErrorMsg()%>" != ""){
		alert("<%=obj.getErrorMsg()%>");
		if("<%=obj.getActionMsg()%>" == "B"){
			opener.goBackQuery();
		}
		window.close();
	}else{
		form1.replyDate.value = "<%=Datetime.getYYYMMDD()%>";
		setAllOpen();
	}
}

$(function(){
	setDisplayItem('spanInsert,spanUpdate,spanQueryAll,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden','H');
});

</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');showErrorMsg('');init();">
<form id="form1" name="form1" method="post" autocomplete="off">
<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td nowrap class="td_form" width="25%">通知補件日期</td>
		<td nowrap class="td_form_white" width="75%">
			<input class="field_RO" type="text" name="" size="7" maxlength="7" value="<%=obj.getNotifyDate()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">補件說明</td>
		<td nowrap class="td_form_white">
			<textarea class="field_RO" name="notifyBody" cols="60" rows="5"><%=obj.getNotifyBody()%></textarea>
		</td>		
	</tr>
	<tr>
		<td nowrap class="td_form">補件回覆日期</td>
		<td nowrap class="td_form_white">
			<input class="field_RO" type="text" name="replyDate" size="7" maxlength="7">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">補件回覆</td>
		<td nowrap class="td_form_white">
			<textarea class="field" name="replyBody" cols="60" rows="5"></textarea>
		</td>		
	</tr>
	<tr>
		<td nowrap class="td_form">相關附件</td>
		<td nowrap class="td_form_white">
			<jsp:include page="cosex0109f.jsp"/>
		</td>
	</tr>
</table>

<!--Toolbar區============================================================-->
<div style="align:center">
<table width="100%" cellspacing="0" cellpadding="0">
	<tr><td nowrap class="bgToolbar" style="text-align:center">
		<input type="hidden" name="state" value="<%=obj.getState()%>">
		<input type="hidden" name="editID" value="<%=obj.getEditID()%>">
		<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
		
		<input type="hidden" name="cos4001DbId" value="<%=obj.getCos4001DbId()%>">
		<input type="hidden" name="con0004DbId" value="<%=obj.getCon0004DbId()%>">
		<input type="hidden" name="applNo" value="<%=obj.getApplNo()%>">
		<jsp:include page="../../home/toolbar.jsp"/>
		<span id="spanDoCaseReply">
			<input class="toolbar_default" type="button" followPK="false" name="doCaseReply" value="確 認 回 覆" onClick="whatButtonFireEvent(this.name)">&nbsp;
		</span> 
	</td></tr> 
</table>
</div>
		
</form>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch(buttonName){
	  case "doCaseReply":
		  form1.state.value = buttonName;
		  checkField();
		  break;
	}
}
</script>
</body>
</html> 


<!--
程式目的：案件重新校正
程式代號：
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%@ page import="java.io.*" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN0903F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="obj0202" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN0202F">
	<jsp:setProperty name="obj0202" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<% 
String id = Common.get(request.getParameter("id"));
String applNo = Common.get(request.getParameter("applNo"));
String eventKind = Common.get(request.getParameter("eventKind"));
obj.setId(Common.get(id));
obj.setApplNo(Common.get(applNo));


if("doConfirm".equals(obj.getState())) {
	try
	{
		obj.enforceClose();
		obj0202.setErrorMsg("強制結案完成");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}

}
//obj0202 = (com.kangdainfo.tcbw.view.medin.MEDIN0202F)obj0202.queryOne();

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
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">
function init() 
{
	if("Y"==form1.isEnd.value)
	{
		window.close();
		window.opener.doBack();	
	}
	setDisplayItem("spanQueryAll,spanInsert,spanClear,spanConfirm,spanUpdate,spanDelete,spanListPrint,spanListHidden","H");
	listContainerRowClick(0);

}

function checkField()
{	
	var alertStr = "";
	alertStr += checkEmpty(form1.enforceCloseReason, "強制結案理由");
	if(alertStr.length!=0){ alert(alertStr); return false; }
	form1.submit();

	return true;
}

</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" autocomplete="off" >
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
	<td class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" height="100%">
	    <tr>
			<td nowrap class="td_form_left">案件強制結案</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="15%">強制結案理由</td>
			<td nowrap class="td_form_white" width="35%">
                <textarea class="field_Q" name="enforceCloseReason" cols="60" rows="5" maxlength="50"><%=obj.getEnforceCloseReason()%></textarea>
			</td>
		</tr>
	</table>
	</div>
</tr>
<!--Toolbar============================================================-->
<tr>
<td nowrap class="bgToolbar"  style="text-align: center">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="doType">
	<input type="hidden" name="isEnd" value="<%=obj.getIsEnd()%>">
	<jsp:include page="../../home/toolbar.jsp" />

    <input class="toolbar_default" followPK="false" type="button" name="doConfirm" value="確　　認" onClick="whatButtonFireEvent(this.name)">&nbsp;
    <input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="window.close()">
</td>
</tr>

</table>

</form>
</body>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName)
{
	switch (buttonName)
	{
	case "doConfirm":
		form1.state.value = buttonName;
		checkField();
		break;
	}
}

</script>
</html>

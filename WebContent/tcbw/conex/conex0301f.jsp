<!--
程式目的：通報表登錄
程式代號：conex0301f.jsp
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%@ page import="java.io.*" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="CONEX0301" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.conex.CONEX0301F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
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
	setDisplayItem("spanQueryAll,spanInsert,spanClear,spanConfirm,spanUpdate,spanDelete,spanListPrint,spanListHidden","H");
	listContainerRowClick(0);
}


function setValue(sysKind,q_con1007_id)
{
	var obj1 = q_con1007_id;
	var oldDeptValue = q_con1007_id.value;
	obj1.options.length=0;
	obj1.options.add(new Option("請選擇",""));	

	var x = getRemoteData(getVirtualPath() + "/ajax/jsonConex0301f.jsp?sysKind="+sysKind.value,"");
	
	if (x!=null && x.length>0) 
	{
		var json = eval('(' + x + ')'); 
		var i = 0;
		for (i=0; i<json.length; i++) 
		{
			if(json[i].id==null)
				continue;
			var astId =  json[i].id;			
			var oOption = new Option(json[i].formdName,astId);
			obj1.options.add(oOption);
	    	if(astId == oldDeptValue) oOption.selected=true;			
		}
		obj1.disabled = false;
	}
}


function toCreate()
{
	if(form1.q_sysKind.value == "") {
			alert("未選擇系統別");
	} else if(form1.q_con1007_id.value == 1) {
		form1.action="../drgex/drgex0101f.jsp";
	} else if(form1.q_con1007_id.value == 2) {
		form1.action="../drgex/drgex0301f.jsp?caseType=1";
	} else if(form1.q_con1007_id.value == 3) {
		form1.action="../hfrex/hfrex0101f.jsp";
	} else if(form1.q_con1007_id.value == 4) {
		form1.action="../cosex/cosex0101f.jsp?q_type=L";
	} else if(form1.q_con1007_id.value == 10) {
		form1.action="../medex/medex0101f.jsp";
	} else if(form1.q_con1007_id.value == 7) {
		form1.action="../cosex/cosex0101f.jsp?q_type=L";
	} else if(form1.q_con1007_id.value == 9) {
		form1.action="../medex/medex5101f.jsp";
	} else if(form1.q_con1007_id.value == "") {
		alert("未選擇表單");
	} else {
		alert("該表單不存在");
	}
	beforeSubmit();
	form1.submit();
	//window.opener.toInsert();
	window.close();
}




</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off" >

<table width="100%" cellspacing="0" cellpadding="0" >
<tr>
	<td class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" height="100%">
	<tr>
		<td nowrap class="queryTDLable">系統別：</td>
		<td nowrap class="queryTDInput" width="65%">
			<select name="q_sysKind" class="field_Q" type="select" onchange="setValue(this,q_con1007_id);">
	    		<%=View.getOption("select codeId,codeName from CommonCode where commonCodeKind.codeKindId = \'SYS\' and codeId in (\'DRG\',\'HFR\',\'COS\',\'MED\') and (isStop is null or isStop = \'\' or isStop <> \'Y\') order by codeId",obj.getQ_con1007_id(),false,1)%>
	    	</select>
	    </td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">通報表單名稱：</td>
		<td nowrap class="queryTDInput" width="65%">
			<select name="q_con1007_id" disabled class="field_Q" type="select">
	    		<%=View.getOption("select id,formdName from Con1007Db",obj.getQ_con1007_id(),false,1)%>
	    	</select>
		</td>
	</tr>
	</table>
	</div>
</tr>
<!--Toolbarå============================================================-->
<tr>
<td nowrap class="bgToolbar"  style="text-align: center">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="doType">
	<jsp:include page="../../home/toolbar.jsp" />
	<span id="toCreate">
    	<input class="toolbar_default" type="button" followPK="false"  name="toCreate1" value="確       定"  onClick="toCreate();">&nbsp;
    </span> 
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
	case "queryAll":
		break;
	}
}
</script>
</html>

<!--
程式目的：
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
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="MEDEX0101" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.medex.MEDEX0101F">
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

function toCreate()
{
	var eventKind=form1.eventKind.value;
	var eventKind1=form1.eventKind1.value;
	//檢查是否有勾選其中一項	
	if(!form1.eventKind.checked && !form1.eventKind1.checked){
		alert("不良事件必須選其中一項!");
	}else{
		if(form1.eventKind.checked)
		       opener.document.all.item("talbe3").value=eventKind;
		
			if(form1.eventKind1.checked)
			   opener.document.all.item("talbe4").value=eventKind1;
			
			window.opener.toInsert();
			window.close();
	}
}




</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off" >
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
	<td class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" height="100%">
	   <tr>
           <td>
                                        不良反應:<input class="field_Q" type="checkbox" name="eventKind" value="1">
              <br>
              <%=View.getLookupField("select formDesc from Con1016Db where systemType='MED' and formType = '01' ")%><br>
           </td>
       </tr>
       <tr>
           <td>
                                       不良品:<input class="field_Q" type="checkbox" name="eventKind1" value="2">
              <br><%=View.getLookupField("select formDesc from Con1016Db where systemType='MED' and formType = '02' ")%><br>                    
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
	<input type="hidden" name="caseType" value="<%=obj.getCaseType()%>">
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

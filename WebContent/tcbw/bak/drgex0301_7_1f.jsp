<!--
程式目的：藥品療效不等通報登錄作業
程式代號：drgex0101
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgex.DRGEX0301F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	//obj = (com.kangdainfo.tcbw.view.drgex.DRGEX0301F)obj.queryOne();	
}else if ("insert".equals(obj.getState()) || "insertError".equals(obj.getState())) {
	obj.insert();
}else if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())) {
	obj.update();
}else if ("delete".equals(obj.getState()) || "deleteError".equals(obj.getState())) {
	obj.delete();
}

objList = (java.util.ArrayList) obj.queryAll();

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
<script type="text/javascript">

var insertDefault = new Array();

function init() {
	setDisplayItem("spanQueryAll,spanInsert,spanClear,spanConfirm,spanUpdate,spanDelete,spanListPrint,spanListHidden","H");		
}

function checkURL(surl){
	var alertStr = "";	
	//alertStr += checkEmpty(form1.applyNo,"主檔編號");
	if(alertStr.length != 0){
		alert("請先執行查詢, 若已查到資料則請選取其中一筆資料");
		return false;
	} else {
		form1.state.value="";
	}
	form1.action = surl;
	beforeSubmit();
	form1.submit();
	
}

</script>
</head>

<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getId()%>">
<table width="100%" border="0" CELLPADDING="0" CELLSPACING="2" align="center">
<tr><td nowrap>
<jsp:include page="../../home/toolbar.jsp">
	<jsp:param value="N" name="btnCopy"/>
</jsp:include>
</td></tr>
</table>
<tr><td nowrap>
  <% request.setAttribute("QueryBean",obj);%>
  <jsp:include page="../../home/page_row.jsp" />
</td></tr>
</table>
<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">
<table width="100%" cellspacing="0" cellpadding="0">
	<tr class="td_form">
		<td nowrap class="td_form_left" >各醫療院所療效不等狀況統計</td>
	</tr>
</table>
<div>	
	<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" rowspan="2">回覆日期</th>
		<th class="listTH" rowspan="2">醫療院所名稱</th>
		<th class="listTH" colspan="3">藥品成分_Acetaminophen_是否有療效不等狀況</th>		
		<th class="listTH" rowspan="2">補充說明</th>
	</tr>
	<tr>
		<th class="listTH" width="20%">是</th>
		<th class="listTH" width="20%">否(近年來有更換廠牌，但無療效不等狀況)</th>
		<th class="listTH" width="20%">否(近年來未更換廠牌)</th>
	</tr>
	<tr >
			<td nowrap class="listTROdd">102/09/01</td>
			<td nowrap class="listTROdd">臺大醫院</td>
			<td nowrap class="listTROdd">2件</td>
			<td nowrap class="listTROdd">0件</td>
			<td nowrap class="listTROdd">0件</td>
			<td nowrap class="listTROdd"></td>
    </tr>
    <tr>
			<td nowrap class="listTREven">102/09/11</td>
			<td nowrap class="listTROdd">臺大醫院</td>
			<td nowrap class="listTROdd">2件</td>
			<td nowrap class="listTROdd">0件</td>
			<td nowrap class="listTROdd">0件</td>
			<td nowrap class="listTROdd">緊急事件</td>
    </tr>
    <tr>
			<td nowrap class="listTREven">共計</td>
			<td nowrap class="listTROdd">總共2家</td>
			<td nowrap class="listTROdd">2件</td>
			<td nowrap class="listTROdd">0件</td>
			<td nowrap class="listTROdd">0件</td>
			<td nowrap class="listTROdd"></td>
    </tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {false,false,false,false,false,false};
	    boolean displayArray[] = {true,true,true,true,true,true};
	    String[] alignArray  =  {"center","center","center","center","center","center"};
		out.write(View.getQuerylist(primaryArray,displayArray,alignArray,objList,obj.getQueryAllFlag()));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName)	{
		case "insert":
			break;		
	}
}
</script>
</body>
</html>

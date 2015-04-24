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
	setDisplayItem("spanConfirm,spanDelete,spanListPrint,spanListHidden","H");		
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

function show(a){
	var obj = document.getElementById(a.name+"1");
	if(a.checked){
		obj.style.display="block";
	}else{
		obj.style.display="none";
	}
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
<span id="spanSave">
    <input class="toolbar_default" type="button" followPK="true"  name="btnSave" value="暫   存" onClick="">&nbsp;
</span>
<span id="spanMaintain">
    <input class="toolbar_default" type="button" followPK="true"  name="btnMaintain" value="送   出" onClick="">&nbsp;
</span>
<span id="spanReUpdate">
    <input class="toolbar_default" type="button" followPK="true"  name="btnReUpdate" value="補件回復" onClick="">&nbsp;
</span>
</td></tr>
</table>
</td></tr>

<tr><td nowrap>
  <% request.setAttribute("QueryBean",obj);%>
  <jsp:include page="../../home/page_row.jsp" />
</td></tr>
</table>
<TABLE CELLPADDING=0 CELLSPACING=0>
	<TR>		
		<td nowrap ID=t1 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('drgex0301f.jsp');">基本資料</A></TD>		
		<td nowrap ID=t2 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('drgex0301_2f.jsp');">療效不等反應</A></TD>
		<td nowrap ID=t3 CLASS="tab_border1">使用藥品</TD>
		<td nowrap ID=t4 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('drgex0301_4f.jsp');">相關附件</A></TD>
		<td nowrap ID=t5 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('drgex0301_5f.jsp');">案件初評表</A></TD>
		<td nowrap ID=t6 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('drgex0301_6f.jsp');">諮議會評估</A></TD>
		<td nowrap ID=t7 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('drgex0301_7f.jsp');">醫院調查</A></TD>
	</TR>
	<TR>
		<td nowrap ID=t8 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('drgex0301_8f.jsp');">廠商回覆</A></TD>	
		<td nowrap ID=t9 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('drgex0301_9f.jsp');">品質調查評估</A></TD>
		<td nowrap ID=t10 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('drgex0301_10f.jsp');">歷史通報</A></TD>
		<td nowrap ID=t11 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('drgex0301_11f.jsp');">歷史版次</A></TD>
		<td nowrap ID=t12 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('drgex0301_12f.jsp');">案件補件</A></TD>
		<td nowrap ID=t13 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('drgex0301_13f.jsp');">郵件清單紀錄</A></TD>
		<td nowrap ID=t14 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('drgex0301_14f.jsp');">案件流程紀錄</A></TD>
	</TR>
</TABLE>
<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">	
<div>
	<table width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form_left" >事件前使用藥品</td>
		</tr>
		<tr>
			<td nowrap style="text-align:left;">
				<table width="100%" class="table_form">
					<tr>
						<td nowrap class="listTH">學名</td>
						<td nowrap class="listTH">商品名</td>
						<td nowrap class="listTH">廠牌</td>
						<td nowrap class="listTH">批號</td>
						<td nowrap class="listTH">含量</td>
						<td nowrap class="listTH">劑型</td>
						<td nowrap class="listTH">給藥途徑</td>
						<td nowrap class="listTH">劑量</td>
						<td nowrap class="listTH">頻率</td>
						<td nowrap class="listTH">起始日期</td>
						<td nowrap class="listTH">結束日期</td>
						<td nowrap class="listTH">適應症</td>
					</tr>
					<tr>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
					</tr>
				</table>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form_left" >懷疑療效不等藥品</td>
		</tr>
		<tr>
			<td nowrap style="text-align:left;">
				<table width="100%" class="table_form">
					<tr>
						<td nowrap class="listTH">學名</td>
						<td nowrap class="listTH">商品名</td>
						<td nowrap class="listTH">廠牌</td>
						<td nowrap class="listTH">批號</td>
						<td nowrap class="listTH">含量</td>
						<td nowrap class="listTH">劑型</td>
						<td nowrap class="listTH">給藥途徑</td>
						<td nowrap class="listTH">劑量</td>
						<td nowrap class="listTH">頻率</td>
						<td nowrap class="listTH">起始日期</td>
						<td nowrap class="listTH">結束日期</td>
						<td nowrap class="listTH">適應症</td>
					</tr>
					<tr class="contentA">
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
					</tr>
				</table>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form_left" >其他併用藥品</td>
		</tr>
		<tr>
			<td nowrap style="text-align:left;">
				<table width="100%" class="table_form">
					<tr>
						<td nowrap class="listTH">學名</td>
						<td nowrap class="listTH">商品名</td>
						<td nowrap class="listTH">廠牌</td>
						<td nowrap class="listTH">批號</td>
						<td nowrap class="listTH">含量</td>
						<td nowrap class="listTH">劑型</td>
						<td nowrap class="listTH">給藥途徑</td>
						<td nowrap class="listTH">劑量</td>
						<td nowrap class="listTH">頻率</td>
						<td nowrap class="listTH">起始日期</td>
						<td nowrap class="listTH">結束日期</td>
						<td nowrap class="listTH">適應症</td>
					</tr>
					<tr>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
						<td nowrap class="listTROdd">xx</td>
					</tr>
				</table>
			</td>		
		</tr>
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

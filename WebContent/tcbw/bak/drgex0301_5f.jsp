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
		<td nowrap ID=t3 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('drgex0301_3f.jsp');">使用藥品</A></TD>		
		<td nowrap ID=t4 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('drgex0301_4f.jsp');">相關附件</A></TD>
		<td nowrap ID=t5 CLASS="tab_border1">案件初評表</TD>
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
			<td nowrap class="td_form" width="20%">評估日期</td>
			<td nowrap class="td_form_white" style="text-align:left;" width="80%">
				<%=View.getPopCalendar("field_Q","q_dateS","")%>
			</td>
		</tr>
		<tr> 
			<td nowrap class="td_form" width="20%">藥品成分資訊</td>
			<td nowrap class="td_form_white" style="text-align:left;" width="80%">
				<input type="checkbox" name="s1">NTI  Drugs&nbsp;&nbsp;
				<input type="checkbox" name="s1">藥理治療分類 (ATC code)&nbsp;&nbsp;
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="20%">通報事件之後果</td>
			<td nowrap class="td_form_white" style="text-align:left;" width="80%">
				<input type="radio" name="s1" id="a" onclick="show(this);">
				藥效改變--<input type="radio" name="s2">增強&nbsp;&nbsp;<input type="radio" name="s2">減弱&nbsp;&nbsp;
				<br>
				<input type="radio" name="s1" id="b" onclick="show(this);">不良反應發生、強度增強或頻率增加&nbsp;&nbsp;
				<br>
				<div id="b1" style="display:none">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" name="s1">非嚴重可預期&nbsp;&nbsp;
					<input type="checkbox" name="s1">非嚴重非預期&nbsp;&nbsp;
					<input type="checkbox" name="s1">嚴重可預期&nbsp;&nbsp;
					<input type="checkbox" name="s1">嚴重非預期&nbsp;&nbsp;
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;
					不良反應：<input type="text" size="10">、MedDRA coding：<input type="text" size="10">&nbsp;&nbsp;
				</div>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="20%">相關性評估</td>
			<td nowrap class="td_form_white" style="text-align:left;" width="80%">
				<div id="a1" style="display:none">
					轉換藥品與藥效不等發生有合理的時序性?
					<input type="radio" name="s3">是<input type="radio" name="s3">否<input type="radio" name="s3">不知<br>
					藥效不等與病人本身疾病、生理狀態或併用藥物有關?
					<input type="radio" name="s4">是<input type="radio" name="s4">否<input type="radio" name="s4">不知<br>
					新藥調整劑量或換回舊藥後維持先前藥效?
					<input type="radio" name="s5">是<input type="radio" name="s5">否<input type="radio" name="s5">不知
				</div>
				<div id="b2" style="display:none">
					轉換藥品與ADR發生有合理的時序性?
					<input type="radio" name="s3">是<input type="radio" name="s3">否<input type="radio" name="s3">不知<br>
					ADR與病人本身疾病、生理狀態或併用藥物有關? 
					<input type="radio" name="s4">是<input type="radio" name="s4">否<input type="radio" name="s4">不知<br>
					停藥後ADR減輕或消失? 
					<input type="radio" name="s5">是<input type="radio" name="s5">否<input type="radio" name="s5">不知
				</div>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="20%">評估結果</td>
			<td nowrap class="td_form_white" style="text-align:left;" width="80%">
				<input type="checkbox" name="s1">幾乎確定(Certain)&nbsp;&nbsp;
				<input type="checkbox" name="s1">極有可能(Probable)&nbsp;&nbsp;
				<input type="checkbox" name="s1">可能(Possible)&nbsp;&nbsp;
				<input type="checkbox" name="s1">存疑(Unlikely)&nbsp;&nbsp;
				<br>
				<input type="checkbox" name="s1">不相關(Unrelated)&nbsp;&nbsp;
				<input type="checkbox" name="s1">資料不全(Unclassified)&nbsp;&nbsp;
				<input type="checkbox" name="s1">無法評估(Unclassifiable)&nbsp;&nbsp;
				<br>
				評估註記：<input type="text" value="" />
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="20%">通報時效</td>
			<td nowrap class="td_form_white" style="text-align:left;" width="80%">
				間隔天數：<input type="text" size="3">天&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="s2">時效佳&nbsp;&nbsp;
				<input type="radio" name="s2">時效待加強&nbsp;&nbsp;
			</td>
		</tr>
		<tr class="contentA">
			<td nowrap class="td_form" width="20%">通報品質</td>
			<td nowrap class="td_form_white" style="text-align:left;" width="80%">
				<input type="radio" name="s3">Excellent report&nbsp;&nbsp;
				<input type="radio" name="s3">Good report&nbsp;&nbsp;
				<input type="radio" name="s3">Fair report&nbsp;&nbsp;
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="20%">歷史通報資料摘要</td>
			<td nowrap class="td_form_white" style="text-align:left;" width="80%">
				歷年本藥品之通報件數：<input class="field_RO" type="text" size="3">件<br>
				歷年本藥品Possible以上之通報件數：<input class="field_RO" type="text" size="3">件<br>
				一年內本藥品之通報件數：<input class="field_RO" type="text" size="3">件<br>
				一年內本藥品Possible以上之通報件數：[批號1]有<input class="field_RO" type="text" size="3">件、[批號2]有<input class="field_RO" type="text" size="3">件
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="20%">備註</td>
			<td nowrap class="td_form_white" style="text-align:left;" width="80%">
				<textarea name="textarea" cols="100" rows="3"></textarea>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="20%">檔案上傳</td>
			<td nowrap class="td_form_white" style="text-align:left;" width="80%">
				<input value="新增上傳附件" type="button"/><br>
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

<!--
程式目的：醫療器材不良事件通報登錄作業
程式代號：medex0101
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.medex.MEDEX0101F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	//obj = (com.kangdainfo.tcbw.view.medex.MEDEX0101F)obj.queryOne();	
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
		<td nowrap ID=t1 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101f.jsp');">基本資料</A></TD>		
		<td nowrap ID=t2 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_2f.jsp');">不良事件</A></TD>		
		<td nowrap ID=t3 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_3f.jsp');">不良反應</A></TD>		
		<td nowrap ID=t4 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_4f.jsp');">不良品</A></TD>		
		<td nowrap ID=t5 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_5f.jsp');">相關附件</A></TD>		
		<td nowrap ID=t6 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_6f.jsp');">轉送評估</A></TD>		
		<td nowrap ID=t7 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_7f.jsp');">不良品初評</A></TD>
		<td nowrap ID=t8 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_8f.jsp');">不良反應評估</A></TD>
	</TR>
	<TR>
	    <td nowrap ID=t9 CLASS="tab_border1">不良品廠商回覆</TD>		
		<td nowrap ID=t10 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_10f.jsp');">不良品複評</A></TD>
		<td nowrap ID=t11 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_11f.jsp');">歷史通報</A></TD>
		<td nowrap ID=t12 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_12f.jsp');">歷史版次</A></TD>
		<td nowrap ID=t13 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_13f.jsp');">案件補件</A></TD>
		<td nowrap ID=t14 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_14f.jsp');">郵件清單紀錄</A></TD>
		<td nowrap ID=t15 CLASS="tab_border2"><A HREF="#" ONCLICK="return checkURL('medex0101_15f.jsp');">案件流程紀錄</A></TD>
	</TR>
</TABLE>
<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">	


<div>
	<table width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form" width="20%">回覆內容</td>
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
</td>
</tr>
<tr>
<td nowrap>
<br>

<div>
	<table width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form_left" colspan="4">廠商通知資訊</td>
		</tr>
		<tr>			
			<td nowrap class="td_form_white"  colspan="4">
               <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	           <thead id="listTHEAD">       
	           <tr>
		         <th class="listTH" width="30%">檢驗日期</th>
		         <th class="listTH">轉送備註</th>
	           </tr>
	           </thead>
	           <tr>
		         <th class="listTROdd">101/10/18</th>
		         <th class="listTROdd">xxxx</th>
	           </tr>
               </table>
			</td>
		</tr>
		<tr><td nowrap><br></td></tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">廠商回覆資訊</td>
		</tr>
		<tr>			
			<td nowrap class="td_form_white"  colspan="4">
               <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	           <thead id="listTHEAD">       
	           <tr>
		         <th class="listTH" width="30%">檢驗日期</th>
		         <th class="listTH">轉送備註</th>
	           </tr>
	           </thead>
	           <tr>
		         <th class="listTROdd">101/10/18</th>
		         <th class="listTROdd">xxxx</th>
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

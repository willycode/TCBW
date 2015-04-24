<!--
程式目的：藥品不良品通報登錄作業
程式代號：drgex0101
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgex.DRGEX0101F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	//obj = (com.kangdainfo.tcbw.view.drgex.DRGEX0101F)obj.queryOne();	
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
<TABLE width="100%" CELLPADDING=0 CELLSPACING=0>
	<TR>
		<td nowrap nowrap CLASS="tab_border1" ID=t1>基本資料</TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t2><A HREF="#" ONCLICK="return checkURL('drgex0101_2f.jsp');">不良藥品資料</A></TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t3><A HREF="#" ONCLICK="return checkURL('drgex0101_3f.jsp');">其他相關附件</A></TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t4><A HREF="#" ONCLICK="return checkURL('drgex0101_4f.jsp');">案件初評</A></TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t5><A HREF="#" ONCLICK="return checkURL('drgex0101_5f.jsp');">案件分級確認</A></TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t6><A HREF="#" ONCLICK="return checkURL('drgex0101_6f.jsp');">CAPA確認表</A></TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t7><A HREF="#" ONCLICK="return checkURL('drgex0101_7f.jsp');">CAPA分析表</A></TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t8><A HREF="#" ONCLICK="return checkURL('drgex0101_8f.jsp');">CAPA評估表</A></TD>
	</TR>
	<TR>	
		<td nowrap nowrap CLASS="tab_border2" ID=t9><A HREF="#" ONCLICK="return checkURL('drgex0101_9f.jsp');">案件評估</A></TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t10><A HREF="#" ONCLICK="return checkURL('drgex0101_10f.jsp');">案件分析</A></TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t11><A HREF="#" ONCLICK="return checkURL('drgex0101_11f.jsp');">歷史通報</A></TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t12><A HREF="#" ONCLICK="return checkURL('drgex0101_12f.jsp');">歷史CAPA評估</A></TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t13><A HREF="#" ONCLICK="return checkURL('drgex0101_13f.jsp');">歷史版次</A></TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t14><A HREF="#" ONCLICK="return checkURL('drgex0101_14f.jsp');">案件補件</A></TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t15><A HREF="#" ONCLICK="return checkURL('drgex0101_15f.jsp');">郵件清單紀錄</A></TD>
		<td nowrap nowrap CLASS="tab_border2" ID=t16><A HREF="#" ONCLICK="return checkURL('drgex0101_16f.jsp');">案件流程紀錄</A></TD>
	</TR>
</TABLE>
<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">	
<div>
	<table width="100%" align="center" class="table_form">
		<tr>
			<td nowrap colspan="4" nowrap class="td_form_left">通報訊息</td>
		</tr>
		<tr>
			<td nowrap width="15%" nowrap class="td_form">發生日期</td>
			<td nowrap width="40%" nowrap class="td_form_white">
                <%=View.getPopCalendar("field","limitDate","1020621")%>			
            </td>
			<td nowrap width="15%" nowrap class="td_form">通報中心接獲通報日期</td>
			<td nowrap width="30%" nowrap class="td_form_white" >
                <%=View.getPopCalendar("field","limitDate","1020621")%>			
            </td>
		</tr>
		<tr>
			<td nowrap nowrap class="td_form">通報者獲知日期</td>
			<td nowrap colspan="3" nowrap class="td_form_white" >
				<%=View.getPopCalendar("field","limitDate","1020621")%>			
			</td>		
		</tr>
		<tr>
			<td nowrap nowrap class="td_form">通報來源</td>
			<td nowrap colspan="3" nowrap class="td_form_white" >
				<input type="radio" name="s1">醫院&nbsp;&nbsp;
				<input type="radio" name="s1">診所&nbsp;&nbsp;
				<input type="radio" name="s1">藥局&nbsp;&nbsp;
				<input type="radio" name="s1">廠商&nbsp;&nbsp;
				<input type="radio" name="s1">民眾&nbsp;&nbsp;
			  <input type="radio" name="s1">衛生單位(偽、劣藥)&nbsp;&nbsp;			
			</td>
		</tr>
		<tr>
			<td nowrap colspan="4" nowrap class="td_form_left">通報者資訊</td>
		</tr>	
		<tr>
			<td nowrap nowrap class="td_form">姓名</td>
			<td nowrap nowrap class="td_form_white" >
				<input type="text" value="王XX"  size="15" />			</td>
			<td nowrap nowrap class="td_form">服務機構</td>
			<td nowrap class="td_form_white" >
				<input type="text" value="XXX股份有限公司" size="30"/>			</td>			
		</tr>		
		<tr>
			<td nowrap nowrap class="td_form">電話</td>
			<td nowrap nowrap class="td_form_white" >
				<input type="text" value="00-0000000" size="15" />			
			</td>
			<td nowrap class="td_form">E-Mail</td>
			<td nowrap class="td_form_white">
				<input type="text" value="XX@kangdainfo.com" size="30"/>			
			</td>				
		</tr>	
		<tr>
			<td nowrap class="td_form">地址</td>
			<td nowrap colspan="3" nowrap class="td_form_white">
				<input type="text" value="XXX市XX路一段XX號" size="40" />			
			</td>
		</tr>
		<tr>
			<td nowrap nowrap class="td_form">屬性</td>
			<td nowrap nowrap class="td_form_white" colspan="3">				
				<input type="radio" name="s5">醫療人員&nbsp;&nbsp;
				<input type="radio" name="s5">廠商&nbsp;&nbsp;
				<input type="radio" name="s5">民眾&nbsp;&nbsp;
				<input type="radio" name="s5">衛生單位
			</td>			
		</tr>
		<tr>
			<td nowrap nowrap class="td_form">職稱</td>
			<td nowrap colspan="3" nowrap class="td_form_white">
			  <input type="radio" name="s2">醫師&nbsp;&nbsp;
			  <input type="radio" name="s2">藥師&nbsp;&nbsp;
			  <input type="radio" name="s2">護理人員&nbsp;&nbsp;
			  <input type="radio" name="s2">其他<input type="text" value=""  />			
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

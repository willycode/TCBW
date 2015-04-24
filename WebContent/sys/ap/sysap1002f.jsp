<!--
程式目的：化妝品參數設定檔
程式代號：sysap1002f
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.sys.ap.SYSAP1001F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>

<%
if (UserRole.isUserLevel()) return;

String qt = "1";
if("".equals(obj.getQ_id()))
	if (!"".equals(qt)) obj.setQ_id(qt);	
	
if("".equals(obj.getQ_id())){
	out.write("系統無法判斷參數類別，若問題持續,請洽詢系統管理者或相關承辦人員！");
	return;
}else{
	obj.setId(obj.getQ_id());
}

if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())) {
	obj.update();
} else {
	obj = (com.kangdainfo.tcbw.view.sys.ap.SYSAP1001F)obj.queryOne();	
}
%>

<html>
<head>
<meta http-equiv="Expires" content="-1"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript">
var insertDefault;	
function doUpdate(){
	form1.state.value = "update";
	form1.submit();
}
</script>
</head>

<body topmargin="0" onLoad="showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" autocomplete="off">

<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto;">
	<table class="table_form" width="100%" height="100%">
	<tr>
		<td nowrap class="td_form">廠商回覆稽催期限(第一次)：</td>
		<td nowrap colspan="3" class="td_form_white">
			<input class="field_Q" type="text" name="field1" size="50" maxlength="100" value="<%=obj.getField1()%>">天
		</td>	
	</tr>
	<tr>
		<td nowrap class="td_form">衛生單位回覆稽催期限(藍燈)：</td>
		<td nowrap colspan="3" class="td_form_white">
			<input class="field_Q" type="text" name="field2" size="50" maxlength="100" value="<%=obj.getField2()%>">月
		</td>	
	</tr>
	<tr>
		<td nowrap class="td_form">衛生單位回覆稽催期限(黃燈)：</td>
		<td nowrap colspan="3" class="td_form_white">
			<input class="field_Q" type="text" name="field4" size="50" maxlength="100" value="<%=obj.getField4()%>">月
		</td>	
	</tr>
	<tr>
		<td nowrap class="td_form">衛生單位回覆稽催期限(紅燈)：</td>
		<td nowrap colspan="3" class="td_form_white">
			<input class="field_Q" type="text" name="field5" size="50" maxlength="100" value="<%=obj.getField5()%>">月
		</td>	
	</tr>
	<tr>
		<td nowrap class="td_form">廠商回覆稽催期限(第二次)：</td>
		<td nowrap colspan="3" class="td_form_white">
			<input class="field_Q" type="text" name="field3" size="50" maxlength="100" value="<%=obj.getField3()%>">天
		</td>	
	</tr>
	<tr>
		<td nowrap class="td_form">異動人員/日期：</td>
		<td nowrap colspan="3" class="td_form_white">
			[<input class="field_RO" type="text" name="editID" size="10" value="<%=obj.getEditID()%>">/
			<input class="field_RO" type="text" name="editDate" size="7" value="<%=obj.getEditDate()%>">]</td>
	</tr>	
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bg" style="text-align:center">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
    <input type="hidden" name="id" value="<%=obj.getId()%>">
    <input type="hidden" name="q_id" value="<%=obj.getQ_id()%>">
<span id="spanConfirm">
	<input class="toolbar_default" type="button" followPK="false" name="confirm" value="更新參數設定" onClick="doUpdate();">&nbsp;
</span>	
</td></tr>

</table>
</form>
</body>
</html>

<!--
程式目的：群組資料維護作業
程式代號：conin0201f.jsp
程式日期：102.10.03
程式作者：Kang Da
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="CONIN0301" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.conin.CONIN0301F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>

<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}
else if ("queryOne".equals(obj.getState())) {
	obj = (com.kangdainfo.tcbw.view.conin.CONIN0301F)obj.queryOne();	
}

if ("true".equals(obj.getQueryAllFlag())){
	objList = (java.util.ArrayList) obj.queryAll();
}
%>

<html>
<head>
<%@ include file="../../home/meta.jsp" %>
<script type="text/javascript">
var insertDefault;	
function checkField(){
	var alertStr = "";
	if(form1.state.value == "queryAll"){
		
	}
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
}

function queryOne(id){
	form1.state.value = "queryOne";
	form1.id.value = id;
	beforeSubmit();
	form1.submit();
}

function checkURL(surl){
	columnTrim(form1.id);
	if(form1.id.value == ""){
		alert("請先執行查詢!");
	}else{
		form1.action = surl;
		beforeSubmit();
		form1.submit();
	}
}

function init(){
	setDisplayItem("spanInsert,spanUpdate,spanDelete,spanListPrint,spanListHidden","H");
}
</script>
</head>
<body topmargin="0" onLoad="init();whatButtonFireEvent('<%=obj.getState()%>');showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField();">
<TABLE CELLPADDING=0 CELLSPACING=0 valign="top">
	<tr>
		<td nowrap ID=t1 CLASS="tab_border1" width="100" HEIGHT="25">使用者資料</td>
		<td nowrap ID=t2 CLASS="tab_border2" width="100"><a href="#" onClick="return checkURL('conin0302f.jsp');">授權維護作業</a></td>
	</tr>
	<tr>
		<td nowrap class="tab_line1"></td>
		<td nowrap class="tab_line2"></td>	
	</tr>
</TABLE>

<!--Query區============================================================-->
<div id="queryContainer" style="width:400px;height:200px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<table class="queryTable"  border="1">
	<tr>
		<td nowrap class="queryTDLable">使用者帳號：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_userId" size="25" maxlength="50" value="<%=obj.getQ_userId()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">使用者名稱：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_userName" size="25" maxlength="50" value="<%=obj.getQ_userName()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDInput" colspan="2" style="text-align:center;">
			<input type="hidden" name="q_isQuery">
			<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="確　　定" onClick="form1.q_isQuery.value='Y'">
			<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="whatButtonFireEvent(this.name)">
		</td>
	</tr>
	</table>
</div>

<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:120px">
	<table class="table_form" width="100%" height="100%">
	<tr>
		<td nowrap class="td_form">使用者帳號：</td>
		<td nowrap class="td_form_white">
			<input class="field_P" type="text" name="userId" size="35" maxlength="50" value="<%=obj.getUserId()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">使用者名稱：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="userName" size="35" maxlength="250" value="<%=obj.getUserName()%>">
		</td>	
	</tr>
	<tr>
		<td nowrap class="td_form">異動人員/日期：</td>
		<td nowrap class="td_form_white">
			<input class="field_RO" type="text" name="editID" size="10" value="<%=obj.getEditID()%>">
			/
			<input class="field_RO" type="text" name="editDate" size="7" value="<%=obj.getEditDate()%>">
		</td>
	</tr>
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:center">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<jsp:include page="../../home/toolbar.jsp" />
</td></tr>

<tr><td nowrap>
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>

<!--List區============================================================-->
<tr><td nowrap class="bgList">
<div id="listContainer" style="height:260px">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">使用者帳號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">使用者名稱</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean[] primaryArray = {true, false, false};
	boolean[] displayArray = {false, true, true};
	String[] alignArray = {"center", "left", "left"};
	out.write(View.getQuerylist(primaryArray, displayArray, alignArray, objList, obj.getQueryAllFlag()));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
</body>
</html>
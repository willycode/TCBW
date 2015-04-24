<!--
程式目的：AuditLogHistory查詢
程式代號：sysap007r
程式日期：0990521
程式作者：Nick
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="SYSAP007" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.common.view.sys.ap.SYSAP007R">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>

<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	obj = (com.kangdainfo.common.view.sys.ap.SYSAP007R)obj.queryOne();	
}
if ("true".equals(obj.getQueryAllFlag())){
	objList = (java.util.ArrayList) obj.queryAll();
}
%>

<html>
<head>
<%@ include file="../../home/meta.jsp" %>
<script type="text/javascript">
var insertDefault;	//二維陣列, 新增時, 設定預設值
function checkField(){
	var alertStr="";
	if(form1.state.value=="queryAll"){
		//alertStr += checkQuery();
	}else if(form1.state.value=="insert"||form1.state.value=="insertError"||form1.state.value=="update"||form1.state.value=="updateError"){
	}
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	return true;
}
function queryOne(id){
	form1.id.value=id;
	form1.state.value="queryOne";
	beforeSubmit();
	form1.submit();
}

function init() {
	setDisplayItem("spanInsert,spanUpdate,spanDelete,spanClear,spanConfirm","H");
}
</script>
</head>

<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">

<!--Query區============================================================-->
<div id="queryContainer" style="width:400px;height:300px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<table class="queryTable"  border="1">
	<tr>
		<td nowrap class="queryTDLable">起迄日期：</td>
		<td nowrap class="queryTDInput">
			<%=View.getPopCalendar("field_Q","q_logDate",obj.getQ_logDate())%>~<%=View.getPopCalendar("field_Q","q_logDateE",obj.getQ_logDateE())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">起迄時間：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_logTime" size="6" maxlength="6" value="<%=obj.getQ_logTime()%>">~<input class="field_Q" type="text" name="q_logTimeE" size="6" maxlength="6" value="<%=obj.getQ_logTimeE()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">使用者帳號：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_userId" size="12" maxlength="20" value="<%=obj.getQ_userId()%>">　名稱：<input class="field_Q" type="text" name="q_userName" size="12" maxlength="20" value="<%=obj.getQ_userName()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">功能代號：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_progCode" size="12" maxlength="10" value="<%=obj.getQ_progCode()%>">　名稱：<input class="field_Q" type="text" name="q_progName" size="12" maxlength="30" value="<%=obj.getQ_progName()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">執行動作：</td>
		<td nowrap class="queryTDInput">
			<select name="q_methodName" class="field_Q">
				<%=View.getTextOption("save;save;update;update;delete;delete;saveOrUpdate;saveOrUpdate;bulkUpdate;bulkUpdate;deleteAll;deleteAll",obj.getQ_methodName())%>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">IP：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_userIP" size="20" maxlength="20" value="<%=obj.getQ_userIP()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">指令：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_entity" size="30" maxlength="50" value="<%=obj.getQ_entity()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDInput" colspan="2" style="text-align:center;">

			<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="確　　定" >
			<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="whatButtonFireEvent(this.name)">
		</td>
	</tr>
	</table>
</div>

<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" height="100%">
	<tr>
		<td nowrap class="td_form">執行日期：</td>
		<td nowrap class="td_form_white">
		  <%=View.getPopCalendar("field_RO","logDate",obj.getLogDate())%>		  <input class="field_RO" type="text" name="logTime" size="6" maxlength="6" value="<%=obj.getLogTime()%>">
		  　執行動作：		  
		  <input class="field_RO" type="text" name="methodName" size="30" maxlength="50" value="<%=obj.getMethodName()%>"></td>
		</tr>
	<tr>
		<td nowrap class="td_form">使用者帳號：</td>
		<td nowrap class="td_form_white">
		  <input class="field_RO" type="text" name="userId" size="12" maxlength="20" value="<%=obj.getUserId()%>">　姓名：<input class="field_RO" type="text" name="userName" size="20" maxlength="20" value="<%=obj.getUserName()%>">　IP:<input class="field_RO" type="text" name="userIP" size="30" maxlength="30" value="<%=obj.getUserIP()%>"></td>
		</tr>
	<tr>
		<td nowrap class="td_form">功能代號：</td>
		<td nowrap class="td_form_white">
			<input class="field_RO" type="text" name="progCode" size="12" maxlength="10" value="<%=obj.getProgCode()%>">　名稱：<input class="field_RO" type="text" name="progName" size="50" maxlength="50" value="<%=obj.getProgName()%>">
		</td>
	</tr>		
	<tr>
	  <td nowrap class="td_form">指令：</td>
	  <td nowrap class="td_form_white"><textarea name="entity" cols="70" rows="4" class="field_RO"><%=obj.getEntity()%></textarea></td>
	  </tr>
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:center">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getId()%>">
	<jsp:include page="../../home/toolbar.jsp" />
</td></tr>

<tr><td nowrap class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>
<!--List區============================================================-->
<tr><td nowrap class="bgList">
<div id="listContainer" style="height:280px">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">開始日期</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">開始時間</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">功能代號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">功能名稱</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">使用者帳號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',6,false);" href="#">執行動作</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',7,false);" href="#">IP</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',8,false);" href="#">指令</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true,false,false,false,false,false,false,false,false};
	boolean displayArray[] = {false,true,true,true,true,true,true,true,true};
	out.write(View.getQuerylist(primaryArray,displayArray,null,objList,obj.getQueryAllFlag(), true, null, null,null,false));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
</body>
</html>

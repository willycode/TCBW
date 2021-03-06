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
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="VMED0701Q" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.vmed.VMED0101Q">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) 
{
	if ("false".equals(obj.getQueryAllFlag()))
	{
		obj.setQueryAllFlag("true"); 
	}
}
else if ("queryOne".equals(obj.getState()))
{
	obj = (com.kangdainfo.tcbw.view.vmed.VMED0101Q)obj.queryOne();
}
obj.setQueryAllFlag("true"); 

if ("true".equals(obj.getQueryAllFlag()))
{	
	objList = (java.util.ArrayList) obj.doQueryAll();
	obj.setStateQueryOneSuccess();
}
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
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">

function checkField()
{
	
}


function init() 
{
	setDisplayItem("spanQueryAll,spanInsert,spanClear,spanConfirm,spanUpdate,spanDelete,spanListPrint,spanListHidden","H");
}


function queryOne(id)
{

}

function toCreate()
{
	form1.state.value = "queryOne";
	//form1.state.value = "insert";
	form1.action = "vmed0701f.jsp";
	form1.submit();
}

</script>
</head>
<body  onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">

<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:left">
    <input type="hidden" name="id" value="">	
    <input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="userName" value="<%=User.getUserName()%>">

	<jsp:include page="../../home/toolbar.jsp" />
	<span id="spanDoQueryAll">
    	<input class="toolbar_default" type="submit" followPK="false" name="doQueryAll" value="查   詢">&nbsp;
    </span>
    <span id="spanQueryClear">
		<input class="toolbar_default" type="button" followPK="false" name="queryClear" value="取　消" onClick="setAllClearQ();">&nbsp;
	</span>
	
</td>
</tr>
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%">
    <tr>
		<td nowrap class="td_form" width="15%">案件編號：</td>
		<td nowrap class="td_form_white" width="35%">
			<input class="field_Q" type="text" name="q_applNoS" size="11" maxlength="11" value="">
			- 
			<input class="field_Q" type="text" name="q_applNoE" size="11" maxlength="11" value="">
		</td>
		<td nowrap class="td_form" width="15%">發佈單位</td>
			<td nowrap class="td_form_white" width="35%" >
			<input class="field_Q" type="text" name="q_applNoE" size="11" maxlength="11" value="">
		</td>
	</tr> 
	<tr>
		<td nowrap class="td_form">醫材主類別</td>
		<td nowrap class="td_form_white" >
			<%=View.getPopCode("field_Q","medMainCategoryCode","",Common.get(View.getOneCodeName("MEDMCT",Common.get(""))),"","MEDMCT","medMainCategory","")%>
		</td>
		<td nowrap class="td_form" >警訊類別</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="radio" id="attachment" name="attachment" value="1">警訊
			<input class="field_Q" type="radio" id="attachment" name="attachment" value="2">回收
		</td>
	</tr> 
	<tr>
		<td nowrap class="td_form">產品名稱</td>
		<td nowrap class="td_form_white">
	        <input class="field_Q" type="text" name="q_applNoS" size="11" maxlength="11" value="">
		</td>
		<td nowrap class="td_form" >發佈日期</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","notifierRevDate","")%>~<%=View.getPopCalendar("field_Q","notifierRevDate","")%>
		</td>
	</tr> 
	<tr>
		<td nowrap class="td_form">公告日期</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","notifierRevDate","")%>~<%=View.getPopCalendar("field_Q","notifierRevDate","")%>
		</td>
		<td nowrap class="td_form">是否發佈新聞稿</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="radio" id="attachment" name="attachment" value="Y">是
			<input class="field_Q" type="radio" id="attachment" name="attachment" value="N">否
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" >是否發佈消費者知識服務網</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="radio" id="attachment" name="attachment" value="Y">是
			<input class="field_Q" type="radio" id="attachment" name="attachment" value="N">否
		</td>
		<td nowrap class="td_form" >是否發佈署網</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="radio" id="attachment" name="attachment" value="Y">是
			<input class="field_Q" type="radio" id="attachment" name="attachment" value="N">否
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" >燈號</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="radio" id="attachment" name="attachment" value="R">紅燈
			<input class="field_Q" type="radio" id="attachment" name="attachment" value="Y">黃燈
			<input class="field_Q" type="radio" id="attachment" name="attachment" value="G">綠燈
		</td>
	</tr>
	</table>
	</div>	
</td>
</tr>


<tr>
<td class="bgList">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',0,false);" href="#">NO</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">案件編號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">發佈單位</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">警訊類別</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">醫材主類別</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">產品名稱</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">發佈日期</a></th>
		<th class="listTH"><a class="text_link_w" >明細</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<tr id="listContainerRow141" class='listTREven' onmouseover="this.className='listTRMouseover'" onmouseout="this.className='listTREven'" onClick="listContainerRowClick('141');queryOne('141');" >
 	<td  class='listTDEven' >1.</td>
 	<td  class='listTDEven' ></td>
 	<td  class='listTDEven' ></td>
 	<td  class='listTDEven' ></td>
 	<td  class='listTDEven' ></td>
 	<td  class='listTDEven' ></td>
 	<td  class='listTDEven' ></td>
	<td><a href='#' onClick="toCreate();">明細</a></td></tr>

	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
</body>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName){
	case "queryAll":
		break;
	}
}
</script>
</html>

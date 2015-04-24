<!--
程式目的：
程式代號：
撰寫日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj"  scope="request" class="com.kangdainfo.tcbw.view.conin.CONIN0801Q">
    <jsp:setProperty name='obj' property='*'/>
</jsp:useBean>
<jsp:useBean id="objList"  scope="page" class="java.util.ArrayList"/>      
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="CONIN0801" />
</jsp:include>
<%

//String progID= StringEscapeUtils.escapeHtml(request.getParameter("progID"));

if ("queryAll".equals(obj.getState())) 
{
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}
else if ("queryOne".equals(obj.getState())) 
{
	obj = (com.kangdainfo.tcbw.view.conin.CONIN0801Q)obj.queryOne();	
}

if ("true".equals(obj.getQueryAllFlag()))
{
	objList = (java.util.ArrayList) obj.queryAll();
}

%>

<html>
<head>
<meta http-equiv="Expires" content="-1"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript">
var insertDefault;	//二維陣列, 新增時, 設定預設值
insertDefault = new Array();

function checkField(type)
{

	beforeSubmit();
	form1.submit();		
}

function getUserName(obj)
{
	if(obj!=null && obj.value.length>0)
	{
	  var hql = "select userName from CommonUser where userId='"+obj.value+"'";
	  var x = getRemoteData('../../ajax/jsonObjects.jsp',hql);
	  if(x!=null&&x.length>0)
	  {
		var json = JSON.parse(x);
		form1.q_openUserName.value=json.obj0;
	  }
	}
}

function queryOne(id)
{
	form1.id.value=id;
	form1.state.value="queryOne";
	beforeSubmit();
	form1.submit();
}

function init() 
{
	setDisplayItem("insert,update,clear,delete,confirm,spanListPrint,spanListHidden","H");
}


</script>

</head>
<body topmargin="5" onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" >

<div id="queryContainer" style="width:600px;height:200px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<table class="queryTable"  border="1">
	<tr>
		<td class="td_form" width="30%">開啟日期：</td>
		<td class="td_form_white" width="70%">
		  <%=View.getPopCalendar("field_Q","q_openDateS",obj.getQ_openDateS()) %>
		  <%=View.getPopCalendar("field_Q","q_openDateE",obj.getQ_openDateE()) %>
		</td>
	</tr>
	<tr>
		<td class="td_form">查看人員帳號：</td>
		<td class="td_form_white"> 
		   <input class="field_Q" type="text" name="q_userId" size="20" maxlength="20" value="<%=obj.getQ_userId()%>" >
		</td>
	</tr>	
	<tr>
		<td class="td_form">被開啟通報者帳號：</td>
		<td class="td_form_white"> 
		   <input class="field_Q" type="text" name="q_openUserId" size="20" maxlength="20" value="<%=obj.getQ_openUserId()%>" >
		</td>
	</tr>	
	<tr>
		<td class="td_form">被開啟通報者帳號姓名：</td>
		<td class="td_form_white"> 
		   <input class="field_Q" type="text" name="q_openUserName" size="20" maxlength="20" value="<%=obj.getQ_openUserName()%>">		
		</td>
	</tr>
	<tr>
		<td class="queryTDInput" colspan="2" style="text-align:center;">
			<input class="toolbar_default" type="submit" name="querySubmit" value="確　　定" >
			<input class="toolbar_default" type="button" name="queryCannel" value="取　　消" onClick="whatButtonFireEvent(this.name)">
		</td>
	</tr>
	</table>
</div>

<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr>
<td class="bg" >
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" height="100%">	
	<tr>
		<td class="td_form" width="15%">開啟日期：</td>
		<td class="td_form_white" width="35%">
		  <input class="field_RO" type="text" name="openDate" size="20"  value="<%=obj.getOpenDate()%>" >
		</td>
		<td class="td_form" width="15%">開啟時間：</td>
		<td class="td_form_white" width="35%"> 
		   <input class="field_RO" type="text" name="openTime" size="20"  value="<%=obj.getOpenTime()%>" >
		</td>
	</tr>	
	<tr>
		<td class="td_form">查看人員帳號：</td>
		<td class="td_form_white"> 
		   <input class="field_RO" type="text" name="userId" size="20"  value="<%=obj.getUserId()%>" >
		</td>
		<td class="td_form">查看人員ip：</td>
		<td class="td_form_white"> 
		   <input class="field_RO" type="text" name="ip" size="20"  value="<%=obj.getIp()%>" >
		</td>
	</tr>
	<tr>
		<td class="td_form">被開啟通報者帳號：</td>
		<td class="td_form_white">  
		   <input class="field_RO" type="text" name="openUserId" size="20"  value="<%=obj.getOpenUserId()%>">		
		</td>
		<td class="td_form">被開啟通報者帳號姓名：</td>
		<td class="td_form_white">  
		   <input class="field_RO" type="text" name="openUserName" size="20"  value="<%=obj.getOpenUserName()%>">		
		</td>
	</tr>
	<tr>
		<td class="td_form">作業方式：</td>
		<td class="td_form_white" colspan="3"> 
		   <input class="field_RO" type="text" name="methodName" size="20"  value="<%=obj.getMethodName()%>" >
		</td>
	</tr>
	</table>
	</div>
</td>
</tr>

<!--Toolbar區============================================================-->
<tr>
<td class="bgToolbar" style="text-align:center">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="linkID" value="<%=obj.getId()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getId()%>">
	<input type="hidden" name="id"  value="<%=obj.getId()%>" />
	<jsp:include page="../../home/toolbar.jsp" />
</td>
</tr>

<tr><td nowrap class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>

<!--List區============================================================-->

<tr><td class="bgList" >
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" ><a class="text_link_w" >序號</a></th>
		<th class="listTH" ><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">名稱</a></th>
		<th class="listTH" ><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">開啟日期</a></th>
		<th class="listTH" ><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">查詢人員</a></th>
		<th class="listTH" ><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">被開啟通報者帳號</a></th>
		<th class="listTH" ><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">被開啟通報者姓名</a></th>	
	</tr>
	</thead>
	<tbody id="listTBODY">
		<%
		boolean primaryArray[] = {true, false, false, false, false, false};
		boolean displayArray[] = {false, true, true, true, true, true};
		String[] arrAlign ={"center","left","center","center","center","center"};
		out.write(View.getQuerylist(primaryArray,displayArray,arrAlign,objList,obj.getQueryAllFlag()));
		%>
	</tbody>
</table>
</div>
</td>
</tr>

</table>	
</form>
</body>
</html>
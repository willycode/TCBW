<!--
程式目的：流程群組人員設定作業
程式代號：conin0701f.jsp
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="CONIN0701" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.conin.CONIN0701F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
String x = request.getParameter("x");

if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	obj = (com.kangdainfo.tcbw.view.conin.CONIN0701F)obj.queryOne();	
}else if ("insert".equals(obj.getState()) || "insertError".equals(obj.getState())) {
	obj.insert();
	if ("insertSuccess".equals(obj.getState())) {
		obj.setQueryAllFlag("true");
		obj.setQ_id(obj.getId());
	}
}else if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())) {
	obj.update();
	if ("updateSuccess".equals(obj.getState())) {
		obj.setQueryAllFlag("true");
	}
}else if ("delete".equals(obj.getState()) || "deleteError".equals(obj.getState())) {
	obj.delete();
	if("deleteError".equals(obj.getState())){
		obj = (com.kangdainfo.tcbw.view.conin.CONIN0701F)obj.queryOne();
	}
}
if ("true".equals(obj.getQueryAllFlag())){
	objList = (java.util.ArrayList) obj.queryAll();
}
%>
<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">
var insertDefault;	//二維陣列, 新增時, 設定預設值
function checkField(){
	var alertStr = "";
	if(form1.state.value == "queryAll"){
		alertStr += checkQuery();
	}else if(form1.state.value=="insert"||form1.state.value=="insertError"||form1.state.value=="update"||form1.state.value=="updateError"){
		alertStr += checkEmpty(form1.sysKind, "系統別");
		alertStr += checkEmpty(form1.con1007_id, "通報表單名稱");
		alertStr += checkEmpty(form1.code, "流程角色代碼");
		alertStr += checkEmpty(form1.name, "流程角色名稱");
		alertStr += checkEmpty(form1.isStop, "是否停用");
	}
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	return true;
}

function queryOne(id){
	form1.id.value = id;
	form1.state.value = "queryOne";
	beforeSubmit();
	form1.submit();
}

function init() {
	setDisplayItem("spanInsert,spanUpdate,spanDelete","H");

	<%if("y".equals(x)){%>
	setValue1(form1.q_sysKind,form1.q_con1007_id);
	setValue2(form1.q_con1007_id,form1.q_name);
	form1.q_con1007_id.disabled = false;
	form1.q_name.disabled = false;
	
	<%}%>
}

function checkURL(surl)
{


	var alertStr = "";
		if(form1.sysKind.value==null || form1.sysKind.value== "")
		{
			alertStr += ("系統別不得為空");
		}
		
		if(alertStr.length!=0)
		{
			alert("請先執行查詢, 若已查到資料則請選取其中一筆資料");
			return false;
		} 
		else 
		{
			form1.state.value="queryAll";
		}
		
		form1.action = surl;
		beforeSubmit();
		form1.submit();
	
}

function setValue1(sysKind,q_con1007_id)
{
	var obj1 = q_con1007_id;

	var oldDeptValue = q_con1007_id.value;
	obj1.options.length=0;
	obj1.options.add(new Option("請選擇",""));	

	var x = getRemoteData(getVirtualPath() + "/ajax/jsonConin0501f.jsp?sysKind="+sysKind.value,"");
	
	if (x!=null && x.length>0) 
	{
		var json = eval('(' + x + ')'); 
		var i = 0;
		for (i=0; i<json.length; i++) 
		{
			if(json[i].id==null)
				continue;
			var astId =  json[i].id;			
			var oOption = new Option(json[i].formdName,astId);
			obj1.options.add(oOption);
	    	if(astId == oldDeptValue) oOption.selected=true;			
		}

		obj1.disabled = false;
	}
}

function setValue2(q_con1007_id,q_name)
{
	var obj1 = q_name;
	var oldDeptValue = q_name.value;
	obj1.options.length=0;
	obj1.options.add(new Option("請選擇",""));	

	var x = getRemoteData(getVirtualPath() + "/ajax/jsonConin0702f.jsp?q_con1007_id="+q_con1007_id.value,"");
	
	if (x!=null && x.length>0) 
	{
		var json = eval('(' + x + ')'); 
		var i = 0;
		for (i=0; i<json.length; i++) 
		{
			if(json[i].id==null)
				continue;
			var astId =  json[i].id;			
			var oOption = new Option(json[i].name,astId);
			obj1.options.add(oOption);
	    	if(astId == oldDeptValue) oOption.selected=true;			
		}
		obj1.disabled = false;
	}
}
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<!--Query區============================================================-->
<div id="queryContainer" style="width:700px;height:250px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<% request.setAttribute("qBean",obj); %>
	<jsp:include page="conin0702q.jsp" />
</div>
<table cellpadding="0" cellspacing="0" valign="top">
	<tr>
		<td nowrap id="t1" class="tab_border1"  width="100" height="25">流程群組</td>
		<td nowrap id="t1" class="tab_border2" width="100" ><a href="#" onClick="return checkURL('conin0702f.jsp');">人員群組</a></td>		
	</tr>
	<tr>
		<td nowrap class="tab_line1"></td>
		<td nowrap class="tab_line2"></td>	
	</tr>
</table>
<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
 
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="99%" height="100%">
	<tr>
		<td nowrap class="td_form" width="35%"><font color="red">*</font>系統別：</td>
		<td nowrap class="td_form_white" width="65%">
			<select name="sysKind" class="field" type="select">
	    		<%=View.getOptionCodeKind("SYS", obj.getSysKind())%>
	    	</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="35%"><font color="red">*</font>通報表單名稱：</td>
		<td nowrap class="td_form_white" width="65%">
		
			<select name="con1007_id" class="field" type="select">
	    		<%=View.getOption("select id,formdName from Con1007Db",obj.getCon1007_id(),false,1)%>
	    	</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="35%"><font color="red">*</font>流程角色代碼：</td>
		<td nowrap class="td_form_white" width="65%">
			<input class="field" type="text" name="code" size="4" maxlength="4" value="<%=obj.getCode()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="35%"><font color="red">*</font>流程角色名稱：</td>
		<td nowrap class="td_form_white" width="65%">
			<input class="field" type="text" name="name" size="50" maxlength="50" value="<%=obj.getName()%>">
		</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form"><font color="red">*</font>是否停用：</td>
	  	<td nowrap class="td_form_white">
	  		<select name="isStop" class="field" type="select">
	    		<%=View.getTextOption("Y;是;N;否;", obj.getIsStop(), 1)%>
	    	</select>	
	  	</td>
	</tr>	
	<tr>
		<td nowrap class="td_form">異動人員/日期：</td>
		<td nowrap class="td_form_white" colspan="3">
		  	[<input class="field_RO" type="text" name="editID" size="10" value="<%=obj.getEditID()%>">/
		  	<input class="field_RO" type="text" name="editDate" size="7" value="<%=obj.getEditDate()%>">]
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
	<input type="hidden" name="userID" value="<%=User.getId()%>">
	<jsp:include page="../../home/toolbar.jsp" />
</td></tr>

<tr><td nowrap class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>

<!--List區============================================================-->
<tr><td nowrap class="bgList">
<div id="listContainer">
<table class="table_form" width="99%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">系統別</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">通報表單名稱</a></th>
		<!--  <th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">流程角色代碼</a></th> -->
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">流程角色名稱</a></th>
		<!--  <th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">是否停用</a></th> -->
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true, false, false, false};
	boolean displayArray[] = {false, true, true, true};
	String[] alignArray = {"center", "center", "center", "center"};
	out.write(View.getQuerylist(primaryArray,displayArray,alignArray,objList,obj.getQueryAllFlag()));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
</body>
</html>
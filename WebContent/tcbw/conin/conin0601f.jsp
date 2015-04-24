<!--
程式目的：案件鎖定解除作業
程式代號：conin0601f.jsp
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="CONIN0601" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.conin.CONIN0601F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<jsp:useBean id="onlineList" scope="page" class="java.util.ArrayList"/>
<%
//onlineList = obj.doQueryOnlineUser();
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	obj = (com.kangdainfo.tcbw.view.conin.CONIN0601F)obj.queryOne();	
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
		obj = (com.kangdainfo.tcbw.view.conin.CONIN0601F)obj.queryOne();
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
		alertStr += checkDate(form1.lockDate, "日期");
	}
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	return true;LL
}

function queryOne(id){
	form1.id.value = id;
	form1.state.value = "queryOne";
	beforeSubmit();
	form1.submit();
}

function remove() {
	if (form1.state.value == 'queryOneSuccess' || $("input[name='userID']").val() != '') {
		var onlinelist = '<%=onlineList%>';
		var online = onlinelist.substring(1,onlinelist.length - 1).split(',');
		
		for (var i = 0;i < online.length;i++) {
			if ($("input[name='userID']").val() == online[i]) {
				if(confirm("此使用者在線上，確定解除")) {
					form1.state.value = "delete";
					beforeSubmit();
					form1.submit();
				}
				return;
			}
		}
		if(confirm("確定解除")) {
			form1.state.value = "delete";
			beforeSubmit();
			form1.submit();
		}
	} else {
		alert('請先查詢');
	}
}

function init() {
	setDisplayItem("spanInsert,spanUpdate,spanDelete","H");
}

function setValue(sysKind,q_formID)
{
	var obj1 = q_formID;
	var oldDeptValue = q_formID.value;
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
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<!--Query區============================================================-->
<div id="queryContainer" style="width:500px;height:150px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<table class="queryTable" border="1">
	<tr>
		<td nowrap class="queryTDLable">系統別：</td>
		<td nowrap class="queryTDInput">
			<select class="field_Q" name="q_systemType" type="select" onchange="setValue(this,q_formID);">
				<%=View.getOptionCodeKind("SYS", obj.getQ_systemType())%>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">表單別：</td>
		<td nowrap class="queryTDInput">
			<select class="field_Q" name=q_formID disabled type="select">
				<%=View.getOption("select id,formdName from Con1007Db",obj.getQ_formID(),false,1)%>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">案號：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_applNo" size="20" maxlength="20" value="<%=obj.getQ_applNo()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">使用者帳號：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_userID" size="50" maxlength="50" value="<%=obj.getQ_userID()%>">
		</td>
	</tr>
		<tr>
		<td nowrap class="queryTDLable">使用者姓名：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_userName" size="20" maxlength="20" value="<%=obj.getQ_userName()%>">
		</td>
	</tr>
		<tr>
		<td nowrap class="queryTDLable">通報者：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_notifier" size="50" maxlength="50" value="<%=obj.getQ_notifier()%>">
		</td>
	</tr>
	
	<tr>
		<td nowrap class="queryTDInput" colspan="2" style="text-align:center;">
			<input type="hidden" name="q_id" value="<%=obj.getQ_id()%>">
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
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="99%" height="100%">
	<tr>
		<td nowrap class="td_form" width="17%">系統別：</td>
		<td nowrap class="td_form_white" width="32%">
			<select class="field" name="systemType" type="select">
				<%=View.getOptionCodeKind("SYS", obj.getSystemType())%>
			</select>
		</td>
		<td nowrap class="td_form" width="18%">表單別：</td>
		<td nowrap class="td_form_white" width="33%">
		<select class="field" name=formCode type="select">
				<%=View.getOption("select id,formdName from Con1007Db",obj.getFormCode(),false,1)%>
		</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="17%">案號：</td>
		<td nowrap class="td_form_white" width="32%">
			<input class="field" type="text" name="applNo" size="20" maxlength="20" value="<%=obj.getApplNo()%>">
		</td>
		<td nowrap class="td_form" width="18%">使用者帳號：</td>
		<td nowrap class="td_form_white" width="33%">
			<input class="field" type="text" name="userID" size="50" maxlength="50" value="<%=obj.getUserID()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="17%">使用者姓名：</td>
		<td nowrap class="td_form_white" width="32%">
			<input class="field" type="text" name="userName" size="20" maxlength="20" value="<%=obj.getUserName()%>">
		</td>
		<td nowrap class="td_form" width="18%">IP：</td>
		<td nowrap class="td_form_white" width="33%">
			<input class="field" type="text" name="ip" size="20" maxlength="20" value="<%=obj.getIp()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="17%">日期：</td>
		<td nowrap class="td_form_white" width="32%">
			<input class="field" type="text" name="lockDate" size="7" maxlength="7" value="<%=obj.getLockDate()%>">
		</td>
		<td nowrap class="td_form" width="18%">時間：</td>
		<td nowrap class="td_form_white" width="33%">
			<input class="field" type="text" name="lockTime" size="6" maxlength="6" value="<%=obj.getLockTime()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" colspan ="1">通報者：</td>
		<td nowrap class="td_form_white" colspan ="3">
			<input class="field" type="text" name="notifier" size="50" maxlength="50" value="<%=obj.getNotifier()%>">
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
	<input class="toolbar_default" type="button" name="dis" value="解　除" onclick="remove();">
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
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">表單別</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">案號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">使用者姓名</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">IP</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',6,false);" href="#">日期</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',7,false);" href="#">時間</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',8,false);" href="#">通報者</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true, false, false, false, false, false, false, false, false};
	boolean displayArray[] = {false, true, true, true, true , true, true, true, true};
	String[] alignArray = {"center", "center", "center", "center", "center", "center", "center", "center", "center"};
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
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.util.PermitViewByVMedSearch">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}
if ("true".equals(obj.getQueryAllFlag())){
	objList = (java.util.ArrayList) obj.queryAll();
}
//obj.setQueryAllFlag("true");
//objList = (java.util.ArrayList) obj.queryAll();
%>
<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/tablesoft.js"></script>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript" src="../js/cbToggle.js"></script>
<script type="text/javascript">

function queryOne(permitStr, chProduct, enProduct, applicationId, applicationName) {
	
}

function sure()
{
	var fds = document.getElementsByName("fds");
	for(var i=0;i<fds.length;i++)
	{		
		var item = fds[i];

		if(item.checked == true)
		{
			var medVale = item.value.split(";:;");
			opener.addMedRow("medTable",medVale[0].split(";")[0],medVale[0].split(";")[1],medVale[1],medVale[2],"",medVale[3],medVale[4],medVale[5],medVale[6],medVale[7],medVale[8],"","");
		}
	}
	window.close();
}

function init(){
	setDisplayItem('spanInsert,spanDuplicate,spanUpdate,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden,spanQueryAll','H');
}

function doQueryAll(){
	var alertStr = "";
	alertStr += checkQuery();
	if(alertStr.length!=0){ alert(alertStr);}
	else{
		form1.state.value = "queryAll";
		beforeSubmit();
		form1.submit();
	}
}

</script>
</head>
<body onload="init();">
<form id="form1" name="form1" method="post" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td class="bg" >
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" height="100%">
		<tr>
			<td nowrap class="td_form">許可證字號：</td>
			<td nowrap class="td_form_white">
				<select name="q_permitKey" class="field_Q">
					<%=View.getOption("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='MEDPKID' order by codeId", obj.getQ_permitKey())%>
				</select>			 
				號：<input class="field_Q" type="text" name="q_permitNo" size="6" maxlength="6" value="<%=obj.getQ_permitNo()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">品名：</td>
			<td nowrap class="td_form_white">
				<input class="field_Q" type="text" name="q_product" size="100" maxlength="100" value="<%=obj.getQ_product()%>">
		    </td>
		</tr>
		<tr>
			<td class="td_form">持有商：</td>
			<td class="td_form_white">
				<input class="field_Q" type="text" name="q_appName" size="50" maxlength="100" value="<%=obj.getQ_appName()%>">
			</td>
		</tr>
		<tr>
			<td class="td_form">製造廠：</td>
			<td class="td_form_white">
				<input class="field_Q" type="text" name="q_factName" size="50" maxlength="100" value="<%=obj.getQ_factName()%>">
			</td>
		</tr>
	</table>
	</div>
</td></tr>
<tr><td class="bg" align="left">
        <input class="field" type="hidden" name="q_removeKey" value="<%=obj.getQ_removeKey()%>">
		<input type="hidden" name="state" value="<%=obj.getState()%>">
		<jsp:include page="toolbar.jsp" />
		<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="查　　詢" onClick="doQueryAll();">
		<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="window.close()">
		<input class="toolbar_default" followPK="false" type="button" name="confirm" value="帶　　回" onClick="sure();">&nbsp;

</td></tr>
<tr><td>
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../home/page.jsp" />
</td></tr>

<tr><td class="bg">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0" id="tbl">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><input type="checkbox" name="cbAll" class="field_Q"></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">許可證字號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">中文品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">英文品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">有效日期</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">申請商名稱</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">製造廠名稱</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">製造廠國別</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
		boolean primaryArray[] = {true,false,true,true,true,true,true,true,true,true};
		boolean displayArray[] = {false,true,true,true,true,false,true,true,true,false};
		out.write(obj.getCheckboxQuerylist(primaryArray,displayArray,null,objList,obj.getQueryAllFlag(),"fds",null,null,null,-1,false,false));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
<script type="text/javascript">
var cb = new cbToggle('cb',document.form1,form1.cbAll,'fds');
cb.config.cssTopLevel = true;
</script>
</body>
</html>
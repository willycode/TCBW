<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.util.Con1009DbSearch">
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
%>
<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../js/default.css" type="text/css"/>
<script type="text/javascript" src="../js/json.js"></script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery.maskedinput.js"></script>
<script type="text/javascript" src="../js/tablesoft.js"></script>
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript" src="../js/cbToggle.js"></script>
<script type="text/javascript" src="../js/toolbar.js"></script>
<script type="text/javascript">
function checkField(){
	var alertStr="";
	if(form1.state.value=="queryAll"){
		if(form1.q_engageKind.value=="" && form1.q_medAgencyKind.value=="")
			alertStr +="至少輸入一項查詢條件";
	}
	if(alertStr.length!=0){ 
		alert(alertStr);
		return false; 
	}
	beforeSubmit();
	form1.submit();
}


function queryOne(id) {

}

function init(){
	setDisplayItem('queryAll,spanInsert,spanDuplicate,spanUpdate,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden','H');
}

function sure(){	
	//opener.clearCon19Table();
	var fds = document.getElementsByName("fds");	
	for(var i=0;i<fds.length;i++){		
		var item = fds[i];
		if(item.checked == true){
			//若曾勾選過，不再新增
			var isAdd = new Boolean(true);
			var con19Row = opener.document.getElementsByName("con19Row");
			var medVale = item.value.split(",");
			if (con19Row!=null && con19Row.length>0) {
				for (var j=0; j<con19Row.length; j++) {
					var id = con19Row[j].value;
					var con19Id = opener.getObjectValue("con19Id"+id);
					if(con19Id==medVale[0])  isAdd = false;										
				}
			}
			if(isAdd==true) 
				opener.addCon19Row("con19Table",medVale[1],medVale[2],medVale[3],medVale[0]);
		}
	}
	window.close();
}
function cancel(){
	window.close();
}

function queryAll() {
	form1.state.value = "queryAll";
	checkField();	
}
</script>
</head>
<body onload="init();">
<form id="form1" name="form1" method="post" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr><td class="bgToolbar" style="text-align:center">
	<input type="hidden" class="field_Q" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getId()%>">
	<input type="hidden" name="q_con19Ids" value="<%=obj.getQ_con19Ids()%>">
	<input class="toolbar_default" type="button" followPK="false" name="confirm" value="查 　詢" onClick="queryAll();">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="confirm" value="確 　定" onClick="sure();">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="cannel" value="關 　閉" onClick="cancel();">&nbsp;
</td></tr>
<tr><td class="bg">
<div id="formContainer" style="height:auto">
<table width="100%" cellspacing="0" cellpadding="0" style="width:auto;height:auto;">
	<tr>
		<td class="queryTDLable">特約類別</td>
		<td class="queryTDInput">
			<select class="field" name="q_engageKind">
				<%=View.getOptionCodeKind("MEDENG", obj.getQ_engageKind())%>
			</select>
		</td>	
	</tr>
	<tr>
		<td class="queryTDLable">醫事機構種類</td>
		<td class="queryTDInput">
			<select class="field" name="q_medAgencyKind">
				<%=View.getOptionCodeKind("MEDKIND", obj.getQ_medAgencyKind())%>
			</select>
		</td>	
	</tr>
</table>
</div>
</td></tr>
<tr><td>
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="page.jsp" />
</td></tr>
<tr><td class="bg">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0" id="tbl">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><input type="checkbox" name="cbAll" class="field_Q"></th>
		<th class="listTH"><a class="text_link_w">醫事機構名稱</a></th>		
		<th class="listTH"><a class="text_link_w">特約類別</a></th>	
		<th class="listTH"><a class="text_link_w">醫事機構種類</a></th>
		<th class="listTH"><a class="text_link_w">地址</a></th>
		<th class="listTH"><a class="text_link_w">電話</a></th>	
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true, false, true, true, true,false, false};
	boolean displayArray[] = {false, false, true, true, true, true, true};
	out.write(View.getCheckboxQuerylist(primaryArray,displayArray,null,objList,obj.getQueryAllFlag(),"fds",null,null,null,-1,false,false));
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
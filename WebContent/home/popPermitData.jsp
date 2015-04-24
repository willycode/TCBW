<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.util.PermitDataSearch">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%	
if ("init".equals(obj.getState())) {
	if(!"".equals(Common.get(request.getParameter("q_prodName1")))){
		obj.setQ_prodName1(Common.get(java.net.URLDecoder.decode(request.getParameter("q_prodName1"),"UTF-8")));
	}
	if(!"".equals(Common.get(request.getParameter("q_ingrMa1")))){
		obj.setQ_ingrMa1(Common.get(java.net.URLDecoder.decode(request.getParameter("q_ingrMa1"),"UTF-8")));
	}
	if(!"".equals(Common.get(request.getParameter("q_appName")))){
		obj.setQ_appName(Common.get(java.net.URLDecoder.decode(request.getParameter("q_appName"),"UTF-8")));
	}
	if(!"".equals(Common.get(request.getParameter("q_factName")))){
		obj.setQ_factName(Common.get(java.net.URLDecoder.decode(request.getParameter("q_factName"),"UTF-8")));
	}
}else if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}
if ("true".equals(obj.getQueryAllFlag())){
	objList = (java.util.ArrayList) obj.queryAll();
}
%>
<html>
<head>
<title>許可證輸入輔助視窗</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../js/default.css" type="text/css"/>
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/tablesoft.js"></script>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript" src="../js/cbToggle.js"></script>
<script type="text/javascript">
function queryOne(permitKey,applicationId,applicationName){
	
}

function sure()
{
	var fds = document.getElementsByName("fds");
	for(var i=0;i<fds.length;i++){		
		var item = fds[i];
		if(item.checked == true){
			var medVale = item.value.split(",");
			medVale[0].split(";");
			opener.addDrg73Row("drg73Table",medVale[0].split(";")[0],medVale[0].split(";")[1],medVale[1],medVale[2]);
		}
	}
	opener.document.all.item("toUpdate").value="Y";
	opener.submitBefore('maintain0','','','','');
	window.close();
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

function init(){
	setDisplayItem('spanInsert,spanDuplicate,spanUpdate,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden,spanQueryAll','H');
}
</script>
</head>
<body onLoad="init();">
<form id="form1" name="form1" method="post" action="popPermitData.jsp">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td class="bg" >
	<input class="field" type="hidden" name="q_removeKey" value="<%=obj.getQ_removeKey()%>">
	<input class="field" type="hidden" name="q_caseType" value="<%=obj.getQ_caseType()%>">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" height="100%">	
	<tr>
		<td class="td_form">品名：</td>
		<td class="td_form_white">
			<input class="field_Q" type="text" name="q_prodName1" size="80" value="<%=obj.getQ_prodName1()%>"><br>
			<input class="field_Q" type="text" name="q_prodName2" size="80" value="<%=obj.getQ_prodName2()%>"><br>
			<input class="field_Q" type="text" name="q_prodName3" size="80" value="<%=obj.getQ_prodName3()%>"><br>
			<input class="field_Q" type="text" name="q_prodName4" size="80" value="<%=obj.getQ_prodName4()%>"><br>
			<input class="field_Q" type="text" name="q_prodName5" size="80" value="<%=obj.getQ_prodName5()%>">
		</td>
	</tr>
	<tr>
		<td class="td_form">學名：</td>
		<td class="td_form_white">
			<input class="field_Q" type="text" name="q_ingrMa1" size="80"  value="<%=obj.getQ_ingrMa1()%>"><br>
			<input class="field_Q" type="text" name="q_ingrMa2" size="80"  value="<%=obj.getQ_ingrMa2()%>"><br>
			<input class="field_Q" type="text" name="q_ingrMa3" size="80"  value="<%=obj.getQ_ingrMa3()%>"><br>
			<input class="field_Q" type="text" name="q_ingrMa4" size="80"  value="<%=obj.getQ_ingrMa4()%>"><br>
			<input class="field_Q" type="text" name="q_ingrMa5" size="80"  value="<%=obj.getQ_ingrMa5()%>">
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

<!--Toolbar區============================================================-->
<tr><td class="bg" style="text-align:center">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<jsp:include page="toolbar.jsp" />
	<input class="toolbar_default" followPK="false" type="button" name="querySubmit" value="查　　詢" onClick="doQueryAll();">
	<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="window.close()">
	<input class="toolbar_default" followPK="false" type="button" name="confirm" value="帶　　回" onClick="sure();">&nbsp;
</td></tr>

<tr><td>
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="page.jsp" />
</td></tr>

<!--List區============================================================-->
<tr><td class="bg" >
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" height="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><input type="checkbox" name="cbAll" class="field_Q"></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">許可證字號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">中文品名</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">英文品名</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">有效日期</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">申請商名稱</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',6,false);" href="#">製造廠名稱</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',7,false);" href="#">製造廠國別</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
		<%
			boolean primaryArray[] = {true,false,false,false,false,true,true,false};
			boolean displayArray[] = {false,true,true,true,true,false,true,true};
			String[] arrAlign = {"center","center","left","left","center","center","center","center"};
			out.write(View.getCheckboxQuerylist(primaryArray,displayArray,arrAlign,objList,obj.getQueryAllFlag(),"fds",null,null,null,-1,false,false));

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

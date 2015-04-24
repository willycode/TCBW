<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.hfrin.HFRIN0704F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
obj.setQueryAllFlag("true");
objList = (java.util.ArrayList) obj.queryAll();
obj.setStateQueryOneSuccess();
%>
<html>
<head>
<title>評估委員</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css">
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">
function queryOne(id,name){
	if (isObj(opener.document.all.item("<%=obj.getFieldId()%>"))) {		
		opener.document.all.item("<%=obj.getFieldId()%>").value = id;		
	}
	if (isObj(opener.document.all.item("<%=obj.getFieldName()%>"))) {
		opener.document.all.item("<%=obj.getFieldName()%>").value = name;		
	}
	window.close();
}

function init(){
	
}

$(function(){
	setDisplayItem('spanInsert,spanUpdate,spanQueryAll,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden','H');
});
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post">
<input type="hidden" name="fieldName" value="<%=obj.getFieldName()%>">
<input type="hidden" name="fieldId" value="<%=obj.getFieldId()%>">
<input type="hidden" name="state" value="<%=obj.getState()%>">

<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg" >
	<div id="formContainer" style="height:70px">
	<table class="table_form" width="100%" height="100%">	
	<tr>		
		<td nowrap class="td_form">姓名：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="q_name" size="25" maxlength="20" value="<%=obj.getQ_name()%>">
		</td>		
	</tr>	
	<tr>		
		<td nowrap class="td_form">職等：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCode("field", "q_postLevCode", "", Common.get(View.getOneCodeName("PL",Common.get(obj.getQ_postLev()))), "", "PL", "q_postLev", obj.getQ_postLev())%>
		</td>		
	</tr>	
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bg" style="text-align:center">
	<jsp:include page="../../home/toolbar.jsp">
		<jsp:param value="N" name="btnCopy"/>
    </jsp:include>
	<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="確　　定" >
	<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="window.close()">
</td></tr>

<tr><td nowrap>
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>

<!--List區============================================================-->
<tr><td nowrap class="bg" >
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">任期區間起迄</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">姓名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">職等</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">任職單位</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
		<%
		boolean primaryArray[] = {true,false,true,false,false};
		boolean displayArray[] = {false,true,true,true,true};
		out.write(View.getQuerylist(primaryArray, displayArray, objList, obj.getQueryAllFlag()));
		%>
	</tbody>
</table>
</div>
</td></tr>
</table>	
</form>
</body>
</html>

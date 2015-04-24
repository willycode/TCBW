<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN0303F">
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
<title>廠商資料</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css">
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript">
function queryOne(manufactorID, manufactorName, manufactorArea, manufactorZipCode, manufactorAddr, manufactorTelArea, manufactorTel){
	if (isObj(opener.document.all.item("manufactorID"))) {		
		opener.document.all.item("manufactorID").value = manufactorID;		
	}
	if (isObj(opener.document.all.item("manufactorName"))) {
		opener.document.all.item("manufactorName").value = manufactorName;		
	}
	if (isObj(opener.document.all.item("manufactorArea"))) {
		opener.document.all.item("manufactorArea").value = manufactorArea;		
	}	
	if (isObj(opener.document.all.item("manufactorZipCode"))) {
		opener.document.all.item("manufactorZipCode").value = manufactorZipCode;		
	}
	if (isObj(opener.document.all.item("manufactorAddr"))) {
		opener.document.all.item("manufactorAddr").value = manufactorAddr;		
	}
	if (isObj(opener.document.all.item("manufactorTelArea"))) {
		opener.document.all.item("manufactorTelArea").value = manufactorTelArea;		
	}
	if (isObj(opener.document.all.item("manufactorTel"))) {
		opener.document.all.item("manufactorTel").value = manufactorTel;		
	}
	opener.lockManufactorInfo();
	window.close();
}
</script>
</head>
<body>
<form id="form1" name="form1" method="post">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg" >
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" height="100%">	
	<tr>		
		<td nowrap class="td_form">廠商名稱：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="q_name" size="30" maxlength="50" value="<%=obj.getQ_name()%>">
		</td>		
	</tr>	
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bg" style="text-align:center">
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
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">廠商統編</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">廠商名稱</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
		<%
		boolean primaryArray[] = {true, true, true, true, true, true, true};
		boolean displayArray[] = {true, true, false, false, false, false, false};
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

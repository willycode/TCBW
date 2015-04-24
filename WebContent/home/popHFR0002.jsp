<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.util.COSUT004F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%	
obj.setQueryAllFlag("true");
objList = (java.util.ArrayList) obj.queryAll();
%>
<html>
<head>
<title>評估委員輔助視窗</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript">

function checkField(){	
	return true;
}

function queryOne(id, comId, name, postLev, unionID, termSdate, termEdate){
	if (isObj(opener.document.all.item("hfr02id")))
		opener.document.all.item("hfr02id").value=id;
	if (isObj(opener.document.all.item("committeeNum")))
		opener.document.all.item("committeeNum").value=comId;
	if (isObj(opener.document.all.item("name")))
		opener.document.all.item("name").value=name;
	if (isObj(opener.document.all.item("postLev")))
		opener.document.all.item("postLev").value=postLev;
	if (isObj(opener.document.all.item("unionID")))
		opener.document.all.item("unionID").value=unionID;	
	if (isObj(opener.document.all.item("termSdate")))
		opener.document.all.item("termSdate").value=termSdate;
	if (isObj(opener.document.all.item("termEdate")))
		opener.document.all.item("termEdate").value=termEdate;			
	window.close();
}
</script>
</head>
<body>
<form id="form1" name="form1" method="post" action="popHFR0002.jsp" onSubmit="return checkField()">
<!--回傳的欄位名稱  -->
<input type="hidden" name="<%=obj.getIdField()%>"  value="<%=obj.getTemp_id()%>"> 
<input type="hidden" name="<%=obj.getNameField()%>"  value="<%=obj.getTemp_other()%>"> 

<!--預設欄位名稱  -->
<input type="hidden" name="idField" value="<%=obj.getIdField()%>">
<input type="hidden" name="nameField" value="<%=obj.getNameField()%>">

<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->

<!--Toolbar區============================================================-->

<tr><td nowrap>
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="page.jsp" />
</td></tr>

<!--List區============================================================-->
<tr><td nowrap class="bg" >
<div id="listContainer" style="height:auto">
<table class="table_form" width="99%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">編號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">姓名</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">職等</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">任職單位</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">任期起始日</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',6,false);" href="#">任期結束日</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
		<%
		boolean primaryArray[] = {true, true, true, true, true, true, true};
		boolean displayArray[] = {false, true, true, true, true, true, true};
		out.write(View.getQuerylist(primaryArray,displayArray,objList,obj.getQueryAllFlag()));
		%>
	</tbody>
</table>
</div>
</td></tr>
</table>	
</form>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.util.COSUT002F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%	
obj.setQueryAllFlag("true");
objList = (java.util.ArrayList) obj.queryAll();
%>
<html>
<head>
<title>信件輔助視窗</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript">

function checkField(){	
	return true;
}

function queryOne(id,mailID)
{	
	opener.document.all.item("formID2").value=id;
	opener.document.all.item("popMailID").value=mailID;
	window.opener.queryOne(id);	
	window.close();
}
</script>
</head>
<body>
<form id="form1" name="form1" method="post" action="popCON1001.jsp" onSubmit="return checkField()">
<!--回傳的欄位名稱  -->
<input type="hidden" name="<%=obj.getIdField()%>"  value="<%=obj.getTemp_id()%>"> 
<input type="hidden" name="<%=obj.getNameField()%>"  value="<%=obj.getTemp_other()%>"> 

<!--預設欄位名稱  -->
<input type="hidden" name="idField" value="<%=obj.getIdField()%>">
<input type="hidden" name="nameField" value="<%=obj.getNameField()%>">
<input type="hidden" name="manyMailID" value="<%=obj.getManyMailID()%>">

<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->

<tr><td nowrap>
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="page.jsp" />
</td></tr>

<!--List區============================================================-->
<tr><td nowrap class="bg" >
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">主旨</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">內容</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
		<%
		boolean primaryArray[] = {true, false, false,true};
		boolean displayArray[] = {false, true, true, false};
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

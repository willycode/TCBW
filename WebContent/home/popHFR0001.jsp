<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.util.COSUT001F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%	
obj.setQueryAllFlag("true");
objList = (java.util.ArrayList) obj.queryAll();
%>
<html>
<head>
<title>委員輔助視窗</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript">

function checkField(){	
	return true;
}

function queryOne(id,sname){
	if (isObj(opener.document.all.item("<%=obj.getNameField()%>"))) {		
		opener.document.all.item("<%=obj.getNameField()%>").value=sname;		
	}
	if (isObj(opener.document.all.item("<%=obj.getIdField()%>"))) {
		opener.document.all.item("<%=obj.getIdField()%>").value=id;		
	}	
	window.close();
}
</script>
</head>
<body>
<form id="form1" name="form1" method="post" action="popHFR0001.jsp" onSubmit="return checkField()">
<!--回傳的欄位名稱  -->
<input type="hidden" name="<%=obj.getIdField()%>"  value="<%=obj.getTemp_id()%>"> 
<input type="hidden" name="<%=obj.getNameField()%>"  value="<%=obj.getTemp_other()%>"> 

<!--預設欄位名稱  -->
<input type="hidden" name="idField" value="<%=obj.getIdField()%>">
<input type="hidden" name="nameField" value="<%=obj.getNameField()%>">

<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg" >
	<div id="formContainer">
	<table class="table_form" width="100%" height="100%">	
		<tr>
			<td nowrap class="td_form" width="100">姓名</td>
			<td nowrap class="td_form_white" >
				<input class="field" type="text" name="committee" size="20" maxlength="10" value="<%=obj.getCommittee()%>">
			</td>
		</tr>
	    <tr>
			<td nowrap class="td_form" >性別</td>
			<td nowrap class="td_form_white" >
			  	<input class="field" type="radio" name="sex" value="1" <%=obj.getSex().equals("1")?"checked":""%>>男
				<input class="field" type="radio" name="sex" value="0" <%=obj.getSex().equals("0")?"checked":""%>>女
			</td>
		</tr> 
	    <tr>
			<td nowrap class="td_form" >身分證號</td>
			<td nowrap class="td_form_white" >
				<input class="field" type="text" name="idNum" size="10" maxlength="10" value="<%=obj.getIdNum()%>">
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
<jsp:include page="page.jsp" />
</td></tr>

<!--List區============================================================-->
<tr><td nowrap class="bg" >
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">姓名</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">性別</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">身分證號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">e-mail</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
		<%
		boolean primaryArray[] = {true, true, false, false, false};
		boolean displayArray[] = {false, true, true, true, true};
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

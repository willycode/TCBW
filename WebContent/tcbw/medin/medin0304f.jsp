<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN0203F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%	

String id1 = Common.get(request.getParameter("id"));
obj.setId1(id1);

String q_code = Common.get(request.getParameter("q_code"));
obj.setQ_code(q_code);

String q_formCode = Common.get(request.getParameter("q_formCode"));
obj.setQ_formCode(q_formCode);

obj.setQueryAllFlag("true");
objList = (java.util.ArrayList) obj.queryAll();

%>
<html>
<head>
<title>案件改派</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="./../../js/jquery.js"></script>
<script type="text/javascript">

function checkField()
{	
	return true;
}

function queryOne(userid, userName)
{

	if (isObj(opener.document.all.item("worker")))
		opener.document.all.item("worker").value=userid;
	
	if (isObj(opener.document.all.item("workerName")))
		opener.document.all.item("workerName").value=userName;
	
	window.close();

}


</script>
</head>
<body >
<form id="form1" name="form1" method="post"  onSubmit="return checkField()">

<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg" >
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" height="100%">	
		<tr>
			<td nowrap class="td_form" width="15%">聯絡人帳號</td>
			<td nowrap class="td_form_white" >
				<input class="field" type="text" name="userid" size="20" maxlength="10" value="<%=obj.getUserid()%>">
			</td>
		</tr>
	    <tr>
			<td nowrap class="td_form" >聯絡人姓名</td>
			<td nowrap class="td_form_white" >
				<input class="field" type="text" name="username" size="20" maxlength="10" value="<%=obj.getUsername()%>">
			</td>
		</tr> 
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bg" style="text-align:center">
	<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="查　　詢" >
	<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="window.close()">
    <input type="hidden" name="state" value="<%=obj.getState()%>">
    <input type="hidden" name="id1" value="<%=obj.getId1()%>">
</td>
</tr>

<tr><td nowrap>
<% request.setAttribute("QueryBean",obj);%>
  <jsp:include page="../../home/page_row.jsp" />
</td></tr>

<!--List區============================================================-->
<tr><td nowrap class="bg" >
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">角色代碼</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">角色名稱</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">帳號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">姓名</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
		<%
		boolean primaryArray[] = {false, false, false, true, true};
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

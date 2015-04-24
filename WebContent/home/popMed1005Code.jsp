<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.medse.MEDSE0003F_popCode">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
String fieldCode = Common.get(request.getParameter("fieldCode"));
String fieldName = Common.get(request.getParameter("fieldName"));
obj.setQueryAllFlag("true");
objList = (java.util.ArrayList) obj.queryAll();
%>
<html>
<head>
<title>代碼輔助視窗</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript">
function queryOne(code,name){
	if (isObj(opener.document.all.item('<%=fieldCode%>'))) {		
		opener.document.all.item('<%=fieldCode%>').value=code;	
	}
	if (isObj(opener.document.all.item('<%=fieldName%>'))) {
		opener.document.all.item('<%=fieldName%>').value=name;	
	}
	window.close();
}
</script>
</head>
<body>
<form id="form1" name="form1" method="post" action="popMed1005Code.jsp">
<input type="hidden" name="id" value="<%=obj.getId()%>">
<input type="hidden" name="code" value="<%=obj.getCode()%>">
<input type="hidden" name="name" value="<%=obj.getName()%>">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg" >
	<div id="formContainer" style="height:120px">
	<table class="table_form" width="100%" height="100%">	
	<tr>
		<td class="queryTDLable">代碼：</td>
		<td class="queryTDInput">
			<input class="field_Q" type="text" name="q_code" size="5" maxlength="5" value="<%=Common.get(obj.getQ_code())%>">
		</td>
	</tr>
	<tr>
		<td class="queryTDLable">名稱：</td>
		<td class="queryTDInput">
			<input class="field_Q" type="text" name="q_name" size="50" maxlength="50" value="<%=Common.get(obj.getQ_name())%>">
		</td>
	</tr>
	<tr>
		<td class="queryTDLable">FDA代碼：</td>
		<td class="queryTDInput">
			<input class="field_Q" type="text" name="q_fdaCode" size="4" maxlength="4" value="<%=obj.getQ_fdaCode()%>">
		</td>
	</tr>
	<tr>
		<td class="queryTDLable" ></font>FDA名稱：</td>
		<td nowrap class="queryTDInput" >
			<input class="field_Q" type="text" name="fdaName" size="30" maxlength="50" value="<%=obj.getFdaName()%>">				
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
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">代碼</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">名稱</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">term</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">Definition</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">FDA代碼</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',6,false);" href="#">FDA名稱</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
		<%
		boolean primaryArray[] = {false,  true, true, false, false, false, false};
		boolean displayArray[] = {false, true,  true, true, true, true, true};
		String[] alignArray = {"center", "center", "left", "left", "left", "center", "center"};
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

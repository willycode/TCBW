<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.hfrin.HFRIN0504F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%	
String r = "";
String hfr0001Id = Common.get(request.getParameter("id"));
String editId = Common.get(request.getParameter("editId"));
String code = Common.get(request.getParameter("code"));
String formCode = Common.get(request.getParameter("formCode"));
String caseStatus = Common.get(request.getParameter("caseStatus"));

obj.setHfr0001DbId(hfr0001Id);
obj.setEditID(editId);
obj.setCode(code);
obj.setFormCode(formCode);
obj.setCaseStatus(caseStatus);

if("updateAssign".equals(obj.getState())){
	try{
		r = obj.updateAssign();
	}catch(Exception e){
		r = "發生異常，請重新操作 !";
		e.printStackTrace();
	}
}

obj.setQueryAllFlag("true");
objList = (java.util.ArrayList) obj.queryAll();
obj.setStateQueryOneSuccess();

%>
<html>
<head>
<title>案件改派</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">
function checkField(){	
	return true;
}

function queryOne(userId){
	form1.state.value = "updateAssign";
	form1.assignMan.value = userId;
	form1.submit();
}

function init(){
	if("<%=r%>" == "Y"){
		alert("案件改派完成");
		opener.goBackQuery();
		window.close();
	}else{
		if("<%=r%>" != ""){
			alert("<%=r%>");
		}
	}
}
</script>
</head>
<body onLoad="init();">
<form id="form1" name="form1" method="post" onSubmit="return checkField()">

<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg" >
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" height="100%">	
		<tr>
			<td nowrap class="td_form">帳號</td>
			<td nowrap class="td_form_white" >
				<input class="field" type="text" name="q_userid" size="20" maxlength="10" value="<%=obj.getQ_userId()%>">
			</td>
		</tr>
	    <tr>
			<td nowrap class="td_form" >姓名</td>
			<td nowrap class="td_form_white" >
				<input class="field" type="text" name="q_username" size="20" maxlength="10" value="<%=obj.getQ_userName()%>">
			</td>
		</tr> 
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bg" style="text-align:center">
	<input type="hidden" name="hfr0001DbId" value="<%=obj.getHfr0001DbId()%>">
	<input type="hidden" name="editID" value="<%=obj.getEditID()%>">
	<input type="hidden" name="code" value="<%=obj.getCode()%>">
	<input type="hidden" name="formCode" value="<%=obj.getFormCode()%>">
	<input type="hidden" name="caseStatus" value="<%=obj.getCaseStatus()%>">
	
    <input type="hidden" name="state" value="<%=obj.getState()%>">
    <input type="hidden" name="assignMan">
    <input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="查　　詢" >
	<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="opener.deleteEdit('<%=obj.getHfr0001DbId()%>');window.close()">
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
		boolean primaryArray[] = {false, false, false, true, false};
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

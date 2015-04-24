<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.hfrin.HFRIN0404F">
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
<title>使用者</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css">
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript">
function queryOne(userId, userName, userTelArea, userTel, userTelExt, userEmail, userType, userJob, userJobName, userPersonId, jobTitle, cityCode, zipCode, addr){
	if (isObj(opener.document.all.item("caseOwner"))) {		
		opener.document.all.item("caseOwner").value = userId;		
	}
	if (isObj(opener.document.all.item("notifierName"))) {
		opener.document.all.item("notifierName").value = userName;		
	}
	if (isObj(opener.document.all.item("notifierTelArea"))) {
		opener.document.all.item("notifierTelArea").value = userTelArea;		
	}	
	if (isObj(opener.document.all.item("notifierTel"))) {
		opener.document.all.item("notifierTel").value = userTel;		
	}
	if (isObj(opener.document.all.item("notifierTelExt"))) {
		opener.document.all.item("notifierTelExt").value = userTelExt;		
	}
	if (isObj(opener.document.all.item("notifierEamil"))) {
		opener.document.all.item("notifierEamil").value = userEmail;		
	}
	if (isObj(opener.document.all.item("notifierType"))) {
		for(var i=0; i<opener.document.all.item("notifierType").length; i++){
			if(opener.document.all.item("notifierType")[i].value == userType){
				opener.document.all.item("notifierType")[i].checked = true;
			}
		}
	}
	if (isObj(opener.document.all.item("notifierDeptID"))) {
		opener.document.all.item("notifierDeptID").value = userJob;		
	}
	if (isObj(opener.document.all.item("notifierDept"))) {
		opener.document.all.item("notifierDept").value = userJobName;		
	}
	if (isObj(opener.document.all.item("notifierIdNum"))) {
		opener.document.all.item("notifierIdNum").value = userPersonId;		
	}
	if (isObj(opener.document.all.item("notifierTitle"))) {
		opener.document.all.item("notifierTitle").value = jobTitle;		
	}
	if (isObj(opener.document.all.item("notifierArea"))) {
		opener.document.all.item("notifierArea").value = cityCode;		
	}
	if (isObj(opener.document.all.item("notifierZipCode"))) {
		opener.document.all.item("notifierZipCode").value = zipCode;		
	}
	if (isObj(opener.document.all.item("address"))) {
		opener.document.all.item("address").value = addr;		
	}
	
	opener.lockUserInfo();
	window.close();
}
</script>
</head>
<body>
<form id="form1" name="form1" method="post">
<input type="hidden" name="q_type" value="<%=obj.getQ_type()%>">

<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg" >
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" height="100%">	
	<tr>		
		<td nowrap class="td_form">使用者帳號：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="q_userId" size="20" maxlength="40" value="<%=obj.getQ_userId()%>">
		</td>		
	</tr>	
	<tr>		
		<td nowrap class="td_form">使用者名稱：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="q_userName" size="30" maxlength="50" value="<%=obj.getQ_userName()%>">
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
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">使用者名稱</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">使用者電話</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">使用者EMAIL</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
		<%
		boolean primaryArray[] = {true, true, true, true, true, true, true, true, true, true, true, true, true, true};
		boolean displayArray[] = {false, true, false, true, false, true, false, false, false, false, false, false, false, false};
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

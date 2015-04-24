<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.util.UserSearch">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%

java.util.ArrayList<String[]> objList = (java.util.ArrayList) obj.doQueryAll();

%>
<html>
<head>
<title>通報者輔助視窗</title>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../js/json.js"></script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery.maskedinput.js"></script>
<script type="text/javascript" src="../js/tablesoft.js"></script>
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript" src="../js/toolbar.js"></script>
<script type="text/javascript">
function checkField(type){
	form1.state.value = type;
	form1.submit();
}

function queryOne(userName, userJob, userEmail, jobTitle, userTel, userFax, userMobile, userAddr, userDept, userId, userTelArea, userTelExt, userFaxArea, userCounty, userZipCode, userJobID) {
	opener.setObjectValue("notifierUserID",userId);
	opener.setObjectValue("notifierName",userName);
	opener.setObjectValue("notifierDept",userJob);
	opener.setObjectValue("notifierDeptID",userJobID);
	opener.setObjectValue("notifierTelArea",userTelArea);
	opener.setObjectValue("notifierTel",userTel);
	opener.setObjectValue("notifierTelExt",userTelExt);
	opener.setObjectValue("notifierEmail",userEmail);
	opener.setObjectValue("notifierPhone",userMobile);
	opener.setObjectValue("notifierFaxArea",userFaxArea);
	opener.setObjectValue("notifierFax",userTel);
	opener.setObjectValue("notifierAddress",userAddr);
	opener.setObjectValue("notifierCounty",userCounty);
	opener.setObjectValue("notifierZipCode",userZipCode);
	opener.checkedRadio(opener.document.all.item("notifierTitle"),jobTitle);
	opener.checkedRadio(opener.document.all.item("notifierType"),userDept);	
	
	window.close();
}

function init(){
	setDisplayItem('queryAll,spanInsert,spanDuplicate,spanUpdate,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden','H');
}

function cancel(){
	window.close();
}

</script>
</head>
<body onload="init();">
<form id="form1" name="form1" method="post" action="popUser.jsp">
<table width="100%" cellspacing="0" cellpadding="0" width="100%" height="100%">
<!--Form區============================================================-->
<tr><td nowrap class="bg" >
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%">	
		<tr>
			<td class="td_form">姓名：</td>
			<td class="td_form_white">
				<input type="text" class="field_Q" name="q_userName" size="20" maxlength="20" value="<%=obj.getQ_userName()%>"/>
			</td>	
		</tr>
		<tr>
			<td class="td_form">E-Mail：</td>
			<td class="td_form_white">
				<input type="text" class="field_Q" name="q_userEmail" size="20" maxlength="100" value="<%=obj.getQ_userEmail()%>"/>　
			</td>	
		</tr>
		<tr>
			<td class="td_form">服務機關：</td>
			<td class="td_form_white">
				<input type="text" class="field_Q" name="q_userJob" size="10" maxlength="10" value="<%=obj.getQ_userJob()%>"/>
			</td>	
		</tr>
	</table>
	</div>	
</td></tr>

<!--Toolbar區============================================================-->
<tr><td class="bgToolbar" style="text-align:center">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input class="toolbar_default" type="button" followPK="false" name="doQuery" value="查　詢" onClick="checkField(this.name);">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doQuery" value="關　閉" onClick="cancel();">&nbsp;
</td></tr>

<tr><td>
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="page.jsp" />
</td></tr>

<tr><td class="bg">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" >NO</th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">姓名</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">服務機關</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">E-Mail</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
		boolean primaryArray[] = {false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};
		boolean displayArray[] = {false,true,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false};
		out.write(View.getQuerylist(primaryArray,displayArray,objList,"true"));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.util.COSUT003F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%	

String nameField=org.apache.commons.lang.StringEscapeUtils.escapeHtml(request.getParameter("nameField"));
String idField=org.apache.commons.lang.StringEscapeUtils.escapeHtml(request.getParameter("idField"));

obj.setQueryAllFlag("true");
objList = (java.util.ArrayList) obj.queryAll();
%>
<html>
<head>
<title>聯絡人輔助視窗</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript">

function checkField(){	
	return true;
}

function queryOne(id, userid, username, usertel,userTelArea 
		,userTelExt , useremail ,jobTitle , userAddr , userCounty , userZipCode, userJob, userJobName , userDept )
{
	if (isObj(opener.document.all.item("commonUser")))
		opener.document.all.item("commonUser").value=id;
	if (isObj(opener.document.all.item("usernameid")))
		opener.document.all.item("usernameid").value=userid;
	if (isObj(opener.document.all.item("username")))
		opener.document.all.item("username").value=username;
	if (isObj(opener.document.all.item("usertel")))
		opener.document.all.item("usertel").value=usertel;	
	if (isObj(opener.document.all.item("useremail")))
		opener.document.all.item("useremail").value=useremail;

	if (isObj(opener.document.all.item("notifierUserID")))
		opener.document.all.item("notifierUserID").value=userid;
	if (isObj(opener.document.all.item("notifierName")))
		opener.document.all.item("notifierName").value=username;
	if (isObj(opener.document.all.item("notifierTel")))
		opener.document.all.item("notifierTel").value=usertel;
	if (isObj(opener.document.all.item("notifierTelArea")))
		opener.document.all.item("notifierTelArea").value=userTelArea;
	if (isObj(opener.document.all.item("notifierEamil")))
		opener.document.all.item("notifierEamil").value=useremail;
	if (isObj(opener.document.all.item("notifierEmail")))
		opener.document.all.item("notifierEmail").value=useremail;
	if (isObj(opener.document.all.item("notifierTitle")))
		opener.document.all.item("notifierTitle").value=jobTitle;
	
	if (isObj(opener.document.all.item("notifierUserID")))
		opener.document.all.item("notifierUserID").value=userid;	
	if (isObj(opener.document.all.item("notifierAddress")))
		opener.document.all.item("notifierAddress").value=userAddr;
	if (isObj(opener.document.all.item("notifierCounty")))
		opener.document.all.item("notifierCounty").value=userCounty;
	if (isObj(opener.document.all.item("notifierZipCode")))
		opener.document.all.item("notifierZipCode").value=userZipCode;	
	if (isObj(opener.document.all.item("notifierAreaCode")))
		opener.document.all.item("notifierAreaCode").value=userTelArea;
	if (isObj(opener.document.all.item("notifierTelExt")))
		opener.document.all.item("notifierTelExt").value=userTelExt;	
	if (isObj(opener.document.all.item("notifierDeptID")))
		opener.document.all.item("notifierDeptID").value=userJob;
	if (isObj(opener.document.all.item("notifierDept")))
		opener.document.all.item("notifierDept").value=userJobName;	
	if (isObj(opener.document.all.item("notifierType"))){
		var len = opener.document.getElementsByName("notifierType").length;	
		for(var i=0 ; i<len ; i++ ){		
		   if(opener.document.getElementsByName("notifierType")[i].value==userDept)	
			   opener.document.getElementsByName("notifierType")[i].checked = true;	
	    }
	}
		
		
	
	if (isObj(opener.document.all.item('<%=nameField%>')))
		opener.document.all.item('<%=nameField%>').value=userid;
	
	if (isObj(opener.document.all.item('<%=idField%>')))
		opener.document.all.item('<%=idField%>').value=id;
	
	window.close();
}
</script>
</head>
<body>
<form id="form1" name="form1" method="post" action="popCommonUser.jsp" onSubmit="return checkField()">
<!--回傳的欄位名稱  -->
<input type="hidden" name="<%=obj.getIdField()%>"  value="<%=obj.getTemp_id()%>"> 
<input type="hidden" name="<%=obj.getNameField()%>"  value="<%=obj.getTemp_other()%>"> 

<!--預設欄位名稱  -->
<input type="hidden" name="idField" value="<%=obj.getIdField()%>">
<input type="hidden" name="nameField" value="<%=obj.getNameField()%>">

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
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">聯絡人帳號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">聯絡人姓名</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">聯絡人電話</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">聯絡人E-MAIL</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
		<%
		boolean primaryArray[] = {true, true, true, true, true ,true ,true ,true ,true,true ,true,true ,true ,true };
		boolean displayArray[] = {false, true, true, true, true ,false ,false ,false ,false ,false ,false ,false ,false ,false };
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

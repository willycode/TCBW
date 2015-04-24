<!--
程式目的：委員基本資料維護作業
程式代號：hfrin0101f.jsp
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="HFRIN0101" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.hfrin.HFRIN0101F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	obj = (com.kangdainfo.tcbw.view.hfrin.HFRIN0101F)obj.queryOne();	
}else if ("insert".equals(obj.getState()) || "insertError".equals(obj.getState())) {
	obj.insert();
	if ("insertSuccess".equals(obj.getState())) {
		obj.setQueryAllFlag("true");
		obj.setQ_id(obj.getId());
	}
}else if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())) {
	obj.update();
	if ("updateSuccess".equals(obj.getState())) {
		obj.setQueryAllFlag("true");
	}
}else if ("delete".equals(obj.getState()) || "deleteError".equals(obj.getState())) {
	obj.delete();
	if("deleteError".equals(obj.getState())){
		obj = (com.kangdainfo.tcbw.view.hfrin.HFRIN0101F)obj.queryOne();
	}
}
if ("true".equals(obj.getQueryAllFlag())){
	objList = (java.util.ArrayList) obj.queryAll();
}
%>
<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">
var insertDefault = new Array(
		new Array("committeeNum", "<%=obj.getComNum()%>"));
function checkField(){
	var alertStr = "";
	if(form1.state.value == "queryAll"){
		alertStr += checkQuery();
	}else if(form1.state.value=="insert"||form1.state.value=="insertError"||form1.state.value=="update"||form1.state.value=="updateError"){
		alertStr += checkEmpty(form1.committeeNum, "委員編號");
		if(form1.committeeNum.value != "")
			alertStr += checkNumber(form1.committeeNum, "委員編號");
		alertStr += checkEmpty(form1.name, "姓名");
		//alertStr += checkEmpty(form1.idNum, "身分證號");
		if(form1.idNum.value != "")
			alertStr += checkPersonalID(form1.idNum, "身分證號");
		alertStr += checkEmail(form1.email, "e-mail");
	}
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	return true;
}

function queryOne(id){
	form1.id.value = id;
	form1.state.value = "queryOne";
	beforeSubmit();
	form1.submit();
}

function init() {

}
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<!--Query區============================================================-->
<div id="queryContainer" style="width:500px;height:150px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<table class="queryTable" border="1">
	<tr>
		<td nowrap class="queryTDLable">姓名：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_name" size="10" maxlength="10" value="<%=obj.getQ_name()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">性別：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="radio" name="q_sex" value="1" <%=obj.getQ_sex().equals("1")?"checked":""%>>男
			<input class="field_Q" type="radio" name="q_sex" value="0" <%=obj.getQ_sex().equals("0")?"checked":""%>>女			
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">身分證號：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_idNum" size="10" maxlength="10" value="<%=obj.getQ_idNum()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDInput" colspan="2" style="text-align:center;">
			<input type="hidden" name="q_id" value="<%=obj.getQ_id()%>">
			<input type="hidden" name="q_isQuery">
			<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="確　　定" onClick="form1.q_isQuery.value='Y'">
			<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="whatButtonFireEvent(this.name)">
		</td>
	</tr>
	</table>
</div>
<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer">
	<table class="table_form" width="99%" height="100%">
	<tr>
		<td nowrap class="td_form"><font color="red">*</font>委員編號：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field" type="text" name="committeeNum" size="3" maxlength="3" value="<%=obj.getCommitteeNum()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form"><font color="red">*</font>姓名：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="name" size="20" maxlength="10" value="<%=obj.getName()%>">
		</td>
		<td nowrap class="td_form">性別：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="radio" name="sex" value="1" <%=obj.getSex().equals("1")?"checked":""%>>男
			<input class="field" type="radio" name="sex" value="0" <%=obj.getSex().equals("0")?"checked":""%>>女
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">通訊地址：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="communAddress" size="50" maxlength="100" value="<%=obj.getCommunAddress()%>">
		</td>
		<td nowrap class="td_form">戶籍地址：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="houseAddress" size="50" maxlength="100" value="<%=obj.getHouseAddress()%>">
		</td>		
	</tr>
	<tr>
		<td nowrap class="td_form">身分證號：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="idNum" size="10" maxlength="10" value="<%=obj.getIdNum()%>">
		</td>
		<td nowrap class="td_form">e-mail：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="email" size="30" maxlength="50" value="<%=obj.getEmail()%>">
		</td>
	</tr>		
	<tr>
		<td nowrap class="td_form">電話：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="telArea" size="2" maxlength="4" value="<%=obj.getTelArea()%>">
			- <input class="field" type="text" name="tel" size="10" maxlength="10" value="<%=obj.getTel()%>">
		</td>
		<td nowrap class="td_form">FAX：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="faxArea" size="2" maxlength="4" value="<%=obj.getFaxArea()%>">
			- <input class="field" type="text" name="fax" size="10" maxlength="10" value="<%=obj.getFax()%>">
		</td>
	</tr>	
	<tr>
		<td nowrap class="td_form">手機：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="mobile" size="10" maxlength="10" value="<%=obj.getMobile()%>">
		</td>
		<td nowrap class="td_form">異動資訊：</td>
		<td nowrap class="td_form_white">
			[<input class="field_RO" type="text" name="editID" size="10" value="<%=obj.getEditID()%>">
			/<input class="field_RO" type="text" name="editDate" size="7" value="<%=obj.getEditDate()%>">] 
		</td>		
	</tr>	
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:center">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getId()%>">
	<jsp:include page="../../home/toolbar.jsp" />
</td></tr>

<tr><td nowrap class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>

<!--List區============================================================-->
<tr><td nowrap class="bgList">
<div id="listContainer">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">委員編號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">姓名</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">性別</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">身分證號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">e-mail</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true, false, false, false, false, false};
	boolean displayArray[] = {false, true, true, true, true, true};
	String[] alignArray = {"center", "center", "center", "center", "center", "center"};
	out.write(View.getQuerylist(primaryArray,displayArray,alignArray,objList,obj.getQueryAllFlag()));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName){
	  case "update":
		  //先mark
		  //document.all.item("committeeNum").disabled=true;
		  break;
	}
}
</script>
</body>
</html>
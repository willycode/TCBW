
<!--
程式目的：個人基本資料維護
程式代號：sysap001f_s
程式日期：0950518
程式作者：clive.chang
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.common.view.sys.ap.SYSAP001F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
String actItem = Common.checkGet(request.getParameter("actItem"));
if (User!=null) {
	obj.setId(User.getId().toString());
	String confirm = request.getParameter("confirm");
	if (Common.get(confirm).equals("確　定　更　新")) {
		obj.setEditID(User.getUserId());
		obj.doUpdatePeraonal();
	}	
	obj = (com.kangdainfo.common.view.sys.ap.SYSAP001F)obj.queryOne();
	
%>

<html>
<head>
<title>個人基本資料維護</title>
<%@ include file="../../home/meta.jsp" %>
<script type="text/javascript">
function checkField(){
    var alertStr="";
		alertStr += checkEmpty(form1.userId,"帳號");
		alertStr += checkEmpty(form1.userName,"姓名");
		//alertStr += checkEmpty(form1.userPWD,"密碼");
		if (form1.userPWD.value!="" && form1.userPWD.value.length<6) {
			alertStr += "密碼長度至少為6碼，請重新輸入！\n";
		}
		alertStr += checkEmpty(form1.deptId,"機關");
		//alertStr += checkEmpty(form1.userTitle,"職稱");
    if(alertStr.length!=0){ alert(alertStr); return false; }
    form1.submit();
}

function init(s) {	
	form1.userPWD.value = "<%=obj.getUserPWD()%>";
	if ((s!=null) && (s=="修改完成")) {
		/**
		if (confirm("個人基本資料更新完成，是否要關閉視窗？")){
			window.close();
		}	
		**/	
		alert("個人基本資料更新完成");
		closePersonalWindow();		
	} else {
		showErrorMsg(s);
	}

}

function closePersonalWindow() {
	window.close();
}

function activeItem() {
	if (isObj(opener.document.getElementById('<%=actItem%>'))) {
		opener.titleBarButtonClick(opener.document.getElementById('<%=actItem%>'));
		opener.document.getElementById('<%=actItem%>').focus();
	}
}
//onbeforeunload="activeItem();"
</script>

</head>

<body topmargin="5" onLoad="init('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<table class="table_form" width="100%" height="100%">
	<tr>
		<td nowrap class="td_form"><font color="red">*</font>帳號：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field_RO" type="text" name="userId" size="20" maxlength="20" value="<%=obj.getUserId()%>">
		</td>
	</tr>
	<tr>
	  <td nowrap class="td_form"><font color="red">*</font>姓名：</td>
	  <td nowrap class="td_form_white">
			<input class="field" type="text" name="userName" size="20" maxlength="50" value="<%=obj.getUserName()%>">		</td>
	  <td nowrap class="td_form">電話：</td>
	  <td nowrap class="td_form_white">
			<input class="field" type="text" name="userTel" size="20" maxlength="20" value="<%=obj.getUserTel()%>">		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">密碼：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="password" autocomplete="off" name="userPWD" size="12" maxlength="50" value="">		</td>
		<td nowrap class="td_form">傳真：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="userFax" size="20" maxlength="20" value="<%=obj.getUserFax()%>">		</td>
	</tr>
	<tr>
	  <td nowrap class="td_form"><font color="red">*</font>單位：</td>
	  <td nowrap colspan="3" class="td_form_white">
			<%=View.getPopOrgan("field_PRO","deptId",obj.getDeptId(),obj.getDeptIdName(),"N","accountingYear","manageDept")%>		</td>
	</tr>
	<tr>
	  <td nowrap class="td_form">手機：</td>
	  <td nowrap colspan="3" class="td_form_white">
			<input class="field" type="text" name="userMobile" size="20" maxlength="20" value="<%=obj.getUserMobile()%>">
		</td>
	  </tr>	
	<tr>
	  <td nowrap class="td_form">Email：</td>
	  <td nowrap colspan="3" class="td_form_white">
			<input class="field" type="text" name="userEmail" size="60" maxlength="100" value="<%=obj.getUserEmail()%>">		</td>
	  </tr>
	</table>
</td></tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bg" style="text-align:center">
<span id="spanConfirm">
<input class="toolbar_default" type="submit" followPK="false" name="confirm" value="確　定　更　新" >
</span>
</td></tr>

</table>
</form>

</body>
</html>
<%
} else {
	out.println("<br><br><br><p align=center>對不起，找不到您的個人資料，若問題持續，請洽系統管理者或承辦人員！<br><br></p>");	
}
%>
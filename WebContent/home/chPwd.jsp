<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<%
String newPwd1 = Common.get(request.getParameter("newPassword1"));
String newPwd2 = Common.get(request.getParameter("newPassword2"));

String js = "";
if (!"".equals(newPwd1) && !"".equals(newPwd1) && newPwd1.equals(newPwd2)) {

	String newPass = Common.getDigestString(newPwd1,"SHA-1");
	if(!Common.get(User.getUserPwd()).equals(newPass)){
		User.setUserPwd(newPass);
		User.setUserPwdModDt(Datetime.getYYYMMDD()+Datetime.getHHMMSS());
		ServiceGetter.getInstance().getCommonService().update(User);
		out.write("密碼變更成功，下次請用新密碼<a href=\"../index.jsp\" target=\"_top\">登入</a>！<br><br><br>");
		//"<a href=\"#\" onclick=\"window.close();\">關閉</a>！");
		return;
	} else {
		js = "alert('密碼違反密碼政策-不得沿用上次的密碼');";
	}
}
%>
<html>
<head>
<title>變更密碼</title>
<link rel="stylesheet" href="../js/default.css" type="text/css">
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript">
function checkField(){
	var alertStr="";
			
	alertStr += checkEmpty(form1.newPassword1,"新密碼");
	alertStr += checkEmpty(form1.newPassword2,"確認新密碼");
	
	var newPassword1=document.all.item("newPassword1").value;
	var newPassword2=document.all.item("newPassword2").value;
	if (newPassword1!=newPassword2){
		alertStr += "[新密碼]和[確認新密碼]不同，請重新輸入！\n";
	}		
	
	if(newPassword1!="" && newPassword1.toUpperCase() == '<%=Common.get(User.getUserId())%>'.toUpperCase()) {
		alertStr += "密碼違反密碼政策-密碼不得與帳號相同，請重新輸入！\n";
	}
	
	if(form1.newPassword1.value!=""){
		var patternString=/^(?=^.{8,}$)((?=.*[A-Za-z])(?=.*[0-9]))^.*$/;
		//var patternString=/^(?=.*\d)(?=.*[a-zA-Z]).{8,}$/;
		var regex = new RegExp(patternString);
		if(!regex.test(newPassword1)) {
			alertStr += "密碼違反密碼政策-長度至少8位(含)以上且為英、數字混合、英文字母大小寫應視為不同，請重新輸入！\n";
		}
	}
	
	if(alertStr.length!=0){
		alert(alertStr);
		return false;
	}	
}
function init() {
	<%=js%>
}
</script>
</head>
<body class="body" onload="init();">
<center>
<form method="post" name="form1" onSubmit="return checkField();">
<br>
<table class="bg" width="75%">
	<tr>
	<td nowrap colspan="2" class="td_form_white">
		說明：<br>
		1.帳號、密碼相同時需變更密碼<br>
		2.密碼若為系統預設密碼時也需變更密碼<br>
		3.每6個月需要更新一次密碼
	</td>
	</tr>
	<tr>
		<td nowrap  colspan="2" style="text-align:center; background:blue;color:white">【變更密碼】</td>
	</tr>		
	<tr>		
		<td nowrap class="td_form">&nbsp;請輸入新密碼：</td>
		<td nowrap class="td_form"  style="text-align:left">
			<input type="password" autocomplete="off" class="field" name="newPassword1" size="12" maxlength="50" value="" >
		</td>
	</tr>		
	<tr>		
		<td nowrap class="td_form">&nbsp;確認新密碼：</td>
		<td nowrap class="td_form"  style="text-align:left">
			<input type="password" autocomplete="off" class="field" name="newPassword2" size="12" maxlength="50" value="" >
		</td>
	</tr>	
	<tr><td nowrap class="td_form" colspan="2" style="background-color:black"></td></tr>
	<tr >
		<td nowrap align="center" colspan="2">
			<input class="toolbar_default" type="submit" name="btnSave" value="確　　定" >
		</td>
	</tr>	
</table>
</form>
</center>
</body>
</html>
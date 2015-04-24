<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.kangdainfo.ServiceGetter"%>
<%@ page import="com.kangdainfo.common.model.bo.*"%>
<%@ page import="com.kangdainfo.common.util.*"%>
<%@ page import="com.kangdainfo.web.util.*"%>
<%
if (Common.get(request.getParameter("logout")).equals("Y")) session.invalidate();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>身份驗證</title>
<style type="text/css">
<!--
body {
	background-image: url(images/side_menu_hover.gif);
	margin-left: 0px;
	margin-right: 0px;
}
-->
</style>
<script type="text/javascript" src="js/function.js"></script>
<script type="text/javascript">
var win = null;
function checkField(){
	if(win != null){
		//win.close();
	}	
	if (document.getElementById("userID").value==""){ 
		alert("請輸入使用者帳號");
		return false; 
	}	
	if (document.getElementById("userPWD").value==""){ 
		alert("請輸入使用者密碼");
		return false;
	}	
	form1.state.value = "submit";
	form1.action = "authLogin.jsp";
	form1.submit();
}

function openGetBackPw(){
	if (document.getElementById("userID").value==""){ 
		alert("請輸入使用者帳號");
		return; 
	}	

	var prop="";
	var windowTop=(window.screen.availHeight-300)/2;
	var windowLeft=(window.screen.availWidth-600)/2;
	prop=prop+"resizable=yes,width=600,height=300,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes";
	if(win != null)
	{
	   win.close();
	}
	win = window.open("sendBackPw.jsp?userId="+document.getElementById("userID").value, "winExp", prop);
}

function init() {
	if(win != null){
		win.close();
	}
	form1.userID.focus();
	form1.userPWD.value = "kds123456";
}
</script>

</head>
<body onLoad="init();">
<form id="form1" name="form1" method="post" autocomplete="off">
<table width="100%" height="100%" >
<tr><td nowrap align="center" valign="middle" >
<table width="900" border="0" align="center" cellpadding="0" cellspacing="0" id="___01">
	<tr>
		<td colspan="8">
			<img id="login_01" src="images/login_01.png" width="900" height="304"  /></td>
	</tr>
	<tr>
		<td rowspan="5">
			<img id="login_02" src="images/login_02.png" width="527" height="250"  /></td>
		<td height="26" colspan="5" bgcolor="#FFFFFF">
        <span id="sprytextfield1">
		    <label>
		      <input name="userID" id="userID" tabindex="1" value="admin" />
	        </label>
		<span class="textfieldRequiredMsg"></span></span>
	   </td>
		<td rowspan="5">
  <img id="login_04" src="images/login_04.png" width="1" height="246"  /></td>
		<td rowspan="5">
			<img id="login_05" src="images/login_05.png" width="90" height="250"  /></td>
	</tr>
	<tr>
		<td colspan="5">
			<img id="login_06" src="images/login_06.png" width="282" height="13"  /></td>
	</tr>
	<tr>
		<td height="29" colspan="5" bgcolor="#FFFFFF">
		  <span id="sprypassword1">
		    <label>
		      <input type="password" autocomplete="off" maxlength="12"  name="userPWD" id="userPWD" tabindex="2" value="" />
	      </label>
		    <span class="passwordRequiredMsg"></span></span>
	    </td>
	</tr>
	<tr>
		<td bgcolor="#FFFFFF">
           <input type="image" src="images/login_09.png" alt="登入" width="62" height="36" border="0" onClick="return checkField();"/>
        </td>
		<td bgcolor="#FFFFFF">
           <input type="image"  src="images/login_08.png" alt="重置" width="63" height="36" border="0" onClick="document.all.item('userID').value='';document.all.item('userPWD').value='';return false;"/>
        </td>
		<td bgcolor="#FFFFFF">
           <input type="image" src="images/login_10.png" width="78" height="36" border="0" usemap="getBackPw" onClick="openGetBackPw();return false;"/> 
        </td>
		<td bgcolor="#FFFFFF"><img id="login_11" src="images/login_11.png" width="1" height="36"  /></td>
		<td width="78" height="36" bgcolor="#FFFFFF">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="5">
		<img id="login_13" src="images/login_13.png" width="282" height="138"  /></td>
	</tr>
</table>
</td></tr></table>  
  <input type="hidden" name="state" />

</form>

</body>
</html>

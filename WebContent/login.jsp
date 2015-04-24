<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="com.kangdainfo.ServiceGetter"%>
<%@ page import="com.kangdainfo.common.model.bo.*"%>
<%@ page import="com.kangdainfo.common.util.*"%>
<%@ page import="com.kangdainfo.web.util.*"%>
<%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf" %>
<%
String	codeBase	=	request.getRequestURL().substring(0,request.getRequestURL().lastIndexOf("/"));
String	archive		=	"GetUserApplet.jar";
String	code		=	"GetUserApplet.class";
String	loginUrl	=	codeBase + "/authLogin.jsp?"+"j_logout="+Common.get(request.getParameter("logout"));

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

#apDiv
{ 
position:absolute;
left:723px ;
top:310px;
width:309px;
height:22px;
}

#apDiv1
{ 
position:absolute;
left:723px;
top:353px;
width:319px;
height:22px;
}

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

function openGetBackPw()
{
	/*
	if (document.getElementById("userID").value=="")
	{ 
		alert("請輸入使用者帳號");
		return; 
	}	
    */

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
	win = window.open("sendBackPw.jsp", "winExp", prop);
}



function init() 
{
	if(win != null)
	{
		win.close();
	}
	form1.userID.focus();
	//form1.userPWD.value = "kds123456";
}

function enter()
{
　if(event.keyCode==13)
  {
　　checkField();
　}

}

function preLogin()
{
	form1.action = "tcbw/conex/conex0001f.jsp";
	form1.submit();
}

</script>

</head>
<body onLoad="init();">
<% if (false) { %>
<APPLET NAME="自動登入AD帳號" CODE="GetUserApplet.class" ARCHIVE="GetUserApplet.jar" WIDTH="80" HEIGHT="80" STYLE="display:none">
	<PARAM NAME="loginUrl" VALUE="<%=loginUrl%>">
</APPLET>
<% } %>
<form id="form1" name="form1" method="post" autocomplete="off">
  
  <div id="apDiv">
  <table>
  <tr>
  <td>
     <img src="images/a.jpg" >
  </td>
  <td>
   <input type="text" class="field" maxlength="30" size="18" onkeydown="enter();"  name="userID" value="" id="userID" tabindex="1" style="FONT-SIZE: 13pt; width: 300px;height: 29px ;" />
  </td>
  </tr>
  </table>
  
  </div>
  <div id="apDiv1">
  <table> 
  <tr>
  <td>
  <img src="images/b.jpg" >
  </td>
  <td>
    <input type="password" class="field" maxlength="30" size="18"  onkeydown="enter();"   name="userPWD" value="" id="userPWD" tabindex="2" style="FONT-SIZE: 13pt; width: 300px;height: 29px ;" />
  </td>
  </tr>
  </table>
  </div>
  <table>
  <tr>
      <td nowrap width="230">
      </td>
      <td>
        <div align="left">
          <img src="images/login.png" width="900" height="550" usemap="#Map" border="0" align="top">
          <map name="Map">
            <area shape="rect" coords="673,391,746,421" onClick="openGetBackPw();return false;"  >
            <area shape="rect" coords="541,391,599,421" onClick="return checkField();"  >
            <area shape="rect" coords="607,391,665,422" onClick="document.all.item('userID').value='';document.all.item('userPWD').value='';return;"  >
          </map>        
          <input type="hidden" name="state" />
        </div>
      </td>
  </tr>
  </table>

</form>

</body>
</html>

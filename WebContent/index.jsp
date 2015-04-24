<%@ page contentType="text/html;charset=utf-8" %>
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

#apDiv
{ 
position:absolute;
left:723px ;
top:310px;
width:459px;
height:22px;
}

#apDiv1
{ 
position:absolute;
left:723px;
top:353px;
width:459px;
height:22px;
}

</style>
<script type="text/javascript" src="js/function.js"></script>
<script type="text/javascript">
var win = null;
function checkField()
{
	if(win != null)
	{
		//win.close();
	}	
	if (document.getElementById("userID").value=="")
	{ 
		alert("請輸入使用者帳號");
		return false; 
	}	
	if (document.getElementById("userPWD").value=="")
	{ 
		alert("請輸入使用者密碼");
		return false;
	}	
	form1.state.value = "submit";
	form1.action = "auth.jsp";
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
	win = window.open("sendBackPw.jsp?userId="+document.getElementById("userID").value, "winExp", prop);
}

function preLogin(){
	form1.action = "tcbw/conex/conex0001f.jsp";
	form1.submit();
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


</script>

</head>
<body onLoad="init();">
<form id="form1" name="form1" method="post" autocomplete="off">
 
  <table>
  <tr>
      <td nowrap width="230">
      </td>
      <td>
        <div align="left">
        <img src="images/index.png" width="900" height="550" usemap="#Map" border="0" align="top">
        <map name="Map">
          <area shape="rect" coords="757,395,830,425" onClick="preLogin();"  >
          <area shape="rect" coords="674,396,747,426" onClick="openGetBackPw();return false;"  >
          <area shape="rect" coords="542,395,598,428" onClick="return checkField();"  >
          <area shape="rect" coords="604,395,667,427" onClick="document.all.item('userID').value='';document.all.item('userPWD').value='';return;">
        </map>
        <input type="hidden" name="state" />
        </div>
      </td>
  </tr>
  
  </table>
  <div id="apDiv">
  <table>
  <tr>
  <td>
     <img src="images/a.jpg" >
  </td>
  <td>
   <input type="text" class="field" maxlength="30" size="18" name="userID" onkeydown="enter();"    value="" id="userID" tabindex="1" style="FONT-SIZE: 13pt; width: 300px;height: 29px ;" />
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
    <input type="password" class="field" maxlength="30" size="18" name="userPWD" onkeydown="enter();"    value="" id="userPWD" tabindex="2" style="FONT-SIZE: 13pt; width: 300px;height: 29px ;" />
  </td>
  </tr>
  </table>
  
  </div>
</form>
</body>
</html>

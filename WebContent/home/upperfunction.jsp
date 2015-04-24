<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" href="../js/style.css" type="text/css">
<script type="text/javascript" src="../js/function.js"></script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image:url(../images/bg2_03.gif);
}
-->
</style>
<script type="text/javascript">
function doMax(){
    top.fbody.document.getElementById("bodyFrame").cols = '26,0,*';
    document.getElementById('max').style.display = 'none';
    document.getElementById('normal').style.display = 'inline';        
	//for ie 10
    top.fbody.document.getElementById("bodyFrame").removeAttribute("cols");
    top.fbody.document.getElementById("bodyFrame").setAttribute("rows", "*");
    top.fbody.document.getElementById("bodyFrame").setAttribute("cols", "26,0,*");
}

function doNormal(){
	top.fbody.document.getElementById("bodyFrame").cols = '26,229,*';
    document.getElementById('max').style.display= 'inline';
    document.getElementById('normal').style.display = 'none';

    //for ie 10
    top.fbody.document.getElementById("bodyFrame").removeAttribute("cols");
    top.fbody.document.getElementById("bodyFrame").setAttribute("rows", "*");
    top.fbody.document.getElementById("bodyFrame").setAttribute("cols", "26,229,*");
}
</script>
</head>
<body onLoad="doNormal();">
<table cellspacing="0" cellpadding="0" border="0">
<tr>
  <td nowrap align="left" valign="top"><img src="../images/bg2_02.gif" width="22" height="31" alt=""></td>
</tr>
<tr>
  <td nowrap align="left" valign="top"><img src="../images/bg2_08.gif" width="22" height="14" alt=""></td>
</tr>
<tr>
    <td nowrap width="22" align="left" valign="top"><span id="max"><a href="#" onClick="doMax()" title="隱藏功能選單"><img src="../images/bg2_09.gif" border="0" alt="隱藏功能選單"></a></span><span id="normal"><a href="#" onClick="doNormal()" title="顯示功能選單"><img src="../images/bg2_10.gif" border="0" alt="顯示功能選單"></a></span></td>
</tr>
<tr>
  <td nowrap align="left" valign="top"><img src="../images/bg2_11.gif" width="22" height="14" alt=""></td>
</tr>
</table>

</body>
</html>

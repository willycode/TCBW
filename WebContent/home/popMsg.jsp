<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="head.jsp" %>
<%
Long msgId = Common.getLong(request.getParameter("q"));
CommonMsg obj = (CommonMsg) View.getObject("from CommonMsg where id=" + msgId);
if (obj!=null) {
%>
<html>
<head>
<title>訊息通知</title>
<meta http-equiv="Expires" content="-1"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="private"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../js/default.css" type="text/css">
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
}
-->
</style></head>
<body>
<table border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td nowrap width="20"><img src="../images/msg_dtl_01.png" width="592" height="36" border="0" usemap="#Map"></td>
	</tr>	
	<tr >
		<td nowrap  background="../images/msg_dtl_02.png"><table width="562" height="320" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td nowrap valign="top"><%=Common.FormatStr(obj.getMsgBody(),"N")%></td>
	      </tr>
	    </table></td>
	</tr>
	<tr >
	  <td nowrap ><img src="../images/msg_dtl_04.png"></td>
    </tr>	
</table>


<map name="Map">
  <area shape="rect" coords="559,1,593,25" href="javascript:window.close();">
  <area shape="rect" coords="558,0,560,1" href="#">
</map>
</body>
</html>
<%} else { out.write("查詢錯誤.."); } %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.kangdainfo.common.util.*" %>
<jsp:useBean id="obj" scope="page" class="com.kangdainfo.common.view.sys.wm.SYSWM001F"/>
<%
long newsID = Common.getLong(request.getParameter("newsID"));
if (newsID>0) obj.setId(String.valueOf(newsID));
else return;

obj = (com.kangdainfo.common.view.sys.wm.SYSWM001F) obj.queryOne();
%>
<html>
<head>
<title>系統公告</title>
<meta http-equiv="Expires" content="-1"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="private"/>
<link rel="stylesheet" href="../js/default.css" type="text/css">
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/function.js"></script>
</head>
<body>
<div align="center">
<br>
<table class="table_form" width="96%">
	<tr>
		<td nowrap  colspan="2" class="td_form" style="background:blue;color:white;text-align:left">【系統公告】</td>
	</tr>
	<tr>		
		<td nowrap class="td_form" width="20%" >&nbsp;公告主旨：</td>
		<td nowrap class="td_form_white" style="text-align:left"><%=obj.getSubject() %></td>
	</tr>	
	<tr>		
		<td nowrap class="td_form">&nbsp;公告期限：</td>
		<td nowrap class="td_form_white"  style="text-align:left">
			<%=Common.formatYYYMMDD(obj.getStartDate(),4) + " ~ " + Common.formatYYYMMDD(obj.getEndDate(),4)%>
		</td>
	</tr>		
	<tr>		
		<td nowrap class="td_form" >&nbsp;公告內容：</td>
		<td nowrap valign="top" class="td_form_white"  style="text-align:left" height="150">
			<%=Common.FormatStr(obj.getContent(),"N")%>
		</td>
	</tr>	
	<%=obj.getItemPictureHTML()%>
		
	<tr >
		<td nowrap class="td_form_white" colspan="2" style="text-align:center">
			<input class="toolbar_default" type="button" name="btnClose" value="關閉視窗" onClick="window.close();">		</td>
	</tr>	
</table>
</div>

</body>
</html>
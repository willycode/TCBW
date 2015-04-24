<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<%
String ids = org.apache.commons.lang.StringEscapeUtils.escapeHtml(request.getParameter("ids"));
if (ids!=null && ids.length()>0) {
	String[] arrIds = ids.split(",");
	StringBuilder sb = new StringBuilder();
	if (arrIds!=null && arrIds.length>0) {

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
<script type="text/javascript">
function genReadNewsLog(readType) {
	getRemoteData('genNewsReadLog.jsp','<%=ids%>');
	if (readType!=null && readType==1) {
		alert('訊息讀取記錄寫入成功！');
	}	
}

function init() {
	window.setInterval("genReadNewsLog()", 5000);
}
</script>
</head>
<body onLoad="init();">
<center>
<br>
<table class="table_form" width="96%">
	<tr >
		<td nowrap class="td_form_white" colspan="2" style="text-align:center">
			<input class="toolbar_default" type="button" name="btnClose" value="讀取記錄" onClick="genReadNewsLog(1);">　<input class="toolbar_default" type="button" name="btnClose" value="關閉視窗" onClick="window.close();">		</td>
	</tr>
	<tr>
		<td nowrap  colspan="2" class="td_form" style="background:blue;color:white;text-align:left">【系統公告】</td>
	</tr>
	<%
	for (String id : arrIds) {
		if (Validate.checkInt(id) && Common.getInt(id)>0) {
			com.kangdainfo.common.view.sys.wm.SYSWM001F obj = new com.kangdainfo.common.view.sys.wm.SYSWM001F();
			obj.setId(id);
			obj = obj.doQueryOne();
	%>	
	<tr>		
		<td nowrap class="td_form" width="20%" >&nbsp;公告主旨：</td>
		<td nowrap class="td_form_white" style="text-align:left"><%=obj.getSubject()%></td>
	</tr>	
	<tr>		
		<td nowrap class="td_form">&nbsp;公告期限：</td>
		<td nowrap class="td_form_white"  style="text-align:left">
			<%=Common.formatYYYMMDD(obj.getStartDate(),4) + " ~ " + Common.formatYYYMMDD(obj.getEndDate(),4)%></td>
	</tr>		
	<tr>		
		<td nowrap class="td_form" >&nbsp;公告內容：</td>
		<td nowrap valign="top" class="td_form_white"  style="text-align:left" height="150">
			<%=Common.FormatStr(obj.getContent(), obj.getIsHTML()) %>
		</td>
	</tr>	
	<%=obj.getItemPictureHTML()%>
	<tr>		
		<td nowrap colspan="2">
			<hr size="1">
		</td>
	</tr>	
	<%
		}
	}
	%>		
</table>
</center>

</body>
</html>
<%
	}
}
%>
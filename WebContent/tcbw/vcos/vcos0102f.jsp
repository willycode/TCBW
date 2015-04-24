<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
</head>
<body>

<form action="">
	<!-- toobar -->
	<div>
		<table width="100%" cellspacing="0" cellpadding="0">
			<!--Toolbar區============================================================-->
			<tr><td nowrap class="bgToolbar" style="text-align:left">
				<button class="toolbar_default">存　檔</button>&nbsp;
				<button class="toolbar_default">放　棄</button>&nbsp;
				<button class="toolbar_default" onclick="window.location.href='vcos0101q.jsp'">返　回</button>
			</td></tr>
		</table>
		<!--  tab  -->
	</div>
<TABLE>
	<TR>
	    <td nowrap nowrap CLASS="tab_border1" ID=t1>案件登錄</TD>
	</TR>
</TABLE>
	
	<jsp:include page="vcos0103.jsp"></jsp:include>
</form>
</body>
</html>
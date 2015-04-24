<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj"  scope="request" class="com.kangdainfo.tcbw.view.pivot.PIVOT0501R">
    <jsp:setProperty name='obj' property='*'/>
</jsp:useBean>
     
<html>
<head>
<meta http-equiv="Expires" content="-1"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">


function init()
{

}
</script>

</head>
<body onLoad="init();">
<form id="form1" name="form1" method="post" autocomplete="off" >
<br>
<table cellspacing="0" cellpadding="0" width="100%" class="bg">
<tr>
<td>
	<table border="1" width="100%" class="table_form">
	    <tr>
			<td colspan="2" style="text-align:center" class="td_form"></td>
		</tr>
		<tr>
			<td class="td_form" width="30%">資料年月：</td>
			<td class="td_form_white">
			    <input class="field_Q" name="q_dateS" type="text" size="5" maxlength="5" value="<%=obj.getQ_dateS()%>" />~
			    <input class="field_Q" name="q_dateE" type="text" size="5" maxlength="5" value="<%=obj.getQ_dateE()%>"/>
			    (請輸入民國年月，例如：10301~10312)
			</td>
		</tr>
	    <tr>
			<td class="td_form">許可證字號：</td>
			<td class="td_form_white">
				<select name="q_permitKey" class="field">
				<%=View.getOptionCodeKind("MEDPKID", obj.getQ_permitKey())%>
			</select>	
				<input class="field_Q" name="q_permitNo" size="8" maxlength="8" value="<%=obj.getQ_permitNo()%>">
			</td>
		</tr>	
		<tr>
			<td class="td_form">申請商：</td>
			<td class="td_form_white">
				<input class="field_Q" name="q_applicationName" size="25" maxlength="25" value="<%=obj.getQ_applicationName()%>">
			</td>
		</tr>	
		<tr>
			<td class="td_form">製造廠：</td>
			<td class="td_form_white">
				<input class="field_Q" name="q_manufactorName" size="25" maxlength="25" value="<%=obj.getQ_manufactorName()%>">
			</td>
		</tr>	
		<tr>
			<td class="td_form">品名：</td>
			<td class="td_form_white">
				<input class="field_Q" name="q_productName" size="25" maxlength="25" value="<%=obj.getQ_productName()%>">
			</td>
		</tr>	
		<tr>
			<td class="queryTDInput" colspan="2" style="text-align:center;">
				<input id="query" type="button" name="query" value="產製報表" class="toolbar_default" />
			</td>
		</tr>	
	</table>
	</td>
	</tr>
</table>
<input type="hidden" name="state" value="<%=obj.getState()%>"/>
</form>
  <script type="text/javascript">
	$(document).ready(function()
	{
		$("#query").click(function()
		{

			var alertStr = "";

			alertStr+=checkNumber(form1.q_dateS,"資料年月(起)");
			alertStr+=checkNumber(form1.q_dateE,"資料年月(迄)");
			
			if(alertStr.length!=0){ alert(alertStr); return false; }
			
			form1.action = "pivot0501p.jsp" ;
			form1.target = "_blank";
			beforeSubmit();
			form1.submit();
		});
	});
</script>
</body>
</html>
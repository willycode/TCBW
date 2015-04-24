<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj"  scope="request" class="com.kangdainfo.common.view.sys.ex.SYSEX900R">
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
<script type="text/javascript">
function setReportType(intType) {
	form1.state.value = intType;
	form1.submit();
}
</script>

</head>
<body>
<form id="form1" name="form1" method="post" autocomplete="off" action="sysex901p.jsp">

	<div id="formContainer" style="height:auto">
	<table class="queryTable" border="1">
		<tr>
			<td nowrap class="queryTDLable">代碼種類：</td>
			<td nowrap class="queryTDInput">
				<select class="field_Q" type="select" name="q_id">
				<%=View.getOption("select id, codeKindId||'-'||codeKindName from " + CommonCodeKind.class.getCanonicalName() + " order by codeKindId", obj.getQ_id())%>
				</select>
			</td>
		</tr>
		<tr>
			<td nowrap class="queryTDLable">代碼編號：</td>
			<td nowrap class="queryTDInput">
				<input type="text" class="field_Q" name="q_codeId" size="20" maxlength="10" value="<%=obj.getQ_codeId()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="queryTDLable">代碼名稱：</td>
			<td nowrap class="queryTDInput">
				<input type="text" class="field_Q" name="q_codeName" size="20" maxlength="50" value="<%=obj.getQ_codeName()%>">
			</td>
		</tr>		
		<tr>
			<td nowrap class="queryTDLable">英文名稱：</td>
			<td nowrap class="queryTDInput">
				<input type="text" class="field_Q" name="q_codeEngName" size="20" maxlength="50" value="<%=obj.getQ_codeEngName()%>">
			</td>
		</tr>		
	<tr>
		<td nowrap class="td_form">列印頁碼：</td>
		<td nowrap class="td_form_white">
		<select class="field_Q" name="isPrintPageNumber">
				<%=View.getYNOption(obj.getIsPrintPageNumber().equals("")?"Y":obj.getIsPrintPageNumber())%>
		</select>		
		</td>
	</tr>		
	<tr>
		<td nowrap class="queryTDLable">前置頁碼：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="pagginPrefix" size="10" maxlength="10" value="">
		</td>
	</tr>		
	<tr>
		<td nowrap class="td_form">起始頁碼：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="pageNumberVar" size="5" maxlength="5" value="1">
		</td>
	</tr>		
	<tr>
		<td nowrap class="queryTDLable">輸出格式：</td>
		<td nowrap class="queryTDInput">
			<select name="q_outputFormat" class="field_Q">
				<%=View.getJasperReportFormatOption(obj.getQ_outputFormat())%>
			</select>			
		</td>
	</tr>		
		<tr>
			<td nowrap class="queryTDInput" colspan="2" style="text-align:center;">
				<input class="toolbar_default" followPK="false" type="submit" name="btnTableModel" value="產製報表">
			</td>			
		</tr>
	</table>
	</div>
<input type="hidden" name="state" value="<%=obj.getState()%>"/>
 </form>
</body>
</html>
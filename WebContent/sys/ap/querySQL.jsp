<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../home/head.jsp" %>
<%@ page import="java.sql.*" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.common.view.sys.ap.SYSAP501F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%	
	if (UserRole.getRoleId()>2) {	
		String strTableContent = "";
		if ("queryAll".equals(obj.getState())) {
			strTableContent = obj.getTableContent();
		}
%>

<html>
<head>
<%@ include file="../../home/meta.jsp" %>
<script type="text/javascript">

function checkField() {
	var alertStr = "";
	var sqlCmd = form1.sqlCmd.value;
	
	if (sqlCmd=="") alertStr = "請輸入查詢條件！";	
	if (alertStr.length!=0) { 
		alert(alertStr); return false; 
	} else {
		form1.state.value = "queryAll";
		return true;
	}
}

function init(){
	form1.state.value="init";
}
</script>
</head>
<body topmargin="10" onLoad="init();">

<form id="form1" name="form1" method="post" onSubmit="return checkField()">
  <table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr>
<td nowrap class="bg">
      <table class="queryTable" cellspacing="0" cellpadding="0" align="center">
	  <tr>
        <td nowrap class="td_form" colspan="6" style="text-align:center">請輸入JDBC SQL</td>
	  </tr>
	  <tr>
		<td nowrap class="queryTDLable">SQL：</td>
		<td nowrap colspan="3" class="queryTDInput">
		  <textarea name="sqlCmd" cols="60" rows="4" class="field"><%=obj.getSqlCmd()%></textarea>
      </td>
	  </tr>	
	  <tr>
		<td nowrap class="queryTDLable">isUpdate：</td>
		<td nowrap colspan="3" class="queryTDInput">
			<input type="checkbox" id="isUpdate" value="Y">
      </td>
	  </tr>		  
	
	  <tr>
		<td nowrap class="queryTDInput" colspan="6" style="text-align:center;">
			<input type="hidden" name="state" value="<%=obj.getState()%>">
			<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="確　　定" >
			　
		  <input class="toolbar_default" followPK="false" type="reset" name="queryCannel" value="取　　消" >		</td>
	  </tr>
	  </table>
</td>
</tr>

<!--Toolbar區============================================================-->
<tr>
<td nowrap>
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td>
</tr>
<!--List區============================================================-->
<tr>
<td nowrap class="bg">
	<%=strTableContent%>
</td>
</tr>
</table>
</form>
</body>
</html>
<%
}
%>

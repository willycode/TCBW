<!--
程式目的：列印時是否個資遮蔽
程式代號：
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%@ page import="java.io.*" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0102Q">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<% 
String code = Common.get(request.getParameter("code"));
String methodName= Common.get(request.getParameter("methodName"));
String hql=Common.get(request.getParameter("hql"));
String db=Common.get(request.getParameter("db"));
%>
<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">
function init() 
{
	setDisplayItem("spanQueryAll,spanInsert,spanClear,spanConfirm,spanUpdate,spanDelete,spanListPrint,spanListHidden","H");
	listContainerRowClick(0);
}

function checkURL(surl)
{	
	if("N"==surl) {
	// 選擇不遮蔽個資時記錄相關LOG
	var url = '../../ajax/jsonCommonLogDb.jsp';
	var q = "&code=" + "<%=code%>"; 
		q +="&methodName=" + "<%=methodName%>";
		q +="&db=" + "<%=db%>";
		q +="&hql=" + "<%=hql%>";
	var xUserUpdate = getRemoteData(url,q);
	}
	window.opener.report(surl);
	window.close();
}

</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off" >
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
	<td class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" height="100%">
	    是否遮蔽個資?
	   <tr>
           <td>
                                      是:<input class="field_Q" type="button" name="doCloseUserInfo" value="Y" onclick="checkURL('Y');">
           </td>
       </tr>
       <tr>
           <td>
                                       否:<input class="field_Q" type="button" name="doCloseUserInfo" value="N" onclick="checkURL('N');">
           </td>
       </tr>
	</table>
	</div>
</tr>
<!--Toolbarå============================================================-->
<tr>
<td nowrap class="bgToolbar"  style="text-align: center">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="caseType" value="<%=obj.getCaseType()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="doType">
	<jsp:include page="../../home/toolbar.jsp" />
</td>
</tr>

</table>

</form>
</body>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName)
{
	switch (buttonName)
	{
	case "queryAll":
		break;
	}
}

</script>
</html>

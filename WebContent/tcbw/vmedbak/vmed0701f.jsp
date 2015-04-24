<!--
程式目的：
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
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="VMED0700F" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.vmed.VMED0101F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%

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

function checkField()
{
	
}


function init() 
{
	changeTab(1);
	setDisplayItem("spanQueryAll,spanInsert,spanDelete,spanClear,spanListPrint,spanListHidden","H");
}

</script>
</head>
<body  onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">

<table width="100%" cellspacing="0" cellpadding="0">

<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:left">
    <input type="hidden" name="id" value="">	
    <input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="userName" value="<%=User.getUserName()%>">
	<input type="hidden" name="changeTabValue" value="<%=obj.getChangeTabValue()%>">
	
	<jsp:include page="../../home/toolbar.jsp" />
	<span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit" value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	
</td>
</tr>
</table>

<TABLE CELLPADDING=0 CELLSPACING=0>
	<TR>
		<td nowrap ID="t1" CLASS="tab_border1"><a id="aTab1" href="#" onClick="changeTab(1);">登錄</a></td>
		<td nowrap ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">廠商回覆</a></td>				
		<td nowrap ID="t5" CLASS="tab_border2"><a id="aTab5" href="#" onClick="changeTab(5);">警訊摘譯</a></td>
		<td nowrap ID="t6" CLASS="tab_border2"><a id="aTab6" href="#" onClick="changeTab(6);">摘譯確認</a></td>
		<td nowrap ID="t7" CLASS="tab_border2"><a id="aTab7" href="#" onClick="changeTab(7);">警訊公告</a></td>			
	</TR>
</TABLE>


<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
    <td nowrap class="bg">
    <jsp:include page="vmed0101js.jsp" />
    <jsp:include page="vmed0102f.jsp" />
    <jsp:include page="vmed0402f.jsp" />
    <jsp:include page="vmed0502f.jsp" />
    <jsp:include page="vmed0602f.jsp" />
    <jsp:include page="vmed0702f.jsp" />
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
	   case "doDoQuit":
			  if(confirm("確定回到查詢頁面?"))
			  {
				   form1.action = "vmed0701q.jsp";
				   form1.state.value = "queryAll";
				   form1.submit();
			  }
			  break;
	}
}
</script>
</html>

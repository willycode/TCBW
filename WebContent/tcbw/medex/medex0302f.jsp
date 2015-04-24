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
<jsp:include page="../../home/secure.jsp"/>
<jsp:useBean id="obj0302" scope="request" class="com.kangdainfo.tcbw.view.medex.MEDEX0302F">
	<jsp:setProperty name="obj0302" property="*"/>
</jsp:useBean>
<%

 if("update".equals(obj0302.getState()) || "updateError".equals(obj0302.getState())  || "upload".equals(obj0302.getState())) 
 {
	obj0302.update();
	if("1".equals(obj0302.getActionType()))
	{
		obj0302.setErrorMsg("廠商回覆完成");
		obj0302.setQueryAllFlag("false");
	}else{
		obj0302.setErrorMsg("暫存完成");
		obj0302.setState("queryOne");
		obj0302.setQueryAllFlag("true");
	}
}
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
<script type="text/javascript" src="./../../js/jquery.js"></script>
<script type="text/javascript">
var popWinName;
function checkField()
{
	var alertStr = "";
	if(alertStr.length!=0){ alert(alertStr); return false;}

	beforeSubmit();
	form1.submit();
	return true;
}

function init() 
{	
	setDisplayItem('spanInsert,spanQueryAll,spanDelete,spanListPrint,spanListHidden,spanUpdate,spanClear','H');

	whatButtonFireEvent("update");
	document.all.item("confirm").value = "暫　存";
	
	
	if(isObj(document.getElementById("t1"))){
		document.getElementById("t1").style.display = 'none';
		document.getElementById("aTab1").style.display = 'none';
	}
	if(isObj(document.getElementById("t3"))){
		document.getElementById("t3").style.display = 'none';
		document.getElementById("aTab3").style.display = 'none';
	}
	if(isObj(document.getElementById("t9"))){
		document.getElementById("t9").style.display = 'none';
		document.getElementById("aTab9").style.display = 'none';
	}
	if(isObj(document.getElementById("t4"))){
		document.getElementById("t4").style.display = '';
		document.getElementById("aTab4").style.display = '';
	}
	
	changeTab(2);
	setAllReadonly();
	form1.replyContent.disabled = false;
	form1.replyContent.removeAttribute('readonly',0);
	
	if("updateSuccess" == "<%=obj0302.getState()%>")
	{
		form1.action = "medex0302f.jsp";
		if("1" == form1.actionType.value)
		{
			form1.action = "medex0301f.jsp";
		}
		form1.state.value = "init";
		form1.submit();
	}
}

function toQuery()
{
	form1.action = "medex0301f.jsp";
	form1.state.value = "init";
	form1.submit();
}

function onClickButton(type)
{
	form1.actionType.value = type;
	form1.state.value = "update";
	form1.userID.value = "<%=User.getUserId()%>";
	<%System.out.println("廠商id: " + User.getUserId());%>
	setBeforePageUnload(false);
	beforeSubmit();
	form1.submit();
}
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj0302.getState()%>');init();showErrorMsg('<%=obj0302.getErrorMsg()%>');">
<form id="form1" name="form1" method="post"  autocomplete="off">
<!--Query區============================================================-->
<div id="queryContainer" style="width:700px;height:450px;display:none">
	<iframe id="queryContainerFrame"></iframe>
</div>
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr>
    <td nowrap class="bgToolbar">
	<input type="hidden" name="state" value="<%=obj0302.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj0302.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=obj0302.getUserID()%>">
	<input type="hidden" name="id" value="<%=obj0302.getId()%>">
	<input type="hidden" name="actionType" value="<%=obj0302.getActionType()%>">	
    <jsp:include page="../../home/toolbar.jsp">
	   <jsp:param value="N" name="btnCopy"/>
    </jsp:include>
	<span id="spanMaintain1">
		<input class="toolbar_default" type="button" followPK="false" name="maintain1" value="回 覆 完 成" onClick="onClickButton(1)">&nbsp;
	</span>
	<span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="toQueryAll" value="返 回 查 詢" onClick="toQuery();">&nbsp;
	</span>
</tr>

<tr>
<td nowrap>
  <% request.setAttribute("QueryBean",obj0302);%>
  <jsp:include page="../../home/page_row.jsp" />
</td>
</tr>
</table>
<TABLE CELLPADDING=0 CELLSPACING=0>
	<TR>
	<td nowrap ID="t1" CLASS="tab_border1"><a id="aTab1" href="#" onClick="changeTab(1);">基本資料</a></td>
	<td nowrap ID="t2" CLASS="tab_border2"><a id="aTab2" href="#" onClick="changeTab(2);">不良事件</a></td>		
	<td nowrap ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">不良反應</a></td>
	<td nowrap ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">不良品</a></td>
	<td nowrap ID="t9" CLASS="tab_border2"><a id="aTab9" href="#" onClick="changeTab(9);">相關附件</a></td>
	<td nowrap ID="t10" CLASS="tab_border2"><a id="aTab10" href="#" onClick="changeTab(10);">廠商回覆</a></td>
	</TR>
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
    <td nowrap class="bg">	
    <div id="formContainer" style="height:auto">
      <jsp:include page="../medin/medin0101js.jsp" />
	  <jsp:include page="../medin/medin0101f.jsp" />
	  <jsp:include page="medex0303f.jsp" />
    </div>
</table>
</form>
</body>
</html>

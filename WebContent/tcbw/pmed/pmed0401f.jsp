<!--
程式目的：PSUR登錄作業
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
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="PMED0401Q" />
</jsp:include>
<jsp:useBean id="obj0301" scope="request" class="com.kangdainfo.tcbw.view.pmed.PMED0301F">
	<jsp:setProperty name="obj0301" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
obj0301 = (com.kangdainfo.tcbw.view.pmed.PMED0301F) obj0301.queryOne();
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
	var id2 = form1.id2.value;
	var alertStr = "";
	if(alertStr.length!=0){ alert(alertStr); return }
	beforeSubmit();
	form1.submit();	
}


function init() 
{
	setDisplayItem('update,clear,spanInsert,spanQueryAll,spanDelete,spanConfirm,spanListPrint,spanListHidden','H');
	
	var tabId = document.getElementById("tabId").value;
	if(tabId == ""){
		changeTab(1);
	}else{
		changeTab(tabId);
	}
	
	setFormItem("pauseSave,assessComplete,addocuments","R");
}
function queryOne(id2,handstatus)
{
    form1.id2.value = id2;
    form1.doType.value = "update";
    form1.state.value = "queryOne";
    //if("03" == handstatus)
    //{
    	//form1.action = "pmed0201f.jsp";
    //}
    //else
    //{
    //	form1.action = "pmed0301f.jsp";
    //}
    
	beforeSubmit();
	form1.submit();
}
//列印提會單
function printWordReport(type) {	
	if(type != null && type != ''){
		form1.reportType.value=type;
		form1.action="pmed0401p.jsp";
		form1.target="_blank";
		form1.submit();
		form1.target="_self";
		form1.action="pmed0401f.jsp";
	}else{
		alert("無法確認報表種類，請查明!!");
	}
}
</script>
</head>
<body  onLoad="whatButtonFireEvent('<%=obj0301.getState()%>');init();showErrorMsg('<%=obj0301.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<!--Query區============================================================-->
<div id="queryContainer" style="width:700px;height:450px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<jsp:include page="pmed0101js.jsp" />
	
</div>

<table width="100%" cellspacing="0" cellpadding="0">

<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:left">
    <input type="hidden" name="id" value="<%=obj0301.getId()%>">	
    <input type="hidden" name="id2" value="<%=obj0301.getId2()%>">
    <input type="hidden" name="state" value="<%=obj0301.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj0301.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="userName" value="<%=User.getUserName()%>">
	<input type="hidden" name="changeTabValue" value="<%=obj0301.getChangeTabValue()%>">
	<input type="hidden" name="tabId" value="<%=obj0301.getTabId()%>">
	<input type="hidden" name="isNotHand" value="<%=obj0301.getIsNotHand()%>">
	<input type="hidden" name="updateType">
	<input type="hidden" name="reportType" value="<%=obj0301.getReportType()%>">
	

	<jsp:include page="../../home/toolbar.jsp" />
	<input class="toolbar_default" type="button" followPK="false" name="doMailList" value="郵件清單紀錄" onClick="whatButtonFireEvent(this.name)">&nbsp;
    <span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit" value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanPrintWord">
	   <input class="toolbar_default" type="button" followPK="false" name="printWord" value="列印提會單" onClick="printWordReport('01');">&nbsp;
    </span>
    

</td>
</tr>
</table>
<TABLE CELLPADDING=0 CELLSPACING=0>
	<TR>
		<td nowrap ID="t1" CLASS="tab_border1"><a id="aTab1" href="#" onClick="changeTab(1);">監視案件</a></td>
	    <td nowrap ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">定期報告</a></td>
	    <td nowrap ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">定期報告評估</a></td>								
	</TR>
</TABLE>
<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">
<jsp:include page="pmed0102f.jsp" />
<jsp:include page="pmed0202f.jsp" />
<jsp:include page="pmed0302f.jsp" />

</td>
</tr>

</table>
</form>
</body>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName)
	{
	   	case "queryAll":
		    break;
	   	case "doDoQuit":
		   	form1.action="pmed0401q.jsp";
		   	form1.state.value = "queryAll";
		   	form1.submit();
		   	break;
		case "doMailList":
			popCon0002('MED','<%=obj0301.getId2()%>');
			break;
	}
}
</script>
</html>

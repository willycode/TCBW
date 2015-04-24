<!--
程式目的：藥品不良品通報-案件分析
程式代號：drgin0114
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0114F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>

<%
if("".equals(obj.getUserID()))
{
  obj.setUserID(User.getUserId());
}
if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())){
	try
	{
		obj.setHttpRequest(pageContext.getRequest());
		obj.doUpdateDrg0114();
		obj.doDeleteCon0003Db();
		obj.setErrorMsg("修改完成");
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if ("doAnaly".equals(obj.getState())){
	try
	{
		obj.setHttpRequest(pageContext.getRequest());
		obj.doUpdateDrg0114();
		obj.doAnalyDrg0114();
		obj.doDeleteCon0003Db();
		obj.setErrorMsg("分析完成");
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("upload".equals(obj.getState()))
{
	try
	{		
		obj.setHttpRequest(pageContext.getRequest());
		obj.doUpdateDrg0114();
		obj.doDeleteCon0003Db();
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.drgin.DRGIN0114F) obj.doQueryOneDrg0102();
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
<script type="text/javascript" src="drgin0102.js"></script>
<script type="text/javascript">
var popWinName;
function checkField()
{
	var alertStr = "";
	if(form1.state.value=="stayedUpload" || form1.state.value=="doSend"){
		//alertStr += checkDate(form1.occurDate, "發生未預期反應日期");
		<%=TCBWCommon.getCheckFiled("DRG01", "DRGIN0114F")%>
	}
	if(alertStr.length!=0){ alert(alertStr); return }

	beforeSubmit();
	form1.submit();
	return true;
}

function init() 
{
	setDisplayItem('spanInsert,spanQueryAll,spanDelete,spanListPrint,spanListHidden','H');
	setFormItem("doAnaly,doUpload,btnQryExpense,doUpload2","R");
	document.all.item("confirm").value = "暫　存";
	var tabId = form1.tabId.value;
	if(tabId != ""){
		changeTab(tabId);
	}else{
		changeTab(9);
	}
	if('<%=obj.getErrorMsg()%>'=='分析完成' ||  '<%=obj.getErrorMsg()%>'=='案件改派完成')
	{			
		form1.action = "drgin0118f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		window.parent.frames["menu"].location.href="../../home/dTreeMenu.jsp";
	}
}

function queryOne(id,type){
	if(form1.tabId.value=="3"){
		form1.drg03id.value=id;
		form1.state.value="queryOne";	
		beforeSubmit();	
		form1.submit();
	}
	else if (form1.tabId.value=="7"){
		form1.drg07id.value=id;
		form1.state.value="queryOne";	
		beforeSubmit();	
		form1.submit();
	}
	else if(form1.tabId.value=="11" || form1.tabId.value=="12"){
		var isPop = "A";
		if(type == "10")
			isPop = "F";
		else if(type == "11")
			isPop = "C";
		else if(type == "9")
			isPop = "C3";
						
		var jscript = "";	
		var prop = "";
		var windowTop=(document.body.clientHeight-400)/2+100;
		var windowLeft=(document.body.clientWidth-400)/2+100;
		prop=prop+"width=1250,height=720,";
		prop=prop+"top="+windowTop+",";
		prop=prop+"left="+windowLeft+",";
		prop=prop+"scrollbars=yes,resizable=yes";
		closeReturnWindow();
		returnWindow=window.open(getVirtualPath() + "tcbw/drgin/drgin0102q.jsp?isPop="+isPop+"&id="+id,"",prop);
	}

}


function getDochange()
{
    if('<%=obj.competenceDrg0114()%>'!='Y')
    {
       alert("您無改派權限!");
    }
    else
    {    
	  var jscript = "";	
	  var prop = "";
	  var windowTop=(document.body.clientHeight-400)/2+100;
	  var windowLeft=(document.body.clientWidth-400)/2+100;
	  prop=prop+"width=1200,height=520,";
	  prop=prop+"top="+windowTop+",";
	  prop=prop+"left="+windowLeft+",";
	  prop=prop+"scrollbars=yes,resizable=yes";
	  closeReturnWindow();
	  var q="?id="+form1.id.value+"&q_formCode=DRG01&q_code=01";
	  returnWindow=window.open(getVirtualPath() + "tcbw/drgin/drgin0108f.jsp"+q,"",prop);
    }
}

function getUserUpdate(sysTemType,formCode,id)
{
	//var hql = "select  id,chargeMan  from Drg0001Db where id='"+id+"'";
	
	//var x = getRemoteData('../../ajax/jsonObjects.jsp',hql);

	//if(x!=null&&x.length>0)
	//{
	//	var json = JSON.parse(x);
		
	//	if(json.obj1!="" && json.obj1!="<%=User.getUserId()%>")
	//	{
	//		alert("此案件已分派給其他作業人員!");
	//		form1.state.value="init";
	//		setFormItem("doAnaly,clear,confirm,doUpload","R");
	//		setFormItem("update","O");
	//		return false;
	//	}	
		
	//}	
	
	var urlUserUpdate = '../../ajax/jsonCon0003Db.jsp';
	
	var qUserUpdate = "?sysTemType="+sysTemType+"&formCode="+formCode+"&id="+id;
	    qUserUpdate +="&notifier="+form1.notifierName.value;

	var xUserUpdate = getRemoteData(urlUserUpdate+qUserUpdate,"");
	
	if (xUserUpdate!=null && xUserUpdate.length>0) 
	{
		var jsonUserUpdate = eval ('(' + xUserUpdate + ')');
		alert("此案件目前已有其他作業人員編輯中!");
		form1.state.value="init";
		setFormItem("doAnaly,clear,confirm,doUpload,btnQryExpense,doUpload2","R");
		setFormItem("update","O");
		return false;
	}	
}


</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post"  autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr>
    <td nowrap class="bgToolbar">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="status" value="<%=obj.getStatus()%>">
	<input type="hidden" name="updateType">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="caseType" value="<%=obj.getCaseType()%>">
	<input type="hidden" name="drgLevType" value="<%=obj.getDrgLevType()%>">	
	<input type="hidden" name="userID" value="<%=obj.getUserID()%>">
	<input type="hidden" name="id" value="<%=obj.getId()%>">	
	<input type="hidden" name="doType" value="<%=obj.getDoType()%>">
	<input type="hidden" name="tabId" value="<%=obj.getTabId()%>">
    <jsp:include page="../../home/toolbar.jsp">
	   <jsp:param value="N" name="btnCopy"/>
    </jsp:include>
	<span id="spanDoAnaly">
	   <input class="toolbar_default" type="button" followPK="false" name="doAnaly" value="分 析 完 成" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanDoChange">
	   <input class="toolbar_default" type="button" followPK="false" name="doChange" value="案 件 改 派" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit" value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanShow"></span>
    </td>
</tr>

<tr>
<td nowrap>
  <% request.setAttribute("QueryBean",obj);%>
  <jsp:include page="../../home/page_row.jsp" />
</td>
</tr>
</table>

<TABLE CELLPADDING=0 CELLSPACING=0>
	<TR>
		<td nowrap ID="t1" CLASS="tab_border1"><a id="aTab1" href="#" onClick="changeTab(1);">基本資料</a></td>
	    <td nowrap ID="t2" CLASS="tab_border2"><a id="aTab2" href="#" onClick="changeTab(2);">不良事件</a></td>
	    <td nowrap ID="t10" CLASS="tab_border2"><a id="aTab10" href="#" onClick="changeTab(10);">其他附件</a></td>
	    <td nowrap ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">案件分級</a></td>
	    <td nowrap ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">分級確認</a></td>
	    <td nowrap ID="t5" CLASS="tab_border2"><a id="aTab5" href="#" onClick="changeTab(5);">CAPA確認表</a></td>
	    <td nowrap ID="t6" CLASS="tab_border2"><a id="aTab6" href="#" onClick="changeTab(6);">CAPA分析表</a></td>
	    <td nowrap ID="t7" CLASS="tab_border2"><a id="aTab7" href="#" onClick="changeTab(7);">CAPA評估表</a></td>
	</TR>
	<TR>    
	    <td nowrap ID="t8" CLASS="tab_border2"><a id="aTab8" href="#" onClick="changeTab(8);">案件評估</a></td>
	    <td nowrap ID="t9" CLASS="tab_border2"><a id="aTab9" href="#" onClick="changeTab(9);">案件分析</a></td>
	    <td nowrap ID="t11" CLASS="tab_border2"><a id="aTab11" href="#" onClick="changeTab(11);">歷史通報</a></td>
	    <td nowrap ID="t12" CLASS="tab_border2"><a id="aTab12" href="#" onClick="changeTab(12);">歷史CAPA評估</a></td>
	</TR>
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
    <td nowrap class="bg">	
    <div id="formContainer" style="height:auto">
       <jsp:include page="drgin0103f.jsp" />
       <jsp:include page="drgin0107f.jsp" />
       <jsp:include page="drgin0110f.jsp" />
       <jsp:include page="drgin0113f.jsp" />
       <jsp:include page="drgin0115f.jsp" />
       <jsp:include page="drgin0119f.jsp" />
</div>
</table>
</form>

<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName){
	  case "update":
		  setFormItem("doAnaly,doUpload,btnQryExpense","O");
		  getUserUpdate("DRG","DRG01",form1.id.value);
		  break;
	  case "doAnaly":
		  form1.state.value = buttonName;
		  checkField();
		  break;
	  case "doDoQuit":
		  form1.action = "drgin0118f.jsp";
		  form1.state.value = "queryAll";
		  form1.submit();
		  break;
	  case "doChange":
		  getDochange();
		  break;
	}
}
</script>
</body>
</html>

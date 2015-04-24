<!--
程式目的：藥品不良品通報-分級作業
程式代號：drgin0106
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="DRGIN0105" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0106F">
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
		obj.doUpdateDrg0102();
		obj.doUpdateDrg0106();
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
else if("backPieces".equals(obj.getState()))
{
	try
	{
		obj.setHttpRequest(pageContext.getRequest());
		obj.backPiecesDrg0106();
		obj.doDeleteCon0003Db();
		obj.setErrorMsg("退件補件完成");
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("backForMail".equals(obj.getState()))
{
	try
	{
		obj.setHttpRequest(pageContext.getRequest());
		obj.backForMailDrg0106();
		obj.doDeleteCon0003Db();
		obj.setErrorMsg("通知補件完成");
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if ("doAccess".equals(obj.getState())){
	try
	{
		obj.setHttpRequest(pageContext.getRequest());
		obj.doUpdateDrg0102();
		obj.doUpdateDrg0106();
		obj.doAccessDrg0106();
		obj.doDeleteCon0003Db();
		obj.setErrorMsg("初評完成");
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("doPostDoc".equals(obj.getState()))
{
	try
	{
		obj.setHttpRequest(pageContext.getRequest());
		obj.doUpdateDrg0106();
		obj.doPostDocDrg0106();
		obj.doDeleteCon0003Db();
		obj.setErrorMsg("發文完成");
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
		obj.doUpdateDrg0102();
		obj.doUpdateDrg0106();
		obj.doDeleteCon0003Db();
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.drgin.DRGIN0106F) obj.doQueryOneDrg0102();
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
	if(form1.state.value=="doAccess"){
		//alertStr += checkDate(form1.occurDate, "發生未預期反應日期");
		<%=TCBWCommon.getCheckFiled("DRG01", "DRGIN0106F")%>
	}
	if(alertStr.length!=0){ alert(alertStr); return }

	beforeSubmit();
	form1.submit();
	return true;
}

function init() 
{
	setDisplayItem("spanInsert,spanQueryAll,spanDelete,spanListPrint,spanListHidden","H");
    
	setFormItem("doUpdateTab3,backPieces,backForMail,doAccess,doPostDoc,doUpload,btnQryExpense","R");
	
	document.all.item("confirm").value = "暫　存";
	<%=TCBWCommon.getIsComplete("DRG01")%>
	<%out.write(TCBWCommon.getIsMlmsField("DRG01",null));%>
	var tabId = form1.tabId.value;
	if(tabId != ""){
		changeTab(tabId);
	}else{
		changeTab(1);
	}
	if('<%=obj.getErrorMsg()%>'=='通知補件完成' || '<%=obj.getErrorMsg()%>'=='退件補件完成' 
		|| '<%=obj.getErrorMsg()%>'=='初評完成' || '<%=obj.getErrorMsg()%>'=='發文完成')
	{			
		form1.action = "drgin0105f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		window.parent.frames["menu"].location.href="../../home/dTreeMenu.jsp";
	}
	changeTab(3);
}

function queryOne(id,type){
	if(form1.tabId.value=="11" || form1.tabId.value=="12"){
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
	}else{	
		form1.drg03id.value=id;	
		form1.state.value="queryOne";	
		beforeSubmit();	
		form1.submit();
	}
}

function getEmail(mailID,isJS,isAdd)
{
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=1200,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "tcbw/conbg/conbg0001f.jsp?isAdd="+isAdd+"&applNo="+form1.applNo.value+"&isJS="+isJS+"&mailID="+mailID+"&id="+form1.id.value,"",prop);
}

function getDoPostDoc(isJS)
{    
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=600,height=300,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "tcbw/drgin/drgin0111f.jsp?applNo="+form1.applNo.value+"&isJS="+isJS,"",prop);
}

function backPieces()
{
   form1.state.value="backPieces";
   form1.submit();
   return true;
}

function backForMail()
{
   form1.state.value="backForMail";
   form1.submit();
   return true;
}

function doPostDoc()
{
   form1.state.value="doPostDoc";
   form1.submit();
   return true;
}

function getDochange()
{
    if('<%=obj.competenceDrg0106()%>'!='Y')
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
	var urlUserUpdate = '../../ajax/jsonCon0003Db.jsp';
	
	var qUserUpdate = "?sysTemType="+sysTemType+"&formCode="+formCode+"&id="+id;
	    qUserUpdate +="&notifier="+form1.notifierName.value;

	var xUserUpdate = getRemoteData(urlUserUpdate+qUserUpdate,"");
	
	if (xUserUpdate!=null && xUserUpdate.length>0) 
	{
		var jsonUserUpdate = eval ('(' + xUserUpdate + ')');
		alert("此案件目前已有其他作業人員編輯中!");
		form1.state.value="init";
		setFormItem("doUpdateTab3,backPieces,backForMail,doAccess,doPostDoc,clear,confirm,doUpload,btnQryExpense","R");
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
	<input type="hidden" name="isComplete" value="<%=obj.getIsComplete()%>">
	<input type="hidden" name="userID" value="<%=obj.getUserID()%>">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="doType" value="<%=obj.getDoType()%>">
	<input type="hidden" name="tabId" value="<%=obj.getTabId()%>">
    <jsp:include page="../../home/toolbar.jsp">
	   <jsp:param value="N" name="btnCopy"/>
    </jsp:include>
    <%if(!"22".equals(Common.get(obj.getStatus())) && !"25".equals(Common.get(obj.getStatus()))){ %>  
    <span id="spanBackPieces">
	   <input class="toolbar_default" type="button" followPK="false" name="backPieces" value="退 件 補 件" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <%}%>
    <%if(!"23".equals(Common.get(obj.getStatus())) && !"25".equals(Common.get(obj.getStatus()))){ %>
    <span id="spanBackForMail">
	   <input class="toolbar_default" type="button" followPK="false" name="backForMail" value="通 知 補 件" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <%}%>
    <%if(!"25".equals(Common.get(obj.getStatus()))){ %>
    <span id="spanDoAccess">
	   <input class="toolbar_default" type="button" followPK="false" name="doAccess" value="初 評 完 成" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <%}%>
    <span id="spanDoChange">
	   <input class="toolbar_default" type="button" followPK="false" name="doChange" value="案 件 改 派" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
    <span id="spanDoGetMLMS">
		<input class="toolbar_default" type="button" followPK="false" name="doGetMLMS" value="檢 視 藥 證 資 料" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<%if("25".equals(Common.get(obj.getStatus()))){ %>
	<span id="spanDoPostDoc">
		<input class="toolbar_default" type="button" followPK="false" name="doPostDoc" value="發 文 完 成" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<%}%>	
    <span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit" value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	</td>
</tr>
<tr>
	<td nowrap class="bgToolbar">
	<span id="spanShow">
	    <input class="toolbar_default" type="button" followPK="false" name="doOriginalVer" value="案件原始版本" onClick="whatButtonFireEvent(this.name)">&nbsp;
		<input class="toolbar_default" type="button" followPK="false" name="doAddDoc" value="案件補件紀錄" onClick="whatButtonFireEvent(this.name)">&nbsp;
	    <input class="toolbar_default" type="button" followPK="false" name="doMailList" value="郵件清單紀錄" onClick="whatButtonFireEvent(this.name)">&nbsp;
	    <input class="toolbar_default" type="button" followPK="false" name="doFlow" value="案件流程紀錄" onClick="whatButtonFireEvent(this.name)">
	</span>
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
       <jsp:include page="drgin0119f.jsp" />
</div>
</table>
</form>

<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName){
	  case "update":
		  setFormItem("btnQryExpense,doUpdateTab3,backPieces,backForMail,doAccess,doPostDoc,doUpload,btnQryExpense","O");
		  getUserUpdate("DRG","DRG01",form1.id.value);
		  break;
	  case "backPieces":
		  getEmail('DRG010003','window.opener.backPieces','N');
		  break;	  
	  case "backForMail":
		  getEmail('DRG010004','window.opener.backForMail','Y');
		  break;
	  case "doChange":
		  getDochange();
		  break;
	  case "doAccess":
		  form1.state.value = buttonName;
		  checkField();
		  break;
	  case "doPostDoc":
		  getDoPostDoc('window.opener.doPostDoc');
		  break;
	  case "doDoQuit":
		  form1.action = "drgin0105f.jsp";
		  form1.state.value = "queryAll";
		  form1.submit();
		  break;
	  case "doGetMLMS":
		  toShowMLMS();
		  break;
	  case "doAddDoc":
		  popCon0004('<%=obj.getApplNo()%>','DRG');
		  break;
	  case "doMailList":
		  popCon0002('DRG','<%=obj.getId()%>');
		  break;
	  case "doFlow":
		  popCon2001('DRG1','<%=obj.getId()%>');
		  break;
	  case "doOriginalVer":
		  popDrg5001('<%=obj.getId()%>');
		  break;
	}
}
</script>
</body>
</html>

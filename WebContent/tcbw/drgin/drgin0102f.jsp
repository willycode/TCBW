<!--
程式目的：藥品不良品通報-審核作業
程式代號：drgin0102
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="DRGIN0101" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0102F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
String isComplete = Common.get(request.getParameter("isComplete"));
obj.setIsComplete(isComplete);

if("".equals(obj.getUserID()))
{
  obj.setUserID(User.getUserId());
}

if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())){
	try
	{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doUpdateDrg0102();
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
		obj.doBackPiecesDrg0102();
		obj.doDeleteCon0003Db();
		obj.setErrorMsg("退件完成");
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("dismissal".equals(obj.getState()))
{
	try
	{
		obj.setHttpRequest(pageContext.getRequest());
		obj.doDismissalDrg0102();
		obj.doDeleteCon0003Db();
		obj.setErrorMsg("撤案完成");
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if("accepted".equals(obj.getState()))
{
	try
	{
		obj.setHttpRequest(pageContext.getRequest());
		obj.doUpdateDrg0102();
		obj.doAcceptedDrg0102();
		obj.doDeleteCon0003Db();
		obj.setErrorMsg("收案完成，案件編號為："+obj.getApplNo());
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
		obj.doDeleteCon0003Db();
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.drgin.DRGIN0102F)obj.doQueryOneDrg0102();
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
<script type="text/javascript" src="drgin0102.js"></script>
<script type="text/javascript">
var popWinName;
function checkField()
{
	var alertStr = "";
	if(form1.state.value=="stayedUpload" || form1.state.value=="doSend"){
		alertStr += checkDate(form1.occurDate, "發生未預期反應日期");		
	}
	if(alertStr.length!=0){ alert(alertStr); return }

	beforeSubmit();
	form1.submit();
	return true;
}


function init() 
{	
	<%=TCBWCommon.getIsComplete("DRG01")%>
	<%out.write(TCBWCommon.getIsMlmsField("DRG01",null));%>
	setDisplayItem('spanInsert,spanQueryAll,spanDelete,spanListPrint,spanListHidden','H');

	setFormItem("accepted,backPieces,dismissal,doUpload,btnQryExpense","R");

	document.all.item("confirm").value = "暫　存";

	var tabId = form1.tabId.value;
	if(tabId != ""){
		changeTab(tabId);
	}else{
		changeTab(1);
	}

	if(<%=Common.get(obj.getErrorMsg()).indexOf("收案完成")!=-1%> || '<%=obj.getErrorMsg()%>'=='退件完成' 
		|| '<%=obj.getErrorMsg()%>'=='撤案完成' || '<%=obj.getErrorMsg()%>'=='案件改派完成' )
	{			
		form1.action = "drgin0101f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		window.parent.frames["menu"].location.href="../../home/dTreeMenu.jsp";
	}
}

function checkURL(surl)
{
	var alertStr = "";	
	//alertStr += checkEmpty(form1.applyNo,"主檔編號");
	if(alertStr.length != 0){
		alert("請先執行查詢, 若已查到資料則請選取其中一筆資料");
		return false;
	} else {
		form1.state.value="";
	}
	form1.action = surl;
	beforeSubmit();
	form1.submit();
}

function getEmail(mailID,isJS)
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
	returnWindow=window.open(getVirtualPath() + "tcbw/conbg/conbg0001f.jsp?isAdd=N&applNo="+form1.applNo.value+"&isJS="+isJS+"&mailID="+mailID+"&id="+form1.id.value,"",prop);
}

function backPieces()
{
   form1.state.value="backPieces";
   form1.submit();
   return true;
}

function dismissal()
{
   form1.state.value="dismissal";
   form1.submit();
   return true;
}

function getDochange()
{
    if('<%=obj.competenceDrg0102()%>'!='Y')
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
		setFormItem("accepted,backPieces,dismissal,clear,confirm,doUpload,btnQryExpense","R");
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
	<input type="hidden" name="applNo" value="<%=obj.getApplNo()%>">
	<input type="hidden" name="updateType">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="isComplete" value="<%=obj.getIsComplete()%>">
	<input type="hidden" name="userID" value="<%=obj.getUserID()%>">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="doType" value="<%=obj.getDoType()%>">
	<input type="hidden" name="tabId" value="<%=obj.getTabId()%>">
    <jsp:include page="../../home/toolbar.jsp">
	   <jsp:param value="N" name="btnCopy"/>
    </jsp:include>
    <span id="spanAccepted">
	   <input class="toolbar_default" type="button" followPK="false" name="accepted" value="收　案" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <span id="spanBackPieces">
	   <input class="toolbar_default" type="button" followPK="false" name="backPieces" value="退　件" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <span id="spanDismissal">
       <input class="toolbar_default" type="button" followPK="false"  name="dismissal" value="撤　案" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
	<span id="spanDoChange">
	   <input class="toolbar_default" type="button" followPK="false" name="doChange" value="案 件 改 派" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
    <span id="spanDoGetMLMS">
		<input class="toolbar_default" type="button" followPK="false" name="doGetMLMS" value="檢 視 藥 證 資 料" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
    <span id="spanDoOriginalVer">
       <input class="toolbar_default" type="button" followPK="false" name="doOriginalVer" value="案件原始版本" onClick="whatButtonFireEvent(this.name)">&nbsp;
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
	</TR>
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
    <td nowrap class="bg">	
    <div id="formContainer" style="height:auto">
        <jsp:include page="drgin0103f.jsp" />
    </div>
    </td>
</tr>
</table>
</form>

<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName){
	  case "update":
		  setFormItem("accepted,backPieces,dismissal,doUpload,btnQryExpense","O");
		  getUserUpdate("DRG","DRG01",form1.id.value);
		  break;
	  case "accepted":
		  form1.state.value = buttonName;
		  checkField();
		  break;
	  case "dismissal":
		  getEmail('DRG010002','window.opener.dismissal');
		  break;
	  case "backPieces":
		  getEmail('DRG010003','window.opener.backPieces');
		  break;
	  case "doChange":
		  getDochange();
		  break;
	  case "doDoQuit":
		  form1.action = "drgin0101f.jsp";
		  form1.state.value = "queryAll";
		  form1.submit();
		  break;
	  case "doGetMLMS":
		  toShowMLMS();
		  break;
	  case "doOriginalVer":
		  popDrg5001('<%=obj.getId()%>');
		  break;
	}
}
</script>
</body>
</html>

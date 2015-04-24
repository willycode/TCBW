<!--
程式目的：藥品不良品通報-案件評估
程式代號：drgin0112
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="DRGIN0117" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0112F">
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
		obj.doUpdateDrg0112();
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
else if ("doPostNo".equals(obj.getState())){
	try
	{
		obj.setHttpRequest(pageContext.getRequest());
		obj.doUpdateDrg0112();
		obj.doPostNoDrg0112();
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
else if ("doDelay".equals(obj.getState())){
	try
	{
		obj.setHttpRequest(pageContext.getRequest());
		obj.doUpdateDrg0112();
		obj.doDeleteCon0003Db();
		obj.setErrorMsg("展延完成");
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if ("doAssess".equals(obj.getState())){
	try
	{
		obj.setHttpRequest(pageContext.getRequest());
		obj.doUpdateDrg0112();
		obj.doAssessDrg0112();
		obj.doDeleteCon0003Db();
		obj.setErrorMsg("評估完成");
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if ("doBack".equals(obj.getState())){
	try
	{
		obj.setHttpRequest(pageContext.getRequest());
		obj.doBackDrg0112();
		obj.doDeleteCon0003Db();
		obj.setErrorMsg("廠商補件通知完成");
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if ("doCorrection".equals(obj.getState())){
	try
	{
		obj.setHttpRequest(pageContext.getRequest());
		obj.doCorrectionDrg0112();
		obj.doDeleteCon0003Db();
		obj.setErrorMsg("重新校正完成");
		obj.setIsNeedBackQuery("Y");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if ("doReAssess".equals(obj.getState())){
	try
	{
		obj.setHttpRequest(pageContext.getRequest());
		obj.doUpdateDrg0112();
		obj.doReAssessDrg0112();
		obj.doDeleteCon0003Db();
		obj.setErrorMsg("重新評估完成");
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
		obj.doUpdateDrg0112();
		obj.doDeleteCon0003Db();
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.drgin.DRGIN0112F) obj.doQueryOneDrg0102();
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
	if(form1.state.value == "doPostNo"){	
		alertStr += checkRadioButton(form1.isPostYn08,"是否發文");	
		alertStr += checkDate(form1.payDate08,"展延日期");	
		alertStr += checkEmpty(form1.payDate08,"廠商交付CAPA日期");	
		alertStr += checkRadioOtherEmpty(form1.isPostYn08,'N',form1.reason08,'不發文理由');	
		alertStr += checkRadioOtherEmpty(form1.isPostYn08,'Y',form1.fdaPostNo08,'FDA發文字號');	
	}
	else if(form1.state.value == "doDelay"){
	    alertStr += checkDate(form1.delayDate08,"展延日期");
	    alertStr += checkEmpty(form1.delayDate08,"展延日期");	
	}
	else if(form1.state.value == "doAssess" || form1.state.value == "doReAssess"){
	    alertStr += checkRadioButton(form1.drgLev08,"風險等級");
	    alertStr += checkEmpty(form1.capaDownDate08,"廠商交付CAPA日期");
	    alertStr += checkEmpty(form1.drgReason08,"本案原由");
	    alertStr += checkEmpty(form1.checkResult08,"清查結果");	
        alertStr += checkEmpty(form1.survey08,"調查報告");
        alertStr += checkEmpty(form1.precaution08,"預防矯正措施及改善時程");	
        alertStr += checkEmpty(form1.assessResult08,"評估結果");	
        alertStr += checkRadioButton(form1.dealWith08,"擬辦事項");	
    }
    else if(form1.state.value == "doCorrection"){
    	alertStr += checkEmpty(form1.correctionReason,"重新校正理由");
    }
   	if(isObj(form1.drgReason08))	alertStr += checkLen(form1.drgReason08,"本案原由",500);
   	if(isObj(form1.checkResult08))	alertStr += checkLen(form1.checkResult08,"清查結果",500);
   	if(isObj(form1.survey08))		alertStr += checkLen(form1.survey08,"調查報告",500);
   	if(isObj(form1.precaution08))	alertStr += checkLen(form1.precaution08,"預防矯正措施及改善時程",500);
   	if(isObj(form1.assessResult08))	alertStr += checkLen(form1.assessResult08,"評估結果",500);
	if(alertStr.length!=0){ alert(alertStr); return false; }

	beforeSubmit();
	form1.submit();
	return true;
}

function init() 
{
	setDisplayItem('spanInsert,spanQueryAll,spanDelete,spanListPrint,spanListHidden','H');
	setFormItem("doPostNo,doDelay,doAssess,doBack,doUpload,btnQryExpense,doUpload2","R");
	    
	document.all.item("confirm").value = "暫　存";

	var tabId = form1.tabId.value;
	if(tabId != ""){
		changeTab(tabId);
	}else{
		changeTab(8);
	}
	if('<%=obj.getErrorMsg()%>'=='發文完成' || '<%=obj.getErrorMsg()%>'=='展延完成' 
		|| '<%=obj.getErrorMsg()%>'=='案件改派完成' || '<%=obj.getErrorMsg()%>'=='評估完成'
			|| '<%=obj.getErrorMsg()%>'=='廠商補件通知完成' || '<%=obj.getErrorMsg()%>'=='重新校正完成'
				|| '<%=obj.getErrorMsg()%>'=='重新評估完成')
	{			
		form1.action = "drgin0117f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		window.parent.frames["menu"].location.href="../../home/dTreeMenu.jsp";
	}
}

function queryOne(id){
	if(form1.tabId.value=="3") form1.drg03id.value=id;
	else if (form1.tabId.value=="7") form1.drg07id.value=id;
	form1.state.value="queryOne";
	beforeSubmit();
	form1.submit();
}


function getDochange()
{
    if('<%=obj.competenceDrg0112()%>'!='Y')
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

function doBack()
{
   form1.state.value="doBack";
   form1.submit();
   return true;
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
	//		setFormItem("doPostNo,doDelay,doAssess,doBack,clear,confirm,doUpload","R");
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
		setFormItem("doPostNo,doDelay,doAssess,doBack,clear,confirm,doUpload,btnQryExpense,doUpload2","R");
		setFormItem("update","O");
		return false;
	}	
}

</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post"  autocomplete="off">
<!--重新校正區============================================================-->
<div id="queryContainer" style="width:400px;height:150px;display:none">
    <iframe id="queryContainerFrame"></iframe>
    <table class="queryTable"  border="1">
        <tr>
			<td nowrap class="td_form_left" colspan="4">展延資訊單</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >重新校正理由</td>
			<td nowrap class="td_form_white" colspan="3">
			   <input class="field_Q" type="text" name="correctionReason" size="50" maxlength="100" value="<%=obj.getCorrectionReason()%>">               
			</td>
		</tr>
		<tr>
			<td nowrap class="queryTDInput" colspan="4" style="text-align:center;">
				<input class="toolbar_default" followPK="false" type="button" name="doCorrection" value="確　　定" onClick="whatButtonFireEvent(this.name)">
				<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="whatButtonFireEvent(this.name)">
			</td>			
		</tr>
     </table>
</div>
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
	<input type="hidden" name="tabId" value="<%=obj.getTabId()%>">
    <jsp:include page="../../home/toolbar.jsp">
	   <jsp:param value="N" name="btnCopy"/>
    </jsp:include>
    <%if("40".equals(Common.get(obj.getStatus()))){ %>
    <span id="spanDoPostNo">
	   <input class="toolbar_default" type="button" followPK="false" name="doPostNo" value="發　文" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<%}%>
	<%if("41".equals(Common.get(obj.getStatus()))){ %>
	<span id="spanDoDelay">
	   <input class="toolbar_default" type="button" followPK="false" name="doDelay" value="展　延" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<%}%>
	<%if("41,42".indexOf(Common.get(obj.getStatus())) != -1){ %>
	<span id="spanDoAssess">
	   <input class="toolbar_default" type="button" followPK="false" name="doAssess" value="評 估 完 成" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<%}%>
	<%if("42".equals(Common.get(obj.getStatus()))){ %>
    <span id="spanDoback">
	   <input class="toolbar_default" type="button" followPK="false" name="doBack" value="廠 商 補 件" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<%}%>
	<%if("40,41,42".indexOf(Common.get(obj.getStatus())) != -1){ %>
	<span id="spanShowCorrection">
	   <input class="toolbar_default" type="button" followPK="false" name="showCorrection" value="重 新 校 正" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<%}%>
	<%if("43".equals(Common.get(obj.getStatus()))){ %>
    <span id="spanDoReAssess">
	   <input class="toolbar_default" type="button" followPK="false" name="doReAssess" value="再 評 估 完 成" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<%}%>
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
	<tr>
		<td nowrap ID="t1" CLASS="tab_border1"><a id="aTab1" href="#" onClick="changeTab(1);">基本資料</a></td>
	    <td nowrap ID="t2" CLASS="tab_border2"><a id="aTab2" href="#" onClick="changeTab(2);">不良事件</a></td>
	    <td nowrap ID="t10" CLASS="tab_border2"><a id="aTab10" href="#" onClick="changeTab(10);">其他附件</a></td>
	    <td nowrap ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">案件分級</a></td>	    
	    <td nowrap ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">分級確認</a></td>
	    <td nowrap ID="t5" CLASS="tab_border2"><a id="aTab5" href="#" onClick="changeTab(5);">CAPA確認表</a></td>
	    <td nowrap ID="t6" CLASS="tab_border2"><a id="aTab6" href="#" onClick="changeTab(6);">CAPA分析表</a></td>
	    <td nowrap ID="t7" CLASS="tab_border2"><a id="aTab7" href="#" onClick="changeTab(7);">CAPA評估表</a></td>
	    <td nowrap ID="t8" CLASS="tab_border2"><a id="aTab8" href="#" onClick="changeTab(8);">案件評估</a></td>
	</tr>
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
</div>
</table>
</form>

<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName){
	  case "update":		  
		  setFormItem("doPostNo,doDelay,doAssess,doBack,doUpload,btnQryExpense,doUpload2","O");
		  getUserUpdate("DRG","DRG01",form1.id.value);
		  break;
	  case "doPostNo":
		  form1.state.value = buttonName;
		  checkField();
		  break;
	  case "doDelay":
		  form1.state.value = buttonName;
		  checkField();
		  break;
	  case "doAssess":
		  form1.state.value = buttonName;
		  checkField();
		  break;
	  case "doBack":
		  getEmail('DRG010009','window.opener.doBack','N');
		  break;
	  case "doDoQuit":
		  form1.action = "drgin0117f.jsp";
		  form1.state.value = "queryAll";
		  form1.submit();
		  break;
	  case "doChange":
		  getDochange();
		  break;
	  case "showCorrection":
		  queryShow('queryContainer');
		  break;
	  case "doCorrection":
		  form1.state.value = buttonName;
		  checkField();
		  break;
	  case "doReAssess":
		  form1.state.value = buttonName;
		  checkField();
		  break;  
	}
}
</script>
</body>
</html>

<!--
程式目的：國內外藥品品質警訊登錄作業
程式代號：vdrg0100f
程式日期：1030424
程式作者：yuwen
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp"/>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.vdrg.VDRG0100F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList72" scope="page" class="java.util.ArrayList"/>

<%

String okMsg = "";

if ("insert".equals(obj.getState()) || "insertError".equals(obj.getState()))
{
	obj.setHttpRequest(pageContext.getRequest());
	obj.doInsert();
}
else if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())) 
{
	obj.setHttpRequest(pageContext.getRequest());
	obj.update();
	if ("updateSuccess".equals(obj.getState()))
	{
		if("maintain0".equals(obj.getActionType())){
			okMsg = "暫存完成";
		}else if("maintain1".equals(obj.getActionType())){
			okMsg = "送件完成";
		}else if("maintain3".equals(obj.getActionType())){
			okMsg = "廠商回覆完成";
		}else if("maintain4".equals(obj.getActionType())){
			okMsg = "評估完成";
		}
	}
}else if ("delete".equals(obj.getState()) || "deleteError".equals(obj.getState())) {
	obj.delete();
	obj.setQueryAllFlag("false");
}else if ("upload".equals(obj.getState())) {
	obj.setHttpRequest(pageContext.getRequest());
	obj.update();
}else{
	obj.setQueryAllFlag("true");
}


if(null != okMsg && !"".equals(okMsg))
{
	obj.setErrorMsg(okMsg);
}
else if (!"deleteSuccess".equals(obj.getState()))
{
	obj.setState("queryOne");
	obj.setQueryAllFlag("true");
}
if ("true".equals(obj.getQueryAllFlag()))
{
	obj = (com.kangdainfo.tcbw.view.vdrg.VDRG0100F)obj.queryOne();	
	
	if(null != obj)
	{
		objList72 = obj.doQueryAllDrg71(obj.getId());
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
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/jquery.maskedinput.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>

<script type="text/javascript">
var insertDefault = new Array
(
		//由後端塞入
		//new Array("status", "00"),
		//new Array("dataRevDate", "<%=Datetime.getYYYMMDD()%>"),
		//new Array("recycleNum", "1"),
		//new Array("warningremark", "<%=View.getCommonCodeName("DRGWAREMARK","05")%>")
);

function checkField()
{
	var alertStr="";
	if(form1.state.value=="queryAll"){
		alertStr += checkQuery();
	}else if(form1.state.value=="insert"||form1.state.value=="insertError"||form1.state.value=="update"||form1.state.value=="updateError"){
		
		if("0201" == form1.formType.value){
			<%=TCBWCommon.getCheckFiled("DRG03","VDRG0201")%>
		}else if("0401" == form1.formType.value){
			<%=TCBWCommon.getCheckFiled("DRG03","VDRG0401")%>
		}
	}
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	form1.submit();
	return true;
}
function queryOne(id , queryType){
	setObjectValue(queryType+"Id",id);
	form1.state.value="queryOne";
	beforeSubmit();
	form1.submit();
	if(!""==queryType && null != queryType){
		document.all.item("listContainerActiveRowId").value = id+queryType;
		document.all.item("listContainerRow"+id+queryType).className = "activeRow";
		document.all.item("listContainerRow"+id+queryType).onmouseover="activeRow";
		document.all.item("listContainerRow"+id+queryType).onmouseout="activeRow";
	}
}
function beforeInit() {
	<%=obj.getDrg73JSBuilder()%>
	<%=obj.getConJSBuilder()%>
}

function init()
{	
	if(null != form1.tabId.value && "" != form1.tabId.value)
	{
		changeTab(form1.tabId.value);
	}
	else
	{
		changeTab(1);
	}
	setDisplayItem('spanInsert,spanQueryAll,spanDelete,spanListPrint,spanListHidden,spanConfirm','H');
	setUploadButtonFormItem("R");

	if("deleteSuccess" == form1.state.value || "maintain1" == "<%=obj.getActionType()%>" || "maintain3" == "<%=obj.getActionType()%>" || "maintain4" == "<%=obj.getActionType()%>"){
		var action = "vdrg"+form1.formType.value+"f.jsp";
		form1.action = action;
		form1.state.value = "queryAll";
		form1.submit();
		window.parent.frames["menu"].location.href="../../home/dTreeMenu.jsp";
	}
	
	if("0101" == form1.formType.value)
	{
		setDisplayItem('spanMaintain3,spanMaintain4','H');
		if("deleteSuccess" != form1.state.value)
		{
			if("Y" == form1.formInsert.value)
			{	
			   whatButtonFireEvent("update");
			   setFormItem("maintain1","O");
			}
		}
	}
	else if("0201" == form1.formType.value)
	{
		setDisplayItem('spanMaintain1,spanMaintain4,spanMaintain5','H');
	}
	else if("0401" == form1.formType.value)
	{
		setDisplayItem('spanMaintain1,spanMaintain3,spanMaintain5','H');
	}
	else if("0501" == form1.formType.value)
	{
		setDisplayItem('spanUpdate,spanClear,spanMaintain0,spanMaintain1,spanMaintain3,spanMaintain4,spanMaintain5','H');
	}
	else if("sdrg0202" == form1.formType.value)
	{
		setDisplayItem('spanMaintain2,spanPrint1,spanUpdate,spanClear,spanMaintain0,spanMaintain1,spanMaintain3,spanMaintain4,spanMaintain5','H');
	}
	
	changeIstransferDept();	
	changeIsImport();
	changeIspublconsumer();
	changeAftereffectOtherField();
	changeNonImportReasonDescField();
	
	if("in" != "<%=User.getInORout()%>")
	{
		document.getElementById("outHide1").style.display = 'none';
		document.getElementById("outHide2").style.display = 'none';
		document.getElementById("outHide3").style.display = 'none';
		document.getElementById("outHide4").style.display = 'none';
		document.getElementById("outHide4_2").style.display = 'none';
		document.getElementById("outHide5").style.display = 'none';
		document.getElementById("outHide6").style.display = 'none';
		document.getElementById("outHide7").style.display = 'none';
		setDisplayItem('print1','H');
	}
	
	if("Y"==form1.toUpdate.value){
		whatButtonFireEvent("update");
	}
	form1.toUpdate.value="";
}
function changeIstransferDept(){
	var istransfer = getRadioChecked1("istransfer");

	document.all.item("transferdept").className = "field";
	document.all.item("transferdeptCodeId").className = "field";
	document.all.item("btn_transferdept").className = "field";
	document.all.item("transferdept").disabled = false;
	document.all.item("transferdeptCodeId").disabled = false;
	document.all.item("btn_transferdept").disabled = false;
	document.all.item("transferdeptCodeId").readOnly = false;
	if("Y" != istransfer){
		document.all.item("transferdept").className = "field_RO";
		document.all.item("transferdeptCodeId").className = "field_RO";
		document.all.item("btn_transferdept").className = "field_QRO";
		document.all.item("transferdept").disabled = true;
		document.all.item("transferdeptCodeId").disabled = true;
		document.all.item("btn_transferdept").disabled = true;
		document.all.item("transferdeptCodeId").readOnly = true;

		document.all.item("transferdept").value = "";
		document.all.item("transferdeptCodeId").value = "";
		document.all.item("transferdeptName").value = "";
	}
}


function submitBefore(buttonName,mailID,isJS,isAdd,isApplicationId)
{

	var isSendMail = false;
	var alertStr="";
		
	if(buttonName == "maintain1" && "0101" == form1.formType.value) 
	{ //送件前先檢核必填欄位再發信
		startSave();//先update一次，避免手動增加許可證後，寄送郵件清單沒抓到值
		<%=TCBWCommon.getCheckFiled("DRG03","VDRG0101")%>
	}
	if(alertStr.length!=0) 
	{
		alert(alertStr);
	} 
	else if(null != mailID && "" != mailID)
	{
		isSendMail = true;
		if("DRG030001" == mailID)
		{
			var drg73Row=document.getElementsByName("drg73Row");	
			if (drg73Row==null || drg73Row.length == 0) 
			{
				isSendMail = false;
			}
		}
	}
	if(isSendMail){
		var parm = "isAdd="+isAdd+"&applNo="+form1.applNo.value+"&isJS="+isJS+"&mailID="+mailID+"&id="+form1.id.value+ "&systemType=DRG";
		var prop = "";
		var windowTop=(document.body.clientHeight-400)/2+100;
		var windowLeft=(document.body.clientWidth-400)/2+100;
		prop=prop+"width=1200,height=600,";
		prop=prop+"top="+windowTop+",";
		prop=prop+"left="+windowLeft+",";
		prop=prop+"scrollbars=yes,resizable=yes";
		closeReturnWindow();
		if(isApplicationId){
			window.open(getVirtualPath() + "home/popSendMail.jsp?"+parm,"",prop);
		}else{
			window.open(getVirtualPath() + "tcbw/conbg/conbg0001f.jsp?"+parm+"&isJS=window.opener.checkField","",prop);
		}
	}else{
		if(alertStr.length==0){
			whatButtonFireEvent(buttonName);
		}		
	}
}

function startSave()
{　
	//setTimeout("startSave()", 30000);
	
	setFormItem("pauseSave,stayedUpload,doSend,doDoQuit,doUpload","R");
	
	form1.action = "vdrg0103f.jsp?isSave=Y&id=" + form1.id.value;
	form1.target = "saveContainerFrame";	
	beforeSubmit();
	form1.submit();

	form1.action = "vdrg0100f.jsp";
	form1.target = "_self";
}

function changeTab(tabId) 
{
	form1.tabId.value = tabId;
	document.getElementById("t1").className = "tab_border2";
	if(isObj(document.getElementById("t2"))){
		document.getElementById("t2").className = "tab_border2";
	}
	if(isObj(document.getElementById("t3"))){
		document.getElementById("t3").className = "tab_border2";
	}

	document.getElementById("aTab1").className = "";
	if(isObj(document.getElementById("aTab2"))){
		document.getElementById("aTab2").className = "";
	}
	if(isObj(document.getElementById("aTab3"))){
		document.getElementById("aTab3").className = "";
	}

	document.getElementById("Tab1").style.display = 'none';
	if(isObj(document.getElementById("Tab2"))){
		document.getElementById("Tab2").style.display = 'none';
	}
	if(isObj(document.getElementById("Tab3"))){
		document.getElementById("Tab3").style.display = 'none';
	}

	if(tabId == 2){
		document.getElementById("t2").className = "tab_border1";	
		document.getElementById("Tab2").style.display = '';
		document.getElementById("aTab2").className = "text_w";		
	}else if (tabId == 3){
		document.getElementById("t3").className = "tab_border1";	
		document.getElementById("Tab3").style.display = '';
		document.getElementById("aTab3").className = "text_w";	
	}else{
		document.getElementById("t1").className = "tab_border1";
		document.getElementById("Tab1").style.display = '';
		document.getElementById("aTab1").className = "text_w";	
	}
}

function changeIsImport(){
	var isImport = getRadioChecked1("isImport");

	document.all.item("nonImportReason").className = "field_RO";
	document.all.item("nonImportReasonDesc").className = "field_RO";
	document.all.item("nonImportReason").disabled = true;
	document.all.item("nonImportReasonDesc").readOnly = true;
	document.all.item("eventapplNo").className = "field_RO";
	document.all.item("eventapplNo").readOnly = true;
	document.getElementById("showImpMsg").style.display = 'none';
	if("N" == isImport){
		document.all.item("eventapplNo").value = "";

		document.all.item("nonImportReason").className = "field";
		document.all.item("nonImportReasonDesc").className = "field";
		document.all.item("nonImportReason").disabled = false;
		document.all.item("nonImportReasonDesc").readOnly = false;
		changeNonImportReasonDescField();
	}
	else if("Y" == isImport)
	{
		
		document.getElementById("showImpMsg").style.display = 'block';
		document.all.item("eventapplNo").className = "field";
		document.all.item("eventapplNo").readOnly = false;
		
		document.all.item("nonImportReason").value = "";
		document.all.item("nonImportReasonDesc").value = "";
		changeNonImportReasonDescField();
	}
}

function changeIspublconsumer(){
	var ispublconsumer = getRadioChecked1("ispublconsumer");
	if (isObj(document.all.item("publconsumerDate")) && isObj(document.all.item("btn_publconsumerDate")))
	{
		document.all.item("publconsumerDate").className = "field";
		document.all.item("publconsumerDate").disabled = false;
		document.all.item("btn_publconsumerDate").className = "field";
		document.all.item("btn_publconsumerDate").disabled = false;	
		if("Y" != ispublconsumer){
			document.all.item("publconsumerDate").className = "field_RO";
			document.all.item("btn_publconsumerDate").className = "field_RO";
			document.all.item("publconsumerDate").disabled = true;
			document.all.item("btn_publconsumerDate").disabled = true;
			document.all.item("publconsumerDate").value="";
		}
	}
}

function popReportWarningForm() {
	var q_lampPrint = getRadioChecked1("q_lampPrint");
	if(null != q_lampPrint && "" != q_lampPrint){
		form1.action="vdrg0100p.jsp";
		form1.target="_blank";
		form1.submit();
		form1.target="_self";
		form1.action="vdrg0100f.jsp";
		document.getElementById("queryContainer").style.display='none';
	}else{
		alert("請選擇欲產生的燈號種類!!");
	}
}

function changeAftereffectOtherField(){
	var aftereffect = getObjectValue("aftereffectCodeId");
	if (isObj(document.all.item("aftereffectOther")))
	{
		if(aftereffect == "90"){
			document.all.item("aftereffectOther").className = "field";
			document.all.item("aftereffectOther").readOnly = false;
		}else{
			document.all.item("aftereffectOther").value = "";
			document.all.item("aftereffectOther").className = "field_RO";
			document.all.item("aftereffectOther").readOnly = true;
		}
	}	
}

function changeNonImportReasonDescField(){	
	var nonImportReason = getObjectValue("nonImportReason");
	if (isObj(document.all.item("nonImportReasonDesc")))
	{
		if(nonImportReason == "90"){		
			document.all.item("nonImportReasonDesc").className = "field";
			document.all.item("nonImportReasonDesc").readOnly = false;
		}else{
			document.all.item("nonImportReasonDesc").value = "";
			document.all.item("nonImportReasonDesc").className = "field_RO";
			document.all.item("nonImportReasonDesc").readOnly = true;
		}
	}
}

function setUploadButtonFormItem(strOption){
	var arrObj = document.forms[0].elements;	
	for(var i=0; i<arrObj.length; i++){  			
		var obj = arrObj[i];
		if(obj.type=="button"){	
			if (like(obj.name,"doUploadCon")){
				if (strOption=="O") {
					obj.disabled = false;
				} else {
					obj.disabled = true;
				}
			}
		}
	}
}

function choesePermitKeyNo(queryType)
{
	queryShow('queryContainer1');
	document.all.item('spanMlms').style.display="none";
	if("1" == queryType)
	{
		document.all.item('spanMlms').style.display="block";
	}
}
function toShowMLMS()
{
	if(null != form1.q_permitKeyNo.value && "" != form1.q_permitKeyNo.value){
		var permitKey = form1.q_permitKeyNo.value.substring(0,2);
		var permitNo = form1.q_permitKeyNo.value.substring(2);	
		var params = 'width=980,height=640,resizable=1,menubar=no,scrollbars=yes';
		closeReturnWindow();
		window.open(getVirtualPath() + "tcbw/drgin/drgin0104f.jsp?permitKey="+permitKey+"&permitNo="+permitNo,'popWinE',params);	
		whatButtonFireEvent("queryCannel");
		
	}else{
		alert("請選擇一筆藥證資料!!");
	}
}
</script>
</head>

<body onLoad="beforeInit();whatButtonFireEvent('<%=obj.getState()%>');showErrorMsg('<%=obj.getErrorMsg()%>');init();">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<iframe name="saveContainerFrame" width="0" height="0" frameborder="0">
</iframe>
<!--Query區============================================================-->
<div id="queryContainer" style="width:300px;height:200px;display:none">
	<div class="queryTitle">列印視窗</div>
	<table class="queryTable" border="1">
		<tr>
			<td nowrap class="queryTDLable">燈號：</td>
			<td nowrap class="queryTDInput">
				<%=View.getRadioBoxOption("field_Q","q_lampPrint","select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONLAMP' order by codeId",obj.getQ_lampPrint())%>
			</td>
		</tr>
		<tr>
			<td class="queryTDInput" colspan="2" style="text-align:center;">
				<input class="toolbar_default" followPK="false" type="button" name="querySubmit" value="確　　定" onClick="popReportWarningForm()">
				<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="whatButtonFireEvent(this.name)">
			</td>
		</tr>
		
	</table>
</div>

<div id="queryContainer1" style="width:300px;height:100px;display:none">
	<div class="queryTitle">查詢視窗</div>
	<table class="queryTable" border="1">
	<tr>
		<td class="td_form">許可證字號：</td>
		<td class="td_form_white">
			<select name="q_permitKeyNo" class="field_Q">
				<%=View.getOption("select drg.permitKey+drg.permitNo, c.codeName+drg.permitNo from Drg7003Db drg , CommonCode c where drg.permitKey = c.codeId and c.commonCodeKind.codeKindId = 'DRGPKID' and drg.drg7001Db.id = "+Common.getLong(obj.getId()),"") %>
			</select>	
		</td>
	</tr> 
	<tr id="spanMlms" style="display:none">
		<td class="queryTDInput" colspan="2" style="text-align:center;">
			<input class="toolbar_default" followPK="false" type="button" name="queryMLMS" value="確　　定" onClick="toShowMLMS()">
			<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="queryHidden('queryContainer1');">
		</td>
	</tr>
	</table>
</div>


<table width="100%" cellspacing="0" cellpadding="0">
<tr><td>
	<div id="formContainer" style="height:auto;display:none;">
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="vdrg0100q.jsp" />
	</div>
</td></tr>
<!--Toolbar區============================================================-->
<tr><td class="bgToolbar" style="text-align:left">
    <input type="hidden" id="listContainerActiveRowId">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getId()%>">
	<input type="hidden" name="actionType" value="<%=obj.getActionType()%>">
	<input type="hidden" name="formType" value="<%=obj.getFormType()%>">
	<input type="hidden" name="formInsert" value="<%=obj.getFormInsert()%>">
	<input type="hidden" name="drg72Id" value="<%=obj.getDrg72Id()%>">
	<input type="hidden" name="tabId" value ="<%=obj.getTabId()%>">
	<input type="hidden" name="toUpdate" value="<%=obj.getToUpdate()%>">
	<jsp:include page="../../home/toolbar.jsp" />
	<span id="spanMaintain0">
		<input class="toolbar_default" type="button" followPK="false" name="maintain0" value="暫　存" onClick="submitBefore(this.name,null,null,null,false);">&nbsp;
	</span>
	<span id="spanMaintain5">
		<input class="toolbar_default" type="button" followPK="false" name="maintain5" value="放 棄 離 開" onClick="submitBefore(this.name,null,null,null,false);">&nbsp;
	</span>
	<span id="spanMaintain1">
		<input class="toolbar_default" type="button" followPK="false" name="maintain1" value="送　出" onClick="submitBefore(this.name,'DRG030001','window.opener.whatButtonFireEvent(\''+this.name+'\');','N',true);">&nbsp;
	</span>
	<span id="spanMaintain3">
		<input class="toolbar_default" type="button" followPK="false" name="maintain3" value="回 覆 完 成" onClick="submitBefore(this.name,null,null,null,false);">&nbsp;
	</span>
	<span id="spanMaintain4">
		<input class="toolbar_default" type="button" followPK="false" name="maintain4" value="評 估 完 成" onClick="submitBefore(this.name,null,null,null,false);">&nbsp;
	</span>
	<span id="spanMaintain2">
		<input class="toolbar_default" type="button" followPK="false" name="maintain2" value="返 回 查 詢" onClick="submitBefore(this.name,null,null,null,false);">&nbsp;
	</span>
	<span id="spanPrint1">
		<input class="toolbar_default" type="button" followPK="false" name="print1" value="列印紅綠燈初稿" onClick="submitBefore(this.name,null,null,null,false);">&nbsp;
	</span>
	<span id="spanMaintain6">
			<input class="toolbar_default" type="button" followPK="false" name="maintain6" value="檢視藥證資料" onClick="choesePermitKeyNo(1);">&nbsp;
	</span>
	<span id="spanQuery1">
		<input class="toolbar_default" type="button" followPK="false" name="btnQuery1" value="郵件清單紀錄" onClick="popCon0002('DRG3','<%=obj.getId()%>');">&nbsp;
	</span>
</td></tr>
</table>

<table width="100%" cellspacing="0" cellpadding="0">
	<tr><td>
	<TABLE CELLPADDING=0 CELLSPACING=0>
		<TR>
			<td nowrap ID="t1" CLASS="tab_border1" ><a id="aTab1" href="#" onClick="changeTab(1);">警訊登錄</a></td>
			<% if(!"0101".equals(obj.getFormType()) && "Y".equals(obj.getIsShowT2Tab())){ %>
				<td nowrap ID="t2" CLASS="tab_border2" ><a id="aTab2" href="#" onClick="changeTab(2);">廠商回覆資料</a></td>
			<% }%>
			<% if(!"0101".equals(obj.getFormType()) && !"0201".equals(obj.getFormType())){ %>
				<td nowrap ID="t3" CLASS="tab_border2" ><a id="aTab3" href="#" onClick="changeTab(3);">評估資料</a></td>
			<% }%>
		</TR>
	</TABLE>
	</td></tr>
</table>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" id="Tab1" width="100%" align="center">
	<tr>
		<td nowrap class="td_form">案件編號：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field_RO" type="text" name="applNo" size="13" maxlength="13" value="<%=obj.getApplNo()%>">
		</td>
	</tr>
	<tr id="outHide1">
		<td nowrap class="td_form">案件狀態：</td>
		<td nowrap class="td_form_white">
			<select class="field_RO" name="status">
				<%=View.getOption("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGQTSTATUS' order by codeId", obj.getStatus())%>
			</select>
		</td>
		<td nowrap class="td_form">資料收集日期：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalndar("field","dataRevDate",obj.getDataRevDate())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">發佈單位：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCode("field","publDept",obj.getPublDept(),obj.getPublDeptName(),"","CONPUBLDEPT","publDeptCodeId",obj.getPublDeptCodeId())%>
		</td>
		<td nowrap class="td_form">原始發佈日期：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalndar("field","publDate",obj.getPublDate())%>
		</td>
	</tr>
	<tr id="outHide2">
		<td nowrap class="td_form">是否為轉發：</td>
		<td nowrap class="td_form_white">
			<%=View.getRadioBoxTextOption("field","istransfer","Y;是;N;否",obj.getIstransfer(),"changeIstransferDept()")%>
		</td>
		<td nowrap class="td_form">轉發單位：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCode("field","transferdept",obj.getTransferdept(),obj.getTransferdeptName(),"","CONPUBLDEPT","transferdeptCodeId",obj.getTransferdeptCodeId())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">資料來源：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field" type="text" name="datasourWebSite" size="150" maxlength="150" value="<%=obj.getDatasourWebSite()%>">
		</td>
	</tr>
	<tr id="outHide3">
		<td nowrap class="td_form">狀況：</td>
		<td nowrap class="td_form_white">
			<%=View.getRadioBoxOption("field","situation","select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONWARNING' order by codeId",obj.getSituation())%>
		</td>
		<td nowrap class="td_form">訊息回收品項數：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="recycleNum" size="10" maxlength="10" value="<%=obj.getRecycleNum()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">商品名：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field" type="text" name="chProduct" size="150" maxlength="1000" value="<%=obj.getChProduct()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">學名：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field" type="text" name="scientificName" size="150" maxlength="1000" value="<%=obj.getScientificName()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">劑型：</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getCheckBoxOption("field","warningmedModel","select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGFORM' order by codeId",obj.getWarningmedModel())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">廠商：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="druggist" size="50" maxlength="200" value="<%=obj.getDruggist()%>">
		</td>
		<td nowrap class="td_form">製造商：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="manufactorName" size="50" maxlength="200" value="<%=obj.getManufactorName()%>">
		</td>		
	</tr>
	<tr>
		<td nowrap class="td_form">產品批號：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field" type="text" name="lotNo" size="50" maxlength="50" value="<%=obj.getLotNo()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">事件簡述：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field" type="text" name="eventDesc" size="100" maxlength="200" value="<%=obj.getEventDesc()%>">
		</td>
	</tr>
	<tr id="outHide4">
		<td nowrap class="td_form">品質異常型態：</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getCheckBoxOption("field","qualitywarningtype","select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGWNTP' ", obj.getQualitywarningtype(),6)%>
		</td>
	</tr>
	<tr id="outHide4_2">
		<td nowrap class="td_form">回收型態：</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getRadioBoxOption("field","recycleType","select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGRECTP' order by codeId",obj.getRecycleType())%>
		</td>
	</tr>
	<tr id="outHide5">
	<!--  
		<td nowrap class="td_form">適應症：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="indication" size="25" maxlength="25" value="<%=obj.getIndication()%>">
		</td>
	-->	
		<td nowrap class="td_form">是否草擬紅綠燈初稿：</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getRadioBoxTextOption("field","iswarning","Y;是;N;否",obj.getIswarning())%>
		</td>		
	</tr>
	<tr>
		<td nowrap class="td_form">紅綠燈初稿：</td>
		<td nowrap class="td_form_white" colspan="3">
			<table width="100%" align="center" class="table_form">
				<tr>
					<td class="bg">
					<jsp:include page="condrg01Layout.jsp">
						<jsp:param name="filetype" value="DRG030001" />
						<jsp:param name="dbName" value="Drg7001Db" />
					</jsp:include>
				</td></tr>
			</table>
		</td>
	</tr>
	<tr id="outHide6">
		<td nowrap class="td_form">警訊備註：</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getPopTextareaCode("field","warningremark",obj.getWarningremark(),"100","2","DRGWAREMARK") %>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">異動人員/日期：</td>
		<td nowrap class="td_form_white" colspan="3">
			[<input class="field_RO" type="text" name="editID" size="10" value="<%=obj.getEditID()%>">/
			<input class="field_RO" type="text" name="editDate" size="7" value="<%=obj.getEditDate()%>">]
		</td>
	</tr>
	<tr id="outHide7">
		<td nowrap class="td_form_white" colspan="4">
			<jsp:include page="vdrg0173Layout.jsp" />
		</td>
	</tr>
	</table>
	<table class="table_form" id="Tab2" width="100%" align="center">
		<tr><td nowrap class="bg">
			<div id="formContainer" style="height:auto">
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td nowrap class="td_form">國內許可證字號：</td>
					<td nowrap class="td_form_white" colspan="3">
						<input class="field_RO" type="text" size="100" name="permitStr" value="<%=obj.getPermitStr()%>">
					</td>
				</tr>
				<tr>
					<td nowrap class="td_form">回覆日期：</td>
					<td nowrap class="td_form_white">
						<%=View.getPopCalndar("field_RO","replyDate",obj.getReplyDate())%>
					</td>
				</tr>
				<tr>
					<td nowrap class="td_form">廠商回覆內容：</td>
					<td nowrap class="td_form_white">
						<textarea class="field"  name="replyDesc" cols="100" rows="2" ><%=obj.getReplyDesc()%></textarea>
					</td>
				</tr>
				<tr>
					<td nowrap class="td_form">產品是否進口：</td>
					<td nowrap class="td_form_white">
						<%=View.getRadioBoxTextOption("field","isImport","Y;是;N;否",obj.getIsImport(),"changeIsImport()")%>
						<span id="showImpMsg" style="display:none"><font color="red">後續請記得填寫重大品質事件廠商主動通報表!!</font></span>
					</td>
				</tr>
				<tr>
					<td nowrap class="td_form">產品未進口原因：</td>
					<td nowrap class="td_form_white" colspan="3">
						<select class="field_RO" name="nonImportReason" onChange="changeNonImportReasonDescField();">
							<%=View.getOption("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGNIMREASON' ", obj.getNonImportReason())%>
						</select>
						<input class="field" type="text" name="nonImportReasonDesc" size=50 maxlength=100 value="<%=obj.getNonImportReasonDesc()%>">
					</td>
				</tr>
				<tr>
					<td nowrap class="td_form">回收案件編號：</td>
					<td nowrap class="td_form_white" colspan="3">
						<input class="field_RO" type="text" name="eventapplNo" size=11 maxlength=11 value="<%=obj.getEventapplNo()%>">
					</td>
				</tr>
				<tr>
					<td nowrap class="td_form">附件(回覆附件)：</td>
					<td nowrap class="td_form_white">
						<table width="100%" align="center" class="table_form">
							<tr><td class="bg">
								<jsp:include page="condrg01Layout.jsp">
									<jsp:param name="filetype" value="DRG030002" />
									<jsp:param name="dbName" value="Drg7002Db" />
								</jsp:include>
							</td></tr>
						</table>
					</td>
				</tr>
			</table>
			</div>
		</td></tr>
		<tr><td nowrap class="bgList">
		<div id="listContainer">
		<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
			<thead id="listTHEAD">
			<tr>
				<th class="listTH" ><a class="text_link_w" >NO.</a></th>
				<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">許可證持有商</a></th>
			</tr>
			</thead> 
			<tbody id="listTBODY">
			<%
				boolean primaryArray72[] = {true,false,true};
				boolean displayArray72[] = {false,true,false};
				out.write(View.getQuerylist(primaryArray72,displayArray72,objList72,obj.getQueryAllFlag()));
			%>
			</tbody>
		</table>
		</div>
		</td></tr>
	</table>
	<table class="table_form" id="Tab3" width="100%" align="center">
		<tr><td nowrap class="bg">
			<div id="formContainer" style="height:auto">
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td nowrap class="td_form">評估日期：</td>
					<td nowrap class="td_form_white">
						<%=View.getPopCalndar("field","estimateDate",obj.getEstimateDate())%>
					</td>
				</tr>
				<tr>
					<td nowrap class="td_form">是否發佈新聞稿：</td>
					<td nowrap class="td_form_white">
						<%=View.getRadioBoxTextOption("field","ispublnews","Y;是;N;否",obj.getIspublnews())%>
					</td>
				</tr>
				<tr>
					<td nowrap class="td_form">是否發佈消費者知識服務網：</td>
					<td nowrap class="td_form_white">
						<%=View.getRadioBoxTextOption("field","ispublconsumer","Y;是;N;否",obj.getIspublconsumer(),"changeIspublconsumer()")%>
					</td>
				</tr>
				<tr>
					<td nowrap class="td_form">消費者知識服務網上架日期：</td>
					<td nowrap class="td_form_white">
						<%=View.getPopCalndar("field","publconsumerDate",obj.getPublconsumerDate())%>
					</td>
				</tr>
				<tr>
					<td nowrap class="td_form">燈號：</td>
					<td nowrap class="td_form_white">
						<%=View.getRadioBoxOption("field","lamp","select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONLAMP' order by codeId",obj.getLamp())%>
					</td>
				</tr>
				<tr>
					<td nowrap class="td_form">後續處理：</td>
					<td nowrap class="td_form_white">
						<%=View.getPopCode("field","aftereffect",obj.getAftereffect(),obj.getAftereffectName(),"","DRGEFFECT","aftereffectCodeId",obj.getAftereffectCodeId(),new int[]{2,2,100,20},false,"changeAftereffectOtherField()")%><br>
						<input class="field" type="text" name="aftereffectOther" size="50" maxlength="50" value="<%=obj.getAftereffectOther()%>">
					</td>
				</tr>
				<tr>
					<td nowrap class="td_form">評估備註：</td>
					<td nowrap class="td_form_white">
						<input class="field" type="text" name="estimateremark" size="50" maxlength="50" value="<%=obj.getEstimateremark()%>">
					</td>
				</tr>
				<tr>
					<td nowrap class="td_form">附件(評估附件)：</td>
					<td nowrap class="td_form_white">
						<table width="100%" align="center" class="table_form">
							<tr><td class="bg">
								<jsp:include page="condrg01Layout.jsp">
									<jsp:param name="filetype" value="DRG030003" />
									<jsp:param name="dbName" value="Drg7001Db" />
								</jsp:include>
							</td></tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
		</td></tr>
	</table>
	</div>
</td></tr>
</table>
</form>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	form1.actionType.value = buttonName;
	switch (buttonName)	
	{
		case "update":
			setFormItem("maintain0","O");
			setFormItem("maintain1","R");
			setUploadButtonFormItem("O");
			break;
		case "clear":
			setUploadButtonFormItem("R");
			setFormItem("print1","O");
			break;
		case "maintain0":
			form1.state.value = "update";
			form1.formInsert.value = "";
			setBeforePageUnload(false);
			beforeSubmit();
			form1.submit();
			break;
		case "maintain1":
			form1.state.value = "update";
			setBeforePageUnload(false);
			checkField();
			break;
		case "maintain2":
			var action = "vdrg"+form1.formType.value+"f.jsp";
			form1.action = action;
			form1.state.value = "queryAll";
			form1.submit();
			break;
		case "maintain3":
			if(null != form1.drg72Id.value && "" != form1.drg72Id.value){
				form1.state.value = "update";
				setBeforePageUnload(false);
				beforeSubmit();
				form1.submit();
			}else{
				alert("請點選一點廠商回覆資料!!");
			}
			break;
		case "maintain4":
			form1.state.value = "update";
			setBeforePageUnload(false);
			checkField();
			break;
		case "maintain5":
			whatButtonFireEvent("delete");
			break;
		case "print1":
			queryShow('queryContainer');
			break;
	}
}
</script>
</body>
</html>

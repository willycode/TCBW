<!--
程式目的：藥品療效不等審核明細作業
程式代號：drgin0302
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp"/>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0302F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objQry03" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0303F">
	<jsp:setProperty name="objQry03" property="*"/>
</jsp:useBean>
<jsp:useBean id="objQry04" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0304F">
	<jsp:setProperty name="objQry04" property="*"/>
</jsp:useBean>
<jsp:useBean id="objQry08" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0308F">
	<jsp:setProperty name="objQry08" property="*"/>
</jsp:useBean>
<jsp:useBean id="objQry09" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0309F">
	<jsp:setProperty name="objQry09" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList41" scope="page" class="java.util.ArrayList"/>
<jsp:useBean id="objList48" scope="page" class="java.util.ArrayList"/>
<jsp:useBean id="objList49" scope="page" class="java.util.ArrayList"/>
<jsp:useBean id="objList46" scope="page" class="java.util.ArrayList"/>
<jsp:useBean id="objList47" scope="page" class="java.util.ArrayList"/>

<%
String formType = Common.get(request.getParameter("formType"));
obj.setFormType(formType);
String q_isComplete = Common.get(request.getParameter("q_isComplete"));
obj.setQ_isComplete(q_isComplete);
String caseType = Common.get(request.getParameter("caseType"));
obj.setCaseType(caseType);

String isQuery = Common.get(request.getParameter("isQuery"));//隱藏綜合查詢返回按鈕

if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())) {
	obj.setHttpRequest(pageContext.getRequest());
	if("0".equals(obj.getActionType())){
		obj.doUpdateCase();
	}else{
		obj.doUpdate();
	}
	obj.setState("updateSuccess");
	obj.setQueryAllFlag("false");
	String okMsg = "";
	if("1".equals(obj.getActionType())){
		okMsg = "受理完成";
	}else if("2".equals(obj.getActionType())){
		okMsg = "退件完成";
	}else if("3".equals(obj.getActionType())){
		okMsg = "撤案完成";
	}else if("4".equals(obj.getActionType())){
		okMsg = "改派完成";
		obj.setState("queryOne");
		obj.setQueryAllFlag("true");
	}else if("8".equals(obj.getActionType())){
		okMsg = "初評完成";
	}else if("9".equals(obj.getActionType())){
		okMsg = "退件補件完成";
	}else if("10".equals(obj.getActionType())){
		okMsg = "通知補件完成";
	}else if("13".equals(obj.getActionType())){
		okMsg = "取消提報完成";
	}else if("14".equals(obj.getActionType())){
		okMsg = "醫院詢問完成";
		obj.setState("queryOne");
		obj.setQueryAllFlag("true");
	}else if("17".equals(obj.getActionType())){
		okMsg = "評估完成";
	}else if("18".equals(obj.getActionType())){
		okMsg = "廠商補件通知完成";
	}else{
		okMsg = "暫存完成";
		obj.setState("queryOne");
		obj.setQueryAllFlag("true");
	}
	if(null == obj.getErrorMsg() || "".equals(obj.getErrorMsg())){
		obj.setErrorMsg(okMsg);
	}else{
		obj.setState("queryOne");
		obj.setQueryAllFlag("true");
	}
}else{
	obj.setQueryAllFlag("true");
}
if ("true".equals(obj.getQueryAllFlag())){
	obj = (com.kangdainfo.tcbw.view.drgin.DRGIN0302F)obj.queryOne();
	if(null != obj){
		objList48 = obj.doQueryAllDrg48(obj.getId());
		objList49 = obj.doQueryAllDrg49(obj.getId());
		objList46 = obj.doQueryAllDrg46(obj.getId());
		objList47 = obj.doQueryAllDrg47(obj.getId());
		objList41 = obj.doQueryAllDrg41(obj.getId(),true);
	}
}
//取得藥品類別參數
java.util.List<CommonCode> list = ServiceGetter.getInstance().getCommonService().getCommonCodeDao().getCommonCodeList("DRG0307");

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
<script type="text/javascript" src="../../js/cbToggle.js"></script>
<script type="text/javascript">
var popWinName;
function checkField(type)
{
	var alertStr = "";
	for(var i=0;i<form1.conSequence4.length;i++)
	{
		if(form1.conSequence4[i].checked){
			if("1" == form1.conSequence4[i].value){
				alertStr += checkRadioButton(form1.effectChangeDesc4,"藥效改變狀況");
			}
			if("2" == form1.conSequence4[i].value){
				alertStr += checkRadioButton(form1.badReactionLev4,"不良反應等級");
				alertStr += checkEmpty(form1.badReactionDesc4,"不良反應狀況");
				//alertStr += checkEmpty(form1.badReactionDra,"MedDRA coding");
			}
		}
	}
	
	if("8" == type)
	{
		for(var i=0;i<form1.conSequence.length;i++)
		{
			if(form1.conSequence[i].checked){
				if("1" == form1.conSequence[i].value){
					alertStr += checkRadioButton(form1.effectChangeDesc,"藥效改變狀況");
				}
				if("2" == form1.conSequence[i].value){
					alertStr += checkRadioButton(form1.badReactionLev,"不良反應等級");
					alertStr += checkEmpty(form1.badReactionDesc,"不良反應等級");
				}
			}
		}
		<%=TCBWCommon.getCheckFiled("DRG02","DRGIN0302F")%> //案件初評表欄位檢核
	}	
	
	alertStr += validateDrg42Table();		
	alertStr += validateDrg43Table();
	<%=TCBWCommon.getIsComplete("DRG02")%>
	
	if(alertStr.length!=0){ alert(alertStr); return false;}

	if("8" == type)
	{
		getEmail('DRG020004','window.opener.onClickButton(20)','N'); //檢核後才發送信件
		return false;
	}

	
	if("upload" == form1.state.value){
		beforeSubmit();
		form1.submit();
	}
	return true;
}

function transfer()
{
   beforeSubmit();
   form1.submit();
   return true;
}

function init() 
{	
	if(null != form1.tabId.value && "" != form1.tabId.value){
		changeTab(form1.tabId.value);
	}else{
		changeTab(1);
	}
	
	setDisplayItem('spanInsert,spanQueryAll,spanDelete,spanListPrint,spanListHidden,spanConfirm','H');
	if("0309" == form1.formType.value || "0309O"  == form1.formType.value || "0309F"  == form1.formType.value 
			)
	{
		setDisplayItem('spanUpdate,spanClear,spanMaintain0,spanMaintain1,spanMaintain2,spanMaintain3,spanMaintain7,spanMaintain8,spanMaintain9,spanMaintain10,spanMaintain11,spanMaintain12,spanMaintain13,spanMaintain18,spanMaintain19','H');
		if("0309"  == form1.formType.value){
			setDisplayItem('spanMaintain4','H');
		}
		if("0309O"  == form1.formType.value || "0309F"  == form1.formType.value){
			setDisplayItem('spanMaintain4,spanMaintain6','H');
		}
		if("0309F"  == form1.formType.value){
			setDisplayItem('spanMaintain5,spanQuery3,spanQuery4,spanQuery5,spanQuery6,spanReport','H');
			changeTab(10);
			if(isObj(document.getElementById("t1")))	document.getElementById("t1").style.display = 'none';
			if(isObj(document.getElementById("t2")))	document.getElementById("t2").style.display = 'none';
			if(isObj(document.getElementById("t3")))	document.getElementById("t3").style.display = 'none';
			if(isObj(document.getElementById("t4")))	document.getElementById("t4").style.display = 'none';
			if(isObj(document.getElementById("t5")))	document.getElementById("t5").style.display = 'none';
			if(isObj(document.getElementById("t6")))	document.getElementById("t6").style.display = 'none';
			if(isObj(document.getElementById("t7")))	document.getElementById("t7").style.display = 'none';
			if(isObj(document.getElementById("t8")))	document.getElementById("t8").style.display = 'none';
			if(isObj(document.getElementById("t9")))	document.getElementById("t9").style.display = 'none';
		}
		
	}
	else
	{
		setFormItem("maintain0,maintain1,maintain2,maintain3,maintain8,maintain19","R");
		if("updateSuccess" == "<%=obj.getState()%>"){
			form1.action = "drgin0301f.jsp";
			if("7" == form1.actionType.value || "8" == form1.actionType.value || "9" == form1.actionType.value || "10" == form1.actionType.value){
				form1.action = "drgin0303f.jsp";
			}else if("17" == form1.actionType.value || "18" == form1.actionType.value){
				form1.action = "drgin0308f.jsp";
			}
			form1.actionType.value = "";
			form1.state.value = "queryAll";
			form1.submit();
		}

		if("90" == "<%=obj.getStatus()%>"){
			setDisplayItem('spanConfirm','H');
		}
	}

	showAssess();
	chgConSequence();
	chgConSequence4();
	
	<%
	if(null == obj.getDrg43JSBuilder() || "".equals(obj.getDrg43JSBuilder())){
		for(CommonCode code : list){
			out.write("addDrg43Row"+code.getCodeId()+"('drg43Table"+code.getCodeId()+"');");
	 	}
	}
	%>

	if('<%=isQuery%>'=='Y')
	{	
		setDisplayItem('spanMaintain6');
	}	
	
	document.all.item('notifierRevDate').className = 'field_RO';
	document.all.item('btn_notifierRevDate').className = 'field_RO';
	document.all.item('occurDate').className = 'field_RO';
	document.all.item('btn_occurDate').className = 'field_RO';
	document.all.item('notifierRevDate').className = 'field_RO';
	document.all.item('btn_notifierRevDate').className = 'field_RO';
	document.all.item('notifierRepDate').className = 'field_RO';
	document.all.item('btn_notifierRepDate').className = 'field_RO';

	<%out.write(TCBWCommon.getIsMlmsField("DRG02","drg43Row"));%>
	if('<%=isQuery%>'!='Y')
	{
	  window.parent.frames["menu"].location.href="../../home/dTreeMenu.jsp";
	}
	
}

function beforeInit() {
	<%=obj.getDrg42JSBuilder()%>
	<%=obj.getDrg43JSBuilder()%>
	<%=obj.getConJSBuilder()%>
}

function changeTab(tabId) 
{
	form1.tabId.value = tabId;
	document.getElementById("t1").className = "tab_border2";
	document.getElementById("t2").className = "tab_border2";
	document.getElementById("t3").className = "tab_border2";
	document.getElementById("t10").className = "tab_border2";
	if(isObj(document.getElementById("t4"))){
		document.getElementById("t4").className = "tab_border2";
	}
	if(isObj(document.getElementById("t5"))){
		document.getElementById("t5").className = "tab_border2";
	}
	if(isObj(document.getElementById("t6"))){
		document.getElementById("t6").className = "tab_border2";
	}
	if(isObj(document.getElementById("t7"))){
		document.getElementById("t7").className = "tab_border2";
	}
	if(isObj(document.getElementById("t8"))){
		document.getElementById("t8").className = "tab_border2";
	}
	if(isObj(document.getElementById("t9"))){
		document.getElementById("t9").className = "tab_border2";
	}
	document.getElementById("aTab1").className = "";
	document.getElementById("aTab2").className = "";
	document.getElementById("aTab3").className = "";
	document.getElementById("aTab10").className = "";
	if(isObj(document.getElementById("aTab4"))){
		document.getElementById("aTab4").className = "";
	}
	if(isObj(document.getElementById("aTab5"))){
		document.getElementById("aTab5").className = "";
	}
	if(isObj(document.getElementById("aTab6"))){
		document.getElementById("aTab6").className = "";
	}
	if(isObj(document.getElementById("aTab7"))){
		document.getElementById("aTab7").className = "";
	}
	if(isObj(document.getElementById("aTab8"))){
		document.getElementById("aTab8").className = "";
	}
	if(isObj(document.getElementById("aTab9"))){
		document.getElementById("aTab9").className = "";
	}
	document.getElementById("Tab1").style.display = 'none';
	document.getElementById("Tab2").style.display = 'none';
	document.getElementById("Tab3").style.display = 'none';
	document.getElementById("Tab4").style.display = 'none';
	document.getElementById("Tab5").style.display = 'none';
	document.getElementById("Tab6").style.display = 'none';
	document.getElementById("Tab7").style.display = 'none';
	document.getElementById("Tab8").style.display = 'none';
	document.getElementById("Tab9").style.display = 'none';
	document.getElementById("Tab10").style.display = 'none';
	if(tabId == 2){
		document.getElementById("t2").className = "tab_border1";	
		document.getElementById("Tab2").style.display = '';
		document.getElementById("aTab2").className = "text_w";		
	}
	else if (tabId == 3){
		document.getElementById("t3").className = "tab_border1";	
		document.getElementById("Tab3").style.display = '';
		document.getElementById("aTab3").className = "text_w";	
	}else if (tabId == 4){
		document.getElementById("t4").className = "tab_border1";	
		document.getElementById("Tab4").style.display = '';
		document.getElementById("aTab4").className = "text_w";	
	}else if (tabId == 5){
		document.getElementById("t5").className = "tab_border1";	
		document.getElementById("Tab5").style.display = '';
		document.getElementById("aTab5").className = "text_w";	
	}else if (tabId == 6){
		document.getElementById("t6").className = "tab_border1";	
		document.getElementById("Tab6").style.display = '';
		document.getElementById("aTab6").className = "text_w";	
	}else if (tabId == 7){
		document.getElementById("t7").className = "tab_border1";	
		document.getElementById("Tab7").style.display = '';
		document.getElementById("aTab7").className = "text_w";	
	}else if (tabId == 8){
		document.getElementById("t8").className = "tab_border1";	
		document.getElementById("Tab8").style.display = '';
		document.getElementById("aTab8").className = "text_w";	
	}else if (tabId == 9){
		document.getElementById("t9").className = "tab_border1";	
		document.getElementById("Tab9").style.display = '';
		document.getElementById("aTab9").className = "text_w";	
	}else if (tabId == 10){
		document.getElementById("t10").className = "tab_border1";	
		document.getElementById("Tab10").style.display = '';
		document.getElementById("aTab10").className = "text_w";	
	}else{
		document.getElementById("t1").className = "tab_border1";
		document.getElementById("Tab1").style.display = '';
		document.getElementById("aTab1").className = "text_w";	
	}
}

function onClickButton(type){
	var chk = true;
	if("0" == type){
		form1.actionType.value = "";
	}else if("1" == type){
		chk = checkField();
	}else if("4" == type){
		form1.actionType.value = type;
		popChargeManView();
		chk = false;
	}else if("7" == type){
		changeTab(4);
		whatButtonFireEvent('update');
		document.all.item("maintain0").disabled = false;
		document.all.item("maintain7").disabled = true;
		chk = false;
	}else if("6" == type){
		var action = "drgin"+form1.formType.value+"f.jsp";
		if(null != form1.caseType.value && "" != form1.caseType.value){
			action += "?caseType="+form1.caseType.value;
		}else if(null != form1.q_isComplete.value && "" != form1.q_isComplete.value){
			action += "?isComplete="+form1.q_isComplete.value;
		}
		form1.action = action;
		form1.state.value = "queryAll";
		form1.submit();
		chk = false;
	}else if("8" == type){
		chk = checkField(type);
	}else if("11" == type){
		if("30" == "<%=obj.getStatus()%>"){
			changeTab(5);
		}else if("50" == "<%=obj.getStatus()%>"){
			changeTab(7);
		}
		whatButtonFireEvent('update');
		document.all.item("maintain0").disabled = false;
		chk = false;
	}else if("12" == type){
		form1.actionType.value = type;
		popDRG4008View();
		chk = false;
	}
	if(chk){
		if("20" == type) {
			form1.actionType.value = "8"; //傳入20時為初評完成
		}
		else {
			form1.actionType.value = type;
		}
		
		form1.state.value = "update";
		setBeforePageUnload(false);
		beforeSubmit();
		form1.submit();
	}
	
}

function report(isClose)
{
	form1.isCloseUserInfo.value=isClose;
	form1.action = "drgin0302p.jsp" ;
	form1.target = "_blank";
	beforeSubmit();
	form1.submit();
	form1.action = "drgin0302f.jsp" ;
	form1.target = "_self";
}

function getEmail(mailID,isJS,isAdd)
{
	var id = form1.id.value;
	if("DRG020007" == mailID){
		id = form1.drg45Id.value;
	}
	var parm = "isAdd="+isAdd+"&applNo="+form1.applNo.value+"&isJS="+isJS+"&mailID="+mailID+"&id="+id+ "&systemType=DRG";
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=1200,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	window.open(getVirtualPath() + "tcbw/conbg/conbg0001f.jsp?"+parm,"",prop);
}

function popChargeManView()
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
	  prop=prop+"width=400,height=400,";
	  prop=prop+"top="+windowTop+",";
	  prop=prop+"left="+windowLeft+",";
	  prop=prop+"scrollbars=yes,resizable=yes";
	  closeReturnWindow();
	  var q="?id="+form1.id.value+"&q_formCode=DRG02&q_code=01";
	  returnWindow=window.open(getVirtualPath() + "home/popChargeMan.jsp"+q,"",prop);
    }
}

function updChargeMan(userName){
	form1.chargeMan.value = userName;
	form1.state.value = "update";
	setBeforePageUnload(false);
	beforeSubmit();
	form1.submit();
}

function choesePermitKeyNo(){
	queryShow('queryContainer');
}
function toShowMLMS(){
	if(null != form1.q_permitKeyNo.value && "" != form1.q_permitKeyNo.value){
		var permitKey = form1.q_permitKeyNo.value.substring(0,2);
		var permitNo = form1.q_permitKeyNo.value.substring(2);	
		var params = 'width=980,height=640,resizable=1,menubar=no,scrollbars=yes';
		if (popWinName!=null) popWinName.close();
		popWinName = window.open("drgin0104f.jsp?permitKey="+permitKey+"&permitNo="+permitNo,'popWinE',params);	
		whatButtonFireEvent("queryCannel");
	}else{
		alert("請選擇一筆藥證資料!!");
	}
}
function showAssess(){
	document.getElementById("assessShow1").style.display="none";
	document.getElementById("assessShow2").style.display="none";
	for(var i=0;i<form1.conSequence4.length;i++)
	{
		if(form1.conSequence4[i].checked){
			if("1" == form1.conSequence4[i].value){
				document.getElementById("assessShow1").style.display="block";
			}
			if("2" == form1.conSequence4[i].value){
				document.getElementById("assessShow2").style.display="block";
			}
		}
	}
}
function popDRG4008View(drg45Id, tabId)
{
	var q_permitKey = "";
	var q_permitNo = "";
	var drg43Row=document.getElementsByName("drg43Row");
	if (drg43Row!=null && drg43Row.length>0) {
		for(var i=0; i<drg43Row.length; i++) {
			var id = drg43Row[i].value;
			if("02" == getObjectValue("medType"+id)){
				q_permitKey = getObjectValue("permitKey"+id);
				q_permitNo = getObjectValue("permitNo"+id);
			}
		}
	}

	if(null != q_permitKey && "" != q_permitKey && null != q_permitNo && "" != q_permitNo){
		var param = "";	
		var prop = "";
		var windowTop=(document.body.clientHeight-400)/2+100;
		var windowLeft=(document.body.clientWidth-400)/2+100;
		prop=prop+"width=800,height=600,";
		prop=prop+"top="+windowTop+",";
		prop=prop+"left="+windowLeft+",";
		prop=prop+"scrollbars=yes,resizable=yes";
		closeReturnWindow();
		param = "drgin0305f.jsp?";
		if(null != drg45Id && "" != drg45Id){
			param = "drgin0306f.jsp?";
		}

		param += "q_permitKey="+q_permitKey+"&q_permitNo="+q_permitNo;
		if(null != drg45Id && "" != drg45Id){
			param += "&id="+drg45Id;
		}
		if(null != tabId && "" != tabId){
			param += "&tabId="+tabId;
		}

		returnWindow=window.open(param,"",prop);
		returnWindow.moveTo(0,0);
		returnWindow.resizeTo(screen.width,screen.height);
	}else{
		alert("未有懷疑療效不等藥品許可證資料");
	}
}
function doQueryOneData(){
	form1.state.value = "queryOne";
	form1.action = "drgin0302f.jsp";
	beforeSubmit();
	form1.submit();
}
function queryOne(id){
	
}
function sendDRG45(drg45id){
	form1.drg45Id.value = drg45id;
	getEmail('DRG020007','window.opener.onClickButton(15)','N');
}
function popDRG4006View(drg4006DbId)
{
	if((null != form1.id.value && "" != form1.id.value) || (null != drg4006DbId && "" != drg4006DbId)){
		var param = getVirtualPath() + "tcbw/drgex/";	
		var prop = "";
		var windowTop=(document.body.clientHeight-400)/2+100;
		var windowLeft=(document.body.clientWidth-400)/2+100;
		prop=prop+"width=800,height=600,";
		prop=prop+"top="+windowTop+",";
		prop=prop+"left="+windowLeft+",";
		prop=prop+"scrollbars=yes,resizable=yes";
		closeReturnWindow();
		
		if(null != drg4006DbId && "" != drg4006DbId){
			param += "drgex0306f.jsp?q_drg4001DbId="+form1.id.value+"&id="+drg4006DbId;
		}else{
			param += "drgex0305f.jsp?q_drg4001DbId="+form1.id.value;
		}
		returnWindow=window.open(param,"",prop);
	}else{
		alert("未有懷疑療效不等藥品許可證資料");
	}
}

function doQueryOne1(){
	form1.state.value = "queryOne";
	form1.action = "drgin0302f.jsp";
	beforeSubmit();
	form1.submit();
}
function popShowOne(id, popType){
	var prop = "";
	var url = "drgin0302f.jsp?id="+id;
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=800,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	if("F" == popType){
		url += "&formType=0309F";
	}else{
		url += "&formType=0309O";
	}
	returnWindow=window.open(url,"",prop);
}
function popReportDrg41Form()
{
	if (isObj(cb)) {
		if (cb.AnyChecked()==false){
			alert("您尚未勾選任何案件！");
		}else{	
			form1.action="drgin0302_1p.jsp";
			form1.target="_self";
			form1.submit();
		}
	}	
}
function chgNotifierType(notifierType){
	if(null != notifierType && "" != notifierType){
		if("01" == notifierType){
			checkedRadio(form1.notifierSource,"01");
		}else if("02" == notifierType){
			checkedRadio(form1.notifierSource,"04");
		}else if("03" == notifierType){
			checkedRadio(form1.notifierSource,"05");
		}else if("04" == notifierType){
			checkedRadio(form1.notifierSource,"06");
		}
	}
}
function popDrg6001(id){
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=850,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "home/popDrg6001.jsp?q_id="+id,"",prop);
}

function popDRG4007View(drg4007DbId)
{
	if(null != drg4007DbId && "" != drg4007DbId){
		var prop = "";
		var windowTop=(document.body.clientHeight-400)/2+100;
		var windowLeft=(document.body.clientWidth-400)/2+100;
		prop=prop+"width=800,height=600,";
		prop=prop+"top="+windowTop+",";
		prop=prop+"left="+windowLeft+",";
		prop=prop+"scrollbars=yes,resizable=yes";
		closeReturnWindow();
		returnWindow=window.open("drgin0310f.jsp?&id="+drg4007DbId,"",prop);
	}
}

function popShowDrg49(ingredient, drg4005Id){
	if(null != ingredient && "" != ingredient){
		var prop = "";
		var windowTop=(document.body.clientHeight-400)/2+100;
		var windowLeft=(document.body.clientWidth-400)/2+100;
		prop=prop+"width=800,height=600,";
		prop=prop+"top="+windowTop+",";
		prop=prop+"left="+windowLeft+",";
		prop=prop+"scrollbars=yes,resizable=yes";
		closeReturnWindow();
		returnWindow=window.open("drgin0311f.jsp?&q_ingredient="+encodeURI(encodeURI(ingredient))+"&q_drg4005Id="+drg4005Id,"",prop);
	}
}
function upload()
{
	 var prop='';
	 prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,';
	 prop=prop+'width=850,';
	 prop=prop+'height=600';
	 closeReturnWindow();

     var url="../../home/popManyUploadSimple.jsp?";
         url+="fileKind=DRG";
         url+="&systemType=DRG020001";
         url+="&uploadId="+form1.id.value;
         url+="&dbName=Drg4001Db";
         
	 returnWindow = window.open(url,'上傳檔案',prop);
}

function showNotifierData(){
	if(document.all.item("notifier").checked){
		document.all.item("notifier").disabled=true;
		var q = document.all.item("id").value;
	    var x = getRemoteData("../../ajax/jsonDrg4001Db.jsp",q );		
	    if(x!=null && x.length>0)
	    {
		  var json1 = JSON.parse(x);

		  if(isObj(document.all.item("notifierName").value))
			document.all.item("notifierName").value =json1.notifierName;
		  if(isObj(document.all.item("notifierTelArea").value))
			document.all.item("notifierTelArea").value =json1.notifierTelArea;
		  if(isObj(document.all.item("notifierTel").value))
			document.all.item("notifierTel").value =json1.notifierTel;
		  if(isObj(document.all.item("notifierTelExt").value))
			document.all.item("notifierTelExt").value =json1.notifierTelExt;
		  if(isObj(document.all.item("notifierEmail").value))
			document.all.item("notifierEmail").value =json1.notifierEmail;
		  if(isObj(document.all.item("notifierPhone").value))
			document.all.item("notifierPhone").value =json1.notifierPhone;
		  if(isObj(document.all.item("notifierFaxArea").value))
			document.all.item("notifierFaxArea").value =json1.notifierFaxArea;
		  if(isObj(document.all.item("notifierFax").value))
			document.all.item("notifierFax").value =json1.notifierFax;
		  if(isObj(document.all.item("notifierAddress").value))
			document.all.item("notifierAddress").value =json1.notifierAddress;		  
		  if(isObj(document.all.item("notifierDept").value))
			document.all.item("notifierDept").value =json1.notifierDept;
		  if(isObj(document.all.item("notifierDeptID").value))
			document.all.item("notifierDeptID").value =json1.notifierDeptID;
	     }

		//記錄log  
		var url = '../../ajax/jsonCommonLogDb.jsp';				
		var q = "&code=DRG02";
			q +="&methodName=open";
			q +="&db=Drg4001Db";
			q +="&hql=select id,applNo,notifierUserID,notifierName from Drg4001Db where id="+form1.id.value;
		var xUserUpdate = getRemoteData(url,q);
	}
}

function chgConSequence(){
	var conSequenceStr="";	
	for(var i=0;i<form1.conSequence.length;i++)
	{
		if(form1.conSequence[i].checked){
			if("1" == form1.conSequence[i].value){
				if(isObj(form1.effectChangeDesc)){
					var effectChangeDescs = form1.effectChangeDesc;
					for (var j=0; j<effectChangeDescs.length; j++) {
						effectChangeDescs[j].className = 'field';
						effectChangeDescs[j].disabled = false;
					}
				}
				conSequenceStr += "1";
			}

			if("2" == form1.conSequence[i].value){
				if(isObj(form1.badReactionLev)){
					var badReactionLevs = form1.badReactionLev;
					for (var j=0; j<badReactionLevs.length; j++) {
						badReactionLevs[j].className = 'field';
						badReactionLevs[j].disabled = false;
					}
				}

				document.all.item('badReactionDesc').disabled = false;
				document.all.item('badReactionDesc').className = 'field';
				conSequenceStr += "2";
			}
		}
	}
	if("1" == conSequenceStr){
		document.all.item('badReactionDesc').disabled = true;
		document.all.item('badReactionDesc').className = 'field_RO';
		document.all.item('badReactionDesc').value = "";

		if(isObj(form1.badReactionLev)){
			var badReactionLevs = form1.badReactionLev;
			for (var i=0; i<badReactionLevs.length; i++) {
				badReactionLevs[i].className = 'field_RO';
				badReactionLevs[i].disabled = true;
				badReactionLevs[i].checked = false;
			}
		}
	}else if("2" == conSequenceStr){
		if(isObj(form1.effectChangeDesc)){
			var effectChangeDescs = form1.effectChangeDesc;
			for (var i=0; i<effectChangeDescs.length; i++) {
				effectChangeDescs[i].className = 'field_RO';
				effectChangeDescs[i].disabled = true;
				effectChangeDescs[i].checked = false;
			}
		}
	}else if(null == conSequenceStr || "" == conSequenceStr){
		document.all.item('badReactionDesc').disabled = true;
		document.all.item('badReactionDesc').className = 'field_RO';
		document.all.item('badReactionDesc').value = "";

		if(isObj(form1.badReactionLev)){
			var badReactionLevs = form1.badReactionLev;
			for (var i=0; i<badReactionLevs.length; i++) {
				badReactionLevs[i].className = 'field_RO';
				badReactionLevs[i].disabled = true;
				badReactionLevs[i].checked = false;
			}
		}

		if(isObj(form1.effectChangeDesc)){
			var effectChangeDescs = form1.effectChangeDesc;
			for (var i=0; i<effectChangeDescs.length; i++) {
				effectChangeDescs[i].className = 'field_RO';
				effectChangeDescs[i].disabled = true;
				effectChangeDescs[i].checked = false;
			}
		}
	}
}

function chgConSequence4(){
	var conSequence4Str="";	
	for(var i=0;i<form1.conSequence4.length;i++)
	{
		if(form1.conSequence4[i].checked){
			if("1" == form1.conSequence4[i].value){
				if(isObj(form1.effectChangeDesc4)){
					var effectChangeDesc4s = form1.effectChangeDesc4;
					for (var j=0; j<effectChangeDesc4s.length; j++) {
						effectChangeDesc4s[j].className = 'field';
						effectChangeDesc4s[j].disabled = false;
					}
				}
				conSequence4Str += "1";
			}

			if("2" == form1.conSequence4[i].value){
				if(isObj(form1.badReactionLev4)){
					var badReactionLev4s = form1.badReactionLev4;
					for (var j=0; j<badReactionLev4s.length; j++) {
						badReactionLev4s[j].className = 'field';
						badReactionLev4s[j].disabled = false;
					}
				}

				document.all.item('badReactionDesc4').disabled = false;
				document.all.item('badReactionDesc4').className = 'field';
				document.all.item('badReactionDesc4').removeAttribute('readonly',0);
				//document.all.item('badReactionDra').disabled = false;
				//document.all.item('badReactionDra').className = 'field';
				//document.all.item('badReactionDra').removeAttribute('readonly',0);
				conSequence4Str += "2";
			}
		}
	}
	
	if("1" == conSequence4Str){
		document.all.item('badReactionDesc4').disabled = true;
		document.all.item('badReactionDesc4').className = 'field_RO';
		document.all.item('badReactionDesc4').value = "";
		//document.all.item('badReactionDra').disabled = true;
		//document.all.item('badReactionDra').className = 'field_RO';
		//document.all.item('badReactionDra').value = "";
		if(isObj(form1.badReactionLev4)){
			var badReactionLev4s = form1.badReactionLev4;
			for (var j=0; j<badReactionLev4s.length; j++) {
				badReactionLev4s[j].className = 'field_RO';
				badReactionLev4s[j].disabled = true;
				badReactionLev4s[j].checked = false;
			}
		}
	}else if("2" == conSequence4Str){
		if(isObj(form1.effectChangeDesc4)){
			var effectChangeDesc4s = form1.effectChangeDesc4;
			for (var j=0; j<effectChangeDesc4s.length; j++) {
				effectChangeDesc4s[j].className = 'field_RO';
				effectChangeDesc4s[j].disabled = true;
				effectChangeDesc4s[j].checked = false;
			}
		}
	}else if(null == conSequence4Str || "" == conSequence4Str){
		document.all.item('badReactionDesc4').disabled = true;
		document.all.item('badReactionDesc4').className = 'field_RO';
		document.all.item('badReactionDesc4').value = "";
		//document.all.item('badReactionDra').disabled = true;
		//document.all.item('badReactionDra').className = 'field_RO';
		//document.all.item('badReactionDra').value = "";
		if(isObj(form1.badReactionLev4)){
			var badReactionLev4s = form1.badReactionLev4;
			for (var j=0; j<badReactionLev4s.length; j++) {
				badReactionLev4s[j].className = 'field_RO';
				badReactionLev4s[j].disabled = true;
				badReactionLev4s[j].checked = false;
			}
		}

		if(isObj(form1.effectChangeDesc4)){
			var effectChangeDesc4s = form1.effectChangeDesc4;
			for (var j=0; j<effectChangeDesc4s.length; j++) {
				effectChangeDesc4s[j].className = 'field_RO';
				effectChangeDesc4s[j].disabled = true;
				effectChangeDesc4s[j].checked = false;
			}
		}
	}
}

//彈出式視窗尋問是否遮蔽個資
function toAsk()
{
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;

	//點選不遮蔽個資時記錄相關LOG
	var q = "code=DRG02";
	q +="&methodName=print";
	q +="&db=Drg4001Db";
	q +="&hql=select id,applNo,notifierUserID,notifierName from Drg4001Db where id = " + form1.id.value;

	
	prop=prop+"width=800,height=100,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "tcbw/conin/conin1001f.jsp?" + q,"",prop);
	
}

</script>
</head>
<body onLoad="beforeInit();whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post"  autocomplete="off">
<iframe name="saveContainerFrame" width="0" height="0" frameborder="0">
</iframe>

<!--Query區============================================================-->
<div id="queryContainerMain" style="display:none">
<% request.setAttribute("qBean", obj); %>
<jsp:include page="drgin0301q.jsp" />
<% request.setAttribute("qBean", objQry03); %>
<jsp:include page="drgin0303q.jsp" />
<% request.setAttribute("qBean", objQry04); %>
<jsp:include page="drgin0304q.jsp" />
<% request.setAttribute("qBean", objQry08); %>
<jsp:include page="drgin0308q.jsp" />
<% request.setAttribute("qBean", objQry09); %>
<jsp:include page="drgin0309q.jsp" />
</div>
<div id="queryContainer" style="width:300px;height:100px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<table class="queryTable" width="100%">
	<tr>
		<td class="td_form">許可證字號：</td>
		<td class="td_form_white">
			<select name="q_permitKeyNo" class="field_Q">
				<%=View.getOption("select dr.permitKey+dr.permitNo, c.codeName+dr.permitNo from Drg4003Db dr , CommonCode c where dr.permitKey = c.codeId and c.commonCodeKind.codeKindId = 'DRGPKID' and dr.drg4001Db.id = "+obj.getId(),"") %>
			</select>	
		</td>
	</tr> 
	<tr>
		<td class="queryTDInput" colspan="2" style="text-align:center;">
			<input class="toolbar_default" followPK="false" type="button" name="querySubmit" value="確　　定" onClick="toShowMLMS()">
			<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="whatButtonFireEvent(this.name)">
		</td>
	</tr>
	</table>
</div>

<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr>
    <td nowrap class="bgToolbar">
    <input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="actionType" value="<%=obj.getActionType()%>">
	<input type="hidden" name="status" value="<%=obj.getStatus()%>">
	<input type="hidden" name="chargeMan" value="<%=obj.getChargeMan()%>">
	<input type="hidden" name="q_isComplete" value="<%=obj.getQ_isComplete()%>">
	<input type="hidden" name="caseType" value="<%=obj.getCaseType()%>">
	<input type="hidden" name="drg45Id" value="<%=obj.getDrg45Id()%>">
	<input type="hidden" name="formType" value="<%=obj.getFormType()%>">
	<input type="hidden" class="field_Q" name="tabId" value ="<%=obj.getTabId()%>">
	<input type="hidden" name="isCloseUserInfo" value="<%=obj.getIsCloseUserInfo()%>">
	<input type="hidden" class="field_Q" name="q_gradeDate" value ="<%=obj.getQ_gradeDate()%>">
	
    <jsp:include page="../../home/toolbar.jsp">
	   <jsp:param value="N" name="btnCopy"/>
    </jsp:include>
    <span id="spanMaintain0">
		<input class="toolbar_default" type="button" followPK="false" name="maintain0" value="暫　存" onClick="onClickButton(0);">&nbsp;
	</span>
    <% if("10".equals(obj.getStatus()) || "11".equals(obj.getStatus())){ %>
	    <span id="spanMaintain1">
	    <!--  
		   <input class="toolbar_default" type="button" followPK="false" name="maintain1" value="受 　理" onClick="getEmail('DRG020002','window.opener.onClickButton(1)','N');">&nbsp;
		-->
		   <input class="toolbar_default" type="button" followPK="false" name="maintain1" value="受 　理" onClick="onClickButton(1)">&nbsp;
	    </span>
	    <span id="spanMaintain2">
		   <input class="toolbar_default" type="button" followPK="false" name="maintain2" value="退　件" onClick="getEmail('DRG020002','window.opener.onClickButton(2)','N');">&nbsp;
	    </span>
	    <span id="spanMaintain3">
	       <input class="toolbar_default" type="button" followPK="false"  name="maintain3" value="撤　案" onClick="getEmail('DRG020003','window.opener.onClickButton(3)','N');">&nbsp;
	    </span>
	<%}%>
	<% if("20".equals(obj.getStatus()) || "22".equals(obj.getStatus()) || "23".equals(obj.getStatus())){ %>
		<span id="spanMaintain7">
		   <input class="toolbar_default" type="button" followPK="false" name="maintain7" value="案 件 分 級" onClick="onClickButton(7)">&nbsp;
	    </span> 
	    <span id="spanMaintain8">
		   <input class="toolbar_default" type="button" followPK="false" name="maintain8" value="初 評 完 成" onClick="onClickButton(8);">&nbsp;
	    </span>
	    <span id="spanMaintain9">
		   <input class="toolbar_default" type="button" followPK="false" name="maintain9" value="退 件 補 件" onClick="getEmail('DRG020005','window.opener.onClickButton(9)','N');">&nbsp;
	    </span>
	    <span id="spanMaintain10">
		   <input class="toolbar_default" type="button" followPK="false" name="maintain10" value="通 知 補 件" onClick="getEmail('DRG020006','window.opener.onClickButton(10)','Y');">&nbsp;
	    </span>
	<% } %>
	<% if("30".equals(obj.getStatus()) || "50".equals(obj.getStatus())){ %>
		<span id="spanMaintain11">
		   <input class="toolbar_default" type="button" followPK="false" name="maintain11" value="案 件 評 估" onClick="onClickButton(11)">&nbsp;
	    </span> 
	<% } %>
	<% if("30".equals(obj.getStatus())){ %>
	    <span id="spanMaintain12">
		   <input class="toolbar_default" type="button" followPK="false" name="maintain12" value="維 護 諮 議 會" onClick="popDRG4008View()">&nbsp;
	    </span>
	    <span id="spanMaintain13">
		   <input class="toolbar_default" type="button" followPK="false" name="maintain13" value="取 消 提 報" onClick="onClickButton(13)">&nbsp;
	    </span>
	<% } %>
	<% if("40".equals(obj.getStatus()) || "50".equals(obj.getStatus()) || "90".equals(obj.getStatus())){ %>
		<span id="spanMaintain14">
		   <input class="toolbar_default" type="button" followPK="false" name="maintain14" value="檢 視 廠 商 回 覆 資 料" onClick="popDRG4006View('')">&nbsp;
	    </span>
	<% } %>
	<% if("50".equals(obj.getStatus())){ %>
	    <span id="spanMaintain18">
		   <input class="toolbar_default" type="button" followPK="false" name="maintain18" value="廠 商 補 件" onClick="getEmail('DRG020008','window.opener.onClickButton(18)','N');">&nbsp;
	    </span>
	<% } %>
	<% if("10".equals(obj.getStatus()) || "11".equals(obj.getStatus()) || "20".equals(obj.getStatus()) || "22".equals(obj.getStatus()) || "23".equals(obj.getStatus()) || "30".equals(obj.getStatus())|| "50".equals(obj.getStatus())){ %>
		<span id="spanMaintain4">
			   <input class="toolbar_default" type="button" followPK="false" name="maintain4" value="案 件 改 派" onClick="onClickButton(4)">&nbsp;
		</span>
	<% } %>
	<span id="spanMaintain5">
		<input class="toolbar_default" type="button" followPK="false" name="maintain5" value="檢 視 藥 證 資 料" onClick="choesePermitKeyNo()">&nbsp;
	</span>
	<span id="spanMaintain6">
		<input class="toolbar_default" type="button" followPK="false" name="maintain6" value="返 回 查 詢" onClick="onClickButton(6)">&nbsp;
	</span>
	</td>	
</tr>
<tr>
	<td class="bgToolbar">
		<span id="spanQuery3">
			<input class="toolbar_default" type="button" followPK="false" name="btnQuery3" value="案件原始版本" onClick="popDrg6001('<%=obj.getId()%>');">&nbsp;
		</span>
		<span id="spanQuery4">
			<input class="toolbar_default" type="button" followPK="false" name="btnQuery4" value="案件補件紀錄" onClick="popCon0004('<%=obj.getApplNo()%>','DRG')">&nbsp;
		</span>
		<span id="spanQuery5">
			<input class="toolbar_default" type="button" followPK="false" name="btnQuery5" value="郵件清單紀錄" onClick="popCon0002('DRG2','<%=obj.getId()%>');">&nbsp;
		</span>
		<span id="spanQuery6">
			<input class="toolbar_default" type="button" followPK="false" name="btnQuery6" value="案件流程紀錄" onClick="popCon2001('DRG2','<%=obj.getId()%>')">
		</span>	
		<span id="spanReport">
	   		<input class="toolbar_default" type="button" followPK="false" name="report" value="案件列印" onClick="whatButtonFireEvent(this.name)">&nbsp;
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

<table width="100%" cellspacing="0" cellpadding="0">
	<tr><td>
	<TABLE CELLPADDING=0 CELLSPACING=0>
		<TR>
			<td nowrap ID="t1" CLASS="tab_border1" ><a id="aTab1" href="#" onClick="changeTab(1);">基本資料</a></td>
		    <td nowrap ID="t2" CLASS="tab_border2" width="5%"><a id="aTab2" href="#" onClick="changeTab(2);">療效不等反應</a></td>
		    <td nowrap ID="t3" CLASS="tab_border2" width="5%"><a id="aTab3" href="#" onClick="changeTab(3);">使用藥品</a></td>
		    <td nowrap ID="t10" CLASS="tab_border2" width="5%"><a id="aTab10" href="#" onClick="changeTab(10);">其他附件</a></td>
		</TR>
		<TR>
			<% if("20".equals(obj.getStatus()) || "22".equals(obj.getStatus()) || "23".equals(obj.getStatus()) || "30".equals(obj.getStatus()) || "40".equals(obj.getStatus()) || "50".equals(obj.getStatus()) || "90".equals(obj.getStatus())){ %>	
		    	<td nowrap ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">案件初評表</a></td>
		    <% }%>
		    <% if("30".equals(obj.getStatus()) || "40".equals(obj.getStatus()) || "50".equals(obj.getStatus()) || "90".equals(obj.getStatus())){ %>	
		    	<td nowrap ID="t5" CLASS="tab_border2" width="5%"><a id="aTab5" href="#" onClick="changeTab(5);">諮議會評估</a></td>
		    	<td nowrap ID="t6" CLASS="tab_border2" width="5%"><a id="aTab6" href="#" onClick="changeTab(6);">醫院調查</a></td>
		    <% }%>
		    <% if("40".equals(obj.getStatus()) || "50".equals(obj.getStatus()) || "90".equals(obj.getStatus())){ %>
		   		<td nowrap ID="t7" CLASS="tab_border2" width="5%"><a id="aTab7" href="#" onClick="changeTab(7);">廠商回覆</a></td>
		    	<td nowrap ID="t8" CLASS="tab_border2" width="5%"><a id="aTab8" href="#" onClick="changeTab(8);">品質調查評估</a></td>
		    <% }%>
		    <td nowrap ID="t9" CLASS="tab_border2" width="5%"><a id="aTab9" href="#" onClick="changeTab(9);">歷史通報</a></td>
		</TR>
	</TABLE>
	</td></tr>
</table>
<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
    <td nowrap class="bg">	
    <div id="formContainer" style="height:auto">
	<table id="Tab1" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form_left" colspan="4">通報訊息</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="15%">登錄編號</td>
			<td nowrap class="td_form_white" colspan="3">
                <input class="field_RO" type="text" name="applNo" value="<%=obj.getApplNo()%>">(由通報中心填寫)
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">發現日期</td>
			<td nowrap class="td_form_white">
                <%=View.getPopCalendar("field","occurDate",obj.getOccurDate())%>
			</td>
			<td nowrap class="td_form">通報中心接獲通報日期</td>
			<td nowrap class="td_form_white">
                <%=View.getPopCalendar("field","notifierRevDate",obj.getNotifierRevDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >通報者獲知日期</td>
			<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field","notifierRepDate",obj.getNotifierRepDate())%>
			</td>
			<td nowrap class="td_form" >收案日期</td>
			<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field_RO","enrolledDate",obj.getEnrolledDate())%>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form" >通報來源</td>
			<td nowrap class="td_form_white"  colspan="3">
				<%=View.getRadioBoxOption("field", "notifierSource", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGNFS' order by codeId", obj.getNotifierSource())%>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">
				通報者資訊
				<input class="field_Q" id="notifier" type="checkBox" name="notifier" value="Y" onclick="showNotifierData()"/>顯示個人資料
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">姓名</td>
			<td nowrap class="td_form_white">
				<input class="field_RO" type="hidden" name="notifierUserID" maxlength="50" size="15" value="<%=obj.getNotifierUserID()%>"/>
				<input class="field_RO" type="text" name="notifierName" maxlength="10" size="15" value=""/>
			</td>
			<td nowrap class="td_form">服務機構</td>
			<td nowrap class="td_form_white">
				<input class="field" type="hidden" name="notifierDeptID" size="10" maxlength="10" value="<%=obj.getNotifierDeptID()%>" />
	  	        <input class="field_RO" type="text" name="notifierDept" size="30" maxlength="50"  value="" />
			</td>			
		</tr>		
		<tr>
			<td nowrap class="td_form">電話</td>
			<td nowrap class="td_form_white">
				 ( <input class="field_RO" type="text" name="notifierTelArea" size="2" maxlength="2" value=""> )
			    - <input class="field_RO" type="text" name="notifierTel" size="10" maxlength="10" value="">
			    # <input class="field_RO" type="text" name="notifierTelExt" size="3" maxlength="3" value="">
			</td>
			<td nowrap class="td_form">E-Mail</td>
			<td nowrap class="td_form_white" >
				<input class="field_RO" type="text" name="notifierEmail" maxlength="30" size="20" value=""/>
			</td>				
		</tr>
		<tr>
			<td nowrap class="td_form">手機</td>
			<td nowrap class="td_form_white">
				<input class="field_RO" type="text" name="notifierPhone" maxlength="10" size="10" value=""/>
			</td>
			<td nowrap class="td_form">傳真</td>
			<td nowrap class="td_form_white" >
				( <input class="field_RO" type="text" name="notifierFaxArea" size="2" maxlength="2" value=""> )
			    - <input class="field_RO" type="text" name="notifierFax" size="10" maxlength="10" value="">
			</td>				
		</tr>	
		<tr>
			<td nowrap class="td_form">地址</td>
			<td nowrap class="td_form_white" colspan="3" >
				<select class="field_RO" name="notifierCounty" onChange="popZipCode(this,notifierZipCode);">
			       <%=View.getOptionCodeKind("CTY", obj.getNotifierCounty())%>
			    </select>
			    <select class="field_RO" name="notifierZipCode">
				   <%=View.getOptionCon1002(obj.getNotifierZipCode())%>
			    </select>
				<input class="field_RO" type="text" name="notifierAddress" maxlength="100" size="100" value=""/>
			</td>
		</tr>
        <tr>
			<td nowrap class="td_form">職稱</td>
			<td nowrap class="td_form_white">
				<%=View.getRadioBoxOption("field_RO", "notifierTitle", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='TITLE' order by codeId", obj.getNotifierTitle())%>			
			</td>
			<td nowrap class="td_form">屬性</td>
			<td nowrap class="td_form_white">
				<%=View.getRadioBoxOption("field_RO", "notifierType", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONNFT1' order by codeId", obj.getNotifierType(),"chgNotifierType(this.value);")%>			
			</td>
		</tr>	
	    <tr>
			<td nowrap class="td_form_left" colspan="4">病人相關資料</td>
		</tr>
		<tr>
			<td nowrap class="td_form">識別代碼</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="patientId"  size="10" maxlength="10" value="<%=obj.getPatientId()%>"/>
			</td>
			<td nowrap class="td_form">性別</td>
			<td nowrap class="td_form_white">
				<%=View.getRadioBoxTextOption("field","patientSex","M;男;F;女",obj.getPatientSex())%>
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form">出生日期</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getPopCalendar("field","patientBirth",obj.getPatientBirth())%>
				年齡：<input class="field" type="text" name="patientAge"  size="5" maxlength="3" value="<%=obj.getPatientAge()%>"/>
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form">身高</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="patientHeight" size="5" maxlength="3" value="<%=obj.getPatientHeight()%>"/>公分
			</td>
			<td nowrap class="td_form">體重</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="patientWeight" size="5" maxlength="3" value="<%=obj.getPatientWeight()%>"/>公斤
			</td>				
		</tr>
	</table>	
	<table id="Tab2" width="100%" align="center" class="table_form">
		<tr>		
			<td nowrap class="td_form" >通報事件之後果</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="checkbox" name="conSequence" value="1" <%if(!"".equals(Common.get(obj.getConSequence()))){for(String con:obj.getConSequence())if("1".equals(con))out.print("checked");}else{out.print("");}%> onclick="chgConSequence();">藥效改變&nbsp;&nbsp;--
				    <%=View.getRadioBoxTextOption("field","effectChangeDesc","1;增強;2;減弱",obj.getEffectChangeDesc())%><br>
				<input class="field" type="checkbox" name="conSequence" value="2" <%if(!"".equals(Common.get(obj.getConSequence()))){for(String con:obj.getConSequence())if("2".equals(con))out.print("checked");}else{out.print("");}%> onclick="chgConSequence();">不良反應發生、強度增強或頻率增加<br>
				    &nbsp;&nbsp;&nbsp;&nbsp;不良反應等級：<%=View.getRadioBoxOption("field", "badReactionLev", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0308 ' order by codeId", obj.getBadReactionLev())%><br>
				    &nbsp;&nbsp;&nbsp;&nbsp;不良反應狀況：<input class="field" type="text" name="badReactionDesc" size="50" maxlength="50" value="<%=obj.getBadReactionDesc()%>"/>	
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">通報事件之描述</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >事件前</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="beforeDesc" cols="95" rows="3" class="field"><%=obj.getBeforeDesc()%></textarea>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >藥品轉換</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="changeDesc" cols="95" rows="3" class="field"><%=obj.getChangeDesc()%></textarea>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >發生事件</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="occurDesc" cols="95" rows="3" class="field"><%=obj.getOccurDesc()%></textarea>
			</td>
		</tr>
	    <tr>
			<td nowrap class="td_form" >事件後</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="afterDesc" cols="95" rows="3" class="field"><%=obj.getAfterDesc()%></textarea>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">其他相關檢查及檢驗數據資訊(請附日期)</td>
		</tr>
		<tr>
			<td nowrap class="td_form">相關檢查,檢驗數據<br>及其他資料</td>
			<td nowrap class="td_form_white" colspan="3">
               <jsp:include page="drgin0342Layout.jsp" />
		    </td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">其他相關資料(例如：診斷、過敏、懷孕、吸菸、喝酒、習慣、其他疾病、肝/腎功能不全...等)</td>
		</tr>
	    <tr>
			<td nowrap class="td_form" >請描述</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="otherDesc" cols="95" rows="3" class="field"><%=obj.getOtherDesc()%></textarea>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">其他資訊</td>
		</tr>
		<tr>
			<td nowrap class="td_form">發生事件後之處置</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getRadioBoxOption("field", "dealWith", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0301' order by codeId",obj.getDealWith())%>
				(請描述)：<input class="field" type="text" name="dealWithOther" size="30" maxlength="50" value="<%=obj.getDealWithOther()%>"/>			
			</td>				
		</tr>
		<tr>
			<td nowrap class="td_form">病人恢復原藥或轉換同成分藥品<br>其症狀是否改善</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getRadioBoxTextOption("field","isImproveYn","Y;是;N;否;0;未知",obj.getIsImproveYn())%>		
			</td>				
		</tr>
		<tr>
			<td nowrap class="td_form">提供聯絡資訊供廠商後續調查評估</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getRadioBoxTextOption("field","isContactYn","Y;是;N;否",obj.getIsContactYn())%>		
			</td>				
		</tr>
		<tr>
			<td nowrap class="td_form">醫師對換藥的態度</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getRadioBoxOption("field", "dressingAttitude", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0302' order by codeId",obj.getDressingAttitude())%>		
			</td>				
		</tr>
		<tr>
			<td nowrap class="td_form">病人服藥順從性</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getRadioBoxOption("field", "obedienceLev", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0303' order by codeId",obj.getObedienceLev())%>		
			</td>				
		</tr>
	</table>
	<table id="Tab3" class="table_form" width="100%" border="0" cellpadding="0" cellspacing="0">
		<%for(CommonCode code : list){%>
			<tr><td class="bg">
				<jsp:include page="drgin0343Layout.jsp">
					 <jsp:param name="type" value="<%=code.getCodeId()%>" />
				</jsp:include>
			</td></tr>
		<% }%>
	</table>
	<table id="Tab10" width="100%" align="center" class="table_form">
		<% if("10".equals(obj.getStatus()) || "11".equals(obj.getStatus()) || "20".equals(obj.getStatus()) || "22".equals(obj.getStatus()) || "23".equals(obj.getStatus()) || "30".equals(obj.getStatus())|| "50".equals(obj.getStatus())){ %>
			<tr><td class="bgToolbar">
				<span id="spanMaintain19">
					<input class="toolbar_default" type="button" followPK="false" name="maintain19" value="附 件 上 傳" onClick="upload()">&nbsp;
				</span>
			</td></tr>
		<% } %>
		<tr><td class="bg">
			<jsp:include page="conin01Layout.jsp"/>
		</td></tr>
	</table>
	<table id="Tab4" class="table_form" width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr> 
			<td nowrap class="td_form">藥品成分資訊</td>
			<td nowrap class="td_form_white">
				<input class="field" type="checkbox" name="medNti" value="Y" <%=obj.getMedNti().equals("Y")?"checked":""%> >NTI Drugs<br>
				藥理治療分類(ATC code)：<input class="field" type="text" name="medAtcCode" size="20" maxlength="50" value="<%=obj.getMedAtcCode()%>">
			</td>
		</tr>
		<tr>		
			<td nowrap class="td_form" >通報事件之後果</td>
			<td nowrap class="td_form_white">
				<input class="field" type="checkbox" name="conSequence4" value="1" <%if(!"".equals(Common.get(obj.getConSequence4()))){for(String con:obj.getConSequence4())if("1".equals(con))out.print("checked");}else{out.print("");}%> onclick="chgConSequence4();showAssess();">藥效改變--
				    <%=View.getRadioBoxTextOption("field","effectChangeDesc4","1;增強;2;減弱",obj.getEffectChangeDesc4())%><br>
				<input class="field" type="checkbox" name="conSequence4" value="2" <%if(!"".equals(Common.get(obj.getConSequence4()))){for(String con:obj.getConSequence4())if("2".equals(con))out.print("checked");}else{out.print("");}%> onClick="chgConSequence4();showAssess();">不良反應發生、強度增強或頻率增加<br>
				不良反應等級：<%=View.getRadioBoxOption("field", "badReactionLev4", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0308 ' order by codeId", obj.getBadReactionLev4())%><br>
				不良反應狀況：<input class="field" type="text" name="badReactionDesc4" size="50" maxlength="50" value="<%=obj.getBadReactionDesc4()%>"/><br>
				MedDRA coding：<input class="field" type="text" name="badReactionDra" size="10" maxlength="10" value="<%=obj.getBadReactionDra()%>"/>	
			</td>	
		</tr>
		<tr>		
			<td nowrap class="td_form">相關性評估</td>
			<td nowrap class="td_form_white">
				<span id="assessShow1" style="display:none">
					轉換藥品與藥效不等發生有合理的時序性?<%=View.getRadioBoxTextOption("field","assessEC1","Y;是;N;否;0;未知",obj.getAssessEC1())%><br>
					藥效不等與病人本身疾病、生理狀態或併用藥物有關?<%=View.getRadioBoxTextOption("field","assessEC2","Y;是;N;否;0;未知",obj.getAssessEC2())%><br>
					新藥調整劑量或換回舊藥後維持先前藥效?<%=View.getRadioBoxTextOption("field","assessEC3","Y;是;N;否;0;未知",obj.getAssessEC3())%><br>
				</span>
				<span id="assessShow2" style="display:none">
					轉換藥品與ADR發生有合理的時序性?<%=View.getRadioBoxTextOption("field","assessBR1","Y;是;N;否;0;未知",obj.getAssessBR1())%><br>
					ADR與病人本身疾病、生理狀態或併用藥物有關?<%=View.getRadioBoxTextOption("field","assessBR2","Y;是;N;否;0;未知",obj.getAssessBR2())%><br>
					停藥後ADR減輕或消失?<%=View.getRadioBoxTextOption("field","assessBR3","Y;是;N;否;0;未知",obj.getAssessBR3())%><br>
				</span>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >療效不等評估結果</td>
			<td nowrap class="td_form_white">
				<%=View.getRadioBoxOption("field", "assessResult", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG2RKL' order by codeId", obj.getAssessResult())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >通報時效</td>
			<td nowrap class="td_form_white">
				間隔天數：<input class="field" type="text" name="intervalDays" size="3" value="<%=obj.getIntervalDays()%>">天&nbsp;&nbsp;&nbsp;&nbsp;
				<%=View.getRadioBoxTextOption("field","notifierAging","1;時效佳;2;時效待加強;",obj.getNotifierAging())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >通報品質</td>
			<td nowrap class="td_form_white">
                 <%=View.getRadioBoxTextOption("field", "notifierQuality", "1;Excellent;2;Good;3;Fair;",obj.getNotifierQuality())%>			
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">提報諮議會</td>
			<td nowrap class="td_form_white">
				<input class="field" type="checkbox" name="isCouncilYn" value="Y" <%=obj.getIsCouncilYn().equals("Y")?"checked":""%> >
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >歷史通報資料摘要(不含本案)</td>
			<td nowrap class="td_form_white">
				歷年本藥品之通報件數：<%=obj.getCaseHCount()%>件
				<br>歷年本藥品Possible以上之通報件數：<%=obj.getCaseHPCount()%>件
				<br>一年內本藥品之通報件數：<%=obj.getCaseHYCount()%>件
				<br>一年內本藥品Possible以上之通報件數：<%=obj.getCaseHPYCount()%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >備註</td>
			<td nowrap class="td_form_white">
				<textarea class="field"  name="remark" cols="120" rows="2" ><%=obj.getRemark()%></textarea>
			</td>
		</tr>
	</table>
	<table id="Tab5" class="table_form" width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td nowrap class="bgList" colspan="5">
				<div id="listContainer" style="height:auto">
				<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
					<thead id="listTHEAD">
					<tr>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">NO</a></th>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">初評日期</a></th>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">評估結果</a></th>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">備註</a></th>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">明細</a></th>
					</tr>
					</thead>
					<tbody id="listTBODY">
					<%
					    boolean primaryArray[] = {true,false,false,false,false};
					    boolean displayArray[] = {false,true,true,true,true};
					    out.write(View.getQuerylist(primaryArray,displayArray,null,objList48,obj.getQueryAllFlag(),true,null,null,"",false));
					%>
					</tbody>
				</table>
				</div>
			</td>
		</tr>
	</table>
	<table id="Tab6" class="table_form" width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td nowrap class="bgList" colspan="5">
				<div id="listContainer" style="height:auto">
				<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
					<thead id="listTHEAD">
					<tr>
						<th class="listTH">No.</th>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">成份</a></th>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">有療效不等狀況</a></th>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">有更換廠牌，無療效不等狀況</a></th>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">未更換廠牌</a></th>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">回覆醫院數</a></th>
					</tr>
					</thead>
					<tbody id="listTBODY">
					<%
					    boolean primaryArray49[] = {false,false,false,false,false,false};
					    boolean displayArray49[] = {false,true,true,true,true,true};
					    out.write(View.getQuerylist(primaryArray49,displayArray49,null,objList49,obj.getQueryAllFlag(),true,null,null,"",false));
					%>
					</tbody>
				</table>
				</div>
			</td>
		</tr>
	</table>
	<table id="Tab7" class="table_form" width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td nowrap class="bgList" colspan="5">
				<div id="listContainer" style="height:auto">
				<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
					<thead id="listTHEAD">
					<tr>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">NO</a></th>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">回覆日期</a></th>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">製程管制與最終產品之結果檢討</a></th>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">原料(含來源)/包材或配方變更之檢討</a></th>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">明細</a></th>
					</tr>
					</thead>
					<tbody id="listTBODY">
					<%
					    boolean primaryArray46[] = {true,false,false,false,false};
					    boolean displayArray46[] = {false,true,true,true,true};
					    out.write(View.getQuerylist(primaryArray46,displayArray46,null,objList46,obj.getQueryAllFlag(),true,null,null,"",false));
					%>
					</tbody>
				</table>
				</div>
			</td>
		</tr>
	</table>
	<table id="Tab8" class="table_form" width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td nowrap class="bgList" colspan="5">
				<div id="listContainer" style="height:auto">
				<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
					<thead id="listTHEAD">
					<tr>
						<th class="listTH">No.</th>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">許可證字號</a></th>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">評估日期</a></th>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">評估意見</a></th>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">明細</a></th>
					</tr>
					</thead>
					<tbody id="listTBODY">
					<%
					    boolean primaryArray47[] = {true,false,false,false,false};
					    boolean displayArray47[] = {false,true,true,true,true};
					    out.write(View.getQuerylist(primaryArray47,displayArray47,null,objList47,obj.getQueryAllFlag(),true,null,null,"",false));
					%>
					</tbody>
				</table>
				</div>
			</td>
		</tr>
	</table>
	<table id="Tab9" class="table_form" width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr><td nowrap class="bgToolbar" style="text-align:left">
			<span id="spanQuery1">
				<input class="toolbar_default" type="button" followPK="false" name="btnQuery1" value="篩 選" onClick="doQueryOne1()">&nbsp;
			</span>
			<span id="spanQuery2">
				<input class="toolbar_default" type="button" followPK="false" name="btnQuery2" value="匯出EXCEL" onClick="popReportDrg41Form()">&nbsp;
			</span>
		</td></tr>
		<tr>
			<td class="td_form">通報日期：</td>
			<td class="td_form_white">
				<%=View.getPopCalendar("field_Q","q_notifierRepDate41S",obj.getQ_notifierRepDate41S())%>~<%=View.getPopCalendar("field_Q","q_notifierRepDate41E",obj.getQ_notifierRepDate41E())%>		
			</td>
		</tr>
		<tr>
			<td class="td_form">通報單位：</td>
			<td class="td_form_white">
				<input class="field_Q" type="text" name="q_notifierDept41" size="25" maxlength="50" value="<%=obj.getQ_notifierDept41()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">通報事件後果：</td>
			<td nowrap class="td_form_white">
				<select class="field_Q" name="q_conSequence41" >
					<%=View.getTextOption("1;藥效改變;2;不良反應發生、強度增強或頻率增加;", obj.getQ_conSequence41(), 1)%>
				</select>
			</td>
		</tr>
		<tr>
			<td nowrap class="bgList" colspan="4">
				<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
					<thead id="listTHEAD">
					<tr>
						<th class="listTH" rowspan="2"><a class="text_link_w"><input type="checkbox" id="cbAll" name="cbAll" class="field_Q"></a></th>
						<th class="listTH" rowspan="2"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">通報日期</a></th>
						<th class="listTH" rowspan="2"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">案件編號</a></th>
						<th class="listTH" colspan="4">懷疑療效不等藥品</th>
						<th class="listTH" colspan="3">事件前使用藥品</th>
						<th class="listTH" rowspan="2"><a class="text_link_w" onclick="return sortTable('listTBODY',10,false);" href="#">通報事件後果</a></th>
						<th class="listTH" rowspan="2"><a class="text_link_w" onclick="return sortTable('listTBODY',11,false);" href="#">發生事情後之處理</a></th>
						<th class="listTH" rowspan="2"><a class="text_link_w" onclick="return sortTable('listTBODY',12,false);" href="#">療效不等評估結果</a></th>
						<th class="listTH" rowspan="2"><a class="text_link_w" onclick="return sortTable('listTBODY',13,false);" href="#">通報單位</a></th>
						<th class="listTH" rowspan="2"><a class="text_link_w" onclick="return sortTable('listTBODY',14,false);" href="#">附件</a></th>
					</tr>
					<tr>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">許可證字號</a></th>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">商品名</a></th>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">製造廠</a></th>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">製造批號</a></th>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">許可證字號</a></th>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',8,false);" href="#">商品名</a></th>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',9,false);" href="#">製造廠</a></th>
						
					</tr>
					</thead>
					<tbody id="listTBODY">
					<%
					    boolean primaryArray41[] = {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
					    boolean displayArray41[] = {false,true,true,true,true,true,true,true,true,true,true,true,true,true,true};
					    out.write(View.getCheckboxQuerylist(primaryArray41,displayArray41,null,objList41,obj.getQueryAllFlag(),"id41s",null,null,"field_Q", -1, false, false));
					%>
					</tbody>
				</table>
			</td>
		</tr>
	</table>
</div>
</table>
</form>
<script type="text/javascript">
var cb = new cbToggle('cb',document.form1,form1.cbAll,'id41s');
cb.config.cssTopLevel = true;
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName)	{
		case "update":
			setFormItem("maintain0,maintain1,maintain2,maintain3,maintain8,maintain19","O");
		break;
		//案件列印-詢問是否遮蔽個資	  
		  case "report":
			  toAsk();
			  break;
	}
}
</script>
</body>
</html>

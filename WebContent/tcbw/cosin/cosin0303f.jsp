<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="COSIN0301" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN0302F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
String q_id = ESAPI.encoder().encodeForHTML(Common.get(request.getParameter("v")));
if(!"".equals(Common.get(q_id))) obj.setId(q_id);

if("insert".equals(obj.getDoType()) && "queryOne".equals(obj.getState())){						
	try{
		obj.doInsert();
	}catch(Exception e){
		e.printStackTrace();
	}
}
if("pauseSave".equals(obj.getState()) || "doSend".equals(obj.getState())){
	try{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doUpdateType();
	}catch(Exception e){
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}else if("doCancelQuit".equals(obj.getState())){
	try{
		obj.doDelete();
		obj.setErrorMsg("放棄資料完成");
		obj.setIsNeedBackQuery("Y");
		obj.setId("");
	}catch(Exception e){
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.cosin.COSIN0302F)obj.queryOne();
%>
<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../cosex/cosex0100f.js"></script>
<script type="text/javascript">
var popWinName = null;
function checkField(){
	var alertStr = "";
	alertStr += checkLen(form1.useMethod, "使用方法", 500);
	alertStr += checkLen(form1.otherExplain, "說明", 100);
	if(form1.nonSeriousOther != null){
		alertStr += checkLen(form1.nonSeriousOther, "不良反應症狀", 100);
	}
	if(form1.nonSeriousDis != null){
		alertStr += checkLen(form1.nonSeriousDis, "不良反應描述", 100);
	}
	alertStr += validateTabFileC();
	alertStr += validateTabFileCOSSD();
	alertStr += validateTabFileCOSDP();
	alertStr += validateTabFileCOSID();
	
	if(form1.state.value == "doSend"){
		<%=TCBWCommon.getCheckFiled("COS01", "COS01")%>
		<%=TCBWCommon.getCheckFiled("COS01", "COS02")%>
		
		var flag1 = false;
		var flag2 = false;
		$(":checkbox[name=showCosType]").each(function(index){
			if($(this).attr("checked")){
				if(index == 0){
					flag1 = true;
				}else{
					flag2 = true;
				}
			}
		});
		
		if(flag1){
			<%=TCBWCommon.getCheckFiled("COS01", "COS03")%>
		}
		if(flag2){
			<%=TCBWCommon.getCheckFiled("COS01", "COS04")%>
		}
		
		alertStr += validateCOS4004DbTable();					// 相關檢查及檢驗數據
		alertStr += validateCOS4005DbTable();					// 併用的其他化粧品
	}
	
	if(alertStr.length > 0){
		alert(alertStr);	return;
	}
	$("input[name=otherDescribe]").each(function(){
		$(this).attr("disabled", false);
	});
	
	beforeSubmit();
	form1.submit();
}

function init(){
	<%if(!"".equals(Common.get(q_id))){%>
	setDisplayItem("spanCaseReply,spanPauseSave,spanDoSend,spanDoQuit,spanCancelQuit","H");
	<%}%>
	
	if("<%=obj.getIsNeedBackQuery()%>" == "N" || "<%=obj.getIsNeedBackQuery()%>" == ""){
		if("<%=obj.getErrorMsg()%>" != ""){
			alert("<%=obj.getErrorMsg()%>");
		}
		if("<%=obj.getStatus()%>" >= "10" || "<%=obj.getStatus()%>" == "02"){
			setAllReadonly();
			setFormItem("pauseSave,stayedUpload,doCancelQuit,doSend","R");
		}else{
			setAllOpen();
			if("<%=obj.getStatus()%>" == "01"){
				setFormItem("stayedUpload","R");
			}
			if("<%=obj.getStatus()%>" > "01"){
				setFormItem("doCancelQuit","R");
			}
		}
		chNotifierSource("<%=obj.getNotifierSource()%>");
		chTraffickWay("<%=obj.getTraffickWay()%>");
		chIsOtherDeptYn("<%=obj.getIsOtherDeptYn()%>");
		<%--
		chAdverseCondition("<%=obj.getAdverseCondition()%>");
		--%>
		chEvenContactYn("<%=obj.getEvenContactYn()%>");
		chDealWith("<%=obj.getDealWith()%>");
		if("<%=obj.getCosType()%>" == "1" || "<%=obj.getCosType()%>" == "3"){
			chIsDamageYn("<%=obj.getIsDamageYn()%>");
		}
		chgSubCode();
		<%=TCBWCommon.getIsComplete("COS01")%>
		
	}else{
		alert("<%=obj.getErrorMsg()%>");
		form1.action = "cosin0301f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
	}

}


// 是否已與廠商接觸過
function chEvenContactYn(val){
	if(val == "Y"){
		$("tr[id=dealWith]").show();
	}else{
		for(var i=0; i<form1.dealWith.length; i++){
			form1.dealWith[i].checked = false;
		}
		$("tr[id=dealWith]").hide();
		
		for(var i=0; i<form1.isRecurrenceYn.length; i++){
			form1.isRecurrenceYn[i].checked = false;
		}
		$("tr[id=isRecurrenceYn]").hide();
	}
}

// 處理後是否再度發生
function chDealWith(val){
	if(val=="O" || val=="A"){
		$("tr[id=isRecurrenceYn]").show();
	}else{
		for(var i=0; i<form1.isRecurrenceYn.length; i++){
			form1.isRecurrenceYn[i].checked = false;
		}
		$("tr[id=isRecurrenceYn]").hide();
	}
}

// 不良品時，變動此欄位時，顯示或隱藏
function chIsDamageYn(val){		
	if(val=="Y" || val=="O"){
		if(val == "Y"){
			form1.otherInformation.disabled = false;
			form1.otherIsDamageYn.value = "";
			form1.otherIsDamageYn.disabled = true;
		}
		if(val == "O"){
			form1.otherIsDamageYn.disabled = false;
			form1.otherInformation.value = "";
			form1.otherInformation.disabled = true;
		}
		if("<%=obj.getCosType()%>" != "3"){
			form1.showCosType[1].checked = true;
			$("#t4").show();
		}
	}else{
	//	form1.otherExplain.value = "";
		form1.otherIsDamageYn.value = "";
		form1.otherIsDamageYn.disabled = true;
		form1.otherInformation.value = "";
		form1.otherInformation.disabled = true;
		if("<%=obj.getCosType()%>" != "3"){
			form1.showCosType[1].checked = false;
			$("#t4").hide();
		}
	}
}

function goBackQuery(){
	form1.action = "cosin0301f.jsp";
	form1.state.value = "queryAll";
	form1.submit();
}

function getNotifierInfo(){
	openCenterWindow(800, 500, '../hfrin/hfrin0411f.jsp');
}

function lockUserInfo(){
	$("[id^=notifier]").each(function(){
		if($(this).attr("type") == "text" || $(this).attr("type") == "password"){
			$(this).removeClass("field").addClass('field_RO').attr("readonly", "readonly");
		}else{
			if($(this).attr("name") == "btnQryExpense"){
				$(this).hide();
			}else{
				$(this).removeClass("field").addClass('field_RO').attr("disabled", "disabled");
			}
		}
	});
	changeNotifierType();
}

// 清除通報者
function clearNotifierInfo(){
	document.all.item("caseOwner").value = "";
	document.all.item("notifierDeptID").value = "";
	
	$("[id^=notifier]").each(function(){
		if($(this).attr("type") == "text"){
			$(this).val("").removeClass("field_RO").addClass('field').attr("readonly", "");
		}else if($(this).attr("type") == "select-one"){
			$(this).val("").removeClass("field_RO").addClass('field').attr("disabled", "");
		}else if($(this).attr("type") == "radio"){
			$(this).attr("checked", "").removeClass("field_RO").addClass('field').attr("disabled", "");
		}
		
		if($(this).attr("name") == "btnQryExpense"){
			$(this).show();
		}
	});
}

function getManufactorInfo(){
	openCenterWindow(800, 500, 'cosin0304f.jsp');
}

function lockManufactorInfo(){
	$("#manufactor1").removeClass("field").addClass('field_RO').attr("readonly", "readonly");
}

function clearManufactorInfo(){
	document.all.item("manufactorID").value = "";
	$("#manufactor1").val("").removeClass("field_RO").addClass('field').attr("readonly", "");
}


function getPermit(){
	openCenterWindow(900, 500, getVirtualPath() + "tcbw/cosex/cosex0110f.jsp?field1=permitKey&field2=permitNo");
}

function getCaseNo(){
	openCenterWindow(900, 500, getVirtualPath() + "tcbw/cosex/cosex0111f.jsp?field1=caseNo");
}

function changeTab(tabId) {
	document.all.item("nextPage").value = "下 一 頁";
	
	if(isObj(document.getElementById("t"+tabId)) && "none" == document.getElementById("t"+tabId).style.display){
		tabId = parseInt(tabId)+1;
	}
	form1.tabId.value = tabId;
	
	document.getElementById("t1").className = "tab_border2";
	document.getElementById("t2").className = "tab_border2";
	document.getElementById("t3").className = "tab_border2";
	document.getElementById("t4").className = "tab_border2";
	document.getElementById("t5").className = "tab_border2";	
	document.getElementById("aTab1").className = "";
	document.getElementById("aTab2").className = "";
	document.getElementById("aTab3").className = "";
	document.getElementById("aTab4").className = "";
	document.getElementById("aTab5").className = "";
		
	document.getElementById("Tab1").style.display = 'none';
	document.getElementById("Tab2").style.display = 'none';
	document.getElementById("Tab3").style.display = 'none';	
	document.getElementById("Tab4").style.display = 'none';
	document.getElementById("Tab5").style.display = 'none';
	
	if(tabId == 2){
		document.getElementById("t2").className = "tab_border1";	
		document.getElementById("Tab2").style.display = '';
		document.getElementById("aTab2").className = "text_w";		
	}else if(tabId == 3){
		document.getElementById("t3").className = "tab_border1";
		document.getElementById("Tab3").style.display = '';
		document.getElementById("aTab3").className = "text_w";
	}else if(tabId == 4){
		document.getElementById("t4").className = "tab_border1";
		document.getElementById("Tab4").style.display = '';
		document.getElementById("aTab4").className = "text_w";
	}else if(tabId == 5){
		document.getElementById("t5").className = "tab_border1";
		document.getElementById("Tab5").style.display = '';
		document.getElementById("aTab5").className = "text_w";
		document.all.item("nextPage").value = "回第一頁";
	}else{
		form1.tabId.value = 1;
		document.getElementById("t1").className = "tab_border1";
		document.getElementById("Tab1").style.display = '';
		document.getElementById("aTab1").className = "text_w";	
	}
}

$(function(){
	setDisplayItem('spanInsert,spanUpdate,spanQueryAll,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden','H');
	if("<%=obj.getCosType()%>" == "1"){
		$("#t4").hide();
		$("#Tab4").hide();
	}else if("<%=obj.getCosType()%>" == "2"){
		$("#t3").hide();
		$("#Tab3").hide();
	}else if("<%=obj.getCosType()%>" == "3"){
		
	}else{	
		$("#t3").hide();
		$("#Tab3").hide();
		$("#t4").hide();
		$("#Tab4").hide();
	}
	changeTab(1);
	
	$(":radio[name=notifierSource]").bind("click", function(){
		chNotifierSource($(this).val());
	});
	$(":radio[name=traffickWay]").bind("click", function(){
		chTraffickWay($(this).val());
	});
	$(":radio[name=isOtherDeptYn]").bind("click", function(){
		chIsOtherDeptYn($(this).val());
	});
	<%--
	$(":radio[name=adverseCondition]").bind("click", function(){
		chAdverseCondition($(this).val());
	});
	--%>
	$(":radio[name=evenContactYn]").bind("click", function(){
		chEvenContactYn($(this).val());
	});
	$(":radio[name=dealWith]").bind("click", function(){
		chDealWith($(this).val());
	});
	$(":radio[name=isDamageYn]").bind("click", function(){
		chIsDamageYn($(this).val());
	});
	<%=obj.getCFileRowSBuilder()%>
	
	if("<%=obj.getCosType()%>" == "2" || "<%=obj.getCosType()%>" == "3"){
		<%=obj.getCOS4004DbRowSBuilder()%>
		<%=obj.getCOS4005DbRowSBuilder()%>
		<%=obj.getCOSSDFileRowSBuilder()%>
		<%=obj.getCOSDPFileRowSBuilder()%>
		<%=obj.getCOSIDFileRowSBuilder()%>
	}
});

function changeNotifierType() {
	var notifierType = getRadioChecked(form1.notifierType);
	if(null != notifierType && "" != notifierType){
		if("01" == notifierType){
			checkedRadio(form1.notifierSource,"06");
		}else if("02" == notifierType){
			checkedRadio(form1.notifierSource,"05");
		}else if("03" == notifierType){
			checkedRadio(form1.notifierSource,"02");
		}else if("04" == notifierType){
			checkedRadio(form1.notifierSource,"03");
		}
	}
}
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('');">
<form id="form1" name="form1" method="post" autocomplete="off">
<!--Query區============================================================-->
<div id="queryContainer" style="width:700px;height:450px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="cosin0301q.jsp" />
</div>

<!--Toolbar區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bgToolbar" style="text-align:left">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="pageSize" value="<%=obj.getPageSize()%>">		<!-- FOR 查詢頁面定位使用 -->
	<input type="hidden" name="currentPage" value="<%=obj.getCurrentPage()%>">		<!-- FOR 查詢頁面定位使用 -->
	<input type="hidden" name="listContainerActiveRowId" value="<%=obj.getListContainerActiveRowId()%>">
	<input type="hidden" name="tabId" value ="<%=obj.getTabId()%>">
	<jsp:include page="../../home/toolbar.jsp" />
	
	<span id="spanPauseSave">
		<input class="toolbar_default" type="button" followPK="false" name="pauseSave" value="暫　存" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanDoSend">
		<input class="toolbar_default" type="button" followPK="false" name="doSend" value="送　出" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanCancelQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doCancelQuit" value="放 棄 離 開" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
    
    <%
    if( !"".equals(obj.getApplNo()) &&  ("20".equals(obj.getStatus()) || "30".equals(obj.getStatus()) || "40".equals(obj.getStatus()) || "50".equals(obj.getStatus())) ){
    %>
    	<span id="spanCaseReply">
	    	<input class="toolbar_default" type="button" followPK="false" name="caseReply" value="補 件 回 覆" onClick="whatButtonFireEvent(this.name)">&nbsp;
	    </span>
    <%
    }
    %>
    <span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit" value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
</td></tr>
</table>


<!--頁籤區============================================================-->
<TABLE CELLPADDING="0" CELLSPACING="0" valign="top">
<tr>
	<td nowrap ID="t1" CLASS="tab_border1"><a id="aTab1" href="#" onClick="changeTab(1);">基本資料</a></td>
	<td nowrap ID="t2" CLASS="tab_border2"><a id="aTab2" href="#" onClick="changeTab(2);">產品基本資料</a></td>
	<td nowrap ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">不良品</a></td>
	<td nowrap ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">不良反應</a></td>
	<td nowrap ID="t5" CLASS="tab_border2"><a id="aTab5" href="#" onClick="changeTab(5);">相關附件</a></td>
</tr>
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	
	<table id="Tab1" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td>
		<table class="table_form" width="100%" height="100%">
		<tr>
			<td nowrap class="td_form">案件編號</td>
			<td nowrap class="td_form_white">
				<input class="field_RO" type="text" name="applNo" size="11" maxlength="11" value="<%=obj.getApplNo()%>">
				<input type="hidden" name="status" value="<%=obj.getStatus()%>">
			</td>
			<td nowrap class="td_form">通報中心接獲通報日期</td>
			<td nowrap class="td_form_white">
				<input class="field_RO" type="text" name="notifierRepDate" size="7" maxlength="7" value="<%=obj.getNotifierRepDate()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">發生日期</td>
	  		<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field", "occurDate", obj.getOccurDate())%>
			</td>
			<td nowrap class="td_form">通報日期</td>
			<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field", "notifierRevDate", obj.getNotifierRevDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">通報來源</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getCommonRadioBoxOption("field", "notifierSource", "CIS", obj.getNotifierSource(), "codeId", 4)%>
				<input class="field" type="text" name="notifierSourceOther" size="15" maxlength="20" value="<%=obj.getNotifierSourceOther()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">通報者姓名</td>
	  		<td nowrap class="td_form_white">
	  			<input type="hidden" name="caseOwner" value="<%=obj.getCaseOwner()%>">
				<input class="field" type="text" id="notifier1" name="notifierName" size="20" maxlength="20" value="<%=obj.getNotifierName()%>">
				<input class="field" type="button" name="getUserInfo" value="通報者查詢" onClick="getNotifierInfo();">
				<input class="field" type="button" name="clearUserInfo" value="清除通報者" onClick="clearNotifierInfo();">
			</td>
			<td nowrap class="td_form">通報者電話</td>
		  	<td nowrap class="td_form_white" >
		  		( <input class="field" type="text" id="notifier2" name="notifierTelArea" size="2" maxlength="2" value="<%=obj.getNotifierTelArea()%>"> )
				- <input class="field" type="text" id="notifier3" name="notifierTel" size="15" maxlength="10" value="<%=obj.getNotifierTel()%>">
				# <input class="field" type="text" id="notifier4" name="notifierTelExt" size="3" maxlength="3" value="<%=obj.getNotifierTelExt()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">通報者職稱</td>
	  		<td nowrap class="td_form_white">
				<select class="field" id="notifier5" name="notifierTitle">
					<%=View.getOptionCodeKind("TITLE", obj.getNotifierTitle()) %>
				</select>
			</td>
			<td nowrap class="td_form">通報者屬性</td>
			<td nowrap class="td_form_white" >
				<%=View.getCommonCodeRadioBoxOption("field", "notifierType", "CONNFT1", obj.getNotifierType(), "changeNotifierType()", "01,02,03,04", "notifier6") %>
			</td>
		</tr>
		
		<tr>
			<td nowrap class="td_form">服務機構</td>
			<td nowrap class="td_form_white">
				<input type="hidden" name="notifierDeptID" value="<%=obj.getNotifierDeptID()%>" />
	  	        <input class="field_RO" type="text" id="notifier7" name="notifierDept" size="30" maxlength="30" value="<%=obj.getNotifierDept()%>" />
			    <input class="field" type="button" id="notifier8" name="btnQryExpense" onClick="popNotifierDept(notifierType,'Y');" value="查詢" width="120px" >
			</td>
			<td nowrap class="td_form">電子郵件信箱</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" id="notifier9" name="notifierEamil" size="40" maxlength="50" value="<%=obj.getNotifierEamil()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">聯絡地址</td>
		  	<td nowrap class="td_form_white" colspan="3">
		  		<select class="field" id="notifier10" name="notifierArea" onChange="popZipCode(this,notifierZipCode);">
			       <%=View.getOptionCodeKind("CTY", obj.getNotifierArea())%>
			    </select>
			    <select class="field" id="notifier11" name="notifierZipCode">
				   <%=View.getOptionCon1002(obj.getNotifierZipCode())%>
			    </select>
				<input class="field" type="text" id="notifier12" name="address" size="50" maxlength="100" value="<%=obj.getAddress()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">是否同意將您個人聯絡方式<br>提供產品負責業者</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getRadioBoxTextOption("field", "isContactYn", "N;否;Y;是", obj.getIsContactYn()) %>
			</td>
		</tr>
		</table>
	</td></tr>
	</table>
	
	<table id="Tab2" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td>
		<table class="table_form" width="100%" height="100%">
		<tr>
			<td nowrap class="td_form">中文品名</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="text" name="chProduct" size="40" maxlength="100" value="<%=obj.getChProduct()%>">
			</td>
			<td nowrap class="td_form">外文品名</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="text" name="enProduct" size="40" maxlength="100" value="<%=obj.checkGet(obj.getEnProduct())%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">不良事件類別</td>
		  	<td nowrap class="td_form_white">
		  		<%=View.getCommonCodeKindBoxOption("field_RO", "showCosType", "CCT", obj.getShowCosType()) %>
		  	</td>
			<td nowrap class="td_form">許可證字號</td>
		  	<td nowrap class="td_form_white">
				<select class="field" name="permitKey" type="select">
					<%=View.getOptionCodeKind("CPT", obj.getPermitKey()) %>
				</select>
				字第
				<input class="field" type="text" name="permitNo" size="20" maxlength="16" value="<%=obj.getPermitNo()%>" onChange="chkPermitProdData()">
				號
				<%
				if(Common.getInt(obj.getStatus()) >= 10 || "02".equals(obj.getStatus())){
					// nothing ..
				}else{
				%>
				<input class="toolbar_default" type="button" name="chosePermit" value="許可證字號" onClick="getPermit();">
				<%
				}
				%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">登錄編號</td>
		  	<td nowrap class="td_form_white">
				<input class="field_RO" type="text" name="caseNo" size="16" maxlength="16" value="<%=obj.getCaseNo()%>">
				<%
				if(Common.getInt(obj.getStatus()) >= 10 || "02".equals(obj.getStatus())){
					// nothing ..
				}else{
				%>
				<input class="toolbar_default" type="button" name="choseCaseNo" value="登錄編號" onClick="getCaseNo();">
				<%
				}
				%>
			</td>
			<td nowrap class="td_form">化粧品項目</td>
		  	<td nowrap class="td_form_white">
				<%=View.getCommonRadioBoxOption("field", "ingredient", "CCI", obj.getIngredient(), "codeId", 3)%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">販賣通路</td>
		  	<td nowrap class="td_form_white" colspan="3">
				<%=View.getCommonRadioBoxOption("field", "traffickWay", "CSP", obj.getTraffickWay(), "codeId", 7)%>
				<input class="field" type="text" name="traffickWayOther" size="30" maxlength="50" value="<%=obj.getTraffickWayOther()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">商家名稱</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="text" name="businessName" size="30" maxlength="50" value="<%=obj.getBusinessName()%>">
			</td>
			<td nowrap class="td_form">商家地址</td>
		  	<td nowrap class="td_form_white">
				<select class="field" name="tradePlace" type="select" onChange="popZipCode(this,tradePlaceZipCode);">
					<%=View.getOptionCodeKind("CTY", obj.getTradePlace()) %>
				</select>
				<select class="field" name="tradePlaceZipCode" type="select">
				   <%=View.getOptionCon1002(obj.getTradePlaceZipCode())%>
			    </select>
			    <input class="field" type="text" name="tradePlaceAddr" size="40" maxlength="40" value="<%=obj.getTradePlaceAddr()%>">
			    <br>
			    <font color="blue">請填寫購買產品之地址。<br>若無法填寫完整地址，請描述商家附近重要地標，如XX捷運站旁。</font>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">國內製造廠/國外產品進口(代理)商</td>
		  	<td nowrap class="td_form_white">
		  		<input type="hidden" name="manufactorID" value="<%=obj.getManufactorID()%>">
				<input class="field_RO" type="text" id="manufactor1" name="manufactorName" size="30" maxlength="50" value="<%=obj.getManufactorName()%>">
				<input class="field" type="button" name="getManufactor" value="廠商查詢" onClick="getManufactorInfo();">
				<input class="field" type="button" name="clearManufactor" value="清除廠商" onClick="clearManufactorInfo();">
			</td>
			<td nowrap class="td_form">廠商聯絡電話</td>
		  	<td nowrap class="td_form_white">
		  		( <input class="field" type="text" name="manufactorTelArea" size="2" maxlength="2" value="<%=obj.getManufactorTelArea()%>"> )
				- <input class="field" type="text" name="manufactorTel" size="10" maxlength="10" value="<%=obj.getManufactorTel()%>">
				# <input class="field" type="text" name="manufactorTelExt" size="3" maxlength="3" value="<%=obj.getManufactorTelExt()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">廠商地址</td>
		  	<td nowrap class="td_form_white" colspan="3">
		  		<select class="field" name="manufactorArea" type="select" onChange="popZipCode(this,manufactorZipCode);">
			       <%=View.getOptionCodeKind("CTY", obj.getManufactorArea())%>
			    </select>
			    <select class="field" name="manufactorZipCode" type="select">
				   <%=View.getOptionCon1002(obj.getManufactorZipCode())%>
			    </select>
				<input class="field" type="text" name="manufactorAddr" size="40" maxlength="40" value="<%=obj.getManufactorAddr()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">製造批號或製造日期</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="text" name="manufactorNo" size="10" maxlength="10" value="<%=obj.getManufactorNo()%>">
			</td>
			<td nowrap class="td_form">保存期限</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="text" name="expirationDate" size="7" maxlength="7" value="<%=obj.getExpirationDate()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">購買日期</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="text" name="tradeDate" size="7" maxlength="7" value="<%=obj.getTradeDate()%>">
			</td>
			<td nowrap class="td_form">是否可提供樣品</td>
		  	<td nowrap class="td_form_white">
				<%=View.getYNRadioBoxTextOption("field", "isSampleYn", obj.getIsSampleYn()) %>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">是否已與廠商接觸過</td>
		  	<td nowrap class="td_form_white" colspan="3">
				<%=View.getYNRadioBoxTextOption("field", "evenContactYn", obj.getEvenContactYn()) %>
			</td>
		</tr>
		<tr id="dealWith">
			<td nowrap class="td_form">後續處理</td>
		  	<td nowrap class="td_form_white" colspan="3">
				<%=View.getRadioBoxTextOption("field", "dealWith", "N;無處理;O;單一換貨;A;整批換貨", obj.getDealWith()) %>
			</td>
		</tr>
		<tr id="isRecurrenceYn">
			<td nowrap class="td_form">處理後是否再度發生</td>
		  	<td nowrap class="td_form_white" colspan="3">
				<%=View.getYNRadioBoxTextOption("field", "isRecurrenceYn", obj.getIsRecurrenceYn()) %>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">同產品是否有類似案例</td>
		  	<td nowrap class="td_form_white" colspan="3">
				<%=View.getYNRadioBoxTextOption("field", "isSimilarYn", obj.getIsSimilarYn()) %>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">是否已送交相關單位處理</td>
		  	<td nowrap class="td_form_white" colspan="3">
		  		<input class="field" type="radio" name="isOtherDeptYn" value="Y" <%=obj.getIsOtherDeptYn().equals("Y")?"checked":"" %>>是，
		  		相關單位名稱
		  		<input class="field" type="text" name="otherDpetName" size="40" maxlength="40" value="<%=obj.getOtherDpetName()%>">
		  		<input class="field" type="radio" name="isOtherDeptYn" value="N" <%=obj.getIsOtherDeptYn().equals("N")?"checked":"" %>>否
		  	</td>
		</tr>
		</table>
	</td></tr>
	</table>
	
	<table id="Tab3" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td>
		<table id="Tab3" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr><td>
			<table class="table_form" width="100%" height="100%">
			<tr>
				<td nowrap class="td_form">不良缺陷描述：</td>
				<td nowrap class="td_form_white" colspan="3">
					<%=obj.getCOS0003DbCheckBoxOption("field", "subCode", "otherDescribe", obj.getSubCode(), obj.getId(), true) %>
				</td>
			</tr>
			<tr>
				<td nowrap class="td_form">不良品是否有損害使用者身體或健康之事實</td>
				<td nowrap class="td_form_white" colspan="3">
					<input class="field" type="radio" name="isDamageYn" value="Y" <%=obj.getIsDamageYn().equals("Y")?"checked":"" %>>是，
					請描述
					<input class="field" type="text" name="otherInformation" size="40" maxlength="50" value="<%=obj.getOtherInformation()%>">
					<input class="field" type="radio" name="isDamageYn" value="N" <%=obj.getIsDamageYn().equals("N")?"checked":"" %>>否
					<input class="field" type="radio" name="isDamageYn" value="O" <%=obj.getIsDamageYn().equals("O")?"checked":"" %>>其他，請描述 :
					<input class="field" type="text" name="otherIsDamageYn" size="40" maxlength="50" value="<%=obj.getOtherIsDamageYn()%>">
				</td>
			</tr>
			<tr>
				<td nowrap class="td_form">說明</td>
				<td nowrap class="td_form_white" colspan="3">
					<textarea class="field" name="otherExplain" rows="2" cols="50"><%=obj.getOtherExplain() %></textarea>
				</td>
			</tr>
			</table>
		</td></tr>
		</table>
	</td></tr>
	</table>
	
	<table id="Tab4" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td>
		<table class="table_form" width="100%" height="100%">
		<tr>
			<td nowrap class="td_form">不良反應症狀</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="nonSeriousOther" rows="2" cols="50"><%=obj.getNonSeriousOther() %></textarea>
				<br>
				<font color="blue">請盡可能使用精確的「標準醫學字彙」或診斷，英文或中文皆可接受。</font>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">不良反應描述</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" name="nonSeriousDis" rows="2" cols="50"><%=obj.getNonSeriousDis() %></textarea>
				<br>
				<font color="blue">請依事件發生前後時序填寫，應包括使用化粧品前的相關病史；<br>使用化粧品及發生不良反應之時間；不良反應症狀、部位及嚴重程度；處置方式與處置後反應。</font>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">不良反應結果</td>
			<td nowrap class="td_form_white" colspan="3">
		  		<%=obj.getCACRadioBoxOption("field", "adverseCondition", obj.getAdverseCondition(), "", 5)%>
		  	</td>
		</tr>
		<tr>
			<td nowrap class="td_form">可疑化粧品使用起迄時間</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getPopCalendar("field", "useDateS", obj.getUseDateS())%>
				~
				<%=View.getPopCalendar("field", "useDateE", obj.getUseDateE())%>
		  	</td>
		</tr>
		<tr>
			<td nowrap class="td_form">使用方法</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" rows="3" cols="80" name="useMethod"><%=obj.getUseMethod()%></textarea>
		  	</td>
		</tr>
		<tr>
			<td nowrap class="td_form">使用頻率</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="useRate" size="70" maxlength="100" value="<%=obj.getUseRate()%>">
		  	</td>
		</tr>
		<tr>
			<td nowrap class="td_form">停用後不良反應是否減輕</td>
			<td nowrap class="td_form_white">
				<%=View.getRadioBoxTextOption("field", "isMitigateYn", "Y;是;N;否;U;無法得知", obj.getIsMitigateYn()) %>
		  	</td>
		  	<td nowrap class="td_form">再使用是否出現同樣反應</td>
			<td nowrap class="td_form_white">
				<%=View.getRadioBoxTextOption("field", "isRecurYn", "Y;是;N;否;U;無法得知", obj.getIsRecurYn()) %>
		  	</td>
		</tr>
		<tr>
			<td nowrap class="td_form">醫師診斷證明</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="diagnosisProof" size="60" maxlength="100" value="<%=obj.getDiagnosisProof()%>">
		  	</td>
		</tr>
		<tr>
			<td nowrap class="td_form">相關附件(醫師診斷證明)</td>
			<td nowrap class="td_form_white" colspan="3">
				<jsp:include page="../cosex/cosex0104f.jsp">
					<jsp:param value="COSDP" name="fileType"/>
				</jsp:include>
		  	</td>
		</tr>
		<tr>
			<td nowrap class="td_form">就醫紀錄(病例報告)</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="diagnosisReport" size="60" maxlength="100" value="<%=obj.getDiagnosisReport()%>">
		  	</td>
		</tr>
		<tr>
			<td nowrap class="td_form">相關附件(就醫紀錄)</td>
			<td nowrap class="td_form_white" colspan="3">
				<jsp:include page="../cosex/cosex0104f.jsp">
					<jsp:param value="COSSD" name="fileType"/>
				</jsp:include>
		  	</td>
		</tr>
		<tr>
			<td nowrap class="td_form">其他相關資料</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="diagnosisOther" size="60" maxlength="100" value="<%=obj.getDiagnosisOther()%>">
		  	</td>
		</tr>
		<tr>
			<td nowrap class="td_form">相關檢查及檢驗數據</td>
			<td nowrap class="td_form_white" colspan="3">
				<jsp:include page="../cosex/cosex0105f.jsp"/>	
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">相關附件(相關檢查及檢驗數據)</td>
			<td nowrap class="td_form_white" colspan="3">
				<jsp:include page="../cosex/cosex0104f.jsp">
					<jsp:param value="COSID" name="fileType"/>
				</jsp:include>
		  	</td>
		</tr>
		<tr>
			<td nowrap class="td_form" colspan="4" style="text-align:center">併用的其他化粧品</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="4">
				<jsp:include page="../cosex/cosex0106f.jsp"/>	
			</td>
		</tr>
		</table>
	</td></tr>
	</table>
	<table id="Tab5" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td>
		<table class="table_form" width="100%" height="100%">
		<tr>
			<td nowrap class="td_form" colspan="3" style="text-align:center">相關附件</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="3">
				<jsp:include page="../cosex/cosex0104f.jsp">
					<jsp:param value="C" name="fileType"/>
				</jsp:include>
		  	</td>
		</tr>
		</table>
	</td></tr>
	</table>
	<table id="toolbarTable" width="100%" align="center" class="table_form">
		<tr><td align="center">
			<input class="toolbar_default" type="button" followPK="false" name="nextPage" value="下 一 頁" onClick="nextPageCheck(form1.tabId.value);changeTab(parseInt(form1.tabId.value)+1);">
		</td></tr>
	</table>
	</div>
</td></tr>
</table>
</form>
</body>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	if(win != null){
		win.close();
	}
	switch (buttonName){
	case "pauseSave":
	case "doSend":
		checkField();
		break;
	case "doCancelQuit":
		form1.state.value = buttonName;
		checkField();
		break;
	case "caseReply":
		form1.state.value = buttonName;
		openCenterWindow(900, 400, "../cosex/cosex0108f.jsp?applNo=" + form1.applNo.value + "&cos4001DbId=" + form1.id.value + "&editID=" + form1.userID.value);
		break;
	case "doDoQuit":
		form1.action = "cosin0301f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		break;
	}
}

function nextPageCheck(tabId){
	var alertStr = "";
	if(tabId == "1"){
		<%=TCBWCommon.getCheckFiled("COS01", "COS01")%>
	}else if(tabId == "2"){
		<%=TCBWCommon.getCheckFiled("COS01", "COS02")%>
	}else if(tabId == "3"){
		<%=TCBWCommon.getCheckFiled("COS01", "COS03")%>
		
		if("O" == getRadioChecked(form1.isDamageYn)){
			alertStr += checkEmpty(form1.otherIsDamageYn, "其他不良品是否有損害使用者身體或健康之事實描述");
		}
	}else if(tabId == "4"){
		<%=TCBWCommon.getCheckFiled("COS01", "COS04")%>
	}
	
	if(alertStr.length != 0){
		alert(alertStr);
	}
}
</script>
</html>


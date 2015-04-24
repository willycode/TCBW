<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="COSEX0101" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosex.COSEX0102F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
if("insert".equals(obj.getDoType()) && "queryOne".equals(obj.getState())){						
	try{
		obj.doInsert();
	}catch(Exception e){
		e.printStackTrace();
	}
}
if("pauseSave".equals(obj.getState()) || "stayedUpload".equals(obj.getState()) || "doSend".equals(obj.getState())){
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

obj = (com.kangdainfo.tcbw.view.cosex.COSEX0102F)obj.queryOne();
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
<script type="text/javascript" src="cosex0100f.js"></script>
<script type="text/javascript">
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
	
	if(form1.state.value=="stayedUpload" || form1.state.value=="doSend"){
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
			if("O" == getRadioChecked(form1.isDamageYn)){
				alertStr += checkEmpty(form1.otherIsDamageYn, "其他不良品是否有損害使用者身體或健康之事實描述");
			}
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
		if("<%=obj.getDoType()%>" == "insert"){
			openStartAutoSave();
		}
		
		<%=TCBWCommon.getIsComplete("COS01")%>
	}else{
		alert("<%=obj.getErrorMsg()%>");
		// FOR 待補件、暫存的UPDATE DTREE
		<%
	    if("A".equals(obj.getQ_type()) || ("L".equals(obj.getQ_type()) && "00".equals(obj.getQ_status()))){
	    %>
	    	window.parent.frames['menu'].location.href = "../../home/dTreeMenu.jsp";
	    <%
	    }
	    %>
		form1.action = "cosex0101f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
	}
}

function openStartAutoSave(){
	if(form1.isAlreadyAutoSave.value != "Y"){
		form1.isAlreadyAutoSave.value = "Y";
		setTimeout("startSave()", 60000);
	}
}

function startSave(){　
	setTimeout("startSave()", 60000);
	setFormItem("pauseSave,stayedUpload,doSend,doDoQuit","R");
	if("<%=obj.getDoType()%>" == "insert"){
		setFormItem("doCancelQuit","R");
	}
	
	$("#spanShow").empty().append($("<font color='red'>自動儲存中</font>")).fadeIn("slow");
	form1.action = "cosex0107f.jsp?isSave=Y";
	form1.target = "autoSaveContainerFrame";
	beforeSubmit();
	form1.submit();
	form1.action = "cosex0103f.jsp";
	form1.target = "_self";
}

function unLockAutoSaveButton(){
	setFormItem("pauseSave,stayedUpload,doSend,doCancelQuit,doDoQuit","O");
	$("#spanShow").empty().append($("<font color='red'>自動儲存完成</font>")).fadeOut(4000);
}

function changeTab(tabId) {
	document.all.item("nextPage").value = "下 一 頁";
	if(isObj(document.getElementById("t"+tabId)) && "none" == document.getElementById("t"+tabId).style.display){
		tabId = parseInt(tabId)+1;
	}
	form1.tabId.value = tabId;
	document.getElementById("t1").className = "tab_border2";
	document.getElementById("t2").className = "tab_border2";
	
	if(isObj(document.getElementById("t3"))) {
		document.getElementById("t3").className = "tab_border2";
		document.getElementById("aTab3").className = "";
		document.getElementById("Tab3").style.display = 'none';	
	}
	
	if(isObj(document.getElementById("t4"))) {
		document.getElementById("t4").className = "tab_border2";
		document.getElementById("aTab4").className = "";
		document.getElementById("Tab4").style.display = 'none';
	}
	
	document.getElementById("t5").className = "tab_border2";
		
	document.getElementById("aTab1").className = "";
	document.getElementById("aTab2").className = "";
	document.getElementById("aTab5").className = "";
		
	document.getElementById("Tab1").style.display = 'none';
	document.getElementById("Tab2").style.display = 'none';
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
		document.getElementById("t1").className = "tab_border1";
		document.getElementById("Tab1").style.display = '';
		document.getElementById("aTab1").className = "text_w";	
		form1.tabId.value = 1;
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
	// FOR 待補件、暫存的UPDATE DTREE
	<%
	if("A".equals(obj.getQ_type()) || ("L".equals(obj.getQ_type()) && "00".equals(obj.getQ_status()))){
	%>
		window.parent.frames['menu'].location.href = "../../home/dTreeMenu.jsp";
	<%
	}
	%>
	
	form1.action = "cosex0101f.jsp";
	form1.state.value = "queryAll";
	form1.submit();
}

function getPermit(){
	openCenterWindow(900, 500, "cosex0110f.jsp?field1=permitKey&field2=permitNo");
}

function getCaseNo(){
	openCenterWindow(900, 500, "cosex0111f.jsp?field1=caseNo");
}

$(function(){
	setDisplayItem('spanInsert,spanUpdate,spanQueryAll,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden','H');
	$("#spanShow").hide();
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
	
	if("<%=obj.getDoType()%>" == "update"){
		$(".field").bind("change", openStartAutoSave);
		$(".field[name*=btn_COSSDFileName]").bind("click", openStartAutoSave);
		$(".field[name*=btn_CFileName]").bind("click", openStartAutoSave);
		$(".field_btnAdd").bind("click", openStartAutoSave);
		$(".field_btnRemove").bind("click", openStartAutoSave);
	}
});
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('');">
<form id="form1" name="form1" method="post" autocomplete="off">
<div id="autoSaveContainer" style="display:none">
	<iframe name="autoSaveContainerFrame"></iframe>
</div>

<!--Query區============================================================-->
<div id="queryContainer" style="width:700px;height:450px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="cosex0101q.jsp" />
</div>

<!--Toolbar區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bgToolbar" style="text-align:left">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="isAlreadyAutoSave">
	
	<input type="hidden" name="pageSize" value="<%=obj.getPageSize()%>">		<!-- FOR 查詢頁面定位使用 -->
	<input type="hidden" name="currentPage" value="<%=obj.getCurrentPage()%>">		<!-- FOR 查詢頁面定位使用 -->
	<input type="hidden" name="listContainerActiveRowId" value="<%=obj.getListContainerActiveRowId()%>">
	<input type="hidden" name="tabId" value ="<%=obj.getTabId()%>">
	<jsp:include page="../../home/toolbar.jsp" />
	
	<span id="spanPauseSave">
		<input class="toolbar_default" type="button" followPK="false" name="pauseSave" value="暫　存" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanStayedUpload">
		<input class="toolbar_default" type="button" followPK="false" name="stayedUpload" value="待 上 傳" onClick="whatButtonFireEvent(this.name)">&nbsp;
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
    <input class="toolbar_default" type="button" followPK="false" name="doReportView" value="列印通報表" onClick="whatButtonFireEvent(this.name)">
    <span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit" value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	
	<span id="spanShow"></span>
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
		<% request.setAttribute("qBean", obj); %>
		<jsp:include page="cosex0100f.jsp" />
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
	case "stayedUpload":
		checkField();
		break;
	case "doSend":
		checkField();
		break;
	case "doCancelQuit":
		form1.state.value = buttonName;
		checkField();
		break;
	case "caseReply":
		form1.state.value = buttonName;
		openCenterWindow(900, 400, "cosex0108f.jsp?applNo=" + form1.applNo.value + "&cos4001DbId=" + form1.id.value + "&editID=" + form1.userID.value);
		break;
	case "doDoQuit":
		form1.action = "cosex0101f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		break;
	//案件列印
	case "doReportView":
		form1.action = "cosex0103p.jsp" ;
		form1.target = "_blank";
		beforeSubmit();
		form1.submit();
		form1.action = "cosex0103f.jsp" ;
		form1.target = "_self";
		break;
	}
}
</script>
</html>

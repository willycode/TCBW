<!--
程式目的：食品非預期反應通報登入作業(外部)-一般表
程式代號：
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="HFREX0101" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.hfrex.HFREX0104F">
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

obj = (com.kangdainfo.tcbw.view.hfrex.HFREX0104F)obj.queryOne();
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
<script type="text/javascript" src="hfrex0101f.js"></script>
<script type="text/javascript">
var yyymmdd = getToday();
var msgS = "，不得大於今日日期\n";

function checkField(){
	var alertStr = "";
	if(form1.state.value=="stayedUpload" || form1.state.value=="doSend"){
		
		// 日期檢核
		$(":text[name$=Date][class=field]").each(function(){		
			var name = $(this).parent().prev().text();
			var m = checkDate(this, name);
			if(m.length == 0){
				if($(this).val() > yyymmdd){
					alertStr += name + msgS;
					this.style.backgroundColor = errorbg;
				}else{
					this.style.backgroundColor = "";
				}
			}else{
				alertStr += m;
			}
		});
		alertStr += checkPersonalID(form1.eatersIdNum, form1.eatersSex);
		alertStr += validateGFoodTable();
		alertStr += validateNFoodTable();
		alertStr += validateDiscriptTable();
		alertStr += validateDoctorTable();
		alertStr += validateDrugTabledr();
		alertStr += validateDrugTableodr();
		alertStr += validateTabFilefood();
		
		<%=TCBWCommon.getCheckFiled("HFR01", "HFR02")%>
		<%=TCBWCommon.getCheckFiled("HFR01", "HFR04")%>
	}
	if(alertStr.length!=0){ alert(alertStr); return; }
	
	if(form1.state.value=="stayedUpload" || form1.state.value=="doSend"){
		var isNon1 = false;
		var isNon2 = false;
		
		var GFArray = document.getElementsByName("GFoodRow");
		if(GFArray!=null && GFArray.length>0){
			isNon1 = true;
		}
		var NFArray = document.getElementsByName("NFoodRow");	
		if(NFArray!=null && NFArray.length>0){	
			isNon2 = true;
		}
		if(isNon1 || isNon2){
			
		}else{
			alert("至少輸入一項健康食品或非衛生署核准為健康食品之錠、膠劑型食品資料 !");
			changeTab(3);
			return;
		}
	}
	
	beforeSubmit();
	form1.submit();
}

function checkPersonalID(pID, pSex){
	var alertStr = "";
	var msg = "食用者身分證字號與性別不相符，請確認!\n";
	if(pID.value!="" && pID.value.length>=2 && (pSex[0].checked || pSex[1].checked)){
		var s = pID.value.substring(1,2);
		if(s == "1"){
			if(!pSex[0].checked){
				alertStr += msg;
				if($(pID).attr("class") == "field"){
					pID.style.backgroundColor = errorbg;
				}
			}else{
				if($(pID).attr("class") == "field"){
					pID.style.backgroundColor = "";
				}
			}
		}else if(s == "2"){
			if(!pSex[1].checked){
				alertStr += msg;
				if($(pID).attr("class") == "field"){
					pID.style.backgroundColor = errorbg;
				}
			}else{
				if($(pID).attr("class") == "field"){
					pID.style.backgroundColor = "";
				}
			}
		}else{
			alertStr += "身分證字號第二碼輸入有誤，請確認 !\n";
			if($(pID).attr("class") == "field"){
				pID.style.backgroundColor = errorbg;
			}
		}
	}else{
		if($(pID).attr("class") == "field"){
			pID.style.backgroundColor = "";
		}
	}
	return alertStr;
}

function init() {
	form1.eatersIdNum.value = "<%=obj.getEatersIdNum()%>";
	form1.notifierIdNum.value = "<%=obj.getNotifierIdNum()%>";
	
	if("<%=obj.getIsNeedBackQuery()%>" == "N" || "<%=obj.getIsNeedBackQuery()%>" == ""){
		if("<%=obj.getErrorMsg()%>" != ""){
			alert("<%=obj.getErrorMsg()%>");
		}
		if("<%=obj.getStatus()%>" < "02"){
			setAllOpen();
			if("<%=obj.getStatus()%>" == "01"){
				setFormItem("stayedUpload","R");
			}
		}else{
			setAllReadonly();
			setFormItem("pauseSave,stayedUpload,doCancelQuit,doSend","R");
			$(":button[name$=Download]").each(function(){
				$(this).attr("disabled", false);
			});
		}
		if("<%=obj.getStatus()%>" == "00"){
			setFormItem("doCancelQuit", "O");
		}else{
			setFormItem("doCancelQuit", "R");
		}
		chUnHealthIsYes("<%=obj.getUnHealthIsYes()%>");									// 健康食品未達宣稱保健功效
		chAgainEatingHealthFood("<%=obj.getAgainEatingHealthFood()%>");					// 曾食用同類健康食品之經驗
		chDrugAllergies("<%=obj.getDrugAllergies()%>");									// 藥物過敏史
		chFoodAllergies("<%=obj.getFoodAllergies()%>");									// 食物過敏史
		chDiseaseHistory();																// 疾病史		
		chLifeHistory();																// 生活史
		updateEaters("<%=obj.getIsSameNotifier()%>", "N");								// 與通報者為同一人
		
		// 通報事件描述如果為空，預設給一筆資料
		<%
		if(obj.getDiscriptSBuilder().length() == 0){
		%>
		addDiscript('tabDiscript',yyymmdd);
		<%
		}
		%>
		
		if("<%=obj.getDoType()%>" == "insert"){
			openStartAutoSave();
		}
		<%=TCBWCommon.getIsComplete("HFR01")%>
		
		
		
	}else{
		alert("<%=obj.getErrorMsg()%>");
		form1.action = "hfrex0101f.jsp";
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
	setFormItem("pauseSave,stayedUpload,doSend,doCancelQuit,doDoQuit","R");
	
	$("#spanShow").empty().append($("<font color='red'>自動儲存中</font>")).fadeIn("slow");
	
	form1.action = "hfrex0111f.jsp?isSave=Y";
	form1.target = "saveContainerFrame";
	beforeSubmit();
	form1.submit();
	form1.action = "hfrex0106f.jsp";
	form1.target = "_self";
}

function unLockAutoSaveButton(){
	setFormItem("pauseSave,stayedUpload,doSend,doDoQuit","O");
	if("<%=obj.getStatus()%>" == "00"){
		setFormItem("doCancelQuit","O");
	}else{
		setFormItem("doCancelQuit","R");
	}
	$("#spanShow").empty().append($("<font color='red'>自動儲存完成</font>")).fadeOut(4000);
}

// 與通報者為同一人
function updateEaters(val, isNeedClear){
	if(val == "Y"){
		if(isNeedClear == "Y"){
			document.all.item("eatersName").value = document.all.item("notifierName").value;
			document.all.item("eatersIdNum").value = document.all.item("notifierIdNum").value;
			document.all.item("eatersTelArea").value = document.all.item("notifierTelArea").value;
			document.all.item("eatersTel").value = document.all.item("notifierTel").value;
			document.all.item("eatersTelExt").value = document.all.item("notifierTelExt").value;
		}
		$("[id*=eater]").each(function(){
			$(this).removeClass("field").addClass('field_RO').attr("readonly", "readonly");
		});
	}else{
		$("[id*=eater]").each(function(){
			if(isNeedClear == "Y"){
				$(this).val("");
			}
			$(this).removeClass("field_RO").addClass('field').attr("readonly", "");
		});
	}
}

function changeTab(tabId) {
	document.getElementById("t1").className = "tab_border2";
	document.getElementById("t2").className = "tab_border2";
	document.getElementById("t3").className = "tab_border2";
	document.getElementById("t4").className = "tab_border2";
	document.getElementById("aTab1").className = "";
	document.getElementById("aTab2").className = "";
	document.getElementById("aTab3").className = "";
	document.getElementById("aTab4").className = "";
	document.getElementById("Tab1").style.display = 'none';
	document.getElementById("Tab2").style.display = 'none';
	document.getElementById("Tab3").style.display = 'none';
	document.getElementById("Tab4").style.display = 'none';

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
	}else{
		document.getElementById("t1").className = "tab_border1";
		document.getElementById("Tab1").style.display = '';
		document.getElementById("aTab1").className = "text_w";	
	}
}

$(function(){
	setDisplayItem('spanInsert,spanUpdate,spanQueryAll,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden','H');
	$("#spanShow").hide();
	changeTab(1);
	
	$(":checkbox[name=diseaseHistory]").bind("click", function(){
		chDiseaseHistory();
	});
	$(":checkbox[name=lifeHistory]").bind("click", function(){
		chLifeHistory();
	});
	$(":radio[name=drugAllergies]").bind("click", function(){
		chDrugAllergies($(this).val());
	});
	$(":radio[name=foodAllergies]").bind("click", function(){
		chFoodAllergies($(this).val());
	});
	<%=obj.getDiscriptSBuilder()%>
	<%=obj.getDoctorSBuilder()%>
	<%=obj.getGFoodSBuilder()%>
	<%=obj.getNFoodSBuilder()%>
	<%=obj.getDrugSBuilder()%>
	<%=obj.getODrugSBuilder()%>
	<%=obj.getFoodFileSBuilder()%>
	
	checkDoctorlistTFOOT();
	checkGFOODlistTFOOT();
	checkNFOODlistTFOOT();
	checkdrlistTFOOT();
	checkodrlistTFOOT();
	checklistTFOOTfood();
	
	if("<%=obj.getDoType()%>" == "update"){
		$(".field").bind("change", openStartAutoSave);
		$(".field[name*=btn_aFileName]").bind("click", openStartAutoSave);
		$(".field_btnAdd").bind("click", openStartAutoSave);
		$(".field_btnRemove").bind("click", openStartAutoSave);
	}
});
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('');">
<form id="form1" name="form1" method="post" autocomplete="off">
<div id="saveContainer" style="display:none">
	<iframe name="saveContainerFrame"></iframe>
</div>

<!--Query區============================================================-->
<div id="queryContainer" style="width:700px;height:450px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="hfrex0101q.jsp" />
</div>

<!--Toolbar區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bgToolbar" style="text-align:left">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">	
	<input type="hidden" name="pageSize" value="<%=obj.getPageSize()%>">		<!-- FOR 查詢頁面定位使用 -->
	<input type="hidden" name="currentPage" value="<%=obj.getCurrentPage()%>">		<!-- FOR 查詢頁面定位使用 -->
	<input type="hidden" name="isAlreadyAutoSave">
	<input type="hidden" name="listContainerActiveRowId" value="<%=obj.getListContainerActiveRowId()%>">
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
    <span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit" value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanDoReportView">
		<input class="toolbar_default" type="button" followPK="false" name="doReportView" value="列印通報表" onClick="whatButtonFireEvent(this.name)">
	</span>
	<span id="spanShow"></span>
</td></tr>
</table>

<!--頁籤區============================================================-->
<TABLE CELLPADDING="0" CELLSPACING="0" valign="top">
<tr>
	<td nowrap ID="t1" CLASS="tab_border1"><a id="aTab1" href="#" onClick="changeTab(1);">基本資料</a></td>
	<td nowrap ID="t2" CLASS="tab_border2"><a id="aTab2" href="#" onClick="changeTab(2);">調查紀錄</a></td>		
	<td nowrap ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">可疑食品列表</a></td>
	<td nowrap ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">其它相關附件</a></td>
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
			<td nowrap class="td_form_left" colspan="4">案件資訊</td>
		</tr>
		<tr>
			<td nowrap class="td_form">案件編號</td>
	  		<td nowrap class="td_form_white">
				<input class="field_RO" type="text" name="applNo" size="15" maxlength="11" value="<%=obj.getApplNo()%>">
			</td>
			<td nowrap class="td_form">通報中心接獲通報日期</td>
			<td nowrap class="td_form_white">
				<input class="field_RO" type="text" name="notifierRepDate" size="15" maxlength="11" value="<%=obj.getNotifierRepDate()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">發生日期</td>
			<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field", "occurDate", obj.getOccurDate())%>
			</td>
			<td nowrap class="td_form">通報者獲知日期</td>
			<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field", "notifierRevDate", obj.getNotifierRevDate())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">通報者資訊</td>
		</tr>
		<tr>
			<td nowrap class="td_form">姓名</td>
	  		<td nowrap class="td_form_white">
				<input class="field_RO" type="text" name="notifierName" size="20" maxlength="20" value="<%=obj.getNotifierName()%>">
			</td>
			<td nowrap class="td_form">身分證字號</td>
			<td nowrap class="td_form_white">
				<input class="field_RO" type="password" autocomplete="off" name="notifierIdNum" size="20" maxlength="10" value="">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">電話</td>
		  	<td nowrap class="td_form_white">
		  		( <input class="field_RO" type="text" name="notifierTelArea" size="2" maxlength="2" value="<%=obj.getNotifierTelArea()%>"> )
				- <input class="field_RO" type="text" name="notifierTel" size="10" maxlength="10" value="<%=obj.getNotifierTel()%>">
				# <input class="field_RO" type="text" name="notifierTelExt" size="3" maxlength="3" value="<%=obj.getNotifierTelExt()%>">
			</td>
			<td nowrap class="td_form">電子郵件信箱</td>
			<td nowrap class="td_form_white" >
				<input class="field_RO" type="text" name="notifierEamil" size="30" maxlength="50" value="<%=obj.getNotifierEamil()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">屬性</td>
			<td nowrap class="td_form_white" >
				<select class="field_RO" name="notifierType" type="select">
					<%=View.getOptionCommonDepartment(obj.getNotifierType(), "02;03;04;") %>
				</select>
			</td>
			<td nowrap class="td_form">服務機構</td>
			<td nowrap class="td_form_white">
				<select class="field_RO" name="notifierDeptID" type="select">
					<%=View.getOptionCon100310051009Db(obj.getNotifierDeptID(), obj.getNotifierType()) %>
				</select>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">職稱</td>
			<td nowrap class="td_form_white">
				<select class="field_RO" name="notifierTitle" type="select">
					<%=View.getOptionCodeKind("TITLE", obj.getNotifierTitle()) %>
				</select>
			</td>
			<td nowrap class="td_form">通報來源</td>
			<td nowrap class="td_form_white">
				<select class="field" name="informedSources" type="select">
					<%=View.getOptionCodeKind("HFRNFS", obj.getInformedSources()) %>
				</select>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">住址</td>
		  	<td nowrap class="td_form_white" colspan="3">
		  		<select class="field_RO" name="notifierArea" type="select" onChange="popZipCode(this,notifierZipCode);">
			       <%=View.getOptionCodeKind("CTY", obj.getNotifierArea())%>
			    </select>
			    <select name="notifierZipCode" class="field_RO" type="select">
				   <%=View.getOptionCon1002(obj.getNotifierZipCode())%>
			    </select>
				<input class="field_RO" type="text" name="address" size="50" maxlength="100" value="<%=obj.getAddress()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" colspan="4">食用者資訊</td>
		</tr>
		<tr>
			<td nowrap class="td_form">姓名</td>
		  	<td nowrap class="td_form_white">
		  		<input class="field" type="text" id="eater1" name="eatersName" size="20" maxlength="20" value="<%=obj.getEatersName()%>">
				，與通報者為同一人
				<select class="field" name="isSameNotifier" type="select" onChange="updateEaters(this.value, 'Y');">
					<%=View.getYNOption(obj.getIsSameNotifier()) %>
				</select>
			</td>
			<td nowrap class="td_form">識別代號</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="text" name="eatersId" size="20" maxlength="20" value="<%=obj.getEatersId()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">身分證字號</td>
			<td nowrap class="td_form_white">
				<input class="field" type="password" autocomplete="off" id="eater2" name="eatersIdNum" size="20" maxlength="10" value="">
			</td>
			<td nowrap class="td_form">連絡電話</td>
		  	<td nowrap class="td_form_white">
				( <input class="field" type="text" id="eater3" name="eatersTelArea" size="2" maxlength="2" value="<%=obj.getEatersTelArea()%>"> )
				- <input class="field" type="text" id="eater4" name="eatersTel" size="10" maxlength="10" value="<%=obj.getEatersTel()%>">
				# <input class="field" type="text" id="eater5" name="eatersTelExt" size="3" maxlength="3" value="<%=obj.getEatersTelExt()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">性別</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="radio" name="eatersSex" value="M" <%=obj.getEatersSex().equals("M")?"checked":"" %>>男
				&nbsp;
				<input class="field" type="radio" name="eatersSex" value="F" <%=obj.getEatersSex().equals("F")?"checked":"" %>>女
			</td>
			<td nowrap class="td_form">出生年份</td>
		  	<td nowrap class="td_form_white">
		  		民國<input class="field" type="text" name="eatersBirthYear" size="10" maxlength="3" value="<%=obj.getEatersBirthYear()%>">年
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">身高</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="text" name="eatersHight" size="10" maxlength="3" value="<%=obj.getEatersHight()%>">公分
			</td>
			<td nowrap class="td_form">體重</td>
		  	<td nowrap class="td_form_white">
				<input class="field" type="text" name="eatersWeight" size="10" maxlength="3" value="<%=obj.getEatersWeight()%>">公斤
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">藥物過敏史</td>
		  	<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="radio" name="drugAllergies" value="Y" <%=obj.getDrugAllergies().equals("Y")?"checked":"" %>>是，
				說明
				<input class="field" type="text" name="drugOther" size="40" maxlength="50" value="<%=obj.getDrugOther()%>">
				&nbsp;
				<input class="field" type="radio" name="drugAllergies" value="N" <%=obj.getDrugAllergies().equals("N")?"checked":"" %>>否
				&nbsp;
				<input class="field" type="radio" name="drugAllergies" value="U" <%=obj.getDrugAllergies().equals("U")?"checked":"" %>>無法得知
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">食物過敏史</td>
		  	<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="radio" name="foodAllergies" value="Y" <%=obj.getFoodAllergies().equals("Y")?"checked":"" %>>是，
				說明
				<input class="field" type="text" name="foodOther" size="40" maxlength="50" value="<%=obj.getFoodOther()%>">
				&nbsp;
				<input class="field" type="radio" name="foodAllergies" value="N" <%=obj.getFoodAllergies().equals("N")?"checked":"" %>>否
				&nbsp;
				<input class="field" type="radio" name="foodAllergies" value="U" <%=obj.getFoodAllergies().equals("U")?"checked":"" %>>無法得知
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">疾病史</td>
		  	<td nowrap class="td_form_white" colspan="3">
				<%=View.getCommonCodeKindBoxOption("field", "diseaseHistory", "HFRDSH", obj.getDiseaseHistory()) %>
				，說明
				<input class="field" type="text" name="diseaseOther" size="40" maxlength="50" value="<%=obj.getDiseaseOther()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">生活史</td>
		  	<td nowrap class="td_form_white" colspan="3">
				<%=View.getCommonCodeKindBoxOption("field", "lifeHistory", "HFRLFH", obj.getLifeHistory()) %>
				，說明
				<input class="field" type="text" name="lifeOther" size="40" maxlength="50" value="<%=obj.getLifeOther()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">是否告知案件評估結果</td>
		  	<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="radio" name="isReactionResult" value="Y" <%=obj.getIsReactionResult().equals("Y")?"checked":"" %>>是
				&nbsp;
				<input class="field" type="radio" name="isReactionResult" value="N" <%=obj.getIsReactionResult().equals("N")?"checked":"" %>>否
			</td>
		</tr>
		<tr>
			<td colspan="4" style="text-align:center">
				<span id="spanDoQuit">
					<input class="toolbar_default" type="button" followPK="false" name="doNextTab2" value="下 一 頁" onClick="nextPageCheck(2);changeTab(2);">&nbsp;
				</span>
			</td>
		</tr>
		</table>
	</td></tr>
	</table>
	
	<table id="Tab2" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td>
		<table class="table_form" width="100%" height="100%" >
		<tr>
			<td nowrap class="td_form">健康食品未達宣稱保健功效</td>
		  	<td nowrap class="td_form_white" colspan="3">
	            <input class="field" type="radio" name="unHealthIsYes" value="Y" <%=obj.getUnHealthIsYes().equals("Y")?"checked":"" %> onClick="chUnHealthIsYes(this.value);">是，
				簡述
	            <input class="field" type="text" name="unHealthBrief" size="50" maxlength="80" value="<%=obj.getUnHealthBrief()%>">
	            <br>
	            <input class="field" type="radio" name="unHealthIsYes" value="N" <%=obj.getUnHealthIsYes().equals("N")?"checked":"" %> onClick="chUnHealthIsYes(this.value);">否
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">非預期反應結果</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=obj.getHFRURCRRadioOption("field", "unReactionResults", obj.getUnReactionResults(), obj.getDeathDate(), obj.getDeathResult(), obj.getEndangerLife(), obj.getOtherNonResponse()) %>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" style="text-align:center" colspan="4">通報事件描述</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="4">
				<jsp:include page="hfrex0107f.jsp"/>	
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" style="text-align:center" colspan="4">醫師診斷及相關檢查及檢驗數據</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="4">
				<jsp:include page="hfrex0108f.jsp"/>	
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">其它相關資料</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=obj.getFORCheckBoxOption("field", "otherrElevantInformation", obj.getOtherrElevantInformation(), obj.getAllergicDescription(), obj.getOthersDescribeHistory()) %>
			</td>
		</tr>
		<tr>
			<td colspan="4" style="text-align:center">
				<span id="spanDoQuit">
					<input class="toolbar_default" type="button" followPK="false" name="doNextTab3" value="下 一 頁" onClick="nextPageCheck(3);changeTab(3);">&nbsp;
				</span>
			</td>
		</tr>
		</table>
	</td></tr>
	</table>
	
	<table id="Tab3" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td>
		<table class="table_form" width="100%" height="100%" >
		<tr>
			<td nowrap class="td_form" style="text-align:center" colspan="4">健康食品</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="4">
				<jsp:include page="hfrex0103f.jsp"/>	
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" style="text-align:center" colspan="4">非衛生署核准為健康食品之錠、膠劑型食品</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="4">
				<jsp:include page="hfrex0104f.jsp"/>	
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" style="text-align:center" colspan="4">併用藥品(西藥、中藥)</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="4">
				<jsp:include page="hfrex0109f.jsp">
					<jsp:param value="dr" name="drugType"/>
				</jsp:include>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" style="text-align:center" colspan="4">併用其他錠、膠劑型食品</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="4">
				<jsp:include page="hfrex0109f.jsp">
					<jsp:param value="odr" name="drugType"/>
				</jsp:include>	
			</td>
		</tr>
		
		<tr>
			<td nowrap class="td_form">曾食用同類健康食品之經驗</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="radio" name="againEatingHealthFood" value="Y" <%=obj.getAgainEatingHealthFood().equals("Y")?"checked":"" %> onClick="chAgainEatingHealthFood(this.value);">是，
				商品名 : <input class="field" type="text" name="healthFoodName" size="30" maxlength="540" value="<%=obj.getHealthFoodName()%>">
				，曾經發生非預期反應 : <input class="field" type="text" name="againOtherNonResponse" size="30" maxlength="50" value="<%=obj.getAgainOtherNonResponse()%>">
				&nbsp;
				<input class="field" type="radio" name="againEatingHealthFood" value="N" <%=obj.getAgainEatingHealthFood().equals("N")?"checked":"" %> onClick="chAgainEatingHealthFood(this.value);">否
				&nbsp;
				<input class="field" type="radio" name="againEatingHealthFood" value="U" <%=obj.getAgainEatingHealthFood().equals("U")?"checked":"" %> onClick="chAgainEatingHealthFood(this.value);">無法得知
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">停止食用健康食品後，此反應是否減輕</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="radio" name="stopEatingReaction" value="Y" <%=obj.getStopEatingReaction().equals("Y")?"checked":"" %>>是
				&nbsp;
				<input class="field" type="radio" name="stopEatingReaction" value="N" <%=obj.getStopEatingReaction().equals("N")?"checked":"" %>>否
				&nbsp;
				<input class="field" type="radio" name="stopEatingReaction" value="U" <%=obj.getStopEatingReaction().equals("U")?"checked":"" %>>無法得知
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">再次食用是否出現相同反應</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="radio" name="againEatingReaction" value="Y" <%=obj.getAgainEatingReaction().equals("Y")?"checked":"" %>>是
				&nbsp;
				<input class="field" type="radio" name="againEatingReaction" value="N" <%=obj.getAgainEatingReaction().equals("N")?"checked":"" %>>否
				&nbsp;
				<input class="field" type="radio" name="againEatingReaction" value="U" <%=obj.getAgainEatingReaction().equals("U")?"checked":"" %>>無法得知
			</td>
		</tr>
		<tr>
			<td colspan="4" style="text-align:center">
				<span id="spanDoQuit">
					<input class="toolbar_default" type="button" followPK="false" name="doNextTab4" value="下 一 頁" onClick="nextPageCheck(4);changeTab(4);">&nbsp;
				</span>
			</td>
		</tr>
		</table>
	</td></tr>
	</table>
	
	<table id="Tab4" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr><td>
		<table class="table_form" width="100%" height="100%" >
		<tr>
			<td nowrap class="td_form" style="text-align:center" colspan="4">相關附件</td>
		</tr>
		<tr>
			<td nowrap class="td_form_white" colspan="4">
				<jsp:include page="hfrex0110f.jsp">
					<jsp:param value="food" name="fileType"/>
				</jsp:include>
			</td>
		</tr>
		</table>
	</td></tr>
	</table>
	
	
	</div>
</td></tr>
</table>
</form>
</body>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName){
	case "pauseSave":
	case "stayedUpload":
	case "doSend":
	case "doCancelQuit":
		form1.state.value = buttonName;
		checkField();
		break;
	case "doDoQuit":
		form1.action = "hfrex0101f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		break;
	case "doReportView":									// 案件列印
		form1.action = "hfrex0102gp.jsp" ;
		form1.target = "_blank";
		form1.submit();
		form1.action = "hfrex0106f.jsp" ;
		form1.target = "_self";
		break;
	}
}

function nextPageCheck(tabId){
	var alertStr = "";
	if(tabId == "2"){
		<%=TCBWCommon.getCheckFiled("HFR01", "HFR02")%>
	}else if(tabId == "3"){
		<%=TCBWCommon.getCheckFiled("HFR01", "HFR04")%>
	}else if(tabId == "4"){
		
	}
	if(alertStr.length > 0){
		alert(alertStr);
	}
}
</script>
</html>

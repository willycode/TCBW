<!--
程式目的：食品非預期反應通報登入作業(內部)-簡表
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
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="HFRIN0401" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.hfrin.HFRIN0402F">
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
		obj.setErrorMsg("資料放棄完成");
		obj.setIsNeedBackQuery("Y");
		obj.setId("");
	}catch(Exception e){
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.hfrin.HFRIN0402F)obj.queryOne();
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
<script type="text/javascript" src="hfrin0400f.js"></script>
<script type="text/javascript">
function checkField(){
	var alertStr = "";
	if(form1.state.value=="stayedUpload" || form1.state.value=="doSend"){
		alertStr += checkPersonalID(form1.eatersIdNum, form1.eatersSex);
		alertStr += checkHFR0001FDate();
		alertStr += validateGFoodTable();
		alertStr += validateNFoodTable();
		alertStr += validateTabFilefood();
		
		<%=TCBWCommon.getCheckFiled("HFR01", "HFR01")%>
		<%=TCBWCommon.getCheckFiled("HFR01", "HFR03")%>
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
			alert("至少輸入一項健康食品或一般食品資料 !");
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
	if("<%=obj.getIsNeedBackQuery()%>" == "N" || "<%=obj.getIsNeedBackQuery()%>" == ""){
		if("<%=obj.getErrorMsg()%>" != ""){
			alert("<%=obj.getErrorMsg()%>");
		}
		if("<%=obj.getStatus()%>" < "02"){
			setAllOpen();
			if("<%=obj.getStatus()%>" == "01"){
				setFormItem("stayedUpload","R");
			}
			if("<%=obj.getStatus()%>" == "00"){
				setFormItem("doCancelQuit","O");
			}else{
				setFormItem("doCancelQuit","R");
			}
		}else{
			setAllReadonly();
			setFormItem("pauseSave,stayedUpload,doCancelQuit,doSend","R");
			$(":button[name$=Download],:button[name^=showPermit][class=field],:button[name^=showNonPermit][class=field]").each(function(){
				$(this).attr("disabled", false);
			});
			
			// FOR 案件原始版本
			<%if(!"".equals(Common.get(q_id))){%>
				setDisplayItem("spanPauseSave,spanStayedUpload,spanDoSend,spanCancelQuit","H");
				$("#spanDoQuit,#spanDoReportView").hide();
			<%}%>
		}
		chDrugAllergies("<%=obj.getDrugAllergies()%>");								// 藥物過敏史
		chFoodAllergies("<%=obj.getFoodAllergies()%>");								// 食物過敏史  
		chDiseaseHistory();															// 疾病史
		chLifeHistory();															// 生活史			
		chUnHealthIsYes("<%=obj.getUnHealthIsYes()%>");								// 健康食品未達宣稱保健功效
		chIsMedical("<%=obj.getIsMedical()%>");										// 是否有就醫
		chIsWhileMedicine("<%=obj.getIsWhileMedicine()%>");							// 同時服用西醫
		chIsWhileCMedicine("<%=obj.getIsWhileCMedicine()%>");						// 同時服用中醫	
		chIsWhileOther("<%=obj.getIsWhileOther()%>");								// 同時食用其他錠、塑膠型食品	
		updateEaters("<%=obj.getIsSameNotifier()%>", "N");							// 同通報者
		
		<%=TCBWCommon.getIsComplete("HFR01")%>
	}else{
		alert("<%=obj.getErrorMsg()%>");
		window.parent.frames['menu'].location.href = "../../home/dTreeMenu.jsp";
		
		form1.action = "hfrin0401f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
	}
}

function updateEaters(val, isNeedClear){
	if(val == "Y"){
		if(isNeedClear == "Y"){
			document.all.item("eatersName").value = document.all.item("notifierName").value;
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
	<%=obj.getGFoodSBuilder()%>
	<%=obj.getNFoodSBuilder()%>
	<%=obj.getFoodFileSBuilder()%>
	
	checkGFOODlistTFOOT();
	checkNFOODlistTFOOT();
	checklistTFOOTfood();
});
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('');">
<form id="form1" name="form1" method="post" autocomplete="off">

<!--Query區============================================================-->
<div id="queryContainer" style="width:700px;height:450px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="hfrin0401q.jsp" />
</div>

<!--Toolbar區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bgToolbar" style="text-align:left">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">	
	<input type="hidden" name="pageSize" value="<%=obj.getPageSize()%>">			<!-- FOR 查詢頁面定位使用 -->
	<input type="hidden" name="currentPage" value="<%=obj.getCurrentPage()%>">		<!-- FOR 查詢頁面定位使用 -->
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
</td></tr>
</table>

<!--頁籤區============================================================-->
<TABLE CELLPADDING="0" CELLSPACING="0" valign="top">
<tr>
	<td nowrap ID="t1" CLASS="tab_border1"><a id="aTab1" href="#" onClick="changeTab(1);">基本資料</a></td>
	<td nowrap ID="t2" CLASS="tab_border2"><a id="aTab2" href="#" onClick="changeTab(2);">通報內容</a></td>		
	<td nowrap ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">可疑食品列表</a></td>
	<td nowrap ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">其它相關附件</a></td>
</tr>
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
		<% request.setAttribute("qBean", obj); %>
		<jsp:include page="hfrin0001f.jsp" >
			<jsp:param value="Y" name="isNeedShowNext"/>
		</jsp:include>
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
		form1.action = "hfrin0401f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		break;
	case "doReportView":									// 案件列印
		form1.action = "../hfrex/hfrex0102gp.jsp" ;
		form1.target = "_blank";
		form1.submit();
		form1.action = "hfrin0403f.jsp" ;
		form1.target = "_self";
		break;
	}
}
</script>
</html>

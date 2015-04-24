<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="COSIN1001" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN1002F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
String competence = "Y";
if("1D".equals(obj.getQ_type()) || "1A".equals(obj.getQ_type())){
	competence = Common.get(TCBWCommon.getCompetence("COS02", "02", User.getUserId(), "4"));
}else{
	competence = Common.get(TCBWCommon.getCompetence("COS03", "06", User.getUserId(), "4"));
}

if("pauseSave".equals(obj.getState()) || "disComplete".equals(obj.getState())){
	try{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doUpdateType();
	}catch(Exception e){
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}else if("additionalCase".equals(obj.getState())){
	try{
		obj.updateAdditionalCase();
	}catch(Exception e){
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.cosin.COSIN1002F)obj.queryOne();
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
	
	if(form1.state.value == "disComplete"){
		<%if("1".equals(obj.getShowCosType()[0])) {%>
			<%=TCBWCommon.getCheckFiled("COS01", "COS01")%>
		<%}%>
		<%if("2".equals(obj.getShowCosType()[0])) {%>
			<%=TCBWCommon.getCheckFiled("COS01", "COS02")%>
		<%}%>
		if("<%=obj.getCosType()%>" == "2"){
			<%=TCBWCommon.getCheckFiled("COS01", "COS04")%>
			<%=TCBWCommon.getCheckFiled("COS01", "COS06")%>
			alertStr += validateCOS0004DbTable();					// 相關檢查及檢驗數據
			alertStr += validateCOS0005DbTable();					// 併用的其他化粧品
		}		
		if("<%=obj.getCosType()%>" == "1"){
			<%=TCBWCommon.getCheckFiled("COS01", "COS03")%>
			<%=TCBWCommon.getCheckFiled("COS01", "COS05")%>
			
			// 初步判定結果為不良品時，才需檢核
			if(form1.firstResult.value == "01"){
				var flag = false;
				if(form1.measure[0].checked){
					flag = true;
				}
				if(form1.measure[1].checked){
					flag = true;
				}
				if(!flag){
					alertStr += "採取措施至少須選擇\n[轉由地方衛生單位作後續處理]、[寄發廠商詢問函]其中一項";
				}
			}
		}
	}
	if(alertStr.length > 0){
		alert(alertStr);	return;
	}
	
	beforeSubmit();
	form1.submit();
}

function init(){
	if("<%=obj.getIsNeedBackQuery()%>" == "N" || "<%=obj.getIsNeedBackQuery()%>" == ""){
		if("<%=obj.getErrorMsg()%>" != ""){
			alert("<%=obj.getErrorMsg()%>");
		}
		setFormItem("pauseSave,disComplete","R");
		if("<%=obj.getQ_type()%>" == "1D" || "<%=obj.getQ_type()%>" == "2D"){
			setFormItem("additionalCase","R");
		}
		$(":button[name$=Download]").each(function(){
			$(this).attr("disabled", false);
		});
		
		//var c = "<%=obj.getChargeMan()%>";
		//var u = "<%=obj.getUserID()%>";
		
		//if(c != ""){
		//	if(c != u){
		//		setFormItem("pageUpdate","R");
		//	}
		//}
		<%=TCBWCommon.getIsComplete("COS01")%>
	}else{
		alert("<%=obj.getErrorMsg()%>");
		deleteEdit("<%=obj.getId()%>");				// 解除LOCK								
		window.parent.frames['menu'].location.href = "../../home/dTreeMenu.jsp";
		
		form1.action = "cosin1001f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
	}
}

function toAsk()
{
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;


	//點選不遮蔽個資時記錄相關LOG
	var q = "code=HFR01";  //通報表代碼，各通報表請參照CON1007_DB裡的FORMCODE欄位
	q +="&methodName=print";
	q +="&db=Hfr0001Db"; //通報表主檔DB，各通報表請修改為各自的內網主檔DB
	q +="&hql=select id,applNo,caseOwner,notifierName from Hfr0001Db where id = " + form1.id.value; //查詢通報表主檔DB相關欄位資訊，做為記錄LOG用
	
	//彈出式視窗大小資訊
	prop=prop+"width=800,height=100,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "tcbw/conin/conin1001f.jsp?" + q,"",prop);  //呼叫共用的conin1001f.jsp並帶入相關參數(q)，原report()方法改在這支jsp裡呼叫
	
}

function report(isClose){
	form1.isCloseUserInfo.value = isClose;
	form1.action = "cosin0402p.jsp" ;
	form1.target = "_blank";
	beforeSubmit();
	form1.submit();
	form1.action = "cosin1002f.jsp" ;
	form1.target = "_self";
}

function changeTab(tabId) {
	document.getElementById("t1").className = "tab_border2";
	document.getElementById("t2").className = "tab_border2";
	document.getElementById("t3").className = "tab_border2";
	document.getElementById("t4").className = "tab_border2";
	document.getElementById("t5").className = "tab_border2";
	document.getElementById("t6").className = "tab_border2";	
	document.getElementById("aTab1").className = "";
	document.getElementById("aTab2").className = "";
	document.getElementById("aTab3").className = "";
	document.getElementById("aTab4").className = "";
	document.getElementById("aTab5").className = "";
	document.getElementById("aTab6").className = "";
	document.getElementById("Tab1").style.display = 'none';
	document.getElementById("Tab2").style.display = 'none';
	document.getElementById("Tab3").style.display = 'none';	
	document.getElementById("Tab4").style.display = 'none';
	document.getElementById("Tab5").style.display = 'none';
	document.getElementById("Tab6").style.display = 'none';
	
	if("<%=obj.getCosType()%>"=="1" && "<%=obj.getIsNeedShowCos0007DbPage()%>"=="Y"){
		document.getElementById("t7").className = "tab_border2";
		document.getElementById("aTab7").className = "";
		document.getElementById("Tab7").style.display = 'none';
	}
	
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
	}else if(tabId == 6){
		document.getElementById("t6").className = "tab_border1";
		document.getElementById("Tab6").style.display = '';
		document.getElementById("aTab6").className = "text_w";
	}else if(tabId == 7){
		document.getElementById("t7").className = "tab_border1";
		document.getElementById("Tab7").style.display = '';
		document.getElementById("aTab7").className = "text_w";
	}else{
		document.getElementById("t1").className = "tab_border1";
		document.getElementById("Tab1").style.display = '';
		document.getElementById("aTab1").className = "text_w";	
	}
}

function goBackQuery(){
	deleteEdit(form1.id.value);
	window.parent.frames['menu'].location.href = "../../home/dTreeMenu.jsp";
	
	form1.action = "cosin1001f.jsp";
	form1.state.value = "queryAll";
	form1.submit();
}

function updateAdditionalCase(){
	form1.action = "cosin1002f.jsp";
	form1.state.value = "additionalCase";
	beforeSubmit();
	form1.submit();
}

$(function(){
	if(win != null) win.close();
	
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
	chUserInput();
	
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
	
	if("<%=obj.getCosType()%>" == "2"){
		<%=obj.getCOS0004DbRowSBuilder()%>
		<%=obj.getCOS0005DbRowSBuilder()%>
		<%=obj.getCOSSDFileRowSBuilder()%>
		<%=obj.getCOSDPFileRowSBuilder()%>
	}
});
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('');">
<form id="form1" name="form1" method="post" autocomplete="off">
<!--Query區============================================================-->
<div id="queryContainer" style="width:700px;height:450px;display:none;">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="cosin1001q.jsp" />
</div>

<!--Toolbar區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bgToolbar" style="text-align:left">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="isCloseUserInfo" value="<%=obj.getIsCloseUserInfo()%>"> <!-- 列印時是否遮蔽個資 -->
	<input type="hidden" name="isNeedShowCos0007DbPage" value="<%=obj.getIsNeedShowCos0007DbPage()%>">
	<jsp:include page="../../home/toolbar.jsp" />
	
	<span id="spanPageUpdate">
		<input class="toolbar_default" type="button" followPK="false" name="pageUpdate" value="修　改" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanPauseSave">
		<input class="toolbar_default" type="button" followPK="false" name="pauseSave" value="暫　存" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanDisComplete">
		<input class="toolbar_default" type="button" followPK="false" name="disComplete" value="分 類 完 成" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	
	<%
	if("1D".equals(obj.getQ_type()) || "2D".equals(obj.getQ_type())){
	%>
	<span id="spanAdditionalCase">
		<input class="toolbar_default" type="button" followPK="false" name="additionalCase" value="補　件" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<%
	}
	%>
	
	<span id="spanReAssign">
		<input class="toolbar_default" type="button" followPK="false" name="reAssign" value="案 件 改 派" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
    <span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit" value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
</td></tr>
<tr><td>
	<input class="toolbar_default" type="button" followPK="false" name="doOriginalVer" value="案件原始版本" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doAddDoc" value="案件補件紀錄" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doMailList" value="郵件清單紀錄" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doFlow" value="案件流程紀錄" onClick="whatButtonFireEvent(this.name)">	
	<input class="toolbar_default" type="button" followPK="false" name="doReportView" value="列印通報表" onClick="whatButtonFireEvent(this.name)">
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
	<td nowrap ID="t6" CLASS="tab_border2"><a id="aTab6" href="#" onClick="changeTab(6);">分類</a></td>
	<%
	if("1".equals(obj.getCosType()) && "Y".equals(obj.getIsNeedShowCos0007DbPage())){
	%>
	<td nowrap ID="t7" CLASS="tab_border2"><a id="aTab7" href="#" onClick="changeTab(7);">評估</a></td>
	<%
	}
	%>
	
</tr>
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
		<% request.setAttribute("qBean", obj); %>
		<jsp:include page="cosin0400f.jsp" />
		
		<%
		if("1D".equals(obj.getQ_type()) || "1A".equals(obj.getQ_type())){
		%>
		<jsp:include page="cosin1003f.jsp" />
		<%
		}else{
		%>
		<jsp:include page="cosin1004f.jsp" />
		<%
		}
		%>
		
		<%
		if("1".equals(obj.getCosType()) && "Y".equals(obj.getIsNeedShowCos0007DbPage())){
		%>
		<jsp:include page="cosin0803f.jsp">
			<jsp:param value="Tab7" name="tabId"/>
		</jsp:include>
		<%
		}
		%>
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
	case "pageUpdate":
		var flag = checkIsEdit(form1.id.value, form1.notifierName.value);
		if(flag){
			setFormItem("pauseSave,disComplete","O");
			if("<%=obj.getQ_type()%>" == "1D" || "<%=obj.getQ_type()%>" == "2D"){
				setFormItem("additionalCase","O");
			}
			setFormItem("pageUpdate","R");
			
			form1.state.value = buttonName;
			setAllOpen();
			$("#Tab7 [class*=field]").each(function(){
				if($(this).attr("type") == "text" || $(this).attr("type") == "textarea"){
					$(this).attr("readonly", true);
				}else{
					$(this).attr("disabled", true);
				}
				if($(this).attr("class") == "field_RO"){
					$(this).attr("disabled", true);
				}
			});
			
			chNotifierSource("<%=obj.getNotifierSource()%>");
			chTraffickWay("<%=obj.getTraffickWay()%>");
			chIsOtherDeptYn("<%=obj.getIsOtherDeptYn()%>");
			<%--
			chAdverseCondition("<%=obj.getAdverseCondition()%>");
			--%>
			chEvenContactYn("<%=obj.getEvenContactYn()%>");
			chDealWith("<%=obj.getDealWith()%>");
			if("<%=obj.getCosType()%>" == "1"){
				chIsDamageYn("<%=obj.getIsDamageYn()%>");
			}
			
			if("<%=obj.getQ_type()%>" == "1D" || "<%=obj.getQ_type()%>" == "1A"){
				if(form1.cos0006DbId.value == ""){
					getCos0003Db(form1.id.value);
				}
			}
		}
		break;
	case "pauseSave":
	case "disComplete":
		form1.state.value = buttonName;
		checkField();
		break;
	case "additionalCase":
		var js = "window.opener.updateAdditionalCase";
		var mailID = "";
		if("<%=obj.getCosType()%>" == "1"){
			mailID = "COS020001";
		}else{
			mailID = "COS030001";
		}
		openCenterWindow(800, 600, getVirtualPath() + "tcbw/conbg/conbg0001f.jsp?isAdd=Y&applNo=" + form1.applNo.value + "&isJS=" + js + "&mailID=" + mailID + "&id=" + form1.id.value);
		break;
	case "reAssign":
		var flag = checkIsEdit(form1.id.value, form1.notifierName.value);
		if(flag){
			if('<%=Common.get(competence)%>' != 'Y'){
				alert("您無改派權限!");
			}else{
				form1.state.value = buttonName;
				var code = "";
				var formCode = "";
				if("<%=obj.getCosType()%>" == "1"){
					code = "02"
					formCode = "COS02";
				}else{
					code = "06"
					formCode = "COS03";
				}
				
				openCenterWindow(800, 300, 'cosin0406f.jsp?id=' + form1.id.value + '&editId=' + form1.userID.value + "&code=" + code + "&formCode=" + formCode + "&caseStatus=" + "<%=obj.getStatus()%>");
			}
		}
		break;
	case "doDoQuit":
		deleteEdit(form1.id.value);
		
		form1.action = "cosin1001f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		break;
	case "doOriginalVer":
		popCos4001('<%=obj.getCos40001Id()%>');
		break;
	case "doAddDoc":
		popCon0004('<%=obj.getApplNo()%>','COS');
		break;
	case "doMailList":
		popCon0002('COS','<%=obj.getId()%>');
		break;
	case "doFlow":
		popCon2001('COS','<%=obj.getId()%>');
		break;
	//案件列印
	case "doReportView":
		toAsk();
	    break;
	}
}
</script>
</html>


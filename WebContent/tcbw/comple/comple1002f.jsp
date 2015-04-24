<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="COMPLE1001" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.comple.COMPLE1002F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
String competence = "Y";
if("1".equals(obj.getQ_type())){
	competence = Common.get(TCBWCommon.getCompetence("COS02", "05", User.getUserId(), "4"));
}else{
	competence = Common.get(TCBWCommon.getCompetence("COS03", "08", User.getUserId(), "4"));
}

if("pauseSave".equals(obj.getState())){
	try{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doUpdateType();
	}catch(Exception e){
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.comple.COMPLE1002F)obj.queryOne();
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
var popWinName = null;		// FOR 服務機構-查詢鈕用
function checkField(){
	var alertStr = "";
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
		setFormItem("pauseSave","R");
		$(":button[name$=Download]").each(function(){
			$(this).attr("disabled", false);
		});
		setFormItem("pageUpdate","O");
		
		$("table[id^=Tab]").each(function(){
			var id = $(this).attr("id"); 
			
			$("#" + id + " [class*=field]").each(function(){
				if($(this).attr("type")=="radio" || $(this).attr("type")=="button"){
					$(this).bind("click", function(){
						changeValue(id);
					});
				}else{
					$(this).bind("change", function(){
						changeValue(id);
					});
				}
			});
		});
	}else{
		alert("<%=obj.getErrorMsg()%>");
		deleteEdit("<%=obj.getId()%>");				// 解除LOCK	
		window.parent.frames['menu'].location.href = "../../home/dTreeMenu.jsp";
		
		form1.action = "comple1001f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
	}
}

function changeValue(id){
	switch(id){
	case "Tab1":
	case "Tab2":
	case "Tab3":
	case "Tab5":
		document.all.item("ch1").value = "Y";
		break;
	case "Tab6":
		document.all.item("ch2").value = "Y";
		break;
	case "Tab7":
		document.all.item("ch3").value = "Y";
		break;
	case "Tab8":
		document.all.item("ch4").value = "Y";
		break;
	case "Tab9":
		document.all.item("ch5").value = "Y";
		break;
	case "Tab10":
		document.all.item("ch6").value = "Y";
		break;
	}
}

function changeTab(tabId) {
	document.getElementById("t1").className = "tab_border2";
	document.getElementById("t2").className = "tab_border2";
	document.getElementById("t3").className = "tab_border2";
	document.getElementById("t4").className = "tab_border2";
	document.getElementById("t5").className = "tab_border2";
	document.getElementById("t6").className = "tab_border2";
	document.getElementById("t8").className = "tab_border2";
	document.getElementById("t10").className = "tab_border2";
	document.getElementById("aTab1").className = "";
	document.getElementById("aTab2").className = "";
	document.getElementById("aTab3").className = "";
	document.getElementById("aTab4").className = "";
	document.getElementById("aTab5").className = "";
	document.getElementById("aTab6").className = "";
	document.getElementById("aTab8").className = "";
	document.getElementById("aTab10").className = "";
	document.getElementById("Tab1").style.display = 'none';
	document.getElementById("Tab2").style.display = 'none';
	document.getElementById("Tab3").style.display = 'none';
	document.getElementById("Tab4").style.display = 'none';
	document.getElementById("Tab5").style.display = 'none';
	document.getElementById("Tab6").style.display = 'none';
	document.getElementById("Tab8").style.display = 'none';
	document.getElementById("Tab10").style.display = 'none';

	if("<%=obj.getCosType()%>" == "1"){
		if("<%=obj.getIsNeedShowCos0009DbPage()%>" == "Y"){
			document.getElementById("t7").className = "tab_border2";
			document.getElementById("aTab7").className = "";
			document.getElementById("Tab7").style.display = 'none';
		}
	}else{
		document.getElementById("t7").className = "tab_border2";
		document.getElementById("aTab7").className = "";
		document.getElementById("Tab7").style.display = 'none';
	}
	
	if("<%=obj.getCosType()%>"=="1" && "<%=obj.getIsNeedShowCos0007DbPage()%>"=="Y"){
		document.getElementById("t9").className = "tab_border2";
		document.getElementById("aTab9").className = "";
		document.getElementById("Tab9").style.display = 'none';
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
	}else if(tabId == 8){
		document.getElementById("t8").className = "tab_border1";
		document.getElementById("Tab8").style.display = '';
		document.getElementById("aTab8").className = "text_w";
	}else if(tabId == 9){
		document.getElementById("t9").className = "tab_border1";
		document.getElementById("Tab9").style.display = '';
		document.getElementById("aTab9").className = "text_w";
	}else if(tabId == 10){
		document.getElementById("t10").className = "tab_border1";
		document.getElementById("Tab10").style.display = '';
		document.getElementById("aTab10").className = "text_w";
	}else{
		document.getElementById("t1").className = "tab_border1";
		document.getElementById("Tab1").style.display = '';
		document.getElementById("aTab1").className = "text_w";	
	}
}

function goBackQuery(){
	deleteEdit("<%=obj.getId()%>");				// 解除LOCK	
	window.parent.frames['menu'].location.href = "../../home/dTreeMenu.jsp";
	
	form1.action = "comple1001f.jsp";
	form1.state.value = "queryAll";
	form1.submit();
}

//不良品時，變動此欄位時，顯示或隱藏
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
	}else{
	//	form1.otherExplain.value = "";
		form1.otherIsDamageYn.value = "";
		form1.otherIsDamageYn.disabled = true;
		form1.otherInformation.value = "";
		form1.otherInformation.disabled = true;
	}
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
	
	<%=obj.getCFileRowSBuilder()%>
	
	if("<%=obj.getCosType()%>" == "1"){
		if("<%=obj.getIsNeedShowCos0009DbPage()%>" == "Y"){
			<%=obj.getCOSVRFileRowSBuilder()%>
		}
		<%=obj.getCOS0012DbRowSBuilder()%>
		<%=obj.getCOSPTFileRowSBuilder()%>
		<%=obj.getCOS0013DbRowSBuilder()%>
		<%=obj.getCOSRTFileRowSBuilder()%>
	}else{
		<%=obj.getCOS0004DbRowSBuilder()%>
		<%=obj.getCOS0005DbRowSBuilder()%>
		<%=obj.getCOSSDFileRowSBuilder()%>
		<%=obj.getCOSDPFileRowSBuilder()%>
		
		<%=obj.getCOSVRFileRowSBuilder()%>
		<%=obj.getCOSHAFileRowSBuilder()%>
		<%=obj.getCOSEBFileRowSBuilder()%>
		<%=obj.getCOSMSFileRowSBuilder()%>
	}
	
	$(":radio[name=isDamageYn]").bind("click", function(){
		chIsDamageYn($(this).val());
	});
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
	<jsp:include page="comple1001q.jsp" />
</div>

<!--Toolbar區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bgToolbar" style="text-align:left">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="isCloseUserInfo" value="<%=obj.getIsCloseUserInfo()%>"> <!-- 列印時是否遮蔽個資 -->
	<input type="hidden" name="con1003DbId" value="<%=obj.getCon1003DbId()%>">
	
	<input type="hidden" name="ch1">
	<input type="hidden" name="ch2">
	<input type="hidden" name="ch3">
	<input type="hidden" name="ch4">
	<input type="hidden" name="ch5">
	<input type="hidden" name="ch6">
	<jsp:include page="../../home/toolbar.jsp" />
	
	<span id="spanPageUpdate">
		<input class="toolbar_default" type="button" followPK="false" name="pageUpdate" value="修　改" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanPauseSave">
		<input class="toolbar_default" type="button" followPK="false" name="pauseSave" value="暫　存" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<%--
	<span id="spanEndCase">
		<input class="toolbar_default" type="button" followPK="false" name="endCase" value="結 案 完 成" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanReAssign">
		<input class="toolbar_default" type="button" followPK="false" name="reAssign" value="案 件 改 派" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	 --%>
    <span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit" value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
</td></tr>
<%--
<tr><td>
	<input class="toolbar_default" type="button" followPK="false" name="doOriginalVer" value="案件原始版本" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doAddDoc" value="案件補件紀錄" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doMailList" value="郵件清單紀錄" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doFlow" value="案件流程紀錄" onClick="whatButtonFireEvent(this.name)">	
	<input class="toolbar_default" type="button" followPK="false" name="doReportView" value="列印通報表" onClick="whatButtonFireEvent(this.name)">
</td></tr>
 --%>
</table>

<!--頁籤區============================================================-->
<TABLE CELLPADDING="0" CELLSPACING="0" valign="top">
<tr>
	<td ID="t1" CLASS="tab_border1"><a id="aTab1" href="#" onClick="changeTab(1);">基本資料</a></td>
	<td ID="t2" CLASS="tab_border2"><a id="aTab2" href="#" onClick="changeTab(2);">不良事件</a></td>
	<td ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">不良品</a></td>
	<td ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">不良反應</a></td>
	<td ID="t5" CLASS="tab_border2"><a id="aTab5" href="#" onClick="changeTab(5);">相關附件</a></td>
	<td ID="t6" CLASS="tab_border2"><a id="aTab6" href="#" onClick="changeTab(6);">分類</a></td>
	<%
	if("1".equals(obj.getCosType()) && "Y".equals(obj.getIsNeedShowCos0007DbPage())){
	%>
	<td nowrap ID="t9" CLASS="tab_border2"><a id="aTab9" href="#" onClick="changeTab(9);">評估</a></td>
	<%
	}
	%>
	
	<%
	if("1".equals(obj.getCosType()) && "N".equals(obj.getIsNeedShowCos0009DbPage())){
		
	}else{
	%>
	<td ID="t7" CLASS="tab_border2"><a id="aTab7" href="#" onClick="changeTab(7);">廠商回覆</a></td>
	<%
	}
	%>
	<td ID="t8" CLASS="tab_border2"><a id="aTab8" href="#" onClick="changeTab(8);">處理</a></td>
	<td ID="t10" CLASS="tab_border2"><a id="aTab10" href="#" onClick="changeTab(10);">結案</a></td>
</tr>
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
		<% request.setAttribute("qBean", obj); %>
		<jsp:include page="../cosin/cosin0400f.jsp">
			<jsp:param value="Y" name="isSpecial"/>
		</jsp:include>
		
		<%-- 分類頁籤 --%>
		<%
		if("1".equals(obj.getCosType())){
		%>
		<jsp:include page="../cosin/cosin1003f.jsp" >
			<jsp:param value="Tab6" name="tabId"/>
		</jsp:include>
		<%
		}else{
		%>
		<jsp:include page="../cosin/cosin1004f.jsp" >
			<jsp:param value="Tab6" name="tabId"/>
		</jsp:include>
		<%
		}
		%>
		
		<%-- 評估頁籤 --%>
		<%
		if("1".equals(obj.getCosType()) && "Y".equals(obj.getIsNeedShowCos0007DbPage())){
		%>
		<jsp:include page="../cosin/cosin0803f.jsp">
			<jsp:param value="Tab9" name="tabId"/>
		</jsp:include>
		<%
		}
		%>
		
		<%-- 廠商回覆頁籤 --%>
		<%
		if("1".equals(obj.getCosType()) && "Y".equals(obj.getIsNeedShowCos0009DbPage())){
		%>
		<jsp:include page="../cosin/cosin0603f.jsp" >
			<jsp:param value="Tab7" name="tabId"/>
		</jsp:include>
		<%
		}
		%>
		
		<%
		if("2".equals(obj.getCosType())){
		%>
		<jsp:include page="../cosin/cosin0604f.jsp" >
			<jsp:param value="Tab7" name="tabId"/>
		</jsp:include>
		<%
		}
		%>
		
		<%-- 處理頁籤 --%>
		<%
		if("1".equals(obj.getCosType())){
		%>
		<jsp:include page="../cosin/cosin0605f.jsp" >
			<jsp:param value="Tab8" name="tabId"/>
		</jsp:include>
		<%
		}else{
		%>
		<jsp:include page="../cosin/cosin0606f.jsp" >
			<jsp:param value="Tab8" name="tabId"/>
		</jsp:include>
		<%
		}
		%>
		
		<%-- 結案頁籤 --%>
		<%
		if("1".equals(obj.getCosType())){
		%>
		<jsp:include page="../cosin/cosin0903f.jsp" >
			<jsp:param value="Tab10" name="tabId"/>
		</jsp:include>
		<%
		}else{
		%>
		<jsp:include page="../cosin/cosin0904f.jsp" >
			<jsp:param value="Tab10" name="tabId"/>
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
			form1.state.value = buttonName;
			setFormItem("pauseSave", "O");
			setFormItem("pageUpdate", "R");
			
			$("#Tab1 [class*=field], #Tab2 [class*=field], #Tab3 [class*=field], #Tab5 [class*=field], #Tab6 [class*=field], #Tab7 [class*=field], " + 
			  "#Tab8 [class*=field], #Tab9 [class*=field], #Tab10 [class*=field]").each(function(){
				if($(this).attr("type") == "text" || $(this).attr("type") == "textarea" || $(this).attr("type") == "password"){
					$(this).attr("readonly", "");
				}else{
					$(this).attr("disabled", "");
				}
				if($(this).attr("class") == "field_RO"){
					$(this).attr("disabled", "disabled");
				}
				if($(this).attr("name") == "notifierTitle"){
					$(this).attr("disabled", "disabled");
				}
			});
			
			if(!$("#Tab1 [name=doOpenUserInfo]").attr("disabled")){
				openUserInfo();
			}
			
			document.all.item("notifierArea").disabled = true;
			document.all.item("notifierZipCode").disabled = true;
			document.all.item("notifierTitle").disabled = true;
			for(var i=0; i<document.all.item("notifierType").length; i++){
				document.all.item("notifierType")[i].disabled = true;
			}
		}
		break;
	case "pauseSave":
		form1.action = "comple1002f.jsp";
		form1.state.value = "pauseSave";
		beforeSubmit();
		form1.submit();
		break;
	case "doDoQuit":
		deleteEdit(form1.id.value);
		
		form1.action = "comple1001f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		break;
	}
}
</script>
</html>


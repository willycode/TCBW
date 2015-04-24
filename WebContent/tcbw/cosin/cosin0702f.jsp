<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="COSIN0701" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN0702F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
if("pauseSave".equals(obj.getState()) || "caseBackUpdate".equals(obj.getState())){
	try{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doUpdateType();
	}catch(Exception e){
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.cosin.COSIN0702F)obj.queryOne();
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
	if(form1.state.value == "caseBackUpdate"){
		<%
		if("Y".equals(obj.getIsCombineBussinessPage())){
		%>
		
		
		
		<%
		}else{
		%>
			if(form1.cosType.value == "1"){
			//	alertStr += checkEmpty(form1.replyDate9, "回覆日期");
			//	alertStr += checkDate(form1.replyDate9, "回覆日期");
			//	alertStr += checkDate(form1.predictDate, "預計完成日期");
				<%=TCBWCommon.getCheckFiled("COS01", "COS07")%>
			}else{
			//	alertStr += checkEmpty(form1.replyDate10, "回覆日期");
			//	alertStr += checkDate(form1.replyDate10, "回覆日期");
				<%=TCBWCommon.getCheckFiled("COS01", "COS09")%>
			}
		<%
		}
		%>
	}

	if(form1.state.value == "caseBackUpdate" || form1.state.value == "pauseSave"){
		<%
		if("Y".equals(obj.getIsCombineBussinessPage())){
		%>
			alertStr += checkLen(form1.similarComplaint0910, "類似客訴", 200);
			alertStr += checkLen(form1.precaution0910, "預防矯正措施", 500);
			alertStr += checkLen(form1.handling0910, "目前處理情形/調查報告", 500);
			alertStr += checkLen(form1.remark0910, "補充說明", 200);
		<%
		}else{
		%>
			if(form1.cosType.value == "1"){
				alertStr += checkLen(form1.handling, "目前處理情形/調查報告", 500);
				alertStr += checkLen(form1.precaution9, "預防矯正措施", 500);
				alertStr += checkLen(form1.similarComplaint, "類似客訴", 200);
				alertStr += checkLen(form1.remark9, "補充說明", 200);			
			}else{
				alertStr += checkLen(form1.dealWith10, "類似客訴案件之後續處理情形", 200);
				alertStr += checkLen(form1.replyOther10, "其他回覆", 200);	
			}
		<%
		}
		%>
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
		setFormItem("pauseSave,caseBackUpdate","R");
		$(":button[name$=Download]").each(function(){
			$(this).attr("disabled", false);
		});
		
		if("<%=obj.getIsCombineBussinessPage()%>" == "Y"){
			if("<%=obj.getCos0910Id()%>" == ""){
				setFormItem("pageUpdate","R");
			}
		}else{
			if("<%=obj.getCosType()%>" == "1"){
				if("<%=obj.getCos0009DbId()%>" == ""){
					setFormItem("pageUpdate","R");
				}
			}else{
				if("<%=obj.getCos0010DbId()%>" == ""){
					setFormItem("pageUpdate","R");
				}
			}
		}
		
		if("Y" == "<%=obj.getIsClose10()%>" || "Y" == "<%=obj.getIsClose09()%>" || "Y" == "<%=obj.getIsClose0910()%>"){
			setFormItem("pageUpdate,pauseSave,caseBackUpdate","R");
		}
	}else{
		alert("<%=obj.getErrorMsg()%>");
		deleteEdit("<%=obj.getId()%>");				// 解除LOCK	
		
		form1.action = "cosin0701f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
	}
}

function openCos0009DbOrCos0010DbPage(){
	$("#Tab7 [class*=field]").each(function(){
		if($(this).attr("type") == "text" || $(this).attr("type") == "textarea"){
			$(this).attr("readonly", false);
		}else{
			$(this).attr("disabled", false);
		}
		
		if($(this).attr("class") == "field_RO"){
			$(this).attr("disabled", true);
		}
	});
}

function changeTab(tabId) {
	document.getElementById("t2").className = "tab_border2";
	document.getElementById("t3").className = "tab_border2";
	document.getElementById("t4").className = "tab_border2";
	document.getElementById("aTab2").className = "";
	document.getElementById("aTab3").className = "";
	document.getElementById("aTab4").className = "";
	document.getElementById("Tab2").style.display = 'none';
	document.getElementById("Tab3").style.display = 'none';
	document.getElementById("Tab4").style.display = 'none';

	document.getElementById("t7").className = "tab_border2";
	document.getElementById("aTab7").className = "";
	document.getElementById("Tab7").style.display = 'none';

	if(tabId == 3){
		document.getElementById("t3").className = "tab_border1";
		document.getElementById("Tab3").style.display = '';
		document.getElementById("aTab3").className = "text_w";
	}else if(tabId == 4){
		document.getElementById("t4").className = "tab_border1";
		document.getElementById("Tab4").style.display = '';
		document.getElementById("aTab4").className = "text_w";
	}else if(tabId == 7){
		document.getElementById("t7").className = "tab_border1";
		document.getElementById("Tab7").style.display = '';
		document.getElementById("aTab7").className = "text_w";
	}else{
		document.getElementById("t2").className = "tab_border1";	
		document.getElementById("Tab2").style.display = '';
		document.getElementById("aTab2").className = "text_w";	
	}
}

$(function(){
	if(win != null){
		win.close();
	}
	setDisplayItem('spanInsert,spanUpdate,spanQueryAll,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden','H');
	setDisplayItem("showPermit,showCaseNo", "H");
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
	<%=obj.getCOSVRFileRowSBuilder()%>
	
	if("<%=obj.getIsCombineBussinessPage()%>" == "Y"){
		$(":radio[name=isComplaintYn0910]").bind("click", function(){
			chIsComplaintYn0910($(this).val());
		});
	}else{
		if("<%=obj.getCosType()%>" == "2"){
			$(":radio[name=isComplaintYn10]").bind("click", function(){
				chIsComplaintYn10($(this).val());
			});
			<%=obj.getCOS0005DbRowSBuilder()%>
		}
	}
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
	<jsp:include page="cosin0701q.jsp" />
</div>

<!--Toolbar區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bgToolbar" style="text-align:left">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="pageSize" value="<%=obj.getPageSize()%>">			<!-- FOR 查詢頁面定位使用 -->
	<input type="hidden" name="currentPage" value="<%=obj.getCurrentPage()%>">		<!-- FOR 查詢頁面定位使用 -->
	<input type="hidden" name="listContainerActiveRowId" value="<%=obj.getListContainerActiveRowId()%>">
	<input type="hidden" name="cosType" value="<%=obj.getCosType()%>">
	<input type="hidden" name="notifierName" value="<%=obj.getNotifierName()%>">
	<input type="hidden" name="isCombineBussinessPage" value="<%=obj.getIsCombineBussinessPage()%>">
	
	<jsp:include page="../../home/toolbar.jsp" />
	<span id="spanPageUpdate">
		<input class="toolbar_default" type="button" followPK="false" name="pageUpdate" value="修　改" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanPauseSave">
		<input class="toolbar_default" type="button" followPK="false" name="pauseSave" value="暫　存" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanCaseBackUpdate">
		<input class="toolbar_default" type="button" followPK="false" name="caseBackUpdate" value="回 覆 完 成" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
    <span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit" value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
</td></tr>
</table>

<!--頁籤區============================================================-->
<TABLE CELLPADDING="0" CELLSPACING="0" valign="top">
<tr>
	<td ID="t2" CLASS="tab_border2"><a id="aTab2" href="#" onClick="changeTab(2);">不良事件</a></td>
	<td ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">不良品</a></td>
	<td ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">不良反應</a></td>
	<td ID="t7" CLASS="tab_border2"><a id="aTab7" href="#" onClick="changeTab(7);">廠商回覆</a></td>
</tr>
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
		<% request.setAttribute("qBean", obj); %>
		<jsp:include page="cosin0400f.jsp" >
			<jsp:param value="Y" name="isCloseNunBussiness"/>
		</jsp:include>
		
		<%-- 廠商回覆頁籤 --%>
		<%
		if("Y".equals(obj.getIsCombineBussinessPage())){
		%>
		<jsp:include page="cosin0612f.jsp" >
			<jsp:param value="Tab7" name="tabId"/>
		</jsp:include>
		<%
		}else{
		%>
			<%
			if("1B".equals(obj.getQ_type())){
			%>
			<jsp:include page="cosin0603f.jsp" >
				<jsp:param value="Tab7" name="tabId"/>
			</jsp:include>
			<%
			}
			%>
			
			<%
			if("2B".equals(obj.getQ_type())){
			%>
			<jsp:include page="cosin0604f.jsp" >
				<jsp:param value="Tab7" name="tabId"/>
			</jsp:include>
			<%
			}
			%>
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
			setFormItem("pauseSave,caseBackUpdate","O");
			setFormItem("pageUpdate","R");
			
			openCos0009DbOrCos0010DbPage();
			if("<%=obj.getIsCombineBussinessPage()%>" == "Y"){
				setFormItem("replyDate0910,btn_replyDate0910","R");
				chIsComplaintYn0910("<%=obj.getIsComplaintYn0910()%>");
			}else{
				if("<%=obj.getCosType()%>" == "1"){
					setFormItem("replyDate9,btn_replyDate9","R");
				}else{
					setFormItem("replyDate10,btn_replyDate10","R");
					chIsComplaintYn10("<%=obj.getIsComplaintYn10()%>");
				}
			}
		}
		break;
	case "pauseSave":
		checkField();
		break;
	case "caseBackUpdate":
		form1.state.value = buttonName;
		checkField();
		break;
	case "doDoQuit":
		deleteEdit(form1.id.value);
		
		form1.action = "cosin0701f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		break;
	}
}
</script>
</html>


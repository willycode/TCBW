<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="COSIN0401" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN0402F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
String competence = TCBWCommon.getCompetence("COS01", "01", User.getUserId(), "4");						

if("pauseSave".equals(obj.getState()) || "acceptCase".equals(obj.getState())){
	try{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doUpdateType();
	}catch(Exception e){
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}else if("unAcceptCase".equals(obj.getState())){
	try{
		obj.doUnAcceptCase();
	}catch(Exception e){
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.cosin.COSIN0402F)obj.queryOne();
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
	
	if(form1.state.value == "acceptCase"){
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
		alertStr += validateCOS0004DbTable();					// 相關檢查及檢驗數據
		alertStr += validateCOS0005DbTable();					// 併用的其他化粧品
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
		
		setFormItem("pauseSave,acceptCase,unAcceptCase","R");
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
		
		form1.action = "cosin0401f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
	}
}

function changeTab(tabId) {
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
	}else{
		document.getElementById("t1").className = "tab_border1";
		document.getElementById("Tab1").style.display = '';
		document.getElementById("aTab1").className = "text_w";	
	}
}

function goBackQuery(){
	deleteEdit(form1.id.value);
	window.parent.frames['menu'].location.href = "../../home/dTreeMenu.jsp";
	
	form1.action = "cosin0401f.jsp";
	form1.state.value = "queryAll";
	form1.submit();
}

function doUnAcceptCase(){
	form1.state.value = "unAcceptCase";
	beforeSubmit();
	form1.submit();
}

function toAsk(){
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;


	// 點選不遮蔽個資時記錄相關LOG
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
	form1.action = "cosin0402f.jsp" ;
	form1.target = "_self";
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
	
	if("<%=obj.getCosType()%>" == "2" || "<%=obj.getCosType()%>" == "3"){
		<%=obj.getCOS0004DbRowSBuilder()%>
		<%=obj.getCOS0005DbRowSBuilder()%>
		<%=obj.getCOSSDFileRowSBuilder()%>
		<%=obj.getCOSDPFileRowSBuilder()%>
		<%=obj.getCOSIDFileRowSBuilder()%>
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
	<jsp:include page="cosin0401q.jsp" />
</div>

<!--Toolbar區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bgToolbar" style="text-align:left">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="isCloseUserInfo" value="<%=obj.getIsCloseUserInfo()%>"> <!-- 列印時是否遮蔽個資 -->
	<jsp:include page="../../home/toolbar.jsp" />
	
	<span id="spanPageUpdate">
		<input class="toolbar_default" type="button" followPK="false" name="pageUpdate" value="修　改" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanPauseSave">
		<input class="toolbar_default" type="button" followPK="false" name="pauseSave" value="暫　存" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanAcceptCase">
		<input class="toolbar_default" type="button" followPK="false" name="acceptCase" value="受　理" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanUnAcceptCase">
		<input class="toolbar_default" type="button" followPK="false" name="unAcceptCase" value="撤　案" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanReAssign">
		<input class="toolbar_default" type="button" followPK="false" name="reAssign" value="案 件 改 派" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<input class="toolbar_default" type="button" followPK="false" name="doReportView" value="列印通報表" onClick="whatButtonFireEvent(this.name)">
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
		<% request.setAttribute("qBean", obj); %>
		<jsp:include page="cosin0400f.jsp" />
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
			setFormItem("pauseSave,acceptCase,unAcceptCase","O");
			setFormItem("pageUpdate","R");
			form1.state.value = buttonName;
			setAllOpen();
			
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
		}
		break;
	case "pauseSave":
	case "acceptCase":
		form1.state.value = buttonName;
		checkField();
		break;
	case "unAcceptCase":
		var js = "window.opener.doUnAcceptCase";
		openCenterWindow(800, 600, getVirtualPath() + "tcbw/conbg/conbg0001f.jsp?isAdd=N&applNo=" + form1.applNo.value + "&isJS=" + js + "&mailID=COS010001&id=" + form1.id.value);
		break;
	case "reAssign":
		var flag = checkIsEdit(form1.id.value, form1.notifierName.value);
		if(flag){
			if('<%=Common.get(competence)%>' != 'Y'){
				alert("您無改派權限!");
			}else{
				form1.state.value = buttonName;
				openCenterWindow(800, 300, 'cosin0406f.jsp?id=' + form1.id.value + '&editId=' + form1.userID.value + "&code=01&formCode=COS01&caseStatus=" + "<%=obj.getStatus()%>");
			}
		}
		break;
	case "doDoQuit":
		deleteEdit(form1.id.value);
		
		form1.action = "cosin0401f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		break;
	case "doReportView":
		toAsk();
		break;
	}
}
</script>
</html>


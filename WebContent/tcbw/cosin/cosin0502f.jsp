<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="COSIN0501" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN0502F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
String competence = "Y";
if("1P".equals(obj.getQ_type())){
	competence = Common.get(TCBWCommon.getCompetence("COS02", "03", User.getUserId(), "4"));
}else{
	competence = Common.get(TCBWCommon.getCompetence("COS03", "07", User.getUserId(), "4"));
}

if("updateStatus".equals(obj.getState())){
	try{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doUpdateType();
	}catch(Exception e){
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.cosin.COSIN0502F)obj.queryOne();
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
		setFormItem("sendDeptORBussiness,sendDeptMail,sendBussinessMail","R");
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
		
		form1.action = "cosin0501f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
	}
}

function report(isClose){
	form1.isCloseUserInfo.value = isClose;
	form1.action = "cosin0402p.jsp" ;
	form1.target = "_blank";
	beforeSubmit();
	form1.submit();
	form1.action = "cosin0502f.jsp" ;
	form1.target = "_self";
}

function toAsk(){
	var jscript = "";	
	var prop = "";
	var windowTop = (document.body.clientHeight-400)/2+100;
	var windowLeft = (document.body.clientWidth-400)/2+100;

	// 點選不遮蔽個資時記錄相關LOG
	var q = "code=HFR01";  					// 通報表代碼，各通報表請參照CON1007_DB裡的FORMCODE欄位
	q += "&methodName=print";
	q += "&db=Hfr0001Db"; 					// 通報表主檔DB，各通報表請修改為各自的內網主檔DB
	q += "&hql=select id,applNo,caseOwner,notifierName from Hfr0001Db where id = " + form1.id.value; //查詢通報表主檔DB相關欄位資訊，做為記錄LOG用
	
	// 彈出式視窗大小資訊
	prop = prop+"width=800,height=100,";
	prop = prop+"top="+windowTop+",";
	prop = prop+"left="+windowLeft+",";
	prop = prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	
	// 呼叫共用的conin1001f.jsp並帶入相關參數(q)，原report()方法改在這支jsp裡呼叫
	returnWindow = window.open(getVirtualPath() + "tcbw/conin/conin1001f.jsp?" + q, "", prop);  
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

// 不良品詢問函
function openCos1Mail(){
	if(form1.recentlyMeasure.value != ""){
		var m = form1.recentlyMeasure.value; 
		var s = form1.isSend06Mail.value; 
		if(m.indexOf("1") != -1 && s.indexOf("1") < 0){
			setFormItem("sendDeptMail","O");
		}
	//	if(m.indexOf("2") != -1 && s.indexOf("2") < 0){
			setFormItem("sendBussinessMail","O");
	//	}
	}
}

function openCosDtl1Mail(){
	openCenterWindow(900, 500, 'cosin0503f.jsp?field1=dept1');						// 開啟衛生單位選取畫面
}

function openDeptMail(){
	var con1003DbId = form1.dept1.value;
	var js = "window.opener.chkChangStatus('1')";
	
	if(confirm("是否發送信件 ?")){
		openCenterWindow(800, 600, getVirtualPath() + "tcbw/conbg/conbg0001f.jsp?isAdd=N&applNo=" + form1.applNo.value + "&isJS=" + js + "&mailID=COS020004&id=" + form1.id.value + "&c3Id=" + con1003DbId);
	}else{
		chkChangStatus('1');
	}
}

function openCosDtl2Mail(){
	var openMail = checkSendBussinessMail("2");
	if(openMail){
		var js = "window.opener.chkChangStatus('2')";
		if(win != null){
			win.close();
		}
		openCenterWindow(800, 600, getVirtualPath() + "tcbw/conbg/conbg0001f.jsp?isAdd=N&applNo=" + form1.applNo.value + "&isJS=" + js + "&mailID=COS020005&id=" + form1.id.value);
	}else{
		chkChangStatus('2');
	}
}

// 不良反應詢問函
function openCos2Mail(){
	var js = "window.opener.changeStatus";
	openCenterWindow(800, 600, getVirtualPath() + "tcbw/conbg/conbg0001f.jsp?isAdd=N&applNo=" + form1.applNo.value + "&isJS=" + js + "&mailID=COS030002&id=" + form1.id.value);
}

function changeStatus(){
	form1.state.value = "updateStatus";
	form1.action = "cosin0502f.jsp";
	beforeSubmit();
	form1.submit();
}

// 檢核有無關聯號，若有再檢核需不需要發送信件
function checkSendBussinessMail(type){
	var openMail = false;
	var nDate = "";
	
	var p = "<%=obj.getApplno1() %>";
	if(p != ""){
		var x = getRemoteData("../../ajax/jsonCheckSendBussinessMail.jsp", p + "&q1=" + type);
		if(x!=null && x.length>0){
			var json = eval ('(' + x + ')');
			if(json.obj0 == "Y"){
				openMail = true;
			}else{
				nDate = json.obj1;
			}
		}else{
			openMail = true;
		}
	}else{
		openMail = true;
	}
	
	if(openMail){
		return true;
	}else{
		alert("因本案件之關聯不良品案件\n已於" + nDate + "發送過廠商詢問函\n將不再重複發送廠商詢問函");
		return false;
	}
}

function goBackQuery(){
	deleteEdit(form1.id.value);
	window.parent.frames['menu'].location.href = "../../home/dTreeMenu.jsp";
	
	form1.action = "cosin0501f.jsp";
	form1.state.value = "queryAll";
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
	
	/*
	$(":radio[name=notifierSource]").bind("click", function(){
		chNotifierSource($(this).val());
	});
	$(":radio[name=traffickWay]").bind("click", function(){
		chTraffickWay($(this).val());
	});
	$(":radio[name=isOtherDeptYn]").bind("click", function(){
		chIsOtherDeptYn($(this).val());
	});
	$(":radio[name=adverseCondition]").bind("click", function(){
		chAdverseCondition($(this).val());
	});
	$(":radio[name=exteriorError]").bind("click", function(){
		chExteriorError($(this).val());
	});
	$(":radio[name=packageError]").bind("click", function(){
		chPackageError($(this).val());
	});
	$(":radio[name=isDamageYn]").bind("click", function(){
		chIsDamageYn($(this).val());
	});
	*/
	
	<%=obj.getCFileRowSBuilder()%>
	
	if("<%=obj.getCosType()%>" == "2"){
		<%=obj.getCOS0004DbRowSBuilder()%>
		<%=obj.getCOS0005DbRowSBuilder()%>
		<%=obj.getCOSSDFileRowSBuilder()%>
	}
});

function chkChangStatus(sendType){
	if(form1.isSend06Mail.value.length > 0){
		form1.isSend06Mail.value += ",";
	}
	form1.isSend06Mail.value += sendType;
	changeStatus();
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
	<jsp:include page="cosin0501q.jsp" />
</div>

<!--Toolbar區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bgToolbar" style="text-align:left">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="isCloseUserInfo" value="<%=obj.getIsCloseUserInfo()%>"> <!-- 列印時是否遮蔽個資 -->
	<input type="hidden" name="recentlyMeasure" value="<%=obj.getRecentlyMeasure()%>">
	<input type="hidden" name="isSend06Mail" value="<%=obj.getIsSend06Mail()%>">
	<input type="hidden" name="dept1">
	<jsp:include page="../../home/toolbar.jsp" />
	
	<span id="spanPageUpdate">
		<input class="toolbar_default" type="button" followPK="false" name="pageUpdate" value="修　改" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<%
	if("1P".equals(obj.getQ_type())){
	%>
		<span id="spanSendDeptMail">
			<input class="toolbar_default" type="button" followPK="false" name="sendDeptMail" value="衛 生 單 位" onClick="openCosDtl1Mail()">&nbsp;
		</span>
		<span id="spanSendBussinessMail">
			<input class="toolbar_default" type="button" followPK="false" name="sendBussinessMail" value="廠 商 通 知" onClick="openCosDtl2Mail()">&nbsp;
		</span>
	<%
	}else{
	%>
	<span id="spanSendDeptORBussiness">
		<input class="toolbar_default" type="button" followPK="false" name="sendDeptORBussiness" value="廠 商 通 知" onClick="whatButtonFireEvent(this.name)">&nbsp;
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
		if("1P".equals(obj.getQ_type())){
		%>
		<jsp:include page="cosin1003f.jsp" >
			<jsp:param value="Tab6" name="tabId"/>
		</jsp:include>
		<%
		}else{
		%>
		<jsp:include page="cosin1004f.jsp" >
			<jsp:param value="Tab6" name="tabId"/>
		</jsp:include>
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
			setFormItem("sendDeptORBussiness","O");
			setFormItem("pageUpdate","R");
			openCos1Mail();
			form1.state.value = buttonName;
		}
		break;
	case "sendDeptORBussiness":
		if("<%=obj.getCosType()%>" != "1"){
			var openMail = checkSendBussinessMail("1");
			if(openMail){
				openCos2Mail();
			}else{
				changeStatus();
			}
		}
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
					code = "03"
					formCode = "COS02";
				}else{
					code = "07"
					formCode = "COS03";
				}
				openCenterWindow(800, 300, 'cosin0406f.jsp?id=' + form1.id.value + '&editId=' + form1.userID.value + "&code=" + code + "&formCode=" + formCode + "&caseStatus=" + "<%=obj.getStatus()%>");
			}
		}
		break;
	case "doDoQuit":
		deleteEdit(form1.id.value);
		
		form1.action = "cosin0501f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		break;
	case "doOriginalVer":
		popCos4001('<%=obj.getCos40001Id()%>');
		break;
	case "doAddDoc":
		popCon0004('<%=obj.getApplNo()%>');
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


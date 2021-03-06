<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="COSIN0901" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN0902F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
String competence = "Y";
if("1E".equals(obj.getQ_type())){
	competence = Common.get(TCBWCommon.getCompetence("COS02", "05", User.getUserId(), "4"));
}else{
	competence = Common.get(TCBWCommon.getCompetence("COS03", "08", User.getUserId(), "4"));
}

if("pauseSave".equals(obj.getState()) || "endCase".equals(obj.getState())){
	try{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doUpdateType();
	}catch(Exception e){
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.cosin.COSIN0902F)obj.queryOne();
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
	if(form1.state.value == "endCase"){
		if(form1.cosType.value == "1"){
			<%=TCBWCommon.getCheckFiled("COS01", "COS12")%>
		}else{
			<%=TCBWCommon.getCheckFiled("COS01", "COS13")%>
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
		setFormItem("pauseSave,endCase","R");
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
		setFormItem("pageUpdate","O");
		<%=TCBWCommon.getIsComplete("COS01")%>
	}else{
		alert("<%=obj.getErrorMsg()%>");
		deleteEdit("<%=obj.getId()%>");				// 解除LOCK	
		window.parent.frames['menu'].location.href = "../../home/dTreeMenu.jsp";
		
		form1.action = "cosin0901f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
	}
}

function report(isClose)
{
	form1.isCloseUserInfo.value=isClose;
	form1.action = "cosin0402p.jsp" ;
	form1.target = "_blank";
	beforeSubmit();
	form1.submit();
	form1.action = "cosin0902f.jsp" ;
	form1.target = "_self";
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
	
	form1.action = "cosin0901f.jsp";
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
	<jsp:include page="cosin0901q.jsp" />
</div>

<!--Toolbar區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bgToolbar" style="text-align:left">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="isCloseUserInfo" value="<%=obj.getIsCloseUserInfo()%>"> <!-- 列印時是否遮蔽個資 -->
	<input type="hidden" name="con1003DbId" value="<%=obj.getCon1003DbId()%>">
	<jsp:include page="../../home/toolbar.jsp" />
	
	<span id="spanPageUpdate">
		<input class="toolbar_default" type="button" followPK="false" name="pageUpdate" value="修　改" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanPauseSave">
		<input class="toolbar_default" type="button" followPK="false" name="pauseSave" value="暫　存" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanEndCase">
		<input class="toolbar_default" type="button" followPK="false" name="endCase" value="結 案 完 成" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
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
		<jsp:include page="cosin0400f.jsp" />
		
		<%-- 分類頁籤 --%>
		<%
		if("1".equals(obj.getCosType())){
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
		
		<%-- 評估頁籤 --%>
		<%
		if("1".equals(obj.getCosType()) && "Y".equals(obj.getIsNeedShowCos0007DbPage())){
		%>
		<jsp:include page="cosin0803f.jsp">
			<jsp:param value="Tab9" name="tabId"/>
		</jsp:include>
		<%
		}
		%>
		
		<%-- 廠商回覆頁籤 --%>
		<%
		if("1".equals(obj.getCosType()) && "Y".equals(obj.getIsNeedShowCos0009DbPage())){
		%>
		<jsp:include page="cosin0603f.jsp" >
			<jsp:param value="Tab7" name="tabId"/>
		</jsp:include>
		<%
		}
		%>
		
		<%
		if("2".equals(obj.getCosType())){
		%>
		<jsp:include page="cosin0604f.jsp" >
			<jsp:param value="Tab7" name="tabId"/>
		</jsp:include>
		<%
		}
		%>
		
		<%-- 處理頁籤 --%>
		<%
		if("1".equals(obj.getCosType())){
		%>
		<jsp:include page="cosin0605f.jsp" >
			<jsp:param value="Tab8" name="tabId"/>
		</jsp:include>
		<%
		}else{
		%>
		<jsp:include page="cosin0606f.jsp" >
			<jsp:param value="Tab8" name="tabId"/>
		</jsp:include>
		<%
		}
		%>
		
		<%-- 結案頁籤 --%>
		<%
		if("1".equals(obj.getCosType())){
		%>
		<jsp:include page="cosin0903f.jsp" >
			<jsp:param value="Tab10" name="tabId"/>
		</jsp:include>
		<%
		}else{
		%>
		<jsp:include page="cosin0904f.jsp" >
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
			setFormItem("pauseSave,endCase","O");
			setFormItem("pageUpdate","R");
			
			$("#Tab10 [class*=field]").each(function(){
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
		break;
	case "pauseSave":
	case "endCase":
		form1.state.value = buttonName;
		checkField();
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
					code = "05"
					formCode = "COS02";
				}else{
					code = "08"
					formCode = "COS03";
				}
				openCenterWindow(800, 300, 'cosin0406f.jsp?id=' + form1.id.value + '&editId=' + form1.userID.value + "&code=" + code + "&formCode=" + formCode + "&caseStatus=" + "<%=obj.getStatus()%>");
			}
		}
		break;
	case "doDoQuit":
		deleteEdit(form1.id.value);
		
		form1.action = "cosin0901f.jsp";
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


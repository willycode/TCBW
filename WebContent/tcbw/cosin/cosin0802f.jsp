<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="COSIN0801" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN0802F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
String competence = "Y";
competence = TCBWCommon.getCompetence("COS02", "04", User.getUserId(), "4");					

if("pauseSave".equals(obj.getState()) || "assessComplete".equals(obj.getState())){
	try{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doUpdateType();
	}catch(Exception e){
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.cosin.COSIN0802F)obj.queryOne();
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
	
	if(form1.state.value == "assessComplete"){
		<%=TCBWCommon.getCheckFiled("COS01", "COS11")%>
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
		setFormItem("pauseSave,assessComplete","R");
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
		
		form1.action = "cosin0801f.jsp";
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
	form1.action = "cosin0802f.jsp" ;
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
	document.getElementById("t7").className = "tab_border2";
	document.getElementById("aTab1").className = "";
	document.getElementById("aTab2").className = "";
	document.getElementById("aTab3").className = "";
	document.getElementById("aTab4").className = "";
	document.getElementById("aTab5").className = "";
	document.getElementById("aTab6").className = "";
	document.getElementById("aTab7").className = "";
	document.getElementById("Tab1").style.display = 'none';
	document.getElementById("Tab2").style.display = 'none';
	document.getElementById("Tab3").style.display = 'none';	
	document.getElementById("Tab4").style.display = 'none';
	document.getElementById("Tab5").style.display = 'none';
	document.getElementById("Tab6").style.display = 'none';
	document.getElementById("Tab7").style.display = 'none';
	
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
	deleteEdit("<%=obj.getId()%>");				// 解除LOCK
	window.parent.frames['menu'].location.href = "../../home/dTreeMenu.jsp";
	
	form1.action = "cosin0801f.jsp";
	form1.state.value = "queryAll";
	form1.submit();
}


$(function(){
	setDisplayItem('spanInsert,spanUpdate,spanQueryAll,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden','H');
	if("<%=obj.getCosType()%>" == "1"){
		$("#t4").hide();
		$("#Tab4").hide();
		
		chFirstResult("<%=obj.getFirstResult()%>");
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
});

// 分類頁籤用
function chFirstResult(val){
	if(val == "02"){
		$("#FR1").show();
		$("[name=nonDefective]").attr("readonly", false);
		
		$("#FR2").hide();
		$("[name=leaveCaseReason]").val("").attr("readonly", true);
	}else if(val == "03"){
		$("#FR2").show();
		$("[name=leaveCaseReason]").attr("readonly", false);
		
		$("#FR1").hide();
		$("[name=nonDefective]").val("").attr("readonly", true);
	}else{
		$("#FR1").hide();
		$("[name=nonDefective]").val("").attr("readonly", true);
		
		$("#FR2").hide();
		$("[name=leaveCaseReason]").val("").attr("readonly", true);
	}
}

function queryCos0006DbOne(rowid, isNeedUpdateOther, classTR, id){
	$("tr[id*=listCos0006DbContainerRow]").each(function(){
		if($(this).attr("id") == ("listCos0006DbContainerRow" + rowid)){
			this.className = "activeRow";
			this.onmouseover = "activeRow";
			this.onmouseout = "activeRow";
		}else{
			this.className = classTR;
			this.onmouseover = function(){
				this.className = "listTRMouseover";
			};
			this.onmouseout = function(){
				this.className = classTR;
			}
		}
	});

	showCOS0006Db(id);
}

function showCOS0006Db(id){
	openCenterWindow(800, 300, 'cosin1005f.jsp?cos0006DbId=' + id);
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
	<jsp:include page="cosin0801q.jsp" />
</div>

<!--Toolbar區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bgToolbar" style="text-align:left">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="pageSize" value="<%=obj.getPageSize()%>">			<!-- FOR 查詢頁面定位使用 -->
	<input type="hidden" name="currentPage" value="<%=obj.getCurrentPage()%>">		<!-- FOR 查詢頁面定位使用 -->
	<input type="hidden" name="isCloseUserInfo" value="<%=obj.getIsCloseUserInfo()%>"> <!-- 列印時是否遮蔽個資 -->
	<input type="hidden" name="listContainerActiveRowId" value="<%=obj.getListContainerActiveRowId()%>">
	<jsp:include page="../../home/toolbar.jsp" />
	
	<span id="spanPageUpdate">
		<input class="toolbar_default" type="button" followPK="false" name="pageUpdate" value="修　改" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanPauseSave">
		<input class="toolbar_default" type="button" followPK="false" name="pauseSave" value="暫　存" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanAssessComplete">
		<input class="toolbar_default" type="button" followPK="false" name="assessComplete" value="評 估 完 成" onClick="whatButtonFireEvent(this.name)">&nbsp;
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
	<td nowrap ID="t1" CLASS="tab_border1"><a id="aTab1" href="#" onClick="changeTab(1);">基本資料</a></td>
	<td nowrap ID="t2" CLASS="tab_border2"><a id="aTab2" href="#" onClick="changeTab(2);">不良事件</a></td>
	<td nowrap ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">不良品</a></td>
	<td nowrap ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">不良反應</a></td>
	<td nowrap ID="t5" CLASS="tab_border2"><a id="aTab5" href="#" onClick="changeTab(5);">相關附件</a></td>
	<td nowrap ID="t6" CLASS="tab_border2"><a id="aTab6" href="#" onClick="changeTab(6);">分類</a></td>
	<td nowrap ID="t7" CLASS="tab_border2"><a id="aTab7" href="#" onClick="changeTab(7);">評估</a></td>
</tr>
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
		<% request.setAttribute("qBean", obj); %>
		<jsp:include page="cosin0400f.jsp" />
		
		<%--分類頁籤 ，無法轉型，自行撰寫--%>
		<table id="Tab6" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td nowrap class="td_form">初步判定結果</td>
			<td nowrap class="td_form_white" colspan="3">
				<select class="field" name="firstResult" type="select" onChange="chFirstResult(this.value);">
					<%=View.getOptionCodeKind("CFR", obj.getFirstResult()) %>
				</select>
				<span id="FR1">
					，非不良品原因
					<input class="field" type="text" name="nonDefective" size="50" maxlength="90" value="<%=obj.getNonDefective()%>">
				</span>
				<span id="FR2">
					，留案備查理由
					<input class="field" type="text" name="leaveCaseReason" size="50" maxlength="90" value="<%=obj.getLeaveCaseReason()%>">
				</span>
				<input type="hidden" name="cos0006DbId" value="<%=obj.getCos0006DbId()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">調查摘要</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="checkbox" name="dMarked" value="Y" <%=obj.getdMarked().equals("Y")?"checked":"" %>>標示問題
				<br>
				
				<input class="field" type="checkbox" name="dLawlessIng" value="Y" <%=obj.getdLawlessIng().equals("Y")?"checked":"" %>>疑似含有不法或其他有效成分，
				說明
				<input class="field" type="text" name="dLawlessIngOther" size="30" maxlength="50" value="<%=obj.getdLawlessIngOther()%>">
				<br>
				
				<input class="field" type="checkbox" name="dExteriorError" value="Y" <%=obj.getdExteriorError().equals("Y")?"checked":"" %>>外觀異常，
				說明
				<input class="field" type="text" name="dExteriorErrorOther" size="30" maxlength="50" value="<%=obj.getdExteriorErrorOther()%>">
				<br>
				
				<input class="field" type="checkbox" name="dPackageError" value="Y" <%=obj.getdPackageError().equals("Y")?"checked":"" %>>包裝瑕疵，
				說明
				<input class="field" type="text" name="dPackageErrorOther" size="30" maxlength="50" value="<%=obj.getdPackageErrorOther()%>">
				<br>
				
				<input class="field" type="checkbox" name="dExpired" value="Y" <%=obj.getdExpired().equals("Y")?"checked":"" %>>過期，
				說明
				<input class="field" type="text" name="dExpiredOther" size="30" maxlength="50" value="<%=obj.getdExpiredOther()%>">
				<br>
				
				<input class="field" type="checkbox" name="dOthers" value="Y" <%=obj.getdOthers().equals("Y")?"checked":"" %>>其他，
				說明
				<input class="field" type="text" name="dOthersDesc" size="30" maxlength="50" value="<%=obj.getdOthersDesc()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">採取措施</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getCommonCheckBoxTextOption("field", "measure", "CMS", obj.getMeasure()) %>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">化粧品不良品通報件數</td>
			<td nowrap class="td_form_white">
				<input class="field_RO" type="text" name="num1" size="20" value="<%=obj.getNum1()%>">
			</td>
			<td nowrap class="td_form">化粧品不良反應通報件數</td>
			<td nowrap class="td_form_white">
				<input class="field_RO" type="text" name="num2" size="20" value="<%=obj.getNum2()%>">
			</td>
		</tr>
		<%
		if("Y".equals(obj.getIsHasHistory())){
		%>
			<tr>
				<td nowrap class="td_form" colspan="4" style="text-align:center;font-size:16px;color:red">分類歷史流程資料</td>
			</tr>
			
			<tr><td nowrap class="bgList" colspan="4">
			<div id="COS0006DbListContainer" style="height:auto">
			<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
				<thead id="COS0006DbListTHEAD">
				<tr>
					<th class="listTH"><a class="text_link_w">NO.</a></th>
					<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">初步判定結果</a></th>
					<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">採取措施</a></th>
					<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">分類日期</a></th>
				</tr>
				</thead>
				<tbody id="COS0006DbListTBODY">
				<%
				    boolean primaryArray[] = {true, false, false, false};
				    boolean displayArray[] = {false, true, true, true};
				    out.write(View.getQuerylist(primaryArray, displayArray, null, obj.getCos0006DbList(), obj.getQueryAllFlag(), true, null, "", "", true, false, false, "Cos0006Db"));
				%>
				</tbody>
			</table>
			</div>
			</td></tr>
		
		<%
		}
		%>
		</table>
		
		<%--評估頁籤 --%>
		<jsp:include page="cosin0803f.jsp" >
			<jsp:param value="Tab7" name="tabId"/>
		</jsp:include>
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
			setFormItem("pauseSave,assessComplete","O");
			setFormItem("pageUpdate","R");
			
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
		break;
	case "assessComplete":
	case "pauseSave":
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
				var code = "04";
				var formCode = "COS02";
				openCenterWindow(800, 300, 'cosin0406f.jsp?id=' + form1.id.value + '&editId=' + form1.userID.value + "&code=" + code + "&formCode=" + formCode + "&caseStatus=" + "<%=obj.getStatus()%>");
			}
		}
		break;
	case "doDoQuit":
		deleteEdit(form1.id.value);
		
		form1.action = "cosin0801f.jsp";
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


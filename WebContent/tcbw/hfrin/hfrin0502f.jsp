<!--
程式目的：食品非預期反應通報審核作業(內部)-簡表
程式代號：hfrin0502f.jsp
程式日期：102.10.29
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="HFRIN0501" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.hfrin.HFRIN0502F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
String competence = TCBWCommon.getCompetence("HFR01", "01", User.getUserId(), "4");	

if("pauseSave".equals(obj.getState()) || "doAccept".equals(obj.getState()) || "doUnAccept".equals(obj.getState()) || "doAddition".equals(obj.getState()) || "doAdditionComplete".equals(obj.getState())){
	try{
		obj.setHttpRequest(pageContext.getRequest());	
		obj.doUpdateType();
	}catch(Exception e){
		e.printStackTrace();
		obj.setIsNeedBackQuery("N");
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.hfrin.HFRIN0502F)obj.queryOne();
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

	if(form1.state.value=="doAccept" || form1.state.value=="doAddition" || form1.state.value=="doAdditionComplete"){
		alertStr += checkHFR0001FDate();
		alertStr += validateGFoodTable();
		alertStr += validateNFoodTable();
		alertStr += validateTabFilefood();
		
		<%=TCBWCommon.getCheckFiled("HFR01", "HFR01")%>
		<%=TCBWCommon.getCheckFiled("HFR01", "HFR03")%>
	}
	if(alertStr.length!=0){ alert(alertStr); return; }
	
	if(form1.state.value=="doAccept" || form1.state.value=="doAddition" || form1.state.value=="doAdditionComplete"){
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

function init() {
	if("<%=obj.getIsNeedBackQuery()%>" == "N" || "<%=obj.getIsNeedBackQuery()%>" == ""){
		if("<%=obj.getErrorMsg()%>" != ""){
			alert("<%=obj.getErrorMsg()%>");
		}
		setFormItem("pauseSave,doAccept,doUnAccept,doAddition,doAdditionComplete","R");
		$(":button[name$=Download],:button[name^=showPermit][class=field],:button[name^=showNonPermit][class=field]").each(function(){
			$(this).attr("disabled", false);
		});
		
		<%=TCBWCommon.getIsComplete("HFR01")%>
	}else{
		alert("<%=obj.getErrorMsg()%>");
		deleteEdit("<%=obj.getId()%>");				// 解除LOCK
		window.parent.frames['menu'].location.href = "../../home/dTreeMenu.jsp";
		
		form1.action = "hfrin0501f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
	}
}

function goBackQuery(){
	deleteEdit("<%=obj.getId()%>");					// 解除LOCK
	window.parent.frames['menu'].location.href = "../../home/dTreeMenu.jsp";
	
	form1.action = "hfrin0501f.jsp";
	form1.state.value = "queryAll";
	form1.submit();
}

function doUnAcceptCase(){
	form1.state.value = "doUnAccept";
	beforeSubmit();
	form1.submit();
}

function toAsk(){
	var jscript = "";	
	var prop = "";
	var windowTop = (document.body.clientHeight-400)/2+100;
	var windowLeft = (document.body.clientWidth-400)/2+100;


	//點選不遮蔽個資時記錄相關LOG
	var q = "code=HFR01";  //通報表代碼，各通報表請參照CON1007_DB裡的FORMCODE欄位
	q += "&methodName=print";
	q += "&db=Hfr0001Db"; //通報表主檔DB，各通報表請修改為各自的內網主檔DB
	q += "&hql=select id,applNo,caseOwner,notifierName from Hfr0001Db where id = " + form1.id.value; //查詢通報表主檔DB相關欄位資訊，做為記錄LOG用
	
	//彈出式視窗大小資訊
	prop=prop+"width=800,height=100,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "tcbw/conin/conin1001f.jsp?" + q,"",prop);  //呼叫共用的conin1001f.jsp並帶入相關參數(q)，原report()方法改在這支jsp裡呼叫
	
}

function report(isClose){
	form1.isCloseUserInfo.value=isClose;
	<%if("1".equals(obj.getHfrType())) {%>	//1為簡表
		form1.action = "hfrin0102sp.jsp" ;
		form1.target = "_blank";
		beforeSubmit();
		form1.submit();
		form1.action = "hfrin0502f.jsp" ;
		form1.target = "_self";
	<%} else {%>
		form1.action = "hfrin0102gp.jsp" ;	//2為一般表
		form1.target = "_blank";
		beforeSubmit();
		form1.submit();
		form1.action = "hfrin0502f.jsp" ;
		form1.target = "_self";
	<%}%>
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

$(function(){
	if(win != null) win.close();
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
	<jsp:include page="hfrin0501q.jsp" />
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
	<span id="spanDoAccept">
    	<input class="toolbar_default" type="button" followPK="false" name="doAccept" value="受   理" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <span id="spanDoUnAccept">
    	<input class="toolbar_default" type="button" followPK="false" name="doUnAccept" value="撤   案" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <span id="spanDoAddition">
    	<input class="toolbar_default" type="button" followPK="false" name="doAddition" value="補   件" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <span id="spanDoAdditionComplete">
    	<input class="toolbar_default" type="button" followPK="false" name="doAdditionComplete" value="補 件 完 成" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <span id="spanDoReAssign">
    	<input class="toolbar_default" type="button" followPK="false" name="doReAssign" value="案 件 改 派" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
    <span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit" value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanDoOriginalVer">
		<input class="toolbar_default" type="button" followPK="false" name="doOriginalVer" value="案件原始版本" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<span id="spanDoFlow">
		<input class="toolbar_default" type="button" followPK="false" name="doFlow" value="案件流程紀錄" onClick="whatButtonFireEvent(this.name)">
	</span>
	<span id="spanDoReportView">
		<input class="toolbar_default" type="button" followPK="false" name="doReportView" value="列印通報表" onClick="whatButtonFireEvent(this.name)">
	</span>
</td></tr>
</table>

<!--頁籤區============================================================-->
<TABLE CELLPADDING="0" CELLSPACING="0" valign="top">
<tr>
	<td ID="t1" CLASS="tab_border1"><a id="aTab1" href="#" onClick="changeTab(1);">基本資料</a></td>
	<td ID="t2" CLASS="tab_border2"><a id="aTab2" href="#" onClick="changeTab(2);">通報內容</a></td>		
	<td ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">可疑食品列表</a></td>
	<td ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">其它相關附件</a></td>
	<td ID="t5" CLASS="tab_border2"><a id="aTab5" href="#" onClick="changeTab(5);">歷史通報</a></td>
</tr>
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
		<% request.setAttribute("qBean", obj); %>
		<jsp:include page="hfrin0001f.jsp" />
		
		<jsp:include page="hfrin0003f.jsp">
			<jsp:param value="Tab5" name="tabId"/>
		</jsp:include>
		
	</div>
</td></tr>
</table>
</form>
</body>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	if(win != null) {
		win.close();
	}
	switch (buttonName){
	case "pageUpdate":
		var flag = checkIsEdit(form1.id.value, form1.notifierName.value);
		if(flag){
			setFormItem("pauseSave,doAccept,doUnAccept","O");
			if("<%=obj.getStatus()%>" == "03"){
				setFormItem("doAdditionComplete","O");
			}else{
				setFormItem("doAddition","O");
			}
			setFormItem("pageUpdate,doReAssign,doQHistory,doReport","R");
			form1.state.value = buttonName;
			setAllOpen();
			
			chDrugAllergies("<%=obj.getDrugAllergies()%>");								// 藥物過敏史
			chFoodAllergies("<%=obj.getFoodAllergies()%>");								// 食物過敏史  
			chDiseaseHistory();															// 疾病史
			chLifeHistory();															// 生活史			
			chUnHealthIsYes("<%=obj.getUnHealthIsYes()%>");								// 健康食品未達宣稱保健功效
			chIsMedical("<%=obj.getIsMedical()%>");										// 是否有就醫
			chIsWhileMedicine("<%=obj.getIsWhileMedicine()%>");							// 同時服用西醫
			chIsWhileCMedicine("<%=obj.getIsWhileCMedicine()%>");						// 同時服用中醫	
			chIsWhileOther("<%=obj.getIsWhileOther()%>");								// 同時食用其他錠、塑膠型食品
		}
		break;
	case "doUnAccept":
		var js = "window.opener.doUnAcceptCase";
		openCenterWindow(800, 600, getVirtualPath() + "tcbw/conbg/conbg0001f.jsp?isAdd=N&applNo=" + form1.applNo.value + "&isJS=" + js + "&mailID=HFR010002&id=" + form1.id.value);
		break;
	case "pauseSave":
	case "doAccept":
	case "doAddition":
	case "doAdditionComplete":
		form1.state.value = buttonName;
		checkField();
		break;
	case "doReAssign":
		var flag = checkIsEdit(form1.id.value, form1.notifierName.value);
		if(flag){
			if('<%=Common.get(competence)%>' != 'Y'){
				alert("您無改派權限!");
			}else{
				form1.state.value = buttonName;
				openCenterWindow(800, 300, 'hfrin0504f.jsp?id=' + form1.id.value + '&editId=' + form1.userID.value + "&code=01&formCode=HFR01&caseStatus=" + "<%=obj.getStatus()%>");
			}
		}
		break;
	case "doDoQuit":
		deleteEdit("<%=obj.getId()%>");				// 解除LOCK
		
		form1.action = "hfrin0501f.jsp";
		form1.state.value = "queryAll";
		form1.submit();
		break;
	case "doOriginalVer":
		popHfr4001(form1.hfr40001Id.value);
		break;
	case "doFlow":
		popCon2001('HFR', form1.id.value);
		break;
	case "doReportView":
		toAsk();
		break;
	case "doQHistory":
		form1.submit();
		break;
	case "doReport":
		form1.action = "hfrin0102p.jsp";
		form1.target = "_blank";
		beforeSubmit();
		form1.submit();
		form1.action = "hfrin0502f.jsp";
		form1.target = "_self";
		break;
	}
}

function popHfr4001(id){
	openCenterWindow(850, 420, getVirtualPath() + "home/popHfr4001.jsp?hfr0001Id=" + id);
}

function report(isClose){
	form1.isCloseUserInfo.value=isClose;
	<%if("1".equals(obj.getHfrType())) {%>	//1為簡表
	form1.action = "hfrin0102sp.jsp" ;
	form1.target = "_blank";
	beforeSubmit();
	form1.submit();
	form1.action = "hfrin0502f.jsp" ;
	form1.target = "_self";
	
	<%} else {%>
	form1.action = "hfrin0102gp.jsp" ;	//2為一般表
	form1.target = "_blank";
	beforeSubmit();
	form1.submit();
	form1.action = "hfrin0502f.jsp" ;
	form1.target = "_self";
	
	<%}%>
}

function queryOne(id, type){
	var isPop = "A";
	if(type == 6)
		isPop = "F";
	
	openCenterWindow(1250, 720, getVirtualPath() + "tcbw/hfrin/hfrin0102q.jsp?isPop=" + isPop + "&id=" + id);
}
</script>
</html>

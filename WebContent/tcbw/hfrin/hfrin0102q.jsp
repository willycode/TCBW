<!--
程式目的：食品非預期反應通報查詢作業
程式代號：hfrin0102q
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="HFRIN0101" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.hfrin.HFRIN0102Q">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<jsp:useBean id="objQry" scope="request" class="com.kangdainfo.tcbw.view.hfrin.HFRIN0101Q">
	<jsp:setProperty name="objQry" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
String isPop = Common.get(request.getParameter("isPop"));
obj = (com.kangdainfo.tcbw.view.hfrin.HFRIN0102Q)obj.queryOne();
objList = (java.util.ArrayList)obj.doQueryHistory();
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

function checkField(){}

function init() {
	var tabId = form1.tabId.value;
	if(tabId != ""){
		changeTab(tabId);
	}else{
		changeTab(1);
	}
}

function changeTab(tabId) {
	document.getElementById("t1").className = "tab_border2";
	document.getElementById("t2").className = "tab_border2";
	document.getElementById("t3").className = "tab_border2";
	document.getElementById("t4").className = "tab_border2";
	document.getElementById("t5").className = "tab_border2";
	//document.getElementById("t6").className = "tab_border2";
	document.getElementById("t7").className = "tab_border2";
	document.getElementById("t8").className = "tab_border2";
	document.getElementById("t9").className = "tab_border2";
	document.getElementById("t10").className = "tab_border2";
	document.getElementById("aTab1").className = "";
	document.getElementById("aTab2").className = "";
	document.getElementById("aTab3").className = "";
	document.getElementById("aTab4").className = "";
	document.getElementById("aTab5").className = "";
	//document.getElementById("aTab6").className = "";
	document.getElementById("aTab7").className = "";
	document.getElementById("aTab8").className = "";
	document.getElementById("aTab9").className = "";
	document.getElementById("aTab10").className = "";
	document.getElementById("Tab1").style.display = 'none';
	document.getElementById("Tab2").style.display = 'none';
	document.getElementById("Tab3").style.display = 'none';
	document.getElementById("Tab4").style.display = 'none';
	document.getElementById("Tab5").style.display = 'none';
	<%
	if("Y".equals(obj.getIsShowPreAssessmentPage()) && "1".equals(obj.getHfrType())){
	%>
	document.getElementById("Tab6").style.display = 'none';
	<%}%>
	//document.getElementById("Tab6").style.display = 'none';
	document.getElementById("Tab7").style.display = 'none';
	document.getElementById("Tab8").style.display = 'none';
	document.getElementById("Tab9").style.display = 'none';
	document.getElementById("Tab10").style.display = 'none';
	
	if(tabId == 2){
		document.getElementById("t2").className = "tab_border1";	
		document.getElementById("Tab2").style.display = '';
		document.getElementById("aTab2").className = "text_w";
		<%
		if("Y".equals(obj.getIsShowPreAssessmentPage()) && "1".equals(obj.getHfrType())){
		%>
		document.getElementById("Tab6").style.display = '';
		<%}%>
		form1.tabId.value="2";
	}else if(tabId == 3){
		document.getElementById("t3").className = "tab_border1";
		document.getElementById("Tab3").style.display = '';
		document.getElementById("aTab3").className = "text_w";
		form1.tabId.value="3";
	}else if(tabId == 4){
		document.getElementById("t4").className = "tab_border1";
		document.getElementById("Tab4").style.display = '';
		document.getElementById("aTab4").className = "text_w";
		form1.tabId.value="4";
	}else if(tabId == 5){
		document.getElementById("t5").className = "tab_border1";
		document.getElementById("Tab5").style.display = '';
		document.getElementById("aTab5").className = "text_w";
		form1.tabId.value="5";
	}else if(tabId == 7){
		document.getElementById("t7").className = "tab_border1";
		document.getElementById("Tab7").style.display = '';
		document.getElementById("aTab7").className = "text_w";
		form1.tabId.value="7";
	}else if(tabId == 8){
		document.getElementById("t8").className = "tab_border1";
		document.getElementById("Tab8").style.display = '';
		document.getElementById("aTab8").className = "text_w";
		form1.tabId.value="8";
	}else if(tabId == 9){
		document.getElementById("t9").className = "tab_border1";
		document.getElementById("Tab9").style.display = '';
		document.getElementById("aTab9").className = "text_w";
		form1.tabId.value="9";
	}else if(tabId == 10){
		document.getElementById("t10").className = "tab_border1";
		document.getElementById("Tab10").style.display = '';
		document.getElementById("aTab10").className = "text_w";
		form1.tabId.value="10";
	}else{
		document.getElementById("t1").className = "tab_border1";
		document.getElementById("Tab1").style.display = '';
		document.getElementById("aTab1").className = "text_w";
		form1.tabId.value="1";
	}
}

function popHfr4001(id){
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=850,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "home/popHfr4001.jsp?hfr0001Id="+id,"",prop);
}

function queryOne(id, type)
{
	var isPop = "A";
	if(type == 6)
		isPop = "F";
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=1250,height=720,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "tcbw/hfrin/hfrin0102q.jsp?isPop="+isPop+"&id="+id,"",prop);
}

function report(isClose)
{
	
	form1.isCloseUserInfo.value=isClose;
	<%if("1".equals(obj.getHfrType())) {%>	//1為簡表
	form1.action = "hfrin0102sp.jsp" ;
	form1.target = "_blank";
	beforeSubmit();
	form1.submit();
	form1.action = "hfrin0102q.jsp" ;
	form1.target = "_self";
	
	<%} else {%>
	form1.action = "hfrin0102gp.jsp" ;	//2為一般表
	form1.target = "_blank";
	beforeSubmit();
	form1.submit();
	form1.action = "hfrin0102q.jsp" ;
	form1.target = "_self";
	
	<%}%>
}

//彈出式視窗尋問是否遮蔽個資
function toAsk()
{
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;


	//點選不遮蔽個資時記錄相關LOG
	var q = "code=HFR01";
	q +="&methodName=print";
	q +="&db=Hfr0001Db";
	q +="&hql=select id,applNo,caseOwner,notifierName from Hfr0001Db where id = " + form1.id.value;
	
	//彈出式視窗大小資訊
	prop=prop+"width=800,height=100,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "tcbw/conin/conin1001f.jsp?" + q,"",prop);
	
}

$(function(){
	setDisplayItem('spanInsert,spanUpdate,spanQueryAll,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden','H');
	//changeTab(1);
	<%if("1".equals(obj.getHfrType())){
		//簡表
		out.write(obj.getGFoodSBuilder());
		out.write(obj.getNFoodSBuilder());
		out.write(obj.getFoodFileSBuilder());
		out.write(obj.getHFRFOFileRowSBuilder());
		out.write(obj.getHFRFMFileRowSBuilder());
		out.write(obj.getHFRFIFileRowSBuilder());
		out.write(obj.getHFRREFileRowSBuilder());
	}else{
		//一般表
		out.write(obj.getDiscriptSBuilder());
		out.write(obj.getDoctorSBuilder());
		out.write(obj.getGFoodSBuilder());
		out.write(obj.getNFoodSBuilder());
		out.write(obj.getDrugSBuilder());
		out.write(obj.getODrugSBuilder());
		out.write(obj.getFoodFileSBuilder());
		out.write(obj.getHFRFOFileRowSBuilder());
		out.write(obj.getHFRFMFileRowSBuilder());
		out.write(obj.getHFRFIFileRowSBuilder());
		out.write(obj.getHFRREFileRowSBuilder());
	}%>

	if("<%=isPop%>" == "A"){
		$("#t9").hide();
		$("#Tab9").hide();
		$("#t10").hide();
		$("#Tab10").hide();
		changeTab(1);
	}else if("<%=isPop%>" == "F"){
		$("#t1").hide();
		$("#Tab1").hide();
		$("#t2").hide();
		$("#Tab2").hide();
		$("#t3").hide();
		$("#Tab3").hide();
		$("#t5").hide();
		$("#Tab5").hide();
		$("#t6").hide();
		$("#Tab6").hide();
		$("#t7").hide();
		$("#Tab7").hide();
		$("#t8").hide();
		$("#Tab8").hide();
		$("#t9").hide();
		$("#Tab9").hide();
		$("#t10").hide();
		$("#Tab10").hide();
		changeTab(4);
	}
});
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" autocomplete="off">
<div id="queryContainer" style="width:300px;height:100px;display:none">
<% request.setAttribute("qBean", objQry); %>
<jsp:include page="hfrin0101_1q.jsp" />
</div>
<!--Toolbar區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bgToolbar" style="text-align:left">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="tabId" value="<%=obj.getTabId()%>">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">	
	<input type="hidden" name="pageSize" value="<%=obj.getPageSize()%>">			<!-- FOR 查詢頁面定位使用 -->
	<input type="hidden" name="currentPage" value="<%=obj.getCurrentPage()%>">		<!-- FOR 查詢頁面定位使用 -->
	<input type="hidden" name="listContainerActiveRowId" value="<%=obj.getListContainerActiveRowId()%>">
	<input type="hidden" name="isCloseUserInfo" value="<%=obj.getIsCloseUserInfo()%>"> <!-- 列印時是否遮蔽個資 -->
	<jsp:include page="../../home/toolbar.jsp" />
    <%if("".equals(isPop)){%>
    <span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit" value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<input class="toolbar_default" type="button" followPK="false" name="doOriginalVer" value="案件原始版本" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doFlow" value="案件流程紀錄" onClick="whatButtonFireEvent(this.name)">
	<input class="toolbar_default" type="button" followPK="false" name="doReportView" value="列印通報表" onClick="whatButtonFireEvent(this.name)">
	<%}%>
	<span id="spanShow"></span>
</td></tr>
</table>

<!--頁籤區============================================================-->
<TABLE CELLPADDING="0" CELLSPACING="0" valign="top">
<tr>
	<td nowrap ID="t1" CLASS="tab_border1"><a id="aTab1" href="#" onClick="changeTab(1);">基本資料</a></td>
	<td nowrap ID="t2" CLASS="tab_border2"><a id="aTab2" href="#" onClick="changeTab(2);">調查紀錄</a></td>		
	<td nowrap ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">可疑食品列表</a></td>
	<td nowrap ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">相關附件</a></td>
	<td nowrap ID="t5" CLASS="tab_border2"><a id="aTab5" href="#" onClick="changeTab(5);">初步評估表</a></td>
	<td nowrap ID="t7" CLASS="tab_border2"><a id="aTab7" href="#" onClick="changeTab(7);">委員評估意見單</a></td>
	<td nowrap ID="t8" CLASS="tab_border2"><a id="aTab8" href="#" onClick="changeTab(8);">評估委員會決議表</a></td>
</tr>
<tr>
	<td nowrap ID="t9" CLASS="tab_border2"><a id="aTab9" href="#" onClick="changeTab(9);">時間表單</a></td>
	<td nowrap ID="t10" CLASS="tab_border2"><a id="aTab10" href="#" onClick="changeTab(10);">歷史通報</a></td>
</tr>
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
		<% request.setAttribute("qBean", obj); %>
		<% if("1".equals(obj.getHfrType())){%>
		<jsp:include page="hfrin0001f.jsp" />
		<% }else{%>
		<jsp:include page="hfrin0002f.jsp" />
		<% }%>
		
		<jsp:include page="hfrin0603f.jsp">
			<jsp:param value="Tab5" name="tabId"/>
		</jsp:include>
		
		<jsp:include page="hfrin0703f.jsp">
			<jsp:param value="Tab7" name="tabId"/>
		</jsp:include>
		
		<jsp:include page="hfrin0803f.jsp">
			<jsp:param value="Tab8" name="tabId"/>
		</jsp:include>
		
		<table id="Tab9" width="100%" align="center" class="table_form">	
			<tr>
				<td class="td_form">案件編號</td>
		  		<td class="td_form_white">
					<input class="field" type="text" name="" size="15" maxlength="11" value="<%=obj.getApplNo()%>">
				</td>
				<td class="td_form">通報日期</td>
				<td class="td_form_white">
					<input class="field" type="text" name="" size="15" maxlength="11" value="<%=obj.getNotifierRepDate()%>">
				</td>
			</tr>
			<tr>
				<td class="td_form">收案日期</td>
		  		<td class="td_form_white">
					<input class="field" type="text" name="" size="15" maxlength="11" value="<%=obj.getEnrolledDateHis()%>">
				</td>
				<td class="td_form">通知補件日期</td>
				<td class="td_form_white">
					<input class="field" type="text" name="" size="15" maxlength="11" value="<%=obj.getAdditionalDateHis()%>">
				</td>
			</tr>
			<tr>
				<td class="td_form">補件完成日期</td>
		  		<td class="td_form_white">
					<input class="field" type="text" name="" size="15" maxlength="11" value="<%=obj.getAdditionalFinshDateHis()%>">
				</td>
				<td class="td_form">病歷調閱發文日期</td>
				<td class="td_form_white">
					<input class="field" type="text" name="" size="15" maxlength="11" value="<%=obj.getMedicalPostingDateHis()%>">
				</td>
			</tr>
			<tr>
				<td class="td_form">病歷調閱完成日期</td>
		  		<td class="td_form_white">
					<input class="field" type="text" name="" size="15" maxlength="11" value="<%=obj.getMedicalCompleteDateHis()%>">
				</td>
				<td class="td_form">檢驗報告發文日期</td>
				<td class="td_form_white">
					<input class="field" type="text" name="" size="15" maxlength="11" value="<%=obj.getInspectPostingDateHis()%>">
				</td>
			</tr>
			<tr>
				<td class="td_form">檢驗報告完成日期</td>
		  		<td class="td_form_white">
					<input class="field" type="text" name="" size="15" maxlength="11" value="<%=obj.getInspectCompleteDateHis()%>">
				</td>
				<td class="td_form">初評完成日期</td>
				<td class="td_form_white">
					<input class="field" type="text" name="" size="15" maxlength="11" value="<%=obj.getPreCompleteDateHis()%>">
				</td>
			</tr>
			<tr>
				<td class="td_form">預評送交日期</td>
		  		<td class="td_form_white">
					<input class="field" type="text" name="" size="15" maxlength="11" value="<%=obj.getAssessmentSendDateHis()%>">
				</td>
				<td class="td_form">預評完成日期</td>
				<td class="td_form_white">
					<input class="field" type="text" name="" size="15" maxlength="11" value="<%=obj.getAssessmentCompleteDateHis()%>">
				</td>
			</tr>
			<tr>
				<td class="td_form">委員會召開日期</td>
		  		<td class="td_form_white">
					<input class="field" type="text" name="" size="15" maxlength="11" value="<%=obj.getCommitteeDateHis()%>">
				</td>
				<td class="td_form">會議記錄發文日期</td>
				<td class="td_form_white">
					<input class="field" type="text" name="" size="15" maxlength="11" value="<%=obj.getRecordDateHis()%>">
				</td>
			</tr>
			<tr>
				<td class="td_form">案件結果是否回饋</td>
		  		<td class="td_form_white">
					<%=obj.getReEvaluateResultHis()%>
				</td>
				<td class="td_form">案件回饋日期</td>
				<td class="td_form_white">
					<input class="field" type="text" name="" size="15" maxlength="11" value="<%=obj.getCaseBackDateHis()%>">
				</td>
			</tr>
		</table>
		<table id="Tab10" width="100%" align="center" class="table_form">
			<tr>
				<td nowrap class="td_form_left" colspan="2">篩選條件
				</td>
			</tr>
			<tr>
				<td nowrap class="td_form" >通報日期</td>
				<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field_Q", "q_notifierRepDateSHis", obj.getQ_notifierRepDateSHis())%>
				~ <%=View.getPopCalendar("field_Q", "q_notifierRepDateEHis", obj.getQ_notifierRepDateEHis())%>
				</td>
			</tr>
			<tr>
				<td nowrap class="td_form" >非預期反應結果</td>
				<td nowrap class="td_form_white">
					<%=obj.getFUROption("field_Q", "q_unReactionResultsHis", obj.getQ_unReactionResultsHis()) %>
				</td>
			</tr>
			<tr>
				<td nowrap class="td_form" >食品品名</td>
				<td nowrap class="td_form_white">
					<input class="field_Q" type="text" name="q_productHis" size="50" maxlength="50" value="<%=obj.getQ_productHis()%>">
				</td>
			</tr>
			<tr><td nowrap class="td_form" ></td><td nowrap class="td_form_white"></td></tr>
						
			<!--Toolbar區============================================================-->
			<tr><td nowrap class="bgToolbar" style="text-align:center" colspan="2">
				<input class="toolbar_default" type="button" followPK="false" name="doQHistory" value="篩    選" onClick="whatButtonFireEvent(this.name)">&nbsp;
				<% if(objList!=null && objList.size()>0){%>
				<input class="toolbar_default" type="button" followPK="false" name="doReport" value="匯出EXCEL" onClick="whatButtonFireEvent(this.name)">
				<% }%>
			</td></tr>
			
			<!-- List 區   -->	
			<tr><td nowrap class="bgPagging" colspan="4">		
				<% request.setAttribute("QueryBean",obj);%>
				<jsp:include page="../../home/page.jsp" />
			</td></tr>

			<tr>
				<td nowrap class="bgList" colspan="4">
				<div id="listContainer" style="height:auto">
				<table class="table_form" width="99%" cellspacing="0" cellpadding="0">
				<thead id="listTHEAD">
					<tr>
						<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">NO</a></th>
						<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">通報日期</a></th>
						<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">案件編號</a></th>
						<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">食品品名</a></th>
						<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">健康食品未達宣稱之保健功效</a></th>
						<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',6,false);" href="#">非預期反應結果</a></th>
						<th class="listTH"><a class="text_link_w" href="#">附件</a></th>
					</tr>
				</thead>
				<tbody id="listTBODY">
				<%
				    boolean primaryArray[] = {true,false,false,false,false,false,false};
				    boolean displayArray[] = {false,true,true,true,true,true,true};
				    boolean linkArray[] = {false,false,true,false,false,false,true};
				    String[] alignArray = {"center","center","center","center","center","center","center"};
				    out.write(View.getQuerylist(primaryArray,displayArray,alignArray,objList,obj.getQueryAllFlag(),true,linkArray,null,"",false,false));
				%>
				</tbody>
				</table>
				</div>
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
		case "doDoQuit":
			form1.action = "hfrin0101q.jsp";
			form1.state.value = "";
			form1.queryAllFlag.value = "true";
			form1.submit();
			break;
		case "doQHistory":
			beforeSubmit();
			form1.submit();
			break;
		case "doReport":
			form1.action = "hfrin0102p.jsp";
			form1.target = "_blank";
			beforeSubmit();
			form1.submit();
			form1.action = "hfrin0102q.jsp" ;
			form1.target = "_self";
			break;
		//詢問是否遮蔽個資	  
		  case "doReportView":
			  toAsk();
			  break;
		case "doOriginalVer":
			popHfr4001('<%=obj.getHfr40001Id()%>');
			break;
		case "doFlow":
			popCon2001('HFR','<%=obj.getId()%>');
			break;
	}
}
</script>
</html>

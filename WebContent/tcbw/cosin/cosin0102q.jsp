<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="COSIN0501" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN0102Q">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<jsp:useBean id="objListHis" scope="page" class="java.util.ArrayList"/>
<%
String isPop = Common.get(request.getParameter("isPop"));
obj = (com.kangdainfo.tcbw.view.cosin.COSIN0102Q)obj.queryOne();
objListHis = (java.util.ArrayList)obj.doQueryHistory();
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
	var tabId = form1.tabId.value;
	if(tabId != ""){
		changeTab(tabId);
	}else{
		changeTab(1);
	}
}

function changeTab(tabId) {
	document.getElementById("t1").className = "tab_border2";
	document.getElementById("aTab1").className = "";
	document.getElementById("Tab1").style.display = 'none';
	
	document.getElementById("t2").className = "tab_border2";
	document.getElementById("aTab2").className = "";
	document.getElementById("Tab2").style.display = 'none';

	document.getElementById("t3").className = "tab_border2";
	document.getElementById("aTab3").className = "";
	document.getElementById("Tab3").style.display = 'none';
	
	document.getElementById("t4").className = "tab_border2";
	document.getElementById("aTab4").className = "";
	document.getElementById("Tab4").style.display = 'none';
	
	document.getElementById("t5").className = "tab_border2";
	document.getElementById("aTab5").className = "";
	document.getElementById("Tab5").style.display = 'none';
	
	<%if(Integer.valueOf(obj.getStatus()) > 10){%>
	document.getElementById("t6").className = "tab_border2";
	document.getElementById("aTab6").className = "";
	document.getElementById("Tab6").style.display = 'none';
	document.getElementById("t7").className = "tab_border2";
	document.getElementById("aTab7").className = "";
	document.getElementById("Tab7").style.display = 'none';
	document.getElementById("t8").className = "tab_border2";
	document.getElementById("aTab8").className = "";
	document.getElementById("Tab8").style.display = 'none';
	document.getElementById("t10").className = "tab_border2";
	document.getElementById("aTab10").className = "";
	document.getElementById("Tab10").style.display = 'none';
		<%if("1".equals(obj.getCosType())){%>
		document.getElementById("t9").className = "tab_border2";
		document.getElementById("aTab9").className = "";
		document.getElementById("Tab9").style.display = 'none';
		<%}%>
	<%}%>

	document.getElementById("t11").className = "tab_border2";
	document.getElementById("aTab11").className = "";
	document.getElementById("Tab11").style.display = 'none';
	
	if(tabId == 2){
		document.getElementById("t2").className = "tab_border1";	
		document.getElementById("Tab2").style.display = '';
		document.getElementById("aTab2").className = "text_w";
		form1.tabId.value=2;
	}else if(tabId == 3){
		document.getElementById("t3").className = "tab_border1";	
		document.getElementById("Tab3").style.display = '';
		document.getElementById("aTab3").className = "text_w";
		form1.tabId.value=3;
	}else if(tabId == 4){
		document.getElementById("t4").className = "tab_border1";	
		document.getElementById("Tab4").style.display = '';
		document.getElementById("aTab4").className = "text_w";
		form1.tabId.value=4;
	}else if(tabId == 5){
		document.getElementById("t5").className = "tab_border1";	
		document.getElementById("Tab5").style.display = '';
		document.getElementById("aTab5").className = "text_w";
		form1.tabId.value=5;
	}else if(tabId == 6){
		document.getElementById("t6").className = "tab_border1";	
		document.getElementById("Tab6").style.display = '';
		document.getElementById("aTab6").className = "text_w";
		form1.tabId.value=6;
	}else if(tabId == 7){
		document.getElementById("t7").className = "tab_border1";	
		document.getElementById("Tab7").style.display = '';
		document.getElementById("aTab7").className = "text_w";
		form1.tabId.value=7;
	}else if(tabId == 8){
		document.getElementById("t8").className = "tab_border1";	
		document.getElementById("Tab8").style.display = '';
		document.getElementById("aTab8").className = "text_w";
		form1.tabId.value=8;
	}else if(tabId == 9){
		document.getElementById("t9").className = "tab_border1";	
		document.getElementById("Tab9").style.display = '';
		document.getElementById("aTab9").className = "text_w";
		form1.tabId.value=9;
	}else if(tabId == 10){
		document.getElementById("t10").className = "tab_border1";	
		document.getElementById("Tab10").style.display = '';
		document.getElementById("aTab10").className = "text_w";
		form1.tabId.value=10;
	}else if(tabId == 11){
		document.getElementById("t11").className = "tab_border1";	
		document.getElementById("Tab11").style.display = '';
		document.getElementById("aTab11").className = "text_w";
		form1.tabId.value=11;
	}else{
		document.getElementById("t1").className = "tab_border1";
		document.getElementById("Tab1").style.display = '';
		document.getElementById("aTab1").className = "text_w";
		form1.tabId.value=1;
	}
}

function changeForm(a){
	v = a.value;
	if(a.value == "01"){
		$('#s1').show();
		$('#s2').hide();
	}else{
		$('#s1').show();
		$('#s2').show();
	}
}

function popUserJobList(){	
	var prop="";
	var windowTop=120;
	var windowLeft=120;
	var q = "";
	prop=prop+"width=1200px,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes";
	window.open(getVirtualPath()+"home/popUserJob.jsp?q="+q+"&v="+v,'popWinE',prop);	
}

function queryOne(id, type)
{
	var isPop = "A";
	if(type == 10)
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
	returnWindow=window.open(getVirtualPath() + "tcbw/cosin/cosin0102q.jsp?isPop="+isPop+"&id="+id,"",prop);
}

function popCos4001(id){
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=850,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "home/popCos4001.jsp?cos0001Id="+id,"",prop);
}

//彈出式視窗尋問是否遮蔽個資
function toAsk()
{
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;

	//點選不遮蔽個資時記錄相關LOG
	var q = "code=COS01";
	q +="&methodName=print";
	q +="&db=Cos0001Db";
	q +="&hql=select a.id,a.applNo,b.caseOwner,a.notifierName from Cos0001Db a,Cos4001Db b where a.cos4001DbId = b.id and a.id = " + form1.id.value;

	
	prop=prop+"width=800,height=100,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "tcbw/conin/conin1001f.jsp?" + q,"",prop);
	
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
	}
	
	if("<%=isPop%>" == "A"){
		$("#t11").hide();
		$("#Tab11").hide();
		changeTab(1);
	}else if("<%=isPop%>" == "F"){
		$("#t1").hide();
		$("#t2").hide();
		$("#t3").hide();
		$("#t4").hide();
		$("#t11").hide();
		$("#Tab1").hide();
		$("#Tab2").hide();
		$("#Tab3").hide();
		$("#Tab4").hide();
		$("#Tab11").hide();
		<%if(Integer.valueOf(obj.getStatus()) > 10){%>
			$("#t6").hide();
			$("#t7").hide();
			$("#t8").hide();
			$("#t10").hide();
			$("#Tab6").hide();
			$("#Tab7").hide();
			$("#Tab8").hide();
			$("#Tab10").hide();
			<%if("1".equals(obj.getCosType())){%>
				$("#t9").hide();
				$("#Tab9").hide();
			<%}
		}%>
		changeTab(5);
	}	
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
	<%=obj.getCFileRowSBuilder()%>
	
	if("<%=obj.getCosType()%>" == "1"){
		if("<%=obj.getIsNeedShowCos0009DbPage()%>" == "Y"){
			<%=obj.getCOSVRFileRowSBuilder()%>
		}
		<%=obj.getCOS0012DbRowSBuilder()%>
		<%=obj.getCOSPTFileRowSBuilder()%>
		<%=obj.getCOS0013DbRowSBuilder()%>
		<%=obj.getCOSRTFileRowSBuilder()%>
	}else if("<%=obj.getCosType()%>" == "2"){
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

function report(isClose)
{
	form1.isCloseUserInfo.value=isClose;
	form1.action = "cosin0402p.jsp" ;
	form1.target = "_blank";
	beforeSubmit();
	form1.submit();
	form1.action = "cosin0102q.jsp" ;
	form1.target = "_self";
}
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('');">
<form id="form1" name="form1" method="post" autocomplete="off">
<!--Query區============================================================-->

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
	<input type="hidden" name="dept1">
	<jsp:include page="../../home/toolbar.jsp" />	
    <%if("".equals(isPop)){%>
	<span id="spanDoQuit">
		<input class="toolbar_default" type="button" followPK="false" name="doDoQuit" value="返 回 查 詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
	</span>
	<input class="toolbar_default" type="button" followPK="false" name="doOriginalVer" value="案件原始版本" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doAddDoc" value="案件補件紀錄" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doMailList" value="郵件清單紀錄" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="doFlow" value="案件流程紀錄" onClick="whatButtonFireEvent(this.name)">	
	<input class="toolbar_default" type="button" followPK="false" name="doReportView" value="列印通報表" onClick="whatButtonFireEvent(this.name)">
	<%}%>
</td></tr>
</table>


<!--頁籤區============================================================-->
<TABLE CELLPADDING="0" CELLSPACING="0" valign="top">
<tr>
	<tr>
		<td nowrap ID="t1" CLASS="tab_border1"><a id="aTab1" href="#" onClick="changeTab(1);">基本資料</a></td>
		<td nowrap ID="t2" CLASS="tab_border2"><a id="aTab2" href="#" onClick="changeTab(2);">產品基本資料</a></td>
		<td nowrap ID="t3" CLASS="tab_border2"><a id="aTab3" href="#" onClick="changeTab(3);">不良品</a></td>
		<td nowrap ID="t4" CLASS="tab_border2"><a id="aTab4" href="#" onClick="changeTab(4);">不良反應</a></td>
		<td nowrap ID="t5" CLASS="tab_border2"><a id="aTab5" href="#" onClick="changeTab(5);">相關附件</a></td>
		<%if("1".equals(obj.getCosType()) && Integer.valueOf(obj.getStatus()) > 10){%>
		<td nowrap ID="t6" CLASS="tab_border2"><a id="aTab6" href="#" onClick="changeTab(6);">不良品分類</a></td>
		<td nowrap ID="t7" CLASS="tab_border2"><a id="aTab7" href="#" onClick="changeTab(7);">不良品廠商回覆</a></td>
		<td nowrap ID="t8" CLASS="tab_border2"><a id="aTab8" href="#" onClick="changeTab(8);">不良品處理</a></td>
		<td nowrap ID="t9" CLASS="tab_border2"><a id="aTab9" href="#" onClick="changeTab(9);">不良品評估</a></td>
		<%}else if("2".equals(obj.getCosType()) && Integer.valueOf(obj.getStatus()) > 10){%>	
		<td nowrap ID="t6" CLASS="tab_border2"><a id="aTab6" href="#" onClick="changeTab(6);">不良反應分類</a></td>
		<td nowrap ID="t7" CLASS="tab_border2"><a id="aTab7" href="#" onClick="changeTab(7);">不良反應廠商回覆</a></td>
		<td nowrap ID="t8" CLASS="tab_border2"><a id="aTab8" href="#" onClick="changeTab(8);">不良反應處理</a></td>
		<td nowrap ID="t10" CLASS="tab_border2"><a id="aTab10" href="#" onClick="changeTab(10);">不良反應結案</a></td>	
		<%}%>
		<%if(Integer.valueOf(obj.getStatus()) <= 10){%>
			<td nowrap ID="t11" CLASS="tab_border2"><a id="aTab11" href="#" onClick="changeTab(11);">歷史通報</a></td>
		<%}%>		
	</tr>
	<tr> 
		<%if("1".equals(obj.getCosType()) && Integer.valueOf(obj.getStatus()) > 10){%>
		<td nowrap ID="t10" CLASS="tab_border2"><a id="aTab10" href="#" onClick="changeTab(10);">不良品結案</a></td>	
		<%}%>
		<%if(Integer.valueOf(obj.getStatus()) > 10){%>
		<td nowrap ID="t11" CLASS="tab_border2"><a id="aTab11" href="#" onClick="changeTab(11);">歷史通報</a></td>
		<%}%>  
	</tr>
</tr>
</TABLE>

<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td>
	<div id="formContainer" style="height:auto;display:none">
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="cosin0102_1q.jsp" />
	</div>	
</td></tr>
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
		<% request.setAttribute("qBean", obj); %>
		<jsp:include page="cosin0400f.jsp" />
		
		<%
		if("1".equals(obj.getCosType()) && Integer.valueOf(obj.getStatus()) > 10){
		%>
		<%-- 分類頁籤 --%>
		<jsp:include page="cosin1003f.jsp" >
			<jsp:param value="Tab6" name="tabId"/>
		</jsp:include>
		<%-- 評估頁籤 --%>
		<jsp:include page="cosin0803f.jsp">
			<jsp:param value="Tab9" name="tabId"/>
		</jsp:include>
		<%-- 廠商回覆頁籤 --%>
		<jsp:include page="cosin0603f.jsp" >
			<jsp:param value="Tab7" name="tabId"/>
		</jsp:include>
		<%-- 處理頁籤 --%>
		<jsp:include page="cosin0605f.jsp" >
			<jsp:param value="Tab8" name="tabId"/>
		</jsp:include>
		<%-- 結案頁籤 --%>
		<jsp:include page="cosin0903f.jsp" >
			<jsp:param value="Tab10" name="tabId"/>
		</jsp:include>
		<%}else if("2".equals(obj.getCosType()) && Integer.valueOf(obj.getStatus()) > 10){%>
		<%-- 分類頁籤 --%>
		<jsp:include page="cosin1004f.jsp" >
			<jsp:param value="Tab6" name="tabId"/>
		</jsp:include>
		<%-- 廠商回覆頁籤 --%>
		<jsp:include page="cosin0604f.jsp" >
			<jsp:param value="Tab7" name="tabId"/>
		</jsp:include>
		<%-- 處理頁籤 --%>
		<jsp:include page="cosin0606f.jsp" >
			<jsp:param value="Tab8" name="tabId"/>
		</jsp:include>
		<%-- 結案頁籤 --%>
		<jsp:include page="cosin0904f.jsp" >
			<jsp:param value="Tab10" name="tabId"/>
		</jsp:include>
		<%}%>
		
		<table id="Tab11" width="100%" align="center" class="table_form">
			<tr>
				<td nowrap class="td_form_left" colspan="2">篩選條件
				</td>
			</tr>
			<tr>
				<td nowrap class="td_form" >通報日期</td>
				<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field_Q", "q_notifierRevDateSHis", obj.getQ_notifierRevDateSHis())%>
				~ <%=View.getPopCalendar("field_Q", "q_notifierRevDateEHis", obj.getQ_notifierRevDateEHis())%>
				</td>
			</tr>
			<tr>
				<td nowrap class="td_form" >不良事件類別</td>
				<td nowrap class="td_form_white">
					<%=View.getCommonCodeKindBoxOption("field_Q", "q_cosTypeHis", "CCT", obj.getQ_cosTypeHis()) %>
				</td>
			</tr>
			<tr>
				<td nowrap width="15%" nowrap class="td_form">不良反應狀況：</td>
				<td nowrap class="td_form_white">
					<%=obj.getCACRadioBoxOption("field_Q", "q_adverseConditionHis", obj.getQ_adverseConditionHis(), "", 10)%>
				</td>
			</tr>
			<tr>
				<td nowrap width="15%" nowrap class="td_form">不良品缺陷：</td>
				<td nowrap class="td_form_white">
					<%=obj.getCOS0003DbCheckBoxOption("field_Q", "q_mainCodeHis", obj.getQ_mainCodeHis()) %>
				</td>
			</tr>
			<tr>
				<td nowrap width="15%" nowrap class="td_form">通報單位：</td>
				<td nowrap nowrap class="td_form_white">
					<%=View.getRadioBoxOption("field_Q", "q_notifierTypeHis", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONNFT1' order by codeId", obj.getQ_notifierTypeHis(), "changeForm(this);")%>
					<br><input class="field_Q" type="text" id="s1" name="q_notifierDeptHis" size="30" maxlength="30" value="<%=obj.getQ_notifierDeptHis()%>" style="display:none"/>
			  	    <input type="button" id="s2" name="btnQryExpense" onClick="popUserJobList();" value="查詢" width="120px" class="field_Q" style="display:none"/>						
				</td>
			</tr>
						
			<!--Toolbar區============================================================-->
			<tr><td nowrap class="bgToolbar" style="text-align:center" colspan="2">
				<input class="toolbar_default" type="button" followPK="false" name="doQHistory" value="篩    選" onClick="whatButtonFireEvent(this.name)">&nbsp;
				<% if(objListHis!=null && objListHis.size()>0){%>
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
				<table class="table_form" width="99%" cellspacing="0" cellpadding="0">
				<thead id="listTHEAD">
					<tr>
						<th class="listTH"><a class="text_link_w" href="#">NO</a></th>
						<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">通報日期</a></th>
						<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">案件編號</a></th>
						<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">不良事件類別</a></th>
						<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">中文品名</a></th>
						<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',6,false);" href="#">外文品名</a></th>
						<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',7,false);" href="#">製造廠/進口代理商</a></th>
						<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',8,false);" href="#">不良反應狀況</a></th>
						<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',9,false);" href="#">不良品缺陷</a></th>
						<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',10,false);" href="#">通報單位</a></th>
						<th class="listTH"><a class="text_link_w" href="#">附件</a></th>
					</tr>
				</thead>
				<tbody id="listTBODY">
				<%
				    boolean primaryArrayHis[] = {true,false,false,false,false,false,false,false,false,false,false};
				    boolean displayArrayHis[] = {false,true,true,true,true,true,true,true,true,true,true};
				    boolean linkArrayHis[] = {false,false,true,false,false,false,false,false,false,false,true};
				    String[] alignArrayHis = {"center","center","center","center","center","center","center","center","center","center","center"};
				    out.write(View.getQuerylist(primaryArrayHis,displayArrayHis,alignArrayHis,objListHis,obj.getQueryAllFlag(),true,linkArrayHis,null,"",false,false));
				%>
				</tbody>
				</table>
			</td></tr>
		</table>
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
		case "doDoQuit":
			form1.action = "cosin0101q.jsp";
			form1.state.value = "";
			form1.queryAllFlag.value = "false";
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
		//案件列印-詢問是否遮蔽個資	  
		  case "doReportView":
			  toAsk();
			  break;
		case "doQHistory":
			beforeSubmit();
			form1.submit();
			break;
		case "doReport":
			form1.action = "cosin0102p.jsp";
			form1.target = "_blank";
			beforeSubmit();
			form1.submit();
			form1.action = "cosin0102q.jsp" ;
			form1.target = "_self";
			break;
	}
}
</script>
</html>


<!--
程式目的：醫療器材通報案件綜合查詢作業
程式代號：medin0301q
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%@ page import="java.io.*" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="MEDIN0301" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN0301Q">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())){
	obj = (com.kangdainfo.tcbw.view.medin.MEDIN0301Q)obj.queryOne();
}
if ("true".equals(obj.getQueryAllFlag())){	
	objList = (java.util.ArrayList) obj.doQueryAll();
	obj.setStateQueryOneSuccess();
}
%>
<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">

function init() {
	setDisplayItem("spanQueryAll,spanInsert,spanClear,spanConfirm,spanUpdate,spanDelete,spanListPrint,spanListHidden","H");
}
function popReportForm(){
	form1.target = "_blank";
	form1.action = "medin0301p.jsp";
	beforeSubmit();
	form1.submit();
	form1.target = "_self";
	form1.action = "medin0301q.jsp";
}
function checkField(){
	var alertStr = "";
	form1.state.value="queryAll";

	if(form1.state.value == "queryAll")
	{
		alertStr += checkQuery();
		
	}
	
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	return true;
}
function selectAllCheckBox(checkBox , selectName){
	var selectName = document.getElementsByName(selectName);
	var isChecked = checkBox.checked;
	for(var i = 0 ; i < selectName.length ; i++){
		selectName[i].checked = isChecked;
	}
}
function queryOne(id,eventKind)
{
    form1.id.value = id;
    form1.state.value = "queryOne";
    if(3!=eventKind) {
        form1.action = "medin2002f.jsp?queryType=1";
    } else {
        form1.action = "medin5001f.jsp";
    }
	beforeSubmit();
	form1.submit();
}
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:left">
    <input type="hidden" name="id" value="">	
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="userName" value="<%=User.getUserName()%>">
	<input type="hidden" name="caseType" value="<%=obj.getCaseType()%>">
	
	<span id="spanDoQueryAll">
    	<input class="toolbar_default" type="submit" followPK="false" name="doQueryAll" value="查   詢"  onclick="return checkField()">&nbsp;
    </span>
    <span id="spanQueryClear">
		<input class="toolbar_default" type="button" followPK="false" name="queryClear" value="取　消" onClick="setAllClearQ();">&nbsp;
	</span>
    <span id="spanPrint">
    	<input class="toolbar_default" type="button" followPK="false" name="btnPrint" value="匯出EXCEL" style="width:80px" onClick="popReportForm();">&nbsp;
    </span>
	<jsp:include page="../../home/toolbar.jsp" />
</td></tr>
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%">
	<tr>
		<td nowrap width="15%" nowrap class="td_form">通報案件類型</td>
		<td nowrap nowrap class="td_form_white">
		<input type="checkbox" onclick="selectAllCheckBox(this,'q_caseType');" class="field_Q"/>全選&nbsp;
		<%--MED0001-eventKind 1查不良反應(國內國外就查caseSource,medPermit = 'Z0'),2查不良品--%>
			<%=View.getCheckBoxTextOption("field_Q","q_caseType","A1;不良反應-國內;A2;不良反應-國外;A3;不良反應-專案進口;A4;不良品;B1;臨床試驗不良事件",obj.getQ_caseType())%>
		</td>
		<td nowrap width="15%" nowrap class="td_form">國內/國外</td>
		<td nowrap nowrap class="td_form_white">
		<%--MED0001,MED2001--%>
			<%=View.getCheckBoxTextOption("field_Q","q_caseSource","in;國內;out;國外;",obj.getQ_caseSource())%>
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">通報來源</td>
		<td nowrap nowrap class="td_form_white">
		<%--MED0001.drugEventSource--%>
			<%=View.getRadioBoxOption("field_Q", "q_drugEventsSources", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='MEDNFS' order by codeId", obj.getQ_drugEventsSources())%>
		</td>
		<td nowrap width="15%" nowrap class="td_form">通報者屬性</td>
		<td nowrap nowrap class="td_form_white">
		<%--MED0001,MED2001--%>
			<select class="field_Q" name="q_notifierType">
				<%=View.getOptionCodeKind("MEDNFT1", obj.getQ_notifierType()) %>
			</select>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">通報單位</td>
		<td nowrap nowrap class="td_form_white">
		<%--MED0001.notifierDept,MED2001.notifierDept--%>
			<input class="field_Q" type="text" name="q_notifyDept" size="50" maxlength="100" value="<%=obj.getQ_notifyDept()%>">
		</td>
		<td nowrap width="15%" nowrap class="td_form">列管單位</td>
		<td nowrap nowrap class="td_form_white">
		<%--MED2001.approvedUnits --%>
			<%=View.getRadioBoxTextOption("field_Q","q_approvedUnits","1;醫事司;2;食品藥物管理署;3;其他",obj.getQ_approvedUnits())%>
			<input class="field_Q" type="text" name="q_approvedUnitsOther" maxlength="30" size="20"  value="<%=obj.getQ_approvedUnitsOther()%>"  />
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">醫療器材品名</td>
		<td nowrap nowrap class="td_form_white">
		<%--MED0004.cName,MED2004.cName --%>
			<input class="field_Q" type="text" name="q_cName" size="50" maxlength="100" value="<%=obj.getQ_cName()%>">
		</td>
		<td nowrap width="15%" nowrap class="td_form">許可證字號</td>
		<td nowrap nowrap class="td_form_white">
		<%--MED0004.permitNumber,MED2004.permitNumber --%>
			字<input class="field_Q" type="text" name="q_medPermit" size="10" maxlength="10" value="<%=obj.getQ_medPermit()%>">
			號<input class="field_Q" type="text" name="q_medPermitNumber" size="10" maxlength="10" value="<%=obj.getQ_medPermitNumber()%>">
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">製造廠</td>
		<td nowrap nowrap class="td_form_white">
		<%--MED0001.medFactory,MED2001.medFactory --%>
			<input class="field_Q" type="text" name="q_medFactory" size="50" maxlength="100" value="<%=obj.getQ_medFactory()%>">
		</td>
		<td nowrap width="15%" nowrap class="td_form">製造廠國別</td>
		<td nowrap nowrap class="td_form_white">
		<%--MED0001.medCountry,MED2001.medCountry --%>
			<input class="field_Q" type="text" name="q_medCountry" size="50" maxlength="100" value="<%=obj.getQ_medCountry()%>">
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">許可證申請商</td>
		<td nowrap nowrap class="td_form_white">
		<%--MED0001.permitFirm,MED2001.permitFirm --%>
			<input class="field_Q" type="text" name="q_permitFirm" size="50" maxlength="100" value="<%=obj.getQ_permitFirm()%>">
		</td>
		<td nowrap width="15%" nowrap class="td_form">醫材主類別</td>
		<td nowrap class="td_form_white">
		<%--MED001.medMainCategory --%>
			<select class="field_Q" name="q_medMainCategory">
				<%=View.getOptionCodeKind("MEDMCT", obj.getQ_medMainCategory()) %>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">醫材次類別</td>
		<td nowrap class="td_form_white">
		<%--MED001.medSecCategory --%>
			<select class="field_Q" name="q_medSecCategory">
				<%=View.getOptionCodeKind("MEDSCT", obj.getQ_medSecCategory()) %>
			</select>
		</td>
		<td nowrap width="15%" nowrap class="td_form">通報品質</td>
		<td nowrap nowrap class="td_form_white">
		<%--MED0007 or MED0008 --%>
			<%=View.getCommonCheckBoxTextOption("field_Q","q_bulletinQuality","MEDNTQL",obj.getQ_bulletinQuality())%>
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">原始不良反應結果</td>
		<td nowrap nowrap class="td_form_white" colspan="4">
		<%--med0001.badReactionResults,med2001.badReactionResults	代碼MED20XX-MEDAD1,代碼MED00XX-MEDAD2 --%>
			<select class="field_Q" name="q_badReactionResultsAD1">
				<%=View.getOptionCodeKind("MEDAD1", obj.getQ_badReactionResultsAD1()) %>
			</select>
			<br/>
			<select class="field_Q" name="q_badReactionResultsAD2">
				<%=View.getOptionCodeKind("MEDAD2", obj.getQ_badReactionResultsAD2()) %>
			</select>
		</td>
		<%--
		<td nowrap width="15%" nowrap class="td_form">評估後不良反應結果</td>
		<td nowrap nowrap class="td_form_white">
		<%--MED0007.adverseReactionsResult--%>
		<%--
			<%=View.getRadioBoxTextOption("field_Q","q_adverseReactionsResult","1;≦2分：存疑;2;3-5分：可能相關;3;6-8分：極有可能相關;4;≧9：確定相關",obj.getQ_adverseReactionsResult())%>
		</td>
		 --%>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">醫材問題代碼名稱</td>
		<td nowrap nowrap class="td_form_white">
		<%--MED0007,8,9.medicalIssues 代碼MED1006 --%>
			<input class="field_Q" type="text" name="q_medicalIssues" size="50" maxlength="100" value="<%=obj.getQ_medicalIssues()%>">	
		</td>
		<td nowrap width="15%" nowrap class="td_form">病人問題代碼名稱</td>
		<td nowrap nowrap class="td_form_white">
		<%--MED0007.patientIssues 代碼MED1005 --%>
			<input class="field_Q" type="text" name="q_patientIssues" size="50" maxlength="100" value="<%=obj.getQ_patientIssues()%>">
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">醫材與不良反應關聯性</td>
		<td nowrap nowrap class="td_form_white">
		<%--MED0007.adverseReactionsResult--%>
			<%=View.getRadioBoxTextOption("field_Q","q_adverseReactionsResult","1;≦2分：存疑;2;3-5分：可能相關;3;6-8分：極有可能相關;4;≧9：確定相關",obj.getQ_adverseReactionsResult())%>
		</td>
		<td nowrap width="15%" nowrap class="td_form">不良品事件等級</td>
		<td nowrap nowrap class="td_form_white">
		<%--MED0008,9.eventClass --%>
			<%=View.getCommonCheckBoxOption("field_Q","q_eventClass","MEDEVC",obj.getQ_eventClass())%>
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">發生日期</td>
		<td nowrap nowrap class="td_form_white">
		<%--MED0001.occurDate,MED2001.occurDate --%>
			<%=View.getPopCalendar("field_Q","q_occurDateS",obj.getQ_occurDateS())%>~<%=View.getPopCalendar("field_Q","q_occurDateE",obj.getQ_occurDateE())%>
		</td>
		<td nowrap width="15%" nowrap class="td_form">通報日期</td>
		<td nowrap nowrap class="td_form_white">
		<%--MED0001.notifierRepDate,MED2001.notifierRepDate --%>
			<%=View.getPopCalendar("field_Q","q_notifierRepDateS",obj.getQ_notifierRepDateS())%>~<%=View.getPopCalendar("field_Q","q_notifierRepDateE",obj.getQ_notifierRepDateE())%>
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">案件狀態</td>
		<td nowrap nowrap class="td_form_white">
			<span>臨床
			<select class="field_Q" name="q_caseStatus_MED2001">
				<%=View.getOptionCodeKind("MEDSTATUS1", obj.getQ_caseStatus_MED2001()) %>
			</select>
			</span><br/>
			<span>不良反應
			<select class="field_Q" name="q_caseStatus_eventType_1">
				<%=View.getOptionCodeKind("MEDSTATUS2", obj.getQ_caseStatus_eventType_1()) %>
			</select>
			</span><br/>
			<span>不良品
			<select class="field_Q" name="q_caseStatus_eventType_2">
				<%=View.getOptionCodeKind("MEDSTATUS2", obj.getQ_caseStatus_eventType_2()) %>
			</select>
			</span>
		</td>
		<td nowrap width="15%" nowrap class="td_form">NCAR通報狀況</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getRadioBoxTextOption("field_Q","q_ncarResult","1;通報;2;不通報",obj.getQ_ncarResult())%>	
		</td>
	</tr>
	<tr>
	    <td nowrap width="15%" nowrap class="td_form">選取查詢結果欄位：</td>
		<td nowrap nowrap class="td_form_white" colspan="3">
			<table width="100%" id="reportTable">
				<tr>
					<td width="5%" class="td_lable"><input type="checkbox" class="field_Q" onclick="selectAllTable(this)"></td>
					<td width="19%" class="td_lable"><input type="checkbox" class="field_Q" id="1" onclick="selectColumn(this)"></td>
					<td width="19%" class="td_lable"><input type="checkbox" class="field_Q" id="2" onclick="selectColumn(this)"></td>
					<td width="19%" class="td_lable"><input type="checkbox" class="field_Q" id="3" onclick="selectColumn(this)"></td>
					<td width="19%" class="td_lable"><input type="checkbox" class="field_Q" id="4" onclick="selectColumn(this)"></td>
					<td width="19%" class="td_lable"><input type="checkbox" class="field_Q" id="5" onclick="selectColumn(this)"></td>
				</tr>
				<tr>
					<td class="td_lable"><input type="checkbox" class="field_Q" id="1" onclick="selectRow(this)"></td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="applNo">案件編號</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="caseType">通報案件類型</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="caseSource">國內/國外</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="drugEventsSources">通報來源</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="notifierType">通報者屬性</td>
				</tr>
				<tr>
					<td class="td_lable"><input type="checkbox" class="field_Q" id="2" onclick="selectRow(this)"></td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="notifierDept">通報單位</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="approvedUnits">列管單位</td>
					<%--MED0001.medCname,MED2001.medTestMedical --%>
					<td><input type="checkbox" class="field_Q" name="reportField" value="medCname">醫療器材品名</td>
					<%--需要查字和號 --%>
					<td><input type="checkbox" class="field_Q" name="reportField" value="medPermit">許可證字號</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="medFactory">製造廠 </td>					
				</tr>
				<tr>
					<td class="td_lable"><input type="checkbox" class="field_Q" id="3" onclick="selectRow(this)"></td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="medCountry">製造廠國別</td>
					<%--MED0001.medPermitFirm,MED2001.medSupplier --%>
					<td><input type="checkbox" class="field_Q" name="reportField" value="medPermitFirm">許可證申請商</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="medMainCategory">醫材主類別</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="medSecCategory">醫材次類別</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="bulletinQuality">通報品質 </td>					
				</tr>
				<tr>
					<td class="td_lable"><input type="checkbox" class="field_Q" id="4" onclick="selectRow(this)"></td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="badReactionResults">原始不良反應結果</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="">評估後不良反應結果</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="medicalIssues">醫材問題代碼</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="patientIssues">病人問題代碼</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="adverseReactionsResult">醫材與不良反應關聯性</td>					
				</tr>
				<tr>
					<td class="td_lable"><input type="checkbox" class="field_Q" id="5" onclick="selectRow(this)"></td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="eventClass">不良品事件等級</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="occurDate">發生日期</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="notifierRepDate">通報日期</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="status">案件狀態</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="ncarResult">NCAR通報狀況</td>					
				</tr>
			</table>
		</td>
	</tr>
	</table>
	</div>	
</td></tr>
<!--List區============================================================-->
<tr><td nowrap class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>

<tr><td nowrap>
<br>
<tr><td nowrap class="bgList">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">NO</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">通報日期</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">案件編號</a></th>		
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">通報案件類型</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">國內/國外</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">醫療器材品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">申請商</a></th>
        <th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',8,false);" href="#">案件狀態</a></th>
        <th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',8,false);" href="#">明細</a></th>	
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {true,false,false,false,false,false,false,false,false,true};
	    boolean displayArray[] = {false,true,true,true,true,true,true,true,true,false};
	    boolean linkArray[] = {false,false,false,false,false,false,false,false,true,false};
	    String[] alignArray  =  {"center","center","center","center","center","center","center","center","center","center"};
		out.write(View.getQuerylist(primaryArray,displayArray,alignArray,objList,obj.getQueryAllFlag(),true,linkArray,null,"",false,false));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
</body>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName){
	case "queryAll":
		break;
	}
}
</script>
</html>

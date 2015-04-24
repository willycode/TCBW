<!--
程式目的：化粧品綜合查詢
程式代號：cosin0201q
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
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="COSIN0201" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN0201Q">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
String caseType = Common.get(request.getParameter("caseType"));
obj.setCaseType(caseType);

if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())){

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
	form1.action = "cosin0201p.jsp";
	beforeSubmit();
	form1.submit();
	form1.target = "_self";
	form1.action = "cosin0201q.jsp";
}

function checkField(){
	var alertStr = "";
	form1.state.value = "queryAll";

	if(form1.state.value == "queryAll"){
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

function queryOne(id){
    form1.id.value = id;
    form1.state.value = "queryOne";
    form1.action = "cosin0102q.jsp";
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
		<td nowrap width="15%" nowrap class="td_form">案件編號</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_applNoS" size="11" maxlength="11" value="<%=obj.getQ_applNoS()%>" onChange="toUpper(this);">
			~
			<input class="field_Q" type="text" name="q_applNoE" size="11" maxlength="11" value="<%=obj.getQ_applNoE()%>" onChange="toUpper(this);">
		</td>
		<td nowrap width="15%" nowrap class="td_form">通報日期</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_notifierRepDateS",obj.getQ_notifierRepDateS())%>~<%=View.getPopCalendar("field_Q","q_notifierRepDateE",obj.getQ_notifierRepDateE())%>
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">通報來源</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getCommonRadioBoxOption("field_Q", "q_notifierSource", "CIS", obj.getQ_notifierSource(), "codeId", 4)%>
		</td>
		<td nowrap width="15%" nowrap class="td_form">通報者屬性</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getRadioBoxOption("field_Q", "q_notifierType", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONNFT1' order by codeId", obj.getQ_notifierType(), "changeForm(this);")%>
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">通報者職稱</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getRadioBoxOption("field_Q", "q_notifierTitle", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='TITLE' order by codeId", obj.getQ_notifierTitle())%>
		</td>
		<td nowrap width="15%" nowrap class="td_form">不良事件類別</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getCommonCodeKindBoxOption("field_Q", "q_cosType", "CCT", obj.getQ_cosType()) %>
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">許可證字號</td>
		<td nowrap nowrap class="td_form_white">
			<select class="field_Q" name="q_permitKey" type="select">
				<%=View.getOptionCodeKind("CPT", obj.getQ_permitKey()) %>
			</select>
			 字第
			<input class="field_Q" type="text" name="q_permitNo" size="15" maxlength="15" value="<%=obj.getQ_permitNo()%>">
		</td>
		<td nowrap width="15%" nowrap class="td_form">留案備查：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="checkbox" name="q_preResult" value="Y" <%=obj.getQ_preResult().equals("Y")?"checked":"" %>>
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">登錄編號</td>
		<td nowrap nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_caseNo" size="16" maxlength="16" value="<%=obj.getQ_caseNo()%>">
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
					<td><input type="checkbox" class="field_Q" name="reportField" value="notifierRepDate">接獲通報日期</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="notifierSource">通報來源</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="notifierType">通報者屬性</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="notifierTitle">通報者職稱</td>
				</tr>
				<tr>
					<td class="td_lable"><input type="checkbox" class="field_Q" id="2" onclick="selectRow(this)"></td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="cosType">不良事件類別</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="permitKey">許可證字號</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="caseNo">登錄編號</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="chProduct">化粧品品名</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="ingredient">化粧品項目</td>					
				</tr>
				<tr>
					<td class="td_lable"><input type="checkbox" class="field_Q" id="3" onclick="selectRow(this)"></td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="traffickWay">販賣通路</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="tradePlace">購買地點</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="manufactorName">製造廠/進口代理商</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="manufactorNo">製造批號或製造日期</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="subCode">不良品缺陷描述</td>				
				</tr>
				<tr>
					<td class="td_lable"><input type="checkbox" class="field_Q" id="4" onclick="selectRow(this)"></td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="adverseCondition">不良反應結果</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="isMitigateYn">停用後不良反應是否減輕</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="isRecurYn">再使用是否出現同樣反應</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="cName">併用化粧品品名</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="firstResult">不良品初步判定結果</td>	
				</tr>
				
				<tr>
					<td class="td_lable"><input type="checkbox" class="field_Q" id="5" onclick="selectRow(this)"></td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="preResult">不良反應初步判定結果</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="measure">採取措施</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="timingLev">時序性</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="dealWith">後續處理</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="dept1">衛生單位回覆狀況</td>
				</tr>
				<tr>
					<td class="td_lable"><input type="checkbox" class="field_Q" id="6" onclick="selectRow(this)"></td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="dept2">廠商回覆狀況</td>
					<td><input type="checkbox" class="field_Q" name="reportField" value="cosCorrelation">不良反應與可疑化粧品相關性</td>
					<td></td>
					<td></td>
					<td></td>					
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
<TABLE CELLPADDING=0 CELLSPACING=0>
	<TR>
		<td nowrap ID=t1 CLASS="tab_border1">化粧品綜合查詢結果</TD>		
	</TR>
</TABLE>
<tr><td nowrap class="bgList">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">NO</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">通報日期</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">案件編號</a></th>		
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">不良事件類型</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">中文品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">外文品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">製造商/進口代理商</a></th>
        <th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',8,false);" href="#">案件狀態</a></th>
        <th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',8,false);" href="#">明細</a></th>	
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {true,false,false,false,false,false,false,false,false};
	    boolean displayArray[] = {false,true,true,true,true,true,true,true,true};
	    boolean linkArray[] = {false,false,false,false,false,false,false,false,true};
	    String[] alignArray  =  {"center","center","center","center","center","center","center","center","center"};
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

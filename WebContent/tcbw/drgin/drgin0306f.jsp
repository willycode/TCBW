<!--
程式目的：藥品療效不等諮議會作業
程式代號：drgin0306
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%@ page import="java.io.*" %>
<jsp:include page="../../home/secure.jsp"/>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0306F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<jsp:useBean id="objList19" scope="page" class="java.util.ArrayList"/>
<%
if("".equals(obj.getUserID())){
  obj.setUserID(User.getUserId());
}
if ("init".equals(obj.getState())) {
	obj.setQueryAllFlag("true");
	obj.setTabId(Common.get(request.getParameter("tabId")));
}else if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())) {
	obj.setHttpRequest(pageContext.getRequest());
	obj.doUpdate();
	obj.setState("updateSuccess");
}
if ("true".equals(obj.getQueryAllFlag())){
	obj = (com.kangdainfo.tcbw.view.drgin.DRGIN0306F)obj.queryOne();
	objList = (java.util.ArrayList) obj.doQueryAll();
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
<script type="text/javascript" src="../../js/cbToggle.js"></script>
<script type="text/javascript">
function checkField(){
	var alertStr = "";
	if(alertStr.length > 0){
		alert(alertStr);
		return false;
	}
	beforeSubmit();
	return true;
}

function init() 
{
	setDisplayItem("spanInsert,spanClear,spanConfirm,spanUpdate,spanQueryAll,spanDelete,spanListPrint,spanListHidden","H");
	if(null != "<%=obj.getTabId()%>" && "" != "<%=obj.getTabId()%>"){
		changeTab("<%=obj.getTabId()%>");
	}else{
		changeTab(1);
	}
	setUnpkOpen();
	if("Y" == form1.isClose.value){
		setFormItem("maintain1,maintain3","R");
	}
	if("Y" == form1.isMedSend.value){
		setDrg49Readonly();
		setCon19Readonly();
	}
}
function beforeInit() {
	<%=obj.getDrg49JSBuilder()%>
	<%=obj.getCon19JSBuilder()%>
}
function doUpdateCase(type)
{
	var alertStr = "";
	if (isObj(cb)) {
		if (cb.AnyChecked()==false){
			alertStr += "您尚未勾選任何諮議會評估案件！";
		}
	}
	alertStr += validateDrg49Table();
	if(alertStr.length > 0){
		alert(alertStr);
	}else{
		form1.actionType.value = type;
		form1.state.value="update";
		setBeforePageUnload(false);
		beforeSubmit();
		form1.submit();
	}
}

function toQuery(){
	form1.action = "drgin0305f.jsp";
	form1.state.value = "init";
	form1.submit();
}

function windowClose()
{
	opener.doQueryOneData();
	window.close();//關閉自己(子視窗)
}

function changeTab(tabId) {
	document.getElementById("t1").className = "tab_border2";
	document.getElementById("t2").className = "tab_border2";
	document.getElementById("tab1").className = "";
	document.getElementById("tab2").className = "";
		
	document.getElementById("Tb1").style.display = 'none';
	document.getElementById("Tb2").style.display = 'none';
	if (tabId==2) {
		document.getElementById("t2").className = "tab_border1";	
		document.getElementById("Tb2").style.display = '';
		document.getElementById("tab2").className = "text_w";		
	}else{
		document.getElementById("t1").className = "tab_border1";
		document.getElementById("Tb1").style.display = '';
		document.getElementById("tab1").className = "text_w";
	}
}
</script>
</head>
<body onLoad="beforeInit();whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:left">
	<table width="100%" border="0" CELLPADDING="0" CELLSPACING="2" align="center">
	<tr><td>
		<input type="hidden" name="id" value="<%=obj.getId()%>">
		<input type="hidden" name="state" value="<%=obj.getState()%>">
		<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
		<input type="hidden" name="q_permitKey" value="<%=obj.getQ_permitKey()%>">
		<input type="hidden" name="q_permitNo" value="<%=obj.getQ_permitNo()%>">
		<input type="hidden" name="isMedSend" value="<%=obj.getIsMedSend()%>">
		<input type="hidden" name="actionType" value="<%=obj.getActionType()%>">
		<input type="hidden" name="isClose" value="<%=obj.getIsClose()%>">

		<jsp:include page="../../home/toolbar.jsp" />
		<span id="spanMaintain1">
	    	<input class="toolbar_default" type="button" followPK="false"  name="maintain1" value="存     檔" onClick="doUpdateCase(1);">&nbsp;
	    </span>
	    <span id="spanMaintain2">
			<input class="toolbar_default" type="button" followPK="false" name="maintain2" value="返 回 查 詢" onClick="toQuery();">&nbsp;
		</span>
		<span id="spanMaintain3">
			<input class="toolbar_default" type="button" followPK="false" name="maintain3" value="評 估 完 成" onClick="doUpdateCase(2);">&nbsp;
		</span>
		<span id="spanMaintain4">
			<input class="toolbar_default" type="button" followPK="false" name="maintain4" value="關     閉" onClick="windowClose();">&nbsp;
		</span>
	</td></tr>
	</table>
</td></tr>
<tr><td>
	<TABLE CELLPADDING="0" CELLSPACING="0" valign="top">
		<tr>
			<td id="t1" CLASS="tab_border1"><a id="tab1" href="#" onClick="changeTab(1);">諮議會評估</a></td>
			<td id="t2" CLASS="tab_border2"><a id="tab2" href="#" onClick="changeTab(2);">醫院調查</a></td>
		</tr>
	</TABLE>
</td></tr>
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table id="Tb1" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form" >諮議會評估</td>
			<td nowrap class="td_form_white">
				<textarea class="field"  name="assessDesc" cols="30" rows="3" ><%=obj.getAssessDesc()%></textarea>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >處理方式</td>
			<td nowrap class="td_form_white">
				<%=View.getCheckBoxOption("field", "assessResult", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId = 'DRG0309' order by codeId ", obj.getAssessResult())%>
			</td>
		</tr>
		<!--List區============================================================-->
		<tr><td nowrap class="bgList" colspan="2">
		<div id="listContainer">
		<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
			<thead id="listTHEAD">
			<tr>
				<th class="listTH"><a class="text_link_w"><input type="checkbox" id="cbAll" name="cbAll" class="field_Q"></a></th>
				<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">案件號碼</a></th>
				<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">通報日期</a></th>
				<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">懷疑療效不等藥品</a></th>
				<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">通報事件後果</a></th>
				<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">不良反應等級</a></th>
				<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">療效不等風險評估結果</a></th>
			</tr>
			</thead>
			<tbody id="listTBODY">
			<%
			    boolean primaryArray[] = {true,false,false,false,false,false,false,false};
			    boolean displayArray[] = {false,false,true,true,true,true,true,true};
			    out.write(View.getCheckboxQuerylist(primaryArray,displayArray,null,objList,obj.getQueryAllFlag(),"ids",null,null,"field_Q", -1, false, false));
			%>
			</tbody>
		</table>
		</div>
		</td></tr>
	</table>
	<table id="Tb2" width="100%" align="center" class="table_form">
		<tr><td>
			<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td nowrap class="bg" >
						<jsp:include page="drgin0349Layout.jsp" />
					</td>
				</tr>
			</table>
			<br><br>
		</td></tr>
		
		<!--List區============================================================-->
		<tr><td nowrap class="bgList" colspan="2">
		<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<jsp:include page="drgin0319Layout.jsp" />
				</td>
			</tr>
		</table>
		</td></tr>
	</table>
	</div>
</td></tr>
</table>
</form>
</body>
<script>
var cb = new cbToggle('cb',document.form1,form1.cbAll,'ids');
cb.config.cssTopLevel = true;
</script>
</html>
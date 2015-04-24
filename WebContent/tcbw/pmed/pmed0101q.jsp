<!--
程式目的：PSUR查詢
程式代號：
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
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="PMED0101Q" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.pmed.PMED0101Q">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) 
{
	if ("false".equals(obj.getQueryAllFlag()))
	{
		obj.setQueryAllFlag("true"); 
	}
}
else if ("queryOne".equals(obj.getState()))
{
	obj = (com.kangdainfo.tcbw.view.pmed.PMED0101Q)obj.queryOne();
}
obj.setQueryAllFlag("true"); 

if ("true".equals(obj.getQueryAllFlag()))
{	
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

function checkField()
{
	var alertStr = "";	
	
	if(alertStr.length > 0){
		alert(alertStr);
		return false;
	}
	beforeSubmit();
	form1.state.value = "queryAll";
	return true;
}


function init() 
{
	setDisplayItem("spanQueryAll,spanInsert,spanClear,spanConfirm,spanUpdate,spanDelete,spanListPrint,spanListHidden","H");
}

function toCreate()
{
	form1.state.value = "queryOne";
	form1.doType.value = "insert";
	form1.action = "pmed0101f.jsp";
	form1.submit();
}
function queryOne(id)
{
    form1.id.value = id;
    form1.doType.value = "update";
    form1.state.value = "queryOne";
    form1.action = "pmed0101f.jsp";
	beforeSubmit();
	form1.submit();
}
var popWinName;
function permitDataQ()
{
	var prop="";
	var windowTop=120; 
	var windowLeft=120;
	var q_PermitKey = form1.q_permitKey.value;
	var q_PermitNo = form1.q_permitNo.value;
	prop=prop+"width=1200px,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes";
	if (popWinName!=null) popWinName.close();
	popWinName = window.open("../medex/medex0107f.jsp?medPermit="+q_PermitKey+"&medPermitNumber="+q_PermitNo,'popWinE',prop);		
}
function permitData2(id,a)
{
	var q_medPermit = id.substring(0,2);
	var q_medPermitNumber = id.substring(2,8);
	form1.q_permitKey.value = q_medPermit;
	form1.q_permitNo.value = q_medPermitNumber;
	permitData1('q_permitKey','q_permitNo');
}
function permitData1(medPermit1,medPermitNumber1)
{
	var q_medPermit = document.all.item(medPermit1).value;
	var q_medPermitNumber = document.all.item(medPermitNumber1).value;
}
</script>
</head>
<body  onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">

<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:left">
    <input type="hidden" name="id" value="">	
    <input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="userName" value="<%=User.getUserName()%>">
	<input type="hidden" name="doType" value="<%=obj.getDoType()%>">
	<input type="hidden" name="caseType" value="<%=obj.getCaseType()%>">

	<jsp:include page="../../home/toolbar.jsp" />
	<span id="toCreate">
    	<input class="toolbar_default" type="button" followPK="false"  name="toCreate1" value="新   增"  onClick="toCreate();">&nbsp;
    </span>
    <span id="spanDoQueryAll">
    	<input class="toolbar_default" type="submit" followPK="false" name="doQueryAll" value="查   詢">&nbsp;
    </span>
    <span id="spanListToExcel">
        <input class="toolbar_default" type="button" followPK="false" name="listToExcel" value="列表列印" onClick="listToExcelReport('listTHEAD','listTBODY')">&nbsp;
    </span>	


	
</td>
</tr>
<!--Form區============================================================-->
<tr>
<td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%">
	<tr>
		<td nowrap class="td_form" width="15%">案件編號：</td>
		<td nowrap class="td_form_white" width="35%">
			<input class="field_Q" type="text" name="q_applNoS" size="11" maxlength="11" value="<%=obj.getQ_applNoS()%>">
			- 
			<input class="field_Q" type="text" name="q_applNoE" size="11" maxlength="11" value="<%=obj.getQ_applNoE()%>">
		</td>
		<td nowrap class="td_form" >公告文號</td>
			<td nowrap class="td_form_white" >
				<input class="field_Q" type="text" name="q_monitorNo" value="<%=obj.getQ_monitorNo()%>">
		</td>
	</tr> 
	<tr>
		<td nowrap class="td_form" >案件狀態：</td>
		<td nowrap class="td_form_white" colspan="3">
	        <select name="q_status" class="field_Q">
			    <%=View.getOptionCodeKind("MEDRPSTATUS", obj.getQ_status())%>
		    </select>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">許可證字：</td>
		<td nowrap class="td_form_white">
		    <select name="q_permitKey" class="field_Q">
				<%=View.getOptionCodeKind("MEDPKID",obj.getQ_permitKey())%>
			</select>
			字第<input class="field_Q" type="text" name="q_permitNo" size="10" maxlength="6" value="<%=obj.getQ_permitNo()%>">號
		    <input type="button" name="btnQryExpense" onClick="permitDataQ();" value="查詢" width="120px" class="field_Q" >
		</td>
		<td nowrap class="td_form" >中文品名</td>
		<td nowrap class="td_form_white" >
			<input class="field_Q" type="text" name="q_chProduct" value="<%=obj.getQ_chProduct()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" >許可證持有商</td>
		<td nowrap class="td_form_white"  colspan="3">
			<input class="field_Q" type="text" name="q_applicationName" value="<%=obj.getQ_applicationName()%>">
		</td>		
	</tr>
	<tr>
		<td nowrap class="td_form">醫材主類別</td>
		<td nowrap class="td_form_white" >
			<%=View.getPopCode("field_Q","q_medMainCategoryCode","",Common.get(View.getOneCodeName("MEDMCT",Common.get(""))),"","MEDMCT","q_medMainCategory",obj.getQ_medMainCategory())%>		
		</td>
		<td nowrap class="td_form">醫材次類別</td>
		<td nowrap class="td_form_white" >
			<%=View.getPopCode("field_Q","q_medSecCategoryCode","",Common.get(View.getOneCodeName("MEDSCT",Common.get(""))),"q_medMainCategory","MEDSCT","q_medSecCategory",obj.getQ_medSecCategory())%>
					
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" >監控期間</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_monitorSDate",obj.getQ_monitorSDate())%>~
			<%=View.getPopCalendar("field_Q","q_monitorEDate",obj.getQ_monitorEDate())%>
		</td>
		<td nowrap width="15%" nowrap class="td_form">是否為歷史移轉資料：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getRadioBoxTextOption("field_Q","q_trans","Y;是;N;否",obj.getQ_trans())%>		
		</td>	
	</tr>
	</table>
	</div>	
</td>
</tr>

<!--List區============================================================-->
<tr><td nowrap class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>
<tr>
<td class="bgList">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',0,false);" href="#">NO</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">案件編號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">案件狀態</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">許可證字號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">中文品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">許可證持有商</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">監控期間(起/迄)</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">明細</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {true,false,false,false,false,false,false};
	    boolean displayArray[] = {false,true,true,true,true,true,true};
	    boolean linkArray[] = {false,true,false,false,false,false,false};
	    String[] alignArray  =  {"center","center","center","center","center","center","center"};
		out.write(View.getQuerylistPlusDetailButton(primaryArray,displayArray,null,objList,obj.getQueryAllFlag(),true,linkArray,null,"",false, false));
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

<!--
程式目的：醫療器材臨床試驗不良事件通報登錄作業-案件查詢作業
程式代號：medex5101f
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
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="MEDEX5101" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.medex.MEDEX5101F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
String caseType = Common.get(request.getParameter("caseType"));
obj.setCaseType(caseType);

String q_status= Common.get(request.getParameter("q_status"));

obj.setInOrOut(User.getInORout());
if ("queryOne".equals(obj.getState()))
{
	obj = (com.kangdainfo.tcbw.view.medex.MEDEX5101F)obj.queryOne();
}

obj.setQueryAllFlag("true");

if("".equals(obj.getUserID()))
{
  obj.setUserID(User.getUserId());
}

if(User.getCommonDepartment() != null)
{											// 判別內外部人員
	if("01".equals(Common.get(User.getCommonDepartment().getShortCode()))){
		obj.setIsInOrOutPerson("O");
	}
	else
	{
		obj.setIsInOrOutPerson("I");
	}
}else
{
	obj.setIsInOrOutPerson("O");
}


if("true".equals(obj.getQueryAllFlag()))
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

function init() 
{
	setDisplayItem("spanQueryAll,spanInsert,spanClear,spanConfirm,spanUpdate,spanDelete,spanListPrint,spanListHidden","H");
	listContainerRowClick(0);
}

function toCreate()
{
	form1.state.value = "queryOne";
	form1.doType.value = "insert";
	form1.action = "medex5102f.jsp";
	form1.submit();
}

function queryOne(id)
{
    updateData(id);
}

function updateData(id)
{
	form1.id.value = id;
	form1.doType.value = "update";
	form1.state.value = "queryOne";
	form1.action = "medex5102f.jsp?q_status="+'<%=q_status%>';
	beforeSubmit();
	form1.submit();
}


function popImport()
{
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=800,height=300,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	window.open("../../home/popImpExcel.jsp?formType=MEDEX5101","",prop);
}

function checkField()
{
	var alertStr = "";
	alertStr += checkDate(form1.q_occurDateS, "通報日期起訖-起");
	alertStr += checkDate(form1.q_occurDateE, "通報日期起訖-迄");
	alertStr += checkDateSE(form1.q_occurDateS, form1.q_occurDateE, "通報日期起訖");
	
	if(alertStr.length > 0){
		alert(alertStr);
		return false;
	}
	beforeSubmit();
	form1.state.value = "queryAll";
	return true;
}

</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr>
<td nowrap class="bgToolbar" style="text-align:left">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="caseType" value="<%=obj.getCaseType()%>">
	<jsp:include page="../../home/toolbar.jsp" />
	<%if(!"40".equals(obj.getQ_status())){ %>
	<span id="toCreate">
    	<input class="toolbar_default" type="button" followPK="false"  name="toCreate1" value="開始通報"  onClick="toCreate();">&nbsp;(欲通報案件請按此鈕)
    </span> 
    <br>
    <%}%>
	<input class="toolbar_default" type="submit" followPK="false" name="drgin0101q_queryAll" value="查　詢" onSubmit="return checkField()">&nbsp;(欲查詢案件請按此鈕)
	<br>
	<%if(!"40".equals(obj.getQ_status())){ %>
    <span id="spanDoImport">
		<input class="toolbar_default" type="button" followPK="false" name="doImport" value="EXCEL匯入" onClick="popImport()">&nbsp;
	</span>
	<br>
	<%}%>
	<span id="spanListToExcel">
        <input class="toolbar_default" type="button" followPK="false" name="listToExcel" value="列表列印" onClick="listToExcelReport('listTHEAD','listTBODY')">&nbsp;
    </span>
</td>
</tr>

<tr>
<td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="medex5101q.jsp" />
	</div>
</td>
</tr>


<!--List區============================================================-->
<tr><td nowrap class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td>
</tr>


<tr>
<td nowrap class="bgList">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',0,false);" href="#">NO</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">案件編號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">通報日期</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">核准文號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">許可證字號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">醫材品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">醫材主類別</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">案件狀態</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',8,false);" href="#">明細</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {true,false,false,false,false,false,false,false};
	    boolean displayArray[] = {false,true,true,true,true,true,true,true};
	    boolean linkArray[] = {false,true,false,false,false,false,false,false};
	    String[] alignArray  =  {"center","center","center","center","center","center","center","center"};
		//out.write(obj.getQuerylist(primaryArray, displayArray,alignArray, objList, obj.getQueryAllFlag(), true, "", true));
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

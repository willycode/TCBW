<!--
程式目的：醫療器材不良事件通報 > > 不良品案件複評 > > 評估中
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
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="MEDIN0801" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN0801F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
String caseType = Common.get(request.getParameter("caseType"));
obj.setCaseType(caseType);

String statusType = Common.get(request.getParameter("statusType"));
obj.setStatusType(statusType);


if ("queryAll".equals(obj.getState())) 
{
	if ("false".equals(obj.getQueryAllFlag()))
	{
		obj.setQueryAllFlag("true"); 
	}
}
else if ("queryOne".equals(obj.getState()))
{
	obj = (com.kangdainfo.tcbw.view.medin.MEDIN0801F)obj.queryOne();
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
<script type="text/javascript">

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


function init() 
{
	setDisplayItem("spanQueryAll,spanInsert,spanClear,spanConfirm,spanUpdate,spanDelete,spanListPrint,spanListHidden","H");
}

function updateData(id)
{
	form1.id.value = id;
	form1.doType.value = "update";
	form1.state.value = "queryOne";

    var url="";

    url+="medin0802f.jsp?";
    url+="statusType="+'<%=statusType%>';
    url+="&caseType="+'<%=obj.getCaseType()%>'
    
	form1.action =url;

	beforeSubmit();
	form1.submit();
}

function queryOne(id)
{
	updateData(id);
}

var popWinName;
function permitDataQ()
{
	var prop="";
	var windowTop=120; 
	var windowLeft=120;
	var q_medPermit = form1.q_medPermit.value;
	var q_medPermitNumber = form1.q_medPermitNumber.value;
	prop=prop+"width=1200px,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes";
	if (popWinName!=null) popWinName.close();
	popWinName = window.open("../medex/medex0107f.jsp?q_medPermit="+q_medPermit+"&q_medPermitNumber="+q_medPermitNumber,'popWinE',prop);		
}
function permitData2(id,a)
{
	var q_medPermit = id.substring(0,2);
	var q_medPermitNumber = id.substring(2,8);
	form1.q_medPermit.value = q_medPermit;
	form1.q_medPermitNumber.value = q_medPermitNumber;
	permitData1('q_medPermit','q_medPermitNumber');
}

function permitData1(medPermit1,medPermitNumber1)
{
	var q_medPermit = document.all.item(medPermit1).value;
	var q_medPermitNumber = document.all.item(medPermitNumber1).value;
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
	<input type="hidden" name="doType" value="<%=obj.getDoType()%>">
	<jsp:include page="../../home/toolbar.jsp" />
	<span id="spanDoQueryAll">
    	<input class="toolbar_default" type="submit" followPK="false" name="doQueryAll" value="查   詢">&nbsp;
    </span>
    <span id="spanQueryClear">
		<input class="toolbar_default" type="button" followPK="false" name="queryClear" value="取　消" onClick="setAllClearQ();">&nbsp;
	</span>
	<span id="spanListToExcel">
        <input class="toolbar_default" type="button" followPK="false" name="listToExcel" value="列表列印" onClick="listToExcelReport('listTHEAD','listTBODY')">&nbsp;
    </span>
</td>
</tr>
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="medin0801q.jsp" />
	</div>	
</td></tr>

<!--List區============================================================-->
<!--
<tr>
<td nowrap class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td>
</tr>
-->
<tr>
<td class="bgList">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',0,false);" href="#">NO</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">燈號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">案件編號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">通報日期</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">許可證字號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">醫材品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">醫材主類別</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">案件狀態</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',8,false);" href="#">作業人員</a></th>
		<th class="listTH"><a class="text_link_w" >明細</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {true,false,false,false,false,false,false,false,false,false};
	    boolean displayArray[] = {false,true,true,true,true,true,true,true,false,true};
	    boolean linkArray[] = {false,false,true,false,false,false,false,false,false,false};
	    String[] alignArray  =  {"center","center","center","center","center","center","center","center","center","center"};
	    out.write(View.getQueryDiscolorList(primaryArray, displayArray,alignArray, objList, obj.getQueryAllFlag(), true, "", false,"medin0802f.jsp","&caseType="+caseType,"&statusType="+statusType,2));
	    //out.write(View.getQuerylistPlusDetailButton(primaryArray,displayArray,null,objList,obj.getQueryAllFlag(),true,linkArray,null,"",false, false));
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

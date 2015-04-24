<!--
程式目的：醫療器材不良事件通報查詢作業
程式代號：medin2001q
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%@ page import="java.io.*" %>
<%if("Y".equals(Common.get(request.getParameter("isPop")))){%>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="MEDIN2001" />
</jsp:include>
<%} %>

<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN2001Q">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
//String caseType = Common.get(request.getParameter("caseType"));
//obj.setCaseType(caseType);
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())){
	obj = (com.kangdainfo.tcbw.view.medin.MEDIN2001Q)obj.queryOne();
}
obj.setQueryAllFlag("true");
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
var insertDefault;	//二維陣列, 新增時, 設定預設值
function checkField()
{
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

function init() 
{
	setDisplayItem("spanQueryAll,spanInsert,spanClear,spanConfirm,spanUpdate,spanDelete,spanListPrint,spanListHidden","H");
}


var popWinName;
function permitDataQ()
{
	var prop="";
	var windowTop=120; 
	var windowLeft=120;
	var medPermit = form1.q_medPermit.value;
	var medPermitNumber = form1.q_medPermitNumber.value;
	prop=prop+"width=1200px,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes";
	if (popWinName!=null) popWinName.close();
	popWinName = window.open("../medex/medex0107f.jsp?medPermit="+medPermit+"&medPermitNumber="+medPermitNumber,'popWinE',prop);		
}

function queryOne(id)
{
    form1.id.value = id;
    form1.state.value = "queryOne";
    form1.action = "medin2002f.jsp";
	beforeSubmit();
	form1.submit();
}
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

function doBack()
{
	form1.action = "medin2001q.jsp";
	form1.state.value = "queryAll";
	form1.submit();
}
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" action="medin2001q.jsp" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:left">
    <input type="hidden" name="id" value="">	
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="userName" value="<%=User.getUserName()%>">
	<input class="toolbar_default" type="submit" followPK="false" name="queryAll" value="查　詢" onSubmit="">&nbsp;
	<span id="spanQueryClear">
		<input class="toolbar_default" type="button" followPK="false" name="queryClear" value="取　消" onClick="setAllClearQ();">&nbsp;
	</span>
	<span id="spanListToExcel">
        <input class="toolbar_default" type="button" followPK="false" name="listToExcel" value="列表列印" onClick="listToExcelReport('listTHEAD','listTBODY')">&nbsp;
    </span>
	<jsp:include page="../../home/toolbar.jsp" />
	
</td></tr>
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="medin2001_1q.jsp" />
	</div>	
</td></tr>

<!--List區============================================================-->
<tr><td nowrap class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>


<tr>
<td nowrap class="bgList">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',0,false);" href="#">NO</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">案件編號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">通報日期</a></th>		
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">不良事件類別</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">許可證字號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">醫材品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">申請商</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">製造廠</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',8,false);" href="#">案件狀態</a></th>
		<th class="listTH">明細</th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {true,false,false,false,false,false,false,false,false,false,false,false};
	    boolean displayArray[] = {false,true,true,true,true,true,true,true,false,false,true,true};
	    boolean linkArray[] = {false,true,false,false,false,false,false,false,false,false,false,true};
	    String[] alignArray  =  {"center","center","center","center","center","center","center","center","center","center","center","center"};
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

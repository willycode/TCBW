<!--
程式目的：藥品不良品通報登錄作業
程式代號：drgex0101
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
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="DRGEX0101" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgex.DRGEX0101F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
String caseType = Common.get(request.getParameter("caseType"));
obj.setCaseType(caseType);

obj.setQueryAllFlag("true");

if("".equals(obj.getUserID()))
{
  obj.setUserID(User.getUserId());
}

//判別內外部人員
if("in".equals(User.getInORout())){
	obj.setIsInOrOutPerson("I");	
}else{
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
function checkField(){
	var alertStr = "";
	alertStr += checkDate(form1.q_occurDateS, "發生未預期反應日期-起");
	alertStr += checkDate(form1.q_occurDateE, "發生未預期反應日期-迄");
	alertStr += checkDateSE(form1.q_occurDateS, form1.q_occurDateE, "發生未預期反應日期");
	
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
	if("O" == "<%=obj.getIsInOrOutPerson()%>" && "2" == form1.caseType.value){	//登錄
		setDisplayItem("toCreate","H");
	}
	setDisplayItem("spanInsert,spanClear,spanConfirm,spanUpdate,spanQueryAll,spanDelete,spanListPrint,spanListHidden","H");
	listContainerRowClick(0);
	window.parent.frames["menu"].location.href="../../home/dTreeMenu.jsp";
}

function toCreate()
{
	form1.state.value = "queryOne";
	form1.doType.value = "insert";
	form1.action = "drgex0102f.jsp";
	form1.submit();
}

function queryOne(id)
{
    if("3"==form1.caseType.value){
        form1.id.value = id;
    	form1.state.value = "queryOne";
    	form1.action = "drgex0107f.jsp";
    }else{
    	form1.id.value = id;
    	form1.doType.value = "update";
    	form1.state.value = "queryOne";
    	form1.action = "drgex0102f.jsp";
    }
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
	window.open("../../home/popImpExcel.jsp?formType=DRGEX0101","",prop);
}

</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:left">
	<input type="hidden" name="state" value="<%=obj.getState()%>">	
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<jsp:include page="../../home/toolbar.jsp" />
	<%if(!"3".equals(obj.getCaseType())){%>
	<span id="toCreate">
    	<input class="toolbar_default" type="button" followPK="false"  name="toCreate1" value="開 始 通 報"  onClick="toCreate();">&nbsp;(欲通報案件請按此鈕)
    </span>
    <br>
    <%}%>
	<span id="spanDoQueryAll">
    	<input class="toolbar_default" type="submit" followPK="false" name="doQueryAll" value="查   詢">&nbsp;(欲查詢案件請按此鈕)
    </span>
    <br>
    <%if(!"2".equals(obj.getCaseType())){ %>
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
	<jsp:include page="drgex0101q.jsp" />
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
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">許可證字號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">中文品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">英文品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">案件狀態</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">明細</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {true,false,false,false,false,false,false};
	    boolean displayArray[] = {false,true,true,true,true,true,true};
	    boolean linkArray[] = {false,true,false,false,false,false,false};
	    String[] alignArray  =  {"center","center","center","center","left","left","center"};
	    out.write(View.getQuerylistPlusDetailButton(primaryArray,displayArray,alignArray,objList,obj.getQueryAllFlag(),true,linkArray,null,"",false, false));
	    //out.write(obj.getQuerylist(primaryArray, displayArray,alignArray, objList, obj.getQueryAllFlag(), true, "", true,linkArray));
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
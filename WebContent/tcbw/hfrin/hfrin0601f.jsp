<!--
程式目的：食品非預期反應通報初評作業
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
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="HFRIN0601" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.hfrin.HFRIN0601F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
obj.setQueryAllFlag("true");
if("".equals(obj.getUserID())){		obj.setUserID(User.getUserId());	}

if("true".equals(obj.getQueryAllFlag())){	
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
	alertStr += checkDate(form1.q_notifierRepDateS, "通報日期-起");
	alertStr += checkDate(form1.q_notifierRepDateE, "通報日期-迄");
	alertStr += checkDateSE(form1.q_notifierRepDateS, form1.q_notifierRepDateE, "通報日期");
	if(alertStr.length > 0){
		alert(alertStr);
		return false;
	}
	form1.state.value = "queryAll";
	beforeSubmit();
	return true;
}

function updateData(id, hfrType){
	form1.id.value = id;
	form1.state.value = "queryOne";
	if(hfrType == "1"){
		form1.action = "hfrin0602f.jsp";
	}else{
		form1.action = "hfrin0605f.jsp";
	}
	beforeSubmit();
	form1.submit();
}

function queryOne(rowId, hfrType){
	updateData(rowId, hfrType);
}

function init() {
	setDisplayItem("spanInsert,spanClear,spanConfirm,spanUpdate,spanQueryAll,spanDelete,spanListPrint,spanListHidden","H");
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
    <span id="spanDoQueryAll">
    	<input class="toolbar_default" type="submit" followPK="false" name="doQueryAll" value="查   詢">&nbsp;
    </span>
    <span id="spanQueryClear">
		<input class="toolbar_default" type="button" followPK="false" name="queryClear" value="取　消" onClick="setAllClearQ();">&nbsp;
	</span>  
	<span id="spanListToExcel">
        <input class="toolbar_default" type="button" followPK="false" name="listToExcel" value="列表列印" onClick="listToExcelReport('listTHEAD','listTBODY')">&nbsp;
    </span>
</td></tr>

<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="hfrin0601q.jsp" />
	</div>
</td></tr>


<tr><td nowrap class="bgList">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">案件編號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">通報日期</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">食品字號</a></th>
		<th class="listTH"><a class="text_link_w" href="#">中文品名</a></th>
		<th class="listTH"><a class="text_link_w" href="#">案件狀態</a></th>
		<th class="listTH"><a class="text_link_w" href="#">作業人員</a></th>
		<th class="listTH"><a class="text_link_w" href="#">明細</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {true, false, false, false, false, false, false, true};
	    boolean displayArray[] = {false, true, true, true, true, true, true, false};
	//  boolean linkArray[] = {false, true, false, false, false, false, false, false};
	//  out.write(View.getQuerylistPlusDetailButton(primaryArray, displayArray, null, objList, obj.getQueryAllFlag(), true, linkArray, null, "", false, false));
		out.write(View.getQueryDiscolorList(primaryArray, displayArray, null, objList, obj.getQueryAllFlag(), true, "", true, "", "&hfrType=", "&userID=" + obj.getUserID(), 1, 7, new String[]{"", "hfrin0602f.jsp", "hfrin0605f.jsp"}));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
</body>
</html>

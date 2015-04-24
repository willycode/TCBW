<!--
程式目的：
程式代號：medex0301
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
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.medex.MEDEX0301F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%

objList = (java.util.ArrayList) obj.doQueryAll();

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
}

function queryData(id)
{
	form1.id.value = id;
	form1.state.value = "queryOne";
	form1.action = "medex0302f.jsp";
	beforeSubmit();
	form1.submit();
}

function queryOne(id){
	
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
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<jsp:include page="../../home/toolbar.jsp" />
	<span id="spanDoQueryAll">
    	<input class="toolbar_default" type="submit" followPK="false" name="doQueryAll" value="查   詢">&nbsp;
    </span> 
    <br>
	<span id="spanListToExcel">
        <input class="toolbar_default" type="button" followPK="false" name="listToExcel" value="列表列印" onClick="listToExcelReport('listTHEAD','listTBODY')">&nbsp;
    </span> 
</td>
</tr>
<tr>
<td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="queryTable" width="100%">
	<tr>
		<td class="td_form">案件編號：</td>
		<td class="td_form_white">
			<input class="field_Q" type="text" name="q_applNo" size="11" maxlength="11" value="<%=obj.getQ_applNo()%>">	
		</td>
	</tr>  
	<tr>
		<td class="td_form">通報日期：</td>
		<td class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_notifierRepDateS",obj.getQ_notifierRepDateS())%>~<%=View.getPopCalendar("field_Q","q_notifierRepDateE",obj.getQ_notifierRepDateE())%>		
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
<td nowrap class="bgList">
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
		<th class="listTH"><a class="text_link_w">明細</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
		boolean primaryArray[] = {true,false,false,false,false,false,false,false};
    	boolean displayArray[] = {false,true,true,true,true,true,true,true};
	    out.write(View.getQuerylist(primaryArray,displayArray,null,objList,obj.getQueryAllFlag(),true,null,null,"",false));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
</body>
</html>
<!--
程式目的：藥品療效不等通報初評作業
程式代號：drgin0303
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
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="DRGIN0304" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0304F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if("".equals(obj.getUserID())){
  obj.setUserID(User.getUserId());
}
if ("init".equals(obj.getState())) {
	obj.setQueryAllFlag("true");
}else if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}
if ("true".equals(obj.getQueryAllFlag())){
	objList = (java.util.ArrayList) obj.doQueryAll();
	obj.setState("queryAllSuccess");
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
	alertStr += checkDate(form1.q_occurDateS, "發現日期起");
	alertStr += checkDate(form1.q_occurDateE, "發現日期迄");
	alertStr += checkDateSE(form1.q_occurDateS, form1.q_occurDateE, "發現日期");

	
	if(alertStr.length > 0){
		alert(alertStr);
		return false;
	}
	form1.state.value = "queryAll";
	beforeSubmit();
	return true;
}

function init() 
{
	setDisplayItem("spanInsert,spanClear,spanConfirm,spanUpdate,spanQueryAll,spanDelete,spanListPrint,spanListHidden","H");
	listContainerRowClick(0);
	window.parent.frames["menu"].location.href="../../home/dTreeMenu.jsp";
}

function queryOne(id)
{
	form1.id.value = id;
	form1.state.value = "queryOne";
	form1.action = "drgin0302f.jsp";
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
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="caseType" value="<%=obj.getCaseType()%>">
	<input type="hidden" name="formType" value="<%=obj.getFormType()%>">
	<jsp:include page="../../home/toolbar.jsp" />
	<span id="spanDoQueryAll">
    	<input class="toolbar_default" type="submit" followPK="false" name="doQuery" value="查   詢">&nbsp;
    </span>  
    <span id="spanQueryClear">
		<input class="toolbar_default" type="button" followPK="false" name="queryClear" value="取　消" onClick="setAllClearQ();">&nbsp;
	</span>
	<span id="spanListToExcel">
        <input class="toolbar_default" type="button" followPK="false" name="listToExcel" value="列表列印" onClick="listToExcelReport('listTHEAD','listTBODY')">&nbsp;
    </span>
</td>
</tr>

<tr>
<td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="drgin0304q.jsp" />
	</div>
</td>
</tr>

<!--List區============================================================-->
<tr><td nowrap class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<!--<jsp:include page="../../home/page.jsp" />-->
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
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">藥品成分資訊</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">通報事件後果</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">不良反應等級</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">療效不等風險評估結果</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">明細</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {true,false,false,false,false,false,false};
	    boolean displayArray[] = {false,true,true,true,true,true,true};
	    boolean linkArray[] = {false,true,false,false,false,false,false};
	    //out.write(View.getQuerylist(primaryArray,displayArray,null,objList,obj.getQueryAllFlag(),true,linkArray,null,"",false, false));
	    out.write(View.getQueryDiscolorList(primaryArray, displayArray,null, objList, obj.getQueryAllFlag(), true, "", true,"drgin0302f.jsp","&formType=0304","",1));		
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
</body>
</html>
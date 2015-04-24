<!--
程式目的：藥品療效不等諮議會作業
程式代號：drgin0305
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
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0305F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if("".equals(obj.getUserID()))
{
  obj.setUserID(User.getUserId());
}
if ("init".equals(obj.getState())) {
	obj.setQueryAllFlag("true");
}else if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}
if ("true".equals(obj.getQueryAllFlag())){
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
	form1.state.value = "queryAll";
	beforeSubmit();
	return true;
}

function init() 
{
	setDisplayItem("spanInsert,spanClear,spanConfirm,spanUpdate,spanQueryAll,spanDelete,spanListPrint,spanListHidden","H");
	listContainerRowClick(0);
}

function toCreateForm()
{
	form1.id.value = "";
	form1.action = "drgin0306f.jsp";
	form1.submit();
}

function queryData(id)
{
	form1.id.value = id;
	form1.state.value = "queryOne";
	form1.action = "drgin0306f.jsp";
	beforeSubmit();
	form1.submit();
}
function queryOne(id){
	
}
function windowClose()
{
	opener.doQueryOneData();
	window.close();//關閉自己(子視窗)
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
	<input type="hidden" name="q_permitKey" value="<%=obj.getQ_permitKey()%>">
	<input type="hidden" name="q_permitNo" value="<%=obj.getQ_permitNo()%>">
	<jsp:include page="../../home/toolbar.jsp" />
	<span id="spanDoCreate">
    	<input class="toolbar_default" type="button" followPK="false"  name="toCreate" value="新   增"  onClick="toCreateForm();">&nbsp;
    </span> 
    <span id="spanMaintain1">
		<input class="toolbar_default" type="button" followPK="false" name="spanMaintain1" value="關     閉" onClick="windowClose();">&nbsp;
	</span>
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
		<th class="listTH"><a class="text_link_w">NO.</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">許可證字號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">商品名稱</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">評估結果</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">評估內容</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">是否已評估完成</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">明細</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {true,false,false,false,false,false,false};
	    boolean displayArray[] = {false,true,true,true,true,true,true};
	    String[] alignArray  =  {"center","center","center","center","center","center"};
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
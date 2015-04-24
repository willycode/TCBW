<!--
程式目的：藥品療效不等品質調查
程式代號：drgin0310
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
<%@page import="java.net.*"%>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0311F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if("".equals(obj.getUserID())){
  obj.setUserID(User.getUserId());
}

obj.setQ_ingredient(URLDecoder.decode(request.getParameter("q_ingredient"),"UTF-8"));

if ("init".equals(obj.getState())) {
	obj.setQueryAllFlag("true");
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
	listContainerRowClick(0);
}

function windowClose()
{
	window.close();//關閉自己(子視窗)
}

function popReportForm()
{
	form1.action="drgin0311p.jsp";
	form1.target="_self";
	form1.submit();
}

</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:left">
	<table width="100%" border="0" CELLPADDING="0" CELLSPACING="2" align="center">
	<tr><td>
		<input type="hidden" name="id" value="<%=obj.getId()%>">
		<input type="hidden" name="state" value="<%=obj.getState()%>">
		<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
		<input type="hidden" name="q_ingredient" value="<%=obj.getQ_ingredient()%>">
		<input type="hidden" name="q_drg4005Id" value="<%=obj.getQ_drg4005Id()%>">
		<jsp:include page="../../home/toolbar.jsp" />
		<span id="spanQuery1">
			<input class="toolbar_default" type="button" followPK="false" name="btnQuery1" value="匯出EXCEL" onClick="popReportForm()">&nbsp;
		</span>
		<span id="spanMaintain3">
			<input class="toolbar_default" type="button" followPK="false" name="maintain1" value="關     閉" onClick="windowClose();">&nbsp;
		</span>
	</td></tr>
	</table>
</td></tr>
<!--List區============================================================-->
<tr><td nowrap class="bgList">
<div id="listContainer">
	<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
		<thead id="listTHEAD">
		<tr>
			<th class="listTH" rowspan="2"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">回覆日期</a></th>
			<th class="listTH" rowspan="2"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">醫療院所回覆</a></th>
			<th class="listTH" colspan="3">藥品成份_<%=obj.getQ_ingredient()%>_是否有療效不等狀況</th>
			<th class="listTH" rowspan="2"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">轉換前廠牌</a></th>
			<th class="listTH" rowspan="2"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">轉換後廠牌</a></th>
			<th class="listTH" rowspan="2"><a class="text_link_w" onclick="return sortTable('listTBODY',8,false);" href="#">補充說明</a></th>
			
		</tr>
		<tr>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">是</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">否(近年來有更換廠牌，但無療效不等狀況)</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">否(近年來未更換廠牌)</a></th>
		</tr>
		</thead>
		<tbody id="listTBODY">
		<%
			boolean primaryArray[] = {false,false,false,false,false,false,false,false,false};
			boolean displayArray[] = {false,true,true,true,true,true,true,true,true};
			out.write(View.getQuerylist(primaryArray,displayArray,null,objList,obj.getQueryAllFlag(),false,null,null,"",false));
		%>
		</tbody>
	</table>
</div>
</td></tr>
</table>
</form>
</body>
</html>
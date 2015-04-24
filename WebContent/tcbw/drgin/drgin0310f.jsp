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
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0310F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if("".equals(obj.getUserID())){
  obj.setUserID(User.getUserId());
}
if ("init".equals(obj.getState())) {
	obj.setQueryAllFlag("true");
}else if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())) {
	obj.doUpdate();
	obj.setState("updateSuccess");
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}
if ("true".equals(obj.getQueryAllFlag())){
	obj = (com.kangdainfo.tcbw.view.drgin.DRGIN0310F)obj.queryOne();
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

	if("Y" == form1.isClose.value){
		setFormItem("maintain1,maintain2","R");
	}else{
		setAllOpen();
	}
}

function doUpdateCase(type)
{
	var alertStr = "";
	if(alertStr.length > 0){
		alert(alertStr);
	}else{
		form1.state.value = "update";
		form1.actionType.value = type;
		setBeforePageUnload(false);
		beforeSubmit();
		form1.submit();
	}
}

function windowClose()
{
	opener.doQueryOneData();
	window.close();//關閉自己(子視窗)
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
	<table width="100%" border="0" CELLPADDING="0" CELLSPACING="2" align="center">
	<tr><td>
		<input type="hidden" name="id" value="<%=obj.getId()%>">
		<input type="hidden" name="state" value="<%=obj.getState()%>">
		<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
		<input type="hidden" name="actionType" value="<%=obj.getActionType()%>">
		<input type="hidden" name="isClose" value="<%=obj.getIsClose()%>">

		<jsp:include page="../../home/toolbar.jsp" />
		<span id="spanMaintain1">
	    	<input class="toolbar_default" type="button" followPK="false"  name="maintain1" value="存     檔" onClick="doUpdateCase(1);">&nbsp;
	    </span>
		<span id="spanMaintain2">
			<input class="toolbar_default" type="button" followPK="false" name="maintain2" value="評 估 完 成" onClick="doUpdateCase(2);">&nbsp;
		</span>
		<span id="spanMaintain3">
			<input class="toolbar_default" type="button" followPK="false" name="maintain3" value="關     閉" onClick="windowClose();">&nbsp;
		</span>
	</td></tr>
	</table>
</td></tr>

<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" align="center">
		<tr>
			<td nowrap class="td_form">評估意見</td>
			<td nowrap class="td_form_white">
				<textarea class="field"  name="assessDesc" cols="100" rows="5" ><%=obj.getAssessDesc()%></textarea>
			</td>
		</tr>
	</table>
	</div>
</td></tr>
<!--List區============================================================-->
<tr><td nowrap class="bgList">
<div id="listContainer">
	<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
		<thead id="listTHEAD">
		<tr>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">NO</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">案件編號</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">通報日期</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">通報事件後果</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">不良反應等級</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">療效不等評估結果</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">是否評估完成</a></th>
		</tr>
		</thead>
		<tbody id="listTBODY">
		<%
			boolean primaryArray[] = {true,false,false,false,false,false,false};
			boolean displayArray[] = {false,true,true,true,true,true,true};
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
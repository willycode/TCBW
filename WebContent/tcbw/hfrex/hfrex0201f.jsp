<!--
程式目的：食品非預期反應(外部)-待上傳作業
程式代號：
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="HFREX0201" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.hfrex.HFREX0201F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>

<%
if(User.getCommonDepartment() == null){
	out.write("無法辨別登入者屬性 !");
	return;
}

obj.setQ_notifierType(User.getCommonDepartment().getShortCode());
obj.setQ_notifierDeptID(User.getUserJob());
obj.setQ_userJobModDate(User.getUserJobModDate());

obj.setQueryAllFlag("true");
obj.setUserID(User.getUserId());

String isRefresh = "N";
if ("doSend".equals(obj.getState())) {
	obj.setHttpRequest(pageContext.getRequest());
	try{
		obj.doSend();
		obj.setErrorMsg("送出成功");
		isRefresh = "Y";
	}catch(Exception e){
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

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
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/cbToggle.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript">
function checkField(){
	var alertStr = "";
	if (cb.AnyChecked()==false) alertStr += "請勾選案件！\n";
	if(alertStr.length!=0){ 
		alert(alertStr); return false; 
	}
	form1.state.value = "doSend";
	beforeSubmit();
	return true;
}

function init() {
	setDisplayItem("spanInsert,spanClear,spanConfirm,spanUpdate,spanQueryAll,spanDelete,spanListPrint,spanListHidden","H");
	listContainerRowClick(0);
	setAllOpen();
	
	if("<%=isRefresh%>" == "Y"){
		window.parent.frames['menu'].location.href = "../../home/dTreeMenu.jsp";
	}
}
</script>
</head>

<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">

<!--Toolbar區============================================================-->
<tr><td class="bgToolbar" style="text-align:center">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<jsp:include page="../../home/toolbar.jsp" />
	<span id="spanDoSend">
    	<input class="toolbar_default" type="submit" followPK="false" name="doSend" value="送   出">&nbsp;
    </span>
</td></tr>

<tr><td class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>

<!--List區============================================================-->
<tr><td class="bgList">
<div id="listContainer">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><input type="checkbox" name="cbAll"></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">案件編號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">發生非預期反應日期</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">食品字號</a></th>
		<th class="listTH"><a class="text_link_w" href="#">中文品名</a></th>
		<th class="listTH"><a class="text_link_w" href="#">案件狀態</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true,false,false,false,false,false};
	boolean displayArray[] = {false,true,true,true,true,true};
	out.write(View.getCheckboxQuerylist(primaryArray, displayArray, null, objList, obj.getQueryAllFlag(), "fds", null, null));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
<script type="text/javascript">	
var cb = new cbToggle('cb',document.form1,form1.cbAll,'fds');
cb.config.cssTopLevel = true;
localButtonFireListener.whatButtonFireEvent = function(buttonName){
	
}
</script>
</body>
</html>

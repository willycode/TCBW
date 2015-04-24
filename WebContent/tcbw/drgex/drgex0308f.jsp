<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgex.DRGEX0308F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
obj.setQ_userId(User.getUserId());
if ("init".equals(obj.getState())) {
	obj.setQueryAllFlag("true");
}else if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())) {
	obj.setHttpRequest(pageContext.getRequest());
	obj.doUpdate();
	obj.setQueryAllFlag("true");
	obj.setErrorMsg("調查回覆完成");
}

if ("true".equals(obj.getQueryAllFlag())){
	obj = (com.kangdainfo.tcbw.view.drgex.DRGEX0308F) obj.doQueryOne();
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
<script type="text/javascript" src="drgex0308Layout.js"></script>
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
function init() {
	setDisplayItem('spanInsert,spanDuplicate,spanUpdate,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden,spanQueryAll','H');
	if(null != form1.id.value && "" != form1.id.value){
		whatButtonFireEvent("update");
	}else{
		alert("查無案件資料!!");
	}
}
function beforeInit() {
	<%=obj.getDrg49JSBuilder()%>
}

function doUpdateCase()
{
	var alertStr = "";
	alertStr += validateDrg49Table();
	if(alertStr.length > 0){
		alert(alertStr);
	}else{
		form1.state.value="update";
		setBeforePageUnload(false);
		beforeSubmit();
		form1.submit();
	}
}

</script>
<body onLoad="beforeInit();whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:left">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<jsp:include page="../../home/toolbar.jsp" />
	<span id="spanMaintain6">
	    <input class="toolbar_default" type="button" followPK="false"  name="doUpdate" value="回 覆 完 成" onClick="doUpdateCase();">&nbsp;
	</span>
</td></tr>
<!--List區============================================================-->
<tr><td class="bg">
	<table width="100%" cellspacing="0" cellpadding="0" id="drg49Table"></table>
</td></tr>
</table>
</form>
</body>
</html>
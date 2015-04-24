<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.util.Con1005DbSearch">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("init".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}
if ("true".equals(obj.getQueryAllFlag())){
	objList = (java.util.ArrayList) obj.queryAll();
}

%>
<html>
<head>
<title>案件原始版本</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript">
function checkField(){	
	return true;
}

function queryOne(compegno, name){
	opener.setObjectValue("applicationId"+form1.drgId.value, compegno);
	opener.setObjectValue("applicationName"+form1.drgId.value, name);
	window.close();
}

function init() {
	setDisplayItem('spanInsert,spanUpdate,spanDelete,spanListPrint,spanListHidden,spanConfirm,spanQueryAll,spanClear','H');
}
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()">
<table width="100%" cellspacing="0" cellpadding="0">

<!--Query區===========================================================-->
<tr><td class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" height="100%">
	<tr>
		<td class="td_form">廠商名稱：</td>
		<td class="td_form_white">
			<input class="field_Q" type="text" name="q_facName" size="13" maxlength="20" value="<%=obj.getQ_facName()%>">
		</td>
	</tr>
	</table>
	</div>
</td></tr>
<!--toolbar區===========================================================-->
<tr><td class="bgToolbar" style="text-align:left">
<jsp:include page="../home/toolbar.jsp" />
<input type="hidden" name="state" value="<%=obj.getState()%>">
	<span id="spanDoQueryAll">
    	<input class="toolbar_default" type="submit" followPK="false" name="doQuery" value="查   詢" >&nbsp;
    </span>
    <span id="spanQueryClear">
		<input class="toolbar_default" type="button" followPK="false" name="queryClear" value="取　消" onClick="window.close()">&nbsp;
	</span>
</td></tr>
<!--List區============================================================-->
<tr><td class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../home/page.jsp" />
</td></tr>
<tr><td nowrap class="bg" >
<input type="hidden" name="drgId" value="<%=obj.getDrgId()%>">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">統一編號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">廠商名稱</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
		<%
		boolean primaryArray[] = {false, true, true};
		boolean displayArray[] = {false, true, true};
		out.write(View.getQuerylist(primaryArray, displayArray,null, objList, obj.getQueryAllFlag(),true,null,null));
		%>
	</tbody>
</table>
</div>
</td></tr>
</table>	
</form>
</body>
</html>

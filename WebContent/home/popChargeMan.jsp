<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.util.ChargeManSearch">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%

java.util.ArrayList<String[]> objList = (java.util.ArrayList) obj.queryAll();

%>
<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../js/default.css" type="text/css"/>
<script type="text/javascript" src="../js/json.js"></script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery.maskedinput.js"></script>
<script type="text/javascript" src="../js/tablesoft.js"></script>
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript" src="../js/cbToggle.js"></script>
<script type="text/javascript" src="../js/toolbar.js"></script>
<script type="text/javascript">
function checkField(type){
	var alertStr="";
	if(form1.state.value);
	if(form1.state.value=="queryAll"){

	}else if(form1.state.value=="insert"||form1.state.value=="insertError"||form1.state.value=="update"||form1.state.value=="updateError"){
		
	}
	if(alertStr.length!=0){ 
		alert(alertStr);
		return false; 
	}
	beforeSubmit();
	return true;
}


function queryOne(id) {

}

function init(){
	setDisplayItem('queryAll,spanInsert,spanDuplicate,spanUpdate,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden','H');
}

function sure(){	
	var userName;
	var fds = document.getElementsByName("fds");
	for(var i=0;i<fds.length;i++){		
		var item = fds[i];
		if(item.checked == true){
			userName = item.value.split(",");
		}
	}
	opener.updChargeMan(userName);
	window.close();
}
function cancel(){
	window.close();
}

</script>
</head>
<body onload="init();">
<form id="form1" name="form1" method="post" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr><td class="bgToolbar" style="text-align:center">
	<input type="hidden" class="field_Q" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" class="field_Q" name="q_code" value="<%=obj.getQ_code()%>">
	<input type="hidden" class="field_Q" name="q_formCode" value="<%=obj.getQ_formCode()%>">
	<input class="toolbar_default" type="button" followPK="false" name="confirm" value="確　定" onClick="sure();">&nbsp;
	<input class="toolbar_default" type="button" followPK="false" name="cannel" value="取 　消" onClick="cancel();">&nbsp;
</td></tr>
<tr><td>
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="page.jsp" />
</td></tr>
<tr><td class="bg">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0" id="tbl">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"></th>
		<th class="listTH"><a class="text_link_w">帳號</a></th>		
		<th class="listTH"><a class="text_link_w">姓名</a></th>	
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {false, true, false};
	boolean displayArray[] = {false, true, true};
	out.write(View.getRadioboxQuerylist(primaryArray,displayArray,null,objList,obj.getQueryAllFlag(),"fds",null,null,null,-1,false,false));
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
</script>
</body>
</html>
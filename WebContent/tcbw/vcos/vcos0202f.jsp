<!--
程式目的：
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
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.vcos.VCOS0202F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>

<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<% 

String id = Common.get(request.getParameter("id"));

objList = (java.util.ArrayList) obj.queryAll();

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
function init() 
{
	setDisplayItem("spanQueryAll,spanInsert,spanClear,spanConfirm,spanUpdate,spanDelete,spanListPrint,spanListHidden","H");
	listContainerRowClick(0);
}

function checkField()
{	
	var fds = document.getElementsByName('fds');

    var str= "";
	
	for(var i = 0 ; i < fds.length ; i ++)
	{
	    var fdsCheckBox = fds[i];
	    
	    if(fdsCheckBox.checked)
		{	
			if(str.length >0 ) str+=",";
	    	str+=fdsCheckBox.value;
		}
	}

	var alertStr = "";

	alertStr+=checkButton(form1.fds,"群組名稱");
		
	if(alertStr.length > 0)
	{
		alert(alertStr);
		return false;
	}
	
	if (isObj(opener.document.all.item("con1012Id")))
  	  opener.document.all.item("con1012Id").value= str;

	opener.checkField();
	
	window.close();
	
	return true;
}

</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" autocomplete="off" >
<table width="100%" cellspacing="0" cellpadding="0">

<!--Toolbar============================================================-->
<tr>
<td nowrap class="bgToolbar"  style="text-align: center">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="doType">
	<jsp:include page="../../home/toolbar.jsp" />

    <input class="toolbar_default" followPK="false" type="button" name="doConfirm" value="確　　認" onClick="whatButtonFireEvent(this.name)">&nbsp;
    <input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="window.close()">
</td>
</tr>

<tr>
<td nowrap class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>

<!--List區============================================================-->
<tr>
<td nowrap class="bgList">
<div id="listContainer">
<table class="table_form" width="99%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
	    <th class="listTH"><input type="checkbox" class="field_Q" name="cbAll"></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">系統別</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">群組代碼</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">群組名稱</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	   boolean primaryArray[] = {true, false, false, false};
	   boolean displayArray[] = {false, true, true, true};
	   String[] alignArray = {"center", "center", "center", "center"};
	   out.write(View.getCheckboxQuerylist(primaryArray,displayArray,null,objList,obj.getQueryAllFlag(),"fds",null,null,"field_Q", -1, false, false));
	%>
	</tbody>
</table>
</div>
</td></tr>

</table>




</form>
</body>
<script type="text/javascript">
var cb = new cbToggle('cb',document.form1,form1.cbAll,'fds');
cb.config.cssTopLevel = true;

localButtonFireListener.whatButtonFireEvent = function(buttonName)
{
	switch (buttonName)
	{
	case "doConfirm":
		form1.state.value = buttonName;
		checkField();
		break;
	}
}

</script>
</html>

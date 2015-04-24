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
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="VCOS0201" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.vcos.VCOS0201F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%

obj.setQueryAllFlag("true");

if("".equals(obj.getUserID()))
{
  obj.setUserID(User.getUserId());
}

if("send".equals(obj.getState()))
{
	try
	{
		obj.doSend();
		obj.setErrorMsg("送出完成");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

if("init".equals(obj.getState()))
{	
	obj.setQ_dataRevDateS(Datetime.getYYYMMDD());
	obj.setQ_dataRevDateE(Datetime.getYYYMMDD());
	objList = (java.util.ArrayList) obj.doQueryAll();
	obj.setStateQueryOneSuccess();
}
else
{
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
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript" src="../../js/cbToggle.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">
function checkField()
{
	var alertStr = "";
	if(alertStr.length > 0)
	{
		alert(alertStr);
		return false;
	}
	beforeSubmit();	
	form1.submit();
	return true;
}

function init() 
{
	if("init"==form1.state.value)
	{
		form1.q_dataRevDateS.value = '<%=Datetime.getYYYMMDD()%>';
		form1.q_dataRevDateS.value = '<%=Datetime.getYYYMMDD()%>';
	}
	setDisplayItem("spanInsert,spanClear,spanConfirm,spanUpdate,spanQueryAll,spanDelete,spanListPrint,spanListHidden","H");
	listContainerRowClick(0);
	setAllOpen();
	window.parent.frames["menu"].location.href="../../home/dTreeMenu.jsp";
}

function sendMail()
{

	var alertStr = "";

	alertStr+=checkButton(form1.fds,"案件編號");
		
	if(alertStr.length > 0)
	{
		alert(alertStr);
		return false;
	}
	

	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=800,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "tcbw/vcos/vcos0202f.jsp","",prop);
}

</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:left">
    <input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="con1012Id" value="<%=obj.getCon1012Id()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="doType" value="<%=obj.getDoType()%>">	
	<jsp:include page="../../home/toolbar.jsp" />
	<span id="spanDoQueryAll">
    	<input class="toolbar_default" type="submit" followPK="false" name="doQueryAll" value="查   詢">&nbsp;
    </span>
    <span id="spanQueryClear">
		<input class="toolbar_default" type="button" followPK="false" name="queryClear" value="取　消" onClick="setAllClearQ();">&nbsp;
	</span>
    <span id="spanSend">
    	<input class="toolbar_default" type="button" followPK="false" name="send" value="發   送" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
</td>
</tr>

<tr>
<td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<% request.setAttribute("qBean", obj); %>
	<jsp:include page="vcos0201q.jsp" />
	</div>
</td>
</tr>


<!--List區============================================================-->
<tr>
<td nowrap class="bgList">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
	    <th class="listTH"><input type="checkbox" name="cbAll"></th>		
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">案件編號</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">品名</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">資料收集日期</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">化粧品項目</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">狀況</a></th>
        <th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">發佈日期</a></th>
        <th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">訊息主題</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {true,false,false,false,false,false,false,false};
	    boolean displayArray[] = {false,true,true,true,true,true,true,true};
	    String[] alignArray  =  {"center","center","left","center","center","center","center","center"};
	    out.write(View.getCheckboxQuerylist(primaryArray,displayArray,alignArray,objList,obj.getQueryAllFlag(),"fds",null,null,"field_Q", -1, false, false));
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

localButtonFireListener.whatButtonFireEvent = function(buttonName){
	switch (buttonName)
	{
	case "queryAll":
		break;	
    case "send":
        if(confirm("確定發送?"))
        {
      	    form1.state.value = buttonName;
      	    checkField();
        }

	    break;
	}
}
</script>
</html>
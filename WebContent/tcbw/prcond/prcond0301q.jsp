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
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="PRCOND0301Q" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.prcond.PROCOND0301Q">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%

if ("queryAll".equals(obj.getState())) 
{
	if ("false".equals(obj.getQueryAllFlag()))
	{
		obj.setQueryAllFlag("true"); 
	}
}
else if ("queryOne".equals(obj.getState()))
{
	obj = (com.kangdainfo.tcbw.view.prcond.PROCOND0301Q)obj.queryOne();
}


if ("true".equals(obj.getQueryAllFlag()))
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
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">

function checkField()
{

	var alertStr = "";
	form1.state.value="queryAll";

	alertStr += checkEmpty(form1.q_projName,"專案名稱");
	
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	form1.submit();
	return true;
}


function init() 
{
	setDisplayItem("spanQueryAll,spanInsert,spanClear,spanConfirm,spanUpdate,spanDelete,spanListPrint,spanListHidden","H");
	document.all.item("MED").style.display="none";
	changeKind();
	
}

function queryOne(id)
{

}


var popWin = null;
function popProjName(type)
{
	var params = 'width=1000,height=600,resizable=1,menubar=no,scrollbars=yes';

	if (popWin!=null) popWin.close();

  
	popWin = window.open("prcond0302q.jsp?type="+type.value,'popChefWin', params);
}

function changeKind()
{
	var kind=form1.q_projType.value;

	  if(kind=="drg")
	  {
		  $(form1.q_datakindMED).attr("checked", false); //清空checkbox選擇的值
		  document.all.item("MED").style.display="none";
		  document.all.item("DRG").style.display="";
	  }
	  else if(kind=="med")
	  {
		  $(form1.q_datakindDRG).attr("checked", false); //清空checkbox選擇的值
		  document.all.item("DRG").style.display="none";
		  document.all.item("MED").style.display="";

	  }	  	 
}
function clearProj()
{
	form1.q_projid.value="";
	form1.q_projName.value="";
	
}
var popWinName;
function show0202()
{
	var prop="";
	var windowTop=120; 
	var windowLeft=120;
	var q_projid = form1.q_projid.value;
	if("" == q_projid) {
		alert("請先選擇專案名稱");
	} else {
		prop=prop+"width=1200px,height=600,";
		prop=prop+"top="+windowTop+",";
		prop=prop+"left="+windowLeft+",";
		prop=prop+"scrollbars=yes";
		if (popWinName!=null) popWinName.close();
		popWinName = window.open("../prcond/prcond0202f.jsp?q_projid="+q_projid+"&isPop=Y"+"&statePop=PRCOND0301Q",'popWinE',prop);	
	}
	

}

</script>
</head>
<body  onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">

<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%">
	<tr>
		<td nowrap width="25%" class="td_form">專案類別：</td>
		<td class="td_form_white">
			<select class="field_Q" name="q_projType" onChange="clearProj();changeKind();">
			 <%=View.getTextOption("drg;藥品;med;醫療器材",obj.getQ_projType(),0)%>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">資料年月：</td>
		<td class="td_form_white">
			<input class="field_Q" name="q_dateS" type="text" size="5" maxlength="5" value="<%=obj.getQ_dateS()%>" />~
			<input class="field_Q" name="q_dateE" type="text" size="5" maxlength="5" value="<%=obj.getQ_dateE()%>"/>
		</td>
	</tr>
	<tr id="DRG">
		<td nowrap class="td_form" >資料類別</td>
		<td nowrap class="td_form_white"  >
			<%=View.getCheckBoxTextOption("field_Q", "q_datakindDRG","drg01;藥品不良品;drg02;藥品療效不等;drg03;藥品警訊;drg04;藥品回收;",obj.getQ_datakindDRG())%>
		</td> 
	</tr>
	<tr id="MED">
		<td nowrap class="td_form" >資料類別</td>
		<td nowrap class="td_form_white"  >
			<%=View.getCheckBoxTextOption("field_Q", "q_datakindMED","med01;醫療器材不良品;med02;醫療器材不良反應;",obj.getQ_datakindMED())%>
		</td> 
	</tr>
	<tr>
		<td nowrap class="td_form">專案名稱：</td>
		<td nowrap class="td_form_white">
		    <input class="field_RO" type="text" name="q_projid" value="<%=obj.getQ_projid()%>" />
		    <input class="field_RO" type="text" size="40" name="q_projName" value="<%=obj.getQ_projName()%>" />
		    <input type="button" name="btn_projName" class="field_Q" type="button" value="專案名稱"  onclick="popProjName(form1.q_projType);">
		</td>
	</tr>
	</table>
	</div>	
</td>
</tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:center">
    <input type="hidden" name="id" value="">	
    <input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="userName" value="<%=User.getUserName()%>">
	<jsp:include page="../../home/toolbar.jsp" />
	<span id="spanDoQueryAll">
    	<input class="toolbar_default" type="submit" followPK="false" name="doQueryAll" value="查   詢">&nbsp;
    </span>
    <span id="spanQueryClear">
		<input class="toolbar_default" type="button" followPK="false" name="queryClear" value="取　消" onClick="setAllClearQ();">&nbsp;
	</span>
	<input id="query" type="button" name="query" value="產製報表" class="toolbar_default" />
	<input id="query" type="button" name="pop0202" value="檢視專案文件" class="toolbar_default" onClick="show0202();" />
	
</td>
</tr>

<tr>
<td class="bgList">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
		<tr>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',0,false);" href="#">序號</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">年度</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">月份</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">資料日期</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">資料類別</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">案件編號</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">許可證字號</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">中文品名</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',8,false);" href="#">許可證持有商</a></th>
		</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	   boolean primaryArray[] = {true,false,false,false,false,false,false,false};
	   boolean displayArray[] = {true,true,true,true,true,true,true,true};
	   String[] alignArray = {"center","center","center","center","center","center","center","center"};
	   out.write(View.getQuerylist(primaryArray,displayArray,alignArray,objList,obj.getQueryAllFlag()));
	%>
	</tbody>
</table>
</div>
</td>
</tr>
</table>
</form>
</body>

<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName)
{
	switch (buttonName)
	{
	   case "queryAll":
		    break;
	}
}

$(document).ready(function()
{
	$("#query").click(function()
	{
		var alertStr = "";

		alertStr += checkEmpty(form1.q_projName,"專案名稱");
	
		if(alertStr.length!=0){ alert(alertStr); return false; }
		
		form1.action = "prcond0301p.jsp" ;
		form1.target = "_blank";
		beforeSubmit();
		form1.submit();
		form1.action = "prcond0301q.jsp" ;
		form1.target = "_self";
		
	});
});


</script>
</html>















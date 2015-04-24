<!--
程式目的：使用者維護作業(外部)
程式代號：conex0202f.jsp
程式日期：102.10.17
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="CONEX0201" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.conex.CONEX0201F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if("queryAll".equals(obj.getState())){
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}

if ("true".equals(obj.getQueryAllFlag())){
	objList = (java.util.ArrayList)obj.doQueryAll();	
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
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript">
var insertDefault;	
function checkField(){
	var alertStr = "";
	if(form1.state.value == "queryAll"){
		alertStr += checkQuery();
	}
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	return true;
}

function queryOne(id)
{
	form1.id.value = id;
	form1.state.value = "queryOne";
	form1.action = "conex0203f.jsp";
	var url = '../../ajax/jsonCommonLogDb.jsp';
	var q = "&code=CONIN0202F";
        q +="&methodName=open";
        q +="&db=CommonUser";
        q +="&hql=select id,'',userId,userName from CommonUser where id="+form1.id.value;

    var xUserUpdate = getRemoteData(url,q);
	beforeSubmit();
	form1.submit();
}


function init(){
	setDisplayItem("spanInsert,spanClear,spanConfirm,spanUpdate,spanDelete,spanListPrint,spanListHidden", "H");
	if(form1.q_department.value == "01")
		$("#t1").hide();
}

function checkURL(surl)
{
	var alertStr = "";
	alertStr += checkEmpty(form1.id,"主檔編號");
	if(alertStr.length != 0){
		alert("請先查詢並選取其中一筆資料");
		return false;
	} else {
		form1.state.value="queryAll";
	}
	beforeSubmit();
	form1.submit();
}

function changeForm(a){
	if(a.value == "01"){
		form1.q_userJobName.value="";
		$("#t1").hide();
	}
	else{
		$("#t1").show();
	}
}

function toCreate()
{
	form1.state.value = "toCreate";
	form1.action = "conex0203f.jsp";
	beforeSubmit();
	form1.submit();
}
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<!--Query區============================================================-->
<div id="queryContainer" style="width:600px;height:200px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<% request.setAttribute("qBean",obj); %>
	<jsp:include page="conex0202q.jsp" />
</div>

<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr><td class="bgToolbar" style="text-align:left">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<jsp:include page="../../home/toolbar.jsp" />
	<span id="toCreate">
    	<input class="toolbar_default" type="button" followPK="false"  name="toCreate1" value="新　增"  onClick="toCreate();">&nbsp;
    </span>
</td></tr>

<tr><td class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>
</table>
<TABLE CELLPADDING=0 CELLSPACING=0>
	<TR>
		<td ID="t1" CLASS="tab_border1">註冊資料查詢</td>
		<%if(Common.getInt(User.getRoleId())>1 || !"".equals(Common.get(User.getSubSystem()))){%>
		<td ID="t2" CLASS="tab_border2"><a href="#" onClick="return checkURL('conex0203f.jsp');">註冊維護資料</a></td>
		<%}%>
	</TR>
</TABLE>
<table width="100%" cellspacing="0" cellpadding="0">
<!--List區============================================================-->
<tr><td class="bgList">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">單位屬性</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">帳號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">中文姓名</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">連絡電話</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">註冊日期</a></th>
		
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true, false, false, false, false, false};
	boolean displayArray[] = {false, true, true, true, true, true};
	out.write(View.getQuerylist(primaryArray, displayArray, objList, obj.getQueryAllFlag()));
	%>
	</tbody>
</table>
</div>
</td></tr>

</table>
</form>
</body>
</html>

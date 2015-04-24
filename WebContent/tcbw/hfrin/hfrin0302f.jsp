<!--
程式目的：委員會期資料維護作業
程式代號：hfrin0301f.jsp
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="HFRIN0301" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.hfrin.HFRIN0302F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	obj = (com.kangdainfo.tcbw.view.hfrin.HFRIN0302F)obj.queryOne();	
}else if ("insert".equals(obj.getState()) || "insertError".equals(obj.getState())) {
	obj.insert();
	if ("insertSuccess".equals(obj.getState())) {
		obj.setQueryAllFlag("true");
		obj.setQ_id(obj.getId());
	}
}else if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())) {
	obj.update();
	if ("updateSuccess".equals(obj.getState())) {
		obj.setQueryAllFlag("true");
	}
}else if ("delete".equals(obj.getState()) || "deleteError".equals(obj.getState())) {
	obj.delete();
	if("deleteError".equals(obj.getState())){
		obj = (com.kangdainfo.tcbw.view.hfrin.HFRIN0302F)obj.queryOne();
	}
}
if ("true".equals(obj.getQueryAllFlag())){
	objList = (java.util.ArrayList) obj.queryAll();
}
%>
<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">
var insertDefault;	//二維陣列, 新增時, 設定預設值
function checkField(){
	var alertStr = "";
	if(form1.state.value == "queryAll"){

	}else if(form1.state.value=="insert"||form1.state.value=="insertError"||form1.state.value=="update"||form1.state.value=="updateError"){
		alertStr += checkEmpty(form1.hfr02id, "編號");
	}
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	return true;
}

function queryOne(id){
	form1.detailid.value = id;
	form1.state.value = "queryOne";
	beforeSubmit();
	form1.submit();
}

function init() {
	setDisplayItem("spanQueryAll,spanUpdate",'H');
}

function checkURL(surl)
{
	var alertStr = "";
	if(form1.id.value==""){
		alert("請先執行查詢或新增!");
	}
	else if (form1.state.value=="insert" || form1.state.value=="insertError" || form1.state.value=="update" || form1.state.value=="updateError") 
	{
		alert("新增或修改狀態無法更換頁標籤，請先點選取消!");
	}
	else
	{
		if(surl=="hfrin0301.jsp") 
		{
			form1.state.value="queryOne";
		}
		else 
		{
			form1.state.value="queryAll";
		}
	
		form1.action = surl;

		beforeSubmit();
		form1.submit();
	}
}

function popHFR0002(nameField, idField){
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=850,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	returnWindow=window.open(getVirtualPath() + "home/popHFR0002.jsp?nameField="+nameField+"&idField="+idField,"",prop);
}

</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<!--Query區============================================================-->

<table cellpadding="0" cellspacing="0" valign="top">
	<tr>
		<td nowrap id="t1" class="tab_border2"  width="100" height="25"><a href="#" onClick="return checkURL('hfrin0301f.jsp');">委員會期資料維護作業</a></td>
		<td nowrap id="t1" class="tab_border1" width="100">評估委員維護作業</td>
		<td nowrap id="t1" class="tab_border2" width="100"><a href="#" onClick="return checkURL('hfrin0303f.jsp');">評估案件維護作業</a></td>			
	</tr>
	<tr>
		<td nowrap class="tab_line1"></td>
		<td nowrap class="tab_line2"></td>	
	</tr>
</table>

<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer">
	<table class="table_form" width="99%" height="100%">
	<tr>
		<td nowrap class="td_form"><font color="red">*</font>編號：</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=obj.getPopHfr0002("field", "committeeNum", obj.getCommitteeNum(), obj.getId())%>
			<input type="hidden" name="hfr02id" value="<%=obj.getHfr02id()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">姓名：</td>
		<td nowrap class="td_form_white"  colspan="3">
			<input class="field_RO" type="text" name="name" size="20" maxlength="10" value="<%=obj.getName()%>">
		</td>	
	</tr>	
	<tr>
		<td nowrap class="td_form">職稱：</td>
		<td nowrap class="td_form_white"  colspan="3">
			<input class="field_RO" type="text" name="postLev" size="20" maxlength="10" value="<%=obj.getPostLev()%>">
		</td>	
	</tr>	
	<tr>
		<td nowrap class="td_form">任職單位：</td>
		<td nowrap class="td_form_white"  colspan="3">
			<input class="field_RO" type="text" name="unionID" size="20" maxlength="10" value="<%=obj.getUnionID()%>">
		</td>	
	</tr>
	<tr>
		<td nowrap class="td_form">任期區間：</td>
		<td nowrap class="td_form_white"  colspan="3">
			<input class="field_RO" type="text" name="termSdate" size="4" maxlength="10" value="<%=obj.getTermSdate()%>">
			~ <input class="field_RO" type="text" name="termEdate" size="4" maxlength="10" value="<%=obj.getTermEdate()%>">
		</td>	
	</tr>		
	<tr>
		<td nowrap class="td_form">異動資訊：</td>
		<td nowrap class="td_form_white" colspan="3">
			[<input class="field_RO" type="text" name="editID" size="10" value="<%=obj.getEditID()%>">
			/<input class="field_RO" type="text" name="editDate" size="7" value="<%=obj.getEditDate()%>">] 
		</td>		
	</tr>	
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:center">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="detailid" value="<%=obj.getDetailid()%>">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getId()%>">
	<input type="hidden" name="q_id" value="<%=obj.getQ_id()%>">
	<input type="hidden" name="q_isQuery">
	<jsp:include page="../../home/toolbar.jsp" />
</td></tr>

<tr><td nowrap class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>

<!--List區============================================================-->
<tr><td nowrap class="bgList">
<div id="listContainer">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">姓名</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">職稱</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">任職單位</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">任期區間</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true, false, false, false, false};
	boolean displayArray[] = {false, true, true, true, true};
	String[] alignArray = {"center", "center", "center", "center", "center"};
	out.write(View.getQuerylist(primaryArray,displayArray,alignArray,objList,obj.getQueryAllFlag()));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
</form>
</body>
</html>
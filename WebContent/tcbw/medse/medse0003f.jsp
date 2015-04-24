<!--
程式目的：病人問題代碼設定作業
程式代號：medse0003f.jsp
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="MEDSE0003" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.medse.MEDSE0003F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	obj = (com.kangdainfo.tcbw.view.medse.MEDSE0003F)obj.queryOne();	
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
		obj = (com.kangdainfo.tcbw.view.medse.MEDSE0003F)obj.queryOne();
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
	var isRadioShow = "";
	if(form1.state.value == "queryAll"){
		//alertStr += checkQuery();
	}else if(form1.state.value=="insert"||form1.state.value=="insertError"||form1.state.value=="update"||form1.state.value=="updateError"){
		alertStr += checkEmpty(form1.code, "代碼");
		alertStr += checkEmpty(form1.name, "名稱");
		alertStr += checkEmpty(form1.term, "term");
		alertStr += checkEmpty(form1.definition, "definition");
	}
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	return true;
}

function queryOne(id)
{
	form1.id.value = id;
	form1.state.value = "queryOne";
	beforeSubmit();
	form1.submit();
}

function init() {

}

function checkURL(surl)
{
	var alertStr = "";
	if (form1.state.value=="insert" || form1.state.value=="insertError" || 
			form1.state.value=="update" || form1.state.value=="updateError") 
	{
		alert("新增或修改狀態無法更換頁標籤，請先點選取消!");
	}
	else
	{
		if(form1.code.value==null || form1.code.value == "")
		{
			alertStr += ("代碼不得為空");
		}
		
		if(alertStr.length!=0)
		{
			alert("請先執行查詢, 若已查到資料則請選取其中一筆資料");
			return false;
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
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<!--Query區============================================================-->
<div id="queryContainer" style="width:700px;height:250px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<% request.setAttribute("qBean",obj); %>
	<jsp:include page="medse0003q.jsp" />
</div>

<table cellpadding="0" cellspacing="0" valign="top">
	<tr>
		<td nowrap id="t1" class="tab_border1"  width="100" height="25">LEVEL1</td>
		<td nowrap id="t1" class="tab_border2" width="100" ><a href="#" onClick="return checkURL('medse0003_1f.jsp');">LEVEL2</a></td>		
	</tr>
	<tr>
		<td nowrap class="tab_line1"></td>
		<td nowrap class="tab_line2"></td>	
	</tr>
</table>

<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" height="100%">
	<tr>
		<td nowrap class="td_form"><font color="red">*</font>代碼：</td>
		<td nowrap class="td_form_white" width="50%">
			<input class="field_P" type="text" name="code" size="5" maxlength="5" value="<%=obj.getCode()%>">				
		</td>
		<td nowrap class="td_form" ><font color="red">*</font>名稱：</td>
		<td nowrap class="td_form_white" width="50%">
			<input class="field" type="text" name="name" size="30" maxlength="50" value="<%=obj.getName()%>">				
		</td>
	</tr>	
	<tr>
		<td nowrap class="td_form" ><font color="red">*</font>term：</td>
		<td nowrap class="td_form_white" colspan="4" width="100%">
			<input class="field" type="text" name="term" size="30" maxlength="150" value="<%=obj.getTerm()%>">				
	</tr>
	<tr>
		</td>
		<td nowrap class="td_form" ><font color="red">*</font>Definition：</td>
		<td nowrap class="td_form_white" colspan="4" width="100%">
		<textarea class="field" name="definition" cols="100" rows="10"><%=obj.getDefinition()%></textarea>
			<!--  <input class="field" type="text" name="definition" size="30" maxlength="1000" value="<%=obj.getDefinition()%>">	 -->			
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" ></font>FDA代碼：</td>
		<td nowrap class="td_form_white" >
			<input class="field" type="text" name="fdaCode" size="4" maxlength="4" value="<%=obj.getFdaCode()%>">				
		</td>
		<td nowrap class="td_form" ></font>FDA名稱：</td>
		<td nowrap class="td_form_white" >
			<input class="field" type="text" name="fdaName" size="30" maxlength="50" value="<%=obj.getFdaName()%>">				
		</td>
	</tr>	
	<tr>
		<td nowrap class="td_form">異動人員/日期：</td>
		<td nowrap class="td_form_white" colspan="3">
		  	[<input class="field_RO" type="text" name="editID" size="10" value="<%=obj.getEditID()%>">/
		  	<input class="field_RO" type="text" name="editDate" size="7" value="<%=obj.getEditDate()%>">]
		</td>
	</tr>
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:center">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getId()%>">
	<jsp:include page="../../home/toolbar.jsp" />
	
</td></tr>

<tr><td nowrap class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>

<!--List區============================================================-->
<tr><td nowrap class="bgList">
<div id="listContainer">
<table class="table_form" width="99%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">代碼</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">名稱</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">term</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">Definition</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">FDA代碼</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',6,false);" href="#">FDA名稱</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true, false, false, false, false, false, false};
	boolean displayArray[] = {false, true, true, true, true, true, true};
	String[] alignArray = {"center", "center", "left", "left", "left", "center", "center"};
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
<!--
程式目的：醫事機構資料歷程查詢作業
程式代號：conse0009_2f.jsp
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="CONSE0009" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.conse.CONSE0009_2F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	obj = (com.kangdainfo.tcbw.view.conse.CONSE0009_2F)obj.queryOne();	
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
		obj = (com.kangdainfo.tcbw.view.conse.CONSE0009_2F)obj.queryOne();
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
//二維陣列, 新增時, 設定預設值
var insertDefault = new Array(
		new Array("medAgencyName", '<%=obj.getMedAgencyName()%>')
	);
function checkField(){
	var alertStr = "";
	var isShow = "";
	if(form1.state.value == "queryAll"){
		alertStr += checkQuery();
	}else if(form1.state.value=="insert"||form1.state.value=="insertError"||form1.state.value=="update"||form1.state.value=="updateError"){
		alertStr += checkEmpty(form1.usernameid, "聯絡人帳號");
		isShow = $('input:checkbox:checked[name="formType"]').val();
		if(isShow == undefined)
			alertStr += "表單別至少選擇一個項目!\n";
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
	setDisplayItem("spanQueryAll,spanInsert,spanUpdate,spanDelete,spanClear,spanConfirm","H");
}

function checkURL(surl)
{
	var alertStr = "";
	if (form1.state.value=="insert" || form1.state.value=="insertError" || form1.state.value=="update" || form1.state.value=="updateError") 
	{
		alert("新增或修改狀態無法更換頁標籤，請先點選取消!");
	}
	else
	{
		if(surl=="conse0009f.jsp") 
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
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<!--Query區============================================================-->
<div id="queryContainer" style="width:800px;height:150px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<% request.setAttribute("qBean",obj); %>
	<jsp:include page="conse0009q.jsp" />
	</table>
</div>

<table cellpadding="0" cellspacing="0" valign="top">
	<tr>
		<td nowrap id="t1" class="tab_border2"  width="100" height="25"><a href="#" onClick="return checkURL('conse0009f.jsp');">醫事機構資料</a></td>
		<td nowrap id="t1" class="tab_border1" width="100">醫事機構資料歷程</td>		
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
	<table class="table_form" width="99%" height="100%">
	<tr>
		<td nowrap class="td_form" width="35%"><font color="red">*</font>分局別：</td>
		<td nowrap class="td_form_white" width="65%">
			<select class="field" name="bnhi">
				<%=View.getOptionCodeKind("MEDDIV", obj.getBnhi())%>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="35%"><font color="red">*</font>醫事機構代碼：</td>
		<td nowrap class="td_form_white" width="65%">
			<input class="field" type="text" name="medAgencyCode" size="12" maxlength="12" value="<%=obj.getMedAgencyCode()%>">				
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="35%"><font color="red">*</font>醫事機構名稱：</td>
		<td nowrap class="td_form_white" width="65%">
			<input class="field" type="text" name="medAgencyName" size="50" maxlength="50" value="<%=obj.getMedAgencyName()%>">				
		</td>
	</tr>	
	<tr>
		<td nowrap class="td_form" width="35%">機構地址：</td>
		<td nowrap class="td_form_white" width="65%">
			<input class="field" type="text" name="agencyAddress" size="50" maxlength="100" value="<%=obj.getAgencyAddress()%>">				
		</td>
	</tr>	
	<tr>
	  	<td nowrap class="td_form">電話號碼：</td>
	  	<td nowrap class="td_form_white">
	  		<input class="field" type="text" name="areaTel" size="2" maxlength="2" value="<%=obj.getAreaTel()%>">
	  		- <input class="field" type="text" name="tel" size="10" maxlength="10" value="<%=obj.getTel()%>">
	  	</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="35%">特約類別：</td>
		<td nowrap class="td_form_white" width="65%">
			<select class="field" name="engageKind">
				<%=View.getOptionCodeKind("MEDENG", obj.getEngageKind())%>
			</select>				
		</td>
	</tr>	
	<tr>
		<td nowrap class="td_form" width="35%">醫事機構種類：</td>
		<td nowrap class="td_form_white" width="65%">
			<select class="field" name="medAgencyKind">
				<%=View.getOptionCodeKind("MEDKIND", obj.getMedAgencyKind())%>
			</select>				
		</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form">終止合約或歇業日期：</td>
	  	<td nowrap class="td_form_white">
	  		<%=View.getPopCalendar("field", "endDate", obj.getEndDate())%>
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
	<input type="hidden" name="detailid" value="<%=obj.getDetailid()%>">
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
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">異動日期</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">分局別</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">醫事機構代碼</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">醫事機構名稱</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">電話號碼</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',6,false);" href="#">特約類別</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',7,false);" href="#">醫事機構種類</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true, false, false, false, false, false, false, false};
	boolean displayArray[] = {false, true, true, true, true, true, true, true};
	String[] alignArray = {"center", "center", "center", "center", "center", "center", "center", "center"};
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
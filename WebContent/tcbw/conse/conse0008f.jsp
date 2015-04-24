<!--
程式目的：通報表單設定作業
程式代號：conse0008f.jsp
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="CONSE0008" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.conse.CONSE0008F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	obj = (com.kangdainfo.tcbw.view.conse.CONSE0008F)obj.queryOne();	
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
		obj = (com.kangdainfo.tcbw.view.conse.CONSE0008F)obj.queryOne();
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
	if(form1.state.value == "queryAll")
	{
		alertStr += checkQuery();
	}
	else if(form1.state.value=="insert"||form1.state.value=="insertError"||
			form1.state.value=="update"||form1.state.value=="updateError")
	{

		alertStr += checkEmpty(form1.con1007_id, "表單別");
		alertStr += checkEmpty(form1.name, "欄位名稱");
		alertStr += checkEmpty(form1.chName, "欄位中文名稱");
		
		alertStr += checkEmpty(form1.tabId, "頁籤");

        alertStr += checkRadioButton(form1.fieldType, "欄位型態");
		
		isRadioShow = $('input:radio:checked[name="isRequired"]').val();

		if(isRadioShow == undefined)
			alertStr += "是否必填不允許空白!\n";
		isRadioShow = $('input:radio:checked[name="isDate"]').val();
		if(isRadioShow == undefined)
			alertStr += "日期格式不允許空白!\n";
		isRadioShow = $('input:radio:checked[name="isNum"]').val();
		if(isRadioShow == undefined)
			alertStr += "數字格式不允許空白!\n";

		//alertStr += checkEmpty(form1.isLength, "長度限制");

		if(form1.isLength.value != "")
			alertStr += checkNumber(form1.isLength, "長度限制");
	}
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	return true;
}

function queryOne(id){
	form1.id.value = id;
	form1.state.value = "queryOne";
	beforeSubmit();
	form1.submit();
}

function init() {

}
</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<!--Query區============================================================-->
<div id="queryContainer" style="width:500px;height:150px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<table class="queryTable" border="1">
	<tr>
		<td nowrap class="queryTDLable">表單別：</td>
		<td nowrap class="queryTDInput">
			<select class="field_Q" name="q_con1007_id">
				<%=View.getOptionCon1007(obj.getQ_con1007_id())%>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">頁籤：</td>
		<td nowrap class="queryTDInput">
			<select class="field_Q" name="q_tabId">
				<%=View.getOptionCodeKind("TAB", obj.getQ_tabId()) %>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">欄位名稱：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_name" size="40" maxlength="50" value="<%=obj.getQ_name()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">欄位中文名稱：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_chName" size="40" maxlength="50" value="<%=obj.getQ_chName()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">是否必填：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="radio" name="q_isRequired" value="S" <%=obj.getQ_isRequired().equals("S")?"checked":""%>>是
			<input class="field_Q" type="radio" name="q_isRequired" value="N" <%=obj.getQ_isRequired().equals("N")?"checked":""%>>否	
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">是否完整性：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="radio" name="q_isComplete" value="Y" <%=obj.getQ_isRequired().equals("Y")?"checked":""%>>是
			<input class="field_Q" type="radio" name="q_isComplete" value="N" <%=obj.getQ_isRequired().equals("N")?"checked":""%>>否	
		</td>
	</tr>
	<tr>
	  	<td nowrap class="queryTDLable">是否檢核藥證欄位：</td>
	  	<td nowrap class="queryTDInput">
			<input class="field_Q" type="radio" name="q_isMlms" value="Y" <%=obj.getQ_isMlms().equals("Y")?"checked":""%>>是
			<input class="field_Q" type="radio" name="q_isMlms" value="N" <%=obj.getQ_isMlms().equals("N")?"checked":""%>>否	
	  	</td>
	</tr>
	<tr>
		<td nowrap class="queryTDInput" colspan="2" style="text-align:center;">
			<input type="hidden" name="q_id" value="<%=obj.getQ_id()%>">
			<input type="hidden" name="q_isQuery">
			<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="確　　定" onClick="form1.q_isQuery.value='Y'">
			<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="whatButtonFireEvent(this.name)">
		</td>
	</tr>
	</table>
</div>
<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="99%" height="100%">
	<tr>
		<td nowrap class="td_form" width="35%"><font color="red">*</font>表單別：</td>
		<td nowrap class="td_form_white" width="65%">
			<select class="field_Q" name="con1007_id">
				<%=View.getOptionCon1007(obj.getCon1007_id())%>
			</select>
		</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form"><font color="red">*</font>頁籤：</td>
	  	<td nowrap class="td_form_white">
	  	    <select class="field_Q" name="tabId">
				<%=View.getOptionCodeKind("TAB", obj.getTabId())%>
			</select>
	  	</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form"><font color="red">*</font>欄位型態：</td>
	  	<td nowrap class="td_form_white">
			<input class="field_Q" type="radio" name="fieldType" value="T" <%=obj.getFieldType().equals("T")?"checked":""%>>text
			<input class="field_Q" type="radio" name="fieldType" value="R" <%=obj.getFieldType().equals("R")?"checked":""%>>radio	
			<input class="field_Q" type="radio" name="fieldType" value="C" <%=obj.getFieldType().equals("C")?"checked":""%>>checkBox	
	  	</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="35%"><font color="red">*</font>欄位名稱：</td>
		<td nowrap class="td_form_white" width="65%">
			<input class="field" type="text" name="name" size="50" maxlength="50" value="<%=obj.getName()%>">
		</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form"><font color="red">*</font>欄位中文名稱：</td>
	  	<td nowrap class="td_form_white">
	  		<input class="field" type="text" name="chName" size="50" maxlength="50" value="<%=obj.getChName()%>">
	  	</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form"><font color="red">*</font>是否必填：</td>
	  	<td nowrap class="td_form_white">
			<input class="field_Q" type="radio" name="isRequired" value="S" <%=obj.getIsRequired().equals("S")?"checked":""%>>是
			<input class="field_Q" type="radio" name="isRequired" value="N" <%=obj.getIsRequired().equals("N")?"checked":""%>>否	
	  	</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form"><font color="red">*</font>日期格式：</td>
	  	<td nowrap class="td_form_white">
			<input class="field_Q" type="radio" name="isDate" value="Y" <%=obj.getIsDate().equals("Y")?"checked":""%>>是
			<input class="field_Q" type="radio" name="isDate" value="N" <%=obj.getIsDate().equals("N")?"checked":""%>>否	
	  	</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form"><font color="red">*</font>數字格式：</td>
	  	<td nowrap class="td_form_white">
			<input class="field_Q" type="radio" name="isNum" value="Y" <%=obj.getIsNum().equals("Y")?"checked":""%>>是
			<input class="field_Q" type="radio" name="isNum" value="N" <%=obj.getIsNum().equals("N")?"checked":""%>>否	
	  	</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form"><font color="red">*</font>是否完整性：</td>
	  	<td nowrap class="td_form_white">
			<input class="field_Q" type="radio" name="isComplete" value="Y" <%=obj.getIsComplete().equals("Y")?"checked":""%>>是
			<input class="field_Q" type="radio" name="isComplete" value="N" <%=obj.getIsComplete().equals("N")?"checked":""%>>否	
	  	</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form"><font color="red">*</font>是否檢核藥證欄位：</td>
	  	<td nowrap class="td_form_white">
			<input class="field_Q" type="radio" name="isMlms" value="Y" <%=obj.getIsMlms().equals("Y")?"checked":""%>>是
			<input class="field_Q" type="radio" name="isMlms" value="N" <%=obj.getIsMlms().equals("N")?"checked":""%>>否	
	  	</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form">長度限制：</td>
	  	<td nowrap class="td_form_white">
			<input class="field" type="text" name="isLength" size="4" maxlength="4" value="<%=obj.getIsLength()%>">
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
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">表單別</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">欄位名稱</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">欄位中文名稱</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">是否必填</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">日期格式</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',6,false);" href="#">數字格式</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',7,false);" href="#">是否完整性</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',8,false);" href="#">是否檢核藥證欄位</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',9,false);" href="#">長度限制</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true, false, false, false, false, false, false, false, false, false};
	boolean displayArray[] = {false, true, true, true, true, true, true, true, true, true};
	String[] alignArray = {"center", "center", "center", "center", "center", "center", "center", "center", "center", "center"};
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
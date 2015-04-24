<!--
程式目的：
程式代號：暫時.jsp
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.conin.EXPORT0004F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList" />
<%
if("queryAll".equals(obj.getState())){
	if("false".equals(obj.getQueryAllFlag())){
		obj.setQueryAllFlag("true");
	}
}
else if("queryOne".equals(obj.getState())){
	obj = (com.kangdainfo.tcbw.view.conin.EXPORT0004F)obj.queryOne();
}
else if("insert".equals(obj.getState()) || "insertError".equals(obj.getState())){
	obj.insert();
	if("insertSuccess".equals(obj.getState())){
		obj.setQueryAllFlag("true");
		obj.setQ_id(obj.getId());
	}
}
else if("update".equals(obj.getState()) || "updateError".equals(obj.getState())){
	obj.update();
	if("updateSuccess".equals(obj.getState())){
		obj.setQueryAllFlag("true");
	}
}
else if("delete".equals(obj.getState()) || "deleteError".equals(obj.getState())){
	obj.delete();
	if("deleteError".equals(obj.getState())){
		obj = (com.kangdainfo.tcbw.view.conin.EXPORT0004F)obj.queryOne();
	}
}

if("true".equals(obj.getQueryAllFlag())){
	objList = (java.util.ArrayList)obj.queryAll();
}
%>
<html>
<head>
<%@ include file="../../home/meta.jsp" %>
<script type="text/javascript">
var insertDefault;
function checkField(){
	var alertStr = ""
	if(form1.state.value == "insert" || form1.state.value == "insertError"
		|| form1.state.value == "update" || form1.state.value == "updateError"){
		alertStr += checkEmpty(form1.field, "欄位名稱");
		alertStr += checkEmpty(form1.tablename, "資料表名稱");
		alertStr += checkEmpty(form1.codename, "代碼名稱");
		alertStr += checkEmpty(form1.isStop, "是否停用");
	}
	if(alertStr.length != 0){
		alert(alertStr);
		return false;
	}
	beforeSubmit();
	return true;	
}

function queryOne(id){
	form1.id.value = id;
	form1.state.value = "queryOne";
	beforeSubmit();
	form1.submit();
}

function init(){}
</script>
</head>
<body onload="whatButtonFireEvent('<%= obj.getState() %>');init();showErrorMsg('<%= obj.getErrorMsg() %>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<!--Query區============================================================-->
<div id="queryContainer" style="width:500px;height:170px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<table class="queryTable" border="1">
		<tr>
			<td nowrap class="queryTDLable">名稱</td>
			<td nowrap class="queryTDInput">
				<input class="field_Q" type="text" name="q_field" size="50" value="<%= obj.getQ_field()%>">								
			</td>
		</tr>
		<tr>
			<td nowrap class="queryTDLable">欄位名中文名稱</td>
			<td nowrap class="queryTDInput">
				<input class="field_Q" type="text" name="q_fieldname" size="50" value="<%= obj.getQ_fieldname()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="queryTDLable">是否停用</td>
			<td nowrap class="queryTDInput">
				<select class="field_Q" name="q_isStop" type="select">
					<%=View.getYNOption(obj.getQ_isStop())%>
				</select>
			</td>
		</tr>
		<tr>
			<td nowrap class="queryTDLable">是否為多筆</td>
			<td nowrap class="queryTDInput">
				<select class="field_Q" name="q_isMany" type="select">
					<%=View.getYNOption(obj.getQ_isMany())%>
				</select>
			</td>
		</tr>
		<tr>		
			<td nowrap class="queryTDInput" colspan="2" style="text-align:center">
				<input name="q_id" type="hidden" value="<%= obj.getQ_id()%>">
				<input name="q_isQuery" type="hidden">
				<input name="querySubmit" type="submit" class="toolbar_default" value="確    定" followPK="false" onclick="form1.q_isQuery.value='Y'">
				<input name="queryCannel" type="button" class="toolbar_default" value="取    消" followPK="false" onclick="whatButtonFireEvent(this.name)">
			</td>
		</tr>
	</table>
</div>
<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
	<td nowrap class="bg">
		<div id="formContainer">
			<table class="table_form" width="100%" height="100%">
				<tr>
					<td nowrap class="td_form">排序</td>
					<td nowrap class="td_form_white">
						<input class="field" type="text" name="seq" size="50" value="<%= obj.getSeq() %>">
					</td>
					<tr>
						<td nowrap class="td_form">欄位名稱</td>
						<td nowrap class="td_form_white">
							<input class="field" type="text" name="field" size="50" value="<%= obj.getField() %>"> 
						</td>
					</tr>
					<tr>
						<td nowrap class="td_form">欄位名中文名稱</td>
						<td nowrap class="td_form_white">
							<input class="field" type="text" name="fieldname" size="50" value="<%= obj.getFieldname() %>"> 
						</td>
					</tr>
					<tr>
						<td nowrap class="td_form">資料表名稱</td>
						<td nowrap class="td_form_white">
							<input class="field" type="text" name="tablename" size="50" value="<%= obj.getTablename() %>"> 
						</td>
					</tr>
					<tr>
						<td nowrap class="td_form">代碼名稱</td>
						<td nowrap class="td_form_white">
							<input class="field" type="text" name="codename" size="50" value="<%= obj.getCodename() %>"> 
						</td>
					</tr>
					<tr>
						<td nowrap class="td_form">是否停用</td>
						<td nowrap class="td_form_white">							
							<select class="field" name="isStop" type="select">
								<%=View.getYNOption(obj.getIsStop())%>
							</select> 
						</td>
					</tr>
					<tr>
						<td nowrap class="td_form">是否為多筆</td>
						<td nowrap class="td_form_white">
							 <select class="field" name="isMany" type="select">
								<%=View.getYNOption(obj.getIsMany())%>
							</select> 
						</td>
					</tr>
					<tr>
						<td nowrap class="td_form">多筆名稱</td>
						<td nowrap class="td_form_white">
							<input class="field" type="text" name="manyName" size="50" value="<%= obj.getManyName() %>"> 
						</td>
					</tr>
					<tr>
						<td nowrap class="td_form">異動人員/日期</td>
						<td nowrap class="td_form_white">
							[
							<input class="field_RO" type="text" name="modifier" value="<%= obj.getModifier() %>">
							/
							<input class="field_RO" type="text" name="modifyDate" value="<%= obj.getModifyDate() %>">
							]
						</td>						
					</tr>
				</tr>
			</table>			
		</div>
	</td>
</tr>
<!--Toolbar區============================================================-->
<tr>
	<td nowrap class="bgToolbar" style="text-align:center">
		<input type="hidden" name="id" value="<%= obj.getId() %>">
		<input type="hidden" name="state" value="<%= obj.getState() %>">
		<input type="hidden" name="queryAllFlag" value="<%= obj.getQueryAllFlag() %>">
		<input type="hidden" name="editID" value="<%= obj.getEditID() %>">
		<jsp:include page="../../home/toolbar.jsp" />
	</td>
</tr>
<tr>
	<td nowrap class="bgPagging">
		<% request.setAttribute("QueryBean",obj); %>
		<jsp:include page="../../home/page.jsp" />		
	</td>
</tr>

<!--List區============================================================-->
<tr>
	<td nowrap class="bgList">
		<div id="listContainer">			
			<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
				<thead id="listTHEAD">
					<tr>
						<th class="listTH"><a class="text_link_w">NO.</a></th>
						<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">排序</a></th>
						<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">名稱</a></th>
						<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">欄位名中文名稱</a></th>
					</tr>				
				</thead>
				<tbody id="listTBODY">
					<%
						boolean primaryArray[] = {true,false,false,false};
						boolean displayArray[] = {false,true,true,true};
						out.write(View.getQuerylist(primaryArray,displayArray,objList,obj.getQueryAllFlag()));
					%>
				</tbody>
			</table>			
		</div>	
	</td>
</tr>
</table>
</form>
</body>
</html>
<!--
程式目的：機關代碼維護作業_CPA版本
程式代號：sysca002f_cpa
程式日期：0980811
程式作者：shark
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.common.view.sys.ca.SYSCA002F_CPA">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>

<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	obj = (com.kangdainfo.common.view.sys.ca.SYSCA002F_CPA)obj.queryOne();	
}else if ("insert".equals(obj.getState()) || "insertError".equals(obj.getState())) {
	obj.insert();
}else if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())) {
	obj.update();
}else if ("delete".equals(obj.getState()) || "deleteError".equals(obj.getState())) {
	obj.delete();
}
if ("true".equals(obj.getQueryAllFlag())){
	objList = (java.util.ArrayList) obj.queryAll();
}
%>

<html>
<head>
<%@ include file="../../home/meta.jsp" %>
<script language="javascript">
var insertDefault;	//二維陣列, 新增時, 設定預設值
function checkField(){
	var alertStr="";
	if(form1.state.value=="queryAll"){
		//alertStr += checkQuery();
	}else if(form1.state.value=="insert"||form1.state.value=="insertError"||form1.state.value=="update"||form1.state.value=="updateError"){
		alertStr += checkEmpty(form1.departmentCode,"機關代碼");
		alertStr += checkEmpty(form1.department,"機關名稱");
		alertStr += checkEmpty(form1.level,"機關層級");
		if (getNumeric(form1.level.value)>1) alertStr += checkEmpt(form1.parentId,"若機關層級不為1時,主管機關");
	}
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
}
function queryOne(id){
	form1.id.value=id;
	form1.state.value="queryOne";
	beforeSubmit();
	form1.submit();
}
</script>
</head>

<body topmargin="0" onLoad="whatButtonFireEvent('<%=obj.getState()%>');showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">

<!--Query區============================================================-->
<div id="queryContainer" style="width:750px;height:350px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<table class="queryTable"  border="1">
	<tr>
		<td nowrap class="queryTDLable">主管機關：</td>
		<td nowrap class="queryTDInput">
			<%=View.getPopOrgan("field_Q","q_parentId",obj.getQ_parentId(),obj.getQ_parentIdName(),"N","q_accountingYear","q_manageDept")%>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">機關代碼：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_departmentCode" size="20" maxlength="20" value="<%=obj.getQ_departmentCode()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">機關名稱：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_department" size="20" maxlength="50" value="<%=obj.getQ_department()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">機關層級：</td>
		<td nowrap class="queryTDInput">
			<select class="field_Q" type="select" name="q_level">
            	<%=View.getTextOption("1;1;2;2;3;3;4;4;5;5;",obj.getQ_level())%>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">機關地址：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_address" size="20" maxlength="20" value="<%=obj.getQ_address()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">電話：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_phone" size="20" maxlength="20" value="<%=obj.getQ_phone()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDInput" colspan="2" style="text-align:center;">

			<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="確　　定" >
			<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="whatButtonFireEvent(this.name)">
		</td>
	</tr>
	</table>
</div>

<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer">
	<table class="table_form" width="100%" height="100%">
	<tr>
	  <td nowrap class="td_form"><font color="red">*</font>機關代碼：</td>
	  <td nowrap class="td_form_white"><input class="field" type="text" name="departmentCode" size="10" maxlength="10" value="<%=obj.getDepartmentCode()%>"></td>
	  </tr>
	<tr>
	  <td nowrap class="td_form"><font color="red">*</font>機關名稱：</td>
	  <td nowrap class="td_form_white"><input class="field" type="text" name="department" size="50" maxlength="100" value="<%=obj.getDepartment()%>"></td>
	  </tr>
	<tr>
	  <td nowrap class="td_form"><font color="red">*</font>機關層級：</td>
	  <td nowrap class="td_form_white"><select class="field" type="select" name="level">
	    <%=View.getTextOption("1;1;2;2;3;3;4;4;5;5;",obj.getLevel())%>
	    </select></td>
	  </tr>
	<tr>
		<td nowrap class="td_form">主管機關：</td>
		<td nowrap class="td_form_white">
		  <%=View.getPopOrgan("field","parentId",obj.getParentId(),obj.getParentIdName(),"N","accountingYear","manageDept")%>
		  </td>
		</tr>
	<tr>
	  <td nowrap class="td_form">電話：</td>
	  <td nowrap class="td_form_white"><input class="field" type="text" name="phone" size="50" maxlength="50" value="<%=obj.getPhone()%>"></td>
	  </tr>
	<tr>
	  <td nowrap class="td_form">郵遞區號：</td>
	  <td nowrap class="td_form_white"><input class="field" type="text" name="zip" size="10" maxlength="3" value="<%=obj.getZip()%>"></td>
		</tr>
	<tr>
	  <td nowrap class="td_form">機關地址：</td>
	  <td nowrap class="td_form_white"><input class="field" type="text" name="address" size="50" maxlength="100" value="<%=obj.getAddress()%>"></td>
		</tr>
	<tr>
	  <td nowrap class="td_form">異動人員/日期：</td>
	  <td nowrap class="td_form_white">
	    [<input class="field_RO" type="text" name="editID" size="10" value="<%=obj.getEditID()%>">/
	    <input class="field_RO" type="text" name="editDate" size="7" value="<%=obj.getEditDate()%>">]
	    </td>
	  </tr>
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bg" style="text-align:center">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getId()%>">
	<jsp:include page="../../home/toolbar.jsp" />
</td></tr>

<tr><td nowrap>
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td></tr>
<!--List區============================================================-->
<tr><td nowrap class="bg">
<div id="listContainer">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>		
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">機關代碼</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">機關名稱</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">層級</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">主管機關</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">機關地址</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',6,false);" href="#">電話</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true,false,false,false,false,false,false};
	boolean displayArray[] = {false,true,true,true,true,true,true};
	String alignArray[] = {"center","left","left","center","left","left","left"};
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

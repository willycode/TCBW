<!--
程式目的：郵遞區號維護作業
程式代號：conse0003f.jsp
程式日期：102.07.19
程式作者：judas.liao
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="CONSE0003" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.conse.CONSE0003F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	obj = (com.kangdainfo.tcbw.view.conse.CONSE0003F)obj.queryOne();	
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
		obj = (com.kangdainfo.tcbw.view.conse.CONSE0003F)obj.queryOne();
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
<script type="text/javascript">
//二維陣列, 新增時, 設定預設值
var insertDefault = new Array(
		new Array("isactive", "Y")
	);
function checkField(){
	var alertStr = "";
	if(form1.state.value == "queryAll"){
		alertStr += checkQuery();
	}else if(form1.state.value=="insert"||form1.state.value=="insertError"||form1.state.value=="update"||form1.state.value=="updateError"){
		alertStr += checkEmpty(form1.city, "縣市");
		alertStr += checkEmpty(form1.zipname, "區域");
		alertStr += checkEmpty(form1.zipcode, "郵遞區號");
		alertStr += checkEmpty(form1.isactive, "是否使用中");
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
<div id="queryContainer" style="width:600px;height:150px;display:none">
	<iframe id="queryContainerFrame"></iframe>
	<div class="queryTitle">查詢視窗</div>
	<table class="queryTable" border="1">
	<tr>
		<td nowrap class="queryTDLable">縣市：</td>
		<td nowrap class="queryTDInput">
			<select class="field_Q" name="q_city" type="select">
				<%=View.getOptionCodeKind("CTY", obj.getQ_city())%>
			</select>
		</td>	
	</tr>
	<tr>
		<td nowrap class="queryTDLable">區域：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_zipname" size="10" maxlength="10" value="<%=obj.getQ_zipname()%>">
		</td>
	</tr>	
	<tr>
		<td nowrap class="queryTDLable">郵遞區號：</td>
		<td nowrap class="queryTDInput">
	    		<input class="field_Q" type="text" name="q_zipcode" size="3" maxlength="3" value="<%=obj.getQ_zipcode()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">是否使用中：</td>
		<td nowrap class="queryTDInput">
			<select name="q_isactive" class="field_Q" type="select">
	    		<%=View.getTextOption("Y;是;N;否;", obj.getQ_isactive(), 1)%>
	    	</select>			
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
	<div id="formContainer">
	<table class="table_form" width="99%" height="100%">
	<tr>
		<td nowrap class="td_form" width="35%"><font color="red">*</font>縣市：</td>
		<td nowrap class="td_form_white" width="65%">
			<select class="field" name="city" type="select">
				<%=View.getOptionCodeKind("CTY", obj.getCity())%>
			</select>
		</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form"><font color="red">*</font>區域：</td>
	  	<td nowrap class="td_form_white" width="65%">
	  		<input class="field" type="text" name="zipname" size="10" maxlength="10" value="<%=obj.getZipname()%>">
	  	</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="35%"><font color="red">*</font>郵遞區號：</td>
		<td nowrap class="td_form_white" width="65%">
				<input class="field" type="text" name="zipcode" size="3" maxlength="3" value="<%=obj.getZipcode()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form"><font color="red">*</font>是否使用中：</td>
		<td nowrap class="td_form_white">
			<select name="isactive" class="field" type="select">
	    		<%=View.getTextOption("Y;是;N;否;", obj.getIsactive(), 1)%>
	    	</select>			
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
	<input type="hidden" name="editID" value="<%=User.getEditId()%>">
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
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH" ><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">縣市</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">區域</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">郵遞區號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">使用中</a></th>
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


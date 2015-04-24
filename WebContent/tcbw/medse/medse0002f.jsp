<!--
程式目的：醫療器材NCAR通報篩選設定作業
程式代號：medse0002f.jsp
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="MEDSE0002" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.medse.MEDSE0002F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	obj = (com.kangdainfo.tcbw.view.medse.MEDSE0002F)obj.queryOne();	
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
		obj = (com.kangdainfo.tcbw.view.medse.MEDSE0002F)obj.queryOne();
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

var insertDefault = new Array(
		new Array("isStop", "Y"),
		new Array("isYes", "1"),
		new Array("isNo", "0"),
		new Array("isUnknown", "0"),
		new Array("isYesType", "0"),
		new Array("isNoType", "0"),
		new Array("isUnknownType", "0")
	);


function checkField(){
	var alertStr = "";
	var isShowEvidentiary = "";
	var isShowSeverity = "";
	if(form1.state.value == "queryAll"){
		alertStr += checkQuery();
	}else if(form1.state.value=="insert"||form1.state.value=="insertError"||form1.state.value=="update"||form1.state.value=="updateError"){
		alertStr += checkEmpty(form1.num, "編號");
		alertStr += checkEmpty(form1.matter, "事項");
		alertStr += checkEmpty(form1.isYesType, "是的屬性");
		alertStr += checkEmpty(form1.isYes, "是的分數");
		if(form1.isYes.value != "")
			alertStr += checkNumber(form1.isYes, "是的分數");
		alertStr += checkEmpty(form1.isNoType, "否的屬性");
		alertStr += checkEmpty(form1.isNo, "否的分數");
		if(form1.isNo.value != "")
			alertStr += checkNumber(form1.isNo, "否的分數");
		alertStr += checkEmpty(form1.isUnknownType, "未知的屬性");
		alertStr += checkEmpty(form1.isUnknown, "未知的分數");
		if(form1.isUnknown.value != "")
			alertStr += checkNumber(form1.isUnknown, "未知的分數");
		alertStr += checkEmpty(form1.seq, "排序");
		if(form1.seq.value != "")
			alertStr += checkNumber(form1.seq, "排序");
		alertStr += checkEmpty(form1.isStop, "是否停用");
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
		<td nowrap class="queryTDLable">編號：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_num" size="4" maxlength="4" value="<%=obj.getQ_num()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">事項：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_matter" size="50" maxlength="50" value="<%=obj.getQ_matter()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">是否停用：</td>
		<td nowrap class="queryTDInput">
			<select name="q_isStop" class="field_Q" type="select">
	    		<%=View.getTextOption("Y;是;N;否;", obj.getQ_isStop(), 1)%>
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
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="99%" height="100%">
	<tr>
		<td nowrap class="td_form" width="35%"><font color="red">*</font>編號：</td>
		<td nowrap class="td_form_white" width="65%">
			<input class="field" type="text" name="num" size="4" maxlength="4" value="<%=obj.getNum()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="35%"><font color="red">*</font>事項：</td>
		<td nowrap class="td_form_white" width="65%">
			<input class="field" type="text" name="matter" size="50" maxlength="50" value="<%=obj.getMatter()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="35%"><font color="red">*</font>是：</td>
		<td nowrap class="td_form_white" width="65%">
			型態
			<select name="isYesType" class="field" type="select">
	    		<%=View.getTextOption("0;+;1;-;", obj.getIsYesType(), 1)%>
	    	</select>
	    	分數	
			<input class="field" type="text" name="isYes" size="2" maxlength="2" value="<%=obj.getIsYes()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="35%"><font color="red">*</font>否：</td>
		<td nowrap class="td_form_white" width="65%">
			型態
			<select name="isNoType" class="field" type="select">
	    		<%=View.getTextOption("0;+;1;-;", obj.getIsNoType(), 1)%>
	    	</select>
	    	分數	
			<input class="field" type="text" name="isNo" size="2" maxlength="2" value="<%=obj.getIsNo()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="35%"><font color="red">*</font>未知：</td>
		<td nowrap class="td_form_white" width="65%">
			型態
			<select name="isUnknownType" class="field" type="select">
	    		<%=View.getTextOption("0;+;1;-;", obj.getIsUnknownType(), 1)%>
	    	</select>
	    	分數	
			<input class="field" type="text" name="isUnknown" size="2" maxlength="2" value="<%=obj.getIsUnknown()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="35%"><font color="red">*</font>排序：</td>
		<td nowrap class="td_form_white" width="65%">
			<input class="field" type="text" name="seq" size="2" maxlength="2" value="<%=obj.getSeq()%>">
		</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form"><font color="red">*</font>是否停用：</td>
	  	<td nowrap class="td_form_white">
	  		<select name="isStop" class="field" type="select">
	    		<%=View.getTextOption("Y;是;N;否;", obj.getIsStop(), 1)%>
	    	</select>	
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
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">編號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">事項</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">是</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">否</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">不知</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',6,false);" href="#">排序</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',7,false);" href="#">是否停用</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true, false, false, false, false, false, false, false};
	boolean displayArray[] = {false, true, true, true, true, true, true, true};
	String[] alignArray = {"center", "center", "left", "center", "center", "center", "center", "center"};
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
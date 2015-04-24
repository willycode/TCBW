<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.util.Con0002DbSearch">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryOne".equals(obj.getState())) {
	obj = (com.kangdainfo.tcbw.util.Con0002DbSearch)obj.queryOne();	
}else{
	obj.setQueryAllFlag("true");
}
if ("true".equals(obj.getQueryAllFlag())){
	objList = (java.util.ArrayList) obj.queryAll();
}
%>
<html>
<head>
<title>郵件清單紀錄</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript" src="../js/tablesoft.js"></script>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
<script type="text/javascript">
function checkField(){	
	return true;
}

function init(){
	setDisplayItem('spanInsert,spanQueryAll,spanDelete,spanListHidden,spanUpdate,spanClear,spanConfirm','H');

	CKEDITOR.replace('mailBody',{
		toolbar :
			[
				{ name: 'document', items : [ 'NewPage','Preview' ] },
				{ name: 'clipboard', items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ] },
				{ name: 'editing', items : [ 'Find','Replace','-','SelectAll','-','Scayt' ] },
				{ name: 'insert', items : [ 'Table'] },
		           '/',
				{ name: 'styles', items : [ 'Styles','Format' ] },
				{ name: 'basicstyles', items : [ 'Bold','Italic','Strike','-','RemoveFormat' ] },
				{ name: 'paragraph', items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote' ] },
				{ name: 'links', items : [ 'Link','Unlink','Anchor' ] },
				{ name: 'tools', items : [ 'Maximize','-','About' ] }
			]
	});
}
function queryOne(id){
	form1.id.value = id;
	form1.state.value = "queryOne";
	beforeSubmit();
	form1.submit();
}

function beforeInit() {
	<%=Common.get(obj.getConJSBuilder())%>
}
</script>
</head>
<body onLoad="beforeInit();whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" action="popCon0002.jsp" onSubmit="return checkField()">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" height="100%">
	<tr>
		<td nowrap class="td_form" width="15%">通報表單名稱：</td>
		<td nowrap class="td_form_white" width="85%">
			<select name="SystemType" class="field">
	    		<%=View.getOption("select formCode,formdName from Con1007Db",obj.getSystemType(),false,1)%>
	    	</select>
		</td>
	</tr>
	<%-- 
	<tr>
		<td nowrap class="td_form">表單編號：</td>
		<td nowrap class="td_form_white">
		    <select class="field" name="formID"  >
	    		<%=View.getOption("select id, formdName from Con1007Db",obj.getFormID(), false, 1)%>
	    	</select>
		</td>
	</tr>
	 
	<tr>
		<td nowrap class="td_form">案件狀態：</td>
		<td nowrap class="td_form_white">
		    <select class="field" name="mailState">
		    	<%=View.getOption("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGST1' order by codeId",obj.getMailState(), false, 1) %>
	    	</select>
		</td>
	</tr>
	--%>
	<tr>
		<td nowrap class="td_form" width="15%">主旨：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="title" size="60" maxlength="30" value="<%=obj.getTitle()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">信件內容：</td>
		<td nowrap class="td_form_white">		
		    <textarea id="mailBody" name="mailBody" cols="80" rows="10"><%=obj.getMailBody()%></textarea>
		</td>
	</tr>
	<tr>
	    <td nowrap class="td_form" width="15%">附件：</td>
	    <td nowrap class="td_form_white">
		    <jsp:include page="con02Layout.jsp"/>
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
	<!--預設欄位名稱  -->
	<input type="hidden" name="sysType" value="<%=obj.getSysType()%>">
	<input type="hidden" name="formID" value="<%=obj.getFormID()%>">
	<input type="hidden" name="applNo" value="<%=obj.getApplNo()%>">
	<jsp:include page="toolbar.jsp" />
</td></tr>

<tr><td nowrap>
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="page.jsp" />
</td></tr>

<!--List區============================================================-->
<tr><td nowrap class="bg" >
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" >NO.</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">案件編號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">郵件日期</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">郵件主旨</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
		<%
		boolean primaryArray[] = {true, false, false, false};
		boolean displayArray[] = {false, true, true, true};
		out.write(View.getQuerylist(primaryArray,displayArray,objList,obj.getQueryAllFlag()));
		%>
	</tbody>
</table>
</div>
</td></tr>
</table>	
</form>
</body>
</html>

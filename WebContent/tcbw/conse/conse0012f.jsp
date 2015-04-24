<!--
程式目的：失敗郵件重寄作業
程式代號：conse0012f.jsp
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="CONSE0012" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.conse.CONSE0012F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%

if ("queryAll".equals(obj.getState())) 
{
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}
else if ("queryOne".equals(obj.getState())) 
{
	obj = (com.kangdainfo.tcbw.view.conse.CONSE0012F)obj.queryOne();	
}
else if ("approve1".equals(obj.getState())) 
{
	obj.update();
	if("updateSuccess".equals(obj.getState()))
	{
	  obj.setErrorMsg("重送出信件 !\n");
	}
}
if ("true".equals(obj.getQueryAllFlag()))
{
	objList = (java.util.ArrayList) obj.queryAll();
}
%>
<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css">
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="./../../ckeditor/ckeditor.js"></script>
<script type="text/javascript">
var insertDefault;	//二維陣列, 新增時, 設定預設值
function checkField()
{
	var alertStr = "";
	if(form1.state.value == "queryAll")
	{
		alertStr += checkQuery();
	}
	else if(form1.state.value=="approve1")
	{
		alertStr += checkEmpty(form1.systemType, "系統別");
		alertStr += checkEmpty(form1.title, "主旨");
		alertStr += checkEmpty(form1.mail, "收件者");
	}
	
	if(alertStr.length!=0)
	{ 
		alert(alertStr); 
		return false; 
	}
	
	beforeSubmit();
	form1.submit();
	return true;
}

function queryOne(id)
{
	form1.id.value = id;
	form1.state.value = "queryOne";
	beforeSubmit();
	form1.submit();
}



function init() 
{
	//CKEDITOR.replace('mailBody');
	setDisplayItem('spanInsert,spanUpdate,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden','H');
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
		<td nowrap class="queryTDLable">系統別：</td>
		<td nowrap class="queryTDInput">
			<select class="field_Q" name="q_systemType" >
				<%=View.getOptionCodeKind("SYS", obj.getQ_systemType())%>
			</select>
		</td>
	</tr>
	<tr>
	  	<td nowrap class="queryTDLable">狀態：</td>
	  	<td nowrap class="queryTDInput">
	  	<select class="field_Q" name="q_mailState" >
				<%=View.getTextOption("F;失敗;S;成功;A;重發成功", obj.getQ_mailState())%>
			</select>
	    </td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">案件編號：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_applNo" size="20" maxlength="10" value="<%=obj.getQ_applNo()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">主旨：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_title" size="50" maxlength="100" value="<%=obj.getQ_title()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">發送日期：</td>
		<td nowrap class="queryTDInput">
			<%=View.getPopCalendar("field_Q", "q_createDateS", obj.getQ_createDateS())%>~
		    <%=View.getPopCalendar("field_Q", "q_createDateE", obj.getQ_createDateE())%>
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
	<table class="table_form" width="100%" height="100%">
	<tr>
		<td nowrap class="td_form" width="15%">系統別：</td>
		<td nowrap class="td_form_white" width="85%">
			<select class="field_QRO" name="systemType" onchange="setValue(this,formID);">
				<%=View.getOptionCodeKind("SYS", obj.getSystemType())%>
			</select>
		</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form">案件編號：</td>
	  	<td nowrap class="td_form_white">
	        <input class="field_QRO" type="text" name="applNo" size="20" maxlength="20" value="<%=obj.getApplNo()%>">
	    </td>
	</tr>
	<tr>
	  	<td nowrap class="td_form">狀態：</td>
	  	<td nowrap class="td_form_white">
	        <input class="field_QRO" type="text" name="mailState" size="20" maxlength="20" value="<%=obj.getMailState()%>">
	    </td>
	</tr>
	<tr>
	  	<td nowrap class="td_form">錯誤訊息：</td>
	  	<td nowrap class="td_form_white">
	        <input class="field_QRO" type="text" name="failReasons" size="100" maxlength="100" value="<%=obj.getFailReasons()%>">
	    </td>
	</tr>
	<tr>
	  	<td nowrap class="td_form"><font color="red">*</font>收件者：</td>
	  	<td nowrap class="td_form_white">
	        <input class="field_Q" type="text" name="mail" size="100" maxlength="100" value="<%=obj.getMail()%>">
	    </td>
	</tr>
	<tr>
	  	<td nowrap class="td_form"><font color="red">*</font>副件：</td>
	  	<td nowrap class="td_form_white">
	        <input class="field_Q" type="text" name="ccmail" size="100" maxlength="100" value="<%=obj.getCcmail()%>">
	    </td>
	</tr>
	<tr>
	  	<td nowrap class="td_form"><font color="red">*</font>密件：</td>
	  	<td nowrap class="td_form_white">
	        <input class="field_Q" type="text" name="bccmail" size="100" maxlength="100" value="<%=obj.getBccmail()%>">
	    </td>
	</tr>
	<tr>
	  	<td nowrap class="td_form"><font color="red">*</font>主旨：</td>
	  	<td nowrap class="td_form_white">
	  		<input class="field_Q" type="text" name="title" size="50" maxlength="100" value="<%=obj.getTitle()%>">
	  	</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form"><font color="red">*</font>內容：</td>
	  	<td nowrap class="td_form_white">
	  		 <textarea id="mailBody" name="mailBody" cols="30" rows="20"><%=obj.getMailBody()%></textarea>
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
	<input class="toolbar_default" type="button" name="approve1" value="重發送信件" onClick="whatButtonFireEvent(this.name)">
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
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">系統別</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">狀態</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">案件編號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">主旨</a></th>
	    <th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">發送日期</a></th>
	    <th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">重送日期</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true, false, false, false, false, false, false};
	boolean displayArray[] = {false, true, true, true, true, true, true};
	String[] alignArray = {"center", "center", "center", "center", "center", "center", "center"};
	out.write(View.getQuerylist(primaryArray,displayArray,alignArray,objList,obj.getQueryAllFlag()));
	%>
	</tbody>
</table>
</div>
</td>
</tr>
</table>
</form>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName)
{
	switch (buttonName)	
	{
	  case "approve1":
		  form1.state.value = buttonName;
		  checkField();
	  break;
	}
}

</script>
</body>
</html>
<!--
程式目的：
程式代號：
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgex.DRGEX0106F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%

if("doReplyUpdate".equals(obj.getState()))
{
	try
	{
		obj.setHttpRequest(pageContext.getRequest());
		obj.doReplyUpdate("send");
		obj.setErrorMsg("補件回覆完成");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
else if ("updFile".equals(obj.getState())) {
	obj.setHttpRequest(pageContext.getRequest());	
	obj.doDeleteFile();
	obj.setState("init");
}

else if("upload".equals(obj.getState()))
{
	try
	{		
		obj.setHttpRequest(pageContext.getRequest());
		obj.doReplyUpdate("save");
		obj = (com.kangdainfo.tcbw.view.drgex.DRGEX0106F) obj.queryOne();
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

obj = (com.kangdainfo.tcbw.view.drgex.DRGEX0106F) obj.queryOne();

%>

<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../ckeditor/ckeditor.js"></script>
<script type="text/javascript">

var insertDefault = new Array();

function checkField(){
	beforeSubmit();
	form1.submit();
	return true;
}



function init() 
{
	setDisplayItem('spanInsert,spanUpdate,spanQueryAll,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden','H');
	form1.replyDate.value="<%=Datetime.getYYYMMDD()%>";
	CKEDITOR.replace('notifyBody',{
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
	if('<%=obj.getErrorMsg()%>'=='補件回覆完成' )
	{
		window.opener.location.href = "drgex0101f.jsp";
		window.close();
	}
}

function beforeInit() {
	<%=obj.getConJSBuilder()%>
}

function upload()
{
	
	 var prop='';
	 prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=no,scrollbars=yes,resizable=yes,';
	 prop=prop+'width=850,';
	 prop=prop+'height=600';
	 closeReturnWindow();
     var url="../../home/popManyUploadSimple.jsp?";
         url+="fileKind=DRG";
         url+="&systemType=DRG010001";
         url+="&uploadId="+form1.id1.value;
         url+="&dbName=Con0004Db"; 
	 returnWindow = window.open(url,'上傳檔案',prop);
}

function updFile(){
	form1.action = "drgex0106f.jsp";
	form1.state.value = "updFile";
	form1.target = "saveContainerFrame";	
	beforeSubmit();
	form1.submit();
	form1.action = "drgex0106f.jsp";
	form1.target = "_self";
}


</script>
</head>
<body onLoad="beforeInit();whatButtonFireEvent('<%=obj.getState()%>');showErrorMsg('<%=obj.getErrorMsg()%>');init();">
<form id="form1" name="form1" method="post" onSubmit="checkField();" autocomplete="off">
<iframe name="saveContainerFrame" width="0" height="0" frameborder="0">
</iframe>
<table width="100%" cellspacing="0" cellpadding="0">
<!--Toolbar區============================================================-->
<tr>
    <td nowrap class="bgToolbar">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="id1" value="<%=obj.getId1()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="inOrOut" value="<%=User.getInORout()%>">
	<input type="hidden" name="isAlreadyAutoSave">
	<input type="hidden" name="applNo" value="<%=obj.getApplNo()%>">
    <jsp:include page="../../home/toolbar.jsp">
	   <jsp:param value="N" name="btnCopy"/>
    </jsp:include>
    <span id="spanDoReplyUpdate">
	   <input class="toolbar_default" type="button" followPK="false" name="doReplyUpdate" value="回　覆" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span> 
    <span id="spanDoUpload">
			<input class="toolbar_default" type="button" followPK="false" name="doUpload" value="附件上傳" onClick="upload()">&nbsp;
	</span>
    </td>
</tr>

<tr>
<td nowrap>
  <% request.setAttribute("QueryBean",obj);%>
  <jsp:include page="../../home/page_row.jsp" />
</td>
</tr>
</table>
<!--Form區============================================================-->
<table width="100%" cellspacing="0" cellpadding="0">	
	<tr>
		<td nowrap class="td_form" width="15%">通知補件日期</td>
		<td nowrap class="td_form_white" width="85%" colspan="3">
             <%=View.getPopCalendar("field_RO","notifyDate",obj.getNotifyDate())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" >補件說明</td>
		<td nowrap class="td_form_white"  colspan="3">
		<textarea class="field_RO" name="notifyBody" cols="100" rows="8"><%=Common.get(obj.getNotifyBody())%></textarea>
		</td>		
	</tr>
	<tr>
		<td nowrap class="td_form" width="15%">補件回覆日期</td>
		<td nowrap class="td_form_white" width="85%" colspan="3">
             <%=View.getPopCalendar("field_RO","replyDate",obj.getReplyDate())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">補件回覆</td>
		<td nowrap class="td_form_white"  colspan="3">
			<textarea class="field_Q" name="replyBody" cols="100" rows="8"><%=obj.getReplyBody()%></textarea>
		</td>		
	</tr>
	<tr>
		<td nowrap class="td_form">附件</td>
		<td nowrap class="td_form_white"  colspan="3">
			<jsp:include page="drgex0106Layout.jsp"/>
		</td>		
	</tr>
</table>
</form>
<script type="text/javascript">
localButtonFireListener.whatButtonFireEvent = function(buttonName)
{
	switch (buttonName)	
	{
	  case "doReplyUpdate":
		  form1.state.value = buttonName;
		  checkField();
		  break;
	}
}



</script>
</body>
</html>

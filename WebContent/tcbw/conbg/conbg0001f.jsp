<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.conbg.CONBG0001F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>

<%
String strJavaScript = "";

//系統別
String systemType = ESAPI.encoder().encodeForHTML(Common.get(request.getParameter("systemType")));

//郵件編號
String mailID = ESAPI.encoder().encodeForHTML(Common.get(request.getParameter("mailID")));

//多筆郵件編號
String manyMailID = ESAPI.encoder().encodeForHTML(Common.get(request.getParameter("manyMailID")));

//夾檔資料
String drgFileData = Common.get(request.getParameter("drgFileData"));

//案號
String applNo = ESAPI.encoder().encodeForHTML(Common.get(request.getParameter("applNo")));
if(!"".equals(applNo))
  obj.setApplNo(applNo);

//狀態
String applState = ESAPI.encoder().encodeForHTML(Common.get(request.getParameter("applState")));
if(!"".equals(applState))
  obj.setState2(applState);

String id = ESAPI.encoder().encodeForHTML(Common.get(request.getParameter("id")));
obj.setId(id);

String kind = ESAPI.encoder().encodeForHTML(Common.get(request.getParameter("kind")));
obj.setKind(kind);

//廠商統編
String applicationId =ESAPI.encoder().encodeForHTML(Common.get(request.getParameter("applicationId")));
obj.setApplicationId(applicationId);

String isJS= ESAPI.encoder().decodeForHTML(ESAPI.encoder().encodeForHTML(Common.get(request.getParameter("isJS"))));
if (!"".equals(Common.get(isJS)))
{
	strJavaScript += isJS;
	if(isJS.indexOf("(") < 0 ){
		strJavaScript += "();";
	}
	strJavaScript += "\n\n";
}

//是否為補件
String isAdd= ESAPI.encoder().encodeForHTML(Common.get(request.getParameter("isAdd")));
obj.setIsAdd(isAdd);

//塞入作業者郵件
String worker= ESAPI.encoder().encodeForHTML(Common.get(request.getParameter("worker")));
obj.setWorker(worker);

String con1003DbId = ESAPI.encoder().encodeForHTML(Common.get(request.getParameter("c3Id")));
obj.setCon1003Id(con1003DbId);

if(!"".equals(systemType))
{	
  obj.setSystemType2(systemType);
}

//若mailID有值，則   不顯示    選擇信件內容按鈕
if(!"".equals(mailID) )
{
   obj.setIsShow("N");
}

//manyMailID，則    顯示   選擇信件內容按鈕
if(!"".equals(manyMailID) )
{
   obj.setIsShow("Y");
}


obj.setUrl(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath());
if("approve1".equals(obj.getState()))
{
	try
	{
		obj.update();
		if("updateSuccess".equals(obj.getState()))
		{
			//obj.setErrorMsg("送出信件成功 !\n");
			obj.setTitle("");
			obj.setMailAddress("");
			obj.setMailBody(null);
		}
		else
		{
			obj.setErrorMsg(obj.getErrorMsg());
			obj.setState("init");
		}
	}
	catch(Exception e)
	{
		if (e.getMessage()!=null && e.getMessage().length()<200) 
		{
			obj.setErrorMsg(Common.escapeJavaScript(e.getMessage()));
		} 
		else 
		{
			obj.setErrorMsg("發生錯誤，請重新操作 !");
			e.printStackTrace();
		}
	}
}
else
{
	obj.queryOne();
}
%>

<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="./../../js/default.css" type="text/css"/>
<script type="text/javascript" src="./../../js/function.js"></script>
<script type="text/javascript" src="./../../js/validate.js"></script>
<script type="text/javascript" src="./../../js/jquery.js"></script>
<script type="text/javascript" src="./../../ckeditor/ckeditor.js"></script>
<script type="text/javascript">
function checkField()
{
	var alertStr = "";
	var temp = "";
	alertStr += checkEmpty(form1.title, "主旨");
	alertStr += checkEmpty(form1.mailAddress, "收件者");

	$("input[type='text'][name^='filePath']").each(function()
	{
		if($(this).val()!=""){
			temp += $(this).val() + "|";
		}
	});
	//alert(temp);
	form1.fileData.value = temp;
	if(alertStr.length > 0)
	{
		form1.state.value = "init";
		alert(alertStr);
		return false;
	}
    form1.state.value = "approve1";
	beforeSubmit();
	return true;
}


var number = 1;

function addAtt(filekind,filePath)
{
	var fileCount;
	fileCount = number++;		
	//var oldHtml = $("#attView").html();
	var newItem ="<div id=\"fileType"+fileCount+"\">";
	newItem +="<input id ='filePath"+fileCount+"' class=\"field_RO\" type=\"text\" name=\"filePath"+fileCount+"\" size=\"100\" readonly maxlength=\"300\" value=\""+filePath+"\">&nbsp;";
	newItem +="<input class=\"field\" type=\"button\" name=\"btn_filePath\" onclick=\"openUploadWindow('filePath"+fileCount+"','','"+filekind+"');\" value=\"瀏覽 .....\">&nbsp;";
	newItem +="<input class=\"field\" name=\"btnAdd\" value=\"+\" type=\"button\" onClick=\"addAtt('"+filekind+"', '', true)\" >&nbsp;";
	newItem +="<input class=\"field\" name=\"btnDel\" value=\"-\" type=\"button\" onClick=\"rmObj('fileType"+fileCount+"')\" >&nbsp;";
	newItem +="</div>";
		
	//$("#attView").html(oldHtml+newItem);
	$("#attView").append(newItem);
}

function rmObj(obj){
	$("#"+obj).remove();
}

$(function() 
{
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

	if(form1.state.value == "updateSuccess")
	{
		<%=strJavaScript%>
		window.close();
	}
});

function queryOne(formid)
{	
	form1.state.value = "queryOne";

	if(formid != "")
		form1.formID2.value = formid;
	else
		form1.formID2.value = "";
	
	beforeSubmit();
	form1.submit();
}

function popCON1001()
{
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=850,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();
	
	var nameField=form1.systemType2.value;
	var manyMailID=form1.manyMailID.value;
	
	returnWindow=window.open(getVirtualPath() + "home/popCON1001.jsp?nameField="+nameField+"&manyMailID="+manyMailID,"",prop);
}

function init()
{
	if('<%=obj.getIsShow()%>' == "N")
	{
		form1.systemType2.disabled = "true";
		form1.chooseMail.disabled = "true";
		form1.formID2.disabled = "true";
	}
}

function beforeInit()
{
	if(""!='<%=Common.get(drgFileData)%>'){
	    <%=obj.doSetFileData(drgFileData)%>
	}
}
</script>
</head>
<body onLoad="beforeInit();init();">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" height="100%">
	<tr>
		<td nowrap class="td_form" width="15%">系統別：</td>
		<td nowrap class="td_form_white">
			<select class="field_QRO" name="systemType2"   onchange="queryOne('');">
				<%=View.getOptionCodeKind("SYS", obj.getSystemType2())%>
			</select>
			<input class="field" type="hidden" name="systemTypeTemp" size="60" maxlength="30" value="<%=obj.getSystemTypeTemp()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">表單編號：</td>
		<td nowrap class="td_form_white">
		    <select class="field_QRO" name="formID2"  >
	    		<%=View.getOption("select id,formdName from Con1007Db",obj.getFormID2(),false,1)%>
	    	</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="15%"><font color="red">*</font>主旨：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="title" size="60" maxlength="30" value="<%=obj.getTitle()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form"><font color="red">*</font>收件者：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="mailAddress" size="80" maxlength="100" value="<%=obj.getMailAddress()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">副件：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="ccmailAddress" size="80"  value="<%=obj.getCcmailAddress()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">密件：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="bccmailAddress" size="80"  value="<%=obj.getBccmailAddress()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">信件內容：</td>
		<td nowrap class="td_form_white">		
		    <textarea id="mailBody" name="mailBody" cols="40" rows="40"><%=obj.getMailBody()%></textarea>
		</td>
	</tr>	
	<tr>
  		<td nowrap class="td_form">附件：</td>
  		<td nowrap class="td_form_white">
  		    <%if("".equals(Common.get(drgFileData))){ %>
  		  	<%=View.getPopUpload("field", "filePath", "", "瀏覽 .....", null, true, obj.getSystemTypeTemp())%>
  			<input class="field" name="btnAdd" value="+" type="button" onClick="addAtt('<%=obj.getSystemTypeTemp()%>')">
  			<%}%>
			<div id="attView">
			
			</div>
  		</td>
	</tr>
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:center">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<input type="hidden" name="fileData">
	<input type="hidden" name="popMailID" value="<%=obj.getPopMailID()%>">
	<input type="hidden" name="mailID" value="<%=obj.getMailID()%>">
	<input type="hidden" name="manyMailID" value="<%=manyMailID%>">
	<input class="toolbar_default" type="submit" name="approve1" value="發送信件">
	<input class="toolbar_default" type="button" name="chooseMail" value="選擇信件內容" onclick="popCON1001();">
</td></tr>

</table>
</form>
</body>
</html>

<!--
程式目的：醫療器材臨床試驗核准文件維護作業
程式代號：medin0101f.jsp
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="MEDIN0101" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN0101F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%
if ("queryAll".equals(obj.getState())) {
	if ("false".equals(obj.getQueryAllFlag())){obj.setQueryAllFlag("true"); }
}else if ("queryOne".equals(obj.getState())) {
	obj = (com.kangdainfo.tcbw.view.medin.MEDIN0101F)obj.queryOne();	
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
		obj = (com.kangdainfo.tcbw.view.medin.MEDIN0101F)obj.queryOne();
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
var loadfile = '<%=obj.getFileData()%>';
function checkField(){
	var alertStr = "";
	var temp = "";
	if(form1.state.value == "queryAll"){
		alertStr += checkQuery();
	}else if(form1.state.value=="insert"||form1.state.value=="insertError"||form1.state.value=="update"||form1.state.value=="updateError"){
		alertStr += checkEmpty(form1.applNo, "文號");
		alertStr += checkEmpty(form1.subject, "主旨");
		alertStr += checkEmpty(form1.isStop, "是否使用中");
		$("input[type='text'][name^='filePath']").each(function(){
			if($(this).val()!=""){
				temp += $(this).val() + "|";
			}
		});
		form1.fileData.value = temp;
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
	if(loadfile != ""){
		var tempFile = loadfile.split("|");;
		for(i=0; i<tempFile.length; i++){
			if(tempFile[i] == "")	continue;
			if(i==0)
				addAtt('MEDIN0101F', tempFile[i], false);
			else
				addAtt('MEDIN0101F', tempFile[i], true);
		}
	}else{
		addAtt('MEDIN0101F', '', false);
	}

}

var number = 1;
function addAtt(filekind, filepath, remove){
	var fileCount;
	fileCount = number++;		
	//var oldHtml = $("#attView").html();
	var newItem ="<div id=\"fileType"+fileCount+"\">";
	newItem +="<input id ='filePath"+fileCount+"' class=\"field_RO\" type=\"text\" name=\"filePath"+fileCount+"\" size=\"20\" readonly maxlength=\"300\" value="+filepath+">&nbsp;";
	newItem +="<input class=\"field\" type=\"button\" name=\"btn_filePath\" onclick=\"openUploadWindow('filePath"+fileCount+"','','"+filekind+"');\" value=\"上傳檔案\" >&nbsp;";
	newItem +="<input class=\"field\" type=\"button\" name=\"btn_filePathDownload\" onclick=\"openDownloadWindow(form1.filePath"+fileCount+".value,'"+filekind+"');\" value=\"下載檔案\" >&nbsp;";
	newItem +="<input class=\"field\" name=\"btnAdd\" value=\"+\" type=\"button\" onClick=\"addAtt('"+filekind+"', '', true)\" >&nbsp;";
	if(remove == true)
		newItem +="<input class=\"field\" name=\"btnDel\" value=\"-\" type=\"button\" onClick=\"rmObj('fileType"+fileCount+"')\" >&nbsp;";
	newItem +="</div>";
		
	//$("#attView").html(oldHtml+newItem);
	$("#attView").append(newItem);
}

function rmObj(obj){
	$("#"+obj).remove();
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
		<td nowrap class="queryTDLable">文號：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_applNo" size="50" maxlength="50" value="<%=obj.getQ_applNo()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">主旨：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_subject" size="50" maxlength="20" value="<%=obj.getQ_subject()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">摘要：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_summary" size="50" maxlength="50" value="<%=obj.getQ_summary()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">是否使用中：</td>
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
		<td nowrap class="td_form" width="35%"><font color="red">*</font>文號：</td>
		<td nowrap class="td_form_white" width="65%">
			<input class="field" type="text" name="applNo" size="50" maxlength="50" value="<%=obj.getApplNo()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="35%"><font color="red">*</font>主旨：</td>
		<td nowrap class="td_form_white" width="65%">
			<input class="field" type="text" name="subject" size="50" maxlength="20" value="<%=obj.getSubject()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="35%">摘要：</td>
		<td nowrap class="td_form_white" width="65%">
			<textarea class="field" name="summary" cols="40" rows="3" value="<%=obj.getSummary()%>"><%=obj.getSummary()%></textarea>
		</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form"><font color="red">*</font>是否使用：</td>
	  	<td nowrap class="td_form_white">
	  		<select name="isStop" class="field" type="select">
	    		<%=View.getTextOption("Y;是;N;否;", obj.getIsStop(), 1)%>
	    	</select>	
	  	</td>
	</tr>
	<tr>
	  	<td nowrap class="td_form">附件：</td>
	  	<td nowrap class="td_form_white">
			<div id="attView">
			
			</div>
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
	<input type="hidden" name="fileData" >
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
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">文號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">主旨</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">摘要</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">是否使用中</a></th>
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
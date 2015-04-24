<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="head.jsp" %>
<jsp:include page="secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="DTREEFRAME" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.common.view.sys.ap.TreeEntry">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
obj.setSysid("-11");	

String btnSave = Common.checkGet(request.getParameter("btnSave"));
String exeKind = Common.checkGet(request.getParameter("exeKind"));
String sid = Common.checkGet(request.getParameter("sid"));
String fid = Common.checkGet(request.getParameter("fid"));
String nodeId = Common.checkGet(request.getParameter("nodeId"));
String js = "";

if (!"".equals(Common.get(btnSave))) {
	if ("insert".equals(obj.getState()) || "insertError".equals(obj.getState())) {
		obj.insert();
	}else if ("update".equals(obj.getState()) || "updateError".equals(obj.getState())) {
		obj.update();
	}else if ("delete".equals(obj.getState()) || "deleteError".equals(obj.getState())) {
		obj.delete();
	}
} else {
	if (!"".equals(sid) && Validate.checkInt(sid)) {
		obj.setId(sid);	
	} else {
		obj.setId("");
	}
	if (!"".equals(fid) && Validate.checkInt(fid)) {
		obj.setPid(fid);
	} else {
		obj.setPid("");
	}
	if (!"".equals(exeKind) && Validate.checkAlphaInt(exeKind)) obj.setState(exeKind);
	
	if ("insert".equals(exeKind)) {
		if (!"".equals(sid) && Validate.checkInt(sid)) obj.setPid(sid);
		obj.setSorted("1000");
		obj.setId("");
	} else if ("update".equals(exeKind)) {
		obj = (com.kangdainfo.common.view.sys.ap.TreeEntry) obj.queryOne();
		obj.setState("update");
	} else if ("delete".equals(exeKind)) {
		obj.delete();
	} else {
		obj = (com.kangdainfo.common.view.sys.ap.TreeEntry) obj.queryOne();		
		obj.setState("update");		
	}
}
%>
<html>
<head>
<title>dTree</title>
<meta http-equiv="Expires" content="-1"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../js/default.css" type="text/css">
<script language="javascript" src="../js/validate.js"></script>
<script language="javascript" src="../js/function.js"></script>
<script language="javascript">
	var popWin;
	var popWinFlag=false;
	
	function checkField(){
		var alertStr="";

		alertStr += checkEmpty(form1.pid,"父節點");
		//alertStr += checkAlphaInt(form1.pid,"父節點");		
		//alertStr += checkEmpty(form1.id,"節點代碼");
		alertStr += checkInt(form1.id,"節點代碼");
		alertStr += checkEmpty(form1.name,"節點名稱");
		
		if (form1.sorted.value!="") alertStr += checkInt(form1.sorted,"排序" );	
		if(alertStr.length!=0){ alert(alertStr); return false; }
		else return checkSpecialChar(form1);		
	}
	
	function popNodeSelectForm() { 	
		popWin = window.open("dTreeNodeSelect.jsp?popId=pid&popName=pname",'dTreeNodeSelect','scrollbars=1, resizable=yes, status=yes, toolbar=no,menubar=no,width=350,height=200');
	}
		
	function popWinClose() {
		popWin.close();
	}

	function beforeInit() {
		if(<%="-1".equals(obj.getPid())%>){
			document.all.item('btnSave').disabled=true;
		}		
		form1.name.focus();
	}
	function init() {
		var s = "<%=obj.getState()%>";
		if (s=="insertSuccess" || s=="updateSuccess" || s=="deleteSuccess" || s=="deleteError") {	
			if (isObj(opener) && (s=="deleteSuccess" || s=="deleteError")) {
				opener.reloadTree('<%=obj.getPid()%>');
			} else if (s=="insertSuccess" || s=="updateSuccess") {
				form1.state.value = "update";
				if (s=="insertSuccess") form1.state.value = "insert";				
				window.parent.frames['dTreeManage'].location.href = "dTreeManage.jsp?openNodeID=<%=obj.getPid()%>";
				/**
				for (var i=0; i<window.parent.frames.length; i++) {
					alert(window.parent.frames[i].name);
					if (window.parent.frames[i].name=='dTreeManage') {
					}
				}
				**/
			}
		}		
	}
		
	function getNodeName(nId) {
		if(nId!=null&&nId.length>0){
		var x = getRemoteData("dTreeGetNode.jsp",nId);
		form1.pname.value=x;	
		}
	}



</script>
</head>

<body topmargin="0" onload="beforeInit();showErrorMsg('<%=obj.getErrorMsg()%>');init();">
<form id="form1" name="form1" method="post" onsubmit="return checkField()">
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td nowrap class="td_lable">
功能選單維護作業
</td></tr>
<tr><td nowrap class="bg">
	<table class="table_form" width="100%" height="100%">
	<tr>
	<td nowrap class="td_form"><font color="red">*</font>父節點：</td>
	<td nowrap class="td_form_white"><input name="pid" type="text" class="field_P" id="pid" value="<%=obj.getPid()%>" autocomplete="off" onchange="getNodeName(this.value);" size="10" />
	  <input name="btnSelectPid" type="button" id="btnSelectPid" value="..." class="field" onclick="popNodeSelectForm();" /> 
	  <input name="pname" id="pname" type="text" class="field_RO" value="<%=obj.getPname()%>" /></td>
	</tr>
	<tr>
		<td nowrap class="td_form"><font color="red">*</font>節點代碼：</td>
		<td nowrap class="td_form_white">
			[<input name="id" type="text" class="field_PRO" id="id" value="<%=obj.getId()%>" size="10" maxlength="20" readonly="true">]　
			排序：<input name="sorted" type="text" class="field" id="sorted" value="<%=obj.getSorted()%>" size="9" maxlength="9" />		</td>
		</tr>
	<tr>
	  <td nowrap class="td_form"><font color="red">*</font>節點名稱：</td>
	  <td nowrap class="td_form_white">
			<input name="name" type="text" class="field" id="name" value="<%=obj.getName()%>" size="40" maxlength="50">		</td>
	  </tr>
	<tr>
	  <td nowrap class="td_form">URL：</td>
	  <td nowrap class="td_form_white"><input name="url" type="text" class="field" id="url" value="<%=obj.getUrl()%>" size="40" maxlength="120" /></td>
	</tr>
	<tr>
	  <td nowrap class="td_form">Title：</td>
	  <td nowrap class="td_form_white">
			<input name="title" type="text" class="field" id="title" value="<%=obj.getTitle()%>" size="40" maxlength="100">		</td>
	</tr>
	<tr>
	<td nowrap class="td_form">Target：</td>
	<td nowrap class="td_form_white"><input name="target" type="text" class="field_P" id="target" value="<%=obj.getTarget()%>" size="20" maxlength="20" /></td>
	</tr>
	<tr>
	<td nowrap class="td_form">Icon：</td>
	<td nowrap class="td_form_white"><input name="icon" type="text" class="field_P" id="icon" value="<%=obj.getIcon()%>" size="40" maxlength="50" /></td>
	</tr>
	<tr>
	<td nowrap class="td_form">IconOpen：</td>
	<td nowrap class="td_form_white"><input name="iconOpen" type="text" class="field_P" id="iconOpen" value="<%=obj.getIconOpen()%>" size="40" maxlength="50" /></td>
	</tr>
	<tr>
	<td nowrap class="td_form">Opened：</td>
	<td nowrap class="td_form_white"><input name="opened" type="text" class="field_P" id="opened" value="<%=obj.getOpened()%>" size="1" maxlength="1" /></td>
	</tr>
	<tr>
	<td nowrap class="td_form">功能代號：</td>
	<td nowrap class="td_form_white"><input name="btnRead" type="text" class="field_P" id="btnRead" value="<%=obj.getBtnRead()%>" size="40" maxlength="50" /></td>
	</tr>
	<tr>
	<td nowrap class="td_form">btnWrite：</td>
	<td nowrap class="td_form_white"><input name="btnWrite" type="text" class="field_P" id="btnWrite" value="<%=obj.getBtnWrite()%>" size="40" maxlength="50" /></td>
	</tr>
	<tr>
	<td nowrap class="td_form">件數統計：</td>
	<td nowrap class="td_form_white"><input name="code" type="text" class="field" id="code" value="<%=obj.getCode()%>" size="40" maxlength="50" /></td>
	</tr>
	</table>
</td>
</tr>
<!--Toolbar區============================================================-->
<tr><td nowrap class="bg" style="text-align:center">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="oldid" value="<%=obj.getOldid()%>">
<span id="spanToolbar">&nbsp;|
<span id="spanInsert">
<input class="toolbar_default" type="submit" name="btnSave"  value="存　檔">&nbsp;|
</span>
</span>	
</td></tr>
</table>
</form>
</body>

</html>

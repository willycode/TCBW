<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="SYSCA002F" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.common.view.sys.ca.SYSCA002F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
String btnSave = request.getParameter("btnSave");
String exeKind = Common.checkGet(request.getParameter("exeKind"));
String sid = Common.get(request.getParameter("sid"));
String fid = Common.get(request.getParameter("fid"));
String nodeId = Common.get(request.getParameter("nodeId"));
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
	obj.setId(sid);
	obj.setPid(fid);
	obj.setState(exeKind);
	
	if ("insert".equals(exeKind)) {
		obj.setPid(sid);
		if (sid.equals("-97")) obj.setPname("機關單位");
		else {	
			obj = (com.kangdainfo.common.view.sys.ca.SYSCA002F) obj.getParentInfo(sid);
		}
		obj.setId("");
	} else if ("update".equals(exeKind)) {		
		obj = (com.kangdainfo.common.view.sys.ca.SYSCA002F) obj.queryOne();
		obj.setState("update");
	} else if ("delete".equals(exeKind)) {
		obj.delete();
		obj.setErrorMsg("");
	} else {
		if (sid.indexOf('-')!=-1) {
			out.write(Common.MSG_DTREE_LEVEL_LIMIT_FOR_EDIT);
			return ;
		}
		obj = (com.kangdainfo.common.view.sys.ca.SYSCA002F) obj.queryOne();		
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
<link rel="stylesheet" href="../../js/default.css?1=ss" type="text/css">
<script language="javascript" src="../../js/validate.js"></script>
<script language="javascript" src="../../js/function.js"></script>
<script language="javascript" src="../../js/toolbar_mini.js"></script>
<script language="javascript" src="../../js/ZhNumUtil.js"></script>
<script language="javascript">
	var popWin;
	var popWinFlag=false;
	
	function checkField(){
		var alertStr="";

		alertStr += checkEmpty(form1.pid,"上層單位");
		alertStr += checkEmpty(form1.departmentCode,"單位代碼");
		alertStr += checkAlphaInt(form1.departmentCode,"單位代碼");
		alertStr += checkEmpty(form1.department,"單位名稱");	
		alertStr += checkEmpty(form1.fullCode,"全碼");	
		alertStr += checkEmpty(form1.fullName,"全名");	
		alertStr += checkEmpty(form1.shortCode,"簡碼");			
		alertStr += checkEmail(form1.email,"電子信箱");	
		form1.editID.value = "<%=User.getUserName()%>";
		if(!checkLength(form1.description,1000)) return false;
		if(alertStr.length!=0){ alert(alertStr); return false; }
		else return checkSpecialChar(form1);		
	}
	
	function popNodeSelectForm() { 	
		popWin = window.open("deptNodeSelect.jsp?popId=pid&popName=pname",'deptNodeSelect','scrollbars=1, resizable=yes, status=yes, toolbar=no,menubar=no,width=350,height=200');
	}
		
	function popWinClose() {
		popWin.close();
	}
	
	function init() {	
		var s = "<%=obj.getState()%>";
		switch (s) {
			case "deleteSuccess" :
				if (isObj(opener)) {
					opener.reloadTree('<%=obj.getPid()%>','刪除完成');
					opener.reloadForm();
				}
				break;
			case "insertSuccess":
			case "updateSuccess":
			case "update" :
				form1.state.value = "update";
				setFormItem("btnSelectPid","R");
				setUnpkOpen();
				if (s=="insertSuccess" || s=="updateSuccess") 
					window.parent.frames['dTreeManage'].location.href = "deptManage.jsp?openNodeID=<%=obj.getPid()%>";
				break;
		}
		if ("<%=exeKind%>"=="insert") form1.departmentCode.focus();				
		
		changeDeptCodeLength();
		
		if (form1.parentLevel.value=="4") {
			alert("<%=Common.MSG_DTREE_LEVEL_LIMIT_FOR_ADD%>");
			setAllReadonly();
		}
	}
		
	function genFullCodeFullName() {
		if (form1.departmentCode.value!="") form1.departmentCode.value = lpad(form1.departmentCode.value,getDeptCodeLenFromParent(form1.parentLevel.value),'0');
		form1.fullCode.value = getDeptFullCode(form1.parentFullCode.value,form1.parentLevel.value,form1.departmentCode.value);
		form1.shortCode.value = getDeptShortCode(form1.parentFullCode.value,form1.parentLevel.value,form1.departmentCode.value);
		if (form1.parentFullName.value!="") {
			form1.fullName.value = form1.parentFullName.value + "-" + form1.department.value;		
		} else form1.fullName.value = form1.department.value;
	}
		
	function getNodeName(nId) {
		var x = getRemoteData("deptGetNode.jsp",nId);
		form1.pname.value=x;	
	}
	
	function changeDeptCodeLength() {
		var level = form1.parentLevel.value;
		var obj = form1.departmentCode;
		try {
			switch(level) {
				case "0": setLength(obj,2); break;
				case "1": setLength(obj,2); break;
				case "2": setLength(obj,2); break;
				default : setLength(obj,2); break;
			}
		} catch(e) {alert(e);}	
		/**		
		var level = form1.parentLevel.value;
		var obj = form1.departmentCode;
		try {
			switch(level) {
				case "0": setLength(obj,3); break;
				case "1": setLength(obj,4); break;
				case "2": setLength(obj,4); break;
				default : setLength(obj,2); break;
			}
		} catch(e) {alert(e)}
		**/		
	}	

	function checkLength(object,leng){
		if(object!=null && leng!=null && object.value.length>leng){
			alert("單元簡介字數超過"+leng+",請修正!!");
			if(object.value.length>0)
				object.focus();
			return false;
		}
		return true;
	}
</script>
</head>

<body topmargin="0" onLoad="init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off" >
<table width="100%" cellspacing="0" cellpadding="0">
<tr>
  <td nowrap class="td_lable">
機關代碼維護作業
</td>
</tr>
<tr><td nowrap class="bg">
	<table class="table_form" width="100%" height="100%">
	<tr>
	<td nowrap class="td_form"><font color="red">*</font>上層單位：</td>
	<td nowrap class="td_form_white" colspan="3"><input name="pid" type="hidden" class="field_EP" id="pid" value="<%=obj.getPid()%>" autocomplete="off" onChange="getNodeName(this.value);" size="10" />
	  <input name="pname" id="pname" type="text" class="field_RO" value="<%=obj.getPname()%>" />
	  <input name="btnSelectPid" type="button" id="btnSelectPid" value="..." class="toolbar_default" onClick="popNodeSelectForm();" />	</td>
	</tr>
	<tr>
	  <td nowrap class="td_form"><font color="red">*</font>單位代碼：</td>
	  <td nowrap class="td_form_white">
			<input class="field_EP" type="text" name="departmentCode" size="20" maxlength="2" value="<%=obj.getDepartmentCode()%>" onChange="genFullCodeFullName();" />		</td>
	  <td nowrap class="td_form"><font color="red">*</font>全碼：</td>
	  <td nowrap class="td_form_white">
			<input class="field_RO" type="text" name="fullCode" size="10" maxlength="10" value="<%=obj.getFullCode()%>" />		</td>
	</tr>
	<tr>
		<td nowrap class="td_form"><font color="red">*</font>單位名稱：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="department" size="20" maxlength="50" value="<%=obj.getDepartment()%>" onChange="genFullCodeFullName();" />		</td>	

	    <td nowrap class="td_form"><font color="red">*</font>簡碼：</td>
	    <td nowrap class="td_form_white">
			<input class="field_E" type="text" name="shortCode" size="10" maxlength="10" value="<%=obj.getShortCode()%>" />		</td>
	</tr>
	<tr>
	<td nowrap class="td_form">單位簡稱：</td>
	<td nowrap class="td_form_white"><input class="field" type="text" name="shortName" size="20" maxlength="50" value="<%=obj.getShortName()%>" onChange="genFullCodeFullName();" /></td>
	<td nowrap class="td_form">電話：</td>
	<td nowrap class="td_form_white">
			<input class="field" type="text" name="phone" size="10" maxlength="50" value="<%=obj.getPhone()%>" />		</td>
	</tr>
	<tr>
	  <td nowrap class="td_form"><font color="red">*</font>單位全名：</td>
	  <td nowrap class="td_form_white">
			<input class="field_RO" type="text" name="fullName" size="20" maxlength="250" value="<%=obj.getFullName()%>" />	  </td>
		<td nowrap class="td_form">傳真：</td>
		<td nowrap class="td_form_white">
			<input class="field" type="text" name="fax" size="10" maxlength="50" value="<%=obj.getFax()%>" />		</td>
	</tr>
	<tr>
	  <td nowrap class="td_form">主管姓名：</td>
	  <td nowrap class="td_form_white">
	    <input class="field" type="text" name="chiefName" size="20" maxlength="50" value="<%=obj.getChiefName()%>" />		</td>
	  <td nowrap class="td_form">職稱：</td>
	  <td nowrap class="td_form_white">
	    <input class="field" type="text" name="chiefTitle" size="10" maxlength="50" value="<%=obj.getChiefTitle()%>" />		</td>
	  </tr>
	<tr>
	  <td nowrap class="td_form">電子信箱：</td>
	  <td nowrap class="td_form_white">
			<input class="field" type="text" name="email" size="20" maxlength="255" value="<%=obj.getEmail()%>" />		</td>
	  <td nowrap class="td_form">網址：</td>
	  <td nowrap class="td_form_white">
			<input class="field" type="text" name="homepage" size="10" maxlength="255" value="<%=obj.getHomepage()%>" />		</td>
	</tr>
	<tr>
	  <td nowrap class="td_form">單位地址：</td>
	  <td nowrap colspan="3" class="td_form_white">
			<select class="field" name="zip">
			  <%=View.getOption("select codeId, codeId||'-'||codeName from CommonCode where commonCodeKind.codeKindId='LOC' order by codeId",obj.getZip())%>
			  </select>
			<input class="field" type="text" name="address" size="40" maxlength="255" value="<%=obj.getAddress()%>" />		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">單位簡介：</td>
		<td nowrap colspan="3" class="td_form_white">
			<textarea name="description" cols="50" rows="4" class="field" onblur="checkLength(this,1000)"><%=obj.getDescription()%></textarea>		</td>
	</tr>
	<tr>
	  <td nowrap class="td_form">異動資訊：</td>
	  <td nowrap colspan="3" class="td_form_white"><input class="field_RO" type="text" name="editID" size="10" value="<%=obj.getEditID()%>" />
	    /
	    <input class="field_RO" type="text" name="editDate" size="7" value="<%=obj.getEditDate()%>" /></td>
	</tr>
	</table>
	</td>
</tr>
<!--Toolbar區============================================================-->
<tr><td nowrap class="bg" style="text-align:center">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="oldid" value="<%=obj.getOldid()%>">
	<input type="hidden" name="id" value="<%=obj.getId()%>">
	<input type="hidden" name="level" value="<%=obj.getLevel()%>">
	<input type="hidden" name="oldParentId" value="<%=obj.getOldParentId()%>">
	<input type="hidden" name="parentLevel" value="<%=obj.getParentLevel()%>">
	<input type="hidden" name="parentFullCode" value="<%=obj.getParentFullCode()%>">
	<input type="hidden" name="parentFullName" value="<%=obj.getParentFullName()%>">	
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

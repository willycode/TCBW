<!--
程式目的：郵遞區號匯入作業
程式代號：conse0004f.jsp
程式日期：102.07.24
程式作者：reyes.chen
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="HFRIN0301" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.hfrin.HFRIN0301F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%

if (obj.getState().equals("importHfr") && !"".equals(obj.getQ_filePath())) {
	try{
		obj.importHfrin();
		if("".equals(obj.getErrorMsg())){
			obj.setErrorMsg("匯入完成 !");
		}
	}catch(Exception e){
		e.printStackTrace();
		obj.setErrorMsg("匯入資料發生未預期錯誤，請連絡管理人員 !");
	}
}

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
<script type="text/javascript">
function checkField(){
	var alertStr = "";
	if(form1.q_filePath.value == ""){
        alertStr += "您必需指定檔案";
	} else {
		var extPos = form1.q_filePath.value.lastIndexOf(".");
		var ext = form1.q_filePath.value.toLowerCase().substring(extPos+1);
		if (extPos == -1) {
			alertStr += "無法判斷您上傳的檔案格式，請檢查檔案是否有副檔名並重新輸入!";
		} else if (ext!='xls'){
			alertStr += "上傳的檔案格式必須是xls格式，請重新輸入!";	    		
		}	
	}				
	if(alertStr.length != 0){ 
		form1.state.value = "";
		alert(alertStr);
		return false;
	}
	
	form1.state.value = "importHfr";
	form1.submit();
	return true;
}
</script>
</head>
<body onLoad="showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">
<div id="formContainer">
	<table class="table_form" width="99%" height="100%">
	<tr>
  		<td nowrap class="td_form"><font color="red">*</font>委員會會期檔案：</td>
  		<td nowrap class="td_form_white">
  			<%=View.getPopUpload("field", "q_filePath", "", true)%>
  			<input type="hidden" name="state" value="">	
        	<input class="toolbar_default" followPK="false" type="submit" name="btnImport" value="匯入">
        	<input type="hidden" name="userID" value="<%=User.getId()%>">
  		</td>
	</tr>
	</table>
</div>
</table>
</form>
</body>
</html>

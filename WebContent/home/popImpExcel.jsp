<!--
程式目的：EXCEL轉入作業 
程式代號：popImpExcel
程式日期：1021217
程式作者：yuwen
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.util.PopImportExcel">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
obj.setFromType(ESAPI.encoder().encodeForHTML(request.getParameter("formType")));
String fromName = "";
if("DRGEX0101".equals(obj.getFromType())){
	fromName = "藥品不良品資料轉入作業";
}else if("DRGEX0301".equals(obj.getFromType())){
	fromName = "藥品療效不等資料轉入作業";
}else if("MEDEX0101".equals(obj.getFromType())){
	fromName = "醫療器材不良事件資料轉入作業";
}else if("MEDEX5101".equals(obj.getFromType())){
	fromName = "醫療器材臨床試驗不良事件資料轉入作業";
}else if("VDRG0301".equals(obj.getFromType())){
	fromName = "國內外藥品品質警訊資料轉入作業";
}

String actionResult = "";
String actionMessage = "";
String btnSubmit = ESAPI.encoder().encodeForHTML(request.getParameter("btnSubmit"));
if (btnSubmit!=null && "doTransferProcess".equals(obj.getState())) {
	try {
		obj.doImportProcess();
		actionResult = obj.getState();
	} catch (Exception e) {
		e.printStackTrace();
		actionResult = "doTransferFail";
		if (e.getMessage()!=null && e.getMessage().length()<300) {
			actionMessage = Common.escapeJavaScript(e.getMessage());
		} else {
			actionMessage = "發生未預期的錯誤,承轉失敗!請重新執行,若問題持續,請洽詢系統管理者或相關承辦人員！";	
		}
	}	
}
%>

<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../js/default.css" type="text/css"/>
<script type="text/javascript" src="../js/json.js"></script>
<script type="text/javascript" src="../js/tablesoft.js"></script>
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script language="javascript">
var actionResult = '<%=actionResult%>';
var actionMessage = '<%=actionMessage%>';

function checkField(){
	var alertStr="";
	alertStr += checkEmpty(form1.srcFilePath,"來源檔案");
	if(alertStr.length!=0){ alert(alertStr); return false; }

	var extPos = form1.srcFilePath.value.lastIndexOf(".");
	var ext = form1.srcFilePath.value.toLowerCase().substring(extPos+1);	
	if (ext=='xls') {
	    form1.state.value = "doTransferProcess";
    	var prop = "";    
    	prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=yes,scrollbars=yes,resizable=yes,';
    	prop=prop+'width=400,';
    	prop=prop+'height=110';	           
    	var moshe=window.open("","exp",prop); 
    	moshe.document.write('<html>');
    	moshe.document.write('<head>');
    	moshe.document.write('<meta http-equiv=Content-Type content=text/html; charset=UTF-8>');			
    	moshe.document.write('<title>.:: ');
    	moshe.document.write('<%=fromName%>');
    	moshe.document.write(' ::.</title>');
    	moshe.document.write('</head>');
    	moshe.document.write('<body topmargin="10" leftmargin="10" marginwidth="0" marginheight="0">\n');	
    	moshe.document.write('<br><br><div align="center"><font color="#CC0000">資料匯入中，請稍候...</font></div>'); 
    	moshe.document.write('</body></html>');	   
    	return true; 		
	} else {
		alert('來源檔需為Excel檔');
		return false;
	}
				
      
}

function init() {
	setDisplayItem('spanQueryAll,spanInsert,spanDuplicate,spanUpdate,spanDelete,spanClear,spanConfirm,spanListPrint,spanListHidden','H');
}

function checkAfterAction(){    
    switch(actionResult){
    case 'noAction':
        break;
    case 'doTransferSuccess':
    	var prop = "";    
	    prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=yes,scrollbars=yes,resizable=yes,';
	    prop=prop+'width=400,';
	    prop=prop+'height=110';	           
		var moshe=window.open("","exp",prop); 
		moshe.close();
        break;
    case 'doTransferFail':
		var moshe=window.open("","exp",prop); 
		moshe.close();    
        alert(actionMessage);
        break;
    }
}



</script>
</head>

<body topmargin="0" onLoad="whatButtonFireEvent('<%=obj.getState()%>');checkAfterAction();init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<br><br>
<table width="90%" align="center" cellpadding="0" cellspacing="0">
<!--Form區============================================================-->
<tr><td class="bg">
	<div id="formContainer" style="height:auto">
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_form">
	<tr>
	  <td align="center"><%=fromName%></td>
	</tr>
	<tr><td>
		
	<table width="100%">
	<tr>
	  <td class="td_form">來源檔(XLS)：</td>
	  <td colspan="2" class="td_form_white"><%=View.getPopUpload("field_Q","srcFilePath",obj.getSrcFilePath())%></td>
	</tr>	  	  
	</table>
	</td></tr>
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr>
	<td class="bg" style="text-align:center">
		<input type="hidden" name="state" value="<%=obj.getState()%>">
		<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
		<input type="hidden" name="editID" value="<%=User.getUserName()%>">
		<input type="hidden" name="fromType" value="<%=obj.getFromType()%>">
		<jsp:include page="../home/toolbar.jsp" />	
		<input class="toolbar_default" type="submit" name="btnSubmit" value="執行匯入作業" >
	</td>
</tr>
</table>
<%=obj.getJs()%>
</form>
</body>
</html>
<!--
程式目的：機關代碼匯入作業 (由人事行政局PEMIS2000)
程式代號：sysca002f_cpa
程式日期：0980811
程式作者：clive.chang
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.common.view.sys.ca.SYSCA002F_CPA">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
String actionResult = "";
String actionMessage = "";
String btnSubmit = request.getParameter("btnSubmit");
if (btnSubmit!=null && "doTransferProcess".equals(obj.getState())) {
	try {
		System.out.println(obj.getSrcFilePath());
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
<%@ include file="../../home/meta.jsp" %>
<script language="javascript">
var actionResult = '<%=actionResult%>';
var actionMessage = '<%=actionMessage%>';

function checkField(){
	var alertStr="";
	alertStr += checkEmpty(form1.srcFilePath,"來源檔案");
	if(alertStr.length!=0){ alert(alertStr); return false; }

	var extPos = form1.srcFilePath.value.lastIndexOf(".");
	var ext = form1.srcFilePath.value.toLowerCase().substring(extPos+1);	
	if (ext=='xls' || ext == 'txt') {
	    form1.state.value = "doTransferProcess"
    	var prop = "";    
    	prop=prop+'toolbar=no;location=no,directories=no,menubar=no,status=yes,scrollbars=yes,resizable=yes,';
    	prop=prop+'width=400,';
    	prop=prop+'height=110';	           
    	var moshe=window.open("","exp",prop); 
    	moshe.document.write('<html>');
    	moshe.document.write('<head>');
    	moshe.document.write('<meta http-equiv=Content-Type content=text/html; charset=UTF-8>');			
    	moshe.document.write('<title>.:: 代碼資料匯入作業 ::.</title>');
    	moshe.document.write('</head>');
    	moshe.document.write('<body topmargin="10" leftmargin="10" marginwidth="0" marginheight="0">\n');	
    	moshe.document.write('<br><br><div align="center"><font color="#CC0000">資料匯入中，請稍候...</font></div>'); 
    	moshe.document.write('</body></html>');	   
    	return true; 		
	} else {
		alert('來源檔需為純文字或Excel檔');
		return false;
	}
				
      
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

<body topmargin="0" onLoad="checkAfterAction();">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<br><br>
<table width="90%" align="center" cellpadding="0" cellspacing="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_form">
	<tr>
	  <td nowrap align="center">機關代碼(PEMIS2000)轉入作業</td>
	</tr>
	<tr><td nowrap>
		
	<table width="100%">
	<tr>
	  <td nowrap class="td_form">來源檔(TXT或XLS)：</td>
	  <td nowrap colspan="2" class="td_form_white"><%=View.getPopUpload("field","srcFilePath",obj.getSrcFilePath())%></td>
	</tr>	  	  
	</table>
	</td></tr>
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr>
	<td nowrap class="bg" style="text-align:center">
		<input type="hidden" name="state" value="<%=obj.getState()%>">
		<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
		<input type="hidden" name="editID" value="<%=User.getUserName()%>">			
		<input class="toolbar_default" type="submit" name="btnSubmit" value="執行匯入作業" >

	</td>
</tr>

</table>
<%=obj.getJs()%>
</form>
</body>
</html>
<!--
程式目的：醫事機構資料匯入作業
程式代號：conse0009_3f
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<% 
String downloadLocPath = "";
String downloadInpPath = "";
%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.conse.CONSE0009_3F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<jsp:useBean id="objList1" scope="page" class="java.util.ArrayList"/>

<%@ page import="java.io.*" %>
<%
	String actionResult = "";
	String actionMessage = "";
	String btnSubmit = request.getParameter("btnSubmit");
	String selectImport = request.getParameter("selectImport");
	if (btnSubmit != null && "doTransferProcess".equals(obj.getState())) {
		try {
			if(!"".equals(obj.getSelectImport())) {
				
			
			obj.setselect(selectImport);
			obj.doImportProcess();
			actionResult = obj.getState();
			
			
			java.util.ArrayList<String[]> arrList = new java.util.ArrayList<String[]>();
			
			//差異資料不匯入
			
			if("2".equals(obj.getSelectImport()))
			{
				String filePath = Datetime.getYYYMMDD() + Datetime.getHHMMSS();
				ServletContext context = getServletContext();
				String filestoreLocation = context.getInitParameter("filestoreLocation");
				File MedData = new File(filestoreLocation + File.separator + filePath);
				MedData.mkdir();
				
				File LocTxt = new File(MedData,File.separator+"1.txt");
				File importTxt = new File(MedData,File.separator+"2.txt");
				
				downloadLocPath = filePath + ":;:" + LocTxt.getName();
				downloadInpPath = filePath + ":;:" + importTxt.getName();
				
				System.out.println("a: " + downloadLocPath);
				System.out.println("b: " + downloadInpPath);
								
		        if(!LocTxt.exists() && !importTxt.exists()){
		        	
		        	LocTxt.createNewFile();
		        	
		        	FileWriter locDataFile = null;
		        	BufferedWriter locInput = null;
		        	FileWriter impDataFile = null;
		        	BufferedWriter impInput = null;
		        	try{
			        	locDataFile = new FileWriter(LocTxt);
			        	locInput = new BufferedWriter(locDataFile);
			        	
			        	importTxt.createNewFile();
			        	impDataFile = new FileWriter(importTxt);
						impInput = new BufferedWriter(impDataFile);
						
						String lodDiffData = "分局別,醫事機構代碼,醫事機構名稱,機構地址,電話區域號碼 ,電話號碼,特約類別,型態別,醫事機構種類,終止合約或歇業日期\n";
						String impDiffData = "分局別,醫事機構代碼,醫事機構名稱,機構地址,電話區域號碼 ,電話號碼,特約類別,型態別,醫事機構種類,終止合約或歇業日期\n";
						
						objList = obj.getLocalArrayList();
						for(Object dtlObj : objList) {				
							Object[] dtl = (Object[]) dtlObj;
							for(int j = 0; j < dtl.length; j++) {
								if(j == dtl.length) {
									lodDiffData += dtl[j];
								} else {
									lodDiffData += dtl[j] + ",";
								}
							}
							lodDiffData += "\n";
						}
						
						locInput.write(lodDiffData);
						
						objList1 = obj.getInputArrayList();
						for(Object dtlObj : objList1) {				
							Object[] dtl = (Object[]) dtlObj;
							for(int j = 0; j < dtl.length; j++) {
								if(j == dtl.length) {
									impDiffData += dtl[j];
								} else {
									impDiffData += dtl[j] + ",";
								}
							}
							impDiffData += "\n";
						}
						System.out.println("DIFFDATA1: " + impDiffData);
						impInput.write(impDiffData);
		        	}catch(IOException ioe){
		        		throw ioe;
		        	}finally{
		        		locDataFile.close();
		        		impDataFile.close();
		        		locInput.close();
						impInput.close();	
		        	}
					
			        //response.setContentType("text/html; charset=UTF-8");
			    	//response.setContentType("application/text");
			    	//response.setHeader("Content-disposition","attachment;filename="+System.getProperty("java.io.tmpdir")+"\\差異資料(匯入).txt");
		        }else{
		        	System.out.println("檔案存在!");
		        }					
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
			actionResult = "doTransferFail";
			if (e.getMessage() != null && e.getMessage().length() < 300) {
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
	if(form1.selectImport.value != ""){	
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
    	moshe.document.write('<title>.:: 匯入作業 ::.</title>');
    	moshe.document.write('</head>');
    	moshe.document.write('<body topmargin="10" leftmargin="10" marginwidth="0" marginheight="0">\n');	
    	moshe.document.write('<br><br><div align="center"><font color="#CC0000">資料匯入中，請稍候...</font></div>'); 
    	moshe.document.write('</body></html>');
    	return true; 		
	} else {
		alert('來源檔需為純文字檔');
		return false;
	}
	} else {
		alert("錯誤!!匯入方式未選擇");
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
    
    case 'doDownload':
    	var moshe=window.open("","exp",prop);
		moshe.close();    
        alert("下載完成");
        break;
    
    }
}

function isSelect() {
	if(""==form1.selectImport.value){
		alert("請選擇匯入方式");
	}
	
}

function init() {
	form1.download.disabled = true;
	form1.download1.disabled = true;
	if("2"==form1.selectImport.value) {
		form1.download.disabled = false;
		form1.download1.disabled = false;
	}
}

</script>
</head>

<body topmargin="0" onLoad="checkAfterAction();init();">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">

<table width="90%" align="center" cellpadding="0" cellspacing="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="99%" height="100%" >
		<tr>
		  <td nowrap align="center">醫事機構資料匯入作業</td>
		</tr>
		<tr>
			<td nowrap>	
			<table width="100%">
			  <tr>
			    <td nowrap class="td_form" width="35%">匯入方式：</td>
				<td nowrap colspan="2" class="td_form_white">
					<select class="field" name="selectImport">
						<option value="">請選擇</option>
						<option value="1" <%if("1".equals(obj.getSelectImport())) out.print("selected"); %>>差異資料覆蓋匯入</option>
						<option value="2" <%if("2".equals(obj.getSelectImport())) out.print("selected"); %>>差異資料不匯入</option>
					</select>					
				</td>
			  </tr>
			</table>
			</td>
		</tr>				
		<tr>
	  		<td nowrap>	
				<table width="100%">
				<tr>
					<td nowrap class="td_form" width="35%">匯入作業：</td>
					<td nowrap colspan="2" class="td_form_white">
						<%=View.getPopUpload("field", "srcFilePath",obj.getSrcFilePath())%>
					</td>
				</tr>
				</table>
			</td>
		</tr>
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr>
	<td nowrap class="bg" style="text-align:center">
		<input type="hidden" name="state" value="<%=obj.getState()%>">
		<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
		<input type="hidden" name="editID" value="<%=User.getUserName()%>">			
		<input class="toolbar_default" type="submit" name="btnSubmit" value="執行匯入作業" onclick="isSelect();">
		<input type="hidden" name="stateDownload" value="<%=obj.getSelectImport()%>">
		<input class="toolbar_default" type="button" name="download" value="下載資料庫存在之差異資料" onclick="openDownloadWindow('<%=downloadLocPath%>','','1');">
		<input class="toolbar_default" type="button" name="download1" value="下載匯入之差異資料" onclick="openDownloadWindow('<%=downloadInpPath%>','','2');">
	</td>
</tr>

</table>
<%=obj.getimportMsg()%>
<%=obj.getupdateMsg()%>
<%=obj.geterrMsg()%>
</form>
</body>
</html>
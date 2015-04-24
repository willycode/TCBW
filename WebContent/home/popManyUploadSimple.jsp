<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="com.kangdainfo.common.util.filestore.*" %>
<%@ page import="com.oreilly.servlet.multipart.*" %>
<%@ page import="com.oreilly.servlet.*" %>
<%@page import="org.apache.commons.io.FileUtils"%>
<%@ include file="../home/head.jsp" %>
<%
String fileKind = Common.get(request.getParameter("fileKind"));
String systemType = Common.get(request.getParameter("systemType"));
String uploadId = Common.get(request.getParameter("uploadId"));
String dbName = Common.get(request.getParameter("dbName"));

String doClose = Common.get(request.getParameter("doClose"));

String filestoreAllowedExtList = org.apache.commons.lang.StringEscapeUtils.escapeHtml(application.getInitParameter("filestoreAllowedExtList"));

%>
<html>
<head>
<meta http-equiv="Expires" content="-1"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="<%="ccmp".equals(request.getParameter("cssType"))?"../js/default_ccmp.css":"../js/default.css"%>" type="text/css"/>
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript" src="../js/jquery.js"></script>

<script type="text/javascript">

function checkField()
{
	var sFlag = true;
	
	var attFiles=document.getElementsByName("attRowId");	
	
	if (attFiles!=null && attFiles.length>0) 
	{
		var i = 0;		
		var allowExt = "<%=filestoreAllowedExtList%>";
		for (i=0; i<attFiles.length; i++) 
		{			
			var rid = attFiles[i].value;
			if (rid!='0' && isObj(document.all.item("upload" +rid))) {
				var id = document.all.item("upload" + rid).value;				
				if (id!='') {
			    	var extPos = id.lastIndexOf(".");
			    	var ext = id.toLowerCase().substring(extPos+1);
			    	if (extPos==-1) {
			    		alert("無法判斷您上傳的檔案格式，請檢查檔案是否有副檔名並重新輸入!");
			    		return false;
			    	}
			    	if (parse(allowExt).length>0) {
			    		if (allowExt.search(ext)== -1 ) {
			    			alert("上傳的檔案格式必須是" + allowExt + "，請重新輸入!");
			    			return false;
			    		}
			    	}
			    	sFlag = false;			
				}					
			}
	
		}		
	}
	if (sFlag) {
        alert("您必需指定檔案");
        return false;		
	}

	var url="upload.jsp?";
        url+="fileKind="+'<%=fileKind%>';
        url+="&systemType="+'<%=systemType%>';
        url+="&uploadId="+'<%=uploadId%>';
        url+="&dbName="+'<%=dbName%>';
        
    form1.action=url;
    return true;
}


var counter = 2;
function addatt()
{
	addattFile();			
}

function subatt()
{
	var iFiles = document.forms[0].howManyFiles.value;				
	var i=0;
	for(i=0; i<iFiles; i++){
		subattFile();
		counter--;	
	}		
}

function addattFile()
{
	var obj = document.getElementById('atta');
	var IH = "";
	var id = counter; //randomUUID();

    IH += '<table width="100%" border="0" cellpadding="0" cellspacing="0"><tr>' +
            '<td class="td_form">檔案說明：</td>' +
            '<td class="td_form_white">' +
            '  <input class="field" type="text" name="file' + id + '">'+
            '  <input class="field" id="upload' + id + '" type="file" name="file' + id + '">'+
            '</td>'+
          '</tr></table>';

    $("#atta").append(IH);
	counter++;
}

function subattFile()
{
	if (counter <= 0) return;
	var obj = document.getElementById('atta');
	var IH = obj.innerHTML;
	var i = IH.lastIndexOf('<TR>');
	IH = IH.substr(0,i) + '</TABLE>';
	obj.innerHTML = IH;
	counter--;
}

function init()
{
	if('<%=doClose%>'=="Y")
	{
		if (isObj(opener.document.all.item("state")))
			opener.document.all.item("state").value="upload";
		
		window.opener.checkField();
		window.close();  
	}

}

function cancelUpload(){
    window.close();
}

</script>
</head>
<body topmargin="5" onLoad="init()">
<form name="form1" enctype="multipart/form-data" method="post" onSubmit="return checkField();">

<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr>
<td class="bg">
    <table class="table_form" width="100%" height="100%">
    <tr>
      <td class="td_form_white">
      ** 上傳的檔案格式必須是<%=filestoreAllowedExtList%>
      </td>
    </tr>
    <tr>
      <td class="td_form_white">
      <div id="atta"> 
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td class="td_form">檔案說明：</td>
            <td class="td_form_white">
               <input class="field" type="text" name="file1">
               <input id="upload1"  class="field" type="file" name="file1">  
               <input type="hidden" name="attRowId" value="1">           
            </td>
        </tr>      
      </table>
      </div>
        <table width="100%" border="0" cellspacing="1" cellpadding="3">
          <tr> 
            <td align="right">
			   <input  type="button" value="更多檔案..." onClick="addatt();"> 
            </td>
          </tr>
        </table>      
      </td>
    </tr>     
    </table>
</td>
</tr>
 <!--Toolbar區============================================================-->
<tr>
<td class="bg" style="text-align:center">      
    <input class="toolbar_default" type="submit" value="上傳檔案">&nbsp;|                      
    <input class="toolbar_default" type="button" value="取消上傳" onClick="cancelUpload()">&nbsp;|                          
</td>
</tr>          
</table>
</form>



</body>
</html>

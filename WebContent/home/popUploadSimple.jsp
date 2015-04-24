<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.oreilly.servlet.*" %>
<%@ page import="com.oreilly.servlet.multipart.*" %>
<%@ include file="head.jsp" %>
<%
String popFileID = Common.checkGet(request.getParameter("popFileID"));
String popFileName = Common.isoToBig5(Common.checkGet(request.getParameter("popFileName")));
String popFileType = Common.isoToBig5(Common.checkGet(request.getParameter("popFileType")));
int maxPostSize = 1024*1024*5; //5 M
if (!"".equals(Common.get(application.getInitParameter("filestoreLimit")))) {
	maxPostSize = Integer.parseInt(org.apache.commons.lang.StringEscapeUtils.escapeHtml(application.getInitParameter("filestoreLimit")));
}
    boolean isMultipart = false;

    String contentType = request.getContentType();

    if(contentType!=null){
        contentType = contentType.toLowerCase();
        if(contentType.startsWith("multipart/form-data")){
            isMultipart = true;
        }
    }

    MultipartRequest mr = null;
    String uploadCaseID = "";
    String actionType = "";
    String fileName = "";
    
    if(isMultipart){
                
        uploadCaseID = new java.rmi.dgc.VMID().toString();
        uploadCaseID = uploadCaseID.replaceAll(":","_");
        
        if(popFileType == null || popFileType.equals(""))
        	popFileType = "filestoreLocation";
        File tempDirectory = new File(application.getInitParameter(popFileType));
        //File tempDirectory = new File(System.getProperty("java.io.tmpdir"));
        
        //String filePath = application.getInitParameter("filestoreLocation")+File.separator+uploadCaseID;
        //Common.CreateDir(filePath);
        //Common.MakeDir(new File(filePath));
        tempDirectory = new File(tempDirectory,uploadCaseID);                
        tempDirectory.mkdirs();               
        
        FileRenamePolicy policy = new DefaultFileRenamePolicy();
        String encoding = "utf-8";
        
        mr = new MultipartRequest(request,tempDirectory.getAbsolutePath(),maxPostSize,encoding,policy);
        //mr = new MultipartRequest(request,filePath,maxPostSize,encoding,policy);
        actionType = Common.checkGet(mr.getParameter("actionType"));
        popFileID = Common.checkGet(mr.getParameter("popFileID"));
        popFileName = Common.checkGet(mr.getParameter("popFileName"));                
    }
    
    String actionResult = "noAction";
    String actionMessage = "";
    
    if("doUpload".equals(actionType)){
        
        Enumeration enu = mr.getFileNames();
        File uploadFile = null;
        
        if(enu.hasMoreElements()){
            String name = (String)enu.nextElement();
            uploadFile = mr.getFile(name);
            int fileSize = (int) uploadFile.length();
            if (fileSize>0) {
                actionResult = "doUploadSuccess";
                actionMessage = "上傳成功";
                fileName = uploadFile.getName();
            } else {
            	Common.RemoveDirectory(uploadFile.getParentFile());
                actionResult = "doUploadFail";
                actionMessage = "上傳失敗，因為上傳的檔案中無任何內容！";                
            }
        }else{
            actionResult = "doUploadFail";
            actionMessage = "找不到上傳的檔案";            
        }
        
        
    }
    

    

%>
<html>
<head>

<meta http-equiv="Expires" content="-1"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../js/default.css" type="text/css">
<script type="text/javascript" src="../js/validate.js"></script>
<script type="text/javascript" src="../js/function.js"></script>
<script type="text/javascript">
var actionResult = '<%=actionResult%>';
var actionMessage = '<%=actionMessage%>';

function checkAfterAction(){    
    switch(actionResult){
    case 'noAction':
        break;
    case 'doUploadSuccess':
        alert(actionMessage); 
		if (isObj(opener.document.all.item("<%=popFileID%>"))) {
			opener.document.all.item("<%=popFileID%>").value='<%=uploadCaseID%>:;:'+'<%=fileName%>';		
		}
        window.close();
        break;
    case 'doUploadFail':
        alert(actionMessage);
        break;
    }
}

function checkField(){
    if(form1.FILE.value == ""){
            alert("您必需指定檔案");
            return false;
    } else {
    	var extPos = form1.FILE.value.lastIndexOf(".");
    	var ext = form1.FILE.value.toLowerCase().substring(extPos+1);
    	var allowExt = "<%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(application.getInitParameter("filestoreAllowedExtList"))%>";
    	if (extPos==-1) {
    		alert("無法判斷您上傳的檔案格式，請檢查檔案是否有副檔名並重新輸入!");
    		return false;
    	}
    	if (parse(allowExt).length>0) {
    		if (allowExt.search(ext)== -1 ) {
    			alert("上傳的檔案格式必須是<%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(application.getInitParameter("filestoreAllowedExtList"))%>，請重新輸入!");
    			return false;
    		}
    	}    	
    }
    form1.actionType.value = "doUpload";
}

function cancelUpload(){
    window.close();
}

</script>
</head>

<body topmargin="5" onload="checkAfterAction()">
<form id="form1" name="form1"  method="post" enctype="multipart/form-data" onSubmit="return checkField();">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg">
    <table class="table_form" width="100%" height="100%">
    <tr>
      <td nowrap colspan="2" class="td_form_white">
        ** 上傳的檔案限制為<%=(maxPostSize/1048576)%>MB，請勿超過。</td>
      </tr>
    <tr>
        <td nowrap class="td_form">上載檔案路徑：</td>
        <td nowrap class="td_form_white">
           <input class="field" TYPE="file" name="FILE">
        </td>
    </tr>
    </table>
</td></tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bg" style="text-align:center">
    <input type="hidden" name="uploadCaseID" value="<%=uploadCaseID%>" >
    <input type="hidden" name="popFileID" value="<%=popFileID%>" >    
    <input type="hidden" name="popFileName" value="<%=popFileName%>" >        
    <input type="hidden" name="actionType" value="" >
    &nbsp;|
    <input class="toolbar_default" type="submit" name="upload"	value="上傳檔案">&nbsp;|                      
    <input class="toolbar_default" type="button" name="cancel"	value="取消上傳" onClick="cancelUpload()">&nbsp;|                          
</td></tr>

</table>
</form>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0109F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>
<%	
if ("setMailFile".equals(obj.getState())){
	try
	{
		obj.doCopyFileToCon0002();
		obj.setErrorMsg("夾檔完成");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}
obj.setQueryAllFlag("true");
objList = (java.util.ArrayList) obj.queryAll_mailFile();

%>

<html>
<head>
<title>Mail File對象輔助視窗</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css?1=ss" type="text/css">
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/cbToggle.js"></script>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">
function checkField()
{	
	var alertStr = "";
	if(form1.state.value=="setMailFile"){
	    var c = form1.fds;
	    var j = 0;
	    if(c.checked!=true){	    	
		    for(var i=0; i < c.length ; i++){
		    	if(form1.fds[i].checked==true) j++;	        
		    }
		    if(j==0){
		    	if(confirm("確定不夾帶任何檔案?"))
		    	{
	                startSendMail();
		    	}else{
		    		return;
		    	}
		    }
	    }	    
	}	
	if(alertStr.length!=0){ alert(alertStr); return }	

	form1.submit();
	return true;
}

function startSendMail()
{
	opener.document.all.item("fileData").value = '<%=obj.getFileData()%>';
	opener.choseEmail();
	window.close();
}

function init()
{
	if('<%=obj.getErrorMsg()%>'=='夾檔完成')
	{
		startSendMail();
	}
}
</script>
</head>
<body onLoad="showErrorMsg('<%=obj.getErrorMsg()%>');init();">
<form id="form1" name="form1" method="post" >
<table width="100%" cellspacing="0" cellpadding="0">
<tr><td>
<% request.setAttribute("QueryBean",obj);%>
<br><br>
</td></tr>
<tr><td class="bg" style="text-align:left">
	<input class="toolbar_default" id="setMailFile" type="button" name="setMailFile" value="選 　　擇" >
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="id" >
</td></tr>
<!--List區============================================================-->
<tr><td class="bg" >
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>	       
	    <th class="listTH"><input type="checkbox" name="cbAll"></th>
	    <th class="listTH"><a class="text_link_w" >No.</a></th>	 	    
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">檔案名稱</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">檔案說明</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	  boolean primaryArray[] = {true,false,false,false};
	  boolean displayArray[] = {false,true,true,true};
	  String[] alignArray = {"center","center","center","center"};
	  boolean linkArray[] = {false,false,false,false};
	  out.write(View.getCheckboxQuerylist(primaryArray, displayArray, alignArray, objList, "true", "fds", linkArray, "_blank"));
	%>  
	</tbody>
</table>
</div>
</td></tr>
</table>	
</form>
<script type="text/javascript">	
var cb = new cbToggle('cb',document.form1,form1.cbAll,'fds');
cb.config.cssTopLevel = true;
$(document).ready(function()
{
	$("#setMailFile").click(function()
	{		
		form1.state.value = "setMailFile";
		checkField();
	});
});
</script>
</body>
</html>

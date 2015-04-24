<!--
程式目的：
程式代號：
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.trans.TRANSMED01">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>

<%

if (obj.getState().equals("importTemplate") && !"".equals(obj.getQ_filePath())) 
{
	    obj.setPeople(User.getUserId());
		obj.trans();	
		obj.setErrorMsg(obj.getMsg());
}	

obj.trans();	
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
<script type="text/javascript">
function checkField(intType)
{
	var alertStr="";

	if (intType==1) form1.state.value = "exportTemplate";
	
	if (intType==2) 
	{
	    if(form1.q_filePath.value == "")
		{
	            alertStr += "您必需指定檔案";
	            return false;
	    } 
	    else 
		{
	    	var extPos = form1.q_filePath.value.lastIndexOf(".");
	    	var ext = form1.q_filePath.value.toLowerCase().substring(extPos+1);
	    	if (extPos==-1) 
		    {
	    		alertStr += "無法判斷您上傳的檔案格式，請檢查檔案是否有副檔名並重新輸入!";
	    		return false;
	    	} 
	    	else if (ext!='xls')
		    {
	    		alertStr += "上傳的檔案格式必須是xls格式，請重新輸入!";	    		
	    	}	
	    }		
		form1.state.value = "importTemplate";
	}	

	if(alertStr.length!=0)
	{ 
		form1.state.value = "";
		alert(alertStr);
		return false;
	}
	else
	{
		form1.submit();
	}	
}

function init()
{
	
}

function resubmit()
{
	form1.state.value = "importTemplate";
	form1.submit();
	//show();
}

function show()
{
  //showModelessDialog ('bar.html','progress','dialogHeight:100px;dialogWidth:400px;status:1;help:0;edge:raised;center:yes');
}	

function openUploadWindow(popFileID, popFileName)
{
	if (isObj(document.all.item(popFileID))) 
	{
	    var prop='';

	    var windowTop=(document.body.clientHeight)/2;
		var windowLeft=(document.body.clientWidth)/2;
		prop=prop+"resizable=1,width=550,height=120,";
		prop=prop+"top="+windowTop+",";
		prop=prop+"left="+windowLeft+",";
		prop=prop+"scrollbars=yes";
	    closeReturnWindow();
	    returnWindow = window.open('popTransUploadSimple.jsp?popFileID=' + popFileID + '&popFileName='+popFileName,'上傳檔案',prop);
	} 
	else 
	{
		alert("欄位不存在,請檢查!");
		return ;
	}
}

</script>
</head>

<body onLoad="init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">

<table  border="1" align="center" class="table_form">
<tr>
  <td class="td_form">匯入檔案路徑：</td>
  <td class="td_form_white">
     <%=View.getPopTransUpload("field","q_filePath",obj.getQ_filePath(), null, null,true)%>
  </td>		
</tr>
</table>
<br>
<%=Common.get(obj.getTransMsg())%>
<input type="hidden" name="state" value="">
</form>
</body>
</html>

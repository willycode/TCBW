<!--
程式目的：醫療器材不良事件通報 > > 不良品案件審核 > > 廠商通知中
程式代號：
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="MEDIN0501" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN0501F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList" scope="page" class="java.util.ArrayList"/>

<%
obj.setQueryAllFlag("true");
if("".equals(obj.getUserID()))
{
	obj.setUserID(User.getUserId());
}
if ("doSend".equals(obj.getState())) 
{
	obj.setHttpRequest(pageContext.getRequest());
	try
	{
		obj.doSend();
		//obj.setErrorMsg("送出成功");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		obj.setErrorMsg("發生未預期錯誤，請重新操作 !");
	}
}

if("true".equals(obj.getQueryAllFlag())){	
	objList = (java.util.ArrayList) obj.doQueryAll();
	obj.setStateQueryOneSuccess();
}
%>
<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/cbToggle.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript">
function checkField()
{
	var alertStr = "";

	if (cb.AnyChecked()==false) alertStr += "請勾選案件！\n";

    var l=0;


    if(document.getElementsByName("fds").length!=null)
      l=document.getElementsByName("fds").length

    var str="";

	for(var i=0; i<l; i++)
    {	   
	   var b=document.getElementsByName("fds");	 
	   if(b[i].checked)
       {
         str+=b[i].value+",";  
       }
	}


	if(str!="")
	{	
	  var x = getRemoteData('../../ajax/jsonMedin0501f.jsp?str='+str,'');

	  if(x!=null && x.length>0)
	  {
		var json1 = JSON.parse(x);
	
		if("Y"==json1.result)
			alertStr+="許可證申請場不相同，請重新勾選!";
	  }
	}
	
	if(alertStr.length!=0)
	{ 
		alert(alertStr); return false; 
	}

	if(form1.state.value=="doSend")
	{
		getEmail('MED020004','window.opener.send','N',str);
		return false;
	}

}

function init() 
{
	setDisplayItem("spanInsert,spanClear,spanConfirm,spanUpdate,spanQueryAll,spanDelete,spanListPrint,spanListHidden","H");
	listContainerRowClick(0);
	setAllOpen();

	  //重新整理左邊樹狀
	  window.parent.frames['menu'].location.href = "../../home/dTreeMenu.jsp";
}

function getEmail(mailID,isJS,isAdd,str)
{
	var jscript = "";	
	var prop = "";
	var windowTop=(document.body.clientHeight-400)/2+100;
	var windowLeft=(document.body.clientWidth-400)/2+100;
	prop=prop+"width=1200,height=600,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes,resizable=yes";
	closeReturnWindow();

	str=str.substring(0,str.length-1);

	returnWindow=window.open(getVirtualPath() + "tcbw/conbg/conbg0001f.jsp?id="+str+"&isAdd="+isAdd+"&isJS="+isJS+"&mailID="+mailID,'',prop);
}

function send()
{
   form1.state.value="doSend";
   beforeSubmit();
   form1.submit();
   return true;
}


</script>
</head>

<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');init();showErrorMsg('<%=obj.getErrorMsg()%>');">
<form id="form1" name="form1" method="post" onSubmit="return checkField()" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">

<!--Toolbar區============================================================-->
<tr><td class="bgToolbar" style="text-align:left">
	<input type="hidden" name="state" value="<%=obj.getState()%>">
	<input type="hidden" name="queryAllFlag" value="<%=obj.getQueryAllFlag()%>">
	<input type="hidden" name="userID" value="<%=User.getUserId()%>">
	<jsp:include page="../../home/toolbar.jsp" />
	<span id="spanDoSend">
    	<input class="toolbar_default" type="button" followPK="false" name="doSend" value="廠商通知" onClick="whatButtonFireEvent(this.name)">&nbsp;
    </span>
</td></tr>

<!--
<tr><td class="bgPagging">
<% request.setAttribute("QueryBean",obj);%>
<jsp:include page="../../home/page.jsp" />
</td>
</tr>
-->
  
<!--List區============================================================-->
<tr><td class="bgList">
<div id="listContainer">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><input type="checkbox" name="cbAll"></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',1,false);" href="#">案件編號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">許可證字號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">許可證申請商代號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">許可證申請商</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	boolean primaryArray[] = {true,false,false,false,false};
	boolean displayArray[] = {false,true,true,true,true};
	out.write(View.getCheckboxQuerylist(primaryArray, displayArray, null, objList, obj.getQueryAllFlag(), "fds", null, null));
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
localButtonFireListener.whatButtonFireEvent = function(buttonName)
{
	switch (buttonName)	
	{
	   case "doSend":
		  checkField();
		  break;
	}
	

	
}
</script>
</body>
</html>

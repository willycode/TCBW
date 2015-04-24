<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0111F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<%
request.setCharacterEncoding("UTF-8");
String applNo = ESAPI.encoder().encodeForHTML(Common.get(request.getParameter("applNo")));
String isJS= ESAPI.encoder().encodeForHTML(Common.get(request.getParameter("isJS")));

if("confirm".equals(obj.getState()))
{
	try
	{
		obj.update();
		if("updateSuccess".equals(obj.getState())){
            //donothing
		}
		else{
			obj.setErrorMsg(obj.getErrorMsg());
			obj.setState("init");
		}
	}
	catch(Exception e){
		if (e.getMessage()!=null && e.getMessage().length()<200){
			obj.setErrorMsg(Common.escapeJavaScript(e.getMessage()));
		} 
		else{
			obj.setErrorMsg("發生錯誤，請重新操作 !");
			e.printStackTrace();
		}
	}
}
else{
	obj = (com.kangdainfo.tcbw.view.drgin.DRGIN0111F) obj.doQueryOne();
}
%>
<html>
<head>
<title>案件發文</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="./../../js/jquery.js"></script>
<script type="text/javascript">

function checkField(type)
{	
	var alertStr = "";	
	alertStr += checkEmpty(form1.postDate, "發文日期");	
	alertStr += checkEmpty(form1.postNo, "發文文號");	
	if(alertStr.length!=0){
		alert(alertStr); 
		return false;
	}
    
	form1.state.value = "confirm";
	beforeSubmit();
	return true;
}

$(function() 
{
	if(form1.state.value == "updateSuccess")
	{
		if("<%=isJS%>" != "")
		{
			<%=isJS%>();
		}				
		window.close();
	}
});

</script>
</head>
<body >
<form id="form1" name="form1" method="post"  onSubmit="return checkField()" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg" >
	<div id="formContainer" style="height:auto">
	<table class="table_form" width="100%" height="100%">	
		<tr>
			<td nowrap class="td_form" width="15%">發文日期</td>
			<td nowrap class="td_form_white" >
			    <%=View.getPopCalendar("field","postDate",obj.getPostDate())%>
			</td>
		</tr>
	    <tr>
			<td nowrap class="td_form" >發文文號</td>
			<td nowrap class="td_form_white" >
				<input class="field" type="text" name="postNo" size="20" maxlength="20" value="<%=obj.getPostNo()%>">
			</td>
		</tr> 
	</table>
	</div>
</td></tr>

<!--Toolbar區============================================================-->
<tr><td nowrap class="bg" style="text-align:center">
	<input class="toolbar_default" type="submit" name="confirm" value="確　定">&nbsp;
	<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="window.close()">
    <input type="hidden" name="state" value="<%=obj.getState()%>">
    <input type="hidden" name="applNo" value="<%=obj.getApplNo()%>">
</td>
</tr>

</table>	
</form>
</body>
</html>

<!--
程式目的：藥品不良品通報-廠商回覆-CAPA確認表
程式代號：drgin0108
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgex.DRGEX0108F">
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
	obj = (com.kangdainfo.tcbw.view.drgex.DRGEX0108F) obj.doQueryOne();
}
%>
<html>
<head>
<title>CAPA確認表</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../../js/default.css" type="text/css"/>
<script type="text/javascript" src="../../js/json.js"></script>
<script type="text/javascript" src="../../js/tablesoft.js"></script>
<script type="text/javascript" src="../../js/validate.js"></script>
<script type="text/javascript" src="../../js/function.js"></script>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">

function checkField(type)
{	
	var alertStr = "";	
	alertStr += checkEmpty(form1.capaDate05,"製造日期" );	
	
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
	setDisplayItem('spanInsert,spanUpdate,spanConfirm,spanClear,spanQueryAll,spanDelete,spanListPrint,spanListHidden','H');
});

</script>
</head>
<body onLoad="whatButtonFireEvent('<%=obj.getState()%>');">
<form id="form1" name="form1" method="post"  onSubmit="return checkField()" autocomplete="off">
<table width="100%" cellspacing="0" cellpadding="0">
<!--Form區============================================================-->
<tr><td nowrap class="bg" >
<div id="formContainer" style="height:auto">
    <table class="table_form" width="100%" height="100%">
	    <tr>
			<td nowrap class="td_form" >案號</td>
			<td nowrap class="td_form_white">
                <input class="field_RO" type="text" name="applNo" size="10" value="<%=obj.getApplNo()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >批號</td>
			<td nowrap class="td_form_white">
                <input class="field_RO" type="text" name="lotNo05" size="10" value="<%=obj.getLotNo05()%>">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >高頻率案件資訊</td>
			<td nowrap class="td_form_white" colspan="3">
				一年內本藥品同此次瑕疵案件之同批號的各案件編號：<input name="hisApplNoY" class="field_RO" type="text" size="80"  value="<%=obj.getHisApplNoY()%>"><br>
				一年內本藥品同此次瑕疵案件之不同批號的各案件編號：<input name="hisApplNoN" class="field_RO"  type="text" size="80" value="<%=obj.getHisApplNoN()%>">
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form" >產品製造</td>
			<td nowrap class="td_form_white">
				<%=View.getRadioBoxTextOption("field_RO", "beforeOrLater05", "1;前次CAPA執行前製造;2;前次CAPA執行後製造;", obj.getBeforeOrLater05())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">製造日期</td>
			<td nowrap class="td_form_white">
                  <%=View.getPopCalendar("field_Q","capaDate05",obj.getCapaDate05())%>
			</td>
		</tr>
    </table>  
</div>  
<!--Toolbar區============================================================-->
<tr><td nowrap class="bg" style="text-align:center">
    <jsp:include page="../../home/toolbar.jsp">
	   <jsp:param value="N" name="btnCopy"/>
    </jsp:include>
	<input class="toolbar_default" type="submit" name="confirm" value="確　定">&nbsp;
	<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　消" onClick="window.close()">
    <input type="hidden" name="state" value="<%=obj.getState()%>">
</td>
</tr>
</table>	
</form>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:include page="../../home/secure.jsp">
	<jsp:param name="DTREE_PROGRAM_IDENTIFIER" value="SYSDS801" />
</jsp:include>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.common.view.sys.ds.SYSDS801F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>
<html>
<head>
<%@ include file="../../home/meta.jsp" %>
<script type="text/javascript">
function checkField(){
	var alertStr="";
	//alertStr += checkEmpty(form1.auditPercentage,"查核百分比");
	alertStr += checkEmpty(form1.q_logDate,"資料日期起");
	alertStr += checkEmpty(form1.q_logDateE,"資料日期迄");
	alertStr += checkDate(form1.q_logDate,"資料日期起");
	alertStr += checkDate(form1.q_logDateE,"資料日期迄");
	alertStr += checkDateSE(form1.q_logDate,form1.q_logDateE,"資料日期");
	if(alertStr.length!=0){ alert(alertStr); return false; }
	beforeSubmit();
	return true;
}

function getUserName(obj){
	if(obj!=null && obj.value.length>0){
	var hql = "select userName from CommonUser where userId='"+obj.value+"'";
	var x = getRemoteData('../../ajax/jsonObjects.jsp',hql);
	if(x!=null&&x.length>0){
		var json = JSON.parse(x);
		form1.q_userName.value=json.obj0;
	}
	}
}
</script>
</head>

<body topmargin="5">
<br><br>
<form id="form1" name="form1" method="post" action="sysds801p.jsp" onsubmit="return checkField();">
<table class="bg"  cellspacing="0" cellpadding="0" align="center"><tr><td nowrap>
	<table class="table_form"  border="1">
	<tr>
        <td nowrap class="td_form" colspan="2" style="text-align:center">使用者登出入紀錄查詢<font color="red">(A4 直式)</font></td>
	</tr>	
	<tr>
		<td nowrap class="td_form"><font color="red">*</font>資料日期</td>
		<td nowrap class="td_form_white">
			起：<%=View.getPopCalendar("field_Q","q_logDate",obj.getQ_logDate()) %>~迄：<%=View.getPopCalendar("field_Q","q_logDateE",obj.getQ_logDateE()) %>		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">使用者</td>
		<td nowrap class="td_form_white"> 帳號
		  <input class="field_Q" type="text" name="q_userID" size="12" maxlength="20" value="<%=obj.getQ_userID()%>" onchange="getUserName(this);">
		  姓名：<input class="field_Q" type="text" name="q_userName" size="12" maxlength="20" value="<%=obj.getQ_userName()%>">		</td>
	</tr>		
	<tr>
		<td nowrap class="td_form">輸出格式：</td>
		<td nowrap class="td_form_white">
			<select name="q_outputFormat" id="q_outputFormat" class="field_Q">
				<%=View.getJasperReportFormatOption(obj.getQ_outputFormat())%>
			</select>	
		</td>
	</tr>	
	<tr>
		<td nowrap class="queryTDInput" colspan="2" style="text-align:center;">
			<input class="toolbar_default" type="submit" name="querySubmit" value="產生報表檔案" >
		</td>
	</tr>
	</table>
	</td></tr>
	</table>	

</form>
</body>
</html>




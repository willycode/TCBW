<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.medex.MEDEX0101F qBean = (com.kangdainfo.tcbw.view.medex.MEDEX0101F) request.getAttribute("qBean");
%>
	<table class="queryTable" width="100%">
	<tr>
		<td nowrap class="td_form" width="15%">案件編號：</td>
		<td nowrap class="td_form_white" width="35%">
			<input class="field_Q" type="text" name="q_applNoS" size="11" maxlength="11" value="<%=qBean.getQ_applNoS()%>">
			- 
			<input class="field_Q" type="text" name="q_applNoE" size="11" maxlength="11" value="<%=qBean.getQ_applNoE()%>">
		</td>
		<td nowrap class="td_form" width="15%">通報日期起訖：</td>
		<td nowrap class="td_form_white" width="35%">
			<%=View.getPopCalendar("field_Q","q_occurDateS",qBean.getQ_occurDateS())%>~<%=View.getPopCalendar("field_Q","q_occurDateE",qBean.getQ_occurDateE())%>
		</td>
	</tr>  
	<tr>
	    <td nowrap class="td_form">許可證字號：</td>
		<td nowrap class="td_form_white">
		    <select name="q_medPermit" class="field_Q" >
				<%=View.getOptionCodeKind("MEDPKID", qBean.getQ_medPermit())%>
			</select>
			字第<input class="field_Q" type="text" name="q_medPermitNumber" size="10" maxlength="6" value="<%=qBean.getQ_medPermitNumber()%>">號
		     <input type="button" name="btnQryExpense" onClick="permitDataQ();" value="查詢" width="120px" class="field_Q" >
		</td>
		<td nowrap class="td_form">案件狀態：</td>
		<td nowrap class="td_form_white">
		    <select class="field_Q" name="q_status">
				<%=View.getTextOption("00;暫存;01;待上傳;02;退件;03;撤案;30;補件中;xx;通報完成", qBean.getQ_status()) %>
			</select>
		</td>
	</tr>	
	<tr>
	    <td nowrap class="td_form">醫材品名：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_medTestMedical" size="60" maxlength="100" value="<%=qBean.getQ_medTestMedical()%>">
		    <input type="hidden" name="userID" value="<%=User.getUserId()%>">
			<input type="hidden" name="id" value="<%=qBean.getId()%>">
			<input type="hidden" name="doType" value="<%=qBean.getDoType()%>">
		</td>
	</tr>
	</table>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.drgin.DRGIN0105F qBean = (com.kangdainfo.tcbw.view.drgin.DRGIN0105F) request.getAttribute("qBean");
%>
	<table class="queryTable" width="100%">
	<tr>
		<td class="td_form">案件編號：</td>
		<td class="td_form_white">
			<input class="field_Q" type="text" name="q_applNoS" size="10" maxlength="11" value="<%=qBean.getQ_applNoS()%>">
			- 
			<input class="field_Q" type="text" name="q_applNoE" size="10" maxlength="11" value="<%=qBean.getQ_applNoE()%>">	
			</td>
		<td class="td_form">許可證字號：</td>
		<td class="td_form_white">		 				
			<select name="q_permitKey" class="field_Q">
				<%=View.getOption("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGPKID' order by codeId", qBean.getQ_permitKey())%>
			</select>			 
			號：<input class="field_Q" type="text" name="q_permitNo" size="6" maxlength="6" value="<%=qBean.getQ_permitNo()%>">
		</td>
	</tr>  
	<tr>
		<td class="td_form">發現日期起訖：</td>
		<td class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_occurDateS",qBean.getQ_occurDateS())%>~<%=View.getPopCalendar("field_Q","q_occurDateE",qBean.getQ_occurDateE())%>		
		</td>
		<td class="td_form">作業人員：</td>
		<td class="td_form_white">
			<input class="field_Q" type="text" name="q_chargeMan" size="6" maxlength="10" value="<%=qBean.getQ_chargeMan()%>">
		</td>
	</tr>
	<tr>
		<td class="td_form">藥品品名：</td>
		<td class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_Product" size="100" maxlength="100" value="<%=qBean.getQ_Product()%>">
	    </td>
	</tr>
	</table>
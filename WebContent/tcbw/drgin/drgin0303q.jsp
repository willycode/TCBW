<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.drgin.DRGIN0303F qBean = (com.kangdainfo.tcbw.view.drgin.DRGIN0303F) request.getAttribute("qBean");
%>
	<table class="queryTable" width="100%">
	<tr>
		<td class="td_form">通報日期：</td>
		<td class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_notifierRepDateS",qBean.getQ_notifierRepDateS())%>~<%=View.getPopCalendar("field_Q","q_notifierRepDateE",qBean.getQ_notifierRepDateE())%>		
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">許可證字號：</td>
		<td nowrap class="td_form_white">
			<select name="q_permitKey" class="field_Q">
				<%=View.getOption("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGPKID' order by codeId", qBean.getQ_permitKey())%>
			</select>			 
			第<input class="field_Q" type="text" name="q_permitNo" size="6" maxlength="6" value="<%=qBean.getQ_permitNo()%>">號
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">藥品名稱：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_productName" size="60" maxlength="100" value="<%=qBean.getQ_productName()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">作業人員：</td>
		<td nowrap class="td_form_white" colspan="3">
			<select name="q_chargeMan" class="field_Q">
				<%=View.getOption("select userId, userName from CommonUser where inORout ='in' order by userId", qBean.getQ_chargeMan())%>
			</select>
		</td>
	</tr>
	</table>
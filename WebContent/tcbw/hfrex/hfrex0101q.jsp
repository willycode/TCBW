<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.hfrex.HFREX0101F qBean = (com.kangdainfo.tcbw.view.hfrex.HFREX0101F) request.getAttribute("qBean");
%>
	<table class="queryTable" width="100%">
	<tr>
		<td nowrap class="td_form">案件編號：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_applNo" size="11" maxlength="11" value="<%=qBean.getQ_applNo()%>" onChange="toUpper(this);">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">通報日期：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q", "q_notifierRepDateS", qBean.getQ_notifierRepDateS())%>
			~
			<%=View.getPopCalendar("field_Q", "q_notifierRepDateE", qBean.getQ_notifierRepDateE())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">許可證字號：</td>
		<td nowrap class="td_form_white">
			<select class="field_Q" name="q_permitKey" type="select">
				<%=View.getOptionCodeKind("HFRPKID", qBean.getQ_permitKey()) %>
			</select>
			<input class="field_Q" type="text" name="q_permitNo" size="20" maxlength="16" value="<%=qBean.getQ_permitNo()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">商品名：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_product" size="60" maxlength="80" value="<%=qBean.getQ_product()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">案件狀態：</td>
		<td nowrap class="td_form_white">
			<select class="field_Q" name="q_status" type="select">
				<%=qBean.getOutStatusOption(qBean.getQ_status()) %>
			</select>
			
			<input type="hidden" name="id" value="<%=qBean.getId()%>">
			<input type="hidden" name="hfrType" value="<%=qBean.getHfrType()%>">
			<input type="hidden" name="status" value="<%=qBean.getStatus()%>">
					
			<input type="hidden" name="userID" value="<%=User.getUserId()%>">
			<input type="hidden" name="doType" value="<%=qBean.getDoType()%>">
		</td>
	</tr>
	</table>
	
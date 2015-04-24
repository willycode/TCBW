<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.hfrin.HFRIN0601F qBean = (com.kangdainfo.tcbw.view.hfrin.HFRIN0601F) request.getAttribute("qBean");
%>
	<table class="queryTable" width="100%">
	<tr>
		<td nowrap class="td_form">案件編號：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_applNo" size="15" maxlength="11" value="<%=qBean.getQ_applNo()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">通報日期起迄：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q", "q_notifierRepDateS", qBean.getQ_notifierRepDateS())%>
			~
			<%=View.getPopCalendar("field_Q", "q_notifierRepDateE", qBean.getQ_notifierRepDateE())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">食品字號：</td>
		<td nowrap class="td_form_white">
			<select class="field_Q" name="q_permitKey" type="select">
				<%=View.getOptionCodeKind("HFRPKID", qBean.getQ_permitKey()) %>
			</select>
			<input class="field_Q" type="text" name="q_permitNo" size="20" maxlength="16" value="<%=qBean.getQ_permitNo()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">品名：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_product" size="60" maxlength="80" value="<%=qBean.getQ_product()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">作業人員：</td>
		<td nowrap class="td_form_white">
			<select class="field_Q" name="q_assignMan" type="select">
				<%=qBean.getCommonUserOption(qBean.getQ_assignMan()) %>
			</select>
			<input type="hidden" name="userID" value="<%=User.getUserId()%>">
			
			<input type="hidden" name="id" value="<%=qBean.getId()%>">
			<input type="hidden" name="hfrType" value="<%=qBean.getHfrType()%>">
			<input type="hidden" name="status" value="<%=qBean.getStatus()%>">
			<input type="hidden" name="assignMan" value="<%=qBean.getAssignMan()%>">
		</td>
	</tr>
	</table>
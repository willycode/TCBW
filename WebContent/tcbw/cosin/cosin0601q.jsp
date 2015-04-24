<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.cosin.COSIN0601F qBean = (com.kangdainfo.tcbw.view.cosin.COSIN0601F) request.getAttribute("qBean");
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
				<%=View.getOptionCodeKind("CPT", qBean.getQ_permitKey()) %>
			</select>
			<input class="field_Q" type="text" name="q_permitNo" size="20" maxlength="14" value="<%=qBean.getQ_permitNo()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">化粧品品名：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_chProduct" size="60" maxlength="80" value="<%=qBean.getQ_chProduct()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">製造廠/進口代理商：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_manufactorName" size="30" maxlength="50" value="<%=qBean.getQ_manufactorName()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">作業人員：</td>
		<td nowrap class="td_form_white">
			<select class="field_Q" name="q_chargeMan" type="select">
				<%=qBean.getCommonUserOption(qBean.getQ_chargeMan()) %>
			</select>
			
			<input type="hidden" name="userID" value="<%=qBean.getUserID()%>">
			<input type="hidden" name="id" value="<%=qBean.getId()%>">
			<input type="hidden" name="q_type" value="<%=qBean.getQ_type()%>">						<%--FOR 辨別不良品或不良反應的處理中程式 --%>
		</td>
	</tr>
	
	<%
	if("1S".equals(qBean.getQ_type())){	
	%>
	<tr>
		<td nowrap class="td_form">不良缺陷描述：</td>
		<td nowrap class="td_form_white">
			<%=qBean.getCOS0003DbCheckBoxOption("field_Q", "q_subCode", "", qBean.getQ_subCode(), "", false) %>
		</td>
	</tr>
	<%
	}
	%>
	
	<%
	if("2S".equals(qBean.getQ_type())){
	%>
	<tr>
		<td nowrap class="td_form">不良反應狀況：</td>
		<td nowrap class="td_form_white">
			<select class="field_Q" name="q_adverseCondition" type="select">
				<%=View.getOptionCodeKind("CAC", qBean.getQ_adverseCondition()) %>
			</select>
		</td>
	</tr>
	<%
	}
	%>
	</table>
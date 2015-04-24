<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.cosex.COSEX0101F qBean = (com.kangdainfo.tcbw.view.cosex.COSEX0101F) request.getAttribute("qBean");
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
			<%=View.getPopCalendar("field_Q", "q_notifierRepDateE", qBean.getQ_notifierRepDateS())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">不良事件類別：</td>
		<td nowrap class="td_form_white">
			<%=View.getCommonCodeKindBoxOption("field_Q", "q_cosType", "CCT", qBean.getQ_cosType()) %>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">許可證字號：</td>
		<td nowrap class="td_form_white">
			<select class="field_Q" name="q_permitKey" type="select">
				<%=View.getOptionCodeKind("CPT", qBean.getQ_permitKey()) %>
			</select>
			<input class="field_Q" type="text" name="q_permitNo" size="20" maxlength="16" value="<%=qBean.getQ_permitNo()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">化妝品品名：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_chProduct" size="60" maxlength="80" value="<%=qBean.getQ_chProduct()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">製造廠/進口代理商：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_manufactorName" size="30" maxlength="50" value="<%=qBean.getQ_manufactorName()%>">
			
			<input type="hidden" name="q_type" value="<%=qBean.getQ_type()%>">				<%--FOR 辨別工作區程式 --%>
			<input type="hidden" name="userID" value="<%=User.getUserId()%>">
			<input type="hidden" name="doType" value="<%=qBean.getDoType()%>">				<%--FOR 辦別目前操作為新增或修改 --%>
			
			<input type="hidden" name="id" value="<%=qBean.getId()%>">						<%--FOR 案件本身資料 --%>		
			<input type="hidden" name="cosType" value="<%=qBean.getCosType()%>">
		</td>
	</tr>
	<%
	if("L".equals(qBean.getQ_type())){
	%>
	<tr>
		<td nowrap class="td_form">案件狀態：</td>
		<td nowrap class="td_form_white">
			<select class="field_Q" name="q_status" type="select">
				<%=View.getOutStatusOption(qBean.getQ_status()) %>
			</select>
		</td>
	</tr>
	<%
	}
	%>
	<tr>
		<td nowrap class="td_form">不良缺陷描述：</td>
		<td nowrap class="td_form_white">
			<%=qBean.getCOS0003DbCheckBoxOption("field_Q", "q_subCode", "", qBean.getQ_subCode(), "", false) %>
		</td>
	</tr>
	</table>
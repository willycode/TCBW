<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.conex.CONEX0201F qBean = (com.kangdainfo.tcbw.view.conex.CONEX0201F) request.getAttribute("qBean");
%>
	<table class="queryTable"  border="1">
	<tr>
		<td class="queryTDLable">單位屬性：</td>
		<td class="queryTDInput">
			<select class="field_Q" name="q_department" type="select" onchange="changeForm(this);">
				<%=View.getTextOption("01;民眾;02;廠商;03;醫事機構;04;衛生單位;", qBean.getQ_department(), 1) %>
			</select>
		</td>
	</tr>
	<tr>
		<td class="queryTDLable">帳號：</td>
		<td class="queryTDInput">
			<input class="field_Q" type="text" name="q_userId" size="20" maxlength="20" value="<%=qBean.getQ_userId()%>"> 
		</td>
	</tr>
	<tr>
		<td class="queryTDLable">中文姓名：</td>
		<td class="queryTDInput">
			<input class="field_Q" type="text" name="q_userName" size="20" maxlength="50" value="<%=qBean.getQ_userName()%>">	
		</td>
	</tr>
	<tr>
		<td class="queryTDLable">身分證號：</td>
		<td class="queryTDInput">
			<input class="field_Q" type="text" name="q_personalId" size="15" maxlength="10" value="<%=qBean.getQ_personalId()%>">
		</td>
	</tr>
	<tr>
		<td class="queryTDLable">連絡電話：</td>
		<td class="queryTDInput">
			<input class="field_Q" type="text" name="q_userTel" size="20" maxlength="20" value="<%=qBean.getQ_userTel()%>">
		</td>
	</tr>
	<tr id="t1">
		<td class="queryTDLable">服務機構：</td>
		<td class="queryTDInput">
			<input class="field_Q" type="text" name="q_userJobName" size="30" maxlength="40" value="<%=qBean.getQ_userJobName()%>">
		</td>
	</tr>
	<tr>
		<td class="queryTDInput" colspan="2" style="text-align:center;">
			<input type="hidden" name="id" value="<%=qBean.getId()%>">
			<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="確　　定" onClick="listContainerRowClick(0);">
			<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="whatButtonFireEvent(this.name)">
		</td>
	</tr>
	</table>
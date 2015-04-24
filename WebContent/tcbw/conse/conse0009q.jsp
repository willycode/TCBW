<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%@ page import="com.kangdainfo.tcbw.view.conse.CONSE0009F" %>
<%
com.kangdainfo.tcbw.view.conse.CONSE0009F qBean = (com.kangdainfo.tcbw.view.conse.CONSE0009F)request.getAttribute("qBean");
%>
	<div class="queryTitle">查詢視窗</div>
	<table class="queryTable" border="1">
	<tr>
		<td nowrap class="queryTDLable">分局別：</td>
		<td nowrap class="queryTDInput">
			<select class="field_Q" name="q_bnhi" type="select">
				<%=View.getOptionCodeKind("MEDDIV", qBean.getQ_bnhi())%>				
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">醫事機構代碼：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_medAgencyCode" size="12" maxlength="12" value="<%=qBean.getQ_medAgencyCode()%>">		
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">醫事機構名稱：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_medAgencyName" size="50" maxlength="50" value="<%=qBean.getQ_medAgencyName()%>">		
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDInput" colspan="2" style="text-align:center;">
			<input type="hidden" name="q_id" value="<%=qBean.getQ_id()%>">
			<input type="hidden" name="q_isQuery">
			<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="確　　定" onClick="form1.q_isQuery.value='Y'">
			<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="whatButtonFireEvent(this.name)">
		</td>
	</tr>
	</table>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%@ page import="com.kangdainfo.tcbw.view.medse.MEDSE0003F" %>
<%
com.kangdainfo.tcbw.view.medse.MEDSE0003F qBean = (com.kangdainfo.tcbw.view.medse.MEDSE0003F)request.getAttribute("qBean");
%>
    <div class="queryTitle">查詢視窗</div>
	<table class="queryTable"  border="1">
	<tr>
		<td class="queryTDLable">代碼：</td>
		<td class="queryTDInput">
			<input class="field_Q" type="text" name="q_code" size="5" maxlength="5" value="<%=Common.get(qBean.getQ_code())%>">
		</td>
	</tr>
	<tr>
		<td class="queryTDLable">名稱：</td>
		<td class="queryTDInput">
			<input class="field_Q" type="text" name="q_name" size="50" maxlength="50" value="<%=Common.get(qBean.getQ_name())%>">
		</td>
	</tr>
	<tr>
		<td class="queryTDLable">FDA代碼：</td>
		<td class="queryTDInput">
			<input class="field_Q" type="text" name="q_fdaCode" size="4" maxlength="4" value="<%=qBean.getQ_fdaCode()%>">		</td>
	</tr>
	<tr>
		<td class="queryTDInput" colspan="2" style="text-align:center;">
			<input class="toolbar_default" followPK="false" type="submit" name="querySubmit" value="確　　定" >
			<input class="toolbar_default" followPK="false" type="button" name="queryCannel" value="取　　消" onClick="whatButtonFireEvent(this.name)">
		</td>
		
	</tr>
	</table>
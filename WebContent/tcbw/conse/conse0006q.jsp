<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%@ page import="com.kangdainfo.tcbw.view.conse.CONSE0006F" %>
<%
com.kangdainfo.tcbw.view.conse.CONSE0006F qBean = (com.kangdainfo.tcbw.view.conse.CONSE0006F)request.getAttribute("qBean");
%>
<div class="queryTitle">查詢視窗</div>
	<table class="queryTable" border="1">
	<tr>
		<td nowrap class="queryTDLable">單位名稱：</td>
		<td nowrap class="queryTDInput">
			<input class="field_Q" type="text" name="q_unionName" size="40" maxlength="50" value="<%=qBean.getQ_unionName()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">地址：</td>
		<td nowrap class="queryTDInput">
		    <select name="q_county" class="field_Q" onChange="setValue(this,q_zipcode);">
			     <%=View.getOptionCodeKind("CTY", qBean.getQ_county())%>
			</select>	
			<select name="q_zipcode" class="field_Q">
				<%=View.getOptionCon1002(qBean.getQ_zipcode())%>
			</select>
			<input class="field_Q" type="text" name="q_address" size="50" maxlength="50" value="<%=qBean.getQ_address()%>">		
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
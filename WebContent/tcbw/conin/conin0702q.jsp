<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%@ page import="com.kangdainfo.tcbw.view.conin.CONIN0701F" %>
<%
com.kangdainfo.tcbw.view.conin.CONIN0701F qBean = (com.kangdainfo.tcbw.view.conin.CONIN0701F)request.getAttribute("qBean");
%>
    <div class="queryTitle">查詢視窗</div>
	<table class="queryTable"  border="1">
	<tr>
		<td nowrap class="queryTDLable">系統別：</td>
		<td nowrap class="queryTDInput">
			<select name="q_sysKind" class="field_Q" type="select" onchange="setValue1(this,q_con1007_id);">
	    		<%=View.getOptionCodeKind("SYS", qBean.getQ_sysKind())%>
	    	</select>
	    </td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">通報表單名稱：</td>
		<td nowrap class="queryTDInput">
			<select name="q_con1007_id" class="field_Q" disabled type="select" onchange="setValue2(this,q_name);">
	    		<%=View.getOption("select id,formdName from Con1007Db",qBean.getQ_con1007_id(),false,1)%>
	    	</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="queryTDLable">流程角色名稱：</td>
		<td nowrap class="queryTDInput">
			<select name="q_name" class="field_Q" disabled type="select">
	    		<%=View.getOption("select id,name from Con1014Db",qBean.getQ_name(),false,1)%>
	    	</select>
			
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
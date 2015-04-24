<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.vcos.VCOS0201F qBean = (com.kangdainfo.tcbw.view.vcos.VCOS0201F) request.getAttribute("qBean");
%>
	<table class="queryTable" width="100%">
	<tr>
		<td nowrap width="15%" class="td_form" width="15%">案件編號：</td>
		<td class="td_form_white" colspan="3">
			<input class="field_Q" type="text" size="10" maxlength="11" name="q_applNoS" value="<%=qBean.getQ_applNoS()%>" /> ~ 
			<input class="field_Q" type="text" size="10" maxlength="11" name="q_applNoE" value="<%=qBean.getQ_applNoE()%>" />
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" class="td_form">品名：</td>
		<td nowrap class="td_form_white" colspan="3">
				<input class="field_Q" type="text" size="60" name="q_chProduct" value="<%=qBean.getQ_chProduct()%>"/>
		</td>
	</tr>  
	<tr>			
		<td nowrap width="15%" class="td_form">資料收集日期：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_dataRevDateS",qBean.getQ_dataRevDateS())%> ~
			<%=View.getPopCalendar("field_Q","q_dataRevDateE",qBean.getQ_dataRevDateE())%> 		
		</td>
		<td nowrap class="td_form">發佈單位：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCode("field_Q","q_publDept",qBean.getQ_publDept(),qBean.getQ_publDeptName(),"","CONPUBLDEPT","q_publDeptCodeId",qBean.getQ_publDeptCodeId())%>
		</td>
	</tr>		
	<tr>
		<td nowrap class="td_form">化粧品項目：</td>
	    <td nowrap class="td_form_white" >
            <select class="field_Q" name="q_ingredient">
				<%=View.getOptionCodeKind("CCI", qBean.getQ_ingredient())%>
			</select>
		</td>
		<td nowrap class="td_form">發佈日期：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_publDateS",qBean.getQ_publDateS())%> ~
			<%=View.getPopCalendar("field_Q","q_publDateE",qBean.getQ_publDateE())%> 				
		</td>			
	</tr>
	</table>
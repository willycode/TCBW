<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.drgin.DRGIN0101Q qBean = (com.kangdainfo.tcbw.view.drgin.DRGIN0101Q) request.getAttribute("qBean");
%>
	<table class="table_form" width="100%">
	<tr>
		<td nowrap width="15%" nowrap class="td_form">案件編號範圍：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_applNoS" size="12" maxlength="12" value="<%=qBean.getQ_applNoS()%>">~		
			<input class="field_Q" type="text" name="q_applNoE" size="12" maxlength="12" value="<%=qBean.getQ_applNoE()%>">
		</td>
		<td nowrap width="15%" nowrap class="td_form">發現日期：</td>
		<td nowrap nowrap class="td_form_white"> <!-- q_dateS ~ q_dateE -->
			<%=View.getPopCalendar("field_Q","q_occurDateS",qBean.getQ_occurDateS())%>~
			<%=View.getPopCalendar("field_Q","q_occurDateE",qBean.getQ_occurDateE())%>				
		</td>
	</tr>  
	<tr>
		<td nowrap width="15%" nowrap class="td_form">通報日期：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_notifierRepDateS",qBean.getQ_notifierRepDateS())%>~
			<%=View.getPopCalendar("field_Q","q_notifierRepDateE",qBean.getQ_notifierRepDateE())%>				
		</td>
		<td nowrap width="15%" nowrap class="td_form">收案日期：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_enrolledDateS",qBean.getQ_enrolledDateS())%>~
			<%=View.getPopCalendar("field_Q","q_enrolledDateE",qBean.getQ_enrolledDateE())%>			
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">通報來源：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getRadioBoxOption("field_Q", "q_notifierSource", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGNFS' order by codeId", qBean.getQ_notifierSource())%>						
		</td>
		<td nowrap width="15%" nowrap class="td_form">通報單位：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getRadioBoxOption("field_Q", "q_notifierType", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONNFT1' order by codeId", "", "changeForm(this);")%>
	  	    <br><input class="field_Q" type="text" id="s1" name="q_notifierDept" size="30" maxlength="30" value="" style="display:none"/>
	  	    <input type="button" id="s2" name="btnQryExpense" onClick="popUserJobList();" value="查詢" width="120px" class="field_Q" style="display:none"/>
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">許可證字號：</td>
		<td nowrap nowrap class="td_form_white">
			<select name="q_permitKey" class="field_Q">
				<%=View.getOption("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGPKID' order by codeId", qBean.getQ_permitKey())%>
			</select>
			號：
			<input class="field_Q" type="text" name="q_permitNo" size="6" maxlength="6" value="<%=qBean.getQ_permitNo()%>">	
		</td>
		<td nowrap width="15%" nowrap class="td_form">藥品品名：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_chProduct" size="50" maxlength="100" value="<%=qBean.getQ_chProduct()%>">		
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">藥商：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_applicationName" size="50" maxlength="100" value="<%=qBean.getQ_applicationName()%>">		
		</td>
		<td nowrap width="15%" nowrap class="td_form">風險等級：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getCheckBoxOption("field_Q", "q_drgLev","select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGRKL' order by codeShortName", qBean.getQ_drgLev())%>
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">製造商：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_manufactorName" size="50" maxlength="100" value="<%=qBean.getQ_manufactorName()%>">		
		</td>
		<td nowrap width="15%" nowrap class="td_form">案件狀態：</td>
		<td nowrap nowrap class="td_form_white">
			<select class="field_Q" name="q_status">
    		   <%=View.getOption("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGST1' order by codeId",qBean.getQ_status(), false, 1) %>
		    </select>
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">有效成分/學名：</td>
		<td nowrap nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_ingredient" size="50" maxlength="100" value="<%=qBean.getQ_ingredient()%>">	
		</td>		
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">是否為歷史移轉資料：</td>
		<td nowrap nowrap class="td_form_white" colspan="3">
		    <%=View.getRadioBoxTextOption("field_Q","q_isTrans","Y;是;N;否;",qBean.getQ_isTrans())%>		
		</td>		
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">不良品缺陷：</td>
		<td nowrap nowrap class="td_form_white" colspan="3">
			<%=qBean.getCheckBoxOption("field_Q", "q_mainCode", "q_subCode", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0105' order by codeId", qBean.getQ_mainCode(),qBean.getQ_subCode())%>		
		</td>
	</tr>
	</table>
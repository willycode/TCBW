<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.medin.MEDIN2001Q qBean = (com.kangdainfo.tcbw.view.medin.MEDIN2001Q) request.getAttribute("qBean");
%>
	<table class="table_form" width="100%">
	<tr>
		<td nowrap width="15%" nowrap class="td_form">案件編號：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_applNoS" size="10" maxlength="11" value="<%=qBean.getQ_applNoS()%>">~		
			<input class="field_Q" type="text" name="q_applNoE" size="10" maxlength="11" value="<%=qBean.getQ_applNoE()%>">
		</td>
		<td nowrap width="15%" nowrap class="td_form">發生日期：</td>
		<td nowrap nowrap class="td_form_white"> <!-- q_dateS ~ q_dateE -->
			<%=View.getPopCalendar("field_Q","q_occurDateS",qBean.getQ_occurDateS())%>~
			<%=View.getPopCalendar("field_Q","q_occurDateE",qBean.getQ_occurDateE())%>				
		</td>
	</tr>  
	<tr>
		<td nowrap width="15%" nowrap class="td_form">通報日期：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_notifierRevDateS",qBean.getQ_notifierRevDateS())%>~
			<%=View.getPopCalendar("field_Q","q_notifierRevDateE",qBean.getQ_notifierRevDateE())%>				
		</td>
		<td nowrap width="15%" nowrap class="td_form">收案日期：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_notifierRepDateS",qBean.getQ_notifierRepDateS())%>~
			<%=View.getPopCalendar("field_Q","q_notifierRepDateE",qBean.getQ_notifierRepDateE())%>			
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">通報來源：</td>
		<td nowrap nowrap class="td_form_white">
		    <%=View.getRadioBoxOption("field_Q", "q_drugEventsSources", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='MEDNFS' order by codeId", qBean.getQ_drugEventsSources())%>
		</td>
		<td nowrap width="15%" nowrap class="td_form">通報單位：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_notifyDept" size="50" maxlength="100" value="<%=qBean.getQ_notifyDept()%>">						
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">案例來源：</td>
		<td nowrap nowrap class="td_form_white">	
		    <%=View.getRadioBoxTextOption("field_Q","q_caseSource","in;國內;out;國外",qBean.getQ_caseSource())%>
		</td>
		<td nowrap width="15%" nowrap class="td_form">不良事件類別：</td>
		<td nowrap nowrap class="td_form_white">	
			<%=View.getCheckBoxTextOption("field_Q","q_eventKind","1;不良反應;2;不良品",qBean.getQ_eventKind())%>
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">許可證字：</td>
		<td nowrap class="td_form_white">
		    <select name="q_medPermit" class="field_Q">
				<%=View.getOptionCodeKind("MEDPKID",qBean.getQ_medPermit())%>
			</select>
			字第<input class="field_Q" type="text" name="q_medPermitNumber" size="10" maxlength="6" value="<%=qBean.getQ_medPermitNumber()%>">號
		    <input type="button" name="btnQryExpense" onClick="permitDataQ();" value="查詢" width="120px" class="field_Q" >
		</td>
		<td nowrap width="15%" nowrap class="td_form">病人識別代碼：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_badReactionPatientCode" size="20" maxlength="10" value="<%=qBean.getQ_badReactionPatientCode()%>">						
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">醫材品名：</td>
		<td nowrap nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_medCname" size="50" maxlength="100" value="<%=qBean.getQ_medCname()%>">		
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">申請商：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_medPermitFirm" size="50" maxlength="100" value="<%=qBean.getQ_medPermitFirm()%>">		
		</td>
	</tr>
	<tr>	
		<td nowrap width="15%" nowrap class="td_form">製造廠：</td>
		<td nowrap nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_medFactory" size="50" maxlength="100" value="<%=qBean.getQ_medFactory()%>">		
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">醫材主類別：</td>
		<td nowrap class="td_form_white">
			<select class="field_Q" name="q_medMainCategory">
				<%=View.getOptionCodeKind("MEDMCT", qBean.getQ_medMainCategory()) %>
			</select>		
		</td>
		<td nowrap width="15%" nowrap class="td_form">醫材次類別：</td>
		<td nowrap nowrap class="td_form_white">
			<select class="field_Q" name="q_medSecCategory">
				<%=View.getOptionCodeKind("MEDSCT", qBean.getQ_medSecCategory()) %>
			</select>		
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">不良反應結果：</td>
		<td nowrap class="td_form_white" colspan="3">
		    <%=View.getCheckBoxOption("field_Q","q_badReactionResults","select codeId,codeName from CommonCode where commonCodeKind.codeKindId = 'MEDAD1'",qBean.getQ_badReactionResults(),4)  %>
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">NCAR通報狀況：</td>
		<td nowrap nowrap class="td_form_white" colspan="3">
			<%=View.getRadioBoxTextOption("field_Q","q_ncarResult","1;通報;2;不通報",qBean.getQ_ncarResult())%>		
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">不良品事件等級：</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getCommonCheckBoxOption("field_Q","q_eventClass","MEDEVC",qBean.getQ_eventClass())%>		
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">案件狀態：</td>
		<td nowrap nowrap class="td_form_white">	
			<span>不良反應
			<select class="field_Q" name="q_caseStatus_eventType_1">
				<%=View.getOptionCodeKind("MEDSTATUS2", qBean.getQ_caseStatus_eventType_1()) %>
			</select>
			</span><br/>
			<span>不良品
			<select class="field_Q" name="q_caseStatus_eventType_2">
				<%=View.getOptionCodeKind("MEDSTATUS2", qBean.getQ_caseStatus_eventType_2()) %>
			</select>
			</span>
		</td>
		<td nowrap width="15%" nowrap class="td_form">是否為歷史移轉資料：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getRadioBoxTextOption("field_Q","q_trans","Y;是;N;否",qBean.getQ_trans())%>		
		</td>
	</tr>
	</table>
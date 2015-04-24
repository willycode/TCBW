<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.medin.MEDIN5301Q qBean = (com.kangdainfo.tcbw.view.medin.MEDIN5301Q) request.getAttribute("qBean");
%>
	<table class="table_form" width="100%">
	<tr>
		<td nowrap width="15%" nowrap class="td_form">案件編號：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_applNoS" size="10" maxlength="11" value="<%=qBean.getQ_applNoS()%>">~		
			<input class="field_Q" type="text" name="q_applNoE" size="10" maxlength="11" value="<%=qBean.getQ_applNoE()%>">
		</td>
		<td nowrap width="15%" nowrap class="td_form">發生日期：</td>
		<td nowrap class="td_form_white"> <!-- q_dateS ~ q_dateE -->
			<%=View.getPopCalendar("field_Q","q_occurDateS",qBean.getQ_occurDateS())%>~
			<%=View.getPopCalendar("field_Q","q_occurDateE",qBean.getQ_occurDateE())%>				
		</td>
	</tr>  
	<tr>
		<td nowrap width="15%" nowrap class="td_form">通報日期：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_notifierRevDateS",qBean.getQ_notifierRevDateS())%>~
			<%=View.getPopCalendar("field_Q","q_notifierRevDateE",qBean.getQ_notifierRevDateE())%>				
		</td>
		<td nowrap width="15%" nowrap class="td_form">收案日期：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_notifierRepDateS",qBean.getQ_notifierRepDateS())%>~
			<%=View.getPopCalendar("field_Q","q_notifierRepDateE",qBean.getQ_notifierRepDateE())%>			
		</td>
	</tr>
	<tr>
		<!--  
		<td nowrap width="15%" nowrap class="td_form">通報來源：</td>
		<td nowrap class="td_form_white">
			 <%=View.getCheckBoxOption("field_Q", "q_notifierSource", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGNFS' order by codeId", qBean.getQ_notifierSource())%>
		</td>
		-->
		<td nowrap width="15%" nowrap class="td_form">通報單位：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_notifyDept" size="50" maxlength="100" value="<%=qBean.getQ_notifyDept()%>">						
		</td>
		<td nowrap width="15%" nowrap class="td_form">病人識別代碼：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_patientId" size="20" maxlength="10" value="<%=qBean.getQ_patientId()%>">						
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">案例來源</td>
		<td nowrap class="td_form_white">				
			<input class="field_Q" type="radio" name="q_caseSource" value="out" <%if("out".equals(qBean.getQ_caseSource())) out.print("checked"); %> >國外
			<input class="field_Q" type="radio" name="q_caseSource" value="in" <%if("in".equals(qBean.getQ_caseSource())) out.print("checked"); %>>國內
		</td>	
		<td nowrap width="15%" nowrap class="td_form">核準文號：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_fdaNum" maxlength="20" size="40" value="<%=qBean.getQ_fdaNum()%>" />&nbsp;&nbsp;
            <%=View.getRadioBoxTextOption("field_Q","q_fdaOptions","1;查驗登記用;2;學術研究用",qBean.getQ_fdaOptions())%>
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">列管單位：</td>
		<td nowrap class="td_form_white">
			<%=View.getRadioBoxTextOption("field_Q","q_approvedUnits","1;醫事司;2;食品藥物管理署;3;其他",qBean.getQ_approvedUnits())%>
			<input class="field_Q" type="text" name="q_approvedUnitsOther" maxlength="30" size="20"  value="<%=qBean.getQ_approvedUnitsOther()%>"  />		
		</td>
		<td nowrap width="15%" nowrap class="td_form">許可證字：</td>
		<td nowrap class="td_form_white" colspan="3">
		    <select name="q_medPermit" class="field_Q">
				<%=View.getOptionCodeKind("MEDPKID",qBean.getQ_medPermit())%>
			</select>
			字第<input class="field_Q" type="text" name="q_medPermitNumber" size="10" maxlength="6" value="<%=qBean.getQ_medPermitNumber()%>">號
		    <input type="button" name="btnQryExpense" onClick="permitDataQ();" value="查詢" width="120px" class="field_Q" >
		</td>
	</tr>
		<tr>
		<td nowrap width="15%" nowrap class="td_form">醫材品名：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_medTestMedical" size="50" maxlength="100" value="<%=qBean.getQ_medTestMedical()%>">		
		</td>
		<td nowrap width="15%" nowrap class="td_form">製造廠：</td>
		<td nowrap nowrap class="td_form_white">
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
		<td nowrap width="15%" nowrap class="td_form" >案件狀態：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getCheckBoxOption("field_Q","q_status","select codeId,codeName from CommonCode where commonCodeKind.codeKindId='MEDSTATUS1' order by codeId ",qBean.getQ_status(),4)%>
		</td>
		<td nowrap width="15%" nowrap class="td_form">是否為歷史移轉資料：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getRadioBoxTextOption("field_Q","q_trans","Y;是;N;否",qBean.getQ_trans())%>		
		</td>
	</tr>
	</table>
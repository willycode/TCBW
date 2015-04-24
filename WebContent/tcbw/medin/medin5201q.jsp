<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.medin.MEDIN5201F qBean = (com.kangdainfo.tcbw.view.medin.MEDIN5201F) request.getAttribute("qBean");
%>
	<table class="table_form" width="100%">
	<tr>
		<td nowrap class="td_form" width="15%">案件編號：</td>
		<td nowrap class="td_form_white" width="35%">
			<input class="field_Q" type="text" name="q_applNoS" size="10" maxlength="10" value="">
			- 
			<input class="field_Q" type="text" name="q_applNoE" size="10" maxlength="10" value="">
		</td>
		<td nowrap class="td_form" width="15%">通報日期起訖：</td>
		<td nowrap class="td_form_white" width="35%">
			<%=View.getPopCalendar("field_Q","q_notifierRepDateS","")%>~<%=View.getPopCalendar("field_Q","q_notifierRepDateE","")%>
		</td>
	</tr> 
	<tr>
		<td nowrap class="td_form">許可證字號：</td>
		<td nowrap class="td_form_white" colspan="3">
		    <select name="q_medPermit" class="field_Q" >
				<%=View.getOptionCodeKind("MEDPKID", qBean.getQ_medPermit())%>
			</select>
			字第<input class="field_Q" type="text" name="q_medPermitNumber" size="10" maxlength="6" value="<%=qBean.getQ_medPermitNumber()%>">號
		</td>
		<!-- 此查詢無作用
		<td nowrap class="td_form">案件狀態：</td>
		<td nowrap class="td_form_white">
		    <select class="field_Q" name="q_status">
				<%=View.getOptionCodeKind("MEDSTATUS2", qBean.getQ_status()) %>
			</select>
		</td>
		 -->
	</tr>
	 <!--  
	<tr>
		<td nowrap class="td_form" width="15%">發生日期起訖：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_occurDateS","")%>~<%=View.getPopCalendar("field_Q","q_occurDateE","")%>
		</td>
		<td nowrap class="td_form" width="15%">收案日期起訖：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_notifierRevDateS","")%>~<%=View.getPopCalendar("field_Q","q_notifierRevDateS","")%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="15%">通報來源：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_meanS" size="10" maxlength="10" value="">
		</td>
		<td nowrap class="td_form" width="15%">通報單位：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q"  type="text" name="q_meanS" size="10" maxlength="10" value="">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="15%">案例來源：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_caseSource" size="10" maxlength="10" value="">
		</td>
		<td nowrap class="td_form" width="15%">核准文號：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_mean" size="10" maxlength="10" value="">
		</td>
	</tr>	
	<tr>
		<td nowrap class="td_form" width="15%">許可證字號：</td>
		<td nowrap class="td_form_white">
			字<input class="field_Q" type="text" name="q_medPermit" size="10" maxlength="10" value="">
			號<input class="field_Q" type="text" name="q_medPermitNumber" size="10" maxlength="10" value="">
		</td>
		<td nowrap class="td_form" width="15%">列管單位：</td>
		<td nowrap class="td_form_white" >
			<input class="field_Q" type="text" name="q_meanS" size="10" maxlength="10" value="">
		</td>
	</tr>  
	<tr>
		<td nowrap class="td_form" width="15%">醫材品名：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_mean" size="60" maxlength="100" value="">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="15%">製造廠：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_mean" size="60" maxlength="100" value="">
		</td>
    </tr>
	<tr>
		<td nowrap class="td_form" width="15%">醫材主類別：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCode("field_Q","q_medMainCategory","","","","CM","q_ovrRuleInspCodeId","",new int[]{2,2,10,20})%>
		</td>
		<td nowrap class="td_form" width="15%">醫材次類別：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCode("field_Q","q_medSecCategory","","","","CM","q_ovrRuleInspCodeId","",new int[]{2,2,10,20})%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" width="15%">不良反應結果：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_mean" size="60" maxlength="100" value="">
		</td>
		<td nowrap class="td_form" width="15%">案件狀態：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCode("field_Q","q_status","","","","CM","q_ovrRuleInspCodeId","",new int[]{2,2,10,20})%>
		</td>
	</tr>
	-->
	</table>
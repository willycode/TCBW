<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.comple.COMPLE0201F qBean = (com.kangdainfo.tcbw.view.comple.COMPLE0201F) request.getAttribute("qBean");
%>
	<table class="queryTable" width="100%">
	<tr>
		<td class="td_form">案件編號：</td>
		<td class="td_form_white" colspan="5">
			<input class="field_Q" type="text" name="q_applNo" size="11" maxlength="11" value="<%=qBean.getQ_applNo()%>">
		</td>
	</tr>  
	<tr>
		<td class="td_form">發現日期：</td>
		<td class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_occurDateS",qBean.getQ_occurDateS())%>~<%=View.getPopCalendar("field_Q","q_occurDateE",qBean.getQ_occurDateE())%>		
		</td>
		<td class="td_form">通報日期：</td>
		<td class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_notifierRepDateS",qBean.getQ_notifierRepDateS())%>~<%=View.getPopCalendar("field_Q","q_notifierRepDateE",qBean.getQ_notifierRepDateE())%>		
		</td>
		<td class="td_form">收案日期：</td>
		<td class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_enrolledDateS",qBean.getQ_enrolledDateS())%>~<%=View.getPopCalendar("field_Q","q_enrolledDateE",qBean.getQ_enrolledDateE())%>		
		</td>
	</tr>
	<tr>
		<td class="td_form">通報來源：</td>
		<td class="td_form_white">
			<%=View.getRadioBoxOption("field_Q", "q_notifierSource", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGNFS' order by codeId", qBean.getQ_notifierSource())%>
		</td>
		<td class="td_form">通報單位：</td>
		<td class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_notifierDept" size="25" maxlength="50" value="<%=qBean.getQ_notifierDept()%>">
		</td>		
	</tr>
	<tr>
		<td nowrap class="td_form">許可證字號：</td>
		<td nowrap class="td_form_white" colspan="5">
			<select name="q_permitKey" class="field_Q">
				<%=View.getOption("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGPKID' order by codeId", qBean.getQ_permitKey())%>
			</select>			 
			字<input class="field_Q" type="text" name="q_permitNo" size="6" maxlength="6" value="<%=qBean.getQ_permitNo()%>">號
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">藥品品名：</td>
		<td nowrap class="td_form_white" colspan="5">
			<input class="field_Q" type="text" name="q_productName" size="60" maxlength="100" value="<%=qBean.getQ_productName()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">學名：</td>
		<td nowrap class="td_form_white" colspan="5">
			<input class="field_Q" type="text" name="q_scientificName" size="60" maxlength="100" value="<%=qBean.getQ_scientificName()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">申請商：</td>
		<td nowrap class="td_form_white" colspan="5">
			<input class="field_Q" type="text" name="q_applicationName" size="50" maxlength="50" value="<%=qBean.getQ_applicationName()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">製造商：</td>
		<td nowrap class="td_form_white" colspan="5">
			<input class="field_Q" type="text" name="q_manufactorName" size="50" maxlength="50" value="<%=qBean.getQ_manufactorName()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">通報事件後果：</td>
		<td nowrap class="td_form_white" colspan="5">
			<%=View.getCheckBoxTextOption("field_Q", "q_conSequence","1;藥效改變;2;不良反應發生、強度增強或頻率增加",qBean.getQ_conSequence())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">藥品成分資訊：</td>
		<td nowrap class="td_form_white" colspan="5">
			<input class="field_Q" type="checkbox" name="q_medNti" value="Y" <%=qBean.getQ_medNti().equals("Y")?"checked":""%> >NTI Drugs<br>
			藥理治療分類(ATC code)：<input class="field_Q" type="text" name="q_medAtcCode" size="20" maxlength="50" value="<%=qBean.getQ_medAtcCode()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">療效不等評估結果：</td>
		<td nowrap class="td_form_white" colspan="5">
			<%=View.getRadioBoxOption("field_Q", "q_assessResult", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG2RKL' order by codeId", qBean.getQ_assessResult())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">案件狀態：</td>
		<td nowrap class="td_form_white" colspan="5">
			<select class="field_Q" name="q_status">
				<%=View.getOptionCodeKind("DRG0310", qBean.getQ_status()) %>
			</select>
		</td>
	</tr>
	</table>
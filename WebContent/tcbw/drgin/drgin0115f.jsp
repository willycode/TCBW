<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0114F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>

<table id="Tab9" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form">學名藥/原廠藥</td>
			<td nowrap class="td_form_white" colspan="3">
			  <%=View.getRadioBoxOption("field", "medicineType09", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0101' order by codeId", obj.getMedicineType09())%>		
		    </td>		
		</tr>
		<tr>
			<td nowrap class="td_form">國產/輸入</td>
			<td nowrap class="td_form_white" colspan="3">
               <%=View.getRadioBoxTextOption("field", "produceType09", "1;國產;2;進口;", obj.getProduceType09())%>		
			 </td>		
		</tr>
		<tr>
			<td nowrap class="td_form">批號範圍</td>
			<td nowrap class="td_form_white" colspan="3">
               <%=View.getRadioBoxOption("field", "lotType09", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0107' order by codeId", obj.getLotType09())%>		
		    </td>		
		</tr>
		<tr>
			<td nowrap class="td_form">廠商不良品缺陷</td>
			<td nowrap class="td_form_white" colspan="3">
		       <%=View.getRadioBoxOption("field", "defect09", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0108' order by codeId", obj.getDefect09())%>
		       (請描述)：<input class="field" type="text" name="defectOther09" size="40" maxlength="50" value="<%=obj.getDefectOther09()%>"/>		
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">廠商調查結果</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getRadioBoxOption("field", "survey09", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0109' order by codeId", obj.getSurvey09())%>
		       (請描述)：<input class="field" type="text" name="surveyOther09" size="40" maxlength="50" value="<%=obj.getSurveyOther09()%>"/>		
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">廠商預防措施</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getRadioBoxOption("field", "precaution09", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0110' order by codeId", obj.getPrecaution09())%>
		       (請描述)：<input class="field" type="text" name="precautionOther09" size="40" maxlength="50" value="<%=obj.getPrecautionOther09()%>"/>		
			</td>		
		</tr>
</table>
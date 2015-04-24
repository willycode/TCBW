<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.vmed.VMED0101F qBean = (com.kangdainfo.tcbw.view.vmed.VMED0101F) request.getAttribute("qBean");
%>
	<table class="table_form" width="100%" height="100%">
	<tr>
		<td nowrap class="td_form">案件編號：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_applNoS" size="11" maxlength="11" value="<%=qBean.getQ_applNoS()%>">~
			<input class="field_Q" type="text" name="q_applNoE" size="11" maxlength="11" value="<%=qBean.getQ_applNoE()%>">
		</td>
		<td nowrap class="td_form">案件狀態：</td>
		<td nowrap class="td_form_white">
			<select class="field_Q" type="select" name="q_status">
				<%=View.getOption("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='MEDQTSTATUS' order by codeId", qBean.getQ_status())%>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">發佈單位：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCode("field_Q","q_publDept",qBean.getQ_publDept(),qBean.getQ_publDeptName(),"","CONPUBLDEPT","q_publDeptCodeId",qBean.getQ_publDeptCodeId())%>
		</td>
		<td nowrap class="td_form">警訊類別：</td>
		<td nowrap class="td_form_white">
			<%=View.getRadioBoxOption("field_Q","q_situation","select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONWARNING' order by codeId",qBean.getQ_situation())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">醫材主類別：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCode("field_Q","q_medMainCategory",qBean.getQ_medMainCategory(),qBean.getQ_medMainCategoryName(),"","MEDMCT","q_medMainCategoryCodeId",qBean.getQ_medMainCategoryCodeId())%>
		</td>
		<td nowrap class="td_form">產品名稱：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_chProduct" size="50" maxlength="50" value="<%=qBean.getQ_chProduct()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">國外回收等級：</td>
		<td nowrap class="td_form_white">
			<%=View.getRadioBoxOption("field_Q","q_recycleclass","select codeId, codeName from CommonCode where commonCodeKind.codeKindId='MEDRCCLASS' order by codeId",qBean.getQ_recycleclass())%>
		</td>
		<td nowrap class="td_form">資料收集日期：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalndar("field_Q","q_dataRevDateS",qBean.getQ_dataRevDateS())%>~<%=View.getPopCalndar("field_Q","q_dataRevDateE",qBean.getQ_dataRevDateE())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">發佈日期：</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getPopCalndar("field_Q","q_publDateS",qBean.getQ_publDateS())%>~<%=View.getPopCalndar("field_Q","q_publDateE",qBean.getQ_publDateE())%>
		</td>		
	</tr>
	<tr>
		<td nowrap class="td_form">製造廠：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_manufactorName" size="100" maxlength="100" value="<%=qBean.getQ_manufactorName()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">警訊摘要：</td>
		<td nowrap class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_contextsummary" size="125" maxlength="125" value="<%=qBean.getQ_contextsummary()%>">
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">產品批號：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_productlotNo" size="25" maxlength="25" value="<%=qBean.getQ_productlotNo()%>">
		</td>
		<td nowrap class="td_form">影響地區：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_effectarea" size="25" maxlength="25" value="<%=qBean.getQ_effectarea()%>">
		</td>
	</tr>
	</table>
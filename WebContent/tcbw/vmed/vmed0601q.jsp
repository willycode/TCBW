<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.vmed.VMED0601F qBean = (com.kangdainfo.tcbw.view.vmed.VMED0601F) request.getAttribute("qBean");
%>
	<table class="table_form" width="100%" height="100%">
	<tr>
		<td class="td_form">案件編號：</td>
		<td class="td_form_white">
			<input class="field_Q" type="text" name="q_applNoS" size="11" maxlength="11" value="<%=qBean.getQ_applNoS()%>">~
			<input class="field_Q" type="text" name="q_applNoE" size="11" maxlength="11" value="<%=qBean.getQ_applNoE()%>">
		</td>
		<td class="td_form">發佈單位：</td>
		<td class="td_form_white">
			<%=View.getPopCode("field_Q","q_publDept",qBean.getQ_publDept(),qBean.getQ_publDeptName(),"","CONPUBLDEPT","q_publDeptCodeId",qBean.getQ_publDeptCodeId())%>
		</td>
	</tr>
	<tr>
		<td class="td_form">警訊類別：</td>
		<td class="td_form_white">
			<%=View.getRadioBoxOption("field_Q","q_situation","select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONWARNING' order by codeId",qBean.getQ_situation())%>
		</td>
		<td class="td_form">醫材主類別：</td>
		<td class="td_form_white">
			<%=View.getPopCode("field_Q","q_medMainCategory",qBean.getQ_medMainCategory(),qBean.getQ_medMainCategoryName(),"","MEDMCT","q_medMainCategoryCodeId",qBean.getQ_medMainCategoryCodeId())%>
		</td>
	</tr>
	<tr>
		<td class="td_form">產品名稱：</td>
		<td class="td_form_white">
			<input class="field_Q" type="text" name="q_chProduct" size="50" maxlength="50" value="<%=qBean.getQ_chProduct()%>">
		</td>
		<td class="td_form">發佈日期：</td>
		<td class="td_form_white" colspan="3">
			<%=View.getPopCalndar("field_Q","q_publDateS",qBean.getQ_publDateS())%>~<%=View.getPopCalndar("field_Q","q_publDateE",qBean.getQ_publDateE())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">許可證字號：</td>
		<td nowrap class="td_form_white">
			<select name="q_permitKey" class="field_Q">
				<%=View.getOption("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='MEDPKID' order by codeId", qBean.getQ_permitKey())%>
			</select>			 
			第<input class="field_Q" type="text" name="q_permitNo" size="6" maxlength="6" value="<%=qBean.getQ_permitNo()%>">號
		</td>
		<td class="td_form">中文品名：</td>
		<td class="td_form_white">
			<input class="field_Q" type="text" name="q_chProduct72" size="50" maxlength="50" value="<%=qBean.getQ_chProduct72()%>">
		</td>
	</tr>
	<tr>
		<td class="td_form">許可證持有商：</td>
		<td class="td_form_white">
			<input class="field_Q" type="text" name="q_applicationName" size="50" maxlength="50" value="<%=qBean.getQ_applicationName()%>">
		</td>
		<td class="td_form">製造廠：</td>
		<td class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_manufactorName" size="50" maxlength="100" value="<%=qBean.getQ_manufactorName()%>">
		</td>
	</tr>
	</table>
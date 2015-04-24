<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.vmed.VMED1201F qBean = (com.kangdainfo.tcbw.view.vmed.VMED1201F) request.getAttribute("qBean");
%>
	<table class="table_form" width="100%" height="100%">
	<tr>
		<td class="td_form" width="15%">案件編號：</td>
		<td class="td_form_white" width="35%">
			<input class="field_Q" type="text" name="q_applNoS" size="11" maxlength="11" value="<%=qBean.getQ_applNoS()%>">~
			<input class="field_Q" type="text" name="q_applNoE" size="11" maxlength="11" value="<%=qBean.getQ_applNoE()%>">
		</td>
		<td class="td_form" width="15%">醫材主類別：</td>
		<td class="td_form_white" width="35%">
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
			<%=View.getPopCalndar("field_Q","q_publDateS",qBean.getQ_publDateS())%>~
			<%=View.getPopCalndar("field_Q","q_publDateE",qBean.getQ_publDateE())%>
		</td>
	</tr>
	<tr>
		<td class="td_form">FDA收文字號：</td>
		<td class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_fdareceiveNo" size="50" value="<%=qBean.getQ_fdareceiveNo()%>">
		</td>
	</tr>
	<tr>
		<td class="td_form">回收摘要：</td>
		<td class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_posesummary" size="50" value="<%=qBean.getQ_posesummary()%>">
		</td>
	</tr>
    <tr>
		<td class="td_form">廠商回收完成字號：</td>
		<td class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_completereceiveno" size="50" value="<%=qBean.getQ_completereceiveno()%>">
		</td>
	</tr>
	</table>
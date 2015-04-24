<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.vdrg.VDRG0201F qBean = (com.kangdainfo.tcbw.view.vdrg.VDRG0201F) request.getAttribute("qBean");
%>
	<table class="table_form" width="100%" height="100%">
	<tr>
		<td class="td_form">案件編號：</td>
		<td class="td_form_white">
			<input class="field_Q" type="text" name="q_applNoS" size="13" maxlength="13" value="<%=qBean.getQ_applNoS()%>">~
			<input class="field_Q" type="text" name="q_applNoE" size="13" maxlength="13" value="<%=qBean.getQ_applNoE()%>">
		</td>
		<td class="td_form">發佈單位：</td>
		<td class="td_form_white">
			<%=View.getPopCode("field_Q","q_publDept",qBean.getQ_publDept(),qBean.getQ_publDeptName(),"","CONPUBLDEPT","q_publDeptCodeId",qBean.getQ_publDeptCodeId())%>
		</td>
	</tr>
	<tr>
		<td class="td_form">狀況：</td>
		<td class="td_form_white">
			<%=View.getCheckBoxOption("field_Q","q_situation","select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONWARNING' order by codeId ",qBean.getQ_situation())%>
		</td>
		<td class="td_form">原始發佈日期：</td>
		<td class="td_form_white">
			<%=View.getPopCalndar("field_Q","q_publDateS",qBean.getQ_publDateS())%>~
			<%=View.getPopCalndar("field_Q","q_publDateE",qBean.getQ_publDateE())%>
		</td>
	</tr>
	<tr>
		<td class="td_form">商品名：</td>
		<td class="td_form_white">
			<input class="field_Q" type="text" name="q_chProduct" size="50" maxlength="100" value="<%=qBean.getQ_chProduct()%>">
		</td>
		<td class="td_form">學名：</td>
		<td class="td_form_white">
			<input class="field_Q" type="text" name="q_scientificName" size="50" maxlength="100" value="<%=qBean.getQ_scientificName()%>">
		</td>
	</tr>
	<tr>
		<td class="td_form">品質異常型態：</td>
		<td class="td_form_white" colspan="3">
			<%=View.getCheckBoxOption("field_Q","q_qualitywarningtype","select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGWNTP' ", qBean.getQ_qualitywarningtype(),6)%>
		</td>
	</tr>
	<tr>
		<td class="td_form">回收型態：</td>
		<td class="td_form_white" colspan="3">
			<%=View.getCheckBoxOption("field_Q","q_recycleType","select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGRECTP' order by codeId",qBean.getQ_recycleType())%>
		</td>
	</tr>
	<tr>
		<td class="td_form">產品是否進口：</td>
		<td class="td_form_white">
			<%=View.getRadioBoxTextOption("field_Q","q_isImport","Y;是;N;否",qBean.getQ_isImport())%>
		</td>
		<td class="td_form">廠商回覆狀況：</td>
		<td class="td_form_white">
			<%=View.getCheckBoxTextOption("field_Q","q_isReply","Y;已回覆;N;未回覆;",qBean.getQ_isReply())%>
		</td>
	</tr>
	</table>
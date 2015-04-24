<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.vmed.VMED1101F qBean = (com.kangdainfo.tcbw.view.vmed.VMED1101F) request.getAttribute("qBean");
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
			<%=View.getPopCalndar("field_Q","q_publDateS",qBean.getQ_publDateS())%>~
			<%=View.getPopCalndar("field_Q","q_publDateE",qBean.getQ_publDateE())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">公告日期：</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getPopCalndar("field_Q","q_publdateS",qBean.getQ_publdateS())%>~
			<%=View.getPopCalndar("field_Q","q_publdateE",qBean.getQ_publdateE())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">是否發佈新聞稿：</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getRadioBoxTextOption("field_Q","q_ispublnews","Y;是;N;否",qBean.getQ_ispublnews())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">是否發佈消費者知識服務網：</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getRadioBoxTextOption("field_Q","q_ispublconsumer","Y;是;N;否",qBean.getQ_ispublconsumer())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">是否發佈新聞稿：</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=View.getRadioBoxTextOption("field_Q","q_isfdaweb","Y;是;N;否",qBean.getQ_isfdaweb())%>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">燈號：</td>
		<td nowrap class="td_form_white" colspan="3">
		    <%=View.getRadioBoxOption("field_Q", "q_lamp", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONLAMP' order by codeId", qBean.getQ_lamp())%>
		</td>
	</tr>
	</table>
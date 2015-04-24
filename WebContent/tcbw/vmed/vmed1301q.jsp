<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
	com.kangdainfo.tcbw.view.vmed.VMED1301F qBean = (com.kangdainfo.tcbw.view.vmed.VMED1301F) request.getAttribute("qBean");
%>
	<table class="table_form" width="100%" height="100%">
	<tr>
		<td class="td_form">案件編號：</td>
		<td class="td_form_white">
			<input class="field_Q" type="text" name="q_applNoS" size="11" maxlength="11" value="<%=qBean.getQ_applNoS()%>">~
			<input class="field_Q" type="text" name="q_applNoE" size="11" maxlength="11" value="<%=qBean.getQ_applNoE()%>">
		</td>
		<td class="td_form">案件狀態：</td>
		<td class="td_form_white">
			<select class="field_Q" name="q_status">
				<%=View.getOption("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='MEDQTSTATUS' order by codeId", qBean.getQ_status())%>
			</select>
		</td>
	</tr>
	<tr>
		<td class="td_form">發佈單位：</td>
		<td class="td_form_white">
			<%=View.getPopCode("field_Q","q_publDept",qBean.getQ_publDept(),qBean.getQ_publDeptName(),"","CONPUBLDEPT","q_publDeptCodeId",qBean.getQ_publDeptCodeId())%>
		</td>
		<td class="td_form">警訊類別：</td>
		<td class="td_form_white">
			<%=View.getRadioBoxOption("field_Q","q_situation","select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONWARNING' order by codeId",qBean.getQ_situation())%>
		</td>
	</tr>
	<tr>
		<td class="td_form">醫材主類別：</td>
		<td class="td_form_white">
			<%=View.getPopCode("field_Q","q_medMainCategory",qBean.getQ_medMainCategory(),qBean.getQ_medMainCategoryName(),"","MEDMCT","q_medMainCategoryCodeId",qBean.getQ_medMainCategoryCodeId())%>
		</td>
		<td class="td_form">產品名稱：</td>
		<td class="td_form_white">
			<input class="field_Q" type="text" name="q_chProduct" size="50" maxlength="50" value="<%=qBean.getQ_chProduct()%>">
		</td>
	</tr>
	<tr>
		<td class="td_form">發佈日期：</td>
		<td class="td_form_white" >
			<%=View.getPopCalndar("field_Q","q_publDateS",qBean.getQ_publDateS())%>~
			<%=View.getPopCalndar("field_Q","q_publDateE",qBean.getQ_publDateE())%>
		</td>
		<td nowrap class="td_form">公告日期：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalndar("field_Q","q_publdateS7005",qBean.getQ_publdateS7005())%>~
			<%=View.getPopCalndar("field_Q","q_publdateE7005",qBean.getQ_publdateE7005())%>
		</td>
	</tr>
	<tr>
		<td class="td_form">國外回收等級：</td>
		<td class="td_form_white">
			<%=View.getRadioBoxOption("field_Q","q_recycleclass","select codeId, codeName from CommonCode where commonCodeKind.codeKindId='MEDRCCLASS' order by codeId",qBean.getQ_recycleclass())%>
		</td>
		<td class="td_form">資料收集日期：</td>
		<td class="td_form_white">
			<%=View.getPopCalndar("field_Q","q_dataRevDateS",qBean.getQ_dataRevDateS())%>~
			<%=View.getPopCalndar("field_Q","q_dataRevDateE",qBean.getQ_dataRevDateE())%>
		</td>
	</tr>
	<tr>
		<td class="td_form">製造廠：</td>
		<td class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_manufactorName" size="100" value="<%=qBean.getQ_manufactorName()%>">
		</td>
	</tr>
	<tr>
		<td class="td_form">警訊摘要：</td>
		<td class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_contextsummary" size="125" value="<%=qBean.getQ_contextsummary()%>">
		</td>
	</tr>
	<tr>
		<td class="td_form">產品批號：</td>
		<td class="td_form_white">
			<input class="field_Q" type="text" name="q_productlotNo" size="25" maxlength="25" value="<%=qBean.getQ_productlotNo()%>">
		</td>
		<td class="td_form">影響地區：</td>
		<td class="td_form_white">
			<input class="field_Q" type="text" name="q_effectarea" size="25" maxlength="25" value="<%=qBean.getQ_effectarea()%>">
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
			<input class="field_Q" type="text" name="q_chProduct7002" size="50" value="<%=qBean.getQ_chProduct7002()%>">
		</td>
	</tr>
	<tr>
		<td class="td_form">許可證持有商：</td>
		<td class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_applicationName" size="50" value="<%=qBean.getQ_applicationName()%>">
		</td>
	</tr>
	<tr>
		<td class="td_form">醫材型號：</td>
		<td class="td_form_white" colspan="3">
			<input class="field_Q" type="text" name="q_medModel" size="50"  value="<%=qBean.getQ_medModel()%>">
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
		<td nowrap class="td_form_white">
		    <%=View.getRadioBoxOption("field_Q", "q_lamp", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONLAMP' order by codeId", qBean.getQ_lamp())%>
		</td>
		<td nowrap width="15%" nowrap class="td_form">是否為歷史移轉資料：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getRadioBoxTextOption("field_Q","q_trans","Y;是;N;否",qBean.getQ_trans())%>		
		</td>
	</tr>
	</table>
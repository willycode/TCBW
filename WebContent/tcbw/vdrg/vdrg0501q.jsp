<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.vdrg.VDRG0501F qBean = (com.kangdainfo.tcbw.view.vdrg.VDRG0501F) request.getAttribute("qBean");
%>
	<table class="table_form" width="100%" height="100%">
	<tr>
		<td class="td_form">案件編號：</td>
		<td class="td_form_white">
			<input class="field_Q" type="text" name="q_applNoS" size="13" maxlength="13" value="<%=qBean.getQ_applNoS()%>">~
			<input class="field_Q" type="text" name="q_applNoE" size="13" maxlength="13" value="<%=qBean.getQ_applNoE()%>">
		</td>
		<td class="td_form">資料收集日期：</td>
		<td class="td_form_white">
			<%=View.getPopCalndar("field_Q","q_dataRevDateS",qBean.getQ_dataRevDateS())%>~
			<%=View.getPopCalndar("field_Q","q_dataRevDateE",qBean.getQ_dataRevDateE())%>
		</td>
	</tr>
	<tr>
		<td class="td_form">案件狀態：</td>
		<td class="td_form_white">
			<%=View.getCommonCheckBoxOption("field_Q","q_status","DRGQTSTATUS", qBean.getQ_status())%>
		</td>
		<td class="td_form">原始發佈日期：</td>
		<td class="td_form_white">
			<%=View.getPopCalndar("field_Q","q_publDateS",qBean.getQ_publDateS())%>~
			<%=View.getPopCalndar("field_Q","q_publDateE",qBean.getQ_publDateE())%>
		</td>
	</tr>
	<tr>
		<td class="td_form">發佈單位：</td>
		<td class="td_form_white">
			<%=View.getPopCode("field_Q","q_publDept",qBean.getQ_publDept(),qBean.getQ_publDeptName(),"","CONPUBLDEPT","q_publDeptCodeId",qBean.getQ_publDeptCodeId())%>
		</td>
		<td class="td_form">是否為轉發：</td>
		<td class="td_form_white">
			<%=View.getRadioBoxTextOption("field_Q","q_istransfer","Y;是;N;否",qBean.getQ_istransfer())%>
		</td>
	</tr>
	<tr>
		<td class="td_form">狀況：</td>
		<td class="td_form_white">
			<%=View.getCheckBoxOption("field_Q","q_situation","select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONWARNING' order by codeId ",qBean.getQ_situation())%>
		</td>
		<td class="td_form">商品名：</td>
		<td class="td_form_white">
			<input class="field_Q" type="text" name="q_chProduct" size="50" maxlength="100" value="<%=qBean.getQ_chProduct()%>">
		</td>
	</tr>
	<tr>
		<td class="td_form">學名：</td>
		<td class="td_form_white">
			<input class="field_Q" type="text" name="q_scientificName" size="50" maxlength="100" value="<%=qBean.getQ_scientificName()%>">
		</td>
		<td class="td_form">廠商：</td>
		<td class="td_form_white">
			<input class="field_Q" type="text" name="q_druggist" size="50" maxlength="200" value="<%=qBean.getQ_druggist()%>">
		</td>
	</tr>
	<tr>
		<td class="td_form">許可證字：</td>
		<td class="td_form_white">
			<select class="field_Q" type="select" name="q_permitKey">
				<%=View.getOption("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGPKID' order by codeId ", qBean.getQ_permitKey())%>
			</select>
			字第<input class="field_Q" type="text" name="q_permitNo" size="14" maxlength="14" value="<%=qBean.getQ_permitNo()%>">號
		</td>
		<td class="td_form">製造廠：</td>
		<td class="td_form_white">
			<input class="field_Q" type="text" name="q_manufactorName" size="50" maxlength="200" value="<%=qBean.getQ_manufactorName()%>">
		</td>
	</tr>
	<tr>
		<td class="td_form">國內許可證持有商：</td>
		<td class="td_form_white">
			<input class="field_Q" type="text" name="q_applicationName" size="50" maxlength="50" value="<%=qBean.getQ_applicationName()%>">
		</td>
		<td class="td_form">產品批號：</td>
		<td class="td_form_white">
			<input class="field_Q" type="text" name="q_lotNo" size="50" maxlength="50" value="<%=qBean.getQ_lotNo()%>">
		</td>
	</tr>
	<tr>
		<td class="td_form">品質異常型態：</td>
		<td class="td_form_white" colspan="3">
		    	<%=View.getCheckBoxOption("field_Q","q_qualitywarningtype","select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGWNTP' ", qBean.getQ_qualitywarningtype(),6)%>
		</td>
	</tr>
	<tr>
		<td class="td_form">產品是否進口：</td>
		<td class="td_form_white" colspan="3">
			<%=View.getRadioBoxTextOption("field_Q","q_isImport","Y;是;N;否",qBean.getQ_isImport())%>
		</td>
	</tr>
	<tr>
		<td class="td_form">回收型態：</td>
		<td class="td_form_white" >
			<%=View.getCheckBoxOption("field_Q","q_recycleType","select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGRECTP' order by codeId",qBean.getQ_recycleType())%>
		</td>
		<td class="td_form">廠商回覆狀況：</td>
		<td class="td_form_white">
			<%=View.getCheckBoxTextOption("field_Q","q_isReply","Y;已回覆;N;未回覆;",qBean.getQ_isReply())%>
		</td>
	</tr>
	<tr>
		<td class="td_form">燈號：</td>
		<td class="td_form_white">
			<%=View.getCheckBoxOption("field_Q","q_lamp","select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONLAMP' order by codeId",qBean.getQ_lamp())%>
		</td>
		<td class="td_form">後續處理：</td>
		<td class="td_form_white">
			<select class="field_Q" name="q_aftereffect">
			<%=View.getOption("select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRGEFFECT' order by codeId ", qBean.getQ_aftereffect())%>
			</select>
		</td>
	</tr>
	<tr>
		<td class="td_form">是否草擬紅綠燈初稿：</td>
		<td class="td_form_white">
			<%=View.getRadioBoxTextOption("field_Q","q_iswarning","Y;是;N;否",qBean.getQ_iswarning())%>
		</td>
		<td nowrap width="15%" nowrap class="td_form">是否為歷史移轉資料：</td>
		<td nowrap nowrap class="td_form_white">
		    <%=View.getRadioBoxTextOption("field_Q","q_isTrans","Y;是;N;否;",qBean.getQ_isTrans())%>		
		</td>
	</tr>
	</table>
<!--
程式目的：不良反應處理作業-處理頁籤
程式代號：
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN0601F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
String tabId = Common.get(request.getParameter("tabId"));
%>

<script type="text/javascript">
$(function(){
	$(":radio[name=dealWith14]").bind("click", function(){
		chDealWithOther14($(this).val());
	});
});

function chDealWithOther14(val){
	if(val == "99"){
		form1.dealWithOther14.disabled = false;
	}else{
		form1.dealWithOther14.value = "";
		form1.dealWithOther14.disabled = true;
	}
}
</script>

<table id="<%=tabId%>" border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td nowrap class="td_form">是否再次發函詢問廠商</td>
	<td nowrap class="td_form_white" colspan="3">
		<%=View.getYNRadioBoxTextOption("field", "isLetterYn14", obj.getIsLetterYn14()) %>
	</td>
</tr>
<tr>
	<td nowrap class="td_form">廠商回覆摘要</td>
	<td nowrap class="td_form_white" colspan="3">
		<input class="field" type="text" name="replyMemo14" size="80" maxlength="100" value="<%=obj.getReplyMemo14()%>">
	</td>
</tr>
<tr>
	<td nowrap class="td_form">後續處置</td>
	<td nowrap class="td_form_white" colspan="3">
		<%=View.getCommonRadioBoxOption("field", "dealWith14", "COSCS", obj.getDealWith14(), "codeId", 4)%>
		<input class="field" type="text" name="dealWithOther14" size="50" maxlength="100" value="<%=obj.getDealWithOther14()%>">
	</td>
</tr>
<tr>
	<td nowrap class="td_form">針對廠商回覆之評估</td>
	<td nowrap class="td_form_white" colspan="3">
		<input class="field" type="text" name="assess" size="80" maxlength="100" value="<%=obj.getAssess()%>">
	</td>
</tr>
<tr>
	<td nowrap class="td_form">調查摘要</td>
	<td nowrap class="td_form_white" colspan="3">
		<input class="field" type="text" name="summary" size="80" maxlength="100" value="<%=obj.getSummary()%>">
	</td>
</tr>
<tr>
	<td nowrap class="td_form">相關附件</td>
	<td nowrap class="td_form_white" colspan="3">
		<jsp:include page="../cosex/cosex0104f.jsp">
			<jsp:param value="COSMS" name="fileType"/>
		</jsp:include>
	</td>
</tr>
<tr>
	<td nowrap class="td_form_left" colspan="4">衛生管理諮議會資訊</td>
</tr>
<tr>
	<td nowrap class="td_form">會議決議簡述</td>
	<td nowrap class="td_form_white" colspan="3">
		<input class="field" type="text" name="resolution" size="80" maxlength="100" value="<%=obj.getResolution()%>">
	</td>
</tr>
<tr>
	<td nowrap class="td_form">相關附件</td>
	<td nowrap class="td_form_white" colspan="3">
		<jsp:include page="../cosex/cosex0104f.jsp">
			<jsp:param value="COSHA" name="fileType"/>
		</jsp:include>
	</td>
</tr>
<tr>
	<td nowrap class="td_form_left" colspan="4">專家書審資訊</td>
</tr>
<tr>
	<td nowrap class="td_form">審查結果簡述</td>
	<td nowrap class="td_form_white" colspan="3">
		<input class="field" type="text" name="result" size="80" maxlength="100" value="<%=obj.getResult()%>">
	</td>
</tr>
<tr>
	<td nowrap class="td_form">相關附件</td>
	<td nowrap class="td_form_white" colspan="3">
		<jsp:include page="../cosex/cosex0104f.jsp">
			<jsp:param value="COSEB" name="fileType"/>
		</jsp:include>
	</td>	
</tr>
<tr>
	<td nowrap class="td_form_left" colspan="4">廠商提交資料資訊</td>
</tr>
<tr>
	<td nowrap class="td_form">預防矯正措施</td>
	<td nowrap class="td_form_white" colspan="3">
		<input class="field" type="text" name="precaution14" size="80" maxlength="100" value="<%=obj.getPrecaution14()%>">
	</td>
</tr>
<tr>
	<td nowrap class="td_form">產品回收報告</td>
	<td nowrap class="td_form_white" colspan="3">
		<input class="field" type="text" name="recycling" size="80" maxlength="100" value="<%=obj.getRecycling()%>">
	</td>
</tr>
<tr>
	<td nowrap class="td_form">其他處置紀錄</td>
	<td nowrap class="td_form_white" colspan="3">
		<input class="field" type="text" name="otherDisposal" size="80" maxlength="100" value="<%=obj.getOtherDisposal()%>">
	</td>
</tr>
<tr>
	<td nowrap class="td_form">處理備註</td>
	<td nowrap class="td_form_white" colspan="3">
		<input class="field" type="text" name="remark14" size="80" maxlength="100" value="<%=obj.getRemark14()%>">
	</td>
</tr>
</table>

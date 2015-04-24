<!--
程式目的：不良品處理作業-處理頁籤
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
</script>

<table id="<%=tabId%>" border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td nowrap class="td_form">是否再次發函詢問廠商</td>
	<td nowrap class="td_form_white" colspan="3">
		<%=View.getYNRadioBoxTextOption("field", "isLetterYn11", obj.getIsLetterYn11()) %>
	</td>
</tr>
<tr>
	<td nowrap class="td_form">廠商回覆摘要</td>
	<td nowrap class="td_form_white" colspan="3">
		<input class="field" type="text" name="replyMemo11" size="80" maxlength="100" value="<%=obj.getReplyMemo11()%>">
	</td>
</tr>
<tr>
	<td nowrap class="td_form">處理備註</td>
	<td nowrap class="td_form_white" colspan="3">
		<input class="field" type="text" name="remark11" size="80" maxlength="100" value="<%=obj.getRemark11()%>">
	</td>
</tr>
<tr>
	<td nowrap class="td_form_left" colspan="4">衛生單位發文資訊</td>
</tr>
<tr>
	<td nowrap class="td_form_white" colspan="4">
		<jsp:include page="cosin0607f.jsp"/>
	</td>	
</tr>
<tr>
	<td nowrap class="td_form">相關附件</td>
	<td nowrap class="td_form_white" colspan="3">
		<jsp:include page="../cosex/cosex0104f.jsp">
			<jsp:param value="COSPT" name="fileType"/>
		</jsp:include>
	</td>
</tr>
<tr>
	<td nowrap class="td_form_left" colspan="4">衛生單位收文資訊</td>
</tr>
<tr>
	<td nowrap class="td_form_white" colspan="4">
		<jsp:include page="cosin0608f.jsp"/>
	</td>	
</tr>
<tr>
	<td nowrap class="td_form">相關附件</td>
	<td nowrap class="td_form_white" colspan="3">
		<jsp:include page="../cosex/cosex0104f.jsp">
			<jsp:param value="COSRT" name="fileType"/>
		</jsp:include>
	</td>
</tr>





</table>

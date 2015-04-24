<!--
程式目的：不良品結案頁籤
程式代號：
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.cosin.COSIN0901F">
	<jsp:setProperty name="obj" property="*"/>		
</jsp:useBean>
<%
String tabId = Common.get(request.getParameter("tabId"));
%>

<table id="<%=tabId%>" border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
	<td nowrap class="td_form">對通報者的回饋</td>
	<td nowrap class="td_form_white" colspan="3">
		<input class="field" type="text" name="feedBack" size="80" maxlength="100" value="<%=obj.getFeedBack()%>">
		<input type="hidden" name="cos0015DbId" value="<%=obj.getCos0015DbId()%>">
	</td>
</tr>
<tr>
	<td nowrap class="td_form">紀錄者意見</td>
	<td nowrap class="td_form_white" colspan="3">
		<input class="field" type="text" name="recordOpinion" size="80" maxlength="100" value="<%=obj.getRecordOpinion()%>">
	</td>
</tr>
</table>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>

<%
String is0501Type = Common.get(request.getParameter("is0501Type"));
%>

<!--Form區============================================================-->
	<table id="Tab5" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form" width="15%">摘譯日期</td>
			<td nowrap class="td_form_white" width="85%">
				<%=View.getPopCalendar("field","notifierRevDate","")%>
			</td>
		</tr>
		
		<%if(!"1".equals(is0501Type)) { %>
		<tr>
			<td nowrap class="td_form" >複審日期</td>
			<td nowrap class="td_form_white" >
				<%=View.getPopCalendar("field","notifierRevDate","")%>
			</td>
		</tr>
		<%} %>
		
		<tr>
			<td nowrap class="td_form" >摘譯內容</td>
			<td nowrap class="td_form_white">
			   <textarea class="field" rows="15" cols="100"></textarea>
			</td>
		</tr>
	</table>


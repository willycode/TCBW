<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.medin.MEDIN5101F qBean = (com.kangdainfo.tcbw.view.medin.MEDIN5101F) request.getAttribute("qBean");
%>
	<table class="queryTable" width="100%">
	<tr>
	    <td>
		    <input type="hidden" name="userID" value="<%=User.getUserId()%>">
			<input type="hidden" name="id" value="<%=qBean.getId()%>">
			<input type="hidden" name="doType" value="<%=qBean.getDoType()%>">
		</td>
	</tr>
	</table>
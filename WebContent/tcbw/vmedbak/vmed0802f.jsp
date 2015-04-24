<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>

<%
String is0801Type = Common.get(request.getParameter("is0801Type"));
%>

<!--Form區============================================================-->
	<table id="Tab8" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form" width="15%">改版日期</td>
			<td nowrap class="td_form_white" width="85%" colspan="3">
				<%=View.getPopCalendar("field","notifierRevDate","")%>
			</td>
		</tr>
		<%if(!"1".equals(is0801Type)) { %>
		<tr>
			<td nowrap class="td_form" >複審日期</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getPopCalendar("field","notifierRevDate","")%>
			</td>
		</tr>
		<%} %>
		<tr>
			<td nowrap class="td_form" >改版原因</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" rows="3" cols="100"></textarea>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >改版標題</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field_Q" type="text" name="applNo" size="100" value="">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >改版內容</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" rows="3" cols="100"></textarea>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >改版備註</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" rows="3" cols="100"></textarea>
			</td>
		</tr>
		
		
		
		<tr>
<td class="bgList" colspan="4">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',0,false);" href="#">NO</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">改版日期</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">改版原因</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">改版標題</a></th>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">改版內容</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<tr id="listContainerRow141" class='listTREven' onmouseover="this.className='listTRMouseover'" onmouseout="this.className='listTREven'" onClick="listContainerRowClick('141');queryOne('141');" >
 	<td  class='listTDEven' >1.</td>
 	<td  class='listTDEven' >1030402</td>
 	<td  class='listTDEven' >原因</td>
 	<td  class='listTDEven' >標題</td>
 	<td  class='listTDEven' >內容</td></tr>
	</tbody>
</table>
</div>
</td>
</tr>
</table>


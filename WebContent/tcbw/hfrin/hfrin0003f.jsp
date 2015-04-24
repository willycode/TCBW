<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="objHirstoryList" scope="page" class="java.util.ArrayList"/>

<%
String tabId = Common.get(request.getParameter("tabId"));
com.kangdainfo.tcbw.view.hfrin.HFRIN0501F qBean = (com.kangdainfo.tcbw.view.hfrin.HFRIN0501F) request.getAttribute("qBean");

objHirstoryList = (java.util.ArrayList)qBean.doQueryHistory();
%>

	<table id="<%=tabId %>" width="100%" align="center" class="table_form">
	<tr>
		<td nowrap class="td_form_left" colspan="2" style="text-align:center">篩選條件</td>
	</tr>
	<tr>
		<td nowrap class="td_form" >通報日期</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q", "q_notifierRepDateSHis", qBean.getQ_notifierRepDateSHis())%> ~ 
			<%=View.getPopCalendar("field_Q", "q_notifierRepDateEHis", qBean.getQ_notifierRepDateEHis())%>
		</td>
	</tr>	
	<tr>
		<td nowrap class="td_form" >非預期反應結果</td>
		<td nowrap class="td_form_white">
			<%=qBean.getFUROption("field_Q", "q_unReactionResultsHis", qBean.getQ_unReactionResultsHis()) %>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" >食品品名</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_productHis" size="50" maxlength="50" value="<%=qBean.getQ_productHis()%>">
		</td>
	</tr>		
	<tr><td nowrap class="td_form" ></td><td nowrap class="td_form_white"></td></tr>
						
	<!--Toolbar區============================================================-->
	<tr><td nowrap class="bgToolbar" style="text-align:center" colspan="2">
		<span id="spanDoQHistory">
			<input class="toolbar_default" type="button" followPK="false" name="doQHistory" value="篩    選" onClick="whatButtonFireEvent(this.name)">&nbsp;
		</span>
		<% 
		if(objHirstoryList!=null && objHirstoryList.size()>0){
		%>
		<span id="spanDoReport">
			<input class="toolbar_default" type="button" followPK="false" name="doReport" value="匯出EXCEL" onClick="whatButtonFireEvent(this.name)">
		</span>
		<%
		}
		%>
	</td></tr>
	
	<%--	
	<tr><td nowrap class="bgPagging" colspan="4">		
		<% request.setAttribute("QueryBean",obj);%>
		<jsp:include page="../../home/page.jsp" />
	</td></tr>	
	 --%>
	 	
	<tr><td nowrap class="bgList" colspan="2">
		<div id="listContainer" style="height:auto">
			<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
			<thead id="listTHEAD">
				<tr>
					<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">NO</a></th>
					<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">通報日期</a></th>
					<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">案件編號</a></th>
					<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">食品品名</a></th>
					<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',5,false);" href="#">健康食品未達宣稱之保健功效</a></th>
					<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',6,false);" href="#">非預期反應結果</a></th>
					<th class="listTH"><a class="text_link_w" href="#">附件</a></th>
				</tr>
			</thead>
			<tbody id="listTBODY">
			<%
				boolean primaryArray[] = {true, false, false, false, false, false, false};
				boolean displayArray[] = {false, true, true, true, true, true, true};
				boolean linkArray[] = {false, false, true, false, false, false, true};
				String[] alignArray = {"center","center","center","center","center","center","center"};
				out.write(View.getQuerylist(primaryArray, displayArray, alignArray, objHirstoryList, "true", true, linkArray, null, "", false, false));
			%>
			</tbody>
			</table>
		</div>
	</td></tr>
	</table>
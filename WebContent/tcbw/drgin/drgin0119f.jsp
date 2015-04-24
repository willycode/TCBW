<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj19" scope="request" class="com.kangdainfo.tcbw.view.drgin.DRGIN0119F">
	<jsp:setProperty name="obj19" property="*"/>
</jsp:useBean>
<jsp:useBean id="obj19List" scope="page" class="java.util.ArrayList"/>
<jsp:useBean id="obj19List1" scope="page" class="java.util.ArrayList"/>
<%
obj19List = (java.util.ArrayList) obj19.doQueryAllDrg0119();
obj19List1 = (java.util.ArrayList) obj19.doQueryAllDrg0119Capa();
%>
<table id="Tab11" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form_left" colspan="2">篩選條件
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >通報日期</td>
			<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q", "q_notifierSdateHis", obj19.getQ_notifierSdateHis())%>
			~ <%=View.getPopCalendar("field_Q", "q_notifierEdateHis", obj19.getQ_notifierEdateHis())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >不良品缺陷</td>
			<td nowrap class="td_form_white">
				<%=obj19.getCheckBoxOption("field_Q", "q_mainCodeHis", "q_subCodeHis", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='DRG0105' order by codeId", obj19.getQ_mainCodeHis(),obj19.getQ_subCodeHis())%>		
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >製造批號</td>
			<td nowrap class="td_form_white">
				<input class="field_Q" type="text" name="q_manufactorNoHis" size="15" maxlength="11" value="<%=obj19.getQ_manufactorNoHis()%>"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >通報單位</td>
			<td nowrap nowrap class="td_form_white">
				<%=View.getRadioBoxOption("field_Q", "q_notifierSourceHis", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONNFT1' order by codeId", obj19.getQ_notifierSourceHis(), "changeForm(this);")%>
	  	    	<br><input class="field_Q" type="text" id="s1" name="q_notifierDeptHis" size="30" maxlength="30" value="<%=obj19.getQ_notifierDeptHis() %>" style="display:none"/>
	  	   		<input type="button" id="s2" name="btnQryUserJob" onClick="popUserJobList();" value="查詢" width="120px" class="field_Q" style="display:none"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" ></td><td nowrap class="td_form_white"></td>
		</tr>
		
<!--Toolbar區============================================================-->
<tr><td nowrap class="bgToolbar" style="text-align:center" colspan="2">
	<input class="toolbar_default" type="button" followPK="false" name="doQHistory" value="篩    選" onClick="whatButtonFireEvent(this.name)">&nbsp;
	<% if(obj19List!=null && obj19List.size()>0){%>
	<input class="toolbar_default" type="button" followPK="false" name="doReport" value="匯出EXCEL" onClick="whatButtonFireEvent(this.name)">
	<% }%>
</td></tr>

<!-- List 區   -->	
<tr><td nowrap class="bgPagging" colspan="4">		
<% request.setAttribute("QueryBean",obj19);%>
<jsp:include page="../../home/page.jsp" />
</td>
</tr>

<tr>
<td nowrap class="bgList" colspan="4">

<table class="table_form" width="99%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" >NO</a></th>
		<th class="listTH"><a class="text_link_w" >通報日期</a></th>
		<th class="listTH"><a class="text_link_w" >案件編號</a></th>
		<th class="listTH"><a class="text_link_w" >英文品名</a></th>
		<th class="listTH"><a class="text_link_w" >不良品缺陷</a></th>
		<th class="listTH"><a class="text_link_w" >製造批號</a></th>
		<th class="listTH"><a class="text_link_w" >製造商</a></th>
		<th class="listTH"><a class="text_link_w" >風險等級</a></th>
		<th class="listTH"><a class="text_link_w" >通報單位</a></th>
		<th class="listTH"><a class="text_link_w" >評估表</a></th>
		<th class="listTH"><a class="text_link_w" >附件</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {true,false,false,false,false,false,false,false,false,false,false};
	    boolean displayArray[] = {false,true,true,true,true,true,true,true,true,true,true};
	    boolean linkArray[] = {false,false,true,false,false,false,false,false,false,true,true};
	    String[] alignArray = {"center","center","center","left","left","left","left","center","center","center","center"};
	    out.write(View.getQuerylist(primaryArray,displayArray,alignArray,obj19List,obj19.getQueryAllFlag(),true,linkArray,null,"",false,false,false,"",false));
	%>
	</tbody>
</table>
</td></tr>

</table>

<table id="Tab12" width="100%" align="center" class="table_form">
<tr>
<td nowrap class="bgList" colspan="4">
<div id="listContainer" style="height:auto">
<table class="table_form" width="99%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" >NO</a></th>
		<th class="listTH"><a class="text_link_w" >FDA發文日期</a></th>
		<th class="listTH"><a class="text_link_w" >FDA發文字號</a></th>
		<th class="listTH"><a class="text_link_w" >通報日期</a></th>
		<th class="listTH"><a class="text_link_w" >案件編號</a></th>
		<th class="listTH"><a class="text_link_w" >不良品缺陷</a></th>
		<th class="listTH"><a class="text_link_w" >製造批號</a></th>
		<th class="listTH"><a class="text_link_w" >風險等級</a></th>
		<th class="listTH"><a class="text_link_w" >評估日期</a></th>
		<th class="listTH"><a class="text_link_w" >廠商回覆</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray1[] = {true,false,false,false,false,false,false,false,false,false};
	    boolean displayArray1[] = {false,true,true,true,true,true,true,true,true,true};
	    boolean linkArray1[] = {false,false,false,false,false,false,false,false,false,true};
	    String[] alignArray1 = {"center","center","center","left","left","left","left","center","center","center"};
	    out.write(View.getQuerylist(primaryArray1,displayArray1,alignArray1,obj19List1,obj19.getQueryAllFlag(),true,linkArray1,null,"",false,false,false,"",false));
	%>
	</tbody>
</table>
</div>
</td></tr>
</table>
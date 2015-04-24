<!--
程式目的：
程式代號：
程式日期：
程式作者：
--------------------------------------------------------
修改作者　　修改日期　　　修改目的
--------------------------------------------------------
-->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj0303" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN0302F">
	<jsp:setProperty name="obj0303" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList0303" scope="page" class="java.util.ArrayList"/>
<%
     
     objList0303 = (java.util.ArrayList) obj0303.doQueryAll();
     obj0303 = (com.kangdainfo.tcbw.view.medin.MEDIN0302F)obj0303.queryOne();
%>
	<table id="Tab5" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form_left" colspan="4">轉送評估</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="15%">通報方式</td>
			<td nowrap class="td_form_white" colspan="3">
               <%=View.getCommonRadioBoxOption("field","bulletinKind","MEDNTS",obj0303.getBulletinKind(),"")%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="15%">轉送備註</td>
			<td nowrap class="td_form_white"  colspan="3">
             <textarea name="remark" rows="3" cols="50"><%=obj0303.getRemark()%></textarea>   
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="15%">分派方式</td>
			<td nowrap class="td_form_white"  colspan="3">
               <%=View.getRadioBoxTextOption("field","assignmentKind","1;自動;2;手動",obj0303.getAssignmentKind())%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="15%">分派人員</td>
			<td nowrap class="td_form_white"  colspan="3">
			   <input class="field_RO" type="text" size="30" name="workerName" value="<%=obj0303.getWorkerName()%>">
			   <input type="hidden" name="worker" value="<%=obj0303.getWorker()%>">
			   <input type="button" name="btn_autoWorker" onClick="getReassignment('Y')" value="選擇分派人員"> 
			   <input type="hidden" name="autoWorker" value="<%=obj0303.getAutoWorker()%>"> 
			</td>
		</tr>
		
<!-- List 區   -->	
<tr><td nowrap class="bgPagging" colspan="4">		
<% request.setAttribute("QueryBean",obj0303);%>
<jsp:include page="../../home/page.jsp" />
</td>
</tr>


<tr>
<td nowrap class="bgList" colspan="4">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">NO</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',2,false);" href="#">通報方式</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',3,false);" href="#">轉送備註</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY',4,false);" href="#">轉送日期</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<%
	    boolean primaryArray[] = {true,false,false,false};
	    boolean displayArray[] = {false,true,true,true};
	    String[] alignArray  =  {"center","center","center","center"};
	    out.write(View.getQuerylist(primaryArray,displayArray,objList0303,obj0303.getQueryAllFlag()));
	%>
	</tbody>
</table>
</div>
</td>
</tr>
</table>
	

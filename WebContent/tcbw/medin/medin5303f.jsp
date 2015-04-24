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
<jsp:useBean id="obj5303" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN5303F">
	<jsp:setProperty name="obj5303" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList5303" scope="page" class="java.util.ArrayList"/>
<%
    objList5303 = (java.util.ArrayList) obj5303.doQueryAll();
    obj5303 = (com.kangdainfo.tcbw.view.medin.MEDIN5303F)obj5303.queryOne();
%>
	<table id="Tab4" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form_left" colspan="4"></td>
		</tr>
		<tr>
		    <td nowrap width="15%" nowrap class="td_form">通報日期：</td>
		    <td nowrap class="td_form_white" colspan="3">
			<%=View.getPopCalendar("field_Q","q_notifierRepDateS",obj5303.getQ_notifierRepDateS())%>~
			<%=View.getPopCalendar("field_Q","q_notifierRepDateE",obj5303.getQ_notifierRepDateE())%>				
		    </td>
	    </tr>
		
		<tr>
		    <td nowrap width="15%" nowrap class="td_form">不良反應結果：</td>
		    <td nowrap class="td_form_white" colspan="3">
		    	<%--
		       <%=View.getCheckBoxOption("field_Q","q_badReactionResults","select codeId,codeName from CommonCode where commonCodeKind.codeKindId = 'MEDAD1'",obj5303.getQ_badReactionResults(),4)  %>
		        --%>
		       <select class="field_Q" name="q_badReactionResults">
				<%=View.getOptionCodeKind("MEDAD1", obj5303.getQ_badReactionResults()) %>
				</select>
		    </td>
	    </tr>
	    
	    <tr>
		    <td nowrap width="15%" nowrap class="td_form">通報單位：</td>
		    <td nowrap class="td_form_white" colspan="3">
		      
		    </td>
	    </tr>
		
        <tr>
		   <td nowrap align="center" colspan="3">
			 <input class="toolbar_default" type="button" followPK="false" name="doQuery" value="查     詢" onClick="whatButtonFireEvent(this.name)">&nbsp;	
		   </td>
	    </tr>
		
<!-- List 區   -->	
<tr><td nowrap class="bgPagging" colspan="4">		
<% request.setAttribute("QueryBean",obj5303);%>
<jsp:include page="../../home/page.jsp" />
</td>
</tr>


<tr>
<td nowrap class="bgList" colspan="4">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY_medin5303f',1,false);" href="#">NO</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY_medin5303f',2,false);" href="#">通報日期</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY_medin5303f',3,false);" href="#">案件編號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY_medin5303f',4,false);" href="#">列管單位</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY_medin5303f',5,false);" href="#">醫材品名</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY_medin5303f',6,false);" href="#">製造廠</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY_medin5303f',7,false);" href="#">醫材主類別</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY_medin5303f',8,false);" href="#">不良反應結果</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY_medin5303f',9,false);" href="#">通報單位</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY_medin5303f',10,false);" href="#">附件</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY_medin5303f">
	<%
		boolean primaryArray[] = {true,false,false,false,false,false,false,false,false,false};
	    boolean displayArray[] = {false,true,true,true,true,true,true,true,true,true};
	    String[] alignArray  =  {"center","center","center","center","center","center","center","center","center","center"};
	    boolean linkArray[] = {false,false,false,false,false,false,false,false,false,true};
		out.write(View.getQuerylist(primaryArray,displayArray,alignArray,objList5303,obj5303.getQueryAllFlag(),true,linkArray,null));
	%>
	</tbody>
</table>
</div>
</td>
</tr>
</table>
	

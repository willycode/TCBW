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
<jsp:useBean id="obj2003" scope="request" class="com.kangdainfo.tcbw.view.medin.MEDIN2003F">
	<jsp:setProperty name="obj2003" property="*"/>
</jsp:useBean>
<jsp:useBean id="objList2003" scope="page" class="java.util.ArrayList"/>
<%
    objList2003 = (java.util.ArrayList) obj2003.doQueryAll();
    obj2003 = (com.kangdainfo.tcbw.view.medin.MEDIN2003F)obj2003.queryOne();
%>
	<table id="Tab8" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form_left" colspan="4">轉送評估</td>
		</tr>
		<tr>
		    <td nowrap width="15%" nowrap class="td_form">通報日期：</td>
		    <td nowrap nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_notifierRevDateS",obj2003.getQ_notifierRevDateS())%>~
			<%=View.getPopCalendar("field_Q","q_notifierRevDateE",obj2003.getQ_notifierRevDateE())%>				
		    </td>
	    </tr>
		<tr>
		    <td nowrap width="15%" nowrap class="td_form">不良事件類別：</td>
		    <td nowrap nowrap class="td_form_white">	
			<%=View.getCheckBoxTextOption("field_Q","q_eventKind","1;不良反應;2;不良品",obj2003.getQ_eventKind())%>
		    </td>
	    </tr>
		<tr>
		    <td nowrap width="15%" nowrap class="td_form">不良反應結果：</td>
		    <td nowrap class="td_form_white" colspan="3">
		    <%=View.getCheckBoxOption("field_Q","q_badReactionResults","select codeId,codeName from CommonCode where commonCodeKind.codeKindId = 'MEDAD1'",obj2003.getQ_badReactionResults(),4)  %>
		    </td>
	    </tr>
		<tr>
		   <td nowrap width="15%" nowrap class="td_form">不良品事件等級：</td>
		   <td nowrap class="td_form_white" colspan="3">
				<%=View.getCommonCheckBoxOption("field_Q","q_eventClass","MEDEVC",obj2003.getQ_eventClass())%>		
		   </td>
	    </tr>
	    <tr>
		   <td nowrap width="15%" nowrap class="td_form">通報單位：</td>
		   <td nowrap class="td_form_white" colspan="3">
				<input class="field_Q" type="text" name="q_notifyDept" size="50" maxlength="100" value="<%=obj2003.getQ_notifyDept()%>">		
		   </td>
	    </tr>
        <tr>
		   <td nowrap align="center" colspan="3">
		   		<input class="toolbar_default" type="button" followPK="false" name="doQuery" value="查     詢" onClick="whatButtonFireEvent(this.name)">&nbsp;
				<% if(objList2003!=null && objList2003.size()>0){%>
				<input class="toolbar_default" type="button" followPK="false" name="doReport" value="匯出EXCEL" onClick="whatButtonFireEvent(this.name)">
				<% }%>	
		   </td>
	    </tr>
		
<!-- List 區   -->	
<tr><td nowrap class="bgPagging" colspan="4">		
<% request.setAttribute("QueryBean",obj2003);%>
<jsp:include page="../../home/page.jsp" />
</td>
</tr>


<tr>
<td nowrap class="bgList" colspan="4">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
		<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY_medin2003f',0,false);" href="#">NO</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY_medin2003f',1,false);" href="#">通報日期</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY_medin2003f',2,false);" href="#">案件編號</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY_medin2003f',3,false);" href="#">不良事件類別</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY_medin2003f',4,false);" href="#">醫材品名</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY_medin2003f',5,false);" href="#">製造廠</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY_medin2003f',6,false);" href="#">醫材主類別</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY_medin2003f',7,false);" href="#">不良反應結果</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY_medin2003f',8,false);" href="#">NCAR通報狀況</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY_medin2003f',9,false);" href="#">不良品事件等級</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY_medin2003f',10,false);" href="#">通報單位</a></th>
		<th class="listTH"><a class="text_link_w" onClick="return sortTable('listTBODY_medin2003f',11,false);" href="#">附件</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY_medin2003f">
	<%
	    boolean primaryArray[] = {true,false,false,false,false,false,false,false,false,false,false,false};
	    boolean displayArray[] = {false,true,true,true,true,true,true,true,true,true,true,true};
	    String[] alignArray  =  {"center","center","center","center","center","center","center","center","center","center","center","center"};
	    boolean linkArray[] = {false,false,false,false,false,false,false,false,false,false,false,true};
	    out.write(obj2003.getQuerylist(primaryArray,displayArray,alignArray,objList2003,obj2003.getQueryAllFlag(),true,linkArray,null,null,false,false,false,null));

	
	%>
	</tbody>
</table>
</div>
</td>
</tr>
</table>
	

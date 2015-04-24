<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.cosin.COSIN0102Q qBean = (com.kangdainfo.tcbw.view.cosin.COSIN0102Q) request.getAttribute("qBean");
%>
	<table class="table_form" width="100%">
	<tr>
		<td nowrap width="15%" class="td_form">案件編號：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_applNoS" size="12" maxlength="12" value="<%=qBean.getQ_applNoS()%>">
			~		
			<input class="field_Q" type="text" name="q_applNoE" size="12" maxlength="12" value="<%=qBean.getQ_applNoE()%>">
		</td>
		<td nowrap width="15%" class="td_form">發生日期：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q", "q_occurDateS", qBean.getQ_occurDateS())%>
			~
			<%=View.getPopCalendar("field_Q", "q_occurDateE", qBean.getQ_occurDateE())%>				
		</td>
	</tr>  
	<tr>
		<td nowrap class="td_form">通報日期：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q", "q_notifierRevDateS", qBean.getQ_notifierRevDateS())%>
			~
			<%=View.getPopCalendar("field_Q", "q_notifierRevDateE", qBean.getQ_notifierRevDateE())%>				
		</td>
		<td nowrap class="td_form">收案日期：</td>
		<td nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q", "q_notifierRepDateS", qBean.getQ_notifierRepDateS())%>
			~
			<%=View.getPopCalendar("field_Q", "q_notifierRepDateE", qBean.getQ_notifierRepDateE())%>			
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">通報來源：</td>
		<td nowrap class="td_form_white">
			<%=View.getCommonRadioBoxOption("field_Q", "q_notifierSource", "CIS", qBean.getQ_notifierSource(), "codeId", 4)%>
		</td>
		<td nowrap class="td_form">通報單位：</td>
		<td nowrap class="td_form_white">
			<%=View.getRadioBoxOption("field_Q", "q_notifierType", "select codeId, codeName from CommonCode where commonCodeKind.codeKindId='CONNFT1' order by codeId", "")%>
			<br>
			<input class="field_Q" type="text" id="s1" name="q_notifierDept" size="30" maxlength="30" value=""/>
	  	    <input type="button" id="s2" name="btnQryExpense" onClick="popUserJobList();" value="查詢" width="120px" class="field"/>						
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">許可證字：</td>
		<td nowrap class="td_form_white">
			<select class="field_Q" name="q_permitKey" type="select">
				<%=View.getOptionCodeKind("CPT", qBean.getQ_permitKey()) %>
			</select>
			 字第
			<input class="field_Q" type="text" name="q_permitNo" size="15" maxlength="15" value="<%=qBean.getQ_permitNo()%>">				
		</td>
		<td nowrap class="td_form">不良事件類別：</td>
		<td nowrap class="td_form_white">
			<%=View.getCommonCodeKindBoxOption("field_Q", "q_cosType", "CCT", qBean.getQ_cosType()) %>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">化粧品品名：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_chProduct" size="50" maxlength="100" value="<%=qBean.getQ_chProduct()%>">		
		</td>
		<td nowrap class="td_form">製造廠/進口代理商：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_manufactorName" size="50" maxlength="100" value="<%=qBean.getQ_manufactorName()%>">		
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">案件狀態：</td>
		<td nowrap class="td_form_white">
			<select class="field_Q" name="q_status" type="select">
				<%=View.getOptionCodeKind("CCS", qBean.getQ_status()) %>
			</select>	
		</td>
		<td nowrap class="td_form">留案備查：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="checkbox" name="q_preResult" value="Y" <%=qBean.getQ_preResult().equals("Y")?"checked":"" %>>
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">是否為歷史移轉資料：</td>
		<td nowrap nowrap class="td_form_white" colspan="3">
		    <%=View.getRadioBoxTextOption("field_Q","q_isTrans","Y;是;N;否;",qBean.getQ_isTrans())%>		
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">不良品缺陷：</td>
		<td nowrap class="td_form_white" colspan="3">
			<%=qBean.getCOS0003DbCheckBoxOption("field_Q", "q_mainCode", qBean.getQ_mainCode()) %>
		</td>
	</tr>
	</table>
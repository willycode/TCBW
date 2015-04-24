<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.sdrg.SDRG0201Q qBean = (com.kangdainfo.tcbw.view.sdrg.SDRG0201Q) request.getAttribute("qBean");
%>
<table class="queryTable" width="100%">	
	<tr>
		<td nowrap class="td_form" width="20%">案件編號：</td>
		<td nowrap class="td_form_white" width="30%" >
			<input class="field_Q" type="text"  name="q_applNoS" size="15" maxlength="15" value="<%=qBean.getQ_applNoS()%>"/>~
			<input class="field_Q" type="text"  name="q_applNoE" size="15" maxlength="15" value="<%=qBean.getQ_applNoE()%>"/>
		</td>
		<td nowrap class="td_form" width="20%">藥品品名：</td>
		<td nowrap class="td_form_white" width="30%">
			<input class="field_Q" type="text"  name="q_chProduct" size="50" maxlength="100" value="<%=qBean.getQ_chProduct()%>"/>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">許可證字號：</td>
		<td nowrap class="td_form_white" colspan="3">
		<select name="q_permitKey" class="field_Q" >
		    <%=View.getOptionCodeKind("DRGPKID", qBean.getQ_permitKey())%>
        </select>
   			<input class="field_Q" type="text" name="q_permitNo" size="15" maxlength="15" value="<%=qBean.getQ_permitNo()%>" />
		</td>		
		</tr>			
	<tr>
		<td nowrap class="td_form">許可證持有商：</td>
		<td nowrap class="td_form_white" >
			<input class="field_Q" type="text"  name="q_appName" size="30" maxlength="50" value="<%=qBean.getQ_appName()%>"/>
		</td>
		<td nowrap class="td_form">製造廠：</td>
		<td nowrap class="td_form_white" >
			<input class="field_Q" type="text"  name="q_manufactorName" size="30" maxlength="50" value="<%=qBean.getQ_manufactorName()%>"/>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" >發文日期：</td>
		<td nowrap class="td_form_white" colspan="3">
		   <%=View.getPopCalendar("field_Q","q_postDateS",qBean.getQ_postDateS())%> ~  
		   <%=View.getPopCalendar("field_Q","q_postDateE",qBean.getQ_postDateS())%>            
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" >回收文號：</td>
		<td nowrap class="td_form_white" colspan="3">
		   <input class="field_Q" type="text" name="q_postNo" size="15" maxlength="15" value="<%=qBean.getQ_postNo()%>"/>                
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">回收分級：</td>
		<td nowrap class="td_form_white" colspan="3">
				<%=View.getCommonCheckBoxOption("field_Q","q_recycleclass","DRGRECCLAS", qBean.getQ_recycleclass())%>
	    </td>
	</tr>
	<tr>
		<td nowrap class="td_form" >訊息來源：</td>
		<td nowrap class="td_form_white" colspan="3">
		   <%=View.getCommonCheckBoxOption("field_Q", "q_msgsource", "DRGRECSR", qBean.getQ_msgsource()) %>           
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form">通報編號：</td>
		<td nowrap class="td_form_white" >
			<input class="field_Q" type="text"  name="q_cureapplno" size="15" maxlength="15" value="<%=qBean.getQ_cureapplno()%>"/>
		</td>
		<td nowrap class="td_form">警訊編號：</td>
		<td nowrap class="td_form_white" >
			<input class="field_Q" type="text"  name="q_quaapplno" size="15" maxlength="15" value="<%=qBean.getQ_quaapplno()%>"/>
		</td>
	</tr>
	<tr>
		<td nowrap class="td_form" >回收期限：</td>
		<td nowrap class="td_form_white" >
		   <%=View.getPopCalendar("field_Q","q_recycledeadlineS",qBean.getQ_recycledeadlineS())%>   ~  
		   <%=View.getPopCalendar("field_Q","q_recycledeadlineE",qBean.getQ_recycledeadlineE())%>           
		</td>
		<td nowrap class="td_form" >主管衛生局：</td>
		<td nowrap class="td_form_white" >
		   <input class="field" type="hidden" name="q_healthbureau" size="10" value="<%=qBean.getQ_healthbureau()%>" />
	  	   <input class="field_RO" type="text" name="q_healthbureauName" size="30" maxlength="50"  value="<%=qBean.getQ_healthbureauName()%>" />
		   <input class="field_Q" type="button" name="btnQryheal" onClick="popHealthData(1);" value="查詢" width="120px" >
		   <input class="field_Q" type="button" name="btnClearheal" onClick="popHealthData(2);" value="清除" width="120px" >	   	
		</td>
	</tr>
</table>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<%
com.kangdainfo.tcbw.view.hfrin.HFRIN0101Q qBean = (com.kangdainfo.tcbw.view.hfrin.HFRIN0101Q) request.getAttribute("qBean");
%>
	<table class="table_form" width="100%">
	<tr>
		<td nowrap width="15%" nowrap class="td_form">案件編號：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_applNoS" size="10" maxlength="12" value="<%=qBean.getQ_applNoS()%>">		
			~<input class="field_Q" type="text" name="q_applNoE" size="10" maxlength="12" value="<%=qBean.getQ_applNoE()%>">		
		</td>
		<td nowrap width="15%" nowrap class="td_form">發生日期：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_occurDateS",qBean.getQ_occurDateS())%>~<%=View.getPopCalendar("field_Q","q_occurDateE",qBean.getQ_occurDateE())%>				
		</td>
	</tr>  
	<tr>
		<td nowrap width="15%" nowrap class="td_form">通報日期：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_notifierRepDateS",qBean.getQ_notifierRepDateS())%>~<%=View.getPopCalendar("field_Q","q_notifierRepDateE",qBean.getQ_notifierRepDateE())%>		
		</td>
		<td nowrap width="15%" nowrap class="td_form">收案日期：</td>
		<td nowrap nowrap class="td_form_white">
			<%=View.getPopCalendar("field_Q","q_notifierRevDateS",qBean.getQ_notifierRevDateS())%>~<%=View.getPopCalendar("field_Q","q_notifierRevDateE",qBean.getQ_notifierRevDateE())%>							
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">通報來源：</td>
		<td nowrap nowrap class="td_form_white" colspan="3">
			<select class="field_Q" name="q_informedSources" type="select">
				<%=View.getOptionCodeKind("HFRNFS", qBean.getQ_informedSources()) %>
			</select>
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">食品字號：</td>
		<td nowrap nowrap class="td_form_white">
			<select class="field_Q" name="q_permitKey" type="select">
				<%=View.getOptionCodeKind("HFRPKID", qBean.getQ_permitKey()) %>
			</select>
			<input class="field_Q" type="text" name="q_permitNo" size="10" maxlength="10" value="<%=qBean.getQ_permitNo()%>">		
		</td>
		<td nowrap width="15%" nowrap class="td_form">食品品名：</td>
		<td nowrap nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_productName" size="50" maxlength="100" value="<%=qBean.getQ_productName()%>">		
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">食品成分：</td>
		<td nowrap class="td_form_white">
			<input class="field_Q" type="text" name="q_ingredient" size="50" maxlength="50" value="<%=qBean.getQ_ingredient()%>">		
		</td>
		<td nowrap width="15%" nowrap class="td_form">購買來源：</td>
		<td nowrap nowrap class="td_form_white">
			<select class="field_Q" name="q_buySource" type="select">
				<%=View.getOptionCodeKind("HFRBYS", qBean.getQ_buySource()) %>
			</select>					
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">非預期反應分類：</td>
		<td nowrap nowrap class="td_form_white">
			<select class="field_Q" name="q_unExpectedClassify" type="select">
				<%=View.getOptionCodeKind("HFRFUC", qBean.getQ_unExpectedClassify()) %>
			</select>	
		</td>
		<td nowrap width="15%" nowrap class="td_form">非預期反應原因：</td>
		<td nowrap class="td_form_white">
			<select class="field_Q" name="q_unExpectedReason" type="select">
				<%=View.getOptionCodeKind("HFRNRS", qBean.getQ_unExpectedReason()) %>
			</select>	
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">證據性：</td>
		<td nowrap nowrap class="td_form_white">
			<select class="field_Q" name="q_evidentiary" type="select">
				<%=View.getOptionCodeKind("HFRFEV", qBean.getQ_evidentiary()) %>
			</select>			
		</td>
		<td nowrap width="15%" nowrap class="td_form">嚴重程度：</td>
		<td nowrap nowrap class="td_form_white">
			<select class="field_Q" name="q_thiSeverity" type="select">
				<%=View.getOptionCodeKind("HFRSVR", qBean.getQ_thiSeverity()) %>
			</select>		
		</td>
	</tr>
	<tr>
	    <td nowrap width="15%" nowrap class="td_form">行政處置層級：</td>
		<td nowrap nowrap class="td_form_white">
			<select class="field_Q" name="q_administrativeLevel" type="select">
				<%=View.getOptionCodeKind("HFRDRSP", qBean.getQ_administrativeLevel()) %>
			</select>		
		</td>
		<td nowrap width="15%" nowrap class="td_form">案件狀態：</td>
		<td nowrap nowrap class="td_form_white">
			<select class="field_Q" name="q_status" type="select">
				<%=View.getOptionCodeKind("FCS", qBean.getQ_status()) %>
			</select>		
		</td>
	</tr>
	<tr>
		<td nowrap width="15%" nowrap class="td_form">是否為歷史移轉資料：</td>
		<td nowrap nowrap class="td_form_white" colspan="3">
		    <%=View.getRadioBoxTextOption("field_Q","q_isTrans","Y;是;N;否;",qBean.getQ_isTrans())%>		
		</td>
	</tr>
	</table>
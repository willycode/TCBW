<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.sdrg.SDRG0501F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>

<% 
obj = (com.kangdainfo.tcbw.view.sdrg.SDRG0501F) obj.doQueryOne0501();
%> 
   <table id="Tab5" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form" width="20%">調查報告展延<br>日期</td>
			<td nowrap class="td_form_white" colspan="3">
			   <%=View.getPopCalendar("field_RO","q_assessextendate",obj.getQ_assessextendate())%>               
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >評估日期</td>
			<td nowrap class="td_form_white" colspan="3"> 
			   <%=View.getPopCalendar("field","assessdate",obj.getAssessdate())%>              
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >本案原由</td>
			<td nowrap class="td_form_white" colspan="3"> 
			   <textarea class="field" name="assessrecyclereason" cols="65" rows="5"><%=obj.getAssessrecyclereason()%></textarea>              
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form_left" style="text-align:center;" colspan="2">廠商輸入內容</td>
			<td nowrap class="td_form_left" style="text-align:center;" colspan="2">TFDA輸入內容</td>
		</tr>
		<tr>
		    <td nowrap class="td_form" width="10%">清查結果</td>
			<td nowrap class="td_form_white">
				<textarea class="field_RO" name="appsurveyresult" cols="65" rows="5"><%=obj.getAppsurveyresult()%></textarea>
			</td>
			<td nowrap class="td_form" width="10%">清查結果</td>
			<td nowrap class="td_form_white"> 
			   <textarea class="field" name="assesssurveyresult" cols="65" rows="5"><%=obj.getAssesssurveyresult()%></textarea>             
			</td>
		</tr>
		<tr>
		    <td nowrap class="td_form" width="10%">調查報告</td>
			<td nowrap class="td_form_white">
			   <textarea class="field_RO" name="appsurveyreport" cols="65" rows="5"><%=obj.getAppsurveyreport()%></textarea>               
			</td>
			<td nowrap class="td_form" width="10%">調查報告</td>
			<td nowrap class="td_form_white">
			   <textarea class="field" name="assesssurveyreport" cols="65" rows="5"><%=obj.getAssesssurveyreport()%></textarea>               
			</td>
		</tr>
		<tr>
		    <td nowrap class="td_form" width="10%">預防矯正措施<br>及改善時程</td>
			<td nowrap class="td_form_white">  
			   <textarea class="field_RO" name="appprecautiontime" cols="65" rows="5"><%=obj.getAppprecautiontime()%></textarea>             
			</td>
			<td nowrap class="td_form" width="10%">預防矯正措施<br>及改善時程</td>
			<td nowrap class="td_form_white">  
			   <textarea class="field" name="assessprecaution" cols="65" rows="5"><%=obj.getAssessprecaution()%></textarea>             
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >擬辦事項</td>
			<td nowrap class="td_form_white" colspan="3">
			   <%=View.getCommonCodeKindBoxOption("field", "assessdealWith", "DRG0111", obj.getAssessdealWith()) %> 
		   </td>
		</tr>
		<tr>
			<td nowrap class="td_form" >評估結果</td>
			<td nowrap class="td_form_white" colspan="3">  
			   <textarea class="field" name="assessresult" cols="65" rows="5"><%=obj.getAssessresult()%></textarea>             
			</td>
		</tr>	
	</table>
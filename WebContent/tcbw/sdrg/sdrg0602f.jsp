<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.sdrg.SDRG0601F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>

<% 
obj = (com.kangdainfo.tcbw.view.sdrg.SDRG0601F) obj.doQueryOne0601();
%> 
   <table id="Tab6" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form" width="20%">分析日期</td>
			<td nowrap class="td_form_white" colspan="3">
			   <%=View.getPopCalendar("field","anadate",obj.getAnadate())%>             
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >學名藥/原廠藥</td>
			<td nowrap class="td_form_white" colspan="3">
			   <%=View.getCommonCodeRadioBoxOption("field", "anamedicineType", "DRG0101", obj.getAnamedicineType(), null, "", "") %>             
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >國產/輸入</td>
			<td nowrap class="td_form_white" colspan="3">
			   <%=View.getCommonCodeRadioBoxOption("field", "anaproduceType", "DRGSRTYPE", obj.getAnaproduceType(), null, "", "") %>                 
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >批號範圍</td>
			<td nowrap class="td_form_white" colspan="3">
			   <%=View.getCommonCodeRadioBoxOption("field", "analotType", "DRG0107", obj.getAnalotType(), null, "", "") %>               
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >回收原因</td>
			<td nowrap class="td_form_white" colspan="3"> 
			   <%=View.getCommonCodeRadioBoxOption("field", "anarecyclereason", "DRGRECRS", obj.getAnarecyclereason(), null, "", "") %> 
			   <input class="field" type="text" name="anarecyclersdesc" size="50" maxlength="100" value="<%=obj.getAnarecyclersdesc()%>">             
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >調查結果</td>
			<td nowrap class="td_form_white" colspan="3">  
			   <%=View.getCommonCodeKindBoxOption("field", "anasurvey", "DRG0109", obj.getAnasurvey()) %>  
			   <input class="field" type="text" name="anasurveyOther" size="50" maxlength="100" value="<%=obj.getAnasurveyOther()%>">           
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >預防措施</td>
			<td nowrap class="td_form_white" colspan="3">  
			   <%=View.getCommonCodeKindBoxOption("field", "anaprecaution", "DRG0110", obj.getAnaprecaution()) %> 
			   <input class="field" type="text" name="anaprecautionOther" size="50" maxlength="100" value="<%=obj.getAnaprecautionOther()%>">          
			</td>
		</tr>	
	</table>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<jsp:useBean id="obj" scope="request" class="com.kangdainfo.tcbw.view.sdrg.SDRG0401F">
	<jsp:setProperty name="obj" property="*"/>
</jsp:useBean>

<% 
obj = (com.kangdainfo.tcbw.view.sdrg.SDRG0401F) obj.doQueryOne0401();
%>   
   <table id="Tab4_1" width="100%" align="center" class="table_form">
        <tr>
			<td nowrap class="td_form_left" colspan="4">廠商確認</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="20%">處置日期</td>
			<td nowrap class="td_form_white"  colspan="3">
			  <%=View.getPopCalendar("field","checkpunishdate",obj.getCheckpunishdate())%> 
			  <input type="hidden" name="checkUpdateYn" value="<%=obj.getCheckUpdateYn()%>">               
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >回收品及庫存品<br>處置方式</td>
			<td nowrap class="td_form_white" colspan="3">
			   <%=View.getCommonCodeRadioBoxOption("field", "checkcyclestorage", "DRGRECCS", obj.getCheckcyclestorage(), null, "", "") %> 
			   <input class="field" type="text" name="checkcyclestorageOther" size="50" maxlength="100" value="<%=obj.getCheckcyclestorageOther()%>">               
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >總數量</td>
			<td nowrap class="td_form_white" colspan="3">
			   <input class="field" type="text" name="checktotalNum" size="10" maxlength="10" value="<%=obj.getChecktotalNum()%>">               
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >總數量單位</td>
			<td nowrap class="td_form_white" colspan="3">
			   <select class="field" name="checktotalUnit">
				  <%=View.getOptionCodeKind("DRGRECUNIT",obj.getChecktotalUnit())%>
			   </select>			                
			</td>
		</tr>
		<tr>
		   <td nowrap class="td_form">附件(銷燬記錄)</td>
		   <td nowrap class="td_form_white" colspan="3">  
		       <table width="100%" align="center" class="table_form">
	           <thead id="listTHEAD">           
	           <tr>
		          <th class="listTH" width="10%" >No.</th>
		          <th class="listTH" width="30%" >檔案名稱</th>
		          <th class="listTH" width="50%" >檔案說明</th>
		          <th class="listTH" width="10%">
		          <span id="spanDoUpload">
		               <input class="toolbar_default" type="button" followPK="false" name="doUpload9" value="附件上傳" onClick="upload('DRG040009')">
		          </span>
		          </th>
	           </tr>
	           </thead>
	           <tbody>
			      <%=obj.getAddFileDrg0409()%>
			   </tbody>
	           </table>
		   </td>
		</tr>
	</table>
	<table id="Tab4_2" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form_left" colspan="4">衛生局確認</td>
		</tr>
		<tr>
			<td nowrap class="td_form" width="20%">查核衛生局</td>
			<td nowrap class="td_form_white" colspan="3">
			    <select class="field" name="checkhealthbureau">
				  <%=obj.getOptionHealthbureau(obj.getId(),obj.getCheckhealthbureau())%>
			   </select>              
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >是否與回收報告書<br>所載數量相符</td>
			<td nowrap class="td_form_white" colspan="3">
			   <%=View.getRadioBoxTextOption("field","ischeckmatchnum","N;否;Y;是;",obj.getIscheckmatchnum())%>               
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >數量不符之理由</td>
			<td nowrap class="td_form_white" colspan="3">
			   <input class="field" type="text" name="checknonmatchreason" size="40" maxlength="50" value="<%=obj.getChecknonmatchreason()%>">               
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >查核日期</td>
			<td nowrap class="td_form_white" colspan="3">
			   <%=View.getPopCalendar("field","checkmanudate",obj.getCheckmanudate())%>                
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >聯絡窗口</td>
			<td nowrap class="td_form_white" colspan="3">  
			   <input class="field" type="text" name="checkcontactman" size="40" maxlength="50" value="<%=obj.getCheckcontactman()%>">             
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >聯絡電話</td>
			<td nowrap class="td_form_white" colspan="3">  
			   <input class="field" type="text" name="checkcontacttel" size="10" maxlength="10" value="<%=obj.getCheckcontacttel()%>">             
			</td>
		</tr>
		<tr>
		   <td nowrap class="td_form">附件(稽查記錄)</td>
		   <td nowrap class="td_form_white" colspan="3">  
		       <table width="100%" align="center" class="table_form">
	           <thead id="listTHEAD">           
	           <tr>
		          <th class="listTH" width="10%" >No.</th>
		          <th class="listTH" width="30%" >檔案名稱</th>
		          <th class="listTH" width="50%" >檔案說明</th>
		          <th class="listTH" width="10%">
		          <span id="spanDoUpload">
		               <input class="toolbar_default" type="button" followPK="false" name="doUpload10" value="附件上傳" onClick="upload('DRG040010')">
		          </span>
		          </th>
	           </tr>
	           </thead>
	           <tbody>
			      <%=obj.getAddFileDrg0410()%>
			   </tbody>
	           </table>
		   </td>
		</tr>		
	</table>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<div id="formContainer" style="height:auto">
	<table id="Tab1" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form">評估日期：</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getPopCalendar("field_Q","xxxDate","")%> 
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">產品是否進口：</td>
			<td nowrap class="td_form_white" colspan="3">
                <input type="radio" name="isImported" value="true">是
				<input type="radio" name="isImported" value="false">否
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">是否發佈新聞稿：</td>
			<td nowrap class="td_form_white" colspan="3">
                <input type="radio" name="isPublishedB" value="true">是
				<input type="radio" name="isPublishedB" value="false">否
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">是否發佈消費者知識服務網：</td>
			<td nowrap class="td_form_white" colspan="3">
                <input type="radio" name="isPublishedA" value="true">是
				<input type="radio" name="isPublishedA" value="false">否
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">消費者知識服務網上架日期：</td>
			<td nowrap class="td_form_white" colspan="3">
                <%=View.getPopCalendar("field_Q","xxxDate","")%> 
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">燈號：</td>
			<td nowrap class="td_form_white" colspan="3">
                <select>
					<option>紅燈</option>
					<option>黃燈</option>
					<option>綠燈</option>
				</select>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">查詢單位：</td>
			<td nowrap class="td_form_white" colspan="3">
                <input type="text" class="field" maxlength="20" size="50" value="" />
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">備註：</td>
			<td nowrap class="td_form_white" colspan="3">
                <input type="text" class="field" maxlength="20" size="50" value="" />
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">後續處理：</td>
			<td nowrap class="td_form_white" colspan="3">
                <textarea class="field_Q" name="" cols="65" rows="5"></textarea>
			</td>
		</tr>		
		<tr>
		   <td nowrap class="td_form" width="25%">附件：</td>
		   <td nowrap class="td_form_white" colspan="3">  
		       <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	           <thead id="listTHEAD">       
	           <tr>
		         <th class="listTH" width="45%">檔案名稱</th>
		         <th class="listTH" width="45%">檔案說明</th>
		         <th class="listTH" width="5%">		            
	                 <input style="font-size: 11px" type="button" class="field_btnAdd" value="" >
	             </th>
	           </tr>
	           </thead>
	           <tr>
		         <th class="listTROdd">xxx</th>
		         <th class="listTROdd">xxx</th>
		         <th class="listTROdd" width="5%">
		             <input style="font-size: 11px" type="button" class="field_btnRemove" value="" >
	                 <input style="font-size: 11px" type="button" class="field_btnAdd" value="" >
	             </th>
	           </tr>
               </table>    
		   </td>
		</tr>	
	</table>
</div>
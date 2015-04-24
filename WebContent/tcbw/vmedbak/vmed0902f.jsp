<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<!--Form區============================================================-->
	<table id="Tab9" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form" width="15%">FDA收文日期</td>
			<td nowrap class="td_form_white" width="35%">
				<%=View.getPopCalendar("field","notifierRevDate","")%>
			</td>
			<td nowrap class="td_form" width="15%">FDA收文字號</td>
			<td nowrap class="td_form_white" width="35%">
				<input class="field_Q" type="text" name="applNo" value="">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >收文摘要</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" rows="3" cols="100"></textarea>
			</td>
		</tr>
		<tr>
		    <td nowrap class="td_form">收文附件</td>
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
		<tr>
			<td nowrap class="td_form" >FDA回文日期</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getPopCalendar("field","notifierRevDate","")%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >回文摘要</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" rows="3" cols="100"></textarea>
			</td>
		</tr>
		<tr>
		    <td nowrap class="td_form">回文附件</td>
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
		<tr>
			<td nowrap class="td_form" >廠商回收完成日期</td>
			<td nowrap class="td_form_white">
				<%=View.getPopCalendar("field","notifierRevDate","")%>
			</td>
			<td nowrap class="td_form" >廠商回收完成字號</td>
			<td nowrap class="td_form_white">
				<input class="field_Q" type="text" name="applNo" value="">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >廠商回收摘要</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" rows="3" cols="100"></textarea>
			</td>
		</tr>
		<tr>
		    <td nowrap class="td_form">廠商回收狀況附件</td>
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


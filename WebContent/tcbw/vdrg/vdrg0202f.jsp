<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>

		<table id="Tab2"  class="table_form" width="100%">
		    <tr>
		       <td nowrap  class="td_form" width="15%">國內許可證字號：</td>
		       <td nowrap class="td_form_white" colspan="3">  
		       12345、23456
		       </td>
		    </tr>
			<tr>
				<td nowrap  class="td_form" width="15%">回覆日期：</td>
				<td class="td_form_white">
					<%=View.getPopCalendar("field","notifierRevDate","")%>
				</td>
			</tr>	
			<tr>
				<td nowrap class="td_form">廠商回覆內容：</td>
				<td class="td_form_white">
					<textarea rows="3" cols="80"></textarea>
				</td>
			</tr>
			<tr>
				<td nowrap class="td_form">產品是否進口：</td>
				<td class="td_form_white">
					<input class="field_Q" type="radio" name="a" />是
					<input class="field_Q" type="radio" name="a" />否
				</td>
			</tr>
			<tr>
				<td nowrap class="td_form">產品未進口原因：</td>
				<td nowrap class="td_form_white" >
				    <select class="field_Q">
			        <option>請選擇</option>
			        <option>產品未進口原因1</option>
			        <option>產品未進口原因2</option>
			        <option>其他</option>
			        </select>
					<input class="field_Q" type="text"  />
				</td>
			</tr>
			<tr>
		       <td nowrap class="td_form">附件(回覆附件)</td>
		       <td nowrap class="td_form_white">  
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

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>

<!--Form區============================================================-->
	<table id="Tab7" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form" width="15%">公告日期</td>
			<td nowrap class="td_form_white" width="35%">
				<%=View.getPopCalendar("field","notifierRevDate","")%>
			</td>
			<td nowrap class="td_form" width="15%">有效日期</td>
			<td nowrap class="td_form_white" width="35%">
				<%=View.getPopCalendar("field","notifierRevDate","")%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >標題</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="applNo" size="60" value="">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >內容</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" rows="5" cols="100"></textarea>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >備註</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" rows="5" cols="100"></textarea>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >資料來源</td>
			<td nowrap class="td_form_white" colspan="3">
				<input class="field" type="text" name="applNo" value="">
			</td>
		</tr>
		<tr>
		    <td nowrap class="td_form">附件</td>
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
			<td nowrap class="td_form" >是否發佈新聞稿</td>
			<td nowrap class="td_form_white">
				<input class="field" type="radio" id="attachment" name="attachment" value="Y">是
				<input class="field" type="radio" id="attachment" name="attachment" value="N">否
			</td>
			<td nowrap class="td_form" >是否發佈消費者知識服務網</td>
			<td nowrap class="td_form_white">
				<input class="field" type="radio" id="attachment" name="attachment" value="Y">是
				<input class="field" type="radio" id="attachment" name="attachment" value="N">否
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >是否發佈署網</td>
			<td nowrap class="td_form_white">
				<input class="field" type="radio" id="attachment" name="attachment" value="Y">是
				<input class="field" type="radio" id="attachment" name="attachment" value="N">否
			</td>
			<td nowrap class="td_form" >燈號</td>
			<td nowrap class="td_form_white">
				<input class="field" type="radio" id="attachment" name="attachment" value="R">紅燈
				<input class="field" type="radio" id="attachment" name="attachment" value="Y">黃燈
				<input class="field" type="radio" id="attachment" name="attachment" value="G">綠燈
			</td>
		</tr>
	</table>


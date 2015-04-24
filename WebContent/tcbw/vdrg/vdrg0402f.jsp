<%@ page contentType="text/html;charset=UTF-8" %>

		<table id="Tab4"  class="table_form" width="100%">		
			<tr>
				<td nowrap  class="td_form" width="15%">評估日期：</td>
				<td class="td_form_white">
					<input class="field_Q calendar-input" type="text" name="q_notifierRepDateS" size="7" maxlength="7" />
					<button name="btn_q_notifierRepDateS" class="field_Q calendar-button" type="button" 
						onclick="popCalendar('q_notifierRepDateS&js=')" title="萬年曆輔助視窗"><image src='../../images/dynCalendar.gif'></button>
				</td>
			</tr>	
			
			<tr>
				<td nowrap class="td_form">是否發佈新聞稿：</td>
				<td class="td_form_white">
					<input class="field_Q" type="radio" name="a" />是
					<input class="field_Q" type="radio" name="a" />否
				</td>
			</tr>
			<tr>
				<td nowrap class="td_form">是否發佈消費者知識服務網：</td>
				<td class="td_form_white">
					<input class="field_Q" type="radio" name="a" />是
					<input class="field_Q" type="radio" name="a" />否
				</td>
			</tr>
			<tr>
				<td nowrap  class="td_form">消費者知識服務網上架日期：</td>
				<td class="td_form_white">
					<input class="field_Q calendar-input" type="text" name="q_notifierRepDateS" size="7" maxlength="7" />
					<button name="btn_q_notifierRepDateS" class="field_Q calendar-button" type="button" 
						onclick="popCalendar('q_notifierRepDateS&js=')" title="萬年曆輔助視窗"><image src='../../images/dynCalendar.gif'></button>
				</td>
			</tr>	
			
			<tr>
				<td nowrap class="td_form">燈號：</td>
				<td nowrap class="td_form_white" >
					<input class="field_Q" type="radio" name="a">紅燈
			        <input class="field_Q" type="radio" name="a">黃燈
			        <input class="field_Q" type="radio" name="a">綠燈
				</td>
			</tr>
			<tr>
				<td nowrap class="td_form">後續處理：</td>
				<td nowrap class="td_form_white">
					<textarea rows="3" cols="80"></textarea>
				</td>
			</tr>
			<tr>
				<td nowrap class="td_form">評估備註：</td>
				<td nowrap class="td_form_white">
					<input class="field_Q" type="text" size="80" />
				</td>
			</tr>
			<tr>
		       <td nowrap class="td_form">附件(評估附件)</td>
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

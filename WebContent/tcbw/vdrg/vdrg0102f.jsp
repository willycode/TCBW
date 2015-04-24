<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>

		<table id="Tab1" class="table_form" width="100%">
			<tr>
				<td nowrap width="15%" class="td_form">案件編號：</td>
				<td class="td_form_white" width="35%">
					<input class="field_Q" type="text" size="10" maxlength="10" />
				</td>
				<td nowrap width="15%" class="td_form">案件狀態：</td>
				<td class="td_form_white" width="35%">
					<input class="field_Q" type="text" size="10" maxlength="10" />
				</td>
			</tr>
			<tr>
				<td nowrap  class="td_form">資料收集日期：</td>
				<td class="td_form_white">
					<%=View.getPopCalendar("field_Q","notifierRevDate","")%>
				</td>
				<td nowrap class="td_form">發佈單位：</td>
				<td class="td_form_white">
					<%=View.getPopCode("field_Q","medMainCategoryCode","",Common.get(View.getOneCodeName("MEDMCT",Common.get(""))),"","MEDMCT","medMainCategory","")%>
				</td>
			</tr>	
			<tr>
				<td nowrap class="td_form">是否為轉發：</td>
				<td class="td_form_white">
				   <input class="field_Q" type="radio" name="ddd" value="true">是
		           <input class="field_Q" type="radio" name="ddd" value="false">否
				</td>
				<td nowrap  class="td_form">轉發單位：</td>
				<td class="td_form_white">
					<%=View.getPopCode("field_Q","medMainCategoryCode","",Common.get(View.getOneCodeName("MEDMCT",Common.get(""))),"","MEDMCT","medMainCategory","")%>
				</td>
			</tr>	
			<tr>
				<td nowrap class="td_form">資料來源：</td>
				<td class="td_form_white">
					<input class="field_Q" type="text" />
				</td>
				<td nowrap class="td_form">狀況：</td>
				<td nowrap class="td_form_white" >
					<input class="field_Q" type="checkbox" name="a">回收
					<input class="field_Q" type="checkbox" name="a">警訊
				</td>
			</tr>
			<tr>
				<td nowrap class="td_form">原始發佈日期：</td>
				<td class="td_form_white">
					<%=View.getPopCalendar("field","notifierRevDate","")%>
				</td>
				<td nowrap class="td_form">訊息回收品項數：</td>
				<td nowrap class="td_form_white">
					<input class="field_Q" type="text"  />
				</td>
			</tr>
	
			<tr>
				<td nowrap class="td_form">商品名：</td>
				<td nowrap class="td_form_white">
					<input class="field_Q" type="text"  />
				</td>
				<td nowrap class="td_form">學名：</td>
				<td nowrap class="td_form_white" >
					<input class="field_Q" type="text"  />
				</td>
			</tr>

			<tr>
				<td nowrap class="td_form">劑型：</td>
				<td nowrap class="td_form_white" colspan="3">
					<input class="field_Q" type="checkbox" name="a">劑型1
					<input class="field_Q" type="checkbox" name="a">劑型2
				</td>
			</tr>
			<tr>
			    <td nowrap class="td_form">廠商：</td>
				<td nowrap class="td_form_white" >
					<input class="field_Q" type="text"  />
				</td>
				<td nowrap class="td_form">製造廠：</td>
				<td nowrap class="td_form_white" >
					<input class="field_Q" type="text"  />
				</td>
			</tr>
			<tr>
			    <td nowrap class="td_form">產品批號：</td>
				<td nowrap class="td_form_white">
					<input class="field_Q" type="text" size="30" />
				</td>
				<td nowrap class="td_form">品質異常型態：</td>
				<td nowrap class="td_form_white">
					<select class="field_Q" >
					<option>品質異常型態1</option>
					<option>品質異常型態2</option>
					</select>
				</td>
			</tr>
			<tr>
				<td nowrap class="td_form">事件簡述：</td>
				<td nowrap class="td_form_white" colspan="3">
					<input class="field_Q" type="text" size="80"  />
				</td>
			</tr>
			
			<tr>
				<td nowrap class="td_form">回收型態：</td>
				<td nowrap class="td_form_white">
					<input class="field_Q" type="checkbox" name="a">主動回收
					<input class="field_Q" type="checkbox" name="a">被動回收
					<input class="field_Q" type="checkbox" name="a">無法確認
					<input class="field_Q" type="checkbox" name="a">非回收訊息
				</td>
				<td nowrap class="td_form">適應症：</td>
				<td nowrap class="td_form_white">
					<input class="field_Q" type="text"  />
				</td>
			</tr>
			<tr>
				<td nowrap class="td_form">是否草擬紅綠燈初稿：</td>
				<td nowrap class="td_form_white" colspan="3">
					<input class="field_Q" type="radio" name="isProposed" value="true">是
					<input class="field_Q" type="radio" name="isProposed" value="false">否
				</td>
			</tr>
			<tr>
				<td nowrap class="td_form">紅綠燈初稿：</td>
				<td nowrap class="td_form_white" colspan="3">
				
				 <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	             <thead id="listTHEAD">       
	             <tr>
		             <th class="listTH" width="45%">檔案名稱</th>
		             <th class="listTH" width="45%">檔案說明</th>
	              </tr>
	              </thead>
	             <tr>
		             <th class="listTROdd">xxx</th>
		             <th class="listTROdd">xxx</th>
	              </tr>
               </table>    
				</td>
			</tr>
			<tr>
				<td nowrap class="td_form">警訊備註：</td>
				<td nowrap class="td_form_white" colspan="3">
					<textarea cols="40"></textarea>
				</td>
			</tr>
			<tr>
		       <td nowrap class="td_form_white" colspan="4">  
		         <table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	             <thead id="listTHEAD">       
	             <tr>
		             <th class="listTH" width="45%">國內許可證</th>
		             <th class="listTH" width="45%">國內許可證持有商</th>
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

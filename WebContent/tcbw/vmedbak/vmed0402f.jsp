<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>

<!--Form區============================================================-->
	<table id="Tab4" width="100%" align="center" class="table_form">
	
	<tr>
		<td nowrap class="td_form_left" colspan="4">摘譯資訊</td>
	</tr>
	<tr>
			<td nowrap class="td_form" >是否涉及回收</td>
			<td nowrap class="td_form_white"  >
				<input class="field" type="radio" id="attachment" name="attachment" value="Y">是
				<input class="field" type="radio" id="attachment" name="attachment" value="N">否
			</td>
			<td nowrap class="td_form" >是否摘譯</td>
			<td nowrap class="td_form_white"  >
				<input class="field" type="radio" id="attachment" name="attachment" value="Y">是
				<input class="field" type="radio" id="attachment" name="attachment" value="N">否
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form" >摘譯人員</td>
			<td nowrap class="td_form_white"  >
				<input class="field" type="text" name="applNo" value="">
			</td>
			<td nowrap class="td_form" >醫材型號</td>
			<td nowrap class="td_form_white"  >
				<input class="field" type="text" name="applNo" value="">
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form" >醫材批號</td>
			<td nowrap class="td_form_white"  colspan="3">
				<input class="field" type="text" name="applNo" value="">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >國內情形</td>
			<td nowrap class="td_form_white"  colspan="3">
				<input class="field" type="text" name="applNo" size="60" value="">
			</td>			
	</tr>
	<tr>
		<td nowrap class="td_form_left" colspan="4">廠商回覆</td>
	</tr>
	<tr>
			<td nowrap class="td_form">許可證字：</td>
			<td nowrap class="td_form_white" colspan="3">
		    <select name="q_permit" class="field_RO">
				<%=View.getOptionCodeKind("MEDPKID","")%>
			</select>
			字第<input class="field_RO" type="text" name="q_permitNumber" size="10" maxlength="6" value="">號
		</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >中文品名</td>
			<td nowrap class="td_form_white"  >
				<input class="field_RO" type="text" name="applNo" value="">
			</td>
			<td nowrap class="td_form" >英文品名</td>
			<td nowrap class="td_form_white"  >
				<input class="field_RO" type="text" name="applNo" value="">
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form" >許可證核准日期</td>
			<td nowrap class="td_form_white"  >
				<%=View.getPopCalendar("field_RO","notifierRevDate","")%>
			</td>
			<td nowrap class="td_form" >許可證有效日期</td>
			<td nowrap class="td_form_white"  >
				<%=View.getPopCalendar("field_RO","notifierRevDate","")%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >許可證持有商</td>
			<td nowrap class="td_form_white">
				<input class="field_RO" type="text" name="applNo" value="">
			</td>
			<td nowrap class="td_form" >製造廠</td>
			<td nowrap class="td_form_white"  colspan="3">
				<input class="field_RO" type="text" name="applNo" value="">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >製造廠國別</td>
			<td nowrap class="td_form_white"  >
				<input class="field_RO" type="text" name="applNo" value="">
			</td>
			<td nowrap class="td_form" >醫材級數</td>
			<td nowrap class="td_form_white"  >
				<%=View.getPopCalendar("field_RO","notifierRevDate","")%>
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form">醫材主類別</td>
			<td nowrap class="td_form_white" >
			    <%=View.getPopCode("field_RO","medMainCategoryCode","",Common.get(View.getOneCodeName("MEDMCT",Common.get(""))),"","MEDMCT","medMainCategory","")%>
			</td>
			<td nowrap class="td_form">醫材次類別</td>
			<td nowrap class="td_form_white" >
			     <%=View.getPopCode("field_RO","medSecCategoryCode","",Common.get(View.getOneCodeName("MEDSCT",Common.get(""))),"medMainCategory","MEDSCT","medSecCategory","")%>
			</td>
		</tr>
	
		<tr>
			<td nowrap class="td_form" width="15%">回覆日期</td>
			<td nowrap class="td_form_white"  width="35%">
				<%=View.getPopCalendar("field","notifierRevDate","")%>
			</td>
			<td nowrap class="td_form" width="15%">本許可證是否為警訊內容產品</td>
			<td nowrap class="td_form_white"  width="35%">
				<input class="field" type="radio" id="attachment" name="attachment" value="Y">是
				<input class="field" type="radio" id="attachment" name="attachment" value="N">否
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >是否國內有受影響產品</td>
			<td nowrap class="td_form_white"  colspan="3">
				<input class="field" type="radio" id="attachment" name="attachment" value="Y">是
				<input class="field" type="radio" id="attachment" name="attachment" value="N">否
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >回覆摘要</td>
			<td nowrap class="td_form_white"  colspan="3">
				<input class="field" type="text" name="applNo" size="60" value="">
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form" >醫材型號</td>
			<td nowrap class="td_form_white"  >
				<input class="field" type="text" name="applNo" value="">
			</td>
			<td nowrap class="td_form" >醫材批號</td>
			<td nowrap class="td_form_white"  >
				<input class="field" type="text" name="applNo" value="">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >受影響數量</td>
			<td nowrap class="td_form_white">
				<input class="field" type="text" name="applNo" value="">
			</td>
			<td nowrap class="td_form" >聯絡窗口</td>
			<td nowrap class="td_form_white"  >
				<input class="field" type="text" name="applNo" value="">
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >聯絡電話</td>
			<td nowrap class="td_form_white"  >
				<input class="field" type="text" name="applNo" value="">
			</td>
			<td nowrap class="td_form" >聯絡信箱</td>
			<td nowrap class="td_form_white"  >
				<input class="field" type="text" name="applNo" value="">
			</td>			
		</tr>
		<tr>
		    <td nowrap class="td_form">回覆附件</td>
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
<td class="bgList" colspan="4">
<div id="listContainer" style="height:auto">
<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
	<thead id="listTHEAD">
	<tr>
<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',0,false);" href="#">NO</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">許可證字號</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">中文品名</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">英文品名</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',4,false);" href="#">許可證核准日期</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',5,false);" href="#">許可證持有商</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',6,false);" href="#">製造廠</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',7,false);" href="#">醫材主類別</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',8,false);" href="#">醫材次類別</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<tr id="listContainerRow141" class='listTREven' onmouseover="this.className='listTRMouseover'" onmouseout="this.className='listTREven'" onClick="listContainerRowClick('141');queryOne('141');" >
 	<td  class='listTDEven' >1.</td>
 	<td  class='listTDEven' ></td>
 	<td  class='listTDEven' ></td>
 	<td  class='listTDEven' ></td>
 	<td  class='listTDEven' ></td>
 	<td  class='listTDEven' ></td>
 	<td  class='listTDEven' ></td>
 	<td  class='listTDEven' ></td>
 	<td  class='listTDEven' ></td>
 	</tr>
	
	</tbody>
</table>
</div>
</td></tr>
		
</table>


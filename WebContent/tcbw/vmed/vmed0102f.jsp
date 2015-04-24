<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>

<!--Form區===========================================================-->
	<table id="Tab1" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form" width="15%">案件編號</td>
			<td nowrap class="td_form_white"  width="35%">
				<input class="field" type="text" name="applNo" value="">
			</td>
			<td nowrap class="td_form"  width="15%">案件狀態</td>
			<td nowrap class="td_form_white" width="35%"  >
				<select class="field" name="medicalIssues1">
				   <option value=''>請選擇</option>
				   <option value='0001' >案件狀態1</option>
				   <option value='1000' >案件狀態2</option>
				   <option value='1001' >案件狀態3</option>
			    </select>
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form" >發佈單位</td>
			<td nowrap class="td_form_white"  >
				<select class="field" name="medicalIssues1">
				   <option value=''>請選擇</option>
				   <option value='0001' >A</option>
				   <option value='1000' >B</option>
				   <option value='1001' >C</option>
			    </select>
			</td>	
			<td nowrap class="td_form" >警訊類別</td>
			<td nowrap class="td_form_white">
				<input class="field" type="radio" id="attachment" name="attachment" value="1">警訊
				<input class="field" type="radio" id="attachment" name="attachment" value="2">回收
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form">醫材主類別</td>
			<td nowrap class="td_form_white" colspan="3">
			    <%=View.getPopCode("field","medMainCategoryCode","",Common.get(View.getOneCodeName("MEDMCT",Common.get(""))),"","MEDMCT","medMainCategory","")%>
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form" >產品名稱</td>
			<td nowrap class="td_form_white"  colspan="3">
				<input class="field" type="text" name="applNo" size="40" value="">
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form" >國外回收等級</td>
			<td nowrap class="td_form_white"  >
				<input class="field" type="radio" id="attachment" name="attachment" value="1">Class I
				<input class="field" type="radio" id="attachment" name="attachment" value="2">Class II
				<input class="field" type="radio" id="attachment" name="attachment" value="3">Class III
			</td>
			<td nowrap class="td_form" >資料收集日期</td>
			<td nowrap class="td_form_white"  >
				<%=View.getPopCalendar("field","notifierRevDate","")%>
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form" >發佈日期</td>
			<td nowrap class="td_form_white"  >
				<%=View.getPopCalendar("field","notifierRevDate","")%>
			</td>
			<td nowrap class="td_form" >製造廠</td>
			<td nowrap class="td_form_white"  >
				<input class="field" type="text"  size="30" name="applNo" value="">
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form" >警訊摘要</td>
			<td nowrap class="td_form_white"  colspan="3">
				<input class="field" type="text" size="80" name="applNo" value="">
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form" >產品型號及批號</td>
			<td nowrap class="td_form_white"  colspan="3">
				<input class="field" type="text" size="80" name="applNo" value="">
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form" >廠商行動</td>
			<td nowrap class="td_form_white"  colspan="3">
				<input class="field" type="text" size="80" name="applNo" value="">
			</td>	
		</tr>
		<tr>
			<td nowrap class="td_form" >影響地區</td>
			<td nowrap class="td_form_white"  colspan="3">
				<input class="field" type="text" size="80" name="applNo" value="">
			</td>			
		</tr>
		<tr>
			<td nowrap class="td_form" >影響數量</td>
			<td nowrap class="td_form_white"  colspan="3">
				<input class="field" type="text" name="applNo" value="">
			</td>		
		</tr>
		<tr>
			<td nowrap class="td_form" >原始網頁</td>
			<td nowrap class="td_form_white" colspan="3" >
				<input class="field" type="text" size="80" name="applNo" value="">
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
		        <th class="listTH" width="10%">		            
	                <input style="font-size: 11px" type="button" class="field_btnAdd" value="" >
	            </th>
	        </tr>
	        </thead>
	        <tr>
		        <th class="listTROdd">xxx</th>
		        <th class="listTROdd">xxx</th>
		        <th class="listTROdd" width="10%">
		          <input style="font-size: 11px" type="button" class="field_btnRemove" value="" >
	              <input style="font-size: 11px" type="button" class="field_btnAdd" value="" >
	            </th>
	        </tr>
            </table>    
		   </td>
		</tr>
	</table>


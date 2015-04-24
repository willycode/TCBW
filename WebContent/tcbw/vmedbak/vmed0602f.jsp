<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>

<!--Form區============================================================-->
	<table id="Tab6" width="100%" align="center" class="table_form">
	    <tr>
			<td nowrap class="td_form" >完成確認日期</td>
			<td nowrap class="td_form_white" colspan="3">
				<%=View.getPopCalendar("field","notifierRevDate","")%>
			</td>
		</tr>
		<tr>
		    <td nowrap class="td_form_left" colspan="4">廠商摘譯確認</td>
	    </tr>
		<tr>
			<td nowrap class="td_form" >許可證持有商</td>
			<td nowrap class="td_form_white" colspan="3">
				<input type="text">
			</td>
		</tr>
		
		<tr>
			<td nowrap class="td_form" width="15%">廠商通知日期</td>
			<td nowrap class="td_form_white" width="35%">
				<%=View.getPopCalendar("field","notifierRevDate","")%>
			</td>
			<td nowrap class="td_form" width="15%">廠商確認日期</td>
			<td nowrap class="td_form_white" width="35%">
				<%=View.getPopCalendar("field","notifierRevDate","")%>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form" >廠商確認摘要</td>
			<td nowrap class="td_form_white" colspan="3">
				<textarea class="field" rows="15" cols="100"></textarea>
			</td>
		</tr>
		<tr>
		    <td nowrap class="td_form">廠商確認附件</td>
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
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">許可證持有商</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">廠商通知日期</a></th>
			<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',3,false);" href="#">廠商確認日期</a></th>
	</tr>
	</thead>
	<tbody id="listTBODY">
	<tr id="listContainerRow141" class='listTREven' onmouseover="this.className='listTRMouseover'" onmouseout="this.className='listTREven'" onClick="listContainerRowClick('141');queryOne('141');" >
 	<td  class='listTDEven' >1.</td>
 	<td  class='listTDEven' ></td>
 	<td  class='listTDEven' ></td>
 	<td  class='listTDEven' ></td>
 	</tr>
	
	</tbody>
</table>
</div>
</td>
</tr>
		
	</table>
	
	

		

	


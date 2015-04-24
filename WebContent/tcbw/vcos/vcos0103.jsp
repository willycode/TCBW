<%@ include file="../../home/head.jsp" %>   
<%@ page contentType="text/html;charset=UTF-8" %>
<div id="formContainer" style="height:auto">
	<table id="Tab1" width="100%" align="center" class="table_form">
		<tr>
			<td nowrap class="td_form" width="20%">案件編號：</td>
			<td nowrap class="td_form_white" >
                <input type="text" class="field" maxlength="20" size="50" value="" />
			</td>
			<td nowrap class="td_form" width="20%">案件狀態：</td>
			<td nowrap class="td_form_white" width="30%">
			    <select>
					<option>請選擇</option>
					<option>狀態A</option>
					<option>狀態B</option>
				</select>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">產品類別：</td>
			<td nowrap class="td_form_white" colspan="3">
              	 <input type="text" class="field_RO" maxlength="20" size="10" value="化粧品" />
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">品名：</td>
			<td nowrap class="td_form_white" colspan="3">
                <input type="text" class="field" maxlength="20" size="50" value="" />
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">資料收集日期：</td>
			<td nowrap class="td_form_white" colspan="3">
               <%=View.getPopCalendar("field_Q","xxxDate","")%> 
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">發佈單位：</td>
			<td nowrap class="td_form_white" >
                <select>
					<option>請選擇</option>
				</select>
			</td>
			<td nowrap class="td_form">發佈單位國家：</td>
			<td nowrap class="td_form_white" >
                <select>
					<option>請選擇</option>
				</select>
			</td>
		</tr>
		<tr>
		    <td nowrap class="td_form">產地：</td>
			<td nowrap class="td_form_white" colspan="3">
                <input type="text" class="field" maxlength="20" size="50" value="" />
			</td>			
		</tr>
		<tr>
		    <td nowrap class="td_form">化粧品項目：</td>
			<td nowrap class="td_form_white" >
                <select>
					<option>請選擇</option>
				</select>
			</td>
			<td nowrap class="td_form">品牌/廠商：</td>
			<td nowrap class="td_form_white" >
                <input type="text" class="field" maxlength="20" size="50" value="" />
			</td>
			
		</tr>
		<tr>
			<td nowrap class="td_form">狀況：</td>
			<td nowrap class="td_form_white" >
                <select>
					<option>請選擇</option>
					<option>回收</option>
					<option>警訊</option>
				</select>
			</td>
			<td nowrap class="td_form">發佈日期：</td>
			<td nowrap class="td_form_white" >
                <%=View.getPopCalendar("field_Q","xxxDate","")%> 
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">訊息主題：</td>
			<td nowrap class="td_form_white" colspan="3">
                <select>
					<option>主題1</option>
					<option>主題2</option>
					<option>主題3</option>
					<option>主題4</option>
					<option>主題5</option>
				</select>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">資訊內容摘要：</td>
			<td nowrap class="td_form_white" colspan="3">
                <textarea class="field_Q" name="" cols="65" rows="5"></textarea>
			</td>
		</tr>
		<tr>
			<td nowrap class="td_form">產品批號：</td>
			<td nowrap class="td_form_white" colspan="3">
                <textarea class="field_Q" name="" cols="65" rows="5"></textarea>
			</td>
		</tr>
		<tr>
		<td nowrap class="td_form">資料來源：</td>
			<td nowrap class="td_form_white" colspan="3">
                <input type="text" class="field" maxlength="20" size="50" value="" />
			</td>
		</tr>		
	</table>
</div>
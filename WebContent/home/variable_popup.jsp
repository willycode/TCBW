<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Expires" content="0"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-control" content="no-cache"/>
<link rel="stylesheet" href="../js/default.css" type="text/css"/>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/function.js"></script>
</head>
<body>
<form action="">
	<div>
		<table class="table_form queryTable" width="100%">
			<tr>
				<td class="td_form">變式代號：</td>
				<td class="td_form_white">
					<input class="field_Q" type="text" size="10" maxlength="11" />
				</td>
			</tr>
			<tr>
				<td class="td_form">變式說明：</td>
				<td class="td_form_white">
					<input class="field_Q" type="text" size="10" maxlength="11" />
				</td>
			</tr>
		</table>
	</div>
	
	<div>
		<table width="99%" border="0" align="center">
			<tr>
				<td nowrap class="querytext" valign="middle"><span
					class="pagetext">一頁<input type="text" class="field_Q"
						name="pageSize" maxlength="4" size="4" value="10"
						autocomplete="off" onchange="gotoPage(1);"
						style="font-size: 12px;">筆, 共3筆 第1到3筆
				</span></td>
		
				<td nowrap class="querytext" valign="middle">
					<div align="right">
						<span class="pagetext"> 共1頁</span>
					</div>
				</td>
			</tr>
		</table>

		<table class="table_form" width="100%" cellspacing="0" cellpadding="0">
			<thead id="listTHEAD">
				<tr>
					<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',0,false);" href="#">NO</a></th>
					<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',1,false);" href="#">變式代號</a></th>
					<th class="listTH"><a class="text_link_w" onclick="return sortTable('listTBODY',2,false);" href="#">變式說明</a></th>
				</tr>
			</thead>
			<tbody id="listTBODY">
				<tr class='listTROdd'
					onmouseover="this.className='listTRMouseover'"
					onmouseout="this.className='listTROdd'">
					<td class='listTDOdd'>1.</td>
					<td class='listTDOdd'>43543534</td>
					<td class='listTDOdd'>變式A</td>
				</tr>
				
				<tr class='listTREven'
					onmouseover="this.className='listTRMouseover'"
					onmouseout="this.className='listTREven'">
					<td class='listTDEven'>2.</td>
					<td class='listTDEven'>345645654</td>
					<td class='listTDEven'>變式B</td>
				</tr>
				
				<tr class='listTROdd'
					onmouseover="this.className='listTRMouseover'"
					onmouseout="this.className='listTROdd'">
					<td class='listTDOdd'>3.</td>
					<td class='listTDOdd'>123234543</td>
					<td class='listTDOdd'>變式C</td>
				</tr>
			</tbody>
		</table>
	</div>
</form>

</body>

</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<script type="text/javascript">

var idCOS4005DbCount = 0;
var arrCOS4005DbFieldName = new Array("cName","manufactorName","useDateS","useDateE","useRate","useMethod","manufactorNo","expirationDate","tradeDate");
var arrCOS4005DbFieldZhName = new Array("","","使用期間起","使用期間迄","","","","保存期限","購買日期");

function addCOS4005DbRow(tblId,v1,v2,v3,v4,v5,v6,v7,v8,v9,v10){
	$("#COS4005DbListTFOOT").remove();
	
	idCOS4005DbCount += 1;
	var tbl = document.getElementById(tblId);
	var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);
	var id = v10!=null?v10:("tmp" + idCOS4005DbCount);
	
    var strCName = row.insertCell(0);
    strCName.innerHTML = '<input type=hidden name=COS4005DbRow value=' + id + '><input type=text class=field name=cName' + id + ' size=20 maxlength=90 style="font-size: 10pt" value="' + (v1!=null?v1:'') + '">';
	
	var strManufactorName = row.insertCell(1);
	strManufactorName.innerHTML = '<input type=text class=field name=manufactorName' + id + ' size=20 maxlength=50 style="font-size: 10pt" value="' + (v2!=null?v2:'') + '">';

	var strUseDateSE = row.insertCell(2);
	strUseDateSE.innerHTML = '<input type=text class=field name=useDateS' + id + ' size=7 maxlength=7 style="font-size: 10pt" value="' + (v3!=null?v3:'') + '"><button class=field style="font-size: 10px" type="button" name="btn_useDateS' + id + '" onClick="popCalendar(\'useDateS' + id + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>' + 
							 '~<input type=text class=field name=useDateE' + id + ' size=7 maxlength=7 style="font-size: 10pt" value="' + (v4!=null?v4:'') + '"><button class=field style="font-size: 10px" type="button" name="btn_useDateE' + id + '" onClick="popCalendar(\'useDateE' + id + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	
	var strUseRate = row.insertCell(3);						 
	strUseRate.innerHTML = '<input type=text class=field name=useRate' + id + ' size=20 maxlength=50 style="font-size: 10pt" value="' + (v5!=null?v5:'') + '">';						 

	var strUseMethod = row.insertCell(4);						 
	strUseMethod.innerHTML = '<input type=text class=field name=useMethod' + id + ' size=20 maxlength=50 style="font-size: 10pt" value="' + (v6!=null?v6:'') + '">';
	
	var strManufactorNo = row.insertCell(5);						 
	strManufactorNo.innerHTML = '<input type=text class=field name=manufactorNo' + id + ' size=15 maxlength=10 style="font-size: 10pt" value="' + (v7!=null?v7:'') + '">';

	var strExpirationDate = row.insertCell(6);						 
	strExpirationDate.innerHTML = '<input type=text class=field name=expirationDate' + id + ' size=7 maxlength=7 style="font-size: 10pt" value="' + (v8!=null?v8:'') + '"><button class=field style="font-size: 10px" type="button" name="btn_expirationDate' + id + '" onClick="popCalendar(\'expirationDate' + id + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';

	var strTradeDate = row.insertCell(7);						 
	strTradeDate.innerHTML = '<input type=text class=field name=tradeDate' + id + ' size=7 maxlength=7 style="font-size: 10pt" value="' + (v9!=null?v9:'') + '"><button class=field style="font-size: 10px" type="button" name="btn_tradeDate' + id + '" onClick="popCalendar(\'tradeDate' + id + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	
	var actionText = row.insertCell(8);
	actionText.align = "center";
	
	var sb = new StringBuffer();
	sb.append('<input style="font-size: 11px" type="button" class="field_btnRemove" value="" onclick="removeRowFromTable(\'' ).append( tblId ).append( '\',this.parentNode.parentNode.rowIndex);createCOS4005DbListTFOOT();">');
	sb.append('<input style="font-size: 11px" type="button" class="field_btnAdd" value="" onclick="copyCOS4005DbRow(\'' ).append( tblId ).append( '\',\'' + id + '\');">');
	actionText.innerHTML = sb.toString();
}

function copyCOS4005DbRow(tblId, rid) {
	var arrFd = new Array(9);
	for(var i=0; i<arrFd.length; i++){		
		if(isObj(document.all.item(arrCOS4005DbFieldName[i] + rid))){
			arrFd[i] = document.all.item(arrCOS4005DbFieldName[i] + rid).value;
		}
	} 	
	addCOS4005DbRow(tblId, arrFd[0], arrFd[1], arrFd[2], arrFd[3], arrFd[4], arrFd[5], arrFd[6], arrFd[7], arrFd[8]);
}

function validateCOS4005DbTable() {
	var COS4005DbArray = document.getElementsByName("COS4005DbRow");	
	if(COS4005DbArray!=null && COS4005DbArray.length>0){	
	
		for(var i=0; i<COS4005DbArray.length; i++){
			var id = COS4005DbArray[i].value;
			
			var sb = new StringBuffer();
			for(var j=0; j<arrCOS4005DbFieldName.length; j++){
				var fd	= document.all.item(arrCOS4005DbFieldName[j] + id);
				if(isObj(fd) && arrCOS4005DbFieldZhName[j] != "") {
					sb.append(checkDate(fd, arrCOS4005DbFieldZhName[j]));
				}
			}
			if (sb.toString().length>0) return sb.toString();
		}
	}
	return "";
}

function createCOS4005DbListTFOOT(){
	var tbl = $("#tabCOS4005Db").get(0);
	if(tbl.rows.length == 1){
		var t = $("<tfoot id='COS4005DbListTFOOT'><tr><td>&nbsp;</td></tr></tfoot>");
		$(tbl).append(t);
	}
}
</script>

<table width="100%" class="table_form" cellspacing="0" cellpadding="0" id="tabCOS4005Db">
	<thead id="COS4005DbListTHEAD">
	<th class="listTH"><font size="2">品名</font></th>
	<th class="listTH"><font size="2">製造廠/進口代理商</font></th>
	<th class="listTH"><font size="2">使用期間起迄</font></th>
	<th class="listTH"><font size="2">使用頻率</font></th>
	<th class="listTH"><font size="2">使用方法</font></th>
	<th class="listTH"><font size="2">製造批號或<br>製造日期</font></th>
	<th class="listTH"><font size="2">保存期限</font></th>
	<th class="listTH"><font size="2">購買日期</font></th>
	<th class="listTH">
		<font size="2">
			<input type="button" class="field_btnAdd" onclick="addCOS4005DbRow('tabCOS4005Db');" />
		</font>
	</th>
	</thead>
	
	<tbody id="COS4005DbListTBODY">
	</tbody>
	
	<tfoot id="COS4005DbListTFOOT">
		<tr><td>&nbsp;</td></tr>
	</tfoot>
</table>

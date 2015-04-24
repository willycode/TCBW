<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<script type="text/javascript">
var idCOS0013DbCount = 0;
var arrCOS0013DbFieldName = new Array("receiptDate","postDate13","postDept13","postNo13","postMemo13");
var arrCOS0013DbFieldZhName = new Array("收文日期","公文發文日期","公文發文單位","","");

function addCOS0013DbRow(tblId,v1,v2,v3,v4,v5,v6){
	$("#COS0013DbListTFOOT").remove();
	
	idCOS0013DbCount += 1;
	var tbl = document.getElementById(tblId);
	var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);
	var id = v6!=null?v6:("tmp" + idCOS0013DbCount);
	
    var strReceiptDate = row.insertCell(0);
    strReceiptDate.innerHTML = '<input type=hidden name=COS0013DbRow value=' + id + '><input type=text class=field name=receiptDate' + id + ' size=7 maxlength=7 style="font-size: 10pt" value="' + (v1!=null?v1:'') + '"><button class=field style="font-size: 10px" type="button" name="btn_receiptDate' + id + '" onClick="popCalendar(\'receiptDate' + id + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	
    var strPostDate = row.insertCell(1);
	strPostDate.innerHTML = '<input type=text class=field name=postDate13' + id + ' size=7 maxlength=7 style="font-size: 10pt" value="' + (v2!=null?v2:'') + '"><button class=field style="font-size: 10px" type="button" name="btn_postDate13' + id + '" onClick="popCalendar(\'postDate13' + id + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
    
	var strPostDept = row.insertCell(2);
	strPostDept.innerHTML = '<input type=text class=field name=postDept13' + id + ' size=15 maxlength=20 style="font-size: 10pt" value="' + (v3!=null?v3:'') + '">';
	
	var strPostNo = row.insertCell(3);
	strPostNo.innerHTML = '<input type=text class=field name=postNo13' + id + ' size=15 maxlength=20 style="font-size: 10pt" value="' + (v5!=null?v5:'') + '">';

	var strPostMemo = row.insertCell(4);
	strPostMemo.innerHTML = '<input type=text class=field name=postMemo13' + id + ' size=10 maxlength=10 style="font-size: 10pt" value="' + (v4!=null?v4:'') + '">';
	
	var actionText = row.insertCell(5);
	actionText.align = "center";
	
	var sb = new StringBuffer();
	sb.append('<input style="font-size: 11px" type="button" class="field_btnRemove" value="" onclick="removeRowFromTable(\'' ).append( tblId ).append( '\',this.parentNode.parentNode.rowIndex);createCOS0013DbListTFOOT();">');
	sb.append('<input style="font-size: 11px" type="button" class="field_btnAdd" value="" onclick="copyCOS0013DbRow(\'' ).append( tblId ).append( '\',\'' + id + '\');">');
	actionText.innerHTML = sb.toString();
}

function copyCOS0013DbRow(tblId, rid) {
	var arrFd = new Array(5);
	for(var i=0; i<arrFd.length; i++){		
		if(isObj(document.all.item(arrCOS0013DbFieldName[i] + rid))){
			arrFd[i] = document.all.item(arrCOS0013DbFieldName[i] + rid).value;
		}
	} 	
	addCOS0013DbRow(tblId, arrFd[0], arrFd[1], arrFd[2], arrFd[3], arrFd[4]);
}

function validateCOS0013DbTable() {
	var COS0013DbArray = document.getElementsByName("COS0013DbRow");	
	if(COS0013DbArray!=null && COS0013DbArray.length>0){	
	
		for(var i=0; i<COS0013DbArray.length; i++){
			var id = COS0013DbArray[i].value;
			
			var sb = new StringBuffer();
			for(var j=0; j<arrCOS0013DbFieldName.length; j++){
				var fd	= document.all.item(arrCOS0013DbFieldName[j] + id);
				if(isObj(fd) && arrCOS0013DbFieldZhName[j] != "") {
					sb.append(checkEmpty(fd, arrCOS0013DbFieldZhName[j]));
					if(j == 0 || j == 1){
						sb.append(checkDate(fd, arrCOS0013DbFieldZhName[j]));
					}
				}
			}
			if (sb.toString().length>0) return sb.toString();
		}
	}
	return "";
}

function createCOS0013DbListTFOOT(){
	var tbl = $("#tabCOS0013Db").get(0);
	if(tbl.rows.length == 1){
		var t = $("<tfoot id='COS0013DbListTFOOT'><tr><td>&nbsp;</td></tr></tfoot>");
		$(tbl).append(t);
	}
}
</script>

<table width="100%" class="table_form" cellspacing="0" cellpadding="0" id="tabCOS0013Db">
	<thead id="COS0013DbListTHEAD">
	<th class="listTH"><font size="2">收文日期</font></th>
	<th class="listTH"><font size="2">公文發文日期</font></th>
	<th class="listTH"><font size="2">公文發文單位</font></th>
	<th class="listTH"><font size="2">公文發文文號</font></th>
	<th class="listTH"><font size="2">內容摘要</font></th>
	<th class="listTH">
		<font size="2">
			<input type="button" class="field_btnAdd" onclick="addCOS0013DbRow('tabCOS0013Db');" />
		</font>
	</th>
	</thead>
	
	<tbody id="COS0013DbListTBODY">
	</tbody>
	
	<tfoot id="COS0013DbListTFOOT">
		<tr><td>&nbsp;</td></tr>
	</tfoot>
</table>

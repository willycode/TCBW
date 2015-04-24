<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<script type="text/javascript">

var idCOS4004DbCount = 0;
var arrCOS4004DbFieldName = new Array("testDate","testItems","testNum");
var arrCOS4004DbFieldZhName = new Array("檢驗日期","","");

function addCOS4004DbRow(tblId,v1,v2,v3,v4){
	$("#COS4004DbListTFOOT").remove();
	
	idCOS4004DbCount += 1;
	var tbl = document.getElementById(tblId);
	var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);
	var id = v4!=null?v4:("tmp" + idCOS4004DbCount);
	
    var strTestDate = row.insertCell(0);
    strTestDate.innerHTML = '<input type=hidden name=COS4004DbRow value=' + id + '><input type=text class=field name=testDate' + id + ' size=7 maxlength=7 style="font-size: 10pt" value="' + (v1!=null?v1:'') + '"><button class=field style="font-size: 10px" type="button" name="btn_testDate' + id + '" onClick="popCalendar(\'testDate' + id + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	
	var strTestItems = row.insertCell(1);
	strTestItems.innerHTML = '<input type=text class=field name=testItems' + id + ' size=30 maxlength=50 style="font-size: 10pt" value="' + (v2!=null?v2:'') + '">';

	var strTestNum = row.insertCell(2);
	strTestNum.innerHTML = '<input type=text class=field name=testNum' + id + ' size=30 maxlength=50 style="font-size: 10pt" value="' + (v3!=null?v3:'') + '">';
		
	var actionText = row.insertCell(3);
	actionText.align = "center";
	
	var sb = new StringBuffer();
	sb.append('<input style="font-size: 11px" type="button" class="field_btnRemove" value="" onclick="removeRowFromTable(\'' ).append( tblId ).append( '\',this.parentNode.parentNode.rowIndex);createCOS4004DbListTFOOT();">');
	sb.append('<input style="font-size: 11px" type="button" class="field_btnAdd" value="" onclick="copyCOS4004DbRow(\'' ).append( tblId ).append( '\',\'' + id + '\');">');
	actionText.innerHTML = sb.toString();
}

function copyCOS4004DbRow(tblId, rid) {
	var arrFd = new Array(3);
	for(var i=0; i<arrFd.length; i++){		
		if(isObj(document.all.item(arrCOS4004DbFieldName[i] + rid))){
			arrFd[i] = document.all.item(arrCOS4004DbFieldName[i] + rid).value;
		}
	} 	
	addCOS4004DbRow(tblId, arrFd[0], arrFd[1], arrFd[2]);
}

function validateCOS4004DbTable() {
	var COS4004DbArray = document.getElementsByName("COS4004DbRow");	
	if(COS4004DbArray!=null && COS4004DbArray.length>0){	
	
		for(var i=0; i<COS4004DbArray.length; i++){
			var id = COS4004DbArray[i].value;
			
			var sb = new StringBuffer();
			for(var j=0; j<arrCOS4004DbFieldName.length; j++){
				var fd	= document.all.item(arrCOS4004DbFieldName[j] + id);
				if(isObj(fd) && arrCOS4004DbFieldZhName[j] != "") {
					sb.append(checkDate(fd, arrCOS4004DbFieldZhName[j]));
				}
			}
			if (sb.toString().length>0) return sb.toString();
		}
	}
	return "";
}

function createCOS4004DbListTFOOT(){
	var tbl = $("#tabCOS4004Db").get(0);
	if(tbl.rows.length == 1){
		var t = $("<tfoot id='COS4004DbListTFOOT'><tr><td>&nbsp;</td></tr></tfoot>");
		$(tbl).append(t);
	}
}
</script>

<table width="100%" class="table_form" cellspacing="0" cellpadding="0" id="tabCOS4004Db">
	<thead id="COS4004DbListTHEAD">
	<th class="listTH"><font size="2">檢驗日期</font></th>
	<th class="listTH"><font size="2">檢驗項目</font></th>
	<th class="listTH"><font size="2">檢驗數據</font></th>
	<th class="listTH">
		<font size="2">
			<input type="button" class="field_btnAdd" onclick="addCOS4004DbRow('tabCOS4004Db');" />
		</font>
	</th>
	</thead>
	
	<tbody id="COS4004DbListTBODY">
	</tbody>
	
	<tfoot id="COS4004DbListTFOOT">
		<tr><td>&nbsp;</td></tr>
	</tfoot>
</table>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<script type="text/javascript">

var idCOS0004DbCount = 0;
var arrCOS0004DbFieldName = new Array("testDate","testItems","testNum");
var arrCOS0004DbFieldZhName = new Array("檢驗日期","","");

function addCOS0004DbRow(tblId,v1,v2,v3,v4){
	$("#COS0004DbListTFOOT").remove();
	
	idCOS0004DbCount += 1;
	var tbl = document.getElementById(tblId);
	var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);
	var id = v4!=null?v4:("tmp" + idCOS0004DbCount);
	
    var strTestDate = row.insertCell(0);
    strTestDate.innerHTML = '<input type=hidden name=COS0004DbRow value=' + id + '><input type=text class=field name=testDate' + id + ' size=7 maxlength=7 style="font-size: 10pt" value="' + (v1!=null?v1:'') + '"><button class=field style="font-size: 10px" type="button" name="btn_testDate' + id + '" onClick="popCalendar(\'testDate' + id + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	
	var strTestItems = row.insertCell(1);
	strTestItems.innerHTML = '<input type=text class=field name=testItems' + id + ' size=30 maxlength=50 style="font-size: 10pt" value="' + (v2!=null?v2:'') + '">';

	var strTestNum = row.insertCell(2);
	strTestNum.innerHTML = '<input type=text class=field name=testNum' + id + ' size=30 maxlength=50 style="font-size: 10pt" value="' + (v3!=null?v3:'') + '">';
		
	var actionText = row.insertCell(3);
	actionText.align = "center";
	
	var sb = new StringBuffer();
	sb.append('<input style="font-size: 11px" type="button" class="field_btnRemove" value="" onclick="removeRowFromTable(\'' ).append( tblId ).append( '\',this.parentNode.parentNode.rowIndex);createCOS0004DbListTFOOT();">');
	sb.append('<input style="font-size: 11px" type="button" class="field_btnAdd" value="" onclick="copyCOS0004DbRow(\'' ).append( tblId ).append( '\',\'' + id + '\');">');
	actionText.innerHTML = sb.toString();
}

function copyCOS0004DbRow(tblId, rid) {
	var arrFd = new Array(3);
	for(var i=0; i<arrFd.length; i++){		
		if(isObj(document.all.item(arrCOS0004DbFieldName[i] + rid))){
			arrFd[i] = document.all.item(arrCOS0004DbFieldName[i] + rid).value;
		}
	} 	
	addCOS0004DbRow(tblId, arrFd[0], arrFd[1], arrFd[2]);
}

function validateCOS0004DbTable(){
	var COS0004DbArray = document.getElementsByName("COS0004DbRow");	
	if(COS0004DbArray!=null && COS0004DbArray.length>0){	
	
		for(var i=0; i<COS0004DbArray.length; i++){
			var id = COS0004DbArray[i].value;
			
			var sb = new StringBuffer();
			for(var j=0; j<arrCOS0004DbFieldName.length; j++){
				var fd	= document.all.item(arrCOS0004DbFieldName[j] + id);
				if(isObj(fd) && arrCOS0004DbFieldZhName[j] != "") {
					sb.append(checkDate(fd, arrCOS0004DbFieldZhName[j]));
				}
			}
			if (sb.toString().length>0) return sb.toString();
		}
	}
	return "";
}

function createCOS0004DbListTFOOT(){
	var tbl = $("#tabCOS0004Db").get(0);
	if(tbl.rows.length == 1){
		var t = $("<tfoot id='COS0004DbListTFOOT'><tr><td>&nbsp;</td></tr></tfoot>");
		$(tbl).append(t);
	}
}
</script>

<table width="100%" class="table_form" cellspacing="0" cellpadding="0" id="tabCOS0004Db">
	<thead id="COS0004DbListTHEAD">
	<th class="listTH"><font size="2">檢驗日期</font></th>
	<th class="listTH"><font size="2">檢驗項目</font></th>
	<th class="listTH"><font size="2">檢驗數據</font></th>
	<th class="listTH">
		<font size="2">
			<input type="button" class="field_btnAdd" onclick="addCOS0004DbRow('tabCOS0004Db');" />
		</font>
	</th>
	</thead>
	
	<tbody id="COS0004DbListTBODY">
	</tbody>
	
	<tfoot id="COS0004DbListTFOOT">
		<tr><td>&nbsp;</td></tr>
	</tfoot>
</table>

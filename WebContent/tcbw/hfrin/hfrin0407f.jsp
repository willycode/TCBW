<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<script type="text/javascript">
var idDCount = 0;
var arrDiscriptFieldName = new Array("discriptDate","position","symptom","severity","doResponse");
var arrDiscriptFieldZhName = new Array("發生日期","","","","");

function addDiscript(tblId,v1,v2,v3,v4,v5,v6){
	idDCount += 1;
	var tbl = document.getElementById(tblId);
	var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);
	var id = v6!=null?v6:("dTmp" + idDCount);
	
    var strDiscriptDate = row.insertCell(0);
    strDiscriptDate.innerHTML = '<input type=hidden name=discriptRow value=' + id + '><input type=text class=field name=discriptDate' + id + ' size=7 maxlength=7 style="font-size: 10px" value="' + (v1!=null?v1:'') + '"><button class=field style="font-size: 10px" type="button" name="btn_discriptDate' + id + '" onclick="popCalendar(\'discriptDate' + id + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	
	var strPosition = row.insertCell(1);
	strPosition.innerHTML = '<input type=text class=field name=position' + id + ' size=40 maxlength=90 style="font-size: 10px" value="' + (v2!=null?v2:'') + '">';

	var strSymptom = row.insertCell(2);
	strSymptom.innerHTML = '<input type=text class=field name=symptom' + id + ' size=40 maxlength=90 style="font-size: 10px" value="' + (v3!=null?v3:'') + '">';
	
	var strSeverity = row.insertCell(3);
	strSeverity.innerHTML = '<input type=text class=field name=severity' + id + ' size=40 maxlength=90 style="font-size: 10px" value="' + (v4!=null?v4:'') + '">';
	
	var strDoResponse = row.insertCell(4);
	strDoResponse.innerHTML = '<input type=text class=field name=doResponse' + id + ' size=40 maxlength=90 style="font-size: 10px" value="' + (v5!=null?v5:'') + '">';
			
	var actionText = row.insertCell(5);
	actionText.align = "center";
	
	var sb = new StringBuffer();
	sb.append('<input style="font-size: 11px" type="button" class="field_btnRemove" value="" onclick="removeRowFromTable(\'' ).append( tblId ).append( '\',this.parentNode.parentNode.rowIndex);">');
	sb.append('<input style="font-size: 11px" type="button" class="field_btnAdd" value="" onclick="copyDiscript(\'' ).append( tblId ).append( '\',\'' + id + '\');">');
	actionText.innerHTML = sb.toString();
}

function copyDiscript(tblId, rid) {
	var arrFd = new Array(5);
	for(var i=0; i<arrFd.length; i++){		
		if(isObj(document.all.item(arrDiscriptFieldName[i] + rid))){
			arrFd[i] = document.all.item(arrDiscriptFieldName[i] + rid).value;
		}
	} 	
	addDiscript(tblId, arrFd[0], arrFd[1], arrFd[2], arrFd[3], arrFd[4]);
}

function clearDiscriptTable(){
	var tbl = document.getElementById('tabDiscript');
	var lastRow = tbl.rows.length;
	if(lastRow > 1){
		for(var i=1; i<lastRow; i++){		
			tbl.deleteRow(lastRow-i);
		}
	}
}

function validateDiscriptTable() {
	var dArray = document.getElementsByName("discriptRow");	
	if(dArray!=null && dArray.length>0){	
		for(var i=0; i<dArray.length; i++){
			var id = dArray[i].value;
			
			var sb = new StringBuffer();
			for(var j=0; j<arrDiscriptFieldName.length; j++){
				var fd	= document.all.item(arrDiscriptFieldName[j] + id);
				if(isObj(fd) && arrDiscriptFieldZhName[j]!="") {
					sb.append(checkEmpty(fd, arrDiscriptFieldZhName[j]));
					sb.append(checkDate(fd, arrDiscriptFieldZhName[j]));
				}
			}
			if (sb.toString().length>0) return sb.toString();
		}
	}
	return "";
}
</script>

<table width="100%" class="table_form" cellspacing="0" cellpadding="0" id="tabDiscript">
	<thead id="DiscriptlistTHEAD">
	<th class="listTH"><font size="2">發生日期</font></th>
	<th class="listTH"><font size="2">部位</font></th>
	<th class="listTH"><font size="2">症狀</font></th>
	<th class="listTH"><font size="2">嚴重程度</font></th>
	<th class="listTH"><font size="2">處置</font></th>
	<th class="listTH">
		<font size="2">
			<input type="button" class="field_btnAdd" onclick="addDiscript('tabDiscript');" />
		</font>
	</th>
	</thead>
	
	<tbody id="DiscriptlistTBODY">
	</tbody>
</table>

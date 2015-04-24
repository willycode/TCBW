<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<script type="text/javascript">
var idDTCount = 0;
var arrDoctorFieldName = new Array("doctorDate","bloodIndex","liverIndex","renalIndex");
var arrDoctorFieldZhName = new Array("醫師診斷日期","","","","");

function addDoctor(tblId,v1,v2,v3,v4,v5){
	idDTCount += 1;
	var tbl = document.getElementById(tblId);
	var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);
	var id = v5!=null?v5:("DTTmp" + idDTCount);
	
    var strDoctorDate = row.insertCell(0);
    strDoctorDate.innerHTML = '<input type=hidden name=doctorRow value=' + id + '><input type=text class=field name=doctorDate' + id + ' size=7 maxlength=7 style="font-size: 10px" value="' + (v1!=null?v1:'') + '"><button class=field style="font-size: 10px" type="button" name="btn_doctorDate' + id + '" onclick="popCalendar(\'doctorDate' + id + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	
	var strBloodIndex = row.insertCell(1);
	strBloodIndex.innerHTML = '<input type=text class=field name=bloodIndex' + id + ' size=15 maxlength=20 style="font-size: 10px" value="' + (v2!=null?v2:'') + '">';

	var strLiverIndex = row.insertCell(2);
	strLiverIndex.innerHTML = '<input type=text class=field name=liverIndex' + id + ' size=15 maxlength=20 style="font-size: 10px" value="' + (v3!=null?v3:'') + '">';
	
	var strRenalIndex = row.insertCell(3);
	strRenalIndex.innerHTML = '<input type=text class=field name=renalIndex' + id + ' size=15 maxlength=20 style="font-size: 10px" value="' + (v4!=null?v4:'') + '">';
			
	var actionText = row.insertCell(4);
	actionText.align = "center";
	
	var sb = new StringBuffer();
	sb.append('<input style="font-size: 11px" type="button" class="field_btnRemove" value="" onclick="removeRowFromTable(\'' ).append( tblId ).append( '\',this.parentNode.parentNode.rowIndex);">');
	sb.append('<input style="font-size: 11px" type="button" class="field_btnAdd" value="" onclick="copyDoctor(\'' ).append( tblId ).append( '\',\'' + id + '\');">');
	actionText.innerHTML = sb.toString();
}

function copyDoctor(tblId, rid) {
	var arrFd = new Array(4);
	for(var i=0; i<arrFd.length; i++){		
		if(isObj(document.all.item(arrDiscriptFieldName[i] + rid))){
			arrFd[i] = document.all.item(arrDiscriptFieldName[i] + rid).value;
		}
	} 	
	addDoctor(tblId, arrFd[0], arrFd[1], arrFd[2], arrFd[3]);
}

function clearDoctorTable(){
	var tbl = document.getElementById('tabDoctor');
	var lastRow = tbl.rows.length;
	if(lastRow > 1){
		for(var i=1; i<lastRow; i++){		
			tbl.deleteRow(lastRow-i);
		}
	}
}

function validateDoctorTable() {
	var dtArray = document.getElementsByName("doctorRow");	
	if(dtArray!=null && dtArray.length>0){	
		for(var i=0; i<dtArray.length; i++){
			var id = dtArray[i].value;
			
			var sb = new StringBuffer();
			for(var j=0; j<arrDoctorFieldName.length; j++){
				var fd	= document.all.item(arrDoctorFieldName[j] + id);
				if(isObj(fd) && arrDoctorFieldZhName[j]!="") {
					sb.append(checkEmpty(fd, arrDoctorFieldZhName[j]));
					sb.append(checkDate(fd, arrDoctorFieldZhName[j]));
				}
			}
			if (sb.toString().length>0) return sb.toString();
		}
	}
	return "";
}
</script>

<table width="100%" class="table_form" cellspacing="0" cellpadding="0" id="tabDoctor">
	<thead id="DoctorlistTHEAD">
	<th class="listTH"><font size="2">醫師診斷日期</font></th>
	<th class="listTH"><font size="2">血中濃度</font></th>
	<th class="listTH"><font size="2">肝功能指數</font></th>
	<th class="listTH"><font size="2">腎功能指數</font></th>
	<th class="listTH">
		<font size="2">
			<input type="button" class="field_btnAdd" onclick="addDoctor('tabDoctor');" />
		</font>
	</th>
	</thead>
	
	<tbody id="DoctorlistTBODY">
	</tbody>
</table>

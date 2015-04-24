<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript">
var idDTCount = 0;
var arrDoctorFieldName = new Array("doctorDate","bloodIndex","liverIndex");
var arrDoctorFieldZhName = new Array("醫師診斷日期","","");

function addDoctor(tblId,v1,v2,v3,v4){
	$("#DoctorlistTFOOT").remove();
	
	if(v4!=null && v4!=""){
		if(v4.indexOf("DTTmp") != -1){
			idDTCount = parseInt(v4.substring(5, v4.length));
		}
	}else{
		idDTCount += 1;
	}
	
	var tbl = document.getElementById(tblId);
	var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);
	var id = v4!=null?v4:("DTTmp" + idDTCount);
	
    var strDoctorDate = row.insertCell(0);
    strDoctorDate.innerHTML = '<input type=hidden name=doctorRow value=' + id + '><input type=text class=field name=doctorDate' + id + ' size=7 maxlength=7 style="font-size: 12pt" value="' + (v1!=null?v1:'') + '"><button class=field style="font-size: 12px" type="button" name="btn_doctorDate' + id + '" onclick="popCalendar(\'doctorDate' + id + '\')" title="萬年曆輔助視窗"><image src="../../images/dynCalendar.gif"></button>';
	
	var strBloodIndex = row.insertCell(1);
	strBloodIndex.innerHTML = '<input type=text class=field name=bloodIndex' + id + ' size=15 maxlength=20 style="font-size: 12pt" value="' + (v2!=null?v2:'') + '">';

	var strLiverIndex = row.insertCell(2);
	strLiverIndex.innerHTML = '<input type=text class=field name=liverIndex' + id + ' size=15 maxlength=20 style="font-size: 12pt" value="' + (v3!=null?v3:'') + '">';
			
	var actionText = row.insertCell(3);
	actionText.align = "center";
	
	var sb = new StringBuffer();
	sb.append('<input style="font-size: 11px" type="button" class="field_btnRemove" value="" onclick="removeRowFromTable(\'' ).append( tblId ).append( '\',this.parentNode.parentNode.rowIndex);createDoctorTFOOT();">');
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
	addDoctor(tblId, arrFd[0], arrFd[1], arrFd[2]);
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
					var tmpS = checkDate(fd, arrDoctorFieldZhName[j]);
					if(tmpS.length == 0){
						if(fd.value > yyymmdd){
							sb.append(arrDoctorFieldZhName[j] + msgS);
							fd.style.backgroundColor = errorbg;
						}else{
							fd.style.backgroundColor = "";
						}
					}else{
						sb.append(tmpS);
					}
				}
			}
			if (sb.toString().length>0) return sb.toString();
		}
	}
	return "";
}

function createDoctorTFOOT(){
	var tbl = $("#tabDoctor").get(0);
	if(tbl.rows.length == 1){
		var t = $("<tfoot id='DoctorlistTFOOT'><tr><td>&nbsp;</td></tr></tfoot>");
		$(tbl).append(t);
	}
}
</script>

<table width="100%" class="table_form" cellspacing="0" cellpadding="0" id="tabDoctor">
	<thead id="DoctorlistTHEAD">
	<th class="listTH"><font size="2">檢驗日期</font></th>
	<th class="listTH"><font size="2">檢驗項目</font></th>
	<th class="listTH"><font size="2">檢驗數據</font></th>
	<th class="listTH">
		<font size="2">
			<input type="button" class="field_btnAdd" onclick="addDoctor('tabDoctor');" />
		</font>
	</th>
	</thead>
	
	<tbody id="DoctorlistTBODY">
	
	</tbody>
	<tfoot id="DoctorlistTFOOT">
		<tr><td>&nbsp;</td></tr>
	</tfoot>
</table>

<script type="text/javascript">
function checkDoctorlistTFOOT(){
	var tbl = $("#tabDoctor").get(0);
	if(tbl.rows.length==2 && $("#DoctorlistTFOOT").html()!=null){
		addDoctor('tabDoctor');
	}
}
</script>



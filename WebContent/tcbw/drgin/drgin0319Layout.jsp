<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../../home/head.jsp" %>
<script type="text/javascript">
var arrCon19Field = new Array("medAgencyName", "engageKind", "medAgencyKind");
var arrCon19FieldName = new Array("醫事機構名稱","特約類別","醫事機構種類");
function addCon19Row(tblId,v1,v2,v3,v4){
	var tbl = document.getElementById(tblId);
	var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);
	var id = row.uniqueID?row.uniqueID:randomUUID();
	
	var medNameText = row.insertCell(0);
	medNameText.innerHTML = "<input type=hidden name=con19Row value=" + id + ">" +
	'<input type=text class="field_RO" name="medAgencyName' + id + '" size=30 maxlength=100 value="' + (v1!=null?v1:'') + '">';

	var engKindText = row.insertCell(1);
	engKindText.innerHTML = '<input type=text class="field_RO" name="engageKind' + id + '" size=10 maxlength=10 value="' + (v2!=null?v2:'') + '">';

	var medKindText = row.insertCell(2);
	medKindText.innerHTML = '<input type=text class="field_RO" name="medAgencyKind' + id + '" size=10 maxlength=10 value="' + (v3!=null?v3:'') + '">';

	var actionText = row.insertCell(3);
	actionText.align = 'center';
	var sb = new StringBuffer();
	sb.append('<input type="button" class="field_btnRemove" name="btn_con19Remove' + id + '" value="" onclick="removeRowFromTable(\'' ).append( tblId ).append( '\',this.parentNode.parentNode.rowIndex);">');
	sb.append('<input type=hidden name=con19Id').append(id).append(' value=' ).append( v4!=null?v4:'' ).append('>');		
	actionText.innerHTML = sb.toString();
}

function clearCon19Table(){
	var tbl = document.getElementById('con19Table');
	if (isObj(opener))
	{
		if(null != opener.document.getElementById('con19Table') && "" != opener.document.getElementById('con19Table'))
		{
			tbl = opener.document.getElementById('con19Table');
		}
	}
	var lastRow = tbl.rows.length;
	if (lastRow > 0) {
		while ( tbl.rows.length != 1 )
		{
			tbl.deleteRow(tbl.rows.length-1);
		}
	}
}

function validateCon19Table() {
	var sb = new StringBuffer();
	var con19Row=document.getElementsByName("con19Row");	
	if (con19Row!=null && con19Row.length>0) {
		for (var i=0; i<con19Row.length; i++) {
			var id = con19Row[i].value;
			var sb = new StringBuffer();
			for (var j=0; j<arrCon19Field.length; j++) {
				var fd	= document.all.item(arrCon19Field[j]+id);
				if (isObj(fd) && arrCon19FieldName[j]!='') {
					sb.append(checkEmpty(fd,arrCon19FieldName[j]));
				}
				
			}
		}
		if (sb.toString().length>0) return sb.toString();
	}
	return "";
}

function popCon1009DbForm() {
	var q_con19Ids = "";
	var con19Row=document.getElementsByName("con19Row");	
	if (con19Row!=null && con19Row.length>0) {
		for (var i=0; i<con19Row.length; i++) {
			var id = con19Row[i].value;
			if(q_con19Ids.length > 0) q_con19Ids += ",";
			q_con19Ids += getObjectValue("con19Id"+id);
		}
	}
	var prop="";
	var windowTop=180; //(document.body.clientHeight-400)/2+180;
	var windowLeft=250; //(document.body.clientWidth-400)/2+250;
	prop=prop+"resizable=yes,width=800,height=420,";
	prop=prop+"top="+windowTop+",";
	prop=prop+"left="+windowLeft+",";
	prop=prop+"scrollbars=yes";
	window.open("../../home/popCon1009.jsp?q_con19Ids=" + q_con19Ids,"",prop);
}

function setCon19Readonly() {
	var sb = new StringBuffer();
	var con19Row=document.getElementsByName("con19Row");	
	if (con19Row!=null && con19Row.length>0) {
		for (var i=0; i<con19Row.length; i++) {
			var id = con19Row[i].value;
			var sb = new StringBuffer();
			for (var j=0; j<arrCon19Field.length; j++) {
				document.all.item(arrCon19Field[j]+id).disabled = true;
			}
			document.all.item("btn_con19Remove"+id).style.display = "none";
		}
	}
	document.all.item("btn_con19Add").style.display = "none";
}

</script>
	<table class="table_form" width="100%" cellspacing="0" cellpadding="0" id="con19Table" onmouseover="this.className='tableMouseover';" onmouseout="this.className='tableMouseout';">
	  <thead id="listTHEAD">
	  	<th class="listTH">醫事機構名稱</th> 
	  	<th class="listTH">特約類別</th> 
	  	<th class="listTH">醫事機構種類</th>
	  	<th class="listTH" width="10%"><input type="button" class="field_btnAdd" name="btn_con19Add" onclick="popCon1009DbForm();" /></th>	 
	  </thead>
	  <tbody id="listTBODY">
	  </tbody>
	</table>	
